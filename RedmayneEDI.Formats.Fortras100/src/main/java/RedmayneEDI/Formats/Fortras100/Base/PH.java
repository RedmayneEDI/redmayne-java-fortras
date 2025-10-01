package RedmayneEDI.Formats.Fortras100.Base;

import RedmayneEDI.Formats.Fortras100.Formatting;

/// <summary>
/// The Fortras format initial document header.
/// </summary>
public class PH
{
    public String Message_Type = null;
    public String HEADER = null;
    /// <summary>
    /// The Sending Party identifier. Usually pre-agreed with the partner.
    /// </summary>
    public String Sending_Party = null;
    /// <summary>
    /// The Receiving Party identifier. Usually pre-agreed with the partner.
    /// </summary>
    public String Receiving_Party = null;

    public PH() {

    }
    public PH(String message_type) {
        Message_Type = message_type;
    }

    public void Parse(String rawText)
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("@@PH")) { line = line.substring(4); }
        Message_Type = Formatting.SafeSubstring(line, 0, 8);
        HEADER = Formatting.SafeSubstring(line,8, 14);
        Sending_Party = Formatting.SafeSubstring(line,22, 8);
        Receiving_Party = Formatting.SafeSubstring(line,30, 8);
    }

    @Override
    public String toString()
    {
        return "@@PH" + Formatting.SafeTruncate(Message_Type, 8) +
                Formatting.SafeTruncate(HEADER, 14) +
                Formatting.SafeTruncate(Sending_Party, 8) +
                Formatting.SafeTruncate(Receiving_Party, 8) +
                Formatting.CRLF;
    }
}
