package org.sgu.oecde.web.jsfbeans.student;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.tests.AdditionalCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
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

    @ManagedProperty(value="#{gradesService}")
    private GradesService gradesService;

    private List<AdditionalCurriculum>advCurriculums;

    private static final long serialVersionUID = 96L;

    public List<AdditionalCurriculum> getStudentsDisciplines(){
        if(advCurriculums==null){
            advCurriculums = testAttemptService.getStudentAttemptsCount(getCurriculums(),student);
            if(CollectionUtils.isEmpty(advCurriculums))
                return new LinkedList();
            List<PointsFacade> points = gradesService.getStudentGrades(getCurriculumAndTeacher(), student);
            for(AdditionalCurriculum c:advCurriculums){
                PointsFacade p = null;
                Iterator<PointsFacade>i = points.iterator();
                while(i.hasNext()){
                    p = i.next();
                    if(c.getCurriculum().equals(p.getPoints().getCurriculum())){
                        i.remove();
                        break;
                    }
                }
                if(p!=null){
                    c.setTestPoints(p.getTestPoints());
                    c.setConcludingReTestPoints(p.getConcludingReTestPoints());
                    c.setConcludingTestPoints(p.getConcludingTestPoints());
                    c.setReTestPoints(p.getReTestPoints());
                }
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
}
