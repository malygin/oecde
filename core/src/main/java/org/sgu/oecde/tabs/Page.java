package org.sgu.oecde.tabs;

import java.util.List;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.util.LangEnum;

/**
 *
 * @author ShihovMY
 */
public class Page extends BasicItem implements Comparable<Page> {
    private String title;
    private String text;
    private String alias;
    private String orderPage;
    private Boolean visible = true;
    private List<PageFile>files;
    private Tab tab;
    private LangEnum lang;
    private static final long serialVersionUID = 157L;

    public Page() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public List<PageFile> getFiles() {
        return files;
    }

    public void setFiles(List<PageFile> files) {
        this.files = files;
    }


    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public String toString() {
        return  super.toString() + ", title:" + title;
    }

    public LangEnum getLang() {
        return lang;
    }

    public void setLang(LangEnum lang) {
        this.lang = lang;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getOrderPage() {
        return orderPage;
    }

    public void setOrderPage(String orderPage) {
        this.orderPage = orderPage;
    }

    @Override
    public int compareTo(Page t) {
        return this.orderPage.compareTo(t.orderPage);
    }
    
    
}
