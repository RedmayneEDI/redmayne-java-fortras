package RedmayneEDI.Formats.Fortras100.ENTL512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class M00
{
    private int max_length = 318;

    public String Data_Type_Qualifier = null;
    public String Release_Version = null;
    public String Waybill_Consignor = null;
    public String Waybill_Consignee = null;
    public String Code_List = null;
    public String Waybill_Number = null;
    public String Waybill_Date = null;
    public String Arrival_Date = null;
    public String Arrival_Time = null;
    public String Time_Limit_Status = null;
    public String Unloading_Start_Date = null;
    public String Unloading_Start_Time = null;
    public String Unloading_End_Date = null;
    public String Unloading_End_Time = null;
    public String Euro_Pallets_Sending_Depot = null;
    public String Box_Pallets_Sending_Depot = null;
    public String Euro_Pallets_Receiving_Depot = null;
    public String Box_Pallets_Receiving_Depot = null;
    public String Unloading_Notes = null;
    public String Routing_ID_1 = null;
    public String Routing_ID_2 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("M00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("M00 length is invalid. Maximum length expected after M00 code is {max_length} but processed " + line.trim().length()); }
        Data_Type_Qualifier = Formatting.SafeSubstring(line,0, 3);
        Release_Version = Formatting.SafeSubstring(line,3, 3);
        Waybill_Consignor = Formatting.SafeSubstring(line,6, 35);
        Waybill_Consignee = Formatting.SafeSubstring(line,41, 35);
        Code_List = Formatting.SafeSubstring(line,76, 3);
        Waybill_Number = Formatting.SafeSubstring(line,79, 35);
        Waybill_Date = Formatting.SafeSubstring(line,114, 8);
        Arrival_Date = Formatting.SafeSubstring(line,122, 8);
        Arrival_Time = Formatting.SafeSubstring(line,130, 4);
        Time_Limit_Status = Formatting.SafeSubstring(line,134, 4);
        Unloading_Start_Date = Formatting.SafeSubstring(line,138, 8);
        Unloading_Start_Time = Formatting.SafeSubstring(line,146, 4);
        Unloading_End_Date = Formatting.SafeSubstring(line,150, 8);
        Unloading_End_Time = Formatting.SafeSubstring(line,158, 4);
        Euro_Pallets_Sending_Depot = Formatting.SafeSubstring(line,162, 4);
        Box_Pallets_Sending_Depot = Formatting.SafeSubstring(line,166, 4);
        Euro_Pallets_Receiving_Depot = Formatting.SafeSubstring(line,170, 4);
        Box_Pallets_Receiving_Depot = Formatting.SafeSubstring(line,174, 4);
        Unloading_Notes = Formatting.SafeSubstring(line,178, 70);
        Routing_ID_1 = Formatting.SafeSubstring(line,248, 35);
        Routing_ID_2 = Formatting.SafeSubstring(line,283, 35);
    }

    @Override
    public String toString()
    {
        var line = "M00" + Formatting.SafeTruncate(Data_Type_Qualifier, 3) +
                Formatting.SafeTruncate(Release_Version, 3, '0', true) +
                Formatting.SafeTruncate(Waybill_Consignor, 35) +
                Formatting.SafeTruncate(Waybill_Consignee, 35) +
                Formatting.SafeTruncate(Code_List, 3) +
                Formatting.SafeTruncate(Waybill_Number, 35) +
                Formatting.SafeTruncate(Waybill_Date, 8) +
                Formatting.SafeTruncate(Arrival_Date, 8) +
                Formatting.SafeTruncate(Arrival_Time, 4) +
                Formatting.SafeTruncate(Time_Limit_Status, 4) +
                Formatting.SafeTruncate(Unloading_Start_Date, 8) +
                Formatting.SafeTruncate(Unloading_Start_Time, 4) +
                Formatting.SafeTruncate(Unloading_End_Date, 8) +
                Formatting.SafeTruncate(Unloading_End_Time, 4) +
                Formatting.SafeTruncate(Euro_Pallets_Sending_Depot, 4) +
                Formatting.SafeTruncate(Box_Pallets_Sending_Depot, 4) +
                Formatting.SafeTruncate(Euro_Pallets_Receiving_Depot, 4) +
                Formatting.SafeTruncate(Box_Pallets_Receiving_Depot, 4) +
                Formatting.SafeTruncate(Unloading_Notes, 70) +
                Formatting.SafeTruncate(Routing_ID_1, 35) +
                Formatting.SafeTruncate(Routing_ID_2, 35) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
