package org.sgu.oecde.news.dao;


import java.util.*;

import org.hibernate.criterion.Projections;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.news.NewsItem;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * Created by IntelliJ IDEA.
 * User: basakovvy
 * Date: 11.11.2009
 * Time: 12:00:25
 */
public class NewsDAO extends BasicDao<NewsItem> implements INewsDao{

    private NewsDAO() {
        super(NewsItem.class);
    }

    public List<NewsItem> getNews(int beginIndex, int endIndex)  throws DataAccessException {
        if (beginIndex < 0 || endIndex <= 0 || beginIndex > endIndex)
            throw new IllegalArgumentException("Неположительные аргументы");
        return getSession().createCriteria(type).setFirstResult(beginIndex).setMaxResults(endIndex).list();
    }

    public int getNewsCount() throws DataAccessException  {
        List<Integer> list =  getSession().createCriteria(type).setProjection(Projections.rowCount()).list();
        return !CollectionUtils.isEmpty(list)?list.get(0):0;
    }

    public void save(NewsItem item) throws DataAccessException {
        getSession().saveOrUpdate(item);
    }
}
