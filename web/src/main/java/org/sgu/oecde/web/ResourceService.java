package org.sgu.oecde.web;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.sgu.oecde.core.education.CalendarConstantName;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestCalendarConstants;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author ShihovMY
 */
@Service
public class ResourceService {
    @Resource
    protected IResourceDao<AbstractResource> resourceDao;

    @Resource
    protected DeCurriculumBuilder curriculumBuilder;

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

    public <T extends AbstractResource>T getResource(DeCurriculum c,AbstractResource r,Class clazz){
        if(c==null||r==null||r.getId()==null)
            return null;
        List<DeCurriculum> l = new LinkedList<DeCurriculum>();
        l.add(c);
        List<AbstractResource> rs = resourceDao.getResourceByCurriculums(l,r.getId(), clazz);
        return (T) rs.get(0);
    }

    public DeCurriculum getDisciplineForStudent(Student student,Long id){
        if(id==null||id==0)
            return null;
        DeCurriculum curriculum = curriculumBuilder.getInstance(student,id);
        List<DeCurriculum> l = curriculumDao.getByExample(curriculum);
        if(CollectionUtils.isEmpty(l))
            return null;
        return l.get(0);
    }

    public Object[] getTestForStudent(AdditionalSelfDependentWork w, Student student){
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
        if((currentDate.compareTo(testBeginDate)>=0)&&(currentDate.compareTo(testEndDate)<0)){
            if(w.getReExameAttemptsUsedNumber()>=1){
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
            }
            if(StringUtils.hasText(testBeginDate)&&StringUtils.hasText(testEndDate)
                    &&(currentDate.compareTo(testBeginDate)>=0)&&(currentDate.compareTo(testEndDate)<=0))
                available = true;
            else{
                data[2] = "тест не открыт";
            }
        }
        if(w.getTrialAttemptsUsedNumber()>=e.getTrialNumber())
            data[3] = "Пробные попытки исчерпаны";
        if(w.getEstimateAttemptsUsedNumber()>=e.getEstimateAttemptsNumber()){
            data[2] = "Попытки исчерпаны";
            available = data[4]==null?false:available;
        }
        data[1]=w;
        data[0]=available;
        return data;
    }

    @PostConstruct
    public void postConstract(){
        reExameBeginDate = semesterGetter.getConstant(CalendarConstantName.reExameBeginDate);
        reExameEndDate = semesterGetter.getConstant(CalendarConstantName.reExameEndDate);
        concludingTestBeginDate = testsDatesGetter.getConstant(TestCalendarConstants.concludingTestBeginDate);
        concludingTestEndDate = testsDatesGetter.getConstant(TestCalendarConstants.concludingTestEndDate);
        simpleSpecialitiesTestsClosing = testsDatesGetter.getConstant(TestCalendarConstants.simpleSpecialitiesTestsClosing);
        regularTestBeginDate = testsDatesGetter.getConstant(TestCalendarConstants.regularTestBeginDate);
        regularTestEndDate = testsDatesGetter.getConstant(TestCalendarConstants.regularTestEndDate);
    }
}