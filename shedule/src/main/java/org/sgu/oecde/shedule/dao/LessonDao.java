package org.sgu.oecde.shedule.dao;

import java.text.ParseException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.shedule.Lesson;
import org.springframework.dao.DataAccessException;

/**
 * {@inheritDoc }
 */
public class LessonDao extends BasicDao<Lesson> implements ILessonDao{

    public LessonDao() {
        super(Lesson.class);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void saveLesson(final Lesson lesson) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate(lesson);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void deleteLesson(final Lesson lesson) throws DataAccessException {
        getHibernateTemplate().delete(lesson);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Integer getLessonCount(final Lesson lesson) throws DataAccessException {
        Criteria cr = getSession().createCriteria(Lesson.class).setProjection(Projections.rowCount());
        getCriteriaByParametrizedItem(lesson,cr);
        return (Integer) cr.list().get(0);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Lesson> getListByMonth(String year, String month) throws DataAccessException, ParseException{
        Criteria crit =getSession().createCriteria(Lesson.class);
        crit.add(Expression.between("lessonDate", year+"."+month+"."+"01 00:00:00",year+"."+month+"."+"31 00:00:00"));
        List<Lesson> lessonList = crit.list();
        return lessonList;
    }
}