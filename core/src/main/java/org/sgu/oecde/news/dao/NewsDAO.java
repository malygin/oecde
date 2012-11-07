package org.sgu.oecde.news.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.exception.DataException;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.util.LangEnum;
import org.sgu.oecde.news.NewTypeEnum;
import org.sgu.oecde.news.NewsItem;
import org.sgu.oecde.news.NewsTag;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 17.08.2010
 */
@Repository("newsDao")
public class NewsDAO extends BasicDao<NewsItem> implements INewsDao{


    public NewsDAO() {
       super(NewsItem.class);
    }

    private static final long serialVersionUID = 160L;

   
    public List<NewsItem> getNewsForStudent(int messageOnPage, int numPage,  LangEnum lang)  throws DataAccessException {
       if (messageOnPage < 0 || numPage <= 0 ) throw new IllegalArgumentException("Неположительные аргументы");
        return getSession().createCriteria(type)
                .add(Restrictions.in("newstype", new NewTypeEnum[] { NewTypeEnum.forStudent,NewTypeEnum.forStudentAndTeacher, NewTypeEnum.forAll}))              
                .add(Restrictions.eq("lang", lang))
                .addOrder(Order.desc("time"))
                .add(Restrictions.isEmpty("tags"))
                .setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).setCacheable(true).list();
    }
    
    public List<NewsItem> getNewsForTeacher(int messageOnPage, int numPage,  LangEnum lang)  throws DataAccessException {
       if (messageOnPage < 0 || numPage <= 0 ) throw new IllegalArgumentException("Неположительные аргументы");
        return getSession().createCriteria(type)
                .add(Restrictions.in("newstype", new NewTypeEnum[] { NewTypeEnum.forTeacher,NewTypeEnum.forStudentAndTeacher, NewTypeEnum.forAll}))              
                .add(Restrictions.eq("lang", lang))
                 .add(Restrictions.isEmpty("tags"))
                .addOrder(Order.desc("time")).setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).setCacheable(true).list();
    }
   
    public List<NewsItem> getNews(int messageOnPage, int numPage,  LangEnum lang)  throws DataAccessException {
       if (messageOnPage < 0 || numPage <= 0 ) throw new IllegalArgumentException("Неположительные аргументы");
        return getSession().createCriteria(type)
                .add(Restrictions.eq("lang", lang))
                .add(Restrictions.isEmpty("tags"))
                .addOrder(Order.desc("time")).setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).setCacheable(true).list();
    }

    public int getNewsStudentCount(LangEnum lang) throws DataAccessException  {
        List<Long> list =  getSession().createCriteria(type)
                .add(Restrictions.in("newstype", new NewTypeEnum[] { NewTypeEnum.forStudent,NewTypeEnum.forStudentAndTeacher, NewTypeEnum.forAll}))              
                .add(Restrictions.eq("lang", lang))
                .add(Restrictions.isEmpty("tags"))
                .setProjection(Projections.rowCount()).setCacheable(true).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }
     public int getNewsTeacherCount(LangEnum lang) throws DataAccessException  {
        List<Long> list =  getSession().createCriteria(type)
                .add(Restrictions.in("newstype", new NewTypeEnum[] { NewTypeEnum.forTeacher,NewTypeEnum.forStudentAndTeacher, NewTypeEnum.forAll}))              
                .add(Restrictions.eq("lang", lang))
                .add(Restrictions.isEmpty("tags"))
                .setProjection(Projections.rowCount()).setCacheable(true).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }
    public int getNewsCount(LangEnum lang) throws DataAccessException  {
        List<Long> list =  getSession().createCriteria(type)
                .add(Restrictions.eq("lang", lang))
                .add(Restrictions.isEmpty("tags"))
                .setProjection(Projections.rowCount()).setCacheable(true).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }

  
    @Transactional
    @Override
    public Long save(NewsItem item) throws DataAccessException {
       Long id= (Long) getSession().save(item);
       // System.out.println("id "+id);
        //getSession().saveOrUpdate(item);
       return id;
    }
    
    @Transactional
    @Override
    public Long saveBlog(NewsItem item) throws DataAccessException {
       NewsTag tag=new NewsTag("blog");
       tag.setId(new Long(1));
       item.addTag(tag);
       Long id= (Long) getSession().save(item);
       // System.out.println("id "+id);
        //getSession().saveOrUpdate(item);
       return id;
    }
    @Transactional
    @Override
    public void delete(NewsItem id) throws DataAccessException {
        getSession().delete(id);
    }
    
    @Transactional
    @Override
    public void update(NewsItem item) throws DataAccessException {
        getSession().merge(item);
    }

    @Override
    public List<NewsItem> getBlogs(int messageOnPage, int numPage, LangEnum lang) throws DataAccessException {
        if (messageOnPage < 0 || numPage <= 0 ) throw new IllegalArgumentException("Неположительные аргументы");
        return getSession().createCriteria(type).setResultTransformer(Criteria.ROOT_ENTITY)
                .add(Restrictions.eq("lang", lang))
                .addOrder(Order.desc("time"))
                .createCriteria("tags", Criteria.INNER_JOIN)
                    .add(Restrictions.in("name", new String[]{"blog"}))
                .setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).setCacheable(true).list();
     }
        @Override
    public List<NewsItem> getBlogsByUser(int messageOnPage, int numPage, LangEnum lang, Long author) throws DataAccessException {
        if (messageOnPage < 0 || numPage <= 0 ) throw new IllegalArgumentException("Неположительные аргументы");
        return getSession().createCriteria(type).setResultTransformer(Criteria.ROOT_ENTITY)
                .add(Restrictions.eq("lang", lang))
                .add(Restrictions.eq("author.id", author))
                .addOrder(Order.desc("time"))
                .createCriteria("tags", Criteria.INNER_JOIN)
                    .add(Restrictions.in("name", new String[]{"blog"}))
                .setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage).setCacheable(true).list();
     }

    @Override
    public int getBlogsCount(LangEnum lang) throws DataException {
        List<Long> list =  getSession().createCriteria(type)
                .add(Restrictions.eq("lang", lang))
                .createCriteria("tags", Criteria.INNER_JOIN)
                    .add(Restrictions.in("name", new String[]{"blog"}))
                .setProjection(Projections.rowCount()).setCacheable(true).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;

    }
       @Override
    public int getBlogsCountByUser(LangEnum lang, Long author) throws DataException {
        List<Long> list =  getSession().createCriteria(type)
                .add(Restrictions.eq("lang", lang))
                .add(Restrictions.eq("author.id", author))
                .createCriteria("tags", Criteria.INNER_JOIN)
                    .add(Restrictions.in("name", new String[]{"blog"}))
                .setProjection(Projections.rowCount()).setCacheable(true).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;

    }

   

}
