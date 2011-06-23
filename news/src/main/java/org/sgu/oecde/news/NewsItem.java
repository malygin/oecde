package org.sgu.oecde.news;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.Admin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import org.sgu.oecde.core.util.LangEnum;
/**
 *
 * @author ShihovMY
 */
public class NewsItem extends BasicItem{

    /**
     * заголовок
     */
    private String header;
    /**
     * тип новости - уровень доступа к ней
     */
    private NewTypeEnum newstype;
    
    private LangEnum lang= LangEnum.ru; 
    /**
     * анонс
     */
    @Size(max=250,message="слишком длинный анонс")
    private String announcement;
    /**
     * полная новость
     */
    private String fullText;
    /**
     * автор
     */
    private Admin author;
    /**
     * дата публикации новости
     */
    private String time;
    /**
     * количество просмотров
     */
    private Integer reviewNumber=0;
   /**
     * количество комментариев
     */
    private Integer commentNumber=0;
    /**
     * доступ. не используется
     */
    private String accessType;
    /**
     * теги. не используются
     */
    private Set<NewsTag> tags;
    private static final long serialVersionUID = 83L;

    public NewsItem(){
    }

    /**
     * заголовок
     * @param header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     *
     * @return заголовок
     */
    public String getHeader() {
        return header;
    }

    /**
     * анонс
     * @param announcement
     */
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    /**
     *
     * @return анонс
     */
    public String getAnnouncement() {
        return announcement;
    }

    /**
     * новость
     * @param fullText
     */
    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    /**
     *
     * @return новость
     */
    public String getFullText() {
        return fullText;
    }

    /**
     * просмотров
     * @param reviewNumber
     */
    public void setReviewNumber(Integer reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    /**
     *
     * @return просмотров
     */
    public Integer getReviewNumber() {
        return reviewNumber;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getAccessType() {
        return accessType;
    }

    public Set<NewsTag> getTags() {
        return tags;
    }

    public void setTags(Set<NewsTag> tags) {
        this.tags = tags;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("заголовок: ").append(header).append("; ");
        sb.append("анонс: ").append(announcement).append("; ");
        return sb.toString();
    }

    /**
     *
     * @return автор
     */
    public Admin getAuthor() {
        return author;
    }

    /**
     * автор
     * @param author
     */
    public void setAuthor(Admin author) {
        this.author = author;
    }

    /**
     *
     * @return дата публикации
     */
    public String getTime() {
        return time;
    }

    /**
     * дата публикации
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public NewTypeEnum getNewstype() {
        return newstype;
    }

    public void setNewstype(NewTypeEnum newstype) {
        this.newstype = newstype;
    }

    public LangEnum getLang() {
        return lang;
    }

    public void setLang(LangEnum lang) {
        this.lang = lang;
    }
    
    
    
}
