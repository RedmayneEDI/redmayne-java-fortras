package RedmayneEDI.Formats.Fortras100.Enums;

/// <summary>
/// Constant String representations of the different Fortras communication properties.
/// </summary>
public class CommunicationTypeQualifiers
{
    /// <summary>
    /// KPE: Indicates that this field is the Name of the contact point
    /// </summary>
    public static String Name = "KPE";
    /// <summary>
    /// TEL: This field is a telephone number.
    /// </summary>
    public static String Telephone = "TEL";
    /// <summary>
    /// EML: The data in this field is an email address.
    /// </summary>
    public static String Email = "EML";
}
