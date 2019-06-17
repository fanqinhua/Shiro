package com.base.helper;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static Logger logger = LoggerFactory.getLogger(DateHelper.class);

    public DateHelper() {
    }

    public static String toStringYYYYMMDD(Date date) {
        return toString(date, "yyyyMMdd");
    }

    public static String toStringYYYYMMDDHHmmss(Date date) {
        return toString(date, "yyyyMMddHHmmss");
    }

    public static String toStringHHmmss(Date date) {
        return toString(date, "HHmmss");
    }

    public static String toString(Date date) {
        return date == null ? null : getFormat((String)null).format(date);
    }

    public static String toString(Date date, String format) {
        return date == null ? null : getFormat(format).format(date);
    }

    public static Date toDateyyyyMMddHHmmss(String date) {
        return date == null ? null : toDate(date, "yyyyMMddHHmmss");
    }

    public static String toDate(String date) {
        return date == null ? null : getFormat((String)null).format(date);
    }

    public static Date toDate(String date, String format) {
        if (date == null) {
            return null;
        } else {
            try {
                return getFormat(format).parse(date);
            } catch (ParseException var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static Date toDate000000(Date date) {
        if (date == null) {
            return null;
        } else {
            String t = toString(date, "yyyy-MM-dd") + " 00:00:00";
            return toDate(t, "yyyy-MM-dd HH:mm:ss");
        }
    }

    public static Date toDate235959(Date date, String format) {
        if (date == null) {
            return null;
        } else {
            String t = toString(date, format) + " 23:59:59";
            return toDate(t, format + " HH:mm:ss");
        }
    }

    public static Date toDate235959(String date, String format) {
        return date == null ? null : toDate(date + " 23:59:59", format + " HH:mm:ss");
    }

    public static Date toNextDate000000(Date date, String format) {
        if (date == null) {
            return null;
        } else {
            Date newdate = new Date();
            newdate.setTime(date.getTime() + 86400000L);
            String t = toString(newdate, format);
            return toDate(t, format);
        }
    }

    public static Timestamp getCurSqlTimestamp() {
        return new Timestamp(DateTime.now().getMillis());
    }

    private static SimpleDateFormat getFormat(String format) {
        if (format == null) {
            format = "yyyy-MM-dd";
        }

        return new SimpleDateFormat(format);
    }

    public static boolean isDateFormat(String date, String format) {
        if (date == null) {
            return false;
        } else {
            try {
                getFormat(format).parse(date);
                return true;
            } catch (ParseException var3) {
                logger.error("isDateFormat[date:" + date + ",format=" + format + "]", var3);
                return false;
            }
        }
    }

    public static String dateConvert(String date) {
        if (date == null) {
            return "";
        } else {
            String formatDate = "";
            if (date.length() >= 8) {
                formatDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " ";
            }

            if (date.length() >= 14) {
                formatDate = formatDate + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
            }

            return formatDate;
        }
    }

    public static int diffDay(Date date1, Date date2) {
        return (int)Math.abs(date1.getTime() / 1000L - date2.getTime() / 1000L) / 86400;
    }

    public static Date addDay(Date date, int i) {
        Date d = new Date();
        long dateTime = 1000L;
        d.setTime((date.getTime() / dateTime + (long)(i * 60 * 60 * 24)) * dateTime);
        return d;
    }

    public static Date minusDay(Date date, int i) {
        return addDay(date, 0 - i);
    }

    public static boolean before(String a, String b) {
        Date d1 = toDateyyyyMMddHHmmss(a);
        Date d2 = toDateyyyyMMddHHmmss(b);
        return d1 != null && d2 != null ? d1.before(d2) : false;
    }

    public static void main(String[] args) {
        Date d1 = toDate("20150503", "yyyyMMdd");
        String d2 = toStringYYYYMMDDHHmmss(d1);
        System.out.println("d2:" + d2);
    }
}
