package org.sgu.oecde.core.education;

import java.io.Serializable;

/**
 * сожержит имена каледнарных констант, содержащих текущие семестр, год, а так же включён ли режим переэкзаменовки
 * @author ShihovMY
 */
public enum CalendarConstantName implements Serializable,ICalendarConstantName{
    /**
     * семестр
     */
    semester,
    /**
     * год
     */
    year,
    /**
     * режим переэкзаменовки ли
     */
    reExame;
    private static final long serialVersionUID = 58L;
}
