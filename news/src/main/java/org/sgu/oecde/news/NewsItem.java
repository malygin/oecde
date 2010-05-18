package org.sgu.oecde.news;

import java.util.HashSet;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.Admin;

/**
 * Created by IntelliJ IDEA.
 * User: basakovvy
 * Date: 10.11.2009
 * Time: 17:01:55
 */
public class NewsItem extends BasicItem{

    private String header;
    private String announcement;
    private String fullText;
    private Admin author;
    private String time;
    private int reviewNumber;
    private String accessType;
    private Set<NewsTag> tags = new HashSet<NewsTag>();
    private int commentNumber;

    public NewsItem(){
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public int getReviewNumber() {
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
        StringBuilder builder = new StringBuilder().append("NewsItem{").append("header='").append(header).append('\'').append(", announcement='").append(announcement).append('\'').append(", fullText='").append(fullText).append('\'').append(", author=").append(author).append(", time=").append(time).append(", reviewNumber='").append(reviewNumber).append('\'').append(", accessType='").append(accessType).append('\'').append('}');
        for (NewsTag tag : tags) {
            builder.append("\n").append(tag);
        }
        return builder.toString();
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public Admin getAuthor() {
        return author;
    }

    public void setAuthor(Admin author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
