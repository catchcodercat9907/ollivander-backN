package io.ollivander.ollivanderbackend.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateTimeHelper {
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY_OF_YEAR = "dayOfYear";
    public static final String WEEK = "week";

    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String SECOND = "second";
    public static Date addTime(Date targetDate, String typeOfTime, int value) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);

        if (YEAR.equals(typeOfTime)) {
            calendar.add(Calendar.YEAR, value);
        } else if (MONTH.equals(typeOfTime)) {
            calendar.add(Calendar.MONTH, value);
        } else if (DAY_OF_YEAR.equals(typeOfTime)) {
            calendar.add(Calendar.DAY_OF_YEAR, value);
        } else if (WEEK.equals(typeOfTime)) {
            calendar.add(Calendar.WEEK_OF_YEAR, value);
        } else if (HOUR.equals(typeOfTime)) {
            calendar.add(Calendar.HOUR, value);
        } else if (MINUTE.equals(typeOfTime)) {
            calendar.add(Calendar.MINUTE, value);
        } else if (SECOND.equals(typeOfTime)) {
            calendar.add(Calendar.SECOND, value);
        }

        return calendar.getTime();
    }
}
