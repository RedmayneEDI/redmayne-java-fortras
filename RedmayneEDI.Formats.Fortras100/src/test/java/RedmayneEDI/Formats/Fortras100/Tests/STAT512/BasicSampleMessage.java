package RedmayneEDI.Formats.Fortras100.Tests.STAT512;

import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.STAT512.FortrasDocument;
import RedmayneEDI.Formats.Fortras100.STAT512.Models.*;
import RedmayneEDI.Formats.Fortras100.STAT512.SegmentGroups.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/// <summary>
/// Generates a sample standard manifest Fortras STAT512 message.
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

        Document.Q00 = new Q00();
            Document.Q00.Release_Version = "1";
            Document.Q00.Sender_ID = "FORWARDER";
            Document.Q00.Receiver_ID = "SHIPPER";
            Document.Q00.Code_List = "STD";
            Document.Q00.Causing_Party_ID = "DELIVERY";

        Document.CONSIGNMENTS = new ArrayList<CONSIGNMENT>();
            var consignment = new CONSIGNMENT();
                var q10 = new Q10();
                    q10.Event_Date = Formatting.GetDateTimeString("yyyyMMdd", Calendar.HOUR_OF_DAY, -1);
                    q10.Event_Time = Formatting.GetDateTimeString("HHmm", Calendar.HOUR_OF_DAY, -1);
                    q10.Sender_Shipment_ID = "WAYBILL123456";
                    q10.Receiver_Shipment_ID = "NEWREF2023";
                consignment.Q10 = q10;

                consignment.BARCODES = new ArrayList<BARCODE>();
                    var barcode = new BARCODE();
                        var q20 = new Q20();
                            q20.Barcode = "003123456";
                            q20.Scan_Code = "DEL";
                            q20.Scan_Date = Formatting.GetDateTimeString("yyyyMMdd", Calendar.HOUR_OF_DAY, -1);
                            q20.Scan_Time = Formatting.GetDateTimeString("HHmmss", Calendar.HOUR_OF_DAY, -1);
                        barcode.Q20 = q20;
                    consignment.BARCODES.add(barcode);
        Document.CONSIGNMENTS.add(consignment);

            consignment = new CONSIGNMENT();
                q10 = new Q10();
                    q10.Event_Date = Formatting.GetCurrentDateTime("yyyyMMdd");
                    q10.Event_Time = Formatting.GetCurrentDateTime("HHmm");
                    q10.Sender_Shipment_ID = "WAYBILL123457";
                    q10.Receiver_Shipment_ID = "NEWREF2024";
                consignment.Q10 = q10;

                consignment.BARCODES = new ArrayList<BARCODE>();
                    barcode = new BARCODE();
                        q20 = new Q20();
                            q20.Barcode = "003123457";
                            q20.Scan_Code = "DMG";
                            q20.Scan_Date = Formatting.GetCurrentDateTime("yyyyMMdd");
                            q20.Scan_Time = Formatting.GetCurrentDateTime("HHmmss");
                        barcode.Q20 = q20;
                consignment.BARCODES.add(barcode);
        Document.CONSIGNMENTS.add(consignment);
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

                File outputFile = new File(dir, "fortras512.stat.sample.txt");
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