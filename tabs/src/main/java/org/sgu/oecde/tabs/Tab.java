package org.sgu.oecde.tabs;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class Tab extends BasicItem implements Comparable<Tab> {
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

    @Override
    public int compareTo(Tab o) {
        int c = 0;
        if(o!=null&&o.getType()!=null)
            c = this.getType().getName().compareTo(o.getType().getName());
        return c;
    }
}
