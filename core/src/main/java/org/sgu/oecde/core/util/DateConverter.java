package org.sgu.oecde.core.util;

import java.util.Date;

import java.text.SimpleDateFormat;

/**
 * составляет дату на основе lessonDate. если приходит String, то происходит его форматирование.
 * Если дата, то она остаётся без изменений. если ни то, ни другое, то налл
 * @author shihovmy
 */
public class DateConverter {

    private DateConverter() {
        throw new AssertionError();
    }
    
    public static String convert(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return  sdf.format(date).toString();
    }

    public static String convert(Long date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return  sdf.format(new Date(date)).toString();
    }
}