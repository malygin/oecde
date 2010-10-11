package org.sgu.oecde.core.education.resource;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 * ресурс умк, либо модуля
 * @author ShihovMY *
 */
abstract public class AbstractResource extends BasicItem{

    /**
     * название
     */
    private String title;
    /**
     * описание коротно
     */
    private String shortDescription;
    /**
     * описание
     */
    private String description;

    /**
     * версия ресурса,начиная с 1
     */
    private Integer version;

    /**
     * видимость ресурса, true- видим
     */
    private Boolean visible;
    /**
     * дата последнего изменения
     */
    private String dateLastСhange;

    /**
     * уровень доступа к ресурсу
     */
    private AccessResource accessResource;
    /**
     * авторы
     */
    private Set<Author>authors;

    public AbstractResource() {
    }

    /**
     * название
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * название
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * авторы
     * @return
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    /**
     * авторы
     * @param authors
     */
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    /**
     * дата последнего изменения
     * @return
     */
    public String getDateLastСhange() {
        return dateLastСhange;
    }

    /**
     * дата последнего изменения
     * @param dateLastchange
     */
    public void setDateLastСhange(String dateLastСhange) {
        this.dateLastСhange = dateLastСhange;
    }

    /**
     * описание
     * @return
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
     * короткое описание
     * @return
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * короткое описание
     * @param shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * версия, от 1
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * версия
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * видимый ли
     * @return
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * видимый ли
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * уровень доступа
     * @return
     */
    public AccessResource getAccessResource() {
        return accessResource;
    }

    /**
     * уровень доступа
     * @param accessResource
     */
    public void setAccessResource(AccessResource accessResource) {
        this.accessResource = accessResource;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
            sb.append("название: ").append(title).append("; ");
        return sb.toString();
    }
}
