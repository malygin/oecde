package org.sgu.oecde.core.education.dao;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc}
 */
@Repository
public class ConstantsDao extends HibernateDaoSupport implements IConstantsDao{

    protected ConstantsDao() {
    }

    private static final long serialVersionUID = 130L;

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Map> getConstants(String entityName) throws DataAccessException{
        return getSession().createCriteria(entityName).setCacheable(true).list();
    }

    /**
     * {@inheritDoc }
     * @see org.hibernate.Session#save(java.lang.Object) save
     */
    @Transactional
    public void save(Map c,String entity) throws DataAccessException{
        getSession().save(entity,c);
    }

    /**
     * {@inheritDoc }     *
     * @see org.hibernate.Session#update(java.lang.Object) update
     */
    @Transactional
    public void update(Map c,String entity) throws DataAccessException {
        getSession().update(entity,c);
    }
}
