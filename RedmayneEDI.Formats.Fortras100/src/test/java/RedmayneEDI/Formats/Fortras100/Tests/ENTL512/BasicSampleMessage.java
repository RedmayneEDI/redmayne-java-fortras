package RedmayneEDI.Formats.Fortras100.Tests.ENTL512;

import RedmayneEDI.Formats.Fortras100.ENTL512.FortrasDocument;
import RedmayneEDI.Formats.Fortras100.ENTL512.Models.*;
import RedmayneEDI.Formats.Fortras100.ENTL512.SegmentGroups.*;
import RedmayneEDI.Formats.Fortras100.Formatting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/// <summary>
/// Generates a sample standard manifest Fortras ENTL512 message.
/// </summary>
public class BasicSampleMessage
{
    public FortrasDocument Document = null;

    public BasicSampleMessage()
    {
        Document = new FortrasDocument();

        // Send the Sending and Receiving Party ID's
        Document.PH.Sending_Party = "FORWARDER";
        Document.PH.Receiving_Party = "SHIPPER";

        Document.M00 = new M00();
        Document.M00.Release_Version = "1";
        Document.M00.Waybill_Consignor = "FORWARDER";
        Document.M00.Waybill_Consignee = "SHIPPER";
        Document.M00.Code_List = "STD";
        Document.M00.Arrival_Date = Formatting.GetDateTimeString("ddMMyyyy", Calendar.HOUR_OF_DAY, -6);
        Document.M00.Arrival_Time = Formatting.GetDateTimeString("HHmm", Calendar.HOUR_OF_DAY, -6);
        Document.M00.Data_Type_Qualifier = "STD";
        Document.M00.Unloading_Start_Date = Formatting.GetDateTimeString("ddMMyyyy", Calendar.HOUR_OF_DAY, -1);
        Document.M00.Unloading_Start_Time = Formatting.GetDateTimeString("HHmm", Calendar.HOUR_OF_DAY, -1);

        Document.LOADING_UNITS = new ArrayList<LOADING_UNIT>();
            var loadingUnit = new LOADING_UNIT();
                loadingUnit.M10 = new M10();
                    loadingUnit.M10.Loading_Unit_Number = "1";
                    loadingUnit.M10.Condition_Of_Loading_Unit_1 = "OK";
                    loadingUnit.M10.Additional_Text_1 = "ITEM UNLOADED";
                loadingUnit.M20 = new M20();
                    loadingUnit.M20.Lead_Seal_Number_1 = "SEAL1";
                    loadingUnit.M20.Condition_Lead_Seal_Number_1 = "OK";
        Document.LOADING_UNITS.add(loadingUnit);

        loadingUnit = new LOADING_UNIT();
            loadingUnit.M10 = new M10();
                loadingUnit.M10.Loading_Unit_Number = "2";
                loadingUnit.M10.Condition_Of_Loading_Unit_1 = "OK";
                loadingUnit.M10.Additional_Text_1 = "ITEM UNLOADED";
            loadingUnit.M20 = new M20();
                loadingUnit.M20.Lead_Seal_Number_1 = "SEAL1";
                loadingUnit.M20.Condition_Lead_Seal_Number_1 = "OK";
        Document.LOADING_UNITS.add(loadingUnit);

        Document.N00 = new ArrayList<N00>();
            var n00 = new N00();
                n00.Waybill_Number_Sending_Depot = "WAYBILL123456";
                n00.Sequential_Waybill_Item = "1";
                n00.Status_Code_1 = "OK";
                n00.Packaging_Type_1 = "PX";
                n00.Text_Notes_1 = "UNLOADED OK";
        Document.N00.add(n00);

        Document.UEZS = new ArrayList<UEZ>();
            var uez = new UEZ();
                uez.O20 = new ArrayList<O20>();
                    var o20 = new O20();
                        o20.Preliminary_Consignment_No_Receiving_Depot = "1";
                        o20.Barcode_1 = "03123456";
                        o20.Error_Message_Code_1 = "OK";
                        o20.Additional_Text_1 = "SCANNED INTO WAREHOUSE";
                uez.O20.add(o20);
                    o20 = new O20();
                        o20.Preliminary_Consignment_No_Receiving_Depot = "1";
                        o20.Barcode_1 = "03123457";
                        o20.Error_Message_Code_1 = "OK";
                        o20.Additional_Text_1 = "SCANNED INTO WAREHOUSE";
                uez.O20.add(o20);
        Document.UEZS.add(uez);
    }

    public String CreateMessage() {
        return CreateMessage("");
    }

    public String CreateMessage(String outputDir)
    {
        try {
            if (outputDir != null && !outputDir.trim().isEmpty()) {
                File dir = new File(outputDir);
                if (!dir.exists()) {
                    dir.mkdirs(); // Create directory (including parents if needed)
                }

                File outputFile = new File(dir, "fortras512.entl.sample.txt");
                try (FileWriter writer = new FileWriter(outputFile)) {
                    writer.write(Document.toString());
                }
                System.out.println("Created " + outputFile.getAbsolutePath());
                return outputFile.getAbsolutePath();
            } else {
                System.out.println(Document.toString());
                return Document.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or throw a runtime exception if you'd rather not swallow errors
        }
    }
}
