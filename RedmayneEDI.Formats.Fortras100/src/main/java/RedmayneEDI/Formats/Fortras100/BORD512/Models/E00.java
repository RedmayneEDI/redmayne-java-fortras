package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class E00
{
    private int max_length = 422;

    public String Sequential_Waybill_Item = null;
    public String Consignment_Position = null;
    public String GG_Release = null;
    public String Number_Of_Packages = null;
    public String Gross_Weight_In_Grams = null;
    public String UN_Number = null;
    public String Packaging_Description = null;
    public String Multiplier = null;
    public String GG_Database_ID = null;
    public String Unique_Key = null;
    public String Material_Name = null;
    public String Additional_Text_For_Nag = null;
    public String Dangerous_Goods_Sample_Major = null;
    public String Dangerous_Goods_Sample_1 = null;
    public String Dangerous_Goods_Sample_2 = null;
    public String Dangerous_Goods_Sample_3 = null;
    public String PackingGroup_ClassificationCode = null;
    public String Net_Explosive_Mass_In_Grams = null;
    public String Transport_Class = null;
    public String Limited_Amount = null;
    public String Calculated_Dangerous_Goods_Points = null;
    public String Tunnel_Limitation_Code = null;
    public String Packaging_Group = null;
    public String Classification_Code = null;
    public String Exempt_Quantity = null;
    public String Net_Weight_In_Grams = null;
    public String Classification_Qualifiers = null;
    public String Harmful_To_Environment = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("E00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("E00 length is invalid. Maximum length expected after E00 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Consignment_Position = Formatting.SafeSubstring(line, 3, 3);
        GG_Release = Formatting.SafeSubstring(line, 6, 3);
        Number_Of_Packages = Formatting.SafeSubstring(line, 9, 4);
        Gross_Weight_In_Grams = Formatting.SafeSubstring(line, 13, 9);
        UN_Number = Formatting.SafeSubstring(line, 22, 4);
        Packaging_Description = Formatting.SafeSubstring(line, 26, 35);
        Multiplier = Formatting.SafeSubstring(line, 61, 4);
        GG_Database_ID = Formatting.SafeSubstring(line, 65, 3);
        Unique_Key = Formatting.SafeSubstring(line, 68, 10);
        Material_Name = Formatting.SafeSubstring(line, 78, 210);
        Additional_Text_For_Nag = Formatting.SafeSubstring(line, 288, 70);
        Dangerous_Goods_Sample_Major = Formatting.SafeSubstring(line, 358, 3);
        Dangerous_Goods_Sample_1 = Formatting.SafeSubstring(line, 361, 3);
        Dangerous_Goods_Sample_2 = Formatting.SafeSubstring(line, 364, 3);
        Dangerous_Goods_Sample_3 = Formatting.SafeSubstring(line, 367, 3);
        PackingGroup_ClassificationCode = Formatting.SafeSubstring(line, 371, 4);
        Net_Explosive_Mass_In_Grams = Formatting.SafeSubstring(line, 375, 9);
        Transport_Class = Formatting.SafeSubstring(line, 384, 1);
        Limited_Amount = Formatting.SafeSubstring(line, 385, 1);
        Calculated_Dangerous_Goods_Points = Formatting.SafeSubstring(line, 386, 9);
        Tunnel_Limitation_Code = Formatting.SafeSubstring(line, 395, 6);
        Packaging_Group = Formatting.SafeSubstring(line, 401, 4);
        Classification_Code = Formatting.SafeSubstring(line, 405, 4);
        Exempt_Quantity = Formatting.SafeSubstring(line, 409, 1);
        Net_Weight_In_Grams = Formatting.SafeSubstring(line, 410, 9);
        Classification_Qualifiers = Formatting.SafeSubstring(line, 419, 3);
        Harmful_To_Environment = Formatting.SafeSubstring(line, 422, 1);
    }

    @Override
    public String toString()
    {
        var line = "E00" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
                Formatting.SafeTruncate(Consignment_Position, 3, '0', true) +
                Formatting.SafeTruncate(GG_Release, 3) +
                Formatting.SafeTruncate(Number_Of_Packages, 4) +
                Formatting.SafeTruncate(Gross_Weight_In_Grams, 9) +
                Formatting.SafeTruncate(UN_Number, 4) +
                Formatting.SafeTruncate(Packaging_Description, 35) +
                Formatting.SafeTruncate(Multiplier, 4) +
                Formatting.SafeTruncate(GG_Database_ID, 3) +
                Formatting.SafeTruncate(Unique_Key, 10) +
                Formatting.SafeTruncate(Material_Name, 210) +
                Formatting.SafeTruncate(Additional_Text_For_Nag, 70) +
                Formatting.SafeTruncate(Dangerous_Goods_Sample_Major, 3) +
                Formatting.SafeTruncate(Dangerous_Goods_Sample_1, 3) +
                Formatting.SafeTruncate(Dangerous_Goods_Sample_2, 3) +
                Formatting.SafeTruncate(Dangerous_Goods_Sample_3, 3) +
                Formatting.SafeTruncate(PackingGroup_ClassificationCode, 4) +
                Formatting.SafeTruncate(Net_Explosive_Mass_In_Grams, 9) +
                Formatting.SafeTruncate(Transport_Class, 1) +
                Formatting.SafeTruncate(Limited_Amount, 1) +
                Formatting.SafeTruncate(Calculated_Dangerous_Goods_Points, 9) +
                Formatting.SafeTruncate(Tunnel_Limitation_Code, 6) +
                Formatting.SafeTruncate(Packaging_Group, 4) +
                Formatting.SafeTruncate(Classification_Code, 4) +
                Formatting.SafeTruncate(Exempt_Quantity, 1) +
                Formatting.SafeTruncate(Net_Weight_In_Grams, 9) +
                Formatting.SafeTruncate(Classification_Qualifiers, 3) +
                Formatting.SafeTruncate(Harmful_To_Environment, 1) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
