package org.sgu.oecde.shedule.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.users.Teacher;
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

    public List<Lesson> getGroupLessons(StudentGroup group,List<? extends Curriculum>curriculums) throws DataAccessException{
        if(CollectionUtils.isEmpty(curriculums)||group==null)
            return null;
        Criteria cr =getSession().createCriteria(type);
        return cr.createAlias("groups", "gr").add(Restrictions.eq("gr.id",group.getId()))
                .add(Property.forName("curriculum").in(curriculums))
                .addOrder(Order.asc("curriculum")).addOrder(Order.asc("lessonDate"))
                .list();
    }

    public List<Lesson> getTeacherLessons(Teacher teacher,List<? extends Curriculum>curriculums) throws DataAccessException{
        if(CollectionUtils.isEmpty(curriculums)||teacher==null)
            return null;
        Criteria cr =getSession().createCriteria(type);
        return cr.add(Property.forName("teacher").eq(teacher))
                .add(Property.forName("curriculum").in(curriculums))
                .addOrder(Order.asc("curriculum")).addOrder(Order.asc("groups")).addOrder(Order.asc("lessonDate"))
                .list();
    }
}