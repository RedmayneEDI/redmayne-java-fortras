package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class F00
{
    private int max_length = 231;

    public String Sequential_Waybill_Item = null;
    public String Consignment_Position = null;
    public String Barcode = null;
    public String Reference_Qualifier_1 = null;
    public String Reference_Number_1 = null;
    public String Reference_Qualifier_2 = null;
    public String Reference_Number_2 = null;
    public String Reference_Qualifier_3 = null;
    public String Reference_Number_3 = null;
    public String Reference_Qualifier_4 = null;
    public String Reference_Number_4 = null;
    public String Reference_Qualifier_5 = null;
    public String Reference_Number_5 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("F00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("F00 length is invalid. Maximum length expected after F00 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Consignment_Position = Formatting.SafeSubstring(line, 3, 3);
        Barcode = Formatting.SafeSubstring(line, 6, 35);
        Reference_Qualifier_1 = Formatting.SafeSubstring(line, 41, 3);
        Reference_Number_1 = Formatting.SafeSubstring(line, 44, 35);
        Reference_Qualifier_2 = Formatting.SafeSubstring(line, 79, 3);
        Reference_Number_2 = Formatting.SafeSubstring(line, 82, 35);
        Reference_Qualifier_3 = Formatting.SafeSubstring(line, 117, 3);
        Reference_Number_3 = Formatting.SafeSubstring(line, 120, 35);
        Reference_Qualifier_4 = Formatting.SafeSubstring(line, 155, 3);
        Reference_Number_4 = Formatting.SafeSubstring(line, 158, 35);
        Reference_Qualifier_5 = Formatting.SafeSubstring(line, 193, 3);
        Reference_Number_5 = Formatting.SafeSubstring(line, 196, 35);
    }

    @Override
    public String toString()
    {
        var line = "F00" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
                Formatting.SafeTruncate(Consignment_Position, 3, '0', true) +
                Formatting.SafeTruncate(Barcode, 35) +
                Formatting.SafeTruncate(Reference_Qualifier_1, 3) +
                Formatting.SafeTruncate(Reference_Number_1, 35) +
                Formatting.SafeTruncate(Reference_Qualifier_2, 3) +
                Formatting.SafeTruncate(Reference_Number_2, 35) +
                Formatting.SafeTruncate(Reference_Qualifier_3, 3) +
                Formatting.SafeTruncate(Reference_Number_3, 35) +
                Formatting.SafeTruncate(Reference_Qualifier_4, 3) +
                Formatting.SafeTruncate(Reference_Number_4, 35) +
                Formatting.SafeTruncate(Reference_Qualifier_5, 3) +
                Formatting.SafeTruncate(Reference_Number_5, 35) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
