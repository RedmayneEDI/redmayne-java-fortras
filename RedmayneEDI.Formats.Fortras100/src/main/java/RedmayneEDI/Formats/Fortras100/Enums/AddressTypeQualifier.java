package RedmayneEDI.Formats.Fortras100.Enums;

/// <summary>
/// An Enum representation of the different Fortras address types.
/// </summary>
public class AddressTypeQualifier
{
    /// <summary>
    /// The Shipper/Consignor of the consignment.
    /// </summary>
    public static final int SHP = 1;
    /// <summary>
    /// The Recipient/Consignee of the consignment.
    /// </summary>
    public static final int CON = 2;
    /// <summary>
    /// The location where the consignment will be loaded.
    /// </summary>
    public static final int LOA = 4;
    /// <summary>
    /// A Neutral sending address for display to receving parties.
    /// </summary>
    public static final int NES = 8;
    /// <summary>
    /// A Neutral receiving address for display to sending parties.
    /// </summary>
    public static final int NEC = 16;
    /// <summary>
    /// The Payee of the invoice for the consignment.
    /// </summary>
    public static final int INV = 32;
    /// <summary>
    /// The address to which the invoice will be dispatched if different to the payee.
    /// </summary>
    public static final int CAD = 64;
    /// <summary>
    /// The Customs Agent or Declarant of the consignment.
    /// </summary>
    public static final int ZLA = 128;
    /// <summary>
    /// The Freight Forwarder who will receive the consignment from the Shipper.
    /// </summary>
    public static final int DLF = 256;
    /// <summary>
    /// A party that should be notified about this consignment.
    /// </summary>
    public static final int NOT = 512;
    /// <summary>
    /// For ABH messages, the ordering party (or customer) placing the collection request.
    /// </summary>
    public static final int ORD = 1024;
    /// <summary>
    /// For ABH messages, the ordering partner (or Shipper) raising the collection request on behalf of the customer.
    /// </summary>
    public static final int ORP = 2048;
}