package org.sgu.oecde.controlworks.dao;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.sgu.oecde.controlworks.ControlWorkAttempt;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public class ControlWorkAttemptDao extends BasicDao<ControlWorkAttempt> implements IControlWorkAttemptDao{

    protected ControlWorkAttemptDao() {
        super(ControlWorkAttempt.class);
    }

    @Override
    public List<ControlWorkAttempt> getAttemptsList(int beginIndex, int endIndex, List<AbstractStudent>students,List<Curriculum>curriculums) throws DataAccessException {
        Assert.state(beginIndex >= 0 || endIndex > 0 || beginIndex < endIndex ||!CollectionUtils.isEmpty(students)||!CollectionUtils.isEmpty(curriculums),"wrong indexes");
        return getSession().createCriteria(type)
                .createAlias("work", "w")
                .setFirstResult(beginIndex).setMaxResults(endIndex)
                .add(Property.forName("w.curriculum").in(curriculums))
                .add(Property.forName("w.student").in(students))
                .addOrder(Order.asc("read"))
                .addOrder(Order.desc("attemptDate"))
                .list();
    }
}
