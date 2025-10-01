package RedmayneEDI.Formats.Fortras100.BORD512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.BORD512.Models.*;

import java.util.ArrayList;

/// <summary>
/// Details of a line item within the Consignment.
/// </summary>
public class CONSIGNMENT_LINE
{
    public D00 D00 = null;
    public ArrayList<D10> D10 = new ArrayList<D10>();
    /// <summary>
    /// Hazardous/ADR information for the Consignment line, if required.
    /// </summary>
    public DANGEROUS_GOODS DANGEROUS_GOODS = null;
    public ArrayList<F00> F00 = new ArrayList<F00>();

    public CONSIGNMENT_LINE()
    {
        D00 = new D00();
        D10 = new ArrayList<D10>();
        DANGEROUS_GOODS = new DANGEROUS_GOODS();
        F00 = new ArrayList<F00>();
    }

    @Override
    public String toString()
    {
        return D00 +
                Formatting.RecordSet(D10, 99) +
                DANGEROUS_GOODS +
                Formatting.RecordSet(F00, 999);
    }

    public void SetSequentialWaybillItem(int waybillItemNumber)
    {
        D00.Sequential_Waybill_Item = "" + waybillItemNumber;
        for (var d10 : D10)
        {
            d10.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
        if (DANGEROUS_GOODS != null)
        {
            DANGEROUS_GOODS.SetSequentialWaybillItem(waybillItemNumber);
        }
        for (var f00 : F00)
        {
            f00.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
    }

    public void SetConsignmentPosition(int consignmentPosition)
    {
        D00.Consignment_Position = "" + consignmentPosition;
        for (var d10 : D10)
        {
            d10.Consignment_Position = "" + consignmentPosition;
        }
        if (DANGEROUS_GOODS != null)
        {
            DANGEROUS_GOODS.SetConsignmentPosition(consignmentPosition);
        }
        for (var f00 : F00)
        {
            f00.Consignment_Position = "" + consignmentPosition;
        }
    }
}

