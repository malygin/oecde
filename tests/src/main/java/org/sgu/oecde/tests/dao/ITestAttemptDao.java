package org.sgu.oecde.tests.dao;

import java.util.List;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface ITestAttemptDao<T extends TestAttempt> extends IResultDao<T>{
    @Transactional
    public void saveAttempt(T attempt)throws DataAccessException;

    public List<T> getByStudentsAndTests(List<? extends TestEntity>tests,List<? extends AbstractStudent>students, T attempt,boolean allEstimatedAttempts)throws DataAccessException;

    public List<T> getByExampleWithType(T attempt,boolean allEstimatedAttempts)throws DataAccessException;
}
