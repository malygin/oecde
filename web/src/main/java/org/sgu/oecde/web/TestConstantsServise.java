package org.sgu.oecde.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.tests.TestCalendarConstants;
import org.sgu.oecde.tests.TestType;
import org.springframework.stereotype.Service;

/**
 *
 * @author ShihovMY
 */
@Service
public class TestConstantsServise implements Serializable{

    
    private String winterConcludingTestReExameAttemtpsCount;
    private String summerRegularTestReExameAttemtpsCount;
    private String summerConcludingTestReExameAttemtpsCount;
    private String winterRegularTestReExameAttemtpsCount;

    private static final long serialVersionUID = 166L;
    
    @Resource
    private StringConstantsGetter testsDatesGetter;

    @PostConstruct
    public void postConstract(){
        summerConcludingTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.summerConcludingTestReExameAttemtpsCount).toString();
        summerRegularTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.summerRegularTestReExameAttemtpsCount).toString();
        winterConcludingTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.winterConcludingTestReExameAttemtpsCount).toString();
        winterRegularTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.winterRegularTestReExameAttemtpsCount).toString();
    }

    public String getRegularAttemtpsCount(int semester) {
        return semester==1?summerRegularTestReExameAttemtpsCount:winterRegularTestReExameAttemtpsCount;
    }

    public String getConcludingAttemtpsCount(int semester) {
        return semester==1?summerConcludingTestReExameAttemtpsCount:winterConcludingTestReExameAttemtpsCount;
    }

    public TestType getConcludingType(){
        return TestType.concluding;
    }
}
