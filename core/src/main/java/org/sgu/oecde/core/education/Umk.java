package org.sgu.oecde.core.education;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.ITeacher;

/**
 *
 * @author ShihovMY
 */
public class Umk extends BasicItem{

    private static final long serialVersionUID = 53L;
    private String name;
    private Set<ITeacher>authors;
    private Set<Module>modules;
    private Set<AbstractResource>resources;

    public Umk() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ITeacher> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<ITeacher> authors) {
        this.authors = authors;
    }

    public <T extends Module> Set<T> getModules() {
        return (Set<T>) modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<AbstractResource> getResources() {
        return resources;
    }

    public void setResources(Set<AbstractResource> resources) {
        this.resources = resources;
    }
}
