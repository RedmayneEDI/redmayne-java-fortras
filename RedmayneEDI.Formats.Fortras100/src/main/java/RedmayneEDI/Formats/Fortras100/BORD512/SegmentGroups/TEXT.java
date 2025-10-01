package RedmayneEDI.Formats.Fortras100.BORD512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.BORD512.Models.H00;
import RedmayneEDI.Formats.Fortras100.BORD512.Models.H10;
import RedmayneEDI.Formats.Fortras100.Formatting;

import java.util.ArrayList;

/// <summary>
/// Free-Text information collection.
/// </summary>
public class TEXT
{
    public ArrayList<H00> H00 = new ArrayList<H00>();
    public ArrayList<H10> H10 = new ArrayList<H10>();

    public TEXT()
    {
        H00 = new ArrayList<H00>();
        H10 = new ArrayList<H10>();
    }

    @Override
    public String toString()
    {
        return Formatting.RecordSet(H00, 4) +
                Formatting.RecordSet(H10, 9);
    }

    public void SetSequentialWaybillItem(int waybillItemNumber)
    {
        for (var h00 : H00)
        {
            h00.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
        for (var h10 : H10)
        {
            h10.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
    }
}
