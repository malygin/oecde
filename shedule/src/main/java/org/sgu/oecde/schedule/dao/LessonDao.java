package org.sgu.oecde.schedule.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.schedule.Lesson;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc }
 */
@Repository
public class LessonDao extends BasicDao<Lesson> implements ILessonDao{

    private final static String GET_LESSONS_FOR_TEACHER_QUERY="select distinct l from Lesson as l join fetch l.citiesWithGroups as g join fetch l.discipline c join fetch l.teacher t where g.group in(:grs) and l.year=:y and l.winter=:w order by c,g.city,g.group,l.lessonDate";

    public LessonDao() {
        super(Lesson.class);
    }

    /**
     * {@inheritDoc }
     */
    @Transactional
    @Override
    public void saveLesson(final Lesson lesson) throws DataAccessException {
        getSession().merge(lesson);
    }

    /**
     * {@inheritDoc }
     */
    @Transactional
    @Override
    public void deleteLesson(final Lesson lesson) throws DataAccessException {
           getSession().delete(lesson);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Lesson> getListByMonth(String year, String month) throws DataAccessException{
        Criteria crit =getSession().createCriteria(type);
        crit.add(Restrictions.between("lessonDate", year+"."+month+"."+"01 00:00:00",year+"."+month+"."+"31 00:00:00"));
        List<Lesson> lessonList = crit.setCacheable(true).list();
        return lessonList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Lesson> getLessonsByGroups(List<? extends StudentGroup> groups, boolean isWinter, int year, int maxResult, int firtsResult) throws DataAccessException {
        return getSession().createQuery(GET_LESSONS_FOR_TEACHER_QUERY)
                .setParameterList("grs", groups).setBoolean("w", isWinter).setInteger("y", year)
                .setFirstResult(getFirstResult(maxResult, firtsResult)).setMaxResults(maxResult).list();
    }

    /**
     * {@inheritDoc }
     */
    public Long getLessonsCountByGroups(List<? extends StudentGroup> groups, boolean isWinter, int year) throws DataAccessException {
        Query q = getSession().createQuery("select count(l) "+GET_LESSONS_FOR_TEACHER_QUERY)
                .setParameterList("grs", groups).setBoolean("w", isWinter).setInteger("y", year);
        List<Long>l =q.list();
        return !l.isEmpty()?(Long)l.get(0):0l;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Long getLessonsCountForStudent(boolean isWinter, Group g,City c, String beginDate, String endDate) throws DataAccessException {
        String query = insertParameters(beginDate, endDate);
        Query q = getSession().createQuery("select count(l) "+query);
        q.setBoolean("w", isWinter).setParameter("g", g).setParameter("c", c);
        List<Long>l =q.list();
        return !l.isEmpty()?(Long)l.get(0):0l;
    }

    public List<Lesson>getLessonsFroStudent( boolean isWinter, Group g,City c, int maxResult, int firtsResult,String beginDate, String endDate) throws DataAccessException{
        String query = insertParameters(beginDate, endDate);
        Query q = getSession().createQuery(query);
        q.setBoolean("w", isWinter).setParameter("g", g).setParameter("c", c).setFirstResult(getFirstResult(maxResult, firtsResult)).setMaxResults(maxResult);
        return q.list();
    }

    private String insertParameters( String beginDate, String endDate){
        String query = "from Lesson l join l.citiesWithGroups cwg where l.winter=:w and cwg.group=:g and cwg.city=:c";
        if(beginDate == null && endDate == null){
            query+= " and l.lessonDate > '"+DateConverter.currentDate()+"'";
        } else{
            query+=" and l.lessonDate between '"+beginDate +" 00:00:00' and '"+endDate+" 00:00:00'";
        }
        return query;
    }

    private int getFirstResult(int maxResult, int pageNumber){
        return maxResult * (pageNumber-1) ;
    }
}