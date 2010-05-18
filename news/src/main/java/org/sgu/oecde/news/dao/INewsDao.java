package org.sgu.oecde.news.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.news.NewsItem;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface INewsDao extends IBasicDao<NewsItem>{
    public List<NewsItem> getNews(int beginIndex, int endIndex)  throws DataAccessException ;

    public int getNewsCount()  throws DataAccessException ;

    @Transactional
    public void save(NewsItem item) throws DataAccessException;
}
