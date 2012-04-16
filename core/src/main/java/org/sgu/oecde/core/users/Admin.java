package org.sgu.oecde.core.users;

/**
 * администратор
 * @author ShihovMY
 */
public class Admin extends AbstractPerson{

    /**
     * должность администратора
     */
    private String position;
    /**
     * описание
     */
    private String description;
    
    private boolean showUsers = false;

    private static final long serialVersionUID = 56L;
    
    public Admin() {
    }

    /**
     *
     * @return описание
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

    /**
     *
     * @return должность
     */
    public String getPosition() {       
        return position;
    }

    /**
     * должность
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isShowUsers() {
        return showUsers;
    }

    public void setShowUsers(boolean showUsers) {
        this.showUsers = showUsers;
    }
    
  
    
    
}
