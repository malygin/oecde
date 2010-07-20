package org.sgu.oecde.news.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.news.NewsItem;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * новостное дао
 * @author ShihovMY
 */

public interface INewsDao extends IBasicDao<NewsItem>{
    /**
     * возвращает лист новостей с {@code beginIndex} no {@code endIndex}
     * @param beginIndex с какого
     * @param endIndex по какую
     * @return новости
     * @throws DataAccessException
     */
    public List<NewsItem> getNews(int messageOnPage, int numPage)  throws DataAccessException ;

    /**
     *
     * @return количество новостей
     * @throws DataAccessException
     */
    public int getNewsCount()  throws DataAccessException ;

    /**
     * сохраняет или изменяет новость
     * @param item новость
     * @throws DataAccessException
     */
 
    public Long save(NewsItem item) throws DataAccessException;
    /** 
     * удаляет новость
     * @param id
     * @throws DataAccessException
     */
    public void delete(NewsItem id) throws DataAccessException;
    /**
     * обновляет новость
     * @param item
     * @throws DataAccessException
     */
    public void update(NewsItem item) throws DataAccessException;
}
