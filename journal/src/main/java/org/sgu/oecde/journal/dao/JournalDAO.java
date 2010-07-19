package org.sgu.oecde.journal.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.journal.EventItem;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.filter.BaseFilter;
import org.sgu.oecde.journal.filter.StudentFilter;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.springframework.util.CollectionUtils;

/**
 * {@inheritDoc }
 */
@Repository("journalDao")
public class JournalDAO extends BasicDao<EventItem> implements IJournalDao {

    protected JournalDAO() {
        super(EventItem.class);
    }

    /**
     * {@inheritDoc }
     */
    public int getCountOfEvents(BaseFilter filter) throws DataAccessException {
        Criteria cr = getSession().createCriteria(type).setProjection(Projections.rowCount());
        processFilter(filter, cr);
        List<Long> list =  cr.setCacheable(true).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }

    /**
     * {@inheritDoc }
     */
    public List<EventItem> getEvents(BaseFilter filter) throws DataAccessException {
        int beginIndex = filter.getCapacity() * (filter.getPageNumber() - 1) + 1;
        int endIndex = filter.getCapacity() * filter.getPageNumber();
        Criteria cr = getSession().createCriteria(type).setCacheable(true).setFirstResult(beginIndex).setMaxResults(endIndex);
        processFilter(filter, cr);
        return cr.list();
    }

    /**
     * {@inheritDoc }
     */
    public void saveEventItem(EventItem evItem)throws DataAccessException {
        getSession().save(evItem);
    }

    private void processFilter(BaseFilter filter,Criteria cr) {
        Assert.notNull(filter);
        Assert.notNull(cr);
        cr.add(Property.forName("time").between(filter.getBeginDate(), filter.getEndDate()));
        Set<EventType> events = filter.getEvents();
        if (events.size() == 0 && filter instanceof StudentFilter) {
            filter.addEventType(EventType.OWN_MESSAGE);
            filter.addEventType(EventType.NEW_NEWS);
        }
        cr.add(Property.forName("eventType").in(events));
    }
}