package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.de.education.DeCurriculum;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentCurriculumBean")
@ViewScoped
@Secured(value="ROLE_STUDENT,ROLE_ADMIN")
public class StudentCurriculumBean extends AbstractStudentBean{

    private List<DeCurriculum>curriculums;

    private Map<DeCurriculum,Teacher>curriculumAndTeacher;

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;
    
    private static final long serialVersionUID = 104L;

    public List<DeCurriculum> getCurriculums() {
        if(curriculums==null)
            curriculums = new ArrayList(studentSessionBean.getCurriculumAndTeacher(semester).keySet());
        return curriculums;
    }

    public Map<DeCurriculum, Teacher> getCurriculumAndTeacher() {
        if(curriculumAndTeacher == null){
            curriculumAndTeacher = studentSessionBean.getCurriculumAndTeacher(semester);
        }
        return curriculumAndTeacher;
    }

    public Map<DeCurriculum, Teacher> getCurriculumAndTeacherByYear() {
        if(curriculumAndTeacher == null){
            curriculumAndTeacher = curriculumDao.<DeCurriculum,Teacher>getTeachersByGroup(semester, semesterGetter.getCalendarYear(student, semester).intValue(), student.getGroup());
        }
        return curriculumAndTeacher;
    }

    public void setStudentSessionBean(StudentSessionBean studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }
}
