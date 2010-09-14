package org.sgu.oecde.shedule.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.shedule.Lesson;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
        getHibernateTemplate().merge(lesson);
    }

    /**
     * {@inheritDoc }
     */
    @Transactional
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
    public List<Lesson> getListByMonth(String year, String month) throws DataAccessException{
        Criteria crit =getSession().createCriteria(Lesson.class);
        crit.add(Restrictions.between("lessonDate", year+"."+month+"."+"01 00:00:00",year+"."+month+"."+"31 00:00:00"));
        List<Lesson> lessonList = crit.setCacheable(true).list();
        return lessonList;
    }

    public List<Lesson> getByGroupAndDisciplines(StudentGroup group,List<? extends Discipline>disciplines) throws DataAccessException{
        if(CollectionUtils.isEmpty(disciplines)||group==null)
            return null;
        Criteria cr =getSession().createCriteria(Lesson.class);
        return cr.add(Property.forName("group").in(new StudentGroup[]{group}))
                .add(Property.forName("group").in(disciplines)).list();
    }
}