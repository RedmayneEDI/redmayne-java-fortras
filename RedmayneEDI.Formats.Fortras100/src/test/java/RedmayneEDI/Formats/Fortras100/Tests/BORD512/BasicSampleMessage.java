package RedmayneEDI.Formats.Fortras100.Tests.BORD512;

import RedmayneEDI.Formats.Fortras100.BORD512.*;
import RedmayneEDI.Formats.Fortras100.BORD512.Models.*;
import RedmayneEDI.Formats.Fortras100.BORD512.SegmentGroups.*;
import RedmayneEDI.Formats.Fortras100.Enums.AddressTypeQualifiers;
import RedmayneEDI.Formats.Fortras100.Enums.CommunicationTypeQualifiers;
import RedmayneEDI.Formats.Fortras100.Enums.DataTypeQualifiers;
import RedmayneEDI.Formats.Fortras100.Formatting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class BasicSampleMessage
{
    public FortrasDocument Document = null;

    public BasicSampleMessage()
    {
        Document = new FortrasDocument();

        // Send the Sending and Receiving Party ID's
        Document.PH.Sending_Party = "SHIPPER";
        Document.PH.Receiving_Party = "DELIVERY";

        // Set the Message Header
        Document.A00 = new A00();
        Document.A00.Data_Type_Qualifier = DataTypeQualifiers.Standard;
        Document.A00.Arrival_Date = Formatting.GetDateTimeString("ddMMyyyy", Calendar.DAY_OF_YEAR, 7);
        Document.A00.Arrival_Time = "0900";
        Document.A00.Waybill_Number = "TESTWAYBILL123456";

        // Create a collection of Consignments - these are the inidividual units of work in the manifest.
        Document.CONSIGNMENTS = new ArrayList<CONSIGNMENT>();

        var consignment = new CONSIGNMENT();
            consignment.ADDRESSES = new ArrayList<ADDRESS>();
                var address = new ADDRESS();
                    address.B00 = new B00();
                        address.B00.Address_Type_Qualifier = AddressTypeQualifiers.Shipper;
                        address.B00.Country_Code = "GB";
                        address.B00.Name_1 = "SENDER NAME HERE";
                        address.B00.Postcode = "AD12 1AS";
                        address.B00.Stree_Name_And_Number = "1 SOME STREET";
                        address.B00.Town_Area = "MANCHESTER";
                    address.B10 = new ArrayList<B10>();
                        var b10 = new B10();
                            b10.Communication_Type_Qualifier = CommunicationTypeQualifiers.Email;
                            b10.Content = "SOURCECONTACT@SOURCECOMPANY.COM";
                        address.B10.add(b10);
                consignment.ADDRESSES.add(address);
                address = new ADDRESS();
                    address.B00 = new B00();
                        address.B00.Address_Type_Qualifier = AddressTypeQualifiers.Consignee;
                        address.B00.Country_Code = "GB";
                        address.B00.Name_1 = "RECEIPIENT NAME HERE";
                        address.B00.Postcode = "N11 123";
                        address.B00.Stree_Name_And_Number = "1 SOME OTHER STREET";
                        address.B00.Town_Area = "LONDON";
                    address.B10 = new ArrayList<B10>();
                        b10 = new B10();
                            b10.Communication_Type_Qualifier = CommunicationTypeQualifiers.Name;
                            b10.Content = "SOME CONTACT PERSON";
                        address.B10.add(b10);
                        b10 = new B10();
                            b10.Communication_Type_Qualifier = CommunicationTypeQualifiers.Email;
                            b10.Content = "CONTACT@DELIVERYCOMPANY.COM";
                        address.B10.add(b10);
                        b10 = new B10();
                            b10.Communication_Type_Qualifier = CommunicationTypeQualifiers.Telephone;
                            b10.Content = "+441234567890";
                        address.B10.add(b10);
                consignment.ADDRESSES.add(address);
            // A collection of Consignment Lines - the individual items that constitute this work unit
            consignment.CONSIGNMENT_LINES = new ArrayList<CONSIGNMENT_LINE>();
                var consignmentLine = new CONSIGNMENT_LINE();
                    consignmentLine.D00 = new D00();
                        consignmentLine.D00.Actual_Weight = "500";
                        consignmentLine.D00.Chargable_Weight = "500";
                        consignmentLine.D00.Consignment_Position = "1";
                        consignmentLine.D00.Content_Of_Goods = "SHINY THINGS";
                        consignmentLine.D00.Cubic_Meters = "1";
                        consignmentLine.D00.Height_In_Meters = "1";
                        consignmentLine.D00.Width_In_Meters = "1";
                        consignmentLine.D00.Length_In_Meters = "1";
                        consignmentLine.D00.Number_Of_Packages = "2";
                        consignmentLine.D00.Number_Of_Packages_On_Pallets = "2";
                        consignmentLine.D00.Packaging_Type = "PAL";
                    consignmentLine.F00 = new ArrayList<F00>();
                        var f00 = new F00();
                            f00.Barcode = "0034123456765432";
                    consignmentLine.F00.add(f00);
                        f00 = new F00();
                            f00.Barcode = "0034123456765433";
                    consignmentLine.F00.add(f00);
            consignment.CONSIGNMENT_LINES.add(consignmentLine);
            consignment.G00 = new G00();
                consignment.G00.Delivery_Terms = "EXW";
                consignment.G00.Actual_Consignment_Gross_Weight_In_Grams = "500";
                consignment.G00.Chargeable_Consignment_Weight_In_Grams = "500";
                consignment.G00.Cubic_Metres = "1";
            consignment.TEXTS = new TEXT();
                consignment.TEXTS.H00 = new ArrayList<H00>();
                    var h00 = new H00();
                        h00.Text_Code_1 = "503";
                        h00.Additional_Text_1 = "SOME TEST CODE HERE";
                    consignment.TEXTS.H00.add(h00);
        Document.CONSIGNMENTS.add(consignment);

        consignment = new CONSIGNMENT();
            consignment.ADDRESSES = new ArrayList<ADDRESS>();
                address = new ADDRESS();
                    address.B00 = new B00();
                        address.B00.Address_Type_Qualifier = AddressTypeQualifiers.Shipper;
                        address.B00.Country_Code = "GB";
                        address.B00.Name_1 = "SENDER NAME HERE";
                        address.B00.Postcode = "AD12 1AS";
                        address.B00.Stree_Name_And_Number = "1 SOME STREET";
                        address.B00.Town_Area = "MANCHESTER";
                    address.B10 = new ArrayList<B10>();
                        b10 = new B10();
                            b10.Communication_Type_Qualifier = CommunicationTypeQualifiers.Email;
                            b10.Content = "SOURCECONTACT@SOURCECOMPANY.COM";
                        address.B10.add(b10);
                consignment.ADDRESSES.add(address);
                address = new ADDRESS();
                    address.B00 = new B00();
                        address.B00.Address_Type_Qualifier = AddressTypeQualifiers.Consignee;
                        address.B00.Country_Code = "DE";
                        address.B00.Name_1 = "GERMAN COMPANY NAME";
                        address.B00.Postcode = "86100";
                        address.B00.Stree_Name_And_Number = "DEUTCH RD 4";
                        address.B00.Town_Area = "BERLIN";
                    address.B10 = new ArrayList<B10>();
                        b10 = new B10();
                            b10.Communication_Type_Qualifier = CommunicationTypeQualifiers.Email;
                            b10.Content = "CONTACT@GERMANCOMPANY.COM";
                        address.B10.add(b10);
                consignment.ADDRESSES.add(address);
            // A collection of Consignment Lines - the individual items that constitute this work unit
            consignment.CONSIGNMENT_LINES = new ArrayList<CONSIGNMENT_LINE>();
                consignmentLine = new CONSIGNMENT_LINE();
                    consignmentLine.D00 = new D00();
                        consignmentLine.D00.Actual_Weight = "500";
                        consignmentLine.D00.Chargable_Weight = "500";
                        consignmentLine.D00.Consignment_Position = "1";
                        consignmentLine.D00.Content_Of_Goods = "SHINY THINGS";
                        consignmentLine.D00.Cubic_Meters = "1";
                        consignmentLine.D00.Height_In_Meters = "1";
                        consignmentLine.D00.Width_In_Meters = "1";
                        consignmentLine.D00.Length_In_Meters = "1";
                        consignmentLine.D00.Number_Of_Packages = "2";
                        consignmentLine.D00.Number_Of_Packages_On_Pallets = "2";
                        consignmentLine.D00.Packaging_Type = "PAL";
                    consignmentLine.F00 = new ArrayList<F00>();
                        f00 = new F00();
                            f00.Barcode = "0034121234567890";
                    consignmentLine.F00.add(f00);
                        f00 = new F00();
                            f00.Barcode = "0034121234567891";
                    consignmentLine.F00.add(f00);
            consignment.CONSIGNMENT_LINES.add(consignmentLine);
            consignment.G00 = new G00();
                consignment.G00.Delivery_Terms = "EXW";
                consignment.G00.Actual_Consignment_Gross_Weight_In_Grams = "500";
                consignment.G00.Chargeable_Consignment_Weight_In_Grams = "500";
                consignment.G00.Cubic_Metres = "1";
            consignment.TEXTS = new TEXT();
                consignment.TEXTS.H00 = new ArrayList<H00>();
                    h00 = new H00();
                        h00.Text_Code_1 = "503";
                        h00.Additional_Text_1 = "SOME TEST CODE HERE";
                consignment.TEXTS.H00.add(h00);
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

                File outputFile = new File(dir, "fortras512.bord.sample.txt");
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