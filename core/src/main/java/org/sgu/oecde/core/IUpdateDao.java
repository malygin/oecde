package org.sgu.oecde.core;

import org.springframework.dao.DataAccessException;

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
     * @param item сущность, которая будет изменена в бд посредством {@link org.hibernate.Session#update}
     * @throws DataAccessException
     */
    public void update(T item) throws DataAccessException;
}
