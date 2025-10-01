package RedmayneEDI.Formats.Fortras100.ENTL512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class O10
{
    private int max_length = 195;

    public String Preliminary_Consignment_No_Receiving_Depot = null;
    public String Code_and_Number_1 = null;
    public String Number_1 = null;
    public String Packaging_Type_1 = null;
    public String Error_Message_Code_1 = null;
    public String Additional_Text_1 = null;
    public String Code_and_Number_2 = null;
    public String Number_2 = null;
    public String Packaging_Type_2 = null;
    public String Error_Message_Code_2 = null;
    public String Additional_Text_2 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("O10")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("O10 length is invalid. Maximum length expected after O10 code is " + max_length + " but processed " + line.trim().length()); }
        Preliminary_Consignment_No_Receiving_Depot = Formatting.SafeSubstring(line,0, 35);
        Code_and_Number_1 = Formatting.SafeSubstring(line,35, 35);
        Number_1 = Formatting.SafeSubstring(line,70, 4);
        Packaging_Type_1 = Formatting.SafeSubstring(line,74, 3);
        Error_Message_Code_1 = Formatting.SafeSubstring(line,77, 3);
        Additional_Text_1 = Formatting.SafeSubstring(line,80, 35);
        Code_and_Number_2 = Formatting.SafeSubstring(line,125, 35);
        Number_2 = Formatting.SafeSubstring(line,160, 4);
        Packaging_Type_2 = Formatting.SafeSubstring(line,164, 3);
        Error_Message_Code_2 = Formatting.SafeSubstring(line,167, 3);
        Additional_Text_2 = Formatting.SafeSubstring(line,170, 35);
    }

    public String toString()
    {
        var line = "O10" + Formatting.SafeTruncate(Preliminary_Consignment_No_Receiving_Depot, 35) +
                Formatting.SafeTruncate(Code_and_Number_1, 35) +
                Formatting.SafeTruncate(Number_1, 4) +
                Formatting.SafeTruncate(Packaging_Type_1, 3) +
                Formatting.SafeTruncate(Error_Message_Code_1, 3) +
                Formatting.SafeTruncate(Additional_Text_1, 35) +
                Formatting.SafeTruncate(Code_and_Number_2, 35) +
                Formatting.SafeTruncate(Number_2, 4) +
                Formatting.SafeTruncate(Packaging_Type_2, 3) +
                Formatting.SafeTruncate(Error_Message_Code_2, 3) +
                Formatting.SafeTruncate(Additional_Text_2, 35) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
