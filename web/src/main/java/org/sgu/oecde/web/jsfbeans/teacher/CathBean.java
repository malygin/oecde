/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.users.Department;
import org.sgu.oecde.core.users.Teacher;

/**
 *
 * @author malygin
 */
@ManagedBean
@ViewScoped
public class CathBean {
    private List<Teacher> teachers ;
    private long id;
    private Teacher head;
    @ManagedProperty(value="#{departmentDao}")
    private IUpdateDao dDao;
    private String name;
    /**
     * Creates a new instance of CathBean
     */
    public CathBean() {
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public Teacher getHead() {
        return head;
    }
    
    public IUpdateDao getdDao() {
        return dDao;
    }

    public void setdDao(IUpdateDao dDao) {
        this.dDao = dDao;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(long id) {
        this.id = id;
        Department d = ((Department)dDao.getById(id));
        Set t = d.getPersons();
        teachers = (t!= null)? new ArrayList<Teacher>(t): new ArrayList();
        head = d.getHead();
        name = d.getName();
    }


    
    
    
}
