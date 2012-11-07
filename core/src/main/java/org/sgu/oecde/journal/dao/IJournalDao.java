package org.sgu.oecde.journal.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.journal.EventItem;
import org.sgu.oecde.journal.FilterType;
import org.springframework.dao.DataAccessException;

/**
 * дао событий
 * @author ShihovMY
 */
public interface IJournalDao extends IBasicDao<EventItem>{

    /**
     * подсчитывает количество событие
     * @param filter
     * @return
     * @throws DataAccessException
     */
    public int getCountOfEvents(FilterType filter) throws DataAccessException;

    /**
     * возвращает события
     * @param filter
     * @return
     * @throws DataAccessException
     */
    public List<EventItem> getEvents(FilterType filter, int papeNumber) throws DataAccessException;

    /**
     * сохраняет полученное событие
     * @param evItem событие
     * @throws DataAccessException
     */
    public void saveEventItem(EventItem evItem)throws DataAccessException;
}
