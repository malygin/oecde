package org.sgu.oecde.news.dao;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.news.NewsItem;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 * {@inheritDoc }
 */
@Repository("newsDao")
public class NewsDAO extends BasicDao<NewsItem> implements INewsDao{

    public NewsDAO() {
        super(NewsItem.class);
    }

    /**
     * {@inheritDoc }
     */
    public List<NewsItem> getNews(int messageOnPage, int numPage)  throws DataAccessException {
//        if (beginIndex < 0 || endIndex <= 0 || beginIndex > endIndex)
//            throw new IllegalArgumentException("Неположительные аргументы");
        return getSession().createCriteria(type).addOrder(Order.desc("time")).setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).setCacheable(true).list();
    }

    /**
     * {@inheritDoc }
     */
    public int getNewsCount() throws DataAccessException  {
        List<Long> list =  getSession().createCriteria(type).setProjection(Projections.rowCount()).setCacheable(true).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }

    /**
     * {@inheritDoc }
     */
    @Transactional
    @Override
    public void save(NewsItem item) throws DataAccessException {
        getSession().saveOrUpdate(item);
    }
    @Transactional
    @Override
    public void delete(NewsItem id) throws DataAccessException {
        getSession().delete(id);
    }
}
