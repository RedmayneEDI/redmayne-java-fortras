package RedmayneEDI.Formats.Fortras100.Enums;

/// <summary>
/// Constant string representations of the different Fortras address types.
/// </summary>
public class AddressTypeQualifiers
{
    /// <summary>
    /// SHP: The Shipper/Consignor of the consignment.
    /// </summary>
    public static String Shipper = "SHP";
    /// <summary>
    /// CON: The Recipient/Consignee of the consignment.
    /// </summary>
    public static String Consignee = "CON";
    /// <summary>
    /// LOA: The location where the consignment will be loaded.
    /// </summary>
    public static String LoadingPoint = "LOA";
    /// <summary>
    /// NES: A Neutral sending address for display to receving parties.
    /// </summary>
    public static String NeutralSender = "NES";
    /// <summary>
    /// NEC: A Neutral receiving address for display to sending parties.
    /// </summary>
    public static String NeutralConsignee = "NEC";
    /// <summary>
    /// INV: The Payee of the invoice for the consignment.
    /// </summary>
    public static String InvoicePayee = "INV";
    /// <summary>
    /// CAD: The address to which the invoice will be dispatched if different to the payee.
    /// </summary>
    public static String InvoiceAddress = "CAD";
    /// <summary>
    /// ZLA: The Customs Agent or Declarant of the consignment.
    /// </summary>
    public static String CustomsDeclarant = "ZLA";
    /// <summary>
    /// DLF: The Freight Forwarder who will receive the consignment from the Shipper.
    /// </summary>
    public static String FreightForwarder = "DLF";
    /// <summary>
    /// NOT: A party that should be notified about this consignment.
    /// </summary>
    public static String NotifyParty = "NOT";
    /// <summary>
    /// ORD: For ABH messages, the ordering party (or customer) placing the collection request.
    /// </summary>
    public static String OrderingParty = "ORD";
    /// <summary>
    /// ORP: For ABH messages, the ordering partner (or Shipper) raising the collection request on behalf of the customer.
    /// </summary>
    public static String OrderingPartner = "ORP";
}
