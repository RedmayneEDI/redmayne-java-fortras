package RedmayneEDI.Formats.Fortras100.Base;

import RedmayneEDI.Formats.Fortras100.Formatting;

/// <summary>
/// The Fortras message summary line that tails the body of all messages.
/// </summary>
public class Z00
{
    /// <summary>
    /// The total number of valid lines within the document, excluding @@ and Z lines.
    /// </summary>
    public String Total_Number_Of_Data_Records = null;
    /// <summary>
    /// The date the message was created on, in DDMMYYYY format.
    /// </summary>
    public String Date_Of_Creation_DDMMYYYY = null;
    /// <summary>
    /// The Time of the message creation, in HHMMSS format.
    /// </summary>
    public String Time_Of_Creation_HHMMSS = null;

    public void Parse(String rawText)
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("Z00")) { line = line.substring(3); }
        Total_Number_Of_Data_Records = Formatting.SafeSubstring(line,0, 6);
        Date_Of_Creation_DDMMYYYY = Formatting.SafeSubstring(line,6, 8);
        Time_Of_Creation_HHMMSS = Formatting.SafeSubstring(line,14, 6);
    }

    @Override
    public String toString()
    {
        if (Date_Of_Creation_DDMMYYYY == null || Date_Of_Creation_DDMMYYYY.equals("")) { Date_Of_Creation_DDMMYYYY = Formatting.GetCurrentDateTime("ddMMyyyy"); }
        if (Time_Of_Creation_HHMMSS == null || Time_Of_Creation_HHMMSS.equals("")) { Time_Of_Creation_HHMMSS = Formatting.GetCurrentDateTime("HHmmss"); }

        return "Z00" + Formatting.SafeTruncate(Total_Number_Of_Data_Records, 6, '0', true) +
            Formatting.SafeTruncate(Date_Of_Creation_DDMMYYYY, 8) +
            Formatting.SafeTruncate(Time_Of_Creation_HHMMSS, 6) +
            Formatting.CRLF;
    }
}
