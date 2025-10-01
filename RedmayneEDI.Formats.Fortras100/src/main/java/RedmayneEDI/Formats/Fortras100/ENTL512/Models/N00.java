package RedmayneEDI.Formats.Fortras100.ENTL512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class N00
{
    private int max_length = 288;

    public String Waybill_Number_Sending_Depot = null;
    public String Sequential_Waybill_Item = null;
    public String Consignment_Number_Sending_Depot = null;
    public String Consignment_Number_Receiving_Depot = null;
    public String Status_Code_1 = null;
    public String Discrepancy_Number_1 = null;
    public String Packaging_Type_1 = null;
    public String Text_Notes_1 = null;
    public String Status_Code_2 = null;
    public String Discrepancy_Number_2 = null;
    public String Packaging_Type_2 = null;
    public String Text_Notes_2 = null;
    public String Status_Code_3 = null;
    public String Discrepancy_Number_3 = null;
    public String Packaging_Type_3 = null;
    public String Text_Notes_3 = null;
    public String Status_Code_4 = null;
    public String Discrepancy_Number_4 = null;
    public String Packaging_Type_4 = null;
    public String Text_Notes_4 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("N00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("N00 length is invalid. Maximum length expected after N00 code is " + max_length + " but processed " + line.trim().length()); }
        Waybill_Number_Sending_Depot = Formatting.SafeSubstring(line, 0, 35);
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 35, 3);
        Consignment_Number_Sending_Depot = Formatting.SafeSubstring(line, 38, 35);
        Consignment_Number_Receiving_Depot = Formatting.SafeSubstring(line, 73, 35);
        Status_Code_1 = Formatting.SafeSubstring(line, 108, 3);
        Discrepancy_Number_1 = Formatting.SafeSubstring(line, 111, 4);
        Packaging_Type_1 = Formatting.SafeSubstring(line, 115, 3);
        Text_Notes_1 = Formatting.SafeSubstring(line, 118, 35);
        Status_Code_2 = Formatting.SafeSubstring(line, 153, 3);
        Discrepancy_Number_2 = Formatting.SafeSubstring(line, 156, 4);
        Packaging_Type_2 = Formatting.SafeSubstring(line, 160, 3);
        Text_Notes_2 = Formatting.SafeSubstring(line, 163, 35);
        Status_Code_3 = Formatting.SafeSubstring(line, 198, 3);
        Discrepancy_Number_3 = Formatting.SafeSubstring(line, 201, 4);
        Packaging_Type_3 = Formatting.SafeSubstring(line, 205, 3);
        Text_Notes_3 = Formatting.SafeSubstring(line, 208, 35);
        Status_Code_4 = Formatting.SafeSubstring(line, 243, 3);
        Discrepancy_Number_4 = Formatting.SafeSubstring(line, 246, 4);
        Packaging_Type_4 = Formatting.SafeSubstring(line, 250, 3);
        Text_Notes_4 = Formatting.SafeSubstring(line, 253, 35);
    }

    public String toString()
    {
        var line = "N00" + Formatting.SafeTruncate(Waybill_Number_Sending_Depot, 35) +
            Formatting.SafeTruncate(Sequential_Waybill_Item, 3) +
            Formatting.SafeTruncate(Consignment_Number_Sending_Depot, 35) +
            Formatting.SafeTruncate(Consignment_Number_Receiving_Depot, 35) +
            Formatting.SafeTruncate(Status_Code_1, 3) +
            Formatting.SafeTruncate(Discrepancy_Number_1, 4) +
            Formatting.SafeTruncate(Packaging_Type_1, 3) +
            Formatting.SafeTruncate(Text_Notes_1, 35) +
            Formatting.SafeTruncate(Status_Code_2, 3) +
            Formatting.SafeTruncate(Discrepancy_Number_2, 4) +
            Formatting.SafeTruncate(Packaging_Type_2, 3) +
            Formatting.SafeTruncate(Text_Notes_2, 35) +
            Formatting.SafeTruncate(Status_Code_3, 3) +
            Formatting.SafeTruncate(Discrepancy_Number_3, 4) +
            Formatting.SafeTruncate(Packaging_Type_3, 3) +
            Formatting.SafeTruncate(Text_Notes_3, 35) +
            Formatting.SafeTruncate(Status_Code_4, 3) +
            Formatting.SafeTruncate(Discrepancy_Number_4, 4) +
            Formatting.SafeTruncate(Packaging_Type_4, 3) +
            Formatting.SafeTruncate(Text_Notes_4, 35) +
            Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
