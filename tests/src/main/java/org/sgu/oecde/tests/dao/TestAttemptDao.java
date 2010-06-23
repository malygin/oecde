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
    public List<T> getByStudentsAndTests(List<? extends TestEntity>tests,List<? extends AbstractStudent>students, T attempt,boolean allEstimatedAttempts)throws DataAccessException{
        attempt.setWork(null);
        Criteria cr =  getSession().createCriteria(type);
        attempt.setStudent(null);
        if(allEstimatedAttempts)
            attempt.setType(null);
        cr = getCriteriaByParametrizedItem(attempt, cr);
        if(allEstimatedAttempts)
            cr.add(Property.forName("type").ne(TestAttemptType.trial));
        return criteriaWithStudents(cr, allEstimatedAttempts, attempt, students)
                .add(Property.forName("student").in(students))
                .add(Property.forName("work").in(tests)).list();
    }

    /**
     * {@inheritDoc }
     */
    public List<T> getByExampleWithType(T attempt,boolean allEstimatedAttempts)throws DataAccessException{
        Criteria cr =  getSession().createCriteria(type);
        if(allEstimatedAttempts)
            attempt.setType(null);
        cr = getCriteriaByParametrizedItem(attempt, cr);
        if(allEstimatedAttempts)
            cr.add(Property.forName("type").ne(TestAttemptType.trial));
        return cr.list();
    }

    private Criteria criteriaWithStudents(Criteria cr,boolean allEstimatedAttempts,T attempt, List<? extends AbstractStudent> students){
        attempt.setStudent(null);
        if(allEstimatedAttempts)
            attempt.setType(null);
        cr = getCriteriaByParametrizedItem(attempt, cr);
        if(allEstimatedAttempts)
            cr.add(Property.forName("type").ne(TestAttemptType.trial));
        return cr.add(Property.forName("student").in(students));
    }
}