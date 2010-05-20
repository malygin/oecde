package org.sgu.oecde.tests.dao;

import java.util.List;
import org.hibernate.Query;
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

    protected TestDao() {
        super((Class<T>)TestEntity.class);
    }

    public  List<T> getByCurriculums(List<? extends Curriculum> curriculums,TestEntity test)throws DataAccessException{
        String testEquality = null;
        if(test!=null)
            testEquality = "t=:ts";
        Query q = HqlConstructor.makeQuery(getSession(), "distinct u.resources ", "from Curriculum cr, Umk u",null, "cr.umk=u and cr in (:c) ", testEquality, null)
                .setParameterList("c", curriculums);
        if(test!=null)
            q.setParameter("ts", test);
        return q.list();
    }
}