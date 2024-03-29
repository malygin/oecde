package org.sgu.oecde.schedule.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.users.Teacher;
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

    private final static String GET_LESSONS_BY_GROUPS_QUERY="from Lesson as l join fetch l.citiesWithGroups as cwg join fetch l.discipline c join fetch l.teacher t where cwg.city =:c and l.year=:y and l.winter=:w";

    private final static String GET_LESSONS_FOR_STUDENT_QUERY="from Lesson l join  l.citiesWithGroups cwg where l.winter=:w and cwg.group=:g and cwg.city=:c";
    private final static String GET_LESSONS_FOR_TEACHER_QUERY="from Lesson l  where l.teacher=:t and l.winter=:w ";

    private final static String ORDER_BY=" order by l.lessonDate,l.discipline";
     private final static String ORDER_BY_DESC=" order by l.lessonDate,l.discipline";

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
    public List<Lesson> getLessonsByCity(City c, boolean isWinter, int year, int maxResult, int firtsResult, String beginDate, String endDate) throws DataAccessException {
        return getSession().createQuery("select distinct l "+GET_LESSONS_BY_GROUPS_QUERY+insertParameters(beginDate, endDate, false)+ORDER_BY_DESC)
                .setParameter("c", c).setBoolean("w", isWinter).setInteger("y", year)
                .setFirstResult(getFirstResult(maxResult, firtsResult)).setMaxResults(maxResult).list();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Long getLessonsCountByCity(City c, boolean isWinter, int year, String beginDate, String endDate) throws DataAccessException {
        String query = "select count(l)  "+GET_LESSONS_BY_GROUPS_QUERY+insertParameters(beginDate, endDate, false);
        Query q = getSession().createQuery(query)
                .setParameter("c", c).setBoolean("w", isWinter).setInteger("y", year);
        List<Long>l =q.list();
        return !l.isEmpty()?(Long)l.get(0):0l;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Long getLessonsCountForStudent(boolean isWinter, Group g,City c, String beginDate, String endDate) throws DataAccessException {
        String query = "select count(l) "+GET_LESSONS_FOR_STUDENT_QUERY+insertParameters(beginDate, endDate, false);
        Query q = getSession().createQuery(query);
        q.setBoolean("w", isWinter).setParameter("g", g).setParameter("c", c);
        List<Long>l =q.list();
        return !l.isEmpty()?(Long)l.get(0):0l;
    }

    @Override
    public List<Lesson>getLessonsForStudent( boolean isWinter, Group g,City c, int maxResult, int firtsResult,String beginDate, String endDate) throws DataAccessException{
       // String query = "select distinct l "+GET_LESSONS_FOR_STUDENT_QUERY+insertParameters(beginDate, endDate)+ORDER_BY;
        String query = "select distinct l "+GET_LESSONS_FOR_STUDENT_QUERY+insertParameters(beginDate, endDate, false)+ORDER_BY;
        Query q = getSession().createQuery(query);
        q.setBoolean("w", isWinter).setParameter("g", g).setParameter("c", c).setFirstResult(getFirstResult(maxResult, firtsResult)).setMaxResults(maxResult);
        return q.list();
    }
    
    @Override
     public List<Lesson>getLessonsForTeacher( boolean isWinter, Teacher t, int maxResult, int firtsResult,String beginDate, String endDate) throws DataAccessException{
       // String query = "select distinct l "+GET_LESSONS_FOR_STUDENT_QUERY+insertParameters(beginDate, endDate)+ORDER_BY;
        String query = "select distinct l "+GET_LESSONS_FOR_TEACHER_QUERY+insertParameters(beginDate, endDate, false)+ORDER_BY;
        Query q = getSession().createQuery(query);
        q.setBoolean("w", isWinter).setParameter("t", t).setFirstResult(getFirstResult(maxResult, firtsResult)).setMaxResults(maxResult);
        return q.list();
    }
        @Override
     public List<Lesson>getLessonsForAdmin( int maxResult, int firtsResult,String beginDate, String endDate) throws DataAccessException{
       // String query = "select distinct l "+GET_LESSONS_FOR_STUDENT_QUERY+insertParameters(beginDate, endDate)+ORDER_BY;
        String query = "select distinct l from Lesson l "+insertParameters(beginDate, endDate, true)+ORDER_BY;
        Query q = getSession().createQuery(query);
        q.setFirstResult(getFirstResult(maxResult, firtsResult)).setMaxResults(maxResult);
        return q.list();
    }


    private String insertParameters( String beginDate, String endDate, boolean first ){
        String query = " ";
        if(beginDate == null && endDate == null){
           // query+= " > '"+DateConverter.currentDate()+"'";
        } else{
            if (!first)  query+="and ";
            else query+=" where ";
            query+="l.lessonDate  between '"+beginDate +" 00:00:00' and '"+endDate+" 00:00:00'";
        }
        return query;
    }
     private String insertParametersDate( String date){
        String query = " and l.lessonDate like '%"+date+"%' ";
//        if(beginDate == null && endDate == null){
//            query+= " > '"+DateConverter.currentDate()+"'";
//        } else{
//            query+=" between '"+beginDate +" 00:00:00' and '"+endDate+" 00:00:00'";
//        }
        return query;
    }

    private int getFirstResult(int maxResult, int pageNumber){
        return maxResult * (pageNumber-1) ;
    }
    
    @Override
    public List<Lesson>getLessonsByDate(Lesson l) throws DataAccessException{
       String query = "select l from Lesson as l where l.lessonDate like :d";
       Query q = getSession().createQuery(query);
       
       q.setString("d", "%"+l.getLessonDate()+"%");
       return q.list();
    }

    @Override
    public Long getLessonsCountForTeacher(boolean isWinter, Teacher t, String beginDate, String endDate) throws DataAccessException {
        String query = "select count(l) "+GET_LESSONS_FOR_TEACHER_QUERY+insertParameters(beginDate, endDate, false);
        Query q = getSession().createQuery(query);
        q.setBoolean("w", isWinter).setParameter("t", t);
        List<Long>l =q.list();
        return !l.isEmpty()?(Long)l.get(0):0l;   
    }

    @Override
    public Long getLessonsCountForAdmin(String beginDate, String endDate) throws DataAccessException {
        String query = "select count(l) from Lesson l "+insertParameters(beginDate, endDate, true);
        Query q = getSession().createQuery(query);  
        List<Long>l =q.list();
        return !l.isEmpty()?(Long)l.get(0):0l;
    }

 
}