package RedmayneEDI.Formats.Fortras100.BORD512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.BORD512.Models.E00;
import RedmayneEDI.Formats.Fortras100.BORD512.Models.E10;
import RedmayneEDI.Formats.Fortras100.Formatting;

import java.util.ArrayList;

/// <summary>
/// Hazardous/ADR Information structure.
/// </summary>
public class DANGEROUS_GOODS
{
    public ArrayList<E00> E00 = new ArrayList<E00>();
    public ArrayList<E10> E10 = new ArrayList<E10>();

    public DANGEROUS_GOODS()
    {
        E00 = new ArrayList<E00>();
        E10 = new ArrayList<E10>();
    }

    @Override
    public String toString()
    {
        return Formatting.RecordSet(E00, 9) +
                Formatting.RecordSet(E10, 4);
    }

    public void SetSequentialWaybillItem(int waybillItemNumber)
    {
        for (var e00 : E00)
        {
            e00.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
        for (var e10 : E10)
        {
            e10.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
    }
    public void SetConsignmentPosition(int consignmentPosition)
    {
        for (var e00 : E00)
        {
            e00.Consignment_Position = "" + consignmentPosition;
        }
        for (var e10 : E10)
        {
            e10.Consignment_Position = "" + consignmentPosition;
        }
    }
}

