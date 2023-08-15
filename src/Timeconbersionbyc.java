import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
public class Timeconbersionbyc {
    public static String getUniqueTimeStamp() {
        Calendar c = Calendar.getInstance();
        long timeStamp = c.getTimeInMillis();
        return String.valueOf(timeStamp);
    }

    /**
     * This method gets the Date instance of the Device timestamp
     * For Example: "Tue Sep 25 04:28:15 GMT+05:30 2012" is converted to "25 Sep 2012 10:58:15 GMT" and the output will be "25 Sep 2012"
     * @param date Locale time stamp
     * @return outputText i.e. GMT Date
     * */
    public static String parseDate(Date date) {
        String gmtTime = getGmtTimeStamp(date);
        SimpleDateFormat inputformat = new SimpleDateFormat(GMT_TIME_FORMAT);
        DateFormat outputFormat = new SimpleDateFormat(DATE_FORMAT);
        Date parsed = new Date();
        try {
            parsed = inputformat.parse(gmtTime);
        } catch (ParseException e) {
            System.out.println("Error at parsing");
        }
        String outputText = outputFormat.format(parsed);
        return outputText;
    }

    /**
     * This method gets the current time and converts the same to GMT.
     * For Example: "Tue Sep 25 04:28:15 GMT+05:30 2012" is converted to "25 Sep 2012 10:58:15 GMT"
     * @return String i.e. GMT Time stamp
     * */
    public static String getGmtTimeStamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(LOCAL_TIME_FORMAT);
        sdf.setTimeZone(new SimpleTimeZone(0, "IST"));
        sdf.applyPattern(GMT_TIME_FORMAT);
        return sdf.format(date);
    }

    /**
     * This method gets the current time and converts the same to GMT.
     * For Example: "Tue Sep 25 04:28:15 GMT+05:30 2002" is converted to "25 Sep 2002 10:58:15 GMT"
     * @return String i.e. GMT Time stamp
     * */
    public static String getGmtTimeStamp() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(LOCAL_TIME_FORMAT);
        sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
        sdf.applyPattern(GMT_TIME_FORMAT);
        return sdf.format(c.getTime());
    }

    /**
     *  Allows to set the GMT Time stamp to device time format.
     * For Example: "25 Sep 2002 10:58:15 GMT" is converted to "Tue Sep 25 04:28:15 GMT+05:30 2002"
     *
     * @param timeStamp  the locale time stamp in String
     * */
    public static Date getLocaleTimeStamp(String timeStamp) {
        Date date=null;
        SimpleDateFormat sdf = new SimpleDateFormat(GMT_TIME_FORMAT);
        try {
            date = sdf.parse(timeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *  TimeStamp is received in this format from Calendar
     */
    final static String LOCAL_TIME_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";

    /**
     *  TimeStamp is stored in this format in DB
     */
    final static String GMT_TIME_FORMAT = "dd MMM yyyy HH:mm:ss Z";

    /**
     *  Date is stored in this format in DB
     */
    final static String DATE_FORMAT = "dd MMM yyyy";

}

