package com.icerrate.matches.utils;

import com.icerrate.matches.data.ResponseCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author icerrate
 */
public class DateUtils {

    private static final String UTC_TIME_ZONE = "UTC";

    public static final String FORMAT_ORIGINAL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String FORMAT_DAY_NUMBER = "dd";

    public static final String FORMAT_DAY_NAME = "EEE";

    public static final String FORMAT_MONTH_YEAR = "MMMM yyyy";

    public static final String FORMAT_DATE_TIME = "MMM dd, yyyy' at 'HH:mm";

    /**
     * Gets the <code>String</code> formatted date from another String date.
     *
     * @param date the <code>String</code> date that is going to be formatted
     * to another String date
     * @param inputFormat the pattern describing the original date and time format
     * @param outputFormat the pattern describing the new date and time format
     *
     * @return the specified formatted <code>String</code> date. Null <code>String</code>
     * would be returned if input date is null or the ParseException is catch.
     */
    public static String formatStringDate(String date, String inputFormat, String outputFormat){
        if (date != null && !date.isEmpty()) {
            try {
                SimpleDateFormat inFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
                inFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE));
                Date newDate = inFormat.parse(date);

                SimpleDateFormat outFormat = new SimpleDateFormat(outputFormat, Locale.getDefault());
                outFormat.setTimeZone(TimeZone.getDefault());
                return outFormat.format(newDate);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
