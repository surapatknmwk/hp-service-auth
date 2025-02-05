package com.alibou.security.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Log4j2
@Component
public class DateUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static final SimpleDateFormat DATE_FORMAT_WITH_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    private static String[] monthThai = new String[]{
            "มกราคม", "กุมภาพันธ์", "มีนาคม",
            "เมษายม", "พฤษภาคม", "มิถุนายน",
            "กรกฎาคม", "สิงหาคม", "กันยายน",
            "ตุลาคม", "พฤศจิกายน", "ธันวาคม"
    };


    public static Timestamp getCurrentDate() {
        Timestamp today = null;
        try {
            Date nowDate = Calendar.getInstance().getTime();
            today = new Timestamp(nowDate.getTime());
        } catch (Exception e) {
            log.error("error msg : {} ", e);
            throw new RuntimeException(e);
        }
        return today;
    }

    public static Timestamp getTimeStamp(String stringDate) {
        Timestamp today = null;
        try {
            today = getDateWithRemoveTime(DATE_FORMAT.parse(stringDate));
        } catch (Exception e) {
            log.error("error msg : {} ", e);
            throw new RuntimeException(e);
        }

        return today;
    }

    public static Timestamp getTimeStampGetMaxTime(String stringDate) {

//        log.info()
        Timestamp today = null;
        try {
            today = getTimeMax(DATE_FORMAT.parse(stringDate));
        } catch (Exception e) {
            log.error("error msg : {} ", e);
            throw new RuntimeException(e);
        }

        return today;
    }


    public static Locale getSystemLocale() {
//        log.info("getSystemLocale  Locale.US");
        return Locale.US;
    }

    public static Timestamp getDateWithRemoveTime(Date date) {
        Timestamp maxTimeDate = null;
        if (AppUtil.isNotNull(date)) {
            SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd", DateUtil.getSystemLocale());
            maxTimeDate = Timestamp.valueOf(newformat.format(date) + " " + "00:00:00.000");
        }
        return maxTimeDate;
    }

    public static Timestamp getTimeMax(Date date) {
        log.info("getDateWithRemoveTime : {} ", date);
        Timestamp maxTimeDate = null;
        try {
            SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd", DateUtil.getSystemLocale());
            maxTimeDate = Timestamp.valueOf(newformat.format(date) + " " + "23:59:59.999");
            log.debug("getDateWithRemoveTime return : {}", maxTimeDate);
        } catch (Exception e) {
            log.error("error msg : {} ", e);
            throw new RuntimeException(e);
        }

        return maxTimeDate;
    }


    public static Timestamp getDateWithMaxTime(String date) {
        log.info("getDateWithMaxTime : {} ", date);
        Timestamp minTimeDate = null;
        try {
            String newFormateDate = convertStringDate(date);
            minTimeDate = Timestamp.valueOf(newFormateDate + " " + "23:59:59.999");

        } catch (Exception e) {
            log.error("error msg : {} ", e);
            throw new RuntimeException(e);
        }

        return minTimeDate;
    }

    private static String convertStringDate(String dateString1) {
        String newDate = "";
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy", DateUtil.getSystemLocale()).parse(dateString1);
            SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd", DateUtil.getSystemLocale());
            newDate = newformat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }


    public static String convertStringToDate(String dateString1) {
        String newDate = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", DateUtil.getSystemLocale()).parse(dateString1);
            SimpleDateFormat newformat = new SimpleDateFormat("dd-MM-yyyy", DateUtil.getSystemLocale());
            newDate = newformat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;


    }

    public static String convertDateToString(Date dateIn) {
        String newDate = "";
        try {
            newDate = DATE_FORMAT_WITH_TIME.format(dateIn);
        } catch (Exception e) {
            log.error("Error {}", e.getMessage(), e);
        }
        return newDate;
    }

    public static String convertDateToStringTH(Date date) {
        SimpleDateFormat DATE_FORMAT_WITH_TIME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"));
        String newDate = "";
        try {
            newDate = DATE_FORMAT_WITH_TIME.format(date);
        } catch (Exception e) {
            log.error("Error {}", e.getMessage(), e);
        }
        return newDate;
    }

    public static String convertDateToStringTH(Date date, String format) {
        SimpleDateFormat DATE_FORMAT_WITH_TIME = new SimpleDateFormat(format, new Locale("th", "TH"));
        String newDate = "";
        try {
            newDate = DATE_FORMAT_WITH_TIME.format(date);
        } catch (Exception e) {
            log.error("Error {}", e.getMessage(), e);
        }
        return newDate;
    }

    public static String convertDateToString(Date dateIn, String format) {
        String newDate = "";
        try {
            SimpleDateFormat newformat = new SimpleDateFormat(format, DateUtil.getSystemLocale());
            newDate = newformat.format(dateIn);
        } catch (Exception e) {
            log.error("Error {}", e.getMessage(), e);
        }
        return newDate;
    }

    public static Date getLongToDate(Long dateLong) {
        Date date = new Date(dateLong);
        return date;
    }

    public static Date getLongToDate(String longStr) {
        Double timeStamp = Double.parseDouble(longStr);
        Date date = new Date(timeStamp.longValue());
        return date;
    }
}
