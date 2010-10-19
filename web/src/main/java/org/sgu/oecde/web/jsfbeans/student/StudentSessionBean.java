package org.sgu.oecde.web.jsfbeans.student;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentSessionBean")
@SessionScoped
public class StudentSessionBean extends AbstractStudentBean{

    private Map<DeCurriculum,Teacher>currentCurriculums;

    private Map<DeCurriculum,Teacher>previousCurriculums;

    private static final long serialVersionUID = 150L;

    public Map<DeCurriculum, Teacher> getCurriculumAndTeacher(int semester) {
        if(((currentCurriculums==null&&semester==SemesterGetter.CURRENT_SEMESTER)||(previousCurriculums==null&&semester==SemesterGetter.PREVIOUS_SEMESTER))){
            setSemester(semester);
            int correctSemester = student.getTransfered()!=null&&student.getTransfered()?0:1;
            Map<DeCurriculum,Teacher> l = curriculumDao.<DeCurriculum,Teacher>getTeachersByGroup(semesterGetter.getSemesterByStudentYear(student, semester-correctSemester).intValue(), semesterGetter.getCalendarYear(correctSemester), student.getGroup());
            if(semester == 0)
                currentCurriculums=l;
            else
                previousCurriculums=l;
        }
        return semester == 0?currentCurriculums:previousCurriculums;
    }
}