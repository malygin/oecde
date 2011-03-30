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
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.de.users.DeSupervisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author ShihovMY
 */
@WebService
public class ConferenceWebService extends SpringBeanAutowiringSupport implements Serializable{

    @Autowired
    IBasicDao<AbstractUser> userDao;

    private static final long serialVersionUID = 218L;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getByUsernameAndPassword")
    public HashMap<String,String>getByUsernameAndPassword(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password) {
        List<AbstractUser> list = userDao.getByExample(AbstractUser.getUserWithName(username));
        HashMap<String,String>parameters = null;
        if(list.size()==1){
            AbstractUser u = list.get(0);
            if(u.getPassword().equals(password)){
                parameters = new HashMap<String, String>();
                parameters.put("username", u.getUsername());
                parameters.put("id", u.getId().toString());
                parameters.put("type", UserType.toType(u).toEngString());
                if(u instanceof AbstractPerson){
                    parameters.put("initials", ((AbstractPerson)u).getInitials());
                    parameters.put("fio", ((AbstractPerson)u).getFio());
                }else if(u instanceof DeSupervisor){
                    parameters.put("initials", ((DeSupervisor)u).getCity().getRusShort());
                    parameters.put("fio", ((DeSupervisor)u).getCity().getName());
                }
            }
        }
        return parameters;
    }

}
