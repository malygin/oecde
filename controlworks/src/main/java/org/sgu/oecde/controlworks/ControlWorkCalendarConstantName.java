package org.sgu.oecde.controlworks;

import org.sgu.oecde.core.education.ICalendarConstantName;

/**
 * календарные константы кр
 * @author ShihovMY
 */
public enum ControlWorkCalendarConstantName implements ICalendarConstantName{
    /**
     * дата начала отправки кр
     */
    controlWorksBeginDate,
    /**
     * завершение отправки кр
     */
    controlWorksEndDate;
    private static final long serialVersionUID = 94L;

    @Override
    public Object getDefault() {
        switch(this){
            case controlWorksEndDate:
                return "2009.01.01 00:00:00";
            case controlWorksBeginDate:
                return "2009.01.01 00:00:00";
            default:
                return new AssertionError();
        }
    }
}
