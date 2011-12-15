package org.sgu.oecde.web.jsfbeans.admin;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.web.jsfbeans.UserViewBean;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherIndexBean;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherSessionBean;
import org.sgu.oecde.de.users.Group;
/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherViewBean")
@ViewScoped
public class TeacherViewBean extends UserViewBean{

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;
    
    @ManagedProperty(value="#{teacherIndexBean}")
    private TeacherIndexBean teacherIndexBean;

    private static final long serialVersionUID = 172L;
    
    private List<Group> groups;
    private  Integer semester;
    protected AbstractUser user;
    
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    
    
     public TeacherViewBean() {
         setType("TEACHER");
    }
     
    @Override
    public void setId(Long id) {
        super.setId(id);
        getUser();
        groups = teacherSessionBean.getGroups(semester);      
        teacherIndexBean.setTeacher((Teacher) user);
    }

    public void setTeacherIndexBean(TeacherIndexBean teacherIndexBean) {
        this.teacherIndexBean = teacherIndexBean;
    }


    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }
    @Override
    public AbstractUser getUser() {
        if(user==null){
            user = super.getUser();
            teacherSessionBean.setTeacher((Teacher) user);
            teacherIndexBean.setTeacher((Teacher) user);
        }
        return user;
    }
    public List<Group> getTeacherGroups(){
            return groups;
    }
    public int getGroupsCount(){
        return  groups.size();
    }
    
    public int getStudentsCount(){
        int count=0;
        for(Group group:  groups){
            count += group.getPersons().size();
        }
        return count;
    }
}
