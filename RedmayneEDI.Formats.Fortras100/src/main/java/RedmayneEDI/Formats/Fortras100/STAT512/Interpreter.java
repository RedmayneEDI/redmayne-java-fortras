package RedmayneEDI.Formats.Fortras100.STAT512;

import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.STAT512.Models.*;
import RedmayneEDI.Formats.Fortras100.STAT512.SegmentGroups.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Interpreter
{
    /// <summary>
    /// The character pattern used to separate elements of the document map.
    /// </summary>
    private final String lineSeparator = "@#//";

    /// <summary>
    /// The character pattern used to substitue EOL characters during initial cleanup.
    /// </summary>
    private final String tempLineBreak = "@~#CRLF@~#";

    /// <summary>
    /// The Encoding used for reading in files. If not set, the system Default value will be used.
    /// </summary>
    public Charset FileFormatEncoding = null;

    public FortrasDocument ReadFile(String filePath)
    {
        try
        {
            byte[] encoded = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath));
            if (FileFormatEncoding == null) FileFormatEncoding = Charset.defaultCharset();
            return ReadString(new String(encoded, FileFormatEncoding));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public FortrasDocument ReadString(String body) throws Exception
    {
        var fortrasDocument = new FortrasDocument();

        ArrayList<String> newLines = new ArrayList<String>();

        String lb = Formatting.GetEOLFormat(body);
        if (lb.equals("")) { throw new Exception("The document does not contain any recognisable line break characters!"); }

        // The conversion steps here help remove situations where data fields may contain line breaks that are not the file's EOL character.
        body = body.replaceAll(lb, tempLineBreak).replaceAll("\r", "").replaceAll("\n", "").replaceAll(tempLineBreak, lb);

        // Break the document into lines.
        String[] lines = body.trim().split(lb);

        // A Line counter to track our position for logging
        int l = 0;

        // Document conversion requires a number of operational passes.
        // First Pass; check and validate all lines in the file to make sure the lines are expected in the document and build an initial document map.
        // Second Pass; Scan the document map for nested elements and group them together.
        // Third Pass; Populate the document model from the grouped map.

        // First Pass; We loop through the document lines to identify any incorrect lines and add the nesting markup
        ArrayList<String> validatedLines = new ArrayList<String>();
        for (String line : lines)
        {
            l++;

            if (line.isBlank())
            {
                throw new Exception("Line " + l + " does not contain any data.");
            }

            String line_header = line.substring(0, 3);
            if (line.startsWith("@")) { line_header = line.substring(0, 4); }

            if (!fortrasDocument.ValidLineIdentifier(line))
            {
                throw new Exception("Line " + l + " has an unrecognised Record Type of [" + line_header + "].");
            }

            String fortrasPath = fortrasDocument.GetPathToLine(line_header);

            validatedLines.add(fortrasPath + lineSeparator + line_header + lineSeparator + line.substring(line_header.length()));
        }

        // Second Pass; Create sequential nesting
        ArrayList<String> nestedLines = new ArrayList<String>();
        HashMap<String, Integer> nodeTracker = new HashMap<String, Integer>();
        String lastLine = "";
        String lastPath = "";

        // We'll loop through each validated line and check that any "_GROUP" lines are numbered.
        // This will help ensure the finished XML is correctly grouped together.
        for (String line : validatedLines)
        {
            String[] chunks = line.split(lineSeparator);
            String line_header = chunks[1];
            String[] nodes = chunks[0].split("/");

            for (int i = 0; i < nodes.length; i++)
            {
                // Prepare any grouping for index values to be added
                if (nodes[i].toUpperCase().contains("_GROUP"))
                {
                    if (!nodeTracker.containsKey(nodes[i])) { nodeTracker.put(nodes[i], 0); }
                }
                if (nodes[i].equals(chunks[1]))
                {
                    if (lastLine.compareTo(chunks[1]) > 0)
                    {
                        String common = fortrasDocument.FindCommonParent(lastPath, nodes);
                        if (!common.isBlank()) { nodeTracker.put(common, nodeTracker.get(common)+1); }
                    }
                    else if (lastLine.equals(chunks[1]))
                    {
                        // If this line is equal to the last, check to see if it should be unique
                        if (fortrasDocument.MaxOccurance(line_header) == 1)
                        {
                            //NOTE: usage of _LAST suffix to cause the two line identifiers to be different to one another,
                            // otherwise the function finds the current node not the parent.
                            String common = fortrasDocument.FindCommonParent(lastPath + "_LAST", nodes);
                            if (!common.isBlank()) { nodeTracker.put(common, nodeTracker.get(common)+1); }
                        }
                    }
                    lastLine = chunks[1];
                    lastPath = chunks[0];
                }
            }

            // Make sure that all nesting is numbered correctly
            String newGrouping = chunks[0];
            for ( Map.Entry<String, Integer> entry : nodeTracker.entrySet() )
            {
                newGrouping = newGrouping.replaceAll(entry.getKey(), entry.getKey() + entry.getValue());
            }

            nestedLines.add(line.replaceAll(chunks[0], newGrouping));
        }

        // Third Pass; Populate the STAT object from the nested lines
        HashMap<Integer, Integer> consignmentMap = new HashMap<Integer, Integer>();
        HashMap<Integer, HashMap<Integer, Integer>> consignmentBarcodesMap = new HashMap<Integer, HashMap<Integer, Integer>>();
        for (String line : nestedLines)
        {
            try
            {
                String[] chunks = line.split(lineSeparator, -1);
                String line_header = chunks[1];
                String[] nodes = chunks[0].split("/", -1);

                if (line_header.equals("@@PH"))
                {
                    fortrasDocument.PH.Parse(chunks[2]);
                }
                else if (line_header.equals("Q00"))
                {
                    fortrasDocument.Q00.Parse(chunks[2]);
                }
                else if (line_header.equals("Z00"))
                {
                    fortrasDocument.Z00.Parse(chunks[2]);
                }
                else if (line_header.equals("@@PT"))
                {
                    fortrasDocument.PT.Parse(chunks[2]);
                }
                else if (nodes[0].startsWith("CONSIGNMENT_GROUP"))
                {
                    int consignment_position = Integer.parseInt(Formatting.RegexFindFirst(nodes[0], "\\d+"));
                    if (!consignmentMap.containsKey(consignment_position))
                    {
                        fortrasDocument.CONSIGNMENTS.add(new CONSIGNMENT());
                        consignmentMap.put(consignment_position, fortrasDocument.CONSIGNMENTS.size() - 1);
                        consignmentBarcodesMap.put(consignment_position, new HashMap<Integer, Integer>());
                    }
                    var consignment = fortrasDocument.CONSIGNMENTS.get(consignmentMap.get(consignment_position));

                    if (nodes[1].equals("Q10"))
                    {
                        consignment.Q10.Parse(chunks[2]);
                    }
                    if (nodes[1].equals("Q11"))
                    {
                        var q11 = new Q11();
                        q11.Parse(chunks[2]);
                        consignment.Q11.add(q11);
                    }
                    if (nodes[1].startsWith("BARCODE_GROUP"))
                    {
                        int barcode_position = Integer.parseInt(Formatting.RegexFindFirst(nodes[1], "\\d+"));
                        if (!consignmentBarcodesMap.get(consignment_position).containsKey(barcode_position))
                        {
                            consignment.BARCODES.add(new BARCODE());
                            consignmentBarcodesMap.get(consignment_position).put(barcode_position, consignment.BARCODES.size() - 1);
                        }
                        var barcode = consignment.BARCODES.get(consignmentBarcodesMap.get(consignment_position).get(barcode_position));
                        if (nodes[2].equals("Q20"))
                        {
                            barcode.Q20.Parse(chunks[2]);
                        }
                        if (nodes[2].equals("Q30"))
                        {
                            var q30 = new Q30();
                            q30.Parse(chunks[2]);
                            barcode.Q30.add(q30);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        return fortrasDocument;
    }
}
