package org.sgu.oecde.shedule;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.sgu.oecde.core.education.AdvancedCurriculum;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.shedule.dao.ILessonDao;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@Service
public class LessonService implements Serializable{
    @Resource
    ILessonDao lessonDao;
    private static final long serialVersionUID = 120L;

    public LessonService() {
    }

    public List<Lesson>getStudentLessons(StudentGroup group,List<? extends AdvancedCurriculum>curriculums){
        List<Discipline>disciplines = new LinkedList<Discipline>();
        if(CollectionUtils.isEmpty(curriculums))
            return null;
        for(AdvancedCurriculum c:curriculums){
            if(c!=null&&c.getDiscipline()!=null)
            disciplines.add(c.getDiscipline());
        }
        return lessonDao.getByGroupAndDisciplines(group, disciplines);
    }
}
