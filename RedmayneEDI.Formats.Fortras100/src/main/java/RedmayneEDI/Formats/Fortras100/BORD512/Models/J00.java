package RedmayneEDI.Formats.Fortras100.BORD512.Models;

import RedmayneEDI.Formats.Fortras100.Formatting;

public class J00
{
    private int max_length = 100;

    public String Total_Number_Of_Consignments = null;
    public String Total_Number_Of_Packages = null;
    public String Actual_Gross_Weight_In_KG = null;
    public String Number_Of_Box_Pallets = null;
    public String Number_Of_Euro_Flat_Pallets = null;
    public String Number_Of_Additional_Loading_Tools_Flat_Pallets = null;
    public String Number_Of_Additional_Loading_Tools_Box_Pallets = null;
    public String Totals_Taxable_From_I00 = null;
    public String Totals_Not_Taxable_From_I00 = null;
    public String Totals_Of_Product_Value_COD_From_I00 = null;
    public String Totals_Of_Customs_From_I00 = null;
    public String Totals_Of_Imports_Turnover_Tax_From_I00 = null;
    public String Totals_Of_Value_Added_Tax_From_I00 = null;

    public void Parse(String rawText) throws Exception
    {
        var line = rawText;
        if (line.toUpperCase().startsWith("J00")) { line = line.substring(3); }
        if (line.trim().length() > max_length) { throw new Exception("J00 length is invalid. Maximum length expected after J00 code is " + max_length + " but processed " + line.trim().length()); }
        Total_Number_Of_Consignments = Formatting.SafeSubstring(line, 0, 3);
        Total_Number_Of_Packages = Formatting.SafeSubstring(line, 3, 6);
        Actual_Gross_Weight_In_KG = Formatting.SafeSubstring(line, 9, 9);
        Number_Of_Box_Pallets = Formatting.SafeSubstring(line, 18, 4);
        Number_Of_Euro_Flat_Pallets = Formatting.SafeSubstring(line, 22, 4);
        Number_Of_Additional_Loading_Tools_Flat_Pallets = Formatting.SafeSubstring(line, 26, 4);
        Number_Of_Additional_Loading_Tools_Box_Pallets = Formatting.SafeSubstring(line, 30, 4);
        Totals_Taxable_From_I00 = Formatting.SafeSubstring(line, 34, 11);
        Totals_Not_Taxable_From_I00 = Formatting.SafeSubstring(line, 45, 11);
        Totals_Of_Product_Value_COD_From_I00 = Formatting.SafeSubstring(line, 56, 11);
        Totals_Of_Customs_From_I00 = Formatting.SafeSubstring(line, 67, 11);
        Totals_Of_Imports_Turnover_Tax_From_I00 = Formatting.SafeSubstring(line, 78, 11);
        Totals_Of_Value_Added_Tax_From_I00 = Formatting.SafeSubstring(line, 89, 11);
    }

    @Override
    public String toString()
    {
        var line = "J00" + Formatting.SafeTruncate(Total_Number_Of_Consignments, 3, '0', true) +
            Formatting.SafeTruncate(Total_Number_Of_Packages, 6, '0', true) +
            Formatting.SafeTruncate(Actual_Gross_Weight_In_KG, 9, '0', true) +
            Formatting.SafeTruncate(Number_Of_Box_Pallets, 4, '0', true) +
            Formatting.SafeTruncate(Number_Of_Euro_Flat_Pallets, 4, '0', true) +
            Formatting.SafeTruncate(Number_Of_Additional_Loading_Tools_Flat_Pallets, 4, '0', true) +
            Formatting.SafeTruncate(Number_Of_Additional_Loading_Tools_Box_Pallets, 4, '0', true) +
            Formatting.SafeTruncate(Totals_Taxable_From_I00, 11) +
            Formatting.SafeTruncate(Totals_Not_Taxable_From_I00, 11) +
            Formatting.SafeTruncate(Totals_Of_Product_Value_COD_From_I00, 11) +
            Formatting.SafeTruncate(Totals_Of_Customs_From_I00, 11) +
            Formatting.SafeTruncate(Totals_Of_Imports_Turnover_Tax_From_I00, 11) +
            Formatting.SafeTruncate(Totals_Of_Value_Added_Tax_From_I00, 11) +
            Formatting.CRLF;
        if (Formatting.TrimLines) { line = line.trim() + Formatting.CRLF; }
        return line;
    }
}
