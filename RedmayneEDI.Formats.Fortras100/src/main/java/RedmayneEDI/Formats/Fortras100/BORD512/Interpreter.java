package RedmayneEDI.Formats.Fortras100.BORD512;

import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.BORD512.Models.*;
import RedmayneEDI.Formats.Fortras100.BORD512.SegmentGroups.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/// <summary>
/// An Interpreter object which will consume a file or string Fortras BORD512 message and return a BORD FortrasDocument type.
/// </summary>
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
        HashMap<String,Integer> nodeTracker = new HashMap<String,Integer>();
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
            for ( Map.Entry<String, Integer> entry : nodeTracker.entrySet() ) {
                newGrouping = newGrouping.replaceAll(entry.getKey(), entry.getKey() + entry.getValue());
            }

            nestedLines.add(line.replaceAll(chunks[0], newGrouping));
        }

        // Third Pass; Populate the BORD object from the nested lines
        HashMap<Integer, Integer> consignmentMap = new HashMap<Integer, Integer>();
        HashMap<Integer, HashMap<Integer, Integer>> consignmentAddressesMap = new HashMap<Integer, HashMap<Integer, Integer>>();
        HashMap<Integer, HashMap<Integer, Integer>> consignmentLinesMap = new HashMap<Integer, HashMap<Integer, Integer>>();
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
                else if (line_header.equals("A00"))
                {
                    fortrasDocument.A00.Parse(chunks[2]);
                }
                else if (line_header.equals("A10"))
                {
                    fortrasDocument.A10 = new A10();
                    fortrasDocument.A10.Parse(chunks[2]);
                }
                else if (line_header.equals("J00"))
                {
                    fortrasDocument.J00.Parse(chunks[2]);
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
                        consignmentAddressesMap.put(consignment_position, new HashMap<Integer, Integer>());
                        consignmentLinesMap.put(consignment_position, new HashMap<Integer, Integer>());
                    }
                    var consignment = fortrasDocument.CONSIGNMENTS.get(consignmentMap.get(consignment_position));
                    if (nodes[1].startsWith("ADDRESS_GROUP"))
                    {
                        int address_position = Integer.parseInt(Formatting.RegexFindFirst(nodes[1], "\\d+"));
                        if (!consignmentAddressesMap.get(consignment_position).containsKey(address_position))
                        {
                            consignment.ADDRESSES.add(new ADDRESS());
                            consignmentAddressesMap.get(consignment_position).put(address_position, consignment.ADDRESSES.size() - 1);
                        }
                        var address = consignment.ADDRESSES.get(consignmentAddressesMap.get(consignment_position).get(address_position));
                        if (nodes[2].equals("B00"))
                        {
                            address.B00.Parse(chunks[2]);
                        }
                        if (nodes[2].equals("B10"))
                        {
                            var b10 = new B10();
                            b10.Parse(chunks[2]);
                            address.B10.add(b10);
                        }
                    }
                    if (nodes[1].equals("C00"))
                    {
                        var c00 = new C00();
                        c00.Parse(chunks[2]);
                        consignment.C00.add(c00);
                    }
                    if (nodes[1].startsWith("CONSIGNMENT_LINE_GROUP"))
                    {
                        int consignment_line_position = Integer.parseInt(Formatting.RegexFindFirst(nodes[1], "\\d+"));
                        if (!consignmentLinesMap.get(consignment_position).containsKey(consignment_line_position))
                        {
                            consignment.CONSIGNMENT_LINES.add(new CONSIGNMENT_LINE());
                            consignmentLinesMap.get(consignment_position).put(consignment_line_position, consignment.CONSIGNMENT_LINES.size() - 1);
                        }
                        var consignment_line = consignment.CONSIGNMENT_LINES.get(consignmentLinesMap.get(consignment_position).get(consignment_line_position));
                        if (nodes[2].equals("D00"))
                        {
                            consignment_line.D00.Parse(chunks[2]);
                        }
                        if (nodes[2].equals("D10"))
                        {
                            var d10 = new D10();
                            d10.Parse(chunks[2]);
                            consignment_line.D10.add(d10);
                        }
                        if (nodes[2].startsWith("DANGEROUS_GOODS_GROUP"))
                        {
                            consignment_line.DANGEROUS_GOODS = new DANGEROUS_GOODS();
                            if (nodes[3].equals("E00"))
                            {
                                var e00 = new E00();
                                e00.Parse(chunks[2]);
                                consignment_line.DANGEROUS_GOODS.E00.add(e00);
                            }
                            if (nodes[2].equals("E10"))
                            {
                                var e10 = new E10();
                                e10.Parse(chunks[2]);
                                consignment_line.DANGEROUS_GOODS.E10.add(e10);
                            }
                        }
                        if (nodes[2].equals("F00"))
                        {
                            var f00 = new F00();
                            f00.Parse(chunks[2]);
                            consignment_line.F00.add(f00);
                        }
                    }
                    if (nodes[1].equals("G00"))
                    {
                        consignment.G00.Parse(chunks[2]);
                    }
                    if (nodes[1].startsWith("TEXTS_GROUP"))
                    {
                        if (consignment.TEXTS == null) { consignment.TEXTS = new TEXT(); }
                        if (nodes[2].equals("H00"))
                        {
                            var h00 = new H00();
                            h00.Parse(chunks[2]);
                            consignment.TEXTS.H00.add(h00);
                        }
                        if (nodes[2].equals("H10"))
                        {
                            var h10 = new H10();
                            h10.Parse(chunks[2]);
                            consignment.TEXTS.H10.add(h10);
                        }
                    }
                    if (nodes[1].equals("I00"))
                    {
                        var i00 = new I00();
                        i00.Parse(chunks[2]);
                        consignment.I00.add(i00);
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
