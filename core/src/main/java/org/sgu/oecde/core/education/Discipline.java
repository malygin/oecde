package org.sgu.oecde.core.education;

import org.sgu.oecde.core.BasicItem;

/**
 * дисциплина
 * @author shihovmy
 */
public class Discipline extends BasicItem{

    private String name;
    private static final long serialVersionUID = 47L;

    public Discipline() {
    }

    public Discipline(Long id) {
        setId(id);
    }

    public Discipline(Long id, String name) {
        setId(id);
        this.name = name;
    }

    /**
     * @return название дисциплины
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
