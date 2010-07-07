package org.sgu.oecde.tests.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.education.dao.ResultDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestAttemptType;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

/**
 * {@inheritDoc }
 */
public class TestAttemptDao <T extends TestAttempt> extends ResultDao<T> implements ITestAttemptDao<T>{

    protected TestAttemptDao() {
    }

    public void saveAttempt(T attempt)throws DataAccessException{
        getSession().save(attempt);
    }

    /**
     * {@inheritDoc }
     */
    public List<T> getByStudentsAndTests(List<? extends TestEntity>tests,List<? extends AbstractStudent>students, T attempt,boolean estimatedAttemptsOnly)throws DataAccessException{
        if(CollectionUtils.isEmpty(students)||CollectionUtils.isEmpty(tests))
            return null;
        Criteria cr =  getSession().createCriteria(type);
        if(attempt!=null){
            attempt.setWork(null);
            attempt.setStudent(null);
            cr = getCriteriaByParametrizedItem(attempt, cr);
        }
        return cr.add(Property.forName("student").in(students))
                .add(Property.forName("work").in(tests)).list();
    }
}