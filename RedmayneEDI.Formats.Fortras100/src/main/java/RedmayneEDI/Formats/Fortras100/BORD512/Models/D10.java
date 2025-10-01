package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class D10
{
    private int max_length = 208;

    public String Sequential_Waybill_Item = null;
    public String Consignment_Position = null;
    public String Product_Number = null;
    public String Country_Of_Origin = null;
    public String Raw_Mass_In_Grams = null;
    public String Fixed_Load_In_Grams = null;
    public String Procedure_Code = null;
    public String Customs_Value = null;
    public String Customs_Value_Currency = null;
    public String Statistical_Value = null;
    public String Statistical_Value_Currency = null;
    public String Appendix_Type_1 = null;
    public String Appendix_Number_1 = null;
    public String Appendix_Date_1 = null;
    public String Appendix_Type_2 = null;
    public String Appendix_Number_2 = null;
    public String Appendix_Date_2 = null;
    public String Appendix_Type_3 = null;
    public String Appendix_Number_3 = null;
    public String Appendix_Date_3 = null;
    public String Appendix_Type_4 = null;
    public String Appendix_Number_4 = null;
    public String Appendix_Date_4 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("D10")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("D10 length is invalid. Maximum length expected after D10 code is " + max_length + " but processed " + line.trim().length()); }
        Sequential_Waybill_Item = Formatting.SafeSubstring(line, 0, 3);
        Consignment_Position = Formatting.SafeSubstring(line, 3, 3);
        Product_Number = Formatting.SafeSubstring(line, 6, 15);
        Country_Of_Origin = Formatting.SafeSubstring(line, 21, 3);
        Raw_Mass_In_Grams = Formatting.SafeSubstring(line, 24, 9);
        Fixed_Load_In_Grams = Formatting.SafeSubstring(line, 33, 9);
        Procedure_Code = Formatting.SafeSubstring(line, 42, 5);
        Customs_Value = Formatting.SafeSubstring(line, 47, 11);
        Customs_Value_Currency = Formatting.SafeSubstring(line, 58, 3);
        Statistical_Value = Formatting.SafeSubstring(line, 62, 11);
        Statistical_Value_Currency = Formatting.SafeSubstring(line, 73, 3);
        Appendix_Type_1 = Formatting.SafeSubstring(line, 76, 6);
        Appendix_Number_1 = Formatting.SafeSubstring(line, 82, 20);
        Appendix_Date_1 = Formatting.SafeSubstring(line, 102, 8);
        Appendix_Type_2 = Formatting.SafeSubstring(line, 110, 6);
        Appendix_Number_2 = Formatting.SafeSubstring(line, 116, 20);
        Appendix_Date_2 = Formatting.SafeSubstring(line, 136, 8);
        Appendix_Type_3 = Formatting.SafeSubstring(line, 144, 6);
        Appendix_Number_3 = Formatting.SafeSubstring(line, 150, 20);
        Appendix_Date_3 = Formatting.SafeSubstring(line, 170, 8);
        Appendix_Type_4 = Formatting.SafeSubstring(line, 178, 6);
        Appendix_Number_4 = Formatting.SafeSubstring(line, 184, 20);
        Appendix_Date_4 = Formatting.SafeSubstring(line, 204, 8);
    }

    @Override
    public String toString()
    {
        var line = "D10" + Formatting.SafeTruncate(Sequential_Waybill_Item, 3, '0', true) +
                Formatting.SafeTruncate(Consignment_Position, 3, '0', true) +
                Formatting.SafeTruncate(Product_Number, 15) +
                Formatting.SafeTruncate(Country_Of_Origin, 3) +
                Formatting.SafeTruncate(Raw_Mass_In_Grams, 9) +
                Formatting.SafeTruncate(Fixed_Load_In_Grams, 9) +
                Formatting.SafeTruncate(Procedure_Code, 5) +
                Formatting.SafeTruncate(Customs_Value, 11) +
                Formatting.SafeTruncate(Customs_Value_Currency, 3) +
                Formatting.SafeTruncate(Statistical_Value, 11) +
                Formatting.SafeTruncate(Statistical_Value_Currency, 3) +
                Formatting.SafeTruncate(Appendix_Type_1, 6) +
                Formatting.SafeTruncate(Appendix_Number_1, 20) +
                Formatting.SafeTruncate(Appendix_Date_1, 8) +
                Formatting.SafeTruncate(Appendix_Type_2, 6) +
                Formatting.SafeTruncate(Appendix_Number_2, 20) +
                Formatting.SafeTruncate(Appendix_Date_2, 8) +
                Formatting.SafeTruncate(Appendix_Type_3, 6) +
                Formatting.SafeTruncate(Appendix_Number_3, 20) +
                Formatting.SafeTruncate(Appendix_Date_3, 8) +
                Formatting.SafeTruncate(Appendix_Type_4, 6) +
                Formatting.SafeTruncate(Appendix_Number_4, 20) +
                Formatting.SafeTruncate(Appendix_Date_4, 8) +
                Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
