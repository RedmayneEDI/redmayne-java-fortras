package RedmayneEDI.Formats.Fortras100.BORD512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.BORD512.Models.B00;
import RedmayneEDI.Formats.Fortras100.BORD512.Models.B10;
import RedmayneEDI.Formats.Fortras100.Formatting;

import java.util.ArrayList;

/// <summary>
/// A representation of an Address for a Consignment.
/// </summary>
public class ADDRESS
{
    /// <summary>
    /// Address information.
    /// </summary>
    public B00 B00 = null;
    /// <summary>
    /// Contact information for the address.
    /// </summary>
    public ArrayList<B10> B10 = new ArrayList<B10>();

    public ADDRESS()
    {
        B00 = new B00();
        B10 = new ArrayList<B10>();
    }

    @Override
    public String toString()
    {
        return B00 +
                Formatting.RecordSet(B10, 9);
    }

    public void SetSequentialWaybillItem(int waybillItemNumber)
    {
        B00.Sequential_Waybill_Item = "" + waybillItemNumber;
        for (var b10 : B10)
        {
            b10.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
    }
}
