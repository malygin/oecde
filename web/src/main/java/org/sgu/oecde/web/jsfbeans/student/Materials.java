package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
@ManagedBean(name="materialsBean")
@ViewScoped
public class Materials extends StudentCurriculumBean{

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    @ManagedProperty(value="#{testService}")
    private TestService testService;

    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;

    private List<AdditionalCurriculum>advCurriculums;

    private static final long serialVersionUID = 96L;

    public List<AdditionalCurriculum> getStudentsDisciplines(){
        
        if(advCurriculums==null){
            List<Points>points = gradesService.getStudentGrades(getCurriculums(), student);
            if(CollectionUtils.isEmpty(points))
                return new ArrayList<AdditionalCurriculum>(0);
            advCurriculums = testAttemptService.getStudentAttemptsCount(getCurriculums(),student);
            Map<DeCurriculum,List<TestEntity>>m = testService.<DeCurriculum>getCurriculumTestsMap(getCurriculums());
            Iterator<Points>pI = points.iterator();
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
                            c.setConcludingReTestPoints(p.getConcludingReTest()==null?0:p.getConcludingReTest()*d.getWeightTest()/100);
                            c.setConcludingTestPoints(p.getConcludingTest()==null?0:p.getConcludingTest()*d.getWeightTest()/100);
                            c.setReTestPoints(p.getReTest());
                            c.setTestsCount(p.getTestsCount()+p.getConcludingTestsCount());
                            c.setActivityPoints(p.getActivityPoints()==null?0:p.getActivityPoints()*d.getWeightAtt()/100);
                            c.setSamAudWorkPoints(p.getSamAudWorkPoints()==null?0:p.getSamAudWorkPoints()*d.getWeightAud()/100);
                            c.setSamAudOutWorkPoints(p.getSamAudOutWorkPoints()==null?0:p.getSamAudOutWorkPoints()*d.getWeightOutAud()/100);
                            c.setPersonalCharPoints(p.getPersonalCharPoints()==null?0:p.getPersonalCharPoints()*d.getWeightPers()/100);
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
                    advCurriculums.add(c);
                }
            }
            Collections.sort(advCurriculums, new OrderByDisciplineName());
        }
        return advCurriculums;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setGradesService(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    private class OrderByDisciplineName implements Comparator<AdditionalCurriculum>{

        @Override
        public int compare(AdditionalCurriculum o1, AdditionalCurriculum o2) {
            int discipline = 0;
            if(o1!=null &&o2!=null &&
                    o1.getCurriculum()!=null&&
                    o2.getCurriculum()!=null&&
                    o1.<DeCurriculum>getCurriculum().getDiscipline()!=null &o2.<DeCurriculum>getCurriculum().getDiscipline()!=null &&
                    o1.<DeCurriculum>getCurriculum().getDiscipline().getName()!=null
                ){
                discipline = o1.<DeCurriculum>getCurriculum().getDiscipline().getName().compareTo(o2.<DeCurriculum>getCurriculum().getDiscipline().getName());
            }
            return discipline;
        }

    }
}
