package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.controlworks.ControlWorkCalendarConstantName;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.education.work.PointToEstimate;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestType;
import org.sgu.oecde.web.GradesService;
import org.sgu.oecde.web.PointsFacade;
import org.sgu.oecde.web.ResourceService;
import org.sgu.oecde.web.jsfbeans.util.NewEntry;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ViewScoped
public class StudentIndexBean extends StudentCurriculumBean{

    @ManagedProperty(value="#{cwDatesGetter}")
    private StringConstantsGetter cwDatesGetter;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    @ManagedProperty(value="#{controlWorkService}")
    private  ControlWorkService cwService;

    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;

    private List<PointsFacade>pointsList;

    private List<NewEntry<DeCurriculum,ControlWork>>m;
    
    private List<AdditionalSelfDependentWork>tests;

    private List<AdditionalSelfDependentWork>concludingTests;

    private boolean exame = false;

    public List<AdditionalSelfDependentWork> badPassedTests(boolean concluding){
        if(!exame)
            return null;
        if(tests==null){
            tests = testAttemptService.getStudentTestsWithAttempts(getCurriculums(), student);
            concludingTests = new ArrayList();
            Iterator<AdditionalSelfDependentWork> i = tests.iterator();
            while(i.hasNext()){
                AdditionalSelfDependentWork passedTest = i.next();
                if(passedTest==null||passedTest.getPointsForWork()>30||TestType.concluding.equals(passedTest.<TestEntity>getWork().getType()))
                    i.remove();
                if(passedTest.getPointsForWork()<30&&TestType.concluding.equals(passedTest.<TestEntity>getWork().getType()))
                   concludingTests.add(passedTest);
            }
        }
        return concluding?concludingTests:tests;
    }

    public List<NewEntry<DeCurriculum,ControlWork>>getWorks(){
        if(!exame)
            return null;
        if(m==null){
            List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(curriculumBuilder.getInstanceByCurrentDate(student, semester));
            Map<DeCurriculum,ControlWork>map = cwService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c);
            m = new ArrayList();
            NewEntry<DeCurriculum,ControlWork>e;
            for(DeCurriculum cur:c){
                ControlWork w = map.get(cur);
                if(w!=null&&(w.getCwAttempt()!=null&&w.getCwAttempt().size() > 0&&w.getProgress().equals(ControlWorkProgress.passed)))
                   continue;
                e = new NewEntry<DeCurriculum, ControlWork>(cur, w);
                m.add(e);
            }
        }
        return m;
    }

    public List<PointsFacade> getLowGradedDisciplines() {
        if(!exame)
            return null;
        if(pointsList==null){
            pointsList = gradesService.getStudentGrades(getCurriculumAndTeacher(), student);
            Iterator<PointsFacade> i = pointsList.iterator();
            while(i.hasNext()){
                PointsFacade points = i.next();
                boolean estimated = true;
                PointToEstimate pe = points.getGrade();
                if(pe!=null){
                    estimated = PointToEstimate.five.equals(pe)
                            ||PointToEstimate.four.equals(pe)
                            ||PointToEstimate.three.equals(pe)
                            ||PointToEstimate.passed.equals(pe);
                }
                if(points.getPoints().getSum()>175&&estimated){
                   i.remove();
                }
            }
        }
        return pointsList;
    }

    public Object getDate(String constantName){
        return cwDatesGetter.getConstant(ControlWorkCalendarConstantName.valueOf(constantName));
    }
    
    public String getTestClosingDate(){
        if(student.getGroup().getYear()==1
                ||(student.getGroup().getYear()==2
                &&(!student.<Group>getGroup().getSpeciality().getName().contains("ускор")
                   ||!student.<Group>getGroup().getSpeciality().getName().contains("сокр"))))
            return resourceService.getSimpleSpecialitiesTestsClosing();
        else
            return resourceService.getRegularTestEndDate();
    }
    @PostConstruct
    public void postConstract(){
        Assert.notNull(resourceService);
        String currentDate = DateConverter.currentDate();
        exame = (currentDate.compareTo(resourceService.getReExameBeginDate())>=0)&&(currentDate.compareTo(resourceService.getReExameEndDate())<0);
    }

    public boolean isExame() {
        return exame;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setCwService(ControlWorkService cwService) {
        this.cwService = cwService;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    public void setCwDatesGetter(StringConstantsGetter cwDatesGetter) {
        this.cwDatesGetter = cwDatesGetter;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}