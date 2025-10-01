package RedmayneEDI.Formats.Fortras100.ENTL512.SegmentGroups;

import RedmayneEDI.Formats.Fortras100.ENTL512.Models.O00;
import RedmayneEDI.Formats.Fortras100.ENTL512.Models.O10;
import RedmayneEDI.Formats.Fortras100.ENTL512.Models.O20;
import RedmayneEDI.Formats.Fortras100.Formatting;

import java.util.ArrayList;

public class UEZ
{
    public ArrayList<O00> O00 = new ArrayList<O00>();

    public ArrayList<O10> O10 = new ArrayList<O10>();

    public ArrayList<O20> O20 = new ArrayList<O20>();

    public UEZ()
    {
        O00 = new ArrayList<O00>();
        O10 = new ArrayList<O10>();
        O20 = new ArrayList<O20>();
    }

    @Override
    public String toString()
    {
        return Formatting.RecordSet(O00, 2) +
                Formatting.RecordSet(O10, 999) +
                Formatting.RecordSet(O20, 999);
    }
}