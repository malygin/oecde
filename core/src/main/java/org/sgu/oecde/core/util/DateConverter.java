package org.sgu.oecde.core.util;

import java.util.Date;

import java.text.SimpleDateFormat;

/**
 * методы по приведению даты к виду {@code yyyy.MM.dd HH:mm:ss}
 * @author shihovmy
 */
public class DateConverter {

    private DateConverter() {
        throw new AssertionError();
    }

    /**
     *
     * @param date
     * @return дата вида {@code yyyy.MM.dd HH:mm:ss}
     */
    public static String convert(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return  sdf.format(date).toString();
    }

    /**
     *
     * @param date
     * @return дата вида {@code yyyy.MM.dd HH:mm:ss}
     */
    public static String convert(Long date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return  sdf.format(new Date(date)).toString();
    }

    /**
     *
     * @return текущая дата вида!!! {@code yyyy.MM.dd HH:mm:ss}
     */
    public static String currentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return  sdf.format(new Date(System.currentTimeMillis())).toString();
    }
}