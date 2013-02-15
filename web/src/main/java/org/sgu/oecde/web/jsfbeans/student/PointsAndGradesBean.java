package org.sgu.oecde.web.jsfbeans.student;

import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.AdditionalCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestService;
import org.sgu.oecde.tests.estimation.TestsCountEnum;
import org.sgu.oecde.web.GradesService;
import org.sgu.oecde.web.PointsFacade;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="gradesBean")
@ViewScoped
public class PointsAndGradesBean extends StudentCurriculumBean{
    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;

    private List<PointsFacade>points;
   
    private PointsFacade currentsFacade=null;
    private List<AdditionalCurriculum>advCurriculums;
    private List<AdditionalCurriculum>advCurriculumsSA;
    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    @ManagedProperty(value="#{testService}")
    private TestService testService;

    private static final long serialVersionUID = 97L;

    public List<PointsFacade>getPoints(){
        if(points==null){
            points = gradesService.getStudentGrades(getCurriculumAndTeacher(), student);
        }
        return points;
    }

    public List<AdditionalCurriculum> getStudentsDisciplines(){

        if(advCurriculums==null){
            List<Points>points = gradesService.getStudentGrades(getCurriculums(), student);
            if(CollectionUtils.isEmpty(points))
                return new ArrayList<AdditionalCurriculum>(0);
            advCurriculums = testAttemptService.getStudentAttemptsCount(getCurriculums(),student);
            Map<DeCurriculum,List<TestEntity>> m = testService.<DeCurriculum>getCurriculumTestsMap(getCurriculums());
            Iterator<Points> pI = points.iterator();
            while(pI.hasNext()){
                Points ps = pI.next();
                testService.countTests(m, ps);
                PointsFacade p = new PointsFacade(ps);
                if(!CollectionUtils.isEmpty(advCurriculums)){
                    Iterator<AdditionalCurriculum>it = advCurriculums.iterator();
                    while(it.hasNext()){
                        AdditionalCurriculum c = it.next();
                        if(c.getCurriculum().equals(ps.getCurriculum())){
                            DeCurriculum d = (DeCurriculum)c.getCurriculum();
                            c.setTestPoints(p.getTest());
                            c.setSum(p.getPoints().getSum());
                            c.setConcludingReTestPoints(p.getConcludingReTest()==null?0:p.getConcludingReTest());
                            c.setConcludingTestPoints(p.getConcludingTest()==null?0:p.getConcludingTest());
                            c.setReTestPoints(p.getReTest());
                            c.setTestsCount(p.getTestsCount()+p.getConcludingTestsCount());
                            c.setActivityPoints(p.getActivityPoints()==null?0:p.getActivityPoints());
                            c.setSamAudWorkPoints(p.getSamAudWorkPoints()==null?0:p.getSamAudWorkPoints());
                            c.setSamAudOutWorkPoints(p.getSamAudOutWorkPoints()==null?0:p.getSamAudOutWorkPoints());
                            c.setPersonalCharPoints(p.getPersonalCharPoints()==null?0:p.getPersonalCharPoints());
                            c.setGrade(p.getGrade());
                            pI.remove();
                        }
                    }
                }
            }
            pI = points.iterator();
            while(pI.hasNext()){
                Points ps = pI.next();
                //  testService.countTests(m, ps);
                AdditionalCurriculum c = new AdditionalCurriculum(ps.getCurriculum());
                if(c.getCurriculum().getUmk()!=null){
                    Integer r = ps.<Integer>getWorkPoints(TestsCountEnum.TESTS_COUNT);
                    Integer ct = ps.<Integer>getWorkPoints(TestsCountEnum.CONCLUDING_TESTS_COUNT);
                    c.setTestsCount((r!=null?r:0)+(ct!=null?ct:0));
                    DeCurriculum d = (DeCurriculum)c.getCurriculum();
                    PointsFacade p = new PointsFacade(ps);
                    c.setActivityPoints(p.getActivityPoints()==null?0:p.getActivityPoints());
                    c.setSamAudWorkPoints(p.getSamAudWorkPoints()==null?0:p.getSamAudWorkPoints());
                    c.setSamAudOutWorkPoints(p.getSamAudOutWorkPoints()==null?0:p.getSamAudOutWorkPoints());
                    c.setPersonalCharPoints(p.getPersonalCharPoints()==null?0:p.getPersonalCharPoints());
                    c.setSum(p.getPoints().getSum());
                    c.setGrade(p.getGrade());



                    advCurriculums.add(c);
                }
            }
            //Collections.sort(advCurriculums, new OrderByDisciplineName());
        }

        return advCurriculums;
    }

