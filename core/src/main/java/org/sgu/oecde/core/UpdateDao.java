package org.sgu.oecde.core;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *{@inheritDoc}
 */
public class UpdateDao<T extends BasicItem> extends BasicDao<T> implements IUpdateDao<T>{

    protected UpdateDao() {
    }
    
    protected UpdateDao(Class<T> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     * @see org.hibernate.Session#update(java.lang.Object) update
     */
    @Override
    @Transactional
    public void update(T item) throws DataAccessException {
        getSession().update(item);
    }
}
