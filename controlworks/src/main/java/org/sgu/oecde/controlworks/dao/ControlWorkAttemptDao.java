package org.sgu.oecde.controlworks.dao;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.sgu.oecde.controlworks.ControlWorkAttempt;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.Teacher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * {@inheritDoc }
 */
@Repository
public class ControlWorkAttemptDao extends UpdateDao<ControlWorkAttempt> implements IControlWorkAttemptDao{

    protected ControlWorkAttemptDao() {
        super(ControlWorkAttempt.class);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ControlWorkAttempt> getAttemptsList(int beginIndex, int endIndex, List<AbstractStudent>students,List<Curriculum>curriculums) throws DataAccessException {
        Assert.state(beginIndex >= 0 || endIndex > 0 || beginIndex < endIndex ||!CollectionUtils.isEmpty(students)||!CollectionUtils.isEmpty(curriculums),"wrong indexes");
        return getSession().createCriteria(type)
                .createAlias("work", "w")
                .setFirstResult(beginIndex).setMaxResults(endIndex)
                .add(Property.forName("w.curriculum").in(curriculums))
                .add(Property.forName("filePath").ne("empty"))
                .add(Property.forName("w.student").in(students))
                .addOrder(Order.asc("read"))
                .addOrder(Order.desc("attemptDate"))
                .setCacheable(true)
                .list();
    }

    public int getAttemptCountForTeacher(List<? extends Curriculum>curriculums,Teacher teacher, boolean readOnly) throws DataAccessException {
        List<Long>list = getSession()
                .createQuery("select count(*) from ControlWorkAttempt cw join cw.work w join w.curriculum cr join cr.teacherToGroups tg join tg.group gr join gr.persons p where cr in (:crs) and w.student in (p) and tg.teacher=:tch and cw.filePath!='empty'"+(readOnly?" and cw.read=1 ":""))
                .setParameterList("crs", curriculums).setParameter("tch", teacher).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }
}
