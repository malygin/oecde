package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="groupViewBean")
@ViewScoped
public class GroupViewBean implements Serializable{
    Group group;
    Long id;
    List<Student>students;

    @ManagedProperty(value="#{groupDao}")
    IBasicDao<Group>groupDao;

    private static final long serialVersionUID = 167L;

    public Group getGroup(){
        return group;
    }

    public List<Student>getStudents(){
        if(students==null){
            if(group!=null)
                students = new ArrayList<Student>(group.getPersons());
        }
        return students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        group = groupDao.getById(id);
        this.id = id;
    }

    public void setGroupDao(IBasicDao<Group> groupDao) {
        this.groupDao = groupDao;
    }
}
