package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWorkCalendarConstantName;
import org.sgu.oecde.core.education.CalendarConstantName;
import org.sgu.oecde.core.education.ICalendarConstantName;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.tests.TestCalendarConstants;
import org.sgu.oecde.web.ResourceService;
/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="calendarConstantsBean")
@ViewScoped
public class ConstantsFormBean implements Serializable{
    private String reExameBeginDate;
    private String reExameEndDate;
    private String concludingTestBeginDate;
    private String concludingTestEndDate;
    private String simpleSpecialitiesTestsClosing;
    private String regularTestEndDate;
    private String regularTestBeginDate;
    private String controlWorksBeginDate;
    private String controlWorksEndDate;
    private String summerRegularTestReExameAttemtpsCount;
    private String summerConcludingTestReExameAttemtpsCount;
    private String winterRegularTestReExameAttemtpsCount;
    private String winterConcludingTestReExameAttemtpsCount;
    private int year;
    private boolean semester;
    private boolean error = false;
    private boolean saved = false;

    private static final long serialVersionUID = 170L;

    @ManagedProperty(value="#{testsDatesGetter}")
    private StringConstantsGetter testsDatesGetter;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    @ManagedProperty(value="#{semesterGetter}")
    private SemesterGetter semesterGetter;

    @ManagedProperty(value="#{cwDatesGetter}")
    private StringConstantsGetter cwDatesGetter;

    public ConstantsFormBean() {
    }

    public void save() throws NoSuchFieldException, IllegalAccessException {
        for(CalendarConstantName n:CalendarConstantName.values()){
            if(n!=CalendarConstantName.semester&&n!=CalendarConstantName.year)
                applyChanges(semesterGetter,n);
        }
        for(TestCalendarConstants n:TestCalendarConstants.values()){
            applyChanges(testsDatesGetter,n);
        }
        for(ControlWorkCalendarConstantName n:ControlWorkCalendarConstantName.values()){
            applyChanges(cwDatesGetter,n);
        }
        int old = semesterGetter.getCurrentSemester();
        if((semester ? SemesterGetter.WINTER_SEMESTER : SemesterGetter.SUMMER_SEMESTER)!=old){
            boolean update = (old == CalendarConstantName.semester.getDefault());
                semesterGetter.save(CalendarConstantName.semester, semester?1:0,update);
        }
        old = semesterGetter.getCurrentYear();
        if(year!=old){
            boolean update = (old == CalendarConstantName.year.getDefault());
                semesterGetter.save(CalendarConstantName.year, year,!update);
        }
    }

    public String getSummerConcludingTestReExameAttemtpsCount() {
        return summerConcludingTestReExameAttemtpsCount;
    }

    public void setSummerConcludingTestReExameAttemtpsCount(String summerConcludingTestReExameAttemtpsCount) {
        this.summerConcludingTestReExameAttemtpsCount = summerConcludingTestReExameAttemtpsCount;
    }

    public String getSummerRegularTestReExameAttemtpsCount() {
        return summerRegularTestReExameAttemtpsCount;
    }

    public void setSummerRegularTestReExameAttemtpsCount(String summerRegularTestReExameAttemtpsCount) {
        this.summerRegularTestReExameAttemtpsCount = summerRegularTestReExameAttemtpsCount;
    }

    public String getWinterConcludingTestReExameAttemtpsCount() {
        return winterConcludingTestReExameAttemtpsCount;
    }

    public void setWinterConcludingTestReExameAttemtpsCount(String winterConcludingTestReExameAttemtpsCount) {
        this.winterConcludingTestReExameAttemtpsCount = winterConcludingTestReExameAttemtpsCount;
    }

    public String getWinterRegularTestReExameAttemtpsCount() {
        return winterRegularTestReExameAttemtpsCount;
    }

    public void setWinterRegularTestReExameAttemtpsCount(String winterRegularTestReExameAttemtpsCount) {
        this.winterRegularTestReExameAttemtpsCount = winterRegularTestReExameAttemtpsCount;
    }

    public String getConcludingTestBeginDate() {
        return concludingTestBeginDate;
    }

    public void setConcludingTestBeginDate(String concludingTestBeginDate) {
        this.concludingTestBeginDate = concludingTestBeginDate;
    }

    public String getConcludingTestEndDate() {
        return concludingTestEndDate;
    }

    public void setConcludingTestEndDate(String concludingTestEndDate) {
        this.concludingTestEndDate = concludingTestEndDate;
    }

    public String getControlWorksBeginDate() {
        return controlWorksBeginDate;
    }

