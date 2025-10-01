package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class A00
{
    private int max_length = 427;

    public String Data_Type_Qualifier = null;
    public String Release_Version = null;
    public String Waybill_Number = null;
    public String Waybill_Date = null;
    public String Transport_Type = null;
    public String Product = null;
    public String Code_List = null;
    public String Currency = null;
    public String Waybill_Consignor_ID = null;
    public String Waybill_Consignee_ID = null;
    public String Freight_Operator = null;
    public String Freight_Operator_Country = null;
    public String Freight_Operator_Postcode = null;
    public String Freight_Operator_Town = null;
    public String Vehicle_License_Number_1 = null;
    public String Vehicle_License_Number_2 = null;
    public String Routing_ID_1 = null;
    public String Routing_ID_2 = null;
    public String Test_Code = null;
    public String Arrival_Date = null;
    public String Arrival_Time = null;
    public String Arrival_Time_Qualifier = null;
    public String Traffic_Type_2 = null;
    public String Driver_Name = null;
    public String Driver_Phone = null;

    public void Parse(String rawText) throws Exception
    {
        String line = rawText;
        if (line.toUpperCase().startsWith("A00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("A00 length is invalid. Maximum length expected after A00 code is " + max_length + " but processed " + line.trim().length()); }
        Data_Type_Qualifier = Formatting.SafeSubstring(line, 0, 3);
        Release_Version = Formatting.SafeSubstring(line, 3, 3);
        Waybill_Number = Formatting.SafeSubstring(line, 6, 35);
        Waybill_Date = Formatting.SafeSubstring(line, 41, 8);
        Transport_Type = Formatting.SafeSubstring(line, 49, 3);
        Product = Formatting.SafeSubstring(line, 52, 3);
        Code_List = Formatting.SafeSubstring(line, 55, 3);
        Currency = Formatting.SafeSubstring(line, 58, 3);
        Waybill_Consignor_ID = Formatting.SafeSubstring(line, 61, 35);
        Waybill_Consignee_ID = Formatting.SafeSubstring(line, 96, 35);
        Freight_Operator = Formatting.SafeSubstring(line, 131, 35);
        Freight_Operator_Country = Formatting.SafeSubstring(line, 166, 3);
        Freight_Operator_Postcode = Formatting.SafeSubstring(line, 169, 9);
        Freight_Operator_Town = Formatting.SafeSubstring(line, 178, 35);
        Vehicle_License_Number_1 = Formatting.SafeSubstring(line, 213, 35);
        Vehicle_License_Number_2 = Formatting.SafeSubstring(line, 248, 35);
        Routing_ID_1 = Formatting.SafeSubstring(line, 283, 35);
        Routing_ID_2 = Formatting.SafeSubstring(line, 318, 35);
        Test_Code = Formatting.SafeSubstring(line, 353, 1);
        Arrival_Date = Formatting.SafeSubstring(line, 354, 8);
        Arrival_Time = Formatting.SafeSubstring(line, 362, 4);
        Arrival_Time_Qualifier = Formatting.SafeSubstring(line, 366, 3);
        Traffic_Type_2 = Formatting.SafeSubstring(line, 369, 3);
        Driver_Name = Formatting.SafeSubstring(line, 372, 35);
        Driver_Phone = Formatting.SafeSubstring(line, 407, 20);
    }

    @Override
    public String toString()
    {
        var line = "A00" + Formatting.SafeTruncate(Data_Type_Qualifier, 3) +
                Formatting.SafeTruncate(Release_Version, 3, '0', true) +
                Formatting.SafeTruncate(Waybill_Number, 35) +
                Formatting.SafeTruncate(Waybill_Date, 8) +
                Formatting.SafeTruncate(Transport_Type, 3) +
                Formatting.SafeTruncate(Product, 3) +
                Formatting.SafeTruncate(Code_List, 3) +
                Formatting.SafeTruncate(Currency, 3) +
                Formatting.SafeTruncate(Waybill_Consignor_ID, 35) +
                Formatting.SafeTruncate(Waybill_Consignee_ID, 35) +
                Formatting.SafeTruncate(Freight_Operator, 35) +
                Formatting.SafeTruncate(Freight_Operator_Country, 3) +
                Formatting.SafeTruncate(Freight_Operator_Postcode, 9) +
                Formatting.SafeTruncate(Freight_Operator_Town, 35) +
                Formatting.SafeTruncate(Vehicle_License_Number_1, 35) +
                Formatting.SafeTruncate(Vehicle_License_Number_2, 35) +
                Formatting.SafeTruncate(Routing_ID_1, 35) +
                Formatting.SafeTruncate(Routing_ID_2, 35) +
                Formatting.SafeTruncate(Test_Code, 1) +
                Formatting.SafeTruncate(Arrival_Date, 8) +
                Formatting.SafeTruncate(Arrival_Time, 4) +
                Formatting.SafeTruncate(Arrival_Time_Qualifier, 3) +
                Formatting.SafeTruncate(Traffic_Type_2, 3) +
                Formatting.SafeTruncate(Driver_Name, 35) +
                Formatting.SafeTruncate(Driver_Phone, 20) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
