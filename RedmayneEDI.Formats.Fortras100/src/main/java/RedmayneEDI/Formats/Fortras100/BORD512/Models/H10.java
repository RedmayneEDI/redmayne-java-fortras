package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class H10
{
    private int max_length = 222;

    public String Sequential_Waybill_Item = null;
    public String Qualifier_for_Text_Usage_1 = null;
    public String Any_Text_1 = null;
    public String Qualifier_for_Text_Usage_2 = null;
    public String Any_Text_2 = null;
    public String Qualifier_for_Text_Usage_3 = null;
    public String Any_Text_3 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("H10")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("H10 length is invalid. Maximum length expected after H10 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Qualifier_for_Text_Usage_1 = Formatting.SafeSubstring(line, 3, 3);
        Any_Text_1 = Formatting.SafeSubstring(line, 6, 70);
        Qualifier_for_Text_Usage_2 = Formatting.SafeSubstring(line, 76, 3);
        Any_Text_2 = Formatting.SafeSubstring(line, 79, 70);
        Qualifier_for_Text_Usage_3 = Formatting.SafeSubstring(line, 149, 3);
        Any_Text_3 = Formatting.SafeSubstring(line, 152, 70);
    }

    @Override
    public String toString()
    {
        var line = "H10" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
            Formatting.SafeTruncate(Qualifier_for_Text_Usage_1, 3) +
            Formatting.SafeTruncate(Any_Text_1, 70) +
            Formatting.SafeTruncate(Qualifier_for_Text_Usage_2, 3) +
            Formatting.SafeTruncate(Any_Text_2, 70) +
            Formatting.SafeTruncate(Qualifier_for_Text_Usage_3, 3) +
            Formatting.SafeTruncate(Any_Text_3, 70) +
            Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
