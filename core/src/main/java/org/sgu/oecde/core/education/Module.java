package org.sgu.oecde.core.education;

import org.sgu.oecde.core.education.resource.AbstractResource;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.springframework.util.StringUtils;

/**
 * модуль умк
 * @author ShihovMY
 */
public class Module extends BasicItem{

    /**
     * название
     */
    private String name;
    /**
     * описание
     */
    private String description;
    /**
     * умк
     */
    private Umk umk;
    /**
     * сет ресурсов
     */
    private Set<? extends AbstractResource>resources;
    private static final long serialVersionUID = 55L;

    public Module() {
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
     * умк
     * @return
     */
    public Umk getUmk() {
        return umk;
    }

    /**
     * умк
     * @param umk
     */
    public void setUmk(Umk umk) {
        this.umk = umk;
    }

    /**
     * ресурсы модуля
     * @param <T> extends AbstractResource
     * @return
     */
    public <T extends AbstractResource>Set<T> getResources() {
        return (Set<T>) resources;
    }

    /**
     * ресурсы модуля
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("название: ").append(name).append("; ");
        if(umk!=null&&StringUtils.hasText(umk.getName()))
            sb.append("умк: ").append(umk.getName()).append("; ");
        return sb.toString();
    }
}
