package org.sgu.oecde.tabs;

import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class PageFile extends BasicItem{
    private String name;
    private String description;
    private Boolean image;
    private Page page;
    private Boolean visible = true;
    private static final long serialVersionUID = 159L;

    public PageFile() {
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}