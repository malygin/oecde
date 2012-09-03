package org.sgu.oecde.core.education;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.sgu.oecde.core.education.resource.AbstractResource;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.Teacher;

/**
 * учебно-методический комплекс. умк
 * @author ShihovMY
 */
public class Umk extends BasicItem {
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
    private Set<Teacher>authors;
    /**
     * модули
     */
    private List<? extends Module>modules;
    /**
     * ресурсы
     */
    private Boolean changed=false;
    private List<? extends AbstractResource> resources;
    private static final long serialVersionUID = 53L;
  

    public Umk() {
    }

    /**
     * название
     * @return
     */
    public String getName() {
        if (name.equals("default")) return "Курс в процессе подготовки";
        return name;
    }

    /**
     * название
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Set<Teacher> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Teacher> authors) {
        this.authors = authors;
    }

 

    /**
     * модули
     * @param <T> extends Module
     * @return
     */
    public <T extends Module> List<T> getModules() {
        return (List<T>) modules;
    }

    /**
     * модули
     * @param modules
     */
    public void setModules(List<? extends Module> modules) {
        this.modules = modules;
    }

    /**
     * ресурсы
     * @param <T> extends AbstractResource
     * @return
     */
    public <T extends AbstractResource> List<T> getResources() {
        
        return (List<T>) resources;
    }

    /**
     * ресурсы
     * @param resources
     */
    public void setResources(List<? extends AbstractResource> resources) {
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

    public Boolean getChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
    }
    
    
  
}
