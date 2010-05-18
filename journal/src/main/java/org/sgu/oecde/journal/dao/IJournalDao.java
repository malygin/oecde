package org.sgu.oecde.journal.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.journal.EventItem;
import org.sgu.oecde.journal.filter.BaseFilter;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface IJournalDao extends IBasicDao<EventItem>{

    public int getCountOfEvents(BaseFilter filter) throws DataAccessException;

    public List<EventItem> getEvents(BaseFilter filter) throws DataAccessException;

    @Transactional
    public void saveEventItem(EventItem evItem)throws DataAccessException;
}
