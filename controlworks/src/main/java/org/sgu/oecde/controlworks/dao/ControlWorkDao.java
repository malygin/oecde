package org.sgu.oecde.controlworks.dao;

import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.education.dao.ResultDao;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc }
 */
public class ControlWorkDao<T extends ControlWork> extends ResultDao<T> implements IControlWorkDao<T>{

    protected ControlWorkDao(Class<T> type) {
        super(type);
    }

    protected ControlWorkDao(){
        super((Class<T>)ControlWork.class);
    }

    /**
     * {@inheritDoc }
     */
    @Transactional
    public void save(ControlWork work) throws DataAccessException{
        getSession().saveOrUpdate(work);
    }
}
