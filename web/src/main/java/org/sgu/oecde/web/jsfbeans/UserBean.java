/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.web.jsfbeans;

import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.SecurityContextHandler;


/**
 *
 * @author KorgovVD
 */
@ManagedBean(name="userBean")
@SessionScoped
public class UserBean {

    private AbstractUser user;
    /** Creates a new instance of UserBean */
    public UserBean() {
        user = SecurityContextHandler.getUser();
    }

    public String getFIO(){
         String fio="";
         UserType userType=UserType.fromRole(user);
         AbstractPerson person = (AbstractPerson) user;
         switch(userType){
             case STUDENT:
                 String[] fioSplit= person.getName().split(" ");
                 fioSplit[1] = fioSplit[1].substring(0,1).toUpperCase().concat(fioSplit[1].substring(1));
                 fioSplit[2] = fioSplit[2].substring(0,1).toUpperCase().concat(fioSplit[2].substring(1));

                 fio=fioSplit[0]+" "+fioSplit[1]+" "+fioSplit[2];
                 break;
             case ADMIN:
                 fio = person.getFio();
                 break;
             case TEACHER:
                 fio = person.getFio();
                 break;
           }
         return fio;
    }

    public String getName(){
        String name = "";
        UserType userType = UserType.fromRole(user);
        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                String[] fioSplit = person.getName().split(" ");
                fioSplit[1] = fioSplit[1].substring(0,1).toUpperCase().concat(fioSplit[1].substring(1));
                name = fioSplit[1];
                break;
            case TEACHER:
                name = person.getName();
                break;
            case ADMIN:
                name = person.getName();
                break;
        }
        return name;
    }

    public String getSurname(){
        String surname = "";
        UserType userType = UserType.fromRole(user);
        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                String[] fioSplit = person.getName().split(" ");
                surname = fioSplit[0];
                break;
            case TEACHER:
                surname = person.getSurname();
                break;
            case ADMIN:
                surname = person.getSurname();
                break;
        }
        return surname;
    }

    public String getSecondName(){
        String secondName = "";
        UserType userType = UserType.fromRole(user);
        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                String[] fioSplit = person.getName().split(" ");
                fioSplit[2] = fioSplit[2].substring(0,1).toUpperCase().concat(fioSplit[2].substring(1));
                secondName = fioSplit[2];
                break;
            case TEACHER:
                secondName = person.getSecondName();
                break;
            case ADMIN:
                secondName = person.getSecondName();
                break;
        }
        return secondName;
    }

    public String getAvatarBig(){
        if(user.getLargePhoto()==null) return "defaultAvatarBig.jpg";
        return user.getLargePhoto();
    }

    public String getAvatarMedium(){
        if(user.getMediumPhoto()==null) return "defaultAvatarMedium.jpg";
        return user.getMediumPhoto();
    }

    public String getAvatarSmall(){
        if(user.getSmallPhoto()==null) return "defaultAvatarSmall.jpg";
        return user.getSmallPhoto();
    }

    public String getBirthDay(){
        String birthDay = "";
        UserType userType = UserType.fromRole(user);
//        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                birthDay = "06-01-1990";
                break;
            case TEACHER:
                birthDay = "06-01-1990";
                break;
            case ADMIN:
                birthDay = "not defined";
                break;
        }
        return birthDay;
    }

    public String getFaculty(){
        String faculty = "";
        UserType userType = UserType.fromRole(user);
//        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                faculty = "Мех-мат.";
                break;
            case TEACHER:
                faculty = "not defined";
                break;
            case ADMIN:
                faculty = "not defined";
                break;
        }
        return faculty;
    }

    public String getDepartment(){
        String department = "";
        UserType userType = UserType.fromRole(user);
//        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                department = "not defined";
                break;
            case TEACHER:
                department = "Мех-мат.";
                break;
            case ADMIN:
                department = "not defined";
                break;
        }
        return department;
    }

    public String getPost(){
        String post = "";
        UserType userType = UserType.fromRole(user);
//        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                post = "not defined";
                break;
            case TEACHER:
                post = "Прикладная информатика в экономике";
                break;
            case ADMIN:
                post = "not defined";
                break;
        }
        return post;
    }

    public String getSpec(){
        String spec = "";
        UserType userType = UserType.fromRole(user);
//        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                spec = "Прикладная информатика в экономике";
                break;
            case TEACHER:
                spec = "not defined";
                break;
            case ADMIN:
                spec = "not defined";
                break;
        }
        return spec;
    }

    public String getCource(){
        String cource = "";
        UserType userType = UserType.fromRole(user);
//        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                cource = "4";
                break;
            case TEACHER:
                cource = "not defined";
                break;
            case ADMIN:
                cource = "not defined";
                break;
        }
        return cource;
    }

    public String getGroup(){
        String group = "";
        UserType userType = UserType.fromRole(user);
//        AbstractPerson person = (AbstractPerson) user;
        switch(userType){
            case STUDENT:
                group = "432";
                break;
            case TEACHER:
                group = "not defined";
                break;
            case ADMIN:
                group = "not defined";
                break;
        }
        return group;
    }
}
