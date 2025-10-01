package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class E10
{
    private int max_length = 371;

    public String Sequential_Waybill_Item = null;
    public String Consignment_Position = null;
    public String Qualifier_For_Text_Usage_1 = null;
    public String Any_Text_1 = null;
    public String Qualifier_For_Text_Usage_2 = null;
    public String Any_Text_2 = null;
    public String Qualifier_For_Text_Usage_3 = null;
    public String Any_Text_3 = null;
    public String Qualifier_For_Text_Usage_4 = null;
    public String Any_Text_4 = null;
    public String Qualifier_For_Text_Usage_5 = null;
    public String Any_Text_5 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("E10")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("E10 length is invalid. Maximum length expected after E10 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Consignment_Position = Formatting.SafeSubstring(line, 3, 3);
        Qualifier_For_Text_Usage_1 = Formatting.SafeSubstring(line, 6, 3);
        Any_Text_1 = Formatting.SafeSubstring(line, 9, 70);
        Qualifier_For_Text_Usage_2 = Formatting.SafeSubstring(line, 79, 3);
        Any_Text_2 = Formatting.SafeSubstring(line, 82, 70);
        Qualifier_For_Text_Usage_3 = Formatting.SafeSubstring(line, 152, 3);
        Any_Text_3 = Formatting.SafeSubstring(line, 155, 70);
        Qualifier_For_Text_Usage_4 = Formatting.SafeSubstring(line, 225, 3);
        Any_Text_4 = Formatting.SafeSubstring(line, 228, 70);
        Qualifier_For_Text_Usage_5 = Formatting.SafeSubstring(line, 298, 3);
        Any_Text_5 = Formatting.SafeSubstring(line, 301, 70);
    }

    @Override
    public String toString()
    {
        var line = "E10" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
                Formatting.SafeTruncate(Consignment_Position, 3, '0', true) +
                Formatting.SafeTruncate(Qualifier_For_Text_Usage_1, 3) +
                Formatting.SafeTruncate(Any_Text_1, 70) +
                Formatting.SafeTruncate(Qualifier_For_Text_Usage_2, 3) +
                Formatting.SafeTruncate(Any_Text_2, 70) +
                Formatting.SafeTruncate(Qualifier_For_Text_Usage_3, 3) +
                Formatting.SafeTruncate(Any_Text_3, 70) +
                Formatting.SafeTruncate(Qualifier_For_Text_Usage_4, 3) +
                Formatting.SafeTruncate(Any_Text_4, 70) +
                Formatting.SafeTruncate(Qualifier_For_Text_Usage_5, 3) +
                Formatting.SafeTruncate(Any_Text_5, 70) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
