package RedmayneEDI.Formats.Fortras100.Base;

import RedmayneEDI.Formats.Fortras100.Formatting;

/// <summary>
/// The standard Fortras end of message tag.
/// </summary>
public class PT
{
    public void Parse(String rawText)
    {
        String line = rawText;
        if (line.toUpperCase().startsWith("@@PT")) { line = Formatting.SafeSubstring(line,4); }
    }

    @Override
    public String toString()
    {
        return "@@PT" + Formatting.CRLF;
    }
}

