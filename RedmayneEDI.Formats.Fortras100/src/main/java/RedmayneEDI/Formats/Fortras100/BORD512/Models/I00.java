package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

/// <summary>
/// Invoicing-related information for the Consignment.
/// </summary>
public class I00
{
    private int max_length = 133;

    public String Sequential_Waybill_Item = null;
    public String Service_Type_1 = null;
    public String Tax_Code_1 = null;
    public String Amount_1 = null;
    public String Service_Type_2 = null;
    public String Tax_Code_2 = null;
    public String Amount_2 = null;
    public String Service_Type_3 = null;
    public String Tax_Code_3 = null;
    public String Amount_3 = null;
    public String Service_Type_4 = null;
    public String Tax_Code_4 = null;
    public String Amount_4 = null;
    public String Service_Type_5 = null;
    public String Tax_Code_5 = null;
    public String Amount_5 = null;
    public String Service_Type_6 = null;
    public String Tax_Code_6 = null;
    public String Amount_6 = null;
    public String Service_Type_7 = null;
    public String Tax_Code_7 = null;
    public String Amount_7 = null;
    public String Service_Type_8 = null;
    public String Tax_Code_8 = null;
    public String Amount_8 = null;
    public String Service_Type_9 = null;
    public String Tax_Code_9 = null;
    public String Amount_9 = null;
    public String Service_Type_10 = null;
    public String Tax_Code_10 = null;
    public String Amount_10 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("I00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("I00 length is invalid. Maximum length expected after I00 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Service_Type_1 = Formatting.SafeSubstring(line, 3, 3);
        Tax_Code_1 = Formatting.SafeSubstring(line, 6, 1);
        Amount_1 = Formatting.SafeSubstring(line, 7, 9);
        Service_Type_2 = Formatting.SafeSubstring(line, 16, 3);
        Tax_Code_2 = Formatting.SafeSubstring(line, 19, 1);
        Amount_2 = Formatting.SafeSubstring(line, 20, 9);
        Service_Type_3 = Formatting.SafeSubstring(line, 29, 3);
        Tax_Code_3 = Formatting.SafeSubstring(line, 32, 1);
        Amount_3 = Formatting.SafeSubstring(line, 33, 9);
        Service_Type_4 = Formatting.SafeSubstring(line, 40, 3);
        Tax_Code_4 = Formatting.SafeSubstring(line, 43, 1);
        Amount_4 = Formatting.SafeSubstring(line, 44, 9);
        Service_Type_5 = Formatting.SafeSubstring(line, 53, 3);
        Tax_Code_5 = Formatting.SafeSubstring(line, 56, 1);
        Amount_5 = Formatting.SafeSubstring(line, 57, 9);
        Service_Type_6 = Formatting.SafeSubstring(line, 66, 3);
        Tax_Code_6 = Formatting.SafeSubstring(line, 69, 1);
        Amount_6 = Formatting.SafeSubstring(line, 70, 9);
        Service_Type_7 = Formatting.SafeSubstring(line, 79, 3);
        Tax_Code_7 = Formatting.SafeSubstring(line, 82, 1);
        Amount_7 = Formatting.SafeSubstring(line, 83, 9);
        Service_Type_8 = Formatting.SafeSubstring(line, 90, 3);
        Tax_Code_8 = Formatting.SafeSubstring(line, 93, 1);
        Amount_8 = Formatting.SafeSubstring(line, 94, 9);
        Service_Type_9 = Formatting.SafeSubstring(line, 103, 3);
        Tax_Code_9 = Formatting.SafeSubstring(line, 106, 1);
        Amount_9 = Formatting.SafeSubstring(line, 107, 9);
        Service_Type_10 = Formatting.SafeSubstring(line, 116, 3);
        Tax_Code_10 = Formatting.SafeSubstring(line, 119, 1);
        Amount_10 = Formatting.SafeSubstring(line, 120, 9);
    }

    @Override
    public String toString()
    {
        var line = "I00" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
                Formatting.SafeTruncate(Service_Type_1, 3) +
                Formatting.SafeTruncate(Tax_Code_1, 1) +
                Formatting.SafeTruncate(Amount_1, 9) +
                Formatting.SafeTruncate(Service_Type_2, 3) +
                Formatting.SafeTruncate(Tax_Code_2, 1) +
                Formatting.SafeTruncate(Amount_2, 9) +
                Formatting.SafeTruncate(Service_Type_3, 3) +
                Formatting.SafeTruncate(Tax_Code_3, 1) +
                Formatting.SafeTruncate(Amount_3, 9) +
                Formatting.SafeTruncate(Service_Type_4, 3) +
                Formatting.SafeTruncate(Tax_Code_4, 1) +
                Formatting.SafeTruncate(Amount_4, 9) +
                Formatting.SafeTruncate(Service_Type_5, 3) +
                Formatting.SafeTruncate(Tax_Code_5, 1) +
                Formatting.SafeTruncate(Amount_5, 9) +
                Formatting.SafeTruncate(Service_Type_6, 3) +
                Formatting.SafeTruncate(Tax_Code_6, 1) +
                Formatting.SafeTruncate(Amount_6, 9) +
                Formatting.SafeTruncate(Service_Type_7, 3) +
                Formatting.SafeTruncate(Tax_Code_7, 1) +
                Formatting.SafeTruncate(Amount_7, 9) +
                Formatting.SafeTruncate(Service_Type_8, 3) +
                Formatting.SafeTruncate(Tax_Code_8, 1) +
                Formatting.SafeTruncate(Amount_8, 9) +
                Formatting.SafeTruncate(Service_Type_9, 3) +
                Formatting.SafeTruncate(Tax_Code_9, 1) +
                Formatting.SafeTruncate(Amount_9, 9) +
                Formatting.SafeTruncate(Service_Type_10, 3) +
                Formatting.SafeTruncate(Tax_Code_10, 1) +
                Formatting.SafeTruncate(Amount_10, 9) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
