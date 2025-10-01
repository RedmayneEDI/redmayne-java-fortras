package RedmayneEDI.Formats.Fortras100.STAT512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class Q11
{
    private int max_length = 210;

    public String Additional_Text2 = null;
    public String Additional_Text3 = null;
    public String Additional_Text4 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("Q11")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("Q11 length is invalid. Maximum length expected after Q11 code is " + max_length + " but processed " + line.trim().length()); }
        Additional_Text2 = Formatting.SafeSubstring(line, 0, 70);
        Additional_Text3 = Formatting.SafeSubstring(line, 70, 70);
        Additional_Text4 = Formatting.SafeSubstring(line, 140, 70);
    }

    @Override
    public String toString()
    {
        var line = "Q11" + Formatting.SafeTruncate(Additional_Text2, 70) +
                Formatting.SafeTruncate(Additional_Text3, 70) +
                Formatting.SafeTruncate(Additional_Text4, 70) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
