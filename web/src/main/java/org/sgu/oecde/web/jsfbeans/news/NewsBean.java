package org.sgu.oecde.web.jsfbeans.news;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.validation.constraints.Size;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.LangEnum;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
import org.sgu.oecde.news.NewTypeEnum;
import org.sgu.oecde.news.NewsItem;
import org.sgu.oecde.news.dao.INewsDao;
import org.sgu.oecde.web.jsfbeans.UserSessionBean;
import org.springframework.security.access.annotation.Secured;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 19.07.2010
 *
 */
@ManagedBean(name = "NewsBean")
@ViewScoped
public class NewsBean implements Serializable {

    @ManagedProperty(value = "#{newsDao}")
    private INewsDao newsDao;
    @ManagedProperty(value = "#{journalService}")
    private JournalService journalService;
    @ManagedProperty(value = "#{userSessionBean}")
    private UserSessionBean userSessionBean;
    private List<NewsItem> news;
    private int countNews = 0;
    private NewsItem currentNewItem;
    private String currentNewId;
    private int NewIdDelete;
    private boolean renderDeleteSuccess = false;
    private boolean renderAddSuccess = false;
    //Сообщений на странице
    private int newsOnPage = 20;
    private int currentPage = 1;
    //поля для добавления новостей
    private String header;
    @Size(max = 250, message = "слишком длинный анонс")
    private String anons;
    private String fulltext;
    private NewTypeEnum type;

    public NewsBean() {
    }

    @Secured("ROLE_ADMIN")
    public void delete() {
        NewsItem n = new NewsItem();
        n.setId(new Long(NewIdDelete));
        newsDao.delete(n);
        renderDeleteSuccess = true;
    }

    @Secured("ROLE_ADMIN")
    public void save() {
      
        NewsItem n = new NewsItem();
        n.setFullText(fulltext);
        n.setAnnouncement(anons);
        n.setHeader(header);
        n.setTime(DateConverter.currentDate());
        n.setAuthor((Admin) SecurityContextHandler.getUser());
        n.setNewstype(type);
        n.setLang(LangEnum.ru);
        renderAddSuccess = true;
        if (fulltext.equals("")){
            journalService.save(EventType.OWN_MESSAGE, SecurityContextHandler.getUser(), n);
            return;
        }      
        Long id = newsDao.save(n);
        n.setId(id);
        if (type!=NewTypeEnum.forTeacher)
        journalService.save(EventType.NEW_NEWS, SecurityContextHandler.getUser(), n);

    }

    @Secured("ROLE_ADMIN")
    public void edit() {
        renderAddSuccess = true;
        newsDao.update(currentNewItem);
    }

    public List<NewsItem> getNews() {
        if (news == null) {
            switch (UserType.toType(SecurityContextHandler.getUser())) {
                case STUDENT:
                    news = newsDao.getNewsForStudent(newsOnPage, currentPage, LangEnum.ru);
                    break;
                case SUPERVISOR:
                    news = newsDao.getNewsForStudent(newsOnPage, currentPage, LangEnum.ru);
                    break;
                case TEACHER:
                    news = newsDao.getNewsForTeacher(newsOnPage, currentPage, LangEnum.ru);
                    break;
                case ADMIN:
                    news = newsDao.getNews(newsOnPage, currentPage, LangEnum.ru);
                    break;
            }
        }
        return news;
    }

    public List<NewsItem> getNewsForNumber(int number) {
        if (news == null) {
            newsOnPage = number;
            news = getNews();
        }
        return news;
    }

    public int getCountNews() {
        if (countNews == 0) {
            switch (UserType.toType(SecurityContextHandler.getUser())) {
                case STUDENT:
                    countNews = newsDao.getNewsStudentCount(LangEnum.ru);
                    break;
                case SUPERVISOR:
                    countNews = newsDao.getNewsStudentCount(LangEnum.ru);
                    break;
                case TEACHER:
                    countNews = newsDao.getNewsTeacherCount(LangEnum.ru);
                    break;
                case ADMIN:
                    countNews = newsDao.getNewsCount(LangEnum.ru);
                    break;
            }
        }
        return countNews;
    }

    public void setCurrentNewId(String id) {
        if (!id.equals("0")) {
            this.currentNewId = id;
            currentNewItem = newsDao.getById(new Long(this.currentNewId));
            currentNewItem.setReviewNumber(currentNewItem.getReviewNumber() + 1);

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
        if (currentNewItem != null) {
            anons = currentNewItem.getAnnouncement();
        }
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFulltext() {
        if (currentNewItem != null) {
            fulltext = currentNewItem.getFullText();
        }
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public String getHeader() {
        if (currentNewItem != null) {
            header = currentNewItem.getHeader();
        }
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

    public NewTypeEnum getType() {
        return type;
    }

    public void setType(NewTypeEnum type) {
        this.type = type;
    }

    public SelectItem[] getNewTypeValues() {
        SelectItem[] items = new SelectItem[NewTypeEnum.values().length];
        int i = 0;
        for (NewTypeEnum g : NewTypeEnum.values()) {
            items[i++] = new SelectItem(g, g.getName());
        }
        return items;
    }

    public UserSessionBean getUserSessionBean() {
        return userSessionBean;
    }

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }
}
