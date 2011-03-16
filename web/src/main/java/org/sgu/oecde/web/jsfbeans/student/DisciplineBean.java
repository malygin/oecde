package org.sgu.oecde.web.jsfbeans.student;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.web.ResourceService;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="disciplineBean")
@ViewScoped
public class DisciplineBean extends StudentCurriculumBean{

    private Long curriculumId;

    private DeCurriculum curriculum;

    private List<Object[]>tests;

    private boolean accessDenied = true;

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    private static final long serialVersionUID = 102L;
    
    public DeCurriculum getDiscipline() {
        return curriculum;
    }

    public List<Object[]> getTests() {
        if(tests==null && curriculum!=null){
            tests = new LinkedList();
            List<AdditionalSelfDependentWork>l = testAttemptService.getStudentSingleCurriculumTestsWithAttempts(curriculum,student);
            if(CollectionUtils.isEmpty(l))
                return new LinkedList();
            else{
                boolean available = resourceService.isConcludingTestAvailable(student, curriculum);
                for(AdditionalSelfDependentWork w:l){
                    tests.add(resourceService.getTestForStudent(w,student,available));
                }
            }
        }
        
        return tests;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
        tests = null;
        curriculum = resourceService.getDisciplineForStudent(student,curriculumId,getCurriculums());
        accessDenied = (curriculum==null);
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public boolean isAccessDenied() {
        return accessDenied;
    }

    public void setAccessDenied(boolean accessDenied) {
        this.accessDenied = accessDenied;
    }
}
