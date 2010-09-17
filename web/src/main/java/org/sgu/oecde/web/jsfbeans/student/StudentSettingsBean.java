package org.sgu.oecde.web.jsfbeans.student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.de.users.Student;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentSettingsBean")
@ViewScoped
public class StudentSettingsBean {

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;

    @ManagedProperty(value="#{userDao}")
    private IUpdateDao<Student>userDao;

    private String password;
    private String eMail;
    private int icq;
    private int cellPhone;

    public void save(){
        Student st = studentSessionBean.getStudent();
        Assert.notNull(st);
        st.setCellPhone(cellPhone);
        st.setEmail(eMail);
        st.setIcq(icq);
        st.setPassword(password);
        userDao.update(st);
    }

    public void setStudentSessionBean(StudentSessionBean studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }

    public void setUserDao(IUpdateDao userDao) {
        this.userDao = userDao;
    }

    public int getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(int cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getIcq() {
        return icq;
    }

    public void setIcq(int icq) {
        this.icq = icq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
