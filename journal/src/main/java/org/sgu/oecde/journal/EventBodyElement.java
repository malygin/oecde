package org.sgu.oecde.journal;

import java.io.Serializable;

/**
 *
 * @author ShihovMY
 */
public class EventBodyElement implements Serializable{
    private long id;
    private String text;
    private String type;

    public static String forumPage;
    public static String testPage;
    public static String studentPage;
    public static String newsPage;
    public static String teacherPage;
    public static String adminPage;
    public static String supervisorPage;
    public static String umkPage;
    public static String curriculumPage;
    public static String disciplinePage;
    public static String groupPage;
    public static String taskPage;
    
    private static final long serialVersionUID = 187L;

    public EventBodyElement(String text) {
        this.text = text;
        type = null;
    }

    public EventBodyElement(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public EventBodyElement(long id, String text, String type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("text: ").append(text).append("; ");
        if(type!=null){
            sb.append("href: ").append("id").append("; type: ").append(type);
        }
        return sb.toString();
    }
}