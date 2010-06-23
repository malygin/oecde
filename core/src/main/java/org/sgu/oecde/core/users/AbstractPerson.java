package org.sgu.oecde.core.users;

/**
 * пользователь - человек. с фамилией именем и отчетсвом
 * @author ShihovMY
 */
public abstract class AbstractPerson extends AbstractUser{

    /**
     * имя
     */
    private String name;
    /**
     * отчество
     */
    private String secondName;
    /**
     * фамилия
     */
    private String surname;

    public AbstractPerson() {
    }

    /**
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * ися
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return отчество
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * отчество
     * @param secondName
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     *
     * @return фамилия
     */
    public String getSurname() {
        return surname;
    }
    /**
     * фамилия
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * возвращает полные фио
     * @return
     */
    public String getFio(){
        StringBuilder sb = new StringBuilder();
        sb.append(getSurname()).append(" ");
        sb.append(getName()).append(" ");
        sb.append(getSecondName());
        return sb.toString();
    }

    /**
     * возвращает инициалы
     * @return
     */
    public String getInitials(){
        StringBuilder sb = new StringBuilder();
        sb.append(getSurname()).append(" ");
        sb.append(getName().charAt(0)).append(". ");
        sb.append(getSecondName().charAt(0));
        return sb.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Surname: ").append(this.surname).append(";\n");
        sb.append("Name: ").append(this.name).append(";\n");
        sb.append("Second name: ").append(this.secondName).append(";\n");
        return sb.toString();
    }
}
