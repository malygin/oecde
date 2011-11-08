package org.sgu.oecde.web.jsfbeans.admin;

import java.util.ArrayList;
import java.util.Iterator;
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
    private List<Admin>adminsForTeacher=null;
    private List<DeSupervisor> supervisors;

    @ManagedProperty(value="#{adminDao}")
    private IBasicDao<Admin>adminDao;
    @ManagedProperty(value="#{supervisorDao}")
    private IBasicDao<DeSupervisor>supervisorDao;
     private List<Long> adminReq=new ArrayList<Long>();
    {
        adminReq.add(new Long(11));
        adminReq.add(new Long(42));
        adminReq.add(new Long(82));
        adminReq.add(new Long(172));
        adminReq.add(new Long(262));
        adminReq.add(new Long(483));
        adminReq.add(new Long(522));
        adminReq.add(new Long(542));
    }
    public List<Admin> getAdmins() {
        if(admins == null){
            admins = adminDao.getAll();
        }
        return admins;
    }
      public List<Admin> getAdminsForTeacher() {
        if(adminsForTeacher==null){
            adminsForTeacher = adminDao.getAll();
            Iterator<Admin> i=adminsForTeacher.iterator();
            while (i.hasNext()){
                Admin a=i.next();
                if  (!adminReq.contains(a.getId()))  i.remove();                 
            }
//          Admin a=admins.get(0);         
//          admins.add(6, admins.get(0));
//          admins.remove(0);
          
        }
        return adminsForTeacher;
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
