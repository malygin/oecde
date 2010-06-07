package org.sgu.oecde.core.users;

/**
 *
 * @author ShihovMY
 */
public class Admin extends AbstractPerson{

    private static final long serialVersionUID = 56L;    
    private String position;
    private String description;
    private Integer type;
    
    public Admin() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
