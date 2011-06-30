package org.sgu.oecde.tabs;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.util.LangEnum;

/**
 *
 * @author ShihovMY
 */
public class Tab extends BasicItem implements Comparable<Tab> {
    private String name;
    private String alias;    
    private String orderTab;
    private List<Page>pages;
    private TabType type;
    private LangEnum lang;
    
    
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

    public String getOrderTab() {
        return orderTab;
    }

    public void setOrderTab(String orderTab) {
        this.orderTab = orderTab;
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
