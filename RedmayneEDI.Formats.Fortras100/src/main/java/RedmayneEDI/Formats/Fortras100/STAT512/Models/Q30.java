package RedmayneEDI.Formats.Fortras100.STAT512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class Q30
{
    private int max_length = 190;

    public String Qualifier_Reference_1 = null;
    public String Reference_Date_1 = null;
    public String Qualifier_Reference_2 = null;
    public String Reference_Date_2 = null;
    public String Qualifier_Reference_3 = null;
    public String Reference_Date_3 = null;
    public String Qualifier_Reference_4 = null;
    public String Reference_Date_4 = null;
    public String Qualifier_Reference_5 = null;
    public String Reference_Date_5 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("Q30")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("Q30 length is invalid. Maximum length expected after Q30 code is " + max_length + " but processed " + line.trim().length()); }
        Qualifier_Reference_1 = Formatting.SafeSubstring(line, 0, 3);
        Reference_Date_1 = Formatting.SafeSubstring(line, 3, 35);
        Qualifier_Reference_2 = Formatting.SafeSubstring(line, 38, 3);
        Reference_Date_2 = Formatting.SafeSubstring(line, 41, 35);
        Qualifier_Reference_3 = Formatting.SafeSubstring(line, 76, 3);
        Reference_Date_3 = Formatting.SafeSubstring(line, 79, 35);
        Qualifier_Reference_4 = Formatting.SafeSubstring(line, 114, 3);
        Reference_Date_4 = Formatting.SafeSubstring(line, 117, 35);
        Qualifier_Reference_5 = Formatting.SafeSubstring(line, 152, 3);
        Reference_Date_5 = Formatting.SafeSubstring(line, 155, 35);
    }

    @Override
    public String toString()
    {
        var line = "Q30" + Formatting.SafeTruncate(Qualifier_Reference_1, 3) +
                Formatting.SafeTruncate(Reference_Date_1, 35) +
                Formatting.SafeTruncate(Qualifier_Reference_2, 3) +
                Formatting.SafeTruncate(Reference_Date_2, 35) +
                Formatting.SafeTruncate(Qualifier_Reference_3, 3) +
                Formatting.SafeTruncate(Reference_Date_3, 35) +
                Formatting.SafeTruncate(Qualifier_Reference_4, 3) +
                Formatting.SafeTruncate(Reference_Date_4, 35) +
                Formatting.SafeTruncate(Qualifier_Reference_5, 3) +
                Formatting.SafeTruncate(Reference_Date_5, 35) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
