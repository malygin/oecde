package org.sgu.oecde.core.education.dao;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * {@inheritDoc}
 */
public class ConstantsDao extends HibernateDaoSupport implements IConstantsDao{

    protected ConstantsDao() {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Map> getConstants(String entityName) throws DataAccessException{
        return getSession().createCriteria(entityName).list();
    }

    /**
     * {@inheritDoc }
     * @see org.hibernate.Session#save(java.lang.Object) save
     */
    public void save(Map c,String entity) throws DataAccessException{
        getSession().save(entity,c);
    }

    /**
     * {@inheritDoc }     *
     * @see org.hibernate.Session#update(java.lang.Object) update
     */
    public void update(Map c,String entity) throws DataAccessException {
        getSession().update(entity,c);
    }
}
