package org.sgu.oecde.core.education;

import org.sgu.oecde.core.education.resource.AbstractResource;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.resource.Author;

/**
 * учебно-методический комплекс. умк
 * @author ShihovMY
 */
public class Umk extends BasicItem{
    /**
     * название
     */
    private String name;
    /**
     * папка, в которой лежит умк
     */
    private String folder;
    /**
     * описание
     */
    private String description;
    /**
     * авторы
     */
    private Set<Author>authors;
    /**
     * модули
     */
    private Set<? extends Module>modules;
    /**
     * ресурсы
     */
    private Set<? extends AbstractResource> resources;
    private static final long serialVersionUID = 53L;
  

    public Umk() {
    }

    /**
     * название
     * @return
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
     * модули
     * @param <T> extends Module
     * @return
     */
    public <T extends Module> Set<T> getModules() {
        return (Set<T>) modules;
    }

    /**
     * модули
     * @param modules
     */
    public void setModules(Set<? extends Module> modules) {
        this.modules = modules;
    }

    /**
     * ресурсы
     * @param <T> extends AbstractResource
     * @return
     */
    public <T extends AbstractResource> Set<T> getResources() {
        return (Set<T>) resources;
    }

    /**
     * ресурсы
     * @param resources
     */
    public void setResources(Set<? extends AbstractResource> resources) {
        this.resources = resources;
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

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("название: ").append(name).append("; ");
        return sb.toString();
    }
}
