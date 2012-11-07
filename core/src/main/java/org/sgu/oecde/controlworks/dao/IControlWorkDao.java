package org.sgu.oecde.controlworks.dao;

import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.springframework.dao.DataAccessException;

/**
 * дао кр
 * @author ShihovMY
 */
public interface IControlWorkDao<T extends ControlWork> extends IResultDao<T>{

    /**
     * сохраняет/изменяет кр
     * @param work
     * @throws DataAccessException
     */
    public void save(ControlWork work) throws DataAccessException;
}
