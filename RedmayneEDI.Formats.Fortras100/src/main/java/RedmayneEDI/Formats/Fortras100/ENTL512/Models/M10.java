package RedmayneEDI.Formats.Fortras100.ENTL512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class M10
{
    private int max_length = 233;

    public String Loading_Unit_Number = null;
    public String Condition_Of_Loading_Unit_1 = null;
    public String Additional_Text_1 = null;
    public String Condition_Of_Loading_Unit_2 = null;
    public String Additional_Text_2 = null;
    public String Condition_Of_Loading_Unit_3 = null;
    public String Additional_Text_3 = null;
    public String Condition_Of_Loading_Unit_4 = null;
    public String Additional_Text_4 = null;
    public String Condition_Of_Loading_Unit_5 = null;
    public String Additional_Text_5 = null;
    public String Condition_Of_Loading_Unit_6 = null;
    public String Additional_Text_6 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("M10")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("M10 length is invalid. Maximum length expected after M10 code is " + max_length + " but processed " + line.trim().length()); }
        Loading_Unit_Number = Formatting.SafeSubstring(line, 0, 35);
        Condition_Of_Loading_Unit_1 = Formatting.SafeSubstring(line, 35, 3);
        Additional_Text_1 = Formatting.SafeSubstring(line, 38, 30);
        Condition_Of_Loading_Unit_2 = Formatting.SafeSubstring(line, 68, 3);
        Additional_Text_2 = Formatting.SafeSubstring(line, 71, 30);
        Condition_Of_Loading_Unit_3 = Formatting.SafeSubstring(line, 101, 3);
        Additional_Text_3 = Formatting.SafeSubstring(line, 104, 30);
        Condition_Of_Loading_Unit_4 = Formatting.SafeSubstring(line, 134, 3);
        Additional_Text_4 = Formatting.SafeSubstring(line, 137, 30);
        Condition_Of_Loading_Unit_5 = Formatting.SafeSubstring(line, 167, 3);
        Additional_Text_5 = Formatting.SafeSubstring(line, 170, 30);
        Condition_Of_Loading_Unit_6 = Formatting.SafeSubstring(line, 200, 3);
        Additional_Text_6 = Formatting.SafeSubstring(line, 203, 30);
    }

    @Override
    public String toString()
    {
        var line = "M10" + Formatting.SafeTruncate(Loading_Unit_Number, 35) +
                Formatting.SafeTruncate(Condition_Of_Loading_Unit_1, 3) +
                Formatting.SafeTruncate(Additional_Text_1, 30) +
                Formatting.SafeTruncate(Condition_Of_Loading_Unit_2, 3) +
                Formatting.SafeTruncate(Additional_Text_2, 30) +
                Formatting.SafeTruncate(Condition_Of_Loading_Unit_3, 3) +
                Formatting.SafeTruncate(Additional_Text_3, 30) +
                Formatting.SafeTruncate(Condition_Of_Loading_Unit_4, 3) +
                Formatting.SafeTruncate(Additional_Text_4, 30) +
                Formatting.SafeTruncate(Condition_Of_Loading_Unit_5, 3) +
                Formatting.SafeTruncate(Additional_Text_5, 30) +
                Formatting.SafeTruncate(Condition_Of_Loading_Unit_6, 3) +
                Formatting.SafeTruncate(Additional_Text_6, 30) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
