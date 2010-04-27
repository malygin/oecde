/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
@Transactional
public interface ITestAttemptDao<T extends TestAttempt> extends IBasicDao<T>{
    public void saveAttempt(T attempt)throws DataAccessException;

    public List<T> getByStudentsAndCurriculums(List<TestEntity>tests,List<Student>students, T attempt)throws DataAccessException;

}
