package org.sgu.oecde.web.jsfbeans.student;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentSessionBean")
@SessionScoped
public class StudentSessionBean extends AbstractStudentBean{

    private Map<DeCurriculum,Teacher>summerCurriculums;

    private Map<DeCurriculum,Teacher>winterCurriculums;

    private static final long serialVersionUID = 150L;

    public Map<DeCurriculum, Teacher> getCurriculumAndTeacher(int semester) {
        if(((summerCurriculums==null&&semester==SemesterGetter.SUMMER_SEMESTER)||(winterCurriculums==null&&semester==SemesterGetter.WINTER_SEMESTER))){
            setSemester(semester);
            int correctSemester = semesterGetter.getSemesterByStudentYear(student, semester);
            int year = semesterGetter.getCurrentYear();
            if(student.getTransfered()!=null&&!student.getTransfered()){
                correctSemester = SemesterGetter.SUMMER_SEMESTER;
                year--;
            }
            Map<DeCurriculum,Teacher> l = curriculumDao.<DeCurriculum,Teacher>getTeachersByGroup(correctSemester, year, student.getGroup());
            if(semester == SemesterGetter.SUMMER_SEMESTER)
                summerCurriculums=l;
            else
                winterCurriculums=l;
        }
        return semester == SemesterGetter.SUMMER_SEMESTER?summerCurriculums:winterCurriculums;
    }

    @Override
    public void setStudent(Student student) {
        summerCurriculums = null;
        winterCurriculums = null;
        super.setStudent(student);
    }
}