package org.sgu.oecde.shedule.dao;

import java.util.ArrayList;
import java.util.List;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.dao.CurriculumDao;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Teacher;
import org.springframework.dao.DataAccessException;

/**
 * {@inheritDoc }
 */
public class AdvancedCurriculumDao extends CurriculumDao<DeCurriculum> implements IAdvancedCurriculumDao{

    @SuppressWarnings("unchecked")
    protected AdvancedCurriculumDao() {
        super(DeCurriculum.class);
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Discipline> getBySemesterYearTeacher(Integer[] semester, int year, Teacher teacher) throws DataAccessException {
        if(teacher == null&& teacher.getId()==0)
            return new ArrayList(0);
        return makeQuery("distinct c.discipline"," t.teacher=:t ",null,null,semester,year)
                 .setParameter("t", teacher).list();
    }

    /**
     * {@inheritDoc }
     */
    public List<Group> getGroupBySemesterYearTeacherDiscipline(Integer[] semester, int year, Teacher teacher,Discipline discipline) throws DataAccessException {
        return makeQuery("distinct t.studentGroup"," t.teacher=:t and c.discipline=:d",new String[]{"t.studentGroup.speciality"},null,semester,year)
                 .setParameter("t", teacher).setParameter("d", discipline).list();
    }
}