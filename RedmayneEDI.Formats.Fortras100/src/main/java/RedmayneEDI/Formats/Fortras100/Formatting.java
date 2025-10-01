package RedmayneEDI.Formats.Fortras100;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// <summary>
/// A Global utilities class available to all Fortras models.
/// </summary>
public class Formatting
{
    /// <summary>
    /// The Carriage Return, Line Feed character to append to the end of lines.
    /// This is a customisable parameter and is usually Unix, Mac or Windows EOL characters.
    /// </summary>
    public static String CRLF = "\n";

    /// <summary>
    /// When enabled, the standard limits on the maximum repetitions of records is lifted.
    /// </summary>
    public static boolean DisableRecordLimits = false;

    /// <summary>
    /// Should lines be trimmed of empty space where possible? Defaults to True. When disabled lines are fully specified.
    /// </summary>
    public static boolean TrimLines = true;

    /// <summary>
    /// Safely returns a substringed version of the given text up to the maximum length if specified, or returns an empty string for nullable values.
    /// </summary>
    /// <param name="text"></param>
    /// <param name="start">The starting position for the substring</param>
    /// <returns></returns>
    public static String SafeSubstring(String text, int start)
    {
        return SafeSubstring(text, start, 0, true);
    }

    /// <summary>
    /// Safely returns a substringed version of the given text up to the maximum length if specified, or returns an empty string for nullable values.
    /// </summary>
    /// <param name="text"></param>
    /// <param name="start">The starting position for the substring</param>
    /// <param name="length">The maximum length of the string to extract</param>
    /// <returns></returns>
    public static String SafeSubstring(String text, int start, int length)
    {
        return SafeSubstring(text, start, length, true);
    }

    /// <summary>
    /// Safely returns a substringed version of the given text up to the maximum length if specified, or returns an empty string for nullable values.
    /// </summary>
    /// <param name="text"></param>
    /// <param name="start">The starting position for the substring</param>
    /// <param name="length">The maximum length of the string to extract</param>
    /// <param name="trim">When true, the return value is trimmed</param>
    /// <returns></returns>
    public static String SafeSubstring(String text, int start, int length, boolean trim)
    {
        if (text.length() <= (start + length))
        {
            if (text.length() > start) {  text = text.substring(start); }
            else { return ""; }
        }
        else
        {
            text = text.substring(start, (start + length)).trim();
        }

        if (trim)
        {
            text = text.trim();
        }

        return text;
    }

    /// <summary>
    /// Safely returns a truncated version of the given text up to the maximum length, or returns an empty string for nullable values.
    /// </summary>
    /// <param name="text"></param>
    /// <param name="maximumLength"></param>
    /// <returns></returns>
    public static String SafeTruncate(String text, int maximumLength)
    {
        return SafeTruncate(text, maximumLength, ' ', false);
    }

    /// <summary>
    /// Safely returns a truncated version of the given text up to the maximum length, or returns an empty string for nullable values.
    /// </summary>
    /// <param name="text"></param>
    /// <param name="maximumLength"></param>
    /// <param name="padWith">Character used to pad the field to maximum length</param>
    /// <returns></returns>
    public static String SafeTruncate(String text, int maximumLength, char padWith)
    {
        return SafeTruncate(text, maximumLength, padWith, false);
    }

    /// <summary>
    /// Safely returns a truncated version of the given text up to the maximum length, or returns an empty string for nullable values.
    /// </summary>
    /// <param name="text"></param>
    /// <param name="maximumLength"></param>
    /// <param name="padWith">Character used to pad the field to maximum length</param>
    /// <param name="padLeft">When enabled, the fields is padded on the left side of the value (often for trailing zeroes)</param>
    /// <returns></returns>
    public static String SafeTruncate(String text, int maximumLength, char padWith, boolean padLeft)
    {
        String newText = text;
        if (newText != null && !newText.equals(""))
        {
            if (text.length() > maximumLength)
            {
                newText = text.substring(0, maximumLength);
            }
        }
        else { newText = ""; }
        if (padLeft) { newText = PadLeft(newText, maximumLength, padWith); }
        else { newText = PadRight(newText, maximumLength, padWith); }
        return newText;
    }

