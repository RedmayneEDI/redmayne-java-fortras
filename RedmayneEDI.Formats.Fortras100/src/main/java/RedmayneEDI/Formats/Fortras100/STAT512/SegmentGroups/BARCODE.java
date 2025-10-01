package RedmayneEDI.Formats.Fortras100.STAT512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.Formatting;
import RedmayneEDI.Formats.Fortras100.STAT512.Models.Q20;
import RedmayneEDI.Formats.Fortras100.STAT512.Models.Q30;

import java.util.ArrayList;

public class BARCODE
{
    public Q20 Q20 = null;
    public ArrayList<Q30> Q30 = null;

    public  BARCODE()
    {
        Q20 = new Q20();
        Q30 = new ArrayList<Q30>();
    }

    @Override
    public String toString()
    {
        return Q20 +
                Formatting.RecordSet(Q30, 999);
    }

}
