package org.sgu.oecde.web.jsfbeans.supervisor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.users.DeSupervisor;
import org.sgu.oecde.de.users.Group;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="supervisorSessionBean")
@SessionScoped
public class SupervisorSessionBean implements Serializable{

    private DeSupervisor supervisor;

    private List<Group>groups;

    @ManagedProperty(value="#{groupDao}")
    IBasicDao<Group>groupDao;

    @ManagedProperty(value="#{semesterGetter}")
    private SemesterGetter semesterGetter;

    private static final long serialVersionUID = 183L;

    public SupervisorSessionBean(){
        AbstractUser user = SecurityContextHandler.getUser();
        if(user!=null&&user instanceof DeSupervisor)
            supervisor = (DeSupervisor) user;
    }

    public List<Group> getGroups() {
        if(groups == null){
            Group example = new Group(supervisor.getCity());
            groups = groupDao.getByExample(example);
            Collections.sort(groups);
        }
        return groups;
    }

    public DeSupervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(DeSupervisor supervisor) {
        this.supervisor = supervisor;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public SemesterGetter getSemesterGetter() {
        return semesterGetter;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }

    @PostConstruct
    public void afterPropertiesSet(){
        Assert.notNull(supervisor);
    }
}