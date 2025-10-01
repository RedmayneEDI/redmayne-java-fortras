package RedmayneEDI.Formats.Fortras100.STAT512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class Q10
{
    private int max_length = 276;

    public String Sender_Shipment_ID = null;
    public String Receiver_Shipment_ID = null;
    public String Pick_Up_Order_No = null;
    public String Status_Code = null;
    public String Event_Date = null;
    public String Event_Time = null;
    public String Shipment_No = null;
    public String Wait_Time = null;
    public String Acknowledging_Party = null;
    public String Additional_Text = null;
    public String Reference_No = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("Q10")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("Q10 length is invalid. Maximum length expected after Q10 code is " + max_length + " but processed " + line.trim().length()); }
        Sender_Shipment_ID = Formatting.SafeSubstring(line, 0, 35);
        Receiver_Shipment_ID = Formatting.SafeSubstring(line, 35, 35);
        Pick_Up_Order_No = Formatting.SafeSubstring(line, 70, 35);
        Status_Code = Formatting.SafeSubstring(line, 105, 3);
        Event_Date = Formatting.SafeSubstring(line, 108, 8);
        Event_Time = Formatting.SafeSubstring(line, 116, 4);
        Shipment_No = Formatting.SafeSubstring(line, 120, 35);
        Wait_Time = Formatting.SafeSubstring(line, 155, 4);
        Acknowledging_Party = Formatting.SafeSubstring(line, 159, 35);
        Additional_Text = Formatting.SafeSubstring(line, 194, 70);
        Reference_No = Formatting.SafeSubstring(line, 264, 12);
    }

    @Override
    public String toString()
    {
        var line = "Q10" + Formatting.SafeTruncate(Sender_Shipment_ID, 35) +
                Formatting.SafeTruncate(Receiver_Shipment_ID, 35) +
                Formatting.SafeTruncate(Pick_Up_Order_No, 35) +
                Formatting.SafeTruncate(Status_Code, 3) +
                Formatting.SafeTruncate(Event_Date, 8) +
                Formatting.SafeTruncate(Event_Time, 4) +
                Formatting.SafeTruncate(Shipment_No, 35) +
                Formatting.SafeTruncate(Wait_Time, 4) +
                Formatting.SafeTruncate(Acknowledging_Party, 35) +
                Formatting.SafeTruncate(Additional_Text, 70) +
                Formatting.SafeTruncate(Reference_No, 12) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
