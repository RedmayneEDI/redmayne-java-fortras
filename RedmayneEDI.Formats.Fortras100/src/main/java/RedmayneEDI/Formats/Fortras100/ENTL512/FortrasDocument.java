package RedmayneEDI.Formats.Fortras100.ENTL512;

import RedmayneEDI.Formats.Fortras100.Base.*;
import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.ENTL512.Models.*;
import RedmayneEDI.Formats.Fortras100.ENTL512.SegmentGroups.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class FortrasDocument
{
    public PH PH = null;
    public M00 M00 = null;
    public ArrayList<LOADING_UNIT> LOADING_UNITS = new ArrayList<LOADING_UNIT>();
    public ArrayList<N00> N00 = new ArrayList<N00>();
    public ArrayList<UEZ> UEZS = new ArrayList<UEZ>();
    public Z00 Z00 = null;
    public PT PT = null;

    /// <summary>
    /// When enabled, the automatic setting of the SequentialWaybillItem field is ignored.
    /// </summary>
    public boolean SkipAutoSequentialWaybillItem = false;

    public FortrasDocument()
    {
        PH = new PH("ENTL512");
        M00 = new M00();
        LOADING_UNITS = new ArrayList<LOADING_UNIT>();
        N00 = new ArrayList<N00>();
        UEZS = new ArrayList<UEZ>();
        Z00 = new Z00();
        PT = new PT();
    }

    @Override
    public String toString()
    {
        // Ensure there's a Carriage return defined
        if (Formatting.CRLF.isBlank()) { Formatting.CRLF = "\n"; }

        String output = M00 +
            Formatting.RecordSet(LOADING_UNITS, 999) +
            Formatting.RecordSet(N00, 999) +
            Formatting.RecordSet(UEZS, 999);

        var lineCount = output.split(Formatting.CRLF).length;
        Z00.Total_Number_Of_Data_Records = "" + lineCount;

        return PH + output + Z00 + PT;
    }

    /// <summary>
    /// Identifies if the given line is valid for the message type.
    /// </summary>
    /// <param name="line"></param>
    /// <returns></returns>
    public boolean ValidLineIdentifier(String line)
    {
        var isValid = false;
        var lineIdentifiers = new ArrayList<String>();
        lineIdentifiers.add("@@PH");
        lineIdentifiers.add("M00");
        lineIdentifiers.add("M10");
        lineIdentifiers.add("M20");
        lineIdentifiers.add("N00");
        lineIdentifiers.add("O00");
        lineIdentifiers.add("O10");
        lineIdentifiers.add("O20");
        lineIdentifiers.add("Z00");
        lineIdentifiers.add("@@PT");

        for (String lineIdentifier : lineIdentifiers)
        {
            if (line.toUpperCase().startsWith(lineIdentifier.toUpperCase())) { isValid = true; break; }
        }

        return isValid;
    }

    /// <summary>
    /// Returns the given identifier's document path. Nesting is indicated with a forward slash.
    /// </summary>
    /// <param name="identifier"></param>
    /// <returns></returns>
    public String GetPathToLine(String identifier)
    {
        switch (identifier)
        {
            case "@@PH":
            case "M00":
            case "N00":
            case "Z00":
            case "@@PT":
                return identifier;
            case "M10":
                return "LOADING_UNITS_GROUP/M10";
            case "M20":
                return "LOADING_UNITS_GROUP/M20";
            case "O00":
                return "UEZ_GROUP/O00";
            case "O10":
                return "UEZ_GROUP/O10";
            case "O20":
                return "UEZ_GROUP/O20";
            default:
                return "";
        }

    }

    /// <summary>
    /// Returns the maximum number of repetitions allowed for the given line identifier within the document.
    /// </summary>
    /// <param name="identifier"></param>
    /// <returns></returns>
    public int MaxOccurance(String identifier)
    {
        switch (identifier)
        {
            case "@@PH":
            case "M00":
            case "M10":
            case "M20":
            case "Z00":
            case "@@PT":
                return 1;
            case "O00":
                return 2;
            case "LOADING_UNITS_GROUP":
            case "N00":
            case "UEZ_GROUP":
            case "O10":
            case "O20":
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
