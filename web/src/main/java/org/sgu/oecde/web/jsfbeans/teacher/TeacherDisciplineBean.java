package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherDisciplinesBean")
@SessionScoped
public class TeacherDisciplineBean extends AbstractTeacherBean{

    private List<DeCurriculum>currentCurriculums;

    private List<DeCurriculum>previousCurriculums;

    private static final long serialVersionUID = 110L;

    public List<DeCurriculum> getDisciplines(){
        if(currentCurriculums==null||previousCurriculums==null){
            List<DeCurriculum> l = curriculumDao.getBySemesterYearAndParameters(semesters(), year(),teacher);
            if(semester == 0)
                currentCurriculums=l;
            else
                previousCurriculums=l;
        }
        return semester == 0?currentCurriculums:previousCurriculums;
    }
}