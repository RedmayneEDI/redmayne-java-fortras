package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class A10
{
    private int max_length = 350;

    public String Loading_Units_1 = null;
    public String Lead_Seal_1 = null;
    public String Lead_Seal_2 = null;
    public String Lead_Seal_3 = null;
    public String Lead_Seal_4 = null;
    public String Loading_Units_2 = null;
    public String Lead_Seal2_1 = null;
    public String Lead_Seal2_2 = null;
    public String Lead_Seal2_3 = null;
    public String Lead_Seal2_4 = null;

    public void Parse(String rawText) throws Exception
    {
        String line = rawText;
        if (line.toUpperCase().startsWith("A10")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("A10 length is invalid. Maximum length expected after A10 code is " + max_length + " but processed " + line.trim().length()); }
        Loading_Units_1 = Formatting.SafeSubstring(line, 0, 35);
        Lead_Seal_1 = Formatting.SafeSubstring(line, 35, 35);
        Lead_Seal_2 = Formatting.SafeSubstring(line, 70, 35);
        Lead_Seal_3 = Formatting.SafeSubstring(line, 105, 35);
        Lead_Seal_4 = Formatting.SafeSubstring(line, 140, 35);
        Loading_Units_2 = Formatting.SafeSubstring(line, 175, 35);
        Lead_Seal2_1 = Formatting.SafeSubstring(line, 210, 35);
        Lead_Seal2_2 = Formatting.SafeSubstring(line, 245, 35);
        Lead_Seal2_3 = Formatting.SafeSubstring(line, 280, 35);
        Lead_Seal2_4 = Formatting.SafeSubstring(line, 315, 35);
    }

    @Override
    public String toString()
    {
        var line = "A10" + Formatting.SafeTruncate(Loading_Units_1, 35) +
            Formatting.SafeTruncate(Lead_Seal_1, 35) +
            Formatting.SafeTruncate(Lead_Seal_2, 35) +
            Formatting.SafeTruncate(Lead_Seal_3, 35) +
            Formatting.SafeTruncate(Lead_Seal_4, 35) +
            Formatting.SafeTruncate(Loading_Units_2, 35) +
            Formatting.SafeTruncate(Lead_Seal2_1, 35) +
            Formatting.SafeTruncate(Lead_Seal2_2, 35) +
            Formatting.SafeTruncate(Lead_Seal2_3, 35) +
            Formatting.SafeTruncate(Lead_Seal2_4, 35) +
            Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
