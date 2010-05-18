package org.sgu.oecde.news;

import org.sgu.oecde.core.BasicItem;

/**
 * Created by IntelliJ IDEA.
 * User: basakovvy
 * Date: 24.11.2009
 * Time: 11:48:12
 */
public class NewsTag extends BasicItem{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NewsTag{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
