package org.sgu.oecde.tests;

import org.sgu.oecde.core.education.CalendarConstants;

/**
 *
 * @author ShihovMY
 */
public class TestCalendarConstants extends CalendarConstants{
    private String regularTestDate;
    private String concludingTestDate;

    protected TestCalendarConstants() {
    }

    public String getConcludingTestDate() {
        return concludingTestDate;
    }

    public void setConcludingTestDate(String concludingTestDate) {
        this.concludingTestDate = concludingTestDate;
    }

    public String getRegularTestDate() {
        return regularTestDate;
    }

    public void setRegularTestDate(String regularTestDate) {
        this.regularTestDate = regularTestDate;
    }    
}
