package org.sgu.oecde.schedule.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.users.StudentGroup;
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
    public Integer getLessonCount(final Lesson lesson) throws DataAccessException {
        Criteria cr = getSession().createCriteria(type).setProjection(Projections.rowCount());
        getCriteriaByParametrizedItem(lesson,cr);
        return (Integer) cr.list().get(0);
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
}