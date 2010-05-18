package org.sgu.oecde.tests.dao;

import java.util.List;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */

public interface ITestDao<T extends TestEntity> extends IUpdateDao<T>{
    public  List<T> getByCurriculums(List<? extends Curriculum> curriculums,TestEntity test)throws DataAccessException;

}
