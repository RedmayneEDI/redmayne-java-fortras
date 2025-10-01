package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class D00
{
    private int max_length = 132;

    public String Sequential_Waybill_Item = null;
    public String Consignment_Position = null;
    public String Number_Of_Packages = null;
    public String Packaging_Type = null;
    public String Number_Of_Packages_On_Pallets = null;
    public String Packaging_Tyoe_On_Pallets = null;
    public String Content_Of_Goods = null;
    public String Code_And_Number = null;
    public String Actual_Weight = null;
    public String Chargable_Weight = null;
    public String Length_In_Meters = null;
    public String Width_In_Meters = null;
    public String Height_In_Meters = null;
    public String Cubic_Meters = null;
    public String Loading_Meters = null;
    public String Number_Of_Pallet_Locations = null;

    public void Parse(String rawText) throws Exception {
        var line = rawText;
        if (line.toUpperCase().startsWith("D00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("D00 length is invalid. Maximum length expected after D00 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Consignment_Position = Formatting.SafeSubstring(line, 3, 3);
        Number_Of_Packages = Formatting.SafeSubstring(line, 6, 4);
        Packaging_Type = Formatting.SafeSubstring(line, 10, 3);
        Number_Of_Packages_On_Pallets = Formatting.SafeSubstring(line, 13, 4);
        Packaging_Tyoe_On_Pallets = Formatting.SafeSubstring(line, 17, 3);
        Content_Of_Goods = Formatting.SafeSubstring(line, 20, 35);
        Code_And_Number = Formatting.SafeSubstring(line, 55, 35);
        Actual_Weight = Formatting.SafeSubstring(line, 90, 9);
        Chargable_Weight = Formatting.SafeSubstring(line, 99, 9);
        Length_In_Meters = Formatting.SafeSubstring(line, 108, 4);
        Width_In_Meters = Formatting.SafeSubstring(line, 112, 4);
        Height_In_Meters = Formatting.SafeSubstring(line, 116, 4);
        Cubic_Meters = Formatting.SafeSubstring(line, 120, 5);
        Loading_Meters = Formatting.SafeSubstring(line, 125, 3);
        Number_Of_Pallet_Locations = Formatting.SafeSubstring(line, 128, 4);
    }

    @Override
    public String toString() {
        var line = "D00" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
                Formatting.SafeTruncate(Consignment_Position, 3, '0', true) +
                Formatting.SafeTruncate(Number_Of_Packages, 4) +
                Formatting.SafeTruncate(Packaging_Type, 3) +
                Formatting.SafeTruncate(Number_Of_Packages_On_Pallets, 4) +
                Formatting.SafeTruncate(Packaging_Tyoe_On_Pallets, 3) +
                Formatting.SafeTruncate(Content_Of_Goods, 35) +
                Formatting.SafeTruncate(Code_And_Number, 35) +
                Formatting.SafeTruncate(Actual_Weight, 9, '0', true) +
                Formatting.SafeTruncate(Chargable_Weight, 9, '0', true) +
                Formatting.SafeTruncate(Length_In_Meters, 4, '0', true) +
                Formatting.SafeTruncate(Width_In_Meters, 4, '0', true) +
                Formatting.SafeTruncate(Height_In_Meters, 4, '0', true) +
                Formatting.SafeTruncate(Cubic_Meters, 5, '0', true) +
                Formatting.SafeTruncate(Loading_Meters, 3, '0', true) +
                Formatting.SafeTruncate(Number_Of_Pallet_Locations, 4, '0', true) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}