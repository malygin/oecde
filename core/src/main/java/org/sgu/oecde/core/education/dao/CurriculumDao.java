package org.sgu.oecde.core.education.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractTeacher;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.HqlConstructor;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public class CurriculumDao<T extends Curriculum> extends BasicDao<T> implements ICurriculumDao<T>{

    private final String CURRICULUM_HQL_QUERY = "from Curriculum c join c.teacherToGroups t";
    private final String CURRICULUM_HQL_QUERY_WHERE = " where c.calendarYear=:y and c.semester in (:s)";

    @SuppressWarnings("unchecked")
    public CurriculumDao() {
        super((Class<T>) Curriculum.class);
    }

    public CurriculumDao(Class<T> type){
        super(type);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<T> getCurriculumsByModSemester(T item, Integer[] semesters) throws DataAccessException {
        Criteria cr =  getSession().createCriteria(type);
        item.setSemester(0);
        cr = this.getCriteriaByParametrizedItem(item, cr).add(Property.forName("semester").in(semesters));
        return cr.list();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<T> getBySemesterYearAndParameters(Integer[] semester, int year, AbstractTeacher teacher) throws DataAccessException {
        if(teacher == null&& teacher.getId()==0)
            return new ArrayList(0);
         return makeQuery("distinct c"," t.teacher=:t ",new String[]{"c.discipline"},null,semester,year)
                 .setParameter("t", teacher).list();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public <E extends AbstractTeacher> List<E> getTeachersByGroup(Integer[] semester, int year, StudentGroup group) throws DataAccessException {
        if(group == null&& group.getId()==0)
            return new ArrayList(0);
        return makeQuery("distinct t.teacher","t.group=:g",null,null,semester,year)
                 .setParameter("g", group).list();
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public <E extends StudentGroup> List<E> getGroupsForTeacher(Integer[] semester, int year, AbstractTeacher teacher) throws DataAccessException {
        if(teacher == null&& teacher.getId()==0)
            return new ArrayList(0);
         return makeQuery("distinct t.group"," t.teacher=:t",new String[]{"t.group.city","t.group.speciality"},null,semester,year)
                 .setParameter("t", teacher).list();
    }

    private Query makeQuery(String prefix, String postfix, String[] fetch,  String orderBy,Integer[] semester, int year){
        return HqlConstructor.makeQuery(getSession(), prefix, CURRICULUM_HQL_QUERY, fetch, CURRICULUM_HQL_QUERY_WHERE, postfix, orderBy)
                .setParameterList("s", semester).setInteger("y", year);
    }
}