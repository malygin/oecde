package org.sgu.oecde.web.jsfbeans.admin;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.de.users.DeSupervisor;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@SessionScoped
public class AdminsBean {
    private List<Admin>admins;
    private List<DeSupervisor> supervisors;

    @ManagedProperty(value="#{adminDao}")
    private IBasicDao<Admin>adminDao;
    @ManagedProperty(value="#{supervisorDao}")
    private IBasicDao<DeSupervisor>supervisorDao;

    public List<Admin> getAdmins() {
        if(admins == null){
            admins = adminDao.getAll();
        }
        return admins;
    }

    public List<DeSupervisor> getSupervisors() {
        if(supervisors == null){
            supervisors = supervisorDao.getAll();
        }
        return supervisors;
    }

    public void setAdminDao(IBasicDao<Admin> adminDao) {
        this.adminDao = adminDao;
    }

    public void setSupervisorDao(IBasicDao<DeSupervisor> supervisorDao) {
        this.supervisorDao = supervisorDao;
    }
}
