package RedmayneEDI.Formats.Fortras100.BORD512;

import RedmayneEDI.Formats.Fortras100.Base.*;
import RedmayneEDI.Formats.Fortras100.BORD512.Models.*;
import RedmayneEDI.Formats.Fortras100.BORD512.SegmentGroups.*;
import RedmayneEDI.Formats.Fortras100.Formatting;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class FortrasDocument
{
    public PH PH = null;
    public A00 A00 = null;
    public A10 A10 = null;
    public ArrayList<CONSIGNMENT> CONSIGNMENTS = new ArrayList<CONSIGNMENT>();
    public J00 J00 = null;
    public Z00 Z00 = null;
    public PT PT = null;

    /// <summary>
    /// When enabled, the automatic setting of the SequentialWaybillItem field is ignored.
    /// </summary>
    public boolean SkipAutoSequentialWaybillItem = false;

    public FortrasDocument()
    {
        PH = new PH("BORD512");
        A00 = new A00();
        CONSIGNMENTS = new ArrayList<CONSIGNMENT>();
        J00 = new J00();
        Z00 = new Z00();
        PT = new PT();
    }

    @Override
    public String toString()
    {
        // Ensure there's a Carriage return defined
        if (Formatting.CRLF == null || Formatting.CRLF.equals("")) { Formatting.CRLF = "\n"; }

        // Apply Sequential Waybill Item Numbers
        if (!SkipAutoSequentialWaybillItem)
        {
            int i = 1;
            for(CONSIGNMENT consignment : CONSIGNMENTS)
            {
                consignment.SetSequentialWaybillItem(i++);
            }
        }

        String output = "";
        if (A00 != null) { output += A00; }
        if (A10 != null) { output += A10; }
        if (CONSIGNMENTS != null) { output += Formatting.RecordSet(CONSIGNMENTS, 999); }
        if (J00 != null) { output += J00; }

        var lineCount = output.split(Formatting.CRLF).length;
        Z00.Total_Number_Of_Data_Records = "" + lineCount;

        return PH + output + Z00 + PT;
    }

    public boolean ValidLineIdentifier(String line)
    {
        var isValid = false;
        var lineIdentifiers = new ArrayList<String>();
        lineIdentifiers.add("@@PH");
        lineIdentifiers.add("A00");
        lineIdentifiers.add("A10");
        lineIdentifiers.add("B00");
        lineIdentifiers.add("B10");
        lineIdentifiers.add("C00");
        lineIdentifiers.add("D00");
        lineIdentifiers.add("D10");
        lineIdentifiers.add("E00");
        lineIdentifiers.add("E10");
        lineIdentifiers.add("F00");
        lineIdentifiers.add("G00");
        lineIdentifiers.add("H00");
        lineIdentifiers.add("H10");
        lineIdentifiers.add("I00");
        lineIdentifiers.add("J00");
        lineIdentifiers.add("Z00");
        lineIdentifiers.add("@@PT");

        for (String lineIdentifier : lineIdentifiers)
        {
            if (line.toUpperCase().startsWith(lineIdentifier.toUpperCase())) { isValid = true; break; }
        }
        return isValid;
    }

    public String GetPathToLine(String identifier)
    {
        switch (identifier)
        {
            case "@@PH":
            case "A00":
            case "A10":
            case "J00":
            case "Z00":
            case "@@PT":
                return identifier;
            case "B00":
                return "CONSIGNMENT_GROUP/ADDRESS_GROUP/B00";
            case "B10":
                return "CONSIGNMENT_GROUP/ADDRESS_GROUP/B10";
            case "C00":
                return "CONSIGNMENT_GROUP/C00";
            case "D00":
                return "CONSIGNMENT_GROUP/CONSIGNMENT_LINE_GROUP/D00";
            case "D10":
                return "CONSIGNMENT_GROUP/CONSIGNMENT_LINE_GROUP/D10";
            case "E00":
                return "CONSIGNMENT_GROUP/CONSIGNMENT_LINE_GROUP/DANGEROUS_GOODS_GROUP/E00";
            case "E10":
                return "CONSIGNMENT_GROUP/CONSIGNMENT_LINE_GROUP/DANGEROUS_GOODS_GROUP/E10";
            case "F00":
                return "CONSIGNMENT_GROUP/CONSIGNMENT_LINE_GROUP/F00";
            case "G00":
                return "CONSIGNMENT_GROUP/G00";
            case "H00":
                return "CONSIGNMENT_GROUP/TEXTS_GROUP/H00";
            case "H10":
                return "CONSIGNMENT_GROUP/TEXTS_GROUP/H10";
            case "I00":
                return "CONSIGNMENT_GROUP/I00";
            default:
                return "";
        }

    }

    public int MaxOccurance(String identifier)
    {
        switch (identifier)
        {
            case "@@PH":
            case "A00":
            case "A10":
            case "J00":
            case "Z00":
            case "B00":
            case "D00":
            case "DANGEROUS_GOODS_GROUP":
            case "G00":
            case "TEXTS_GROUP":
                return 1;
            case "CONSIGNMENT_GROUP":
            case "ADDRESS_GROUP":
            case "C00":
            case "CONSIGNMENT_LINE_GROUP":
            case "F00":
                return 999;
            case "B10":
            case "E00":
            case "H10":
            case "I00":
                return 9;
            case "D10":
                return 99;
            case "E10":
            case "H00":
                return 4;
            default:
                return 0;
        }
    }

    /// <summary>
    /// Tries to locate the common parent between the given last path and the current node path.
    /// </summary>
    /// <param name="lastPath"></param>
    /// <param name="nodes"></param>
    /// <returns></returns>
    public String FindCommonParent(String lastPath, String[] nodes)
    {
        String[] lastNodes = lastPath.split("/");
        String current = "";
        for (int i = 0; i < nodes.length; i++)
        {
            if (lastNodes.length > i && lastNodes[i] != null)
            {
                if (lastNodes[i].equals(nodes[i])) { current = nodes[i]; }
            }
        }
        return current;
    }
}
