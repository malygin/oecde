package org.sgu.oecde.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.users.DeSupervisor;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.dao.ILessonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author ShihovMY
 */
@WebService(serviceName = "ConferenceWebService" )
public class ConferenceWeb extends SpringBeanAutowiringSupport implements Serializable{


    IBasicDao<AbstractUser> userDao;
    ILessonDao lessonDao;

    private static final long serialVersionUID = 218L;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getByUsernameAndPassword")
    public String getByUsernameAndPassword(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password) {
        List<AbstractUser> list = userDao.getByExample(AbstractUser.getUserWithName(username));
        StringBuffer parameters = new StringBuffer();
        if(list.size()==1){
            AbstractUser u = list.get(0);
            if(u.getPassword().equals(password)){
                //parameters = new HashMap<String, String>();
                parameters.append(u.getUsername()).append(";");
                parameters.append(u.getId().toString()).append(";");
                parameters.append(UserType.toType(u).toEngString()).append(";");
                if(u instanceof AbstractPerson){
                    parameters.append(((AbstractPerson)u).getInitials()).append(";");
                    parameters.append(((AbstractPerson)u).getFio()).append(";");
                }else if(u instanceof DeSupervisor){
                    parameters.append(((DeSupervisor)u).getCity().getRusShort()).append(";");
                    parameters.append(((DeSupervisor)u).getCity().getName()).append(";");
                }
                if (u instanceof Teacher){
                     List<Lesson>   lessons = lessonDao.getLessonsForTeacher(false,(Teacher)u,30,1,"2012.01.01","2013.11.11");
                    if (lessons!=null){
                         for (Lesson l:lessons)                     
                            parameters.append(l.getId()+":"+l.getLessonDate()+":").append(l.getRoom()/13 +1).append(";");   
               
                     }                     
                
                }
            }
        }
        return parameters.toString();
    }



 @WebMethod(exclude=true)
    public void setUserDao(IBasicDao<AbstractUser> userDao) {
        this.userDao = userDao;
    }
 @WebMethod(exclude=true)
    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }
 
        
        

    
}
