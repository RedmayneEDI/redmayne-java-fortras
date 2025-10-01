package RedmayneEDI.Formats.Fortras100.ENTL512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class O00
{
    private int max_length = 260;

    public String Preliminary_Consignment_No_Receiving_Depot = null;
    public String Qualifier = null;
    public String Name_1 = null;
    public String Name_2 = null;
    public String Street_And_Street_Number = null;
    public String Country_Code = null;
    public String Postcode = null;
    public String Place = null;
    public String Town_Area = null;
    public String Consignor_ID = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("O00")) { line = Formatting.SafeSubstring(line,3); }
        if (line.trim().length() > max_length) { throw new Exception("O00 length is invalid. Maximum length expected after O00 code is " + max_length + " but processed " + line.trim().length()); }
        Preliminary_Consignment_No_Receiving_Depot = Formatting.SafeSubstring(line, 0, 35);
        Qualifier = Formatting.SafeSubstring(line, 35, 3);
        Name_1 = Formatting.SafeSubstring(line, 38, 35);
        Name_2 = Formatting.SafeSubstring(line, 73, 35);
        Street_And_Street_Number = Formatting.SafeSubstring(line, 108, 35);
        Country_Code = Formatting.SafeSubstring(line, 143, 3);
        Postcode = Formatting.SafeSubstring(line, 146, 9);
        Place = Formatting.SafeSubstring(line, 155, 35);
        Town_Area = Formatting.SafeSubstring(line, 190, 35);
        Consignor_ID = Formatting.SafeSubstring(line, 225, 35);
    }

    public String toString()
    {
        var line = "O00" + Formatting.SafeTruncate(Preliminary_Consignment_No_Receiving_Depot, 35) +
                Formatting.SafeTruncate(Qualifier, 3) +
                Formatting.SafeTruncate(Name_1, 35) +
                Formatting.SafeTruncate(Name_2, 35) +
                Formatting.SafeTruncate(Street_And_Street_Number, 35) +
                Formatting.SafeTruncate(Country_Code, 3) +
                Formatting.SafeTruncate(Postcode, 9) +
                Formatting.SafeTruncate(Place, 35) +
                Formatting.SafeTruncate(Town_Area, 35) +
                Formatting.SafeTruncate(Consignor_ID, 35) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
