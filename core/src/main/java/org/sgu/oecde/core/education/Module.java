package org.sgu.oecde.core.education;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class Module extends BasicItem{

    private static final long serialVersionUID = 55L;
    private String name;
    private Umk umk;
    private Set<? extends AbstractResource>resources;

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
}
