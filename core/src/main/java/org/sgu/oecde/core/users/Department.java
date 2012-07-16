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
    private String phoneNumber;
    private String email;
    private String address;
    private String url;
    
    private Teacher head;
  
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Teacher getHead() {
        return head;
    }

    public void setHead(Teacher head) {
        this.head = head;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

  
    
}
