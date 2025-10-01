package RedmayneEDI.Formats.Fortras100.Enums;

/// <summary>
/// Constant string representation of Fortras Bordero message types.
/// </summary>
public class DataTypeQualifiers
{
    /// <summary>
    /// Standard Manifest Message.
    /// The Consignments are live in-transit between origin and destination.
    /// </summary>
    public static String Standard = "STD";
    /// <summary>
    /// Collection Request Message.
    /// The Consignments are not yet collected, and collection should be arranged based on the information in the message.
    /// </summary>
    public static String Abholung = "ABH";
}
