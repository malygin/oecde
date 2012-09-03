package org.sgu.oecde.de.education;

import org.sgu.oecde.core.BasicItem;

/**
 * город
 * @author ShihovMY
 */
public class City extends BasicItem{
    /**
     * название
     */
    private String name;
    /**
     * описание
     */
    private String description;
    /**
     * сокращённо латинсими буквами
     */
    private String engShort;
    /**
     * сокращённо русскими буквами
     */
    private String rusShort;
    private static final long serialVersionUID = 42;

    public City() {
    }

    public City(Long id) {
        setId(id);
    }

    /**
     *
     * @return название
     */
    public String getName() {
        return name;
    }

    /**
     * название
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * описание
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return сокращённо латинсими буквами
     */
    public String getEngShort() {
        return engShort;
    }

    /**
     * сокращённо латинсими буквами
     * @param engShort
     */
    public void setEngShort(String engShort) {
        this.engShort = engShort;
    }

    /**
     *
     * @return сокращённо русскими буквами
     */
    public String getRusShort() {
        return rusShort;
    }

    /**
     * сокращённо русскими буквами
     * @param rusShort
     */
    public void setRusShort(String rusShort) {
        this.rusShort = rusShort;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("название: ").append(getName()).append("; ");
        return sb.toString();
    }
}
