package org.sgu.oecde.tests.dao;

import java.util.List;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * дао по работе с результатами прохождения тестов
 * @author ShihovMY
 * @param <T> extends TestAttempt
 */
public interface ITestAttemptDao<T extends TestAttempt> extends IResultDao<T>{

    /**
     * сохраняет данную попытку
     * @param attempt попытка
     * @throws DataAccessException
     */
    @Transactional
    public void saveAttempt(T attempt)throws DataAccessException;

    /**
     *
     * @param tests тесты
     * @param students студенты
     * @param attempt образец попытки
     * @param allEstimatedAttempts
     * @return
     * @throws DataAccessException
     */
    public List<T> getByStudentsAndTests(List<? extends TestEntity>tests,List<? extends AbstractStudent>students, T attempt,boolean allEstimatedAttempts)throws DataAccessException;
}
