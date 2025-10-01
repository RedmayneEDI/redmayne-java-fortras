package RedmayneEDI.Formats.Fortras100.ENTL512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class M20
{
    private int max_length = 152;

    public String Lead_Seal_Number_1 = null;
    public String Condition_Lead_Seal_Number_1 = null;
    public String Lead_Seal_Number_2 = null;
    public String Condition_Lead_Seal_Number_2 = null;
    public String Lead_Seal_Number_3 = null;
    public String Condition_Lead_Seal_Number_3 = null;
    public String Lead_Seal_Number_4 = null;
    public String Condition_Lead_Seal_Number_4 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("M20")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("M20 length is invalid. Maximum length expected after M20 code is " + max_length + " but processed " + line.trim().length()); }
        Lead_Seal_Number_1 = Formatting.SafeSubstring(line, 0, 35);
        Condition_Lead_Seal_Number_1 = Formatting.SafeSubstring(line, 35, 3);
        Lead_Seal_Number_2 = Formatting.SafeSubstring(line, 38, 35);
        Condition_Lead_Seal_Number_2 = Formatting.SafeSubstring(line, 73, 3);
        Lead_Seal_Number_3 = Formatting.SafeSubstring(line, 76, 35);
        Condition_Lead_Seal_Number_3 = Formatting.SafeSubstring(line, 111, 3);
        Lead_Seal_Number_4 = Formatting.SafeSubstring(line, 114, 35);
        Condition_Lead_Seal_Number_4 = Formatting.SafeSubstring(line, 149, 3);
    }

    @Override
    public String toString()
    {
        var line = "M20" + Formatting.SafeTruncate(Lead_Seal_Number_1, 35) +
                Formatting.SafeTruncate(Condition_Lead_Seal_Number_1, 3) +
                Formatting.SafeTruncate(Lead_Seal_Number_2, 35) +
                Formatting.SafeTruncate(Condition_Lead_Seal_Number_2, 3) +
                Formatting.SafeTruncate(Lead_Seal_Number_3, 35) +
                Formatting.SafeTruncate(Condition_Lead_Seal_Number_3, 3) +
                Formatting.SafeTruncate(Lead_Seal_Number_4, 35) +
                Formatting.SafeTruncate(Condition_Lead_Seal_Number_4, 3) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
}
}
