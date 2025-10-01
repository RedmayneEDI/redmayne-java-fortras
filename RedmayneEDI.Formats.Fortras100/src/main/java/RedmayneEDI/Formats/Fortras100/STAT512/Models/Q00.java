package RedmayneEDI.Formats.Fortras100.STAT512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class Q00
{
    private int max_length = 181;

    public String Release_Version = null;
    public String Code_List = null;
    public String Sender_ID = null;
    public String Receiver_ID = null;
    public String Causing_Party_ID = null;
    public String Routing_ID_1 = null;
    public String Routing_ID_2 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("Q00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("Q00 length is invalid. Maximum length expected after Q00 code is " + max_length + " but processed " + line.trim().length()); }
        Release_Version = Formatting.SafeSubstring(line, 0, 3);
        Code_List = Formatting.SafeSubstring(line, 3, 3);
        Sender_ID = Formatting.SafeSubstring(line, 6, 35);
        Receiver_ID = Formatting.SafeSubstring(line, 41, 35);
        Causing_Party_ID = Formatting.SafeSubstring(line, 76, 35);
        Routing_ID_1 = Formatting.SafeSubstring(line, 111, 35);
        Routing_ID_2 = Formatting.SafeSubstring(line, 146, 35);
    }

    @Override
    public String toString()
    {
        var line = "Q00" + Formatting.SafeTruncate(Release_Version, 3) +
                Formatting.SafeTruncate(Code_List, 3) +
                Formatting.SafeTruncate(Sender_ID, 35) +
                Formatting.SafeTruncate(Receiver_ID, 35) +
                Formatting.SafeTruncate(Causing_Party_ID, 35) +
                Formatting.SafeTruncate(Routing_ID_1, 35) +
                Formatting.SafeTruncate(Routing_ID_2, 35) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
