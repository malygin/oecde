package org.sgu.oecde.web.jsfbeans.admin;

import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.users.Teacher;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ViewScoped
public class TeacherListBean {

    @ManagedProperty(value="#{teacherDao}")
    private IBasicDao<Teacher>teacherDao;

    private List<Teacher>tList;

    private String page="1";

    private final int maxNumber = 40;

    /**
     * получение списка умк с проверкой на нал некоторых тестов и тех что 
     * лезут экспшены на ресурсы @todo проверить почему
     * 
     * @return 
     */
    public List<Teacher> getTeacherList() {
        if(tList==null){
            tList = teacherDao.getByPage(maxNumber, Integer.parseInt(page),"surname");             
        }
        return tList;
    }
    
     public int getCount(){
        return teacherDao.getCount();
    }

//    public int getCount(){
//        List<Umk> l = getUmkList();
//        return l!=null?umkList.size():0;
//    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setTeacherDao(IBasicDao<Teacher> teacherDao) {
        this.teacherDao = teacherDao;
    }

 
   
}
