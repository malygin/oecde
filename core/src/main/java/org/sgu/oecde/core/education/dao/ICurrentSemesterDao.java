package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.education.CalendarConstants;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface ICurrentSemesterDao {
    public List<CalendarConstants> getCurrentSemester() throws DataAccessException;

    @Transactional
    public void save(CalendarConstants c,String entity) throws DataAccessException;

    @Transactional
    public void update(CalendarConstants c,String entity) throws DataAccessException;
}
