package org.sgu.oecde.journal.dao;

import java.util.List;
import org.hibernate.Query;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.journal.EventItem;
import org.sgu.oecde.journal.FilterType;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    private static final long serialVersionUID = 161L;

    /**
     * {@inheritDoc }
     */
    public int getCountOfEvents(FilterType filter) throws DataAccessException {
        Query cr = getSession().createQuery(processFilter(filter).insert(0, "select count(*) ").toString());
        List<Long> list =  cr.list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }

    /**
     * {@inheritDoc }
     */
    public List<EventItem> getEvents(FilterType filter,int pageNumber) throws DataAccessException {
        int beginIndex = filter.getCapacity() * (pageNumber-1) ;
        Query cr = getSession().createQuery(processFilter(filter).append(" order by e.time desc").toString()).setFirstResult(beginIndex).setMaxResults(filter.getCapacity());
        return cr.list();
    }

    /**
     * {@inheritDoc }
     */
    @Transactional
    public void saveEventItem(EventItem evItem)throws DataAccessException {
        getSession().save(evItem);
    }

    private StringBuilder processFilter(FilterType filter) {
        Assert.notNull(filter);
        StringBuilder sb = new StringBuilder("from EventItem e where e.time between '");
        sb.append(filter.getBeginDate()).append("' and '").append(filter.getEndDate()).append("' ");
        filter.addCondition(sb);
        return sb;
    }
}