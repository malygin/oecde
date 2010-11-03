package org.sgu.oecde.tabs;

import java.util.List;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class Page extends BasicItem{
    private String title;
    private String text;
    private Boolean visible = true;
    private Set<PageFile>files;
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

    public Set<PageFile> getFiles() {
        return files;
    }

    public void setFiles(Set<PageFile> files) {
        this.files = files;
    }

    public String toString() {
        return  super.toString() + ", title:" + title;
    }
}