    public List<AdditionalCurriculum> getStudentsSA(){

        if(advCurriculumsSA==null){
            List<Points>points = gradesService.getStudentGrades(getCurriculumsSA(), student);
            if(CollectionUtils.isEmpty(points))
                return new ArrayList<AdditionalCurriculum>(0);
            advCurriculumsSA = testAttemptService.getStudentAttemptsCount(getCurriculumsSA(),student);
            Map<DeCurriculum,List<TestEntity>> m = testService.<DeCurriculum>getCurriculumTestsMap(getCurriculums());
            Iterator<Points> pI = points.iterator();
            while(pI.hasNext()){
                Points ps = pI.next();
                testService.countTests(m, ps);
                PointsFacade p = new PointsFacade(ps);
                if(!CollectionUtils.isEmpty(advCurriculumsSA)){
                    Iterator<AdditionalCurriculum>it = advCurriculumsSA.iterator();
                    while(it.hasNext()){
                        AdditionalCurriculum c = it.next();
                        if(c.getCurriculum().equals(ps.getCurriculum())){
                            DeCurriculum d = (DeCurriculum)c.getCurriculum();
                            c.setTestPoints(p.getTest());
                            c.setSum(p.getPoints().getSum());
                            c.setConcludingReTestPoints(p.getConcludingReTest()==null?0:p.getConcludingReTest()*d.getWeightTest()/100);
                            c.setConcludingTestPoints(p.getConcludingTest()==null?0:p.getConcludingTest());
                            c.setReTestPoints(p.getReTest());
                            c.setTestsCount(p.getTestsCount()+p.getConcludingTestsCount());
                            c.setActivityPoints(p.getActivityPoints()==null?0:p.getActivityPoints());
                            c.setSamAudWorkPoints(p.getSamAudWorkPoints()==null?0:p.getSamAudWorkPoints());
                            c.setSamAudOutWorkPoints(p.getSamAudOutWorkPoints()==null?0:p.getSamAudOutWorkPoints());
                            c.setPublishpoints(p.getPublishPoints());
                            c.setPersonalCharPoints(p.getPersonalCharPoints()==null?0:p.getPersonalCharPoints());
                            pI.remove();
                        }
                    }
                }
            }
            pI = points.iterator();
            while(pI.hasNext()){
                Points ps = pI.next();
                //  testService.countTests(m, ps);
                AdditionalCurriculum c = new AdditionalCurriculum(ps.getCurriculum());
                if(c.getCurriculum().getUmk()!=null){
                    Integer r = ps.<Integer>getWorkPoints(TestsCountEnum.TESTS_COUNT);
                    Integer ct = ps.<Integer>getWorkPoints(TestsCountEnum.CONCLUDING_TESTS_COUNT);
                    c.setTestsCount((r!=null?r:0)+(ct!=null?ct:0));
                    DeCurriculum d = (DeCurriculum)c.getCurriculum();
                    PointsFacade p = new PointsFacade(ps);
                    c.setActivityPoints(p.getActivityPoints()==null?0:p.getActivityPoints());
                    c.setSamAudWorkPoints(p.getSamAudWorkPoints()==null?0:p.getSamAudWorkPoints());
                    c.setSamAudOutWorkPoints(p.getSamAudOutWorkPoints()==null?0:p.getSamAudOutWorkPoints());
                    c.setPersonalCharPoints(p.getPersonalCharPoints()==null?0:p.getPersonalCharPoints());
                    c.setSum(p.getPoints().getSum());


                    advCurriculumsSA.add(c);
                }
            }
            //Collections.sort(advCurriculums, new OrderByDisciplineName());
        }

        return advCurriculumsSA;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    public PointsFacade getCurrentsFacade() {
        return currentsFacade;
    }

    public void setCurrentsFacade(PointsFacade currentsFacade) {
        this.currentsFacade = currentsFacade;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}