package org.sgu.oecde.core;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * интерфейс для UpdateDao. наследуется от IBasicDao. 
 * @author ShihovMY
 * @param <T> наследник {@code org.sgu.oecde.core.BasicItem}
 * @see org.sgu.oecde.core.BasicDao
 * @see org.sgu.oecde.core.BasicItem
 */
public interface IUpdateDao<T extends BasicItem> extends IBasicDao<T>  {

    /**
     *
     * @param item сущность, которая будет изменена в бд посредством {@code org.hibernate.Session.update}
     * @throws DataAccessException
     */
    @Transactional
    public void update(T item) throws DataAccessException;
}
