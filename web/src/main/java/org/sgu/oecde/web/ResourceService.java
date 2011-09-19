package org.sgu.oecde.web;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.core.education.CalendarConstantName;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestCalendarConstants;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author ShihovMY
 */
@Service
public class ResourceService implements Serializable{
    @Resource
    protected IResourceDao<AbstractResource> resourceDao;

    @Resource
    protected DeCurriculumBuilder curriculumBuilder;

    @Resource
    protected ControlWorkService controlWorkService;

    @Resource
    protected ICurriculumDao<DeCurriculum> curriculumDao;

    @Resource
    private StringConstantsGetter testsDatesGetter;

    @Resource
    private SemesterGetter semesterGetter;

    private String reExameBeginDate;
    private String reExameEndDate;
    private String concludingTestBeginDate;
    private String concludingTestEndDate;
    private String simpleSpecialitiesTestsClosing;
    private String regularTestEndDate;
    private String regularTestBeginDate;
    private String winterConcludingTestReExameAttemtpsCount;
    private String summerRegularTestReExameAttemtpsCount;
    private String summerConcludingTestReExameAttemtpsCount;
    private String winterRegularTestReExameAttemtpsCount;

    private static final long serialVersionUID = 164L;

    public String getRegularAttemtpsCount(int semester) {
        return semester==SemesterGetter.SUMMER_SEMESTER?summerRegularTestReExameAttemtpsCount:winterRegularTestReExameAttemtpsCount;
    }

    public String getConcludingAttemtpsCount(int semester) {
        return semester==SemesterGetter.SUMMER_SEMESTER?summerConcludingTestReExameAttemtpsCount:winterConcludingTestReExameAttemtpsCount;
    }

    public <T extends AbstractResource>T getResource(DeCurriculum c,AbstractResource r,Class clazz){
        if(c==null||r==null||r.getId()==null)
            return null;
        List<DeCurriculum> l = ListUtil.oneItemList(c);
        List<T> rs = resourceDao.<DeCurriculum,T>getResourceByCurriculums(l,r.getId(), clazz).get(c);
        if(CollectionUtils.isEmpty(rs))
            return null;
        return rs.get(0);
    }

    public DeCurriculum getDisciplineForStudent(Student student,Long id,List<DeCurriculum>curriculums){
        if(id==null||id==0)
            return null;
        if(!CollectionUtils.isEmpty(curriculums))
            for(DeCurriculum c:curriculums){
                if(c!=null&&c.getId().equals(id)){
                    return c;
                }
            }
        DeCurriculum curriculum = curriculumBuilder.getInstance(student,id);
        curriculum.setSelected(null);
        List<DeCurriculum> l = curriculumDao.getByExample(curriculum);
        if(CollectionUtils.isEmpty(l))
            return null;
        return l.get(0);
    }

    public boolean isConcludingTestAvailable(Student student,DeCurriculum curriculum){
        if(!curriculum.getGotControlWork()||curriculum.getControlWorksPaperOnly())
            return true;
        ControlWork cw = controlWorkService.getStudensControlWorks(student, ListUtil.<DeCurriculum>oneItemList(curriculum)).get(curriculum);
        return cw!=null&&ControlWorkProgress.passed.equals(cw.getProgress());
    }

    public Object[] getTestForStudent(AdditionalSelfDependentWork w, Student student, boolean concludingAvailable){
        if(w==null||student==null)
            return null;
        boolean available = false;
        TestEntity e = w.<TestEntity>getWork();
        if(e==null)
            return null;

        Object[]data = new Object[5];

        String testBeginDate = reExameBeginDate;
        String testEndDate = reExameEndDate;
        String currentDate = DateConverter.currentDate();

        if(TestType.concluding.equals(e.getType())&&!concludingAvailable){
            available = false;
            data[2] = "Контрольная работа не зачтена";
        }else{
            if((currentDate.compareTo(testBeginDate)>=0)&&(currentDate.compareTo(testEndDate)<0)){
                if(w.getReExameAttemptsUsedNumber()>=Integer.parseInt(
                        (TestType.concluding.equals(e.getType())
                        ?getConcludingAttemtpsCount(semesterGetter.getCurrentSemester())
                        :getRegularAttemtpsCount(semesterGetter.getCurrentSemester())))){
                    data[4] = "Попытки переэкзаменовки исчерпаны";
                }else
                    available = true;
                   
            }else{
                testBeginDate = e.getOpenDate();
                testEndDate = e.getCloseDate();
                if(!StringUtils.hasText(testBeginDate)||!StringUtils.hasText(testEndDate)){
                    switch(e.getType()){
                        case concluding:
                            testBeginDate = concludingTestBeginDate;
                            testEndDate = concludingTestEndDate;
                            break;                            
                        case trial:
                             testBeginDate = regularTestBeginDate;
                             testEndDate = regularTestEndDate;
                             break;
                        case regular:
                            if(student.getGroup().getYear()==1
                                    ||(student.getGroup().getYear()==2
                                    &&(!student.<Group>getGroup().getSpeciality().getName().contains("ускор")
                                       ||!student.<Group>getGroup().getSpeciality().getName().contains("сокр"))))
                                testEndDate = simpleSpecialitiesTestsClosing;
                            else
                                testEndDate = regularTestEndDate;
                            testBeginDate = regularTestBeginDate;
                            break;
                    }
                    e.setOpenDate(testBeginDate);
                    e.setCloseDate(testEndDate);
                }
                if(StringUtils.hasText(testBeginDate)&&StringUtils.hasText(testEndDate)
                        &&(currentDate.compareTo(testBeginDate)>=0)&&(currentDate.compareTo(testEndDate)<=0)
                        &&w.getCurriculum().getSemester()==semesterGetter.getSemesterByStudentYear(student, semesterGetter.getCurrentSemester()))
                    available = true;
                else{
                    data[2] = "Тест не доступен";
                }
            
            if(w.getTrialAttemptsUsedNumber()>=e.getTrialNumber())
                data[3] = "Пробные попытки исчерпаны";
            if(w.getEstimateAttemptsUsedNumber()>=e.getEstimateAttemptsNumber()){
                data[2] = "Попытки исчерпаны";
                available = data[4]==null?false:available;
            }
            }
            if(!student.getFullAccess()){
                data[4] = "Тесты не доступны";
                available = false;
            }
        }
        data[1]=w;
        data[0]=available;
        return data;
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
        summerConcludingTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.summerConcludingTestReExameAttemtpsCount).toString();
        summerRegularTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.summerRegularTestReExameAttemtpsCount).toString();
        winterConcludingTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.winterConcludingTestReExameAttemtpsCount).toString();
        winterRegularTestReExameAttemtpsCount = testsDatesGetter.getConstant(TestCalendarConstants.winterRegularTestReExameAttemtpsCount).toString();
    }

    public String getConcludingTestBeginDate() {
        return concludingTestBeginDate;
    }

    public String getConcludingTestEndDate() {
        return concludingTestEndDate;
    }

    public String getReExameBeginDate() {
        return reExameBeginDate;
    }

    public String getSimpleSpecialitiesTestsClosing() {
        return simpleSpecialitiesTestsClosing;
    }

    public String getReExameEndDate() {
        return reExameEndDate;
    }

    public String getRegularTestBeginDate() {
        return regularTestBeginDate;
    }

    public String getRegularTestEndDate() {
        return regularTestEndDate;
    }
}
