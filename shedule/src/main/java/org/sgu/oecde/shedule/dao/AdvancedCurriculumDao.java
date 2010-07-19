package org.sgu.oecde.shedule.dao;

import java.util.ArrayList;
import java.util.List;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.dao.CurriculumDao;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.users.Teacher;
import org.springframework.dao.DataAccessException;

/**
 * {@inheritDoc }
 */
public class AdvancedCurriculumDao<T extends Curriculum> extends CurriculumDao<T> implements IAdvancedCurriculumDao<T>{

    @SuppressWarnings("unchecked")
    protected AdvancedCurriculumDao() {
        super((Class<T>) Curriculum.class);
    }

    @SuppressWarnings("unchecked")
    protected AdvancedCurriculumDao(Class<T> type){
        super(type);
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
    public List<StudentGroup> getGroupBySemesterYearTeacherDiscipline(Integer[] semester, int year, Teacher teacher,Discipline discipline) throws DataAccessException {
        if(teacher == null||teacher.getId()==null||discipline == null||discipline.getId()==null)
            return new ArrayList(0);
        return makeQuery("distinct t.group"," t.teacher=:t and c.discipline=:d",new String[]{"t.group.speciality"},null,semester,year)
                 .setParameter("t", teacher).setParameter("d", discipline).list();
    }
}