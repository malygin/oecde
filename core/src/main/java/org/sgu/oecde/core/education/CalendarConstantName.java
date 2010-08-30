package org.sgu.oecde.core.education;

import java.io.Serializable;

/**
 * сожержит имена каледнарных констант, содержащих текущие семестр, год, а так же включён ли режим переэкзаменовки
 * @author ShihovMY
 */
public enum CalendarConstantName implements Serializable,ICalendarConstantName{

    /**
     * дата начала переэкзаменовки
     */
    reExameBeginDate,
    /**
     * дата окончания переэкзаменовки
     */
    reExameEndDate,
    /**
     * семестр
     */
    semester,
    /**
     * год
     */
    year;
    private static final long serialVersionUID = 58L;

    @Override
    public Object getDefault() {
        switch(this){
            case reExameBeginDate:
                return "2009.01.01 00:00:00";
            case reExameEndDate:
                return "2009.01.01 00:00:00";
            case semester:
                return 0;
            case year:
                return 2009;
            default:
                return new AssertionError();
        }
    }
}
