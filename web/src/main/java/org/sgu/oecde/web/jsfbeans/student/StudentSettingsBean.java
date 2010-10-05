package org.sgu.oecde.web.jsfbeans.student;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.Email;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.de.users.Student;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentSettingsBean")
@ViewScoped
public class StudentSettingsBean implements Serializable{

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;

    @ManagedProperty(value="#{userDao}")
    private IUpdateDao<Student>userDao;

    @Email(message="неверный и-меил")
    private String eMail;

    @Digits(integer=9,message="Содержит недопустимые символы",fraction=0)
    private int icq;
    @Digits(integer=10,message="Содержит недопустимые символы",fraction=0)
    private int cellPhone;

    private boolean saved;

    private String error;

    private static final long serialVersionUID = 156L;

    public void save(){
        Student st = studentSessionBean.getStudent();
        Assert.notNull(st);
        st.setCellPhone(cellPhone);
        st.setEmail(eMail);
        st.setIcq(icq);
        try {
            userDao.update(st);
        } catch (Exception e) {
            e.fillInStackTrace();
            error = "При сохранении возникла ошибка";
        }
        saved = true;
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

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
