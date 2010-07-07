package org.sgu.oecde.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.news.dao.INewsDao;
import org.sgu.oecde.news.dao.NewsDAO;
import org.springframework.test.context.ContextConfiguration;


/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml"})
public class AppTest extends BasicTest{

    @Ignore
    @Test
    public void newsAdd111(){
        String fullText = "qweeeeeeeee qwe      qwe qw aoksf[glsdkfn[ gofg ";
        String header = "sdf ksdjfgkjdfk gjdf g   sdf gfgf ";
        String announcement = " ssfdg df  dfkjngdk jfpgsjdf ";
        NewsItem item = new NewsItem();
        item.setAnnouncement(announcement);
        item.setFullText(fullText);
        item.setHeader(header);
        setDao("adminDao");
        Admin author = this.<Admin>getItem(1L);
        item.setAuthor(author);
        setDao("newsDao");
        this.<INewsDao>getDao().save(item);
    }

//    @Ignore
    @Test
    public void listNews1() {
        int pageNumber = 1;
        int newsPerPage = 10;
        int beginIndex = (pageNumber - 1) * newsPerPage;
        int endIndex = (pageNumber) * newsPerPage;
        setDao("newsDao");
        List<NewsItem> news = this.<INewsDao>getDao().getNews(beginIndex, endIndex);
        int newsCount = this.<INewsDao>getDao().getNewsCount();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("news", news);
        if (newsCount % newsPerPage == 0) {
            model.put("pageCount", newsCount / newsPerPage);
        } else {
            model.put("pageCount", newsCount / newsPerPage + 1);
        }
        model.put("pN", pageNumber);
        model.put("currentPage", pageNumber);
        model.put("newsPerPage", newsPerPage);
        model.put("count", newsPerPage);
        System.out.println(model);
    }

    @Ignore
    @Test
    public void fullText111() {
        Long id = 1L;
        int pageNumber = 1;
        int newsPerPage = 10;
        Map<String, Object> model = new HashMap<String, Object>();
        setDao("newsDao");
        NewsItem item = this.<NewsItem>getItem(id);
        model.put("item", item);
        model.put("pN", pageNumber);
        model.put("count", newsPerPage);
        System.out.println(model);
        item.setReviewNumber(item.getReviewNumber() + 1);
        this.<INewsDao>getDao().save(item);
    }
}
