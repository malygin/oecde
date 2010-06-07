package org.sgu.oecde.core.education.resource;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.ITeacher;

/**
 *
 * @author ShihovMY
 *
 */
abstract public class AbstractResource extends BasicItem{
    
    private String title;
    private String shortDescription;
    private String description;

    //версия ресурса,начиная с 1
    private Integer version;

    //видимость ресурса, true- видим
    private Boolean visible;
    private String dateLastchange;

    //уровень доступа к ресурсу
    private AccessResource accessResource;
    private Set<ITeacher>authors;

    public AbstractResource() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<ITeacher> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<ITeacher> authors) {
        this.authors = authors;
    }

    public String getDateLastchange() {
        return dateLastchange;
    }

    public void setDateLastchange(String dateLastchange) {
        this.dateLastchange = dateLastchange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public AccessResource getAccessResource() {
        return accessResource;
    }

    public void setAccessResource(AccessResource accessResource) {
        this.accessResource = accessResource;
    }




}
