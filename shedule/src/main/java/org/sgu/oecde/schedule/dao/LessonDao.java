package org.sgu.oecde.schedule.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.schedule.Lesson;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc }
 */
@Repository
public class LessonDao extends BasicDao<Lesson> implements ILessonDao{

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
    public Long getLessonCount(final Lesson lesson, long groupId, String beginDate, String endDate) throws DataAccessException {
        Criteria cr = insertParameters(lesson,groupId,beginDate,endDate);
        cr.setProjection(Projections.rowCount());
        return (Long) cr.list().get(0);
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
    public List<Lesson> getByGroups(List<? extends StudentGroup> groups, boolean isWinter, int year) throws DataAccessException {
        return getSession().createQuery("select distinct l from Lesson as l join fetch l.groups as g join fetch l.discipline c join fetch l.teacher t where g in(:grs) and l.year=:y and l.winter=:w order by c,g,l.lessonDate")
                .setParameterList("grs", groups).setBoolean("w", isWinter).setInteger("y", year).list();
    }

    public List<Lesson>getByLessonAndDate( Lesson l, long groupId, int maxResult, int firtsResult,String beginDate, String endDate) throws DataAccessException{
        if(l == null)
            return new ArrayList<Lesson>(0);
        Criteria crit = insertParameters(l,groupId,beginDate,endDate);
        crit.setMaxResults(maxResult);
        crit.setFirstResult(firtsResult-1);
        return crit.list();
    }

    private Criteria insertParameters(final Lesson lesson, long groupId, String beginDate, String endDate){
        Criteria crit = getSession().createCriteria(type);
        crit = getCriteriaByParametrizedItem(lesson, crit);
        if(beginDate == null && endDate == null)
            crit.add(Restrictions.gt("lessonDate", DateConverter.currentDate()));
        else
            crit.add(Restrictions.between("lessonDate", beginDate +" 00:00:00",endDate+" 00:00:00"));
        crit.createAlias("groups", "gr").add(Restrictions.eq("gr.id", groupId));
        return crit;
    }
}