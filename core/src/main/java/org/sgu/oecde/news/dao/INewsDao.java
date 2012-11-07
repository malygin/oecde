package org.sgu.oecde.news.dao;

import java.util.List;
import org.hibernate.exception.DataException;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.util.LangEnum;
import org.sgu.oecde.news.NewsItem;
import org.springframework.dao.DataAccessException;

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
    public List<NewsItem> getNewsForStudent(int messageOnPage, int numPage,  LangEnum lang)  throws DataAccessException ;
    public List<NewsItem> getNewsForTeacher(int messageOnPage, int numPage,  LangEnum lang)  throws DataAccessException ;
    public List<NewsItem> getNews(int messageOnPage, int numPage,  LangEnum lang)  throws DataAccessException ;
    public List<NewsItem> getBlogs(int messageOnPage, int numPage,  LangEnum lang)  throws DataAccessException ;
    public List<NewsItem> getBlogsByUser(int messageOnPage, int numPage, LangEnum lang, Long author) throws DataAccessException;

    /**
     *
     * @return количество новостей
     * @throws DataAccessException
     */
    public int getBlogsCount(LangEnum lang) throws DataException ;
    public int getBlogsCountByUser(LangEnum lang, Long author) throws DataException ;

    public int getNewsCount(LangEnum lang)  throws DataAccessException ;
    public int getNewsStudentCount(LangEnum lang)  throws DataAccessException ;
    public int getNewsTeacherCount(LangEnum lang)  throws DataAccessException ;

    /**
     * сохраняет или изменяет новость
     * @param item новость
     * @throws DataAccessException
     */
 
    public Long save(NewsItem item) throws DataAccessException;
    public Long saveBlog(NewsItem item) throws DataAccessException ;

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
