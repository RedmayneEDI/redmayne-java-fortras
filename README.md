![Redmayne EDI](./RedmayneEDI.png)

# redmayne-java-fortras

EDI processing library for the Fortras100 (or Fortras) standard of messages

Written originally in .Net Core 5 and ported to Java, this library provides functionality to read and write Fortras100 BORD512, STAT512 and ENTL512 messages.

A Unit Test class is provided that demonstrates creating messages in code and writing to file, as well as reading in plain-text files for processing.

# Fortras100 Messages
FORTRAS (short for Forschungs- und Entwicklungsgesellschaft für Transportwesen) is an industry-specific EDI standard used primarily in logistics and freight forwarding. 
Developed initially by System Alliance, it serves as a widely adopted ASCII/text-based format for exchanging critical logistics messages between freight forwarders and their business partners.

When implementing Fortras100 support to connect with a business partner, you should seek guidance from your business partner as to if they have implemeneted any additional customisations to the format - such as additional fields on data lines, or specifics on date and time formats, if non-ASCII characters (such as double byte characters) will be transmitted, and if empty padding on data lines can be truncated.

This library is built to the most generic definitions for the format with all fields interpretted as string values, without forcing specific data types to allow for the broadest message support. Data type checks should be part of any consuming code. 

# BORD512 Format

This message format represents a series of goods consignments for transport and is typically used by the Logstics industry to define either a pre-arranged transport of goods consignments or to make a request for goods to be transported.

# STAT512 Format

The STAT512 format represents status or event activities for goods consignments, such as collection or delivery information.

# ENTL512 Format

An ENTL512 format message provides goods consignment waypoint information, typically for depot arrival, unloading scans or conditional scans of goods consignments from transport vehicles.

# Reading and Writing File examples

## Simple read of a BORD512 file example

```
var bordInterpreter = new RedmayneEDI.Formats.Fortras100.BORD512.Interpreter();
var bordDocument = bordInterpreter.ReadFile("my-bord-file.txt");

var arrival = Formatting.GetDateTime(bordDocument.A00.Arrival_Date, bordDocument.A00.Arrival_Time).toString("MM/dd/yyyy HH:mm"); // US Date format
System.out.println("Waybill Number: " + bordDocument.A00.Waybill_Number);
System.out.println("Arriving: " + arrival);
```

## Simple write of a BORD512 file example

```
var document = new RedmayneEDI.Formats.Fortras100.BORD512.FortrasDocument();

document.PH.Sending_Party = "SHIPPER";
document.PH.Receiving_Party = "DELIVERY";
document.A00 = new RedmayneEDI.Formats.Fortras100.BORD512.Models.A00();
    document.A00.Data_Type_Qualifier = RedmayneEDI.Formats.Fortras100.Enums.DataTypeQualifiers.Standard;
    document.A00.Arrival_Date = RedmayneEDI.Formats.Fortras100.Formatting.GetDateTimeString("ddMMyyyy",Calendar.DAY_OF_MONTH, 7);
    document.A00.Arrival_Time = "0900";
    document.A00.Waybill_Number = "SHIPMENT123";
    document.A00.Waybill_Date = RedmayneEDI.Formats.Fortras100.Formatting.GetCurrentDateTime("ddMMyyyy");
// (Add some consignments to the document)

String filePath = "my-new-bord-file.bord512";
try (PrintWriter out = new PrintWriter(filePath)) {
    out.println(document.toString());
    System.out.println("File written: " + filePath);
} catch (IOException e) {
    e.printStackTrace();
}
```
