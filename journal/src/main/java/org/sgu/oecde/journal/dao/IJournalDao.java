package org.sgu.oecde.journal.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.journal.EventItem;
import org.sgu.oecde.journal.filter.BaseFilter;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

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
    public int getCountOfEvents(BaseFilter filter) throws DataAccessException;

    /**
     * возвращает события
     * @param filter
     * @return
     * @throws DataAccessException
     */
    public List<EventItem> getEvents(BaseFilter filter) throws DataAccessException;

    /**
     * сохраняет полученное событие
     * @param evItem событие
     * @throws DataAccessException
     */
    @Transactional
    public void saveEventItem(EventItem evItem)throws DataAccessException;
}
