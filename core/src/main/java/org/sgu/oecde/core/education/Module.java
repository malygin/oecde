package org.sgu.oecde.core.education;

import org.sgu.oecde.core.education.resource.AbstractResource;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.ITeacher;

/**
 *
 * @author ShihovMY
 */
public class Module extends BasicItem{

    private static final long serialVersionUID = 55L;
    private String name;
    private String description;
    private Umk umk;
    private Set<? extends AbstractResource>resources;
    private Set<ITeacher>authors;

    public Module() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Umk getUmk() {
        return umk;
    }

    public void setUmk(Umk umk) {
        this.umk = umk;
    }

    public <T extends AbstractResource>Set<T> getResources() {
        return (Set<T>) resources;
    }

    public void setResources(Set<? extends AbstractResource> resources) {
        this.resources = resources;
    }

    public Set<ITeacher> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<ITeacher> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
