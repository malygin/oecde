package org.sgu.oecde.tabs;

import java.util.List;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class Tab extends BasicItem{
    private String name;
    private List<Page>pages;
    private TabType type;
    private static final long serialVersionUID = 158L;

    public Tab() {
    }

    public Tab(TabType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public TabType getType() {
        return type;
    }

    public void setType(TabType type) {
        this.type = type;
    }
}
