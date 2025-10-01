package RedmayneEDI.Formats.Fortras100.BORD512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.BORD512.*;
import RedmayneEDI.Formats.Fortras100.BORD512.Models.*;

import java.util.ArrayList;

/// <summary>
/// A representation of a Consignment. One unit of work to represent the movement of goods from order origin to destination.
/// </summary>
public class CONSIGNMENT
{
    /// <summary>
    /// A Collection of Addresses relating to the Consignment.
    /// </summary>
    public ArrayList<ADDRESS> ADDRESSES = new ArrayList<ADDRESS>();
    /// <summary>
    /// A Collection of Customs Information for the Consignment.
    /// </summary>
    public ArrayList<C00> C00 = new ArrayList<C00>();
    /// <summary>
    /// A Collection of the item details that make up this Consignment.
    /// </summary>
    public ArrayList<CONSIGNMENT_LINE> CONSIGNMENT_LINES = new ArrayList<CONSIGNMENT_LINE>();
    /// <summary>
    /// Summary information for the Consignment.
    /// </summary>
    public G00 G00 = null;
    /// <summary>
    /// A Collection of free-text information relating to the Consignment.
    /// </summary>
    public TEXT TEXTS = null;
    /// <summary>
    /// Invoicing-related information for the Consignment.
    /// </summary>
    public ArrayList<I00> I00 = new ArrayList<I00>();

    public CONSIGNMENT()
    {
        ADDRESSES = new ArrayList<ADDRESS>();
        C00 = new ArrayList<C00>();
        CONSIGNMENT_LINES = new ArrayList<CONSIGNMENT_LINE>();
        G00 = new G00();
        TEXTS = new TEXT();
        I00 = new ArrayList<I00>();
    }

    @Override
    public String toString()
    {
        int i = 1;
        for (var consignment_line : CONSIGNMENT_LINES)
        {
            consignment_line.SetConsignmentPosition(i++);
        }

        return Formatting.RecordSet(ADDRESSES, 999) +
                Formatting.RecordSet(C00, 999) +
                Formatting.RecordSet(CONSIGNMENT_LINES, 999) +
                G00 +
                TEXTS +
                Formatting.RecordSet(I00, 9);
    }

    public void SetSequentialWaybillItem(int waybillItemNumber)
    {
        for (var address : ADDRESSES)
        {
            address.SetSequentialWaybillItem(waybillItemNumber);
        }
        for (var c00 : C00)
        {
            c00.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
        for (var consignment_line : CONSIGNMENT_LINES)
        {
            consignment_line.SetSequentialWaybillItem(waybillItemNumber);
        }
        G00.Sequential_Waybill_Item = "" + waybillItemNumber;
        TEXTS.SetSequentialWaybillItem(waybillItemNumber);
        for (var i00 : I00)
        {
            i00.Sequential_Waybill_Item = "" + waybillItemNumber;
        }
    }
}
