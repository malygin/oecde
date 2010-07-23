package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.AdditionalCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.TestService;
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
                            c.setTestPoints(p.getTestPoints());
                            c.setConcludingReTestPoints(p.getConcludingReTestPoints());
                            c.setConcludingTestPoints(p.getConcludingTestPoints());
                            c.setReTestPoints(p.getReTestPoints());
                            c.setTestsCount(p.getTestsCount()+p.getConcludingTestsCount());
                            pI.remove();
                        }
                    }
                }
            }
            pI = points.iterator();
            while(pI.hasNext()){
                Points ps = pI.next();
                AdditionalCurriculum c = new AdditionalCurriculum(ps.getCurriculum());
                advCurriculums.add(c);
            }
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
}
