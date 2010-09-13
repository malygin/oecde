package org.sgu.oecde.core.education.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.HqlConstructor;
import org.springframework.dao.DataAccessException;

/**
 * {@inheritDoc}
 */
public class CurriculumDao<T extends Curriculum> extends BasicDao<T> implements ICurriculumDao<T>{

    private final String CURRICULUM_HQL_QUERY = "from Curriculum c join c.teacherToGroups t";
    private final String CURRICULUM_HQL_QUERY_WHERE = "c.calendarYear=:y and c.semester in (:s)";
    private static final long serialVersionUID = 119L;

    @SuppressWarnings("unchecked")
    protected CurriculumDao() {
        super((Class<T>) Curriculum.class);
    }

    protected CurriculumDao(Class<T> type){
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
    public List<T> getBySemesterYearAndParameters(Integer[] semester, int year, Teacher teacher) throws DataAccessException {
        if(teacher == null&& teacher.getId()==0)
            return new ArrayList(0);
         return makeQuery("distinct c"," t.teacher=:t ",new String[]{"c.discipline"},null,semester,year)
                 .setParameter("t", teacher).list();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public <K extends Curriculum,V extends Teacher>Map<K,V> getTeachersByGroup(int semester, int year, StudentGroup group) throws DataAccessException {
        if(group == null&& group.getId()==0)
            return null;
        ScrollableResults result = getSession().createQuery("select distinct c,t.teacher from AdvancedCurriculum c join c.teacherToGroups t join fetch c.discipline join fetch c.speciality where c.calendarYear=:y and c.semester =:s and t.group=:g")
                .setParameter("s", semester).setParameter("y", year).setParameter("g", group).setCacheable(true).scroll();
        Map<K,V>map = new HashMap<K,V>();
        while(result.next()){
            map.put((K)result.get(0), (V)result.get(1));
        }
        return map;
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public <E extends StudentGroup> List<E> getGroupsForTeacher(Integer[] semester, int year, Teacher teacher) throws DataAccessException {
        if(teacher == null&& teacher.getId()==0)
            return new ArrayList(0);
         return makeQuery("distinct t.group"," t.teacher=:t",new String[]{"t.group.city","t.group.speciality"},null,semester,year)
                 .setParameter("t", teacher).setCacheable(false).list();
    }

    /**
     * формирует общий запрос
     * @param prefix
     * @param postfix
     * @param fetch
     * @param orderBy
     * @param semester
     * @param year
     * @return
     */
    protected Query makeQuery(String prefix, String postfix, String[] fetch,  String orderBy,Integer[] semester, int year){
        return HqlConstructor.makeQuery(getSession(), prefix, CURRICULUM_HQL_QUERY, fetch, CURRICULUM_HQL_QUERY_WHERE, postfix, orderBy)
                .setParameterList("s", semester).setInteger("y", year);
    }
}