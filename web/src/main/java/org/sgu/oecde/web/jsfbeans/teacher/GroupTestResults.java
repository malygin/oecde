package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.web.ResourceService;
import org.sgu.oecde.web.jsfbeans.util.NewEntry;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="groupTestResults")
@ViewScoped
public class GroupTestResults extends AbstractStudentsListBean{

    @ManagedProperty(value="#{testAttemptService}")
    TestAttemptService testAttemptService;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    private List<NewEntry<Student,AdditionalSelfDependentWork>>tests;

    private static final long serialVersionUID = 112L;

    public List<NewEntry<Student,AdditionalSelfDependentWork>>  getGroupTestResults() {
        if(tests==null){
            List<AdditionalSelfDependentWork> l = testAttemptService.getCurriculumAttempts(getCurriculum(),getStudentsList());
            Student st = null;
            NewEntry e = null;
            tests = new ArrayList<NewEntry<Student, AdditionalSelfDependentWork>>();
            for(AdditionalSelfDependentWork w:l){
                if(!w.getStudent().equals(st)){
                    e = new NewEntry(w.getStudent(), w);
                    tests.add(e);
                }
                st = w.getStudent();
            }
        }
        return tests;
    }

    public String getRegularAttemtpsCount() {
        return resourceService.getRegularAttemtpsCount(getSemester());
    }

    public String getConcludingAttemtpsCount() {
        return resourceService.getConcludingAttemtpsCount(getSemester());
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

}
