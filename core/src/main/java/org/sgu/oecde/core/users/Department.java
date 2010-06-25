package org.sgu.oecde.core.users;

/**
 * департамент/кафедра/отдел/лаборатория и тд
 * @author ShihovMY
 */
public class Department extends AbstractGroup<Teacher>{
    /**
     * описание департамента. к примеру, для кафедры может содержать название факультета
     */
    private String description;
    /**
     * телефон
     */
    private Integer phoneNumber;
    private static final long serialVersionUID = 68L;

    public Department() {
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
     * @return телефон
     */
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * телефон
     * @param phoneNumber
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
