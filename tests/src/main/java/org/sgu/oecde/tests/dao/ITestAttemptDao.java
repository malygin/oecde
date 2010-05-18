/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface ITestAttemptDao<T extends TestAttempt> extends IBasicDao<T>{
    @Transactional
    public void saveAttempt(T attempt)throws DataAccessException;

    public List<T> getByStudentsAndTests(List<? extends TestEntity>tests,List<? extends AbstractStudent>students, T attempt,boolean allEstimatedAttempts)throws DataAccessException;

    public List<T>getByStudentsAndCurriculums(List<? extends Curriculum>curriculums,List<? extends AbstractStudent>students, T attempt,boolean allEstimatedAttempts)throws DataAccessException;

    public List<T> getByExampleWithType(T attempt,boolean allEstimatedAttempts)throws DataAccessException;
}
