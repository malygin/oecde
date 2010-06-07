package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.education.CalendarConstants;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author ShihovMY
 */
public class CurrentSemesterDao extends HibernateDaoSupport implements ICurrentSemesterDao{

    protected CurrentSemesterDao() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CalendarConstants> getCurrentSemester() throws DataAccessException{
        return getSession().createCriteria(CalendarConstants.class).list();
    }

    public void save(CalendarConstants c,String entity) throws DataAccessException{
        getSession().save(entity,c);
    }

    public void update(CalendarConstants c,String entity) throws DataAccessException {
        getSession().update(entity,c);
    }
}
