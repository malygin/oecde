package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.web.jsfbeans.UserSessionBean;
import org.sgu.oecde.web.jsfbeans.util.CryptoClassDES;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="adminSessionBean")
@SessionScoped
public class AdminSessionBean extends UserSessionBean implements Serializable{

    protected Admin admin;

    private static final long serialVersionUID = 154L;

    public AdminSessionBean(){
        AbstractUser user = SecurityContextHandler.getUser();
        if(user!=null&&user instanceof Admin)
            admin = (Admin) user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @PostConstruct
    public void afterPropertiesSet(){
        Assert.notNull(admin);
    }
    
    public String getEncryptedUserNPass() throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String encryptMe = this.admin.getUsername() +":"+ this.admin.getPassword();
        String returnMe = CryptoClassDES.encrypt(encryptMe);
        return returnMe;
    }
}
