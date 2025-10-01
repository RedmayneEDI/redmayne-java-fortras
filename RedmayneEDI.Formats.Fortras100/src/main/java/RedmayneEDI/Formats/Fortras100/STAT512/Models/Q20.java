package RedmayneEDI.Formats.Fortras100.STAT512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class Q20
{
    private int max_length = 125;

    public String Shipment_No = null;
    public String Measuring_Point_Qualifier = null;
    public String Barcode = null;
    public String Scan_Date = null;
    public String Scan_Time = null;
    public String Scan_Code = null;
    public String Additional_Text = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("Q20")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("Q20 length is invalid. Maximum length expected after Q20 code is " + max_length + " but processed " + line.trim().length()); }
        Shipment_No = Formatting.SafeSubstring(line, 0, 35);
        Measuring_Point_Qualifier = Formatting.SafeSubstring(line, 35, 3);
        Barcode = Formatting.SafeSubstring(line, 38, 35);
        Scan_Date = Formatting.SafeSubstring(line, 73, 8);
        Scan_Time = Formatting.SafeSubstring(line, 81, 6);
        Scan_Code = Formatting.SafeSubstring(line, 87, 3);
        Additional_Text = Formatting.SafeSubstring(line, 90, 35);
    }

    @Override
    public String toString()
    {
        var line = "Q20" + Formatting.SafeTruncate(Shipment_No, 35) +
                Formatting.SafeTruncate(Measuring_Point_Qualifier, 3) +
                Formatting.SafeTruncate(Barcode, 35) +
                Formatting.SafeTruncate(Scan_Date, 8) +
                Formatting.SafeTruncate(Scan_Time, 6) +
                Formatting.SafeTruncate(Scan_Code, 3) +
                Formatting.SafeTruncate(Additional_Text, 35) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
