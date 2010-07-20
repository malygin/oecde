
package org.sgu.oecde.web.jsfbeans.messages.news;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.journal.Journal;
import org.sgu.oecde.news.NewsItem;
import org.sgu.oecde.news.dao.INewsDao;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 19.07.2010
 * 
 */
@ManagedBean(name="NewsBean")
@ViewScoped
public class NewsBean {
     @ManagedProperty(value="#{newsDao}")
     private INewsDao newsDao;
      @ManagedProperty(value="#{journalServise}")
      private Journal journal;

     private List<NewsItem> news;
     private int countNews;

     private NewsItem currentNewItem;
     private int currentNewId;
     private int NewIdDelete;

     private boolean renderDeleteSuccess=false;
     private boolean renderAddSuccess=false;
     //Сообщений на странице
     private int newsOnPage=3;
     private int currentPage=1;
    //поля для добавления новостей
    private String header;
    private String anons;
    private String fulltext;




    public NewsBean() {
    }

    public void delete (){
        NewsItem n=new NewsItem();
        n.setId(new Long(NewIdDelete));
        newsDao.delete(n);
        renderDeleteSuccess=true;
    }

    public void save(){
        NewsItem n=new NewsItem();
       n.setFullText(fulltext);
       n.setAnnouncement(anons);
       n.setHeader(header);
         n.setTime(DateConverter.currentDate());
         renderAddSuccess=true;
        Long id=newsDao.save(n);
        n.setId(id);
        journal.logNewNews(n, SecurityContextHandler.getUser());
      
    }
    public void edit(){
         renderAddSuccess=true;
         newsDao.update(currentNewItem);
    }
    public List<NewsItem> getNews(){
        if (news==null) news=newsDao.getNews(newsOnPage, currentPage);
         return news;
    }
    public int getCountNews(){
        return newsDao.getNewsCount();
    }
   

    public void  setCurrentNewId(int id){
        if (id!=0){
              this.currentNewId=id;
              currentNewItem= newsDao.getById(new Long(this.currentNewId));            
              currentNewItem.setReviewNumber(currentNewItem.getReviewNumber()+1);
              newsDao.update(currentNewItem);
              journal.logViewNews(currentNewItem, SecurityContextHandler.getUser());
        }
    }

    public int getCurrentNewId() {
        return currentNewId;
    }    

  
    public INewsDao getNewsDao() {
        return newsDao;
    }

    public void setNewsDao(INewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNewsOnPage() {
        return newsOnPage;
    }

    public int getNewIdDelete() {
        return NewIdDelete;
    }

    public void setNewIdDelete(int NewIdDelete) {
        this.NewIdDelete = NewIdDelete;
    }

    public boolean isRenderDeleteSuccess() {
        return renderDeleteSuccess;
    }

    public void setRenderDeleteSuccess(boolean renderDeleteSuccess) {
        this.renderDeleteSuccess = renderDeleteSuccess;
    }

    public String getAnons() {
         if (currentNewItem!=null) anons=currentNewItem.getAnnouncement();
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFulltext() {
          if (currentNewItem!=null) fulltext=currentNewItem.getFullText();
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public String getHeader() {
        if (currentNewItem!=null) header=currentNewItem.getHeader();
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }



    public boolean isRenderAddSuccess() {
        return renderAddSuccess;
    }

    public void setRenderAddSuccess(boolean renderAddSuccess) {
        this.renderAddSuccess = renderAddSuccess;
    }

    public NewsItem getCurrentNewItem() {
        return currentNewItem;
    }

    public void setCurrentNewItem(NewsItem currentNewItem) {
        this.currentNewItem = currentNewItem;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }



}
