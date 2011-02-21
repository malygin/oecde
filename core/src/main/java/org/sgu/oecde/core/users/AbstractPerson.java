package org.sgu.oecde.core.users;

import org.springframework.util.StringUtils;
import static org.sgu.oecde.core.util.ProperCase.bringTo;

/**
 * пользователь - человек. с фамилией именем и отчетсвом
 * @author ShihovMY
 */
public abstract class AbstractPerson extends AbstractUser implements Comparable<AbstractPerson>{

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
        return bringTo(name);
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
        return bringTo(secondName);
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
        return bringTo(surname);
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
        if(StringUtils.hasText(getName()))
            sb.append(getName().charAt(0)).append(". ");
        if(StringUtils.hasText(getSecondName()))
            sb.append(getSecondName().charAt(0));
        return sb.toString();
    }

    public String getClassType(){
         if (this instanceof org.sgu.oecde.core.users.Admin)
              return "Admin";
         if (this instanceof org.sgu.oecde.core.users.Teacher)
              return "Teacher";
         else return "Student";
    }

   public String getClassTypeLowerCase(){
         if (this instanceof org.sgu.oecde.core.users.Admin)
              return "admin";
         if (this instanceof org.sgu.oecde.core.users.Teacher)
              return "teacher";
         else return "student";
    }
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Surname: ").append(getSurname()).append(";\n");
        sb.append("Name: ").append(getName()).append(";\n");
        sb.append("Second name: ").append(getSecondName()).append(";\n");
        return sb.toString();
    }

    @Override
    public int compareTo(AbstractPerson o) {
        int st = 0;
        if(this!=null &&o!=null && this.getFio()!=null){
            st = this.getFio().compareTo(o.getFio());
        }
        return st;
    }

}
