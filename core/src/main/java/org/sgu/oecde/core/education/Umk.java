package org.sgu.oecde.core.education;

import org.sgu.oecde.core.education.resource.AbstractResource;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractTeacher;
//import org.sgu.oecde.core.users.ITeacher;

/**
 *
 * @author ShihovMY
 */
public class Umk extends BasicItem{

    private static final long serialVersionUID = 53L;
    private String name;
    private String description;
    //private Set<ITeacher>authors;
    private Set<? extends Module>modules;
    private Set<? extends AbstractResource> resources;
  

    public Umk() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<ITeacher> getAuthors() {
//        return authors;
//    }
//
//    public void setAuthors(Set<ITeacher> authors) {
//        this.authors = authors;
//    }

    public <T extends Module> Set<T> getModules() {
        return (Set<T>) modules;
    }

    public void setModules(Set<? extends Module> modules) {
        this.modules = modules;
    }

    public <T extends AbstractResource> Set<T> getResources() {
        return (Set<T>) resources;
    }

    public void setResources(Set<? extends AbstractResource> resources) {
        this.resources = resources;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
