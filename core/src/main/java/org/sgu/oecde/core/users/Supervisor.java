package org.sgu.oecde.core.users;

/**
 * наблюдатель. адмнистратор с ограниченным набором прав и функционала
 * @author ShihovMY
 */
public class Supervisor extends AbstractUser{
    /**
     * описание
     */
    private String description;
    private static final long serialVersionUID = 62L;

    public Supervisor() {
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
}
