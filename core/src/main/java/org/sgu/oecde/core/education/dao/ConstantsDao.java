package org.sgu.oecde.core.education.dao;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author ShihovMY
 */
public class ConstantsDao extends HibernateDaoSupport implements IConstantsDao{

    protected ConstantsDao() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map> getConstants(String entityName) throws DataAccessException{
        return getSession().createCriteria(entityName).list();
    }

    public void save(Map c,String entity) throws DataAccessException{
        getSession().save(entity,c);
    }

    public void update(Map c,String entity) throws DataAccessException {
        getSession().update(entity,c);
    }
}
