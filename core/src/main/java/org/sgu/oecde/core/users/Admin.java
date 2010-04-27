package org.sgu.oecde.core.users;

/**
 *
 * @author ShihovMY
 */
public class Admin extends AbstractPerson{

    private static final long serialVersionUID = 56L;    
    private String position;
    private String description;
    private int type;
    
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