    /// <summary>
    /// Generates a ToString()'d output of a Fortras list, limited by the maxRecords specified, or the full set if maximum limits are disabled.
    /// </summary>
    /// <typeparam name="T">The Data Type of the List.</typeparam>
    /// <param name="records">The contents of the List.</param>
    /// <param name="maxRecords">Maximum repetitions (can be disabled via DisableRecordLimits)</param>
    /// <returns></returns>
     public static <T> String RecordSet(ArrayList<T> records, int maxRecords) {
        StringBuilder output = new StringBuilder();

        if (records == null) {
            return output.toString();
        }

        if (DisableRecordLimits) {
            for (T record : records) {
                output.append(record);
            }
        } else {
            for (int i = 0; i < maxRecords && i < records.size(); i++) {
                output.append(records.get(i));
            }
        }

        return output.toString();
    }

    /// <summary>
    /// Scans for and attempts to identify the EOL character within the given string based upon the highest number of usages. Returns an empty string when unidentified, or the string representation of the EOL character (eg: "\r\n")
    /// </summary>
    /// <param name="text">The text to identify the EOL character in.</param>
    /// <returns>Either the EOL character (\r,\n, or \r\n), or empty on failure to find the character.</returns>
    public static String GetEOLFormat(String text)
    {
        int crlf = 0;
        int cr = 0;
        int lf = 0;
        if (text.contains("\r\n"))
        {
            crlf = (text.split("\r\n").length - 1);
            text = text.replaceAll("\r\n", "");
        }
        if (text.contains("\r"))
        {
            cr = (text.split("\r").length - 1);
            text = text.replaceAll("\r", "");
        }
        if (text.contains("\n"))
        {
            lf = (text.split("\n").length - 1);
        }
        if (crlf <= 0 && cr <= 0 && lf <= 0) { return ""; }
        else if (crlf == cr && crlf == lf) { return "\r\n"; }
        else if (crlf > cr && crlf > lf) { return "\r\n"; }
        else if (cr > crlf && cr > lf) { return "\r"; }
        else if (lf > crlf && lf > cr) { return "\n"; }
        else { return ""; }
    }

