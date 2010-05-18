package org.sgu.oecde.de.education;

import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class City extends BasicItem{
    private String name;
    private String description;
    private String engShort;
    private static final long serialVersionUID = 42;

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEngShort() {
        return engShort;
    }

    public void setEngShort(String engShort) {
        this.engShort = engShort;
    }
}
