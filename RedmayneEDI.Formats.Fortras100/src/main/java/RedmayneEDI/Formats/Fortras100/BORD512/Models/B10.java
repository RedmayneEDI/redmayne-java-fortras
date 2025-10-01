package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

/// <summary>
/// A model for storing contact information for a Consignment.
/// </summary>
public class B10
{
    private int max_length = 262;

    public String Sequential_Waybill_Item = null;
    public String Communication_Type_Qualifier = null;
    public String Content = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("B10")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("B10 length is invalid. Maximum length expected after B10 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Communication_Type_Qualifier = Formatting.SafeSubstring(line, 3, 3);
        Content = Formatting.SafeSubstring(line, 6, 256);
    }

    @Override
    public String toString()
    {
        var line = "B10" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
            Formatting.SafeTruncate(Communication_Type_Qualifier, 3) +
            Formatting.SafeTruncate(Content, 256) +
            Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
