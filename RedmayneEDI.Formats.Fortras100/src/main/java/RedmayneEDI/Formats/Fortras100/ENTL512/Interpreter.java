package RedmayneEDI.Formats.Fortras100.ENTL512;

import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.ENTL512.Models.*;
import RedmayneEDI.Formats.Fortras100.ENTL512.SegmentGroups.*;

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

        // Third Pass; Populate the ENTL object from the nested lines
        HashMap<Integer, Integer> loadingUnitsMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> uezsMap = new HashMap<Integer, Integer>();
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
                else if (line_header.equals("M00"))
                {
                    fortrasDocument.M00.Parse(chunks[2]);
                }
                else if (line_header.equals("Z00"))
                {
                    fortrasDocument.Z00.Parse(chunks[2]);
                }
                else if (line_header.equals("@@PT"))
                {
                    fortrasDocument.PT.Parse(chunks[2]);
                }
                else if (nodes[0].startsWith("LOADING_UNITS_GROUP"))
                {
                    int consignment_position = Integer.parseInt(Formatting.RegexFindFirst(nodes[0], "\\d+"));
                    if (!loadingUnitsMap.containsKey(consignment_position))
                    {
                        fortrasDocument.LOADING_UNITS.add(new LOADING_UNIT());
                        loadingUnitsMap.put(consignment_position, fortrasDocument.LOADING_UNITS.size() - 1);
                    }
                    var consignment = fortrasDocument.LOADING_UNITS.get(loadingUnitsMap.get(consignment_position));
                    if (nodes[1].equals("M10"))
                    {
                        consignment.M10.Parse(chunks[2]);
                    }
                    if (nodes[1].equals("M20"))
                    {
                        consignment.M20.Parse(chunks[2]);
                    }
                }
                if (nodes[0].equals("N00"))
                {
                    var n00 = new N00();
                    n00.Parse(chunks[2]);
                    fortrasDocument.N00.add(n00);
                }
                else if (nodes[0].startsWith("UEZ_GROUP"))
                {
                    int consignment_position = Integer.parseInt(Formatting.RegexFindFirst(nodes[0], "\\d+"));
                    if (!uezsMap.containsKey(consignment_position))
                    {
                        fortrasDocument.UEZS.add(new UEZ());
                        uezsMap.put(consignment_position, fortrasDocument.UEZS.size() - 1);
                    }
                    var consignment = fortrasDocument.UEZS.get(uezsMap.get(consignment_position));
                    if (nodes[1].equals("O00"))
                    {
                        var o00 = new O00();
                        o00.Parse(chunks[2]);
                        consignment.O00.add(o00);
                    }
                    if (nodes[1].equals("O10"))
                    {
                        var o10 = new O10();
                        o10.Parse(chunks[2]);
                        consignment.O10.add(o10);
                    }
                    if (nodes[1].equals("O20"))
                    {
                        var o20 = new O20();
                        o20.Parse(chunks[2]);
                        consignment.O20.add(o20);
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
