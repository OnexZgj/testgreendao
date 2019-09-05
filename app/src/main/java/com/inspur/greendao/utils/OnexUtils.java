package com.inspur.greendao.utils;

public class OnexUtils {


    public static String toText(int dayOfWeek) {
        String dayofWeek="";
        switch (dayOfWeek) {
            case 0:
                dayofWeek="日";
                break;
            case 1:
                dayofWeek="一";
                break;
            case 2:
                dayofWeek="二";
                break;
            case 3:
                dayofWeek="三";
                break;
            case 4:
                dayofWeek="四";
                break;
            case 5:
                dayofWeek="五";
                break;
            case 6:
                dayofWeek="六";
                break;
        }

        return dayofWeek;


    }

    public static String toHourText(int hour) {
        String dayofWeek="";
        switch (hour) {
            case 0:
                dayofWeek="十二";
                break;
            case 1:
                dayofWeek="一";
                break;
            case 2:
                dayofWeek="二";
                break;
            case 3:
                dayofWeek="三";
                break;
            case 4:
                dayofWeek="四";
                break;
            case 5:
                dayofWeek="五";
                break;
            case 6:
                dayofWeek="六";
                break;
            case 7:
                dayofWeek="七";
                break;
            case 8:
                dayofWeek="八";
                break;
            case 9:
                dayofWeek="九";
                break;
            case 10:
                dayofWeek="十";
                break;
            case 11:
                dayofWeek="十一";
                break;
        }


        return dayofWeek;
    }
}
