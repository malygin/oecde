package org.sgu.oecde.web.jsfbeans.news;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Size;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
import org.sgu.oecde.news.NewsItem;
import org.sgu.oecde.news.dao.INewsDao;
import org.springframework.security.access.annotation.Secured;

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

    @ManagedProperty(value="#{journalService}")
    private JournalService journalService;

     private List<NewsItem> news;
     private int countNews;

     private NewsItem currentNewItem;
     private String currentNewId;
     private int NewIdDelete;

     private boolean renderDeleteSuccess=false;
     private boolean renderAddSuccess=false;

     //Сообщений на странице
     private int newsOnPage=3;
     private int currentPage=1;

     //поля для добавления новостей
     private String header;

     @Size(max=250,message="слишком длинный анонс")
     private String anons;
     private String fulltext;




    public NewsBean() {
    }

    @Secured("ROLE_ADMIN")
    public void delete (){
        NewsItem n=new NewsItem();
        n.setId(new Long(NewIdDelete));
        newsDao.delete(n);
        renderDeleteSuccess=true;
    }

    @Secured("ROLE_ADMIN")
    public void save(){
        NewsItem n=new NewsItem();
       n.setFullText(fulltext);
       n.setAnnouncement(anons);
       n.setHeader(header);
         n.setTime(DateConverter.currentDate());
         renderAddSuccess=true;
        Long id=newsDao.save(n);
        n.setId(id);
       // journalService.save(EventType.NEW_NEWS, SecurityContextHandler.getUser(), n);

    }

    @Secured("ROLE_ADMIN")
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

    public void  setCurrentNewId(String id){
        if (!id.equals("0")){
              this.currentNewId=id;
              currentNewItem= newsDao.getById(new Long(this.currentNewId));
              currentNewItem.setReviewNumber(currentNewItem.getReviewNumber()+1);
              newsDao.update(currentNewItem);
              journalService.save(EventType.NEWS_VIEW, SecurityContextHandler.getUser(), currentNewItem);
        }
    }

    public String getCurrentNewId() {
        return currentNewId;
    }

    public void setNewsDao(INewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public JournalService getJournalService() {
        return journalService;
    }

    public INewsDao getNewsDao() {
        return newsDao;
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

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }
}
