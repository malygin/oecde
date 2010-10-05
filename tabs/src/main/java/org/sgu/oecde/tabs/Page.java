package org.sgu.oecde.tabs;

import java.util.List;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class Page extends BasicItem{
    private String title;
    private String text;
    private Boolean visible = true;
    private List<PageFile>files;
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

    public String toString() {
        return  super.toString() + ", title:" + title;
    }
}
