package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

/// <summary>
/// Representation of an address for a Consignment.
/// </summary>
public class B00
{
    private int max_length = 333;

    public String Sequential_Waybill_Item = null;
    public String Address_Type_Qualifier = null;
    public String Name_1 = null;
    public String Stree_Name_And_Number = null;
    public String Country_Code = null;
    public String Postcode = null;
    public String Place = null;
    public String Partner_ID = null;
    public String Name_2 = null;
    public String Name_3 = null;
    public String Town_Area = null;
    public String Global_Localization_Number = null;
    public String Customs_ID = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("B00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("B00 length is invalid. Maximum length expected after B00 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Address_Type_Qualifier = Formatting.SafeSubstring(line, 3, 3);
        Name_1 = Formatting.SafeSubstring(line, 6, 35);
        Stree_Name_And_Number = Formatting.SafeSubstring(line, 41, 35);
        Country_Code = Formatting.SafeSubstring(line, 76, 3);
        Postcode = Formatting.SafeSubstring(line, 79, 9);
        Place = Formatting.SafeSubstring(line, 88, 35);
        Partner_ID = Formatting.SafeSubstring(line, 123, 35);
        Name_2 = Formatting.SafeSubstring(line, 158, 35);
        Name_3 = Formatting.SafeSubstring(line, 193, 35);
        Town_Area = Formatting.SafeSubstring(line, 228, 35);
        Global_Localization_Number = Formatting.SafeSubstring(line, 263, 35);
        Customs_ID = Formatting.SafeSubstring(line, 298, 35);
    }

    @Override
    public String toString()
    {
        var line = "B00" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
            Formatting.SafeTruncate(Address_Type_Qualifier, 3) +
            Formatting.SafeTruncate(Name_1, 35) +
            Formatting.SafeTruncate(Stree_Name_And_Number, 35) +
            Formatting.SafeTruncate(Country_Code, 3) +
            Formatting.SafeTruncate(Postcode, 9) +
            Formatting.SafeTruncate(Place, 35) +
            Formatting.SafeTruncate(Partner_ID, 35) +
            Formatting.SafeTruncate(Name_2, 35) +
            Formatting.SafeTruncate(Name_3, 35) +
            Formatting.SafeTruncate(Town_Area, 35) +
            Formatting.SafeTruncate(Global_Localization_Number, 35) +
            Formatting.SafeTruncate(Customs_ID, 35) +
            Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }

}
