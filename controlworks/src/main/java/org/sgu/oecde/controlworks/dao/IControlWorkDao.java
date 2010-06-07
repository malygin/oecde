package org.sgu.oecde.controlworks.dao;

import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface IControlWorkDao<T extends ControlWork> extends IResultDao<T>{

    @Transactional
    public void save(ControlWork work) throws DataAccessException;
}
