package org.sgu.oecde.news.dao;

import java.util.List;
import org.hibernate.criterion.Projections;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.news.NewsItem;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;


/**
 * {@inheritDoc }
 */
public class NewsDAO extends BasicDao<NewsItem> implements INewsDao{

    private NewsDAO() {
        super(NewsItem.class);
    }

    /**
     * {@inheritDoc }
     */
    public List<NewsItem> getNews(int beginIndex, int endIndex)  throws DataAccessException {
        if (beginIndex < 0 || endIndex <= 0 || beginIndex > endIndex)
            throw new IllegalArgumentException("Неположительные аргументы");
        return getSession().createCriteria(type).setFirstResult(beginIndex).setMaxResults(endIndex).list();
    }

    /**
     * {@inheritDoc }
     */
    public int getNewsCount() throws DataAccessException  {
        List<Integer> list =  getSession().createCriteria(type).setProjection(Projections.rowCount()).list();
        return !CollectionUtils.isEmpty(list)?list.get(0):0;
    }

    /**
     * {@inheritDoc }
     */
    public void save(NewsItem item) throws DataAccessException {
        getSession().saveOrUpdate(item);
    }
}
