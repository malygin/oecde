package org.sgu.oecde.core;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public class UpdateDao<T extends BasicItem> extends BasicDao<T> implements IUpdateDao<T>{

    protected UpdateDao() {
    }
    
    protected UpdateDao(Class<T> type) {
        super(type);
    }

    @Override
    public void update(T item) throws DataAccessException {
        getSession().update(item);
    }
}