    /// <summary>
    /// Returns a String date and String time as a single ZonedDateTime object, assumes date and time is in UTC.
    /// </summary>
    /// <param name="dateStr">The Date in a ddMMyyyy pattern.</param>
    /// <param name="timeStr">The Time in a HHmm pattern.</param>
    /// <returns></returns>
    public static ZonedDateTime GetDateTime(String dateStr, String timeStr)
    {
        return GetDateTime(dateStr, timeStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    /// <summary>
    /// Returns a string date and string time as a single DateTime object
    /// </summary>
    /// <param name="dateStr">The Date in a ddMMyyyy pattern.</param>
    /// <param name="timeStr">The Time in a HHmm pattern.</param>
    /// <param name="dateTimeKind">Optional indicator if the date is UTC or Local timezone.</param>
    /// <returns></returns>
    public static ZonedDateTime GetDateTime(String dateStr, String timeStr, DateTimeFormatter dateTimeKind)
    {
        int year = 0; try { year = Integer.parseInt(dateStr.substring(4, (4+4))); } catch (Exception ex) {  }
        int month = 0; try { month = Integer.parseInt(dateStr.substring(2, (2+2))); } catch (Exception ex) {  }
        int day = 0; try { day = Integer.parseInt(dateStr.substring(0, 2)); } catch (Exception ex) {  }
        int hour = 0; try { hour = Integer.parseInt(timeStr.substring(0, 2)); } catch (Exception ex) {  }
        int min = 0; try { min = Integer.parseInt(timeStr.substring(2, (2+2))); } catch (Exception ex) {  }

        ZonedDateTime zonedDateTime = ZonedDateTime.parse(year + "-" + month + "-" + day + "T" + hour + ":" + min + ":00", dateTimeKind);
        return zonedDateTime;
    }

    /// <summary>
    /// Returns a String date as a ZonedDateTime object
    /// </summary>
    /// <param name="dateStr">The Date in a ddMMyyyy pattern.</param>
    /// <returns></returns>
    public static ZonedDateTime GetDate(String dateStr)
    {
        return GetDate(dateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    /// <summary>
    /// Returns a String date as a ZonedDateTime object
    /// </summary>
    /// <param name="dateStr">The Date in a ddMMyyyy pattern.</param>
    /// <param name="dateTimeKind">Optional DateTimeFormatter indicator for the local time zone format.</param>
    /// <returns></returns>
    public static ZonedDateTime GetDate(String dateStr, DateTimeFormatter dateTimeKind)
    {
        int year = Integer.parseInt(dateStr.substring(4, (4+4)));
        int month = Integer.parseInt(dateStr.substring(2, (2+2)));
        int day = Integer.parseInt(dateStr.substring(0, 2));

        ZonedDateTime zonedDateTime = ZonedDateTime.parse(year + "-" + month + "-" + day + "T00:00:00", dateTimeKind);
        return zonedDateTime;
    }

    /// <summary>
    /// Returns a String padded to the left to the given length, using a space ' ' as the padding.
    /// </summary>
    /// <param name="text">The String to pad</param>
    /// <param name="maxLength">The maximum length of the String.</param>
    /// <returns>The padded String</returns>
    public static String PadLeft(String text, int maxLength)
    {
        return PadLeft(text, maxLength, ' ');
    }

    /// <summary>
    /// Returns a String padded to the left to the given length and using the given character.
    /// </summary>
    /// <param name="text">The String to pad</param>
    /// <param name="maxLength">The maximum length of the String.</param>
    /// <param name="padWith">The character to pad the string with.</param>
    /// <returns>The padded String</returns>
    public static String PadLeft(String text, int maxLength, char padWith)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(padWith).repeat(Math.max(0, maxLength)));
        return sb.substring(text.length()) + text;
    }

    /// <summary>
    /// Returns a String padded to the right to the given length and using the given character.
    /// </summary>
    /// <param name="text">The String to pad</param>
    /// <param name="maxLength">The maximum length of the String.</param>
    /// <returns>The padded String</returns>
    public static String PadRight(String text, int maxLength)
    {
        return PadRight(text, maxLength, ' ');
    }

    /// <summary>
    /// Returns a String padded to the right to the given length and using the given character.
    /// </summary>
    /// <param name="text">The String to pad</param>
    /// <param name="maxLength">The maximum length of the String.</param>
    /// <param name="padWith">The character to pad the string with.</param>
    /// <returns>The padded String</returns>
    public static String PadRight(String text, int maxLength, char padWith)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(padWith).repeat(Math.max(0, maxLength)));
        return text + sb.substring(text.length());
    }


    public static String RegexFindFirst(String sourceString, String pattern) {
        Pattern re = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher m = re.matcher(sourceString);
        if(m.find()){
            return m.group(0);
        }
        return "";
    }

    /// <summary>
    /// Simple Java wrapper to return the current datetime in the given dateFormat String.
    /// </summary>
    /// <param name="dateFormat">The String for dateTime formatting.</param>
    /// <returns>The current date/time in the provided dateFormat String</returns>
    public static String GetCurrentDateTime(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    /// <summary>
    /// Simple Java wrapper to return the current datetime in the given dateFormat String.
    /// </summary>
    /// <param name="dateFormat">The String for dateTime formatting.</param>
    /// <param name="timeElement">A Calendar.ERA enumeration for a date period.</param>
    /// <param name="timeAlteration">The degree with which to modify the date.</param>
    /// <returns>The current date/time in the provided dateFormat String</returns>
    public static String GetDateTimeString(String dateFormat, int timeElement, int timeAlteration)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(timeElement, timeAlteration);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }
}
