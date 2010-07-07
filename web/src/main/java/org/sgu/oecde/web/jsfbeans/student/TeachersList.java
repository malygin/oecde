package org.sgu.oecde.web.jsfbeans.student;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.Teacher;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teachersListBean")
@ViewScoped
public class TeachersList extends StudentCurriculumBean{

    private List<Teacher>teachersList;

    private static final long serialVersionUID = 99L;

    public List<Teacher> getTeachers(){
        if(teachersList==null)
            teachersList = curriculumDao.<Teacher>getTeachersByGroup(semesterGetter.getSemestersByInt(semester), semesterGetter.getCalendarYear(semester), student.getGroup());
        return teachersList;
    }

    public void setSemester(int semester) {
        this.semester = semester;
        teachersList = null;
    }
}
