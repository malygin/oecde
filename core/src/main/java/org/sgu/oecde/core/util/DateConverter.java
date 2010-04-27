/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.util;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * составляет дату на основе lessonDate. если приходит String, то происходит его форматирование.
 * Если дата, то она остаётся без изменений. если ни то, ни другое, то налл
 * @author shihovmy
 */
public class DateConverter {

    private DateConverter() {
        throw new AssertionError();
    }
    
    public static Date convert(Object date){
        Date newDate = null;
        if(date instanceof String){
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            try {
                newDate = sdf.parse(date.toString());
            } catch (ParseException ex) {
                Logger.getLogger(DateConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (date instanceof Date){
            newDate = (Date) date;
        }
        return newDate;
    }
}