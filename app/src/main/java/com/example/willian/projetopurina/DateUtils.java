package com.example.willian.projetopurina;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private Date date;
    private Calendar calendar;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static DateUtils from(Date date) {
        return new DateUtils(date);
    }

    private DateUtils(Date date) {
        this.date = date;
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(date);
    }

//    public DateUtils addDays(int ammount) {
//        calendar.add(Calendar.DAY_OF_MONTH, ammount);
//        return new DateUtils(calendar.getTime());
//    }

    public DateUtils addMonths(int ammount) {
        calendar.add(Calendar.MONTH, ammount);
        return new DateUtils(calendar.getTime());
    }

//    public DateUtils addYears(int ammount) {
//        calendar.add(Calendar.YEAR, ammount);
//        return new DateUtils(calendar.getTime());
//    }

    public String format() {
        return formatter.format(date);
    }

    public Date getDate() {
        return date;
    }
}
