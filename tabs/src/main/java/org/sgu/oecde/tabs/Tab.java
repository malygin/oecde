package org.sgu.oecde.tabs;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public class Tab extends BasicItem{
    private String name;
    private Set<Page>pages;
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

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    public TabType getType() {
        return type;
    }

    public void setType(TabType type) {
        if(type!=null)
            type.checkIntstantiation();
        this.type = type;
    }
}
