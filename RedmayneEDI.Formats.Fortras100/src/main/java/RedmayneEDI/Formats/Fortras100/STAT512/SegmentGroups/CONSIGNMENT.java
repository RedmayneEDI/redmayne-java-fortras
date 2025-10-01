package RedmayneEDI.Formats.Fortras100.STAT512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.STAT512.Models.Q10;
import RedmayneEDI.Formats.Fortras100.STAT512.Models.Q11;

import java.util.ArrayList;

public class CONSIGNMENT
{
    public Q10 Q10 = null;
    public ArrayList<Q11> Q11 = null;
    public ArrayList<BARCODE> BARCODES = null;

    public CONSIGNMENT()
    {
        Q10 = new Q10();
        Q11 = new ArrayList<Q11>();
        BARCODES = new ArrayList<BARCODE>();
    }

    @Override
    public String toString()
    {
        return Q10 +
                Formatting.RecordSet(Q11, 999) +
                Formatting.RecordSet(BARCODES, 999);
    }

}
