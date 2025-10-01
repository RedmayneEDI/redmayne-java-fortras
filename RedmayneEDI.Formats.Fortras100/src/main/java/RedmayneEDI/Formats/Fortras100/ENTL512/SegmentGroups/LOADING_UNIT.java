package RedmayneEDI.Formats.Fortras100.ENTL512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.ENTL512.Models.M10;
import RedmayneEDI.Formats.Fortras100.ENTL512.Models.M20;

public class LOADING_UNIT
{
    public M10 M10 = null;

    public M20 M20 = null;

    public LOADING_UNIT()
    {
        M10 = new M10();
        M20 = new M20();
    }

    @Override
    public String toString()
    {
        return M10.toString() + M20.toString();
    }
}