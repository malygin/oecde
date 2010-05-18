package org.sgu.oecde.tests.util;

import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.tests.TestCalendarConstants;

/**
 *
 * @author ShihovMY
 */
public class TestPassingDatesGetter extends SemesterGetter{

    protected TestPassingDatesGetter() {
    }

    public String getRegularTestDate(){
        return this.<TestCalendarConstants>getConstants().getRegularTestDate();
    }

    public String getConcludingTestDate(){
        return this.<TestCalendarConstants>getConstants().getConcludingTestDate();
    }
}
