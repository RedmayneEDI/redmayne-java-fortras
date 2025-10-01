package RedmayneEDI.Formats.Fortras100.ENTL512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class O20
{
    private int max_length = 181;

    public String Preliminary_Consignment_No_Receiving_Depot = null;
    public String Barcode_1 = null;
    public String Error_Message_Code_1 = null;
    public String Additional_Text_1 = null;
    public String Barcode_2 = null;
    public String Error_Message_Code_2 = null;
    public String Additional_Text_2 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("O20")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("O20 length is invalid. Maximum length expected after O20 code is " + max_length + " but processed " + line.trim().length()); }
        Preliminary_Consignment_No_Receiving_Depot = Formatting.SafeSubstring(line,0, 35);
        Barcode_1 = Formatting.SafeSubstring(line,35, 35);
        Error_Message_Code_1 = Formatting.SafeSubstring(line,70, 3);
        Additional_Text_1 = Formatting.SafeSubstring(line,73, 35);
        Barcode_2 = Formatting.SafeSubstring(line,108, 35);
        Error_Message_Code_2 = Formatting.SafeSubstring(line,111, 3);
        Additional_Text_2 = Formatting.SafeSubstring(line,114, 35);
    }

    public String toString()
    {
        var line = "O20" + Formatting.SafeTruncate(Preliminary_Consignment_No_Receiving_Depot, 35) +
                Formatting.SafeTruncate(Barcode_1, 35) +
                Formatting.SafeTruncate(Error_Message_Code_1, 3) +
                Formatting.SafeTruncate(Additional_Text_1, 35) +
                Formatting.SafeTruncate(Barcode_2, 35) +
                Formatting.SafeTruncate(Error_Message_Code_2, 3) +
                Formatting.SafeTruncate(Additional_Text_2, 35) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