    public void setControlWorksBeginDate(String controlWorksBeginDate) {
        this.controlWorksBeginDate = controlWorksBeginDate;
    }

    public String getControlWorksEndDate() {
        return controlWorksEndDate;
    }

    public void setControlWorksEndDate(String controlWorksEndDate) {
        this.controlWorksEndDate = controlWorksEndDate;
    }

    public String getReExameBeginDate() {
        return reExameBeginDate;
    }

    public void setReExameBeginDate(String reExameBeginDate) {
        this.reExameBeginDate = reExameBeginDate;
    }

    public String getReExameEndDate() {
        return reExameEndDate;
    }

    public void setReExameEndDate(String reExameEndDate) {
        this.reExameEndDate = reExameEndDate;
    }

    public String getRegularTestBeginDate() {
        return regularTestBeginDate;
    }

    public void setRegularTestBeginDate(String regularTestBeginDate) {
        this.regularTestBeginDate = regularTestBeginDate;
    }

    public String getRegularTestEndDate() {
        return regularTestEndDate;
    }

    public void setRegularTestEndDate(String regularTestEndDate) {
        this.regularTestEndDate = regularTestEndDate;
    }

    public boolean isSemester() {
        return semester;
    }

    public void setSemester(boolean semester) {
        this.semester = semester;
    }

    public String getSimpleSpecialitiesTestsClosing() {
        return simpleSpecialitiesTestsClosing;
    }

    public void setSimpleSpecialitiesTestsClosing(String simpleSpecialitiesTestsClosing) {
        this.simpleSpecialitiesTestsClosing = simpleSpecialitiesTestsClosing;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCwDatesGetter(StringConstantsGetter cwDatesGetter) {
        this.cwDatesGetter = cwDatesGetter;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }

    public void setTestsDatesGetter(StringConstantsGetter testsDatesGetter) {
        this.testsDatesGetter = testsDatesGetter;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    private void applyChanges(StringConstantsGetter getter,ICalendarConstantName n){
        Object tmp = getter.getConstant(n);
        try{
            Object newCnstnt = this.getClass().getDeclaredField(n.name()).get(this);
            Object o = n.getDefault();
            if(!tmp.equals(newCnstnt)&&!o.equals(newCnstnt)){
                boolean update = tmp.equals(n.getDefault());
                getter.save(n, newCnstnt,!update);
            }
            saved = true;
            resourceService.postConstract();
            semesterGetter.fillConstantsMap();
            testsDatesGetter.afterPropertiesSet();
            cwDatesGetter.afterPropertiesSet();
        }catch(Exception e){
            e.fillInStackTrace();
            error = true;
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @PostConstruct
    public void postConstract(){
        reExameBeginDate = semesterGetter.getConstant(CalendarConstantName.reExameBeginDate).toString();
        reExameEndDate = semesterGetter.getConstant(CalendarConstantName.reExameEndDate).toString();
        concludingTestBeginDate = testsDatesGetter.getConstant(TestCalendarConstants.concludingTestBeginDate).toString();
        concludingTestEndDate = testsDatesGetter.getConstant(TestCalendarConstants.concludingTestEndDate).toString();
        simpleSpecialitiesTestsClosing = testsDatesGetter.getConstant(TestCalendarConstants.simpleSpecialitiesTestsClosing).toString();
        regularTestBeginDate = testsDatesGetter.getConstant(TestCalendarConstants.regularTestBeginDate).toString();
        regularTestEndDate = testsDatesGetter.getConstant(TestCalendarConstants.regularTestEndDate).toString();
        reExameBeginDate = semesterGetter.getConstant(CalendarConstantName.reExameBeginDate).toString();
        reExameEndDate = semesterGetter.getConstant(CalendarConstantName.reExameEndDate).toString();
        controlWorksBeginDate = cwDatesGetter.getConstant(ControlWorkCalendarConstantName.controlWorksBeginDate).toString();
        controlWorksEndDate = cwDatesGetter.getConstant(ControlWorkCalendarConstantName.controlWorksEndDate).toString();
        semester = semesterGetter.getCurrentSemester()==1;
        year = semesterGetter.getCurrentYear();
        summerConcludingTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.summerConcludingTestReExameAttemtpsCount).toString();
        summerRegularTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.summerRegularTestReExameAttemtpsCount).toString();
        winterConcludingTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.winterConcludingTestReExameAttemtpsCount).toString();
        winterRegularTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.winterRegularTestReExameAttemtpsCount).toString();
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
