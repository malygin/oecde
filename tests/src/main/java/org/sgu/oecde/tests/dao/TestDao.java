package org.sgu.oecde.tests.dao;

import java.util.List;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.util.HqlConstructor;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */

public class TestDao<T extends TestEntity> extends UpdateDao<T> implements ITestDao<T>{

    public TestDao() {
    }

    public  List<T> getByCurriculums(List<Curriculum> curriculums,TestEntity test)throws DataAccessException{
        return HqlConstructor.makeQuery(getSession(), "distinct t", "from curriculum cr join umk u join test t", null, "cr in (:c)", "t=:ts", "t")
                .setParameterList("s", curriculums).setParameter("ts", test).list();
    }
}