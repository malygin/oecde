package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.web.TestConstantsServise;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentResults")
@ViewScoped
public class StudentResults extends AbstractStudentsListBean{
    
    private Student student;

    private List<ControlWork>works;

    private List<AdditionalSelfDependentWork>tests;

    @ManagedProperty(value="#{studentDao}")
    IBasicDao<Student>studentDao;

    @ManagedProperty(value="#{testAttemptService}")
    TestAttemptService testAttemptService;

    @ManagedProperty(value="#{controlWorkService}")
    ControlWorkService controlWorkService;

    @ManagedProperty(value="#{testConstantsServise}")
    private TestConstantsServise testConstantsServise;

    private static final long serialVersionUID = 109L;

    public List<AdditionalSelfDependentWork> getResults() {
        if(tests==null)
            tests = testAttemptService.getStudentSingleCurriculumTestsWithAttempts(getCurriculum(),student);
        return tests;
    }

    public List<ControlWork> getControlWorks(){
        if(works==null){
            List<DeCurriculum> c =  controlWorkService.getCurriculumsWithControlWorks(getCurriculum());
            if(!CollectionUtils.isEmpty(c)&&c.size()==1)
                works = new ArrayList(controlWorkService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c).values());
        }
        return works;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setStudentId(Long studentId) {
        student = studentDao.getById(studentId);
        setGroup(student.<Group>getGroup());
        works = null;
        tests = null;
    }

    public Student getStudent() {
        return student;
    }

    public Long getStudentId(){
        return student==null?0:student.getId();
    }

    public void setId(Long id) {
        works = null;
        tests = null;
    }

    public void setControlWorkService(ControlWorkService controlWorkService) {
        this.controlWorkService = controlWorkService;
    }

    public void setStudentDao(IBasicDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    public String getRegularAttemtpsCount() {
        return testConstantsServise.getRegularAttemtpsCount(getSemester());
    }

    public String getConcludingAttemtpsCount() {
        return testConstantsServise.getConcludingAttemtpsCount(getSemester());
    }

    public void setTestConstantsServise(TestConstantsServise testConstantsServise) {
        this.testConstantsServise = testConstantsServise;
    }
}
