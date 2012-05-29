package org.sgu.oecde.news;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.education.resource.Image;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.LangEnum;
import org.sgu.oecde.news.dao.INewsDao;
import org.springframework.test.context.ContextConfiguration;


/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml"})
public class AppTest extends BasicTest{

   @Ignore
    @Test
    public void newsAdd(){
        String fullText = "qweeeeeeeee qwe      qwe qw aoksf[glsdkfn[ gofg ";
        String header = "новости от кати";
        String announcement = " ssfdg df  dfkjngdk jfpgsjdf ";
        NewsItem item = new NewsItem();
        item.setTime(DateConverter.currentDate());
        item.setAnnouncement(announcement);
        item.setFullText(fullText);
        item.setHeader(header);
        item.setNewstype(NewTypeEnum.forAll);
        item.setLang(LangEnum.ru);
     
        
        Image im = new Image();
        im.setUrl("inf.png");
        
        item.addImage(im);

        setDao("adminDao");
        Admin author = this.<Admin>getItem(82L);
        System.out.println(author);
        item.setAuthor(author);
        setDao("newsDao");
        this.<INewsDao>getDao().saveBlog(item);
    }

//  @Ignore
    @Test
    public void listNews() {  
         setDao("adminDao");
        Admin author = this.<Admin>getItem(82L);
        setDao("newsDao");
        int blogc=this.<INewsDao>getDao().getBlogsCountByUser(LangEnum.ru, 11L);
       // int newc=this.<INewsDao>getDao().getNewsCount(LangEnum.ru);
       System.out.println("!" + blogc);
//
        List<NewsItem> news = this.<INewsDao>getDao().getBlogsByUser(20, 1, LangEnum.ru,82L);
        for(NewsItem n:news){
           System.out.println(n.getId());
           System.out.println(n.getImages());
        }
       System.out.println(""+news.size());
    }

    @Ignore
    @Test
    public void fullText() {
        Long id = 25L;
        int pageNumber = 1;
        int newsPerPage = 10;
        Map<String, Object> model = new HashMap<String, Object>();
        setDao("newsDao");
        NewsItem item = this.<NewsItem>getItem(id);     
        System.out.println(item);
      //  item.setReviewNumber(item.getReviewNumber() + 1);
      //  this.<INewsDao>getDao().save(item);
    }
    @Ignore
    @Test
    public void delete() {
        Long id = 25L;
          NewsItem item = new NewsItem();
          item.setId(id);
        setDao("newsDao");
        this.<INewsDao>getDao().delete(item);

    }

    @Ignore
    @Test
    public void update() {
      String fullText = "11111111111111111";
        String header = "222222222222222222222222";
        String announcement = " 33333333333333333333";
        NewsItem item = new NewsItem();
        item.setId(26L);
        item.setTime(DateConverter.currentDate());
        item.setAnnouncement(announcement);
        item.setFullText(fullText);
        item.setHeader(header);
        setDao("newsDao");
        this.<INewsDao>getDao().save(item);

    }
    @Ignore
    @Test
    public void plusOne() {
          setDao("newsDao");
        NewsItem item = this.<NewsItem>getItem(27L);
       item.setReviewNumber(item.getReviewNumber()+1);
        this.<INewsDao>getDao().save(item);

    }

    @Ignore
    @Test
    public void getCoutn() {
          setDao("newsDao");
        System.out.println("" +  this.<INewsDao>getDao().getNewsCount(LangEnum.ru));


    }
}
