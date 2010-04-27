package org.sgu.oecde.tests.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public class TestAttemptDao <T extends TestAttempt> extends BasicDao<T> implements ITestAttemptDao<T>{

    public TestAttemptDao() {
    }

    public void saveAttempt(T attempt)throws DataAccessException{
        getSession().save(attempt);
    }

    public List<T> getByStudentsAndCurriculums(List<TestEntity>tests,List<Student>students, T attempt)throws DataAccessException{
        Criteria cr =  getSession().createCriteria(type);
        attempt.setWork(null);
        attempt.setStudent(null);
        cr = getCriteriaByParametrizedItem(attempt, cr);
        return cr.add(Property.forName("test").in(tests))
                .add(Property.forName("student").in(students)).list();
    }
}
