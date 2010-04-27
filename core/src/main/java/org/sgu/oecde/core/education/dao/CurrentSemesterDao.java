/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.education.CalendarConstants;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@Transactional
public class CurrentSemesterDao extends HibernateDaoSupport implements ICurrentSemesterDao{

    public CurrentSemesterDao() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public CalendarConstants getCurrentSemester() throws DataAccessException{
        List<CalendarConstants> l = getSession()
                .createCriteria(CalendarConstants.class).list();
        if(!CollectionUtils.isEmpty(l))
            return l.iterator().next();
        else
            return null;
    }
}
