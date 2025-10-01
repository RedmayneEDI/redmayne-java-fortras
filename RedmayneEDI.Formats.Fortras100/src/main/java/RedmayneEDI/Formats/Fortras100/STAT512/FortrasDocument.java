package RedmayneEDI.Formats.Fortras100.STAT512;

import RedmayneEDI.Formats.Fortras100.Base.*;
import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.STAT512.Models.*;
import RedmayneEDI.Formats.Fortras100.STAT512.SegmentGroups.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class FortrasDocument
{
    public PH PH = null;
    public Q00 Q00 = null;
    public ArrayList<CONSIGNMENT> CONSIGNMENTS = new ArrayList<CONSIGNMENT>();
    public Z00 Z00 = null;
    public PT PT = null;

    /// <summary>
    /// When enabled, the automatic setting of the SequentialWaybillItem field is ignored.
    /// </summary>
    public boolean SkipAutoSequentialWaybillItem = false;

    public FortrasDocument()
    {
        PH = new PH("STAT512");
        Q00 = new Q00();
        CONSIGNMENTS = new ArrayList<CONSIGNMENT>();
        Z00 = new Z00();
        PT = new PT();
    }

    @Override
    public String toString()
    {
        // Ensure there's a Carriage return defined
        if (Formatting.CRLF.isBlank()) { Formatting.CRLF = "\n"; }

        String output = Q00 +
                Formatting.RecordSet(CONSIGNMENTS, 999);

        var lineCount = output.split(Formatting.CRLF).length;
        Z00.Total_Number_Of_Data_Records = "" + lineCount;

        return PH + output + Z00 + PT;
    }

    public boolean ValidLineIdentifier(String line)
    {
        var isValid = false;
        var lineIdentifiers = new ArrayList<String>();
        lineIdentifiers.add("@@PH");
        lineIdentifiers.add("Q00");
        lineIdentifiers.add("Q10");
        lineIdentifiers.add("Q11");
        lineIdentifiers.add("Q20");
        lineIdentifiers.add("Q30");
        lineIdentifiers.add("Z00");
        lineIdentifiers.add("@@PT");
        for (var lineIdentifier : lineIdentifiers)
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
            case "Q00":
            case "Z00":
            case "@@PT":
                return identifier;
            case "Q10":
                return "CONSIGNMENT_GROUP/Q10";
            case "Q11":
                return "CONSIGNMENT_GROUP/Q11";
            case "Q20":
                return "CONSIGNMENT_GROUP/BARCODE_GROUP/Q20";
            case "Q30":
                return "CONSIGNMENT_GROUP/BARCODE_GROUP/Q30";
            default:
                return "";
        }

    }

    public int MaxOccurance(String identifier)
    {
        switch (identifier)
        {
            case "@@PH":
            case "Q00":
            case "Q10":
            case "Q20":
            case "Z00":
            case "@@PT":
                return 1;
            case "CONSIGNMENT_GROUP":
            case "Q11":
            case "BARCODE_GROUP":
            case "Q30":
                return 999;
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
