package org.sgu.oecde.core.users;

import java.util.Set;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.sgu.oecde.core.education.TeacherToGroup;

/**
 * преподаватель
 * @author ShihovMY
 */
public class Teacher extends AbstractPerson{

    /**
     * должность
     */
    private String post;
    /**
     * преподаватель - студенческая группа - учебный план
     */
    private Set<TeacherToGroup> teacherToGroups;
    /**
     * департамент/отдел/кафедра
     */
    private Department department;

    /**
     * и меил
     */
    @Email(message="неверный и-меил")
    private String email;
    /**
     * сотовый
     */
    @Digits(integer=10,message="Содержит недопустимые символы",fraction=0)
    private Integer cellPhone;
    /**
     * рабочий
     */
    @Digits(integer=10,message="Содержит недопустимые символы",fraction=0)
    private Integer officePhoneNumber;
    /**
     * дополнительная информация, заполняемая преподавателем
     */
    @Size(max=250,message="слишком много символов")
    private String additionalInformation;
    
    private static final long serialVersionUID = 52L;

    public Teacher() {
    }

    public Teacher(Long id) {
        setId(id);
    }

    /**
     * должность
     * @param post
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     *
     * @return должность
     */
    public String getPost() {
        return post;
    }

    /**
     *
     * @return преподаватель - студенческая группа - учебный план
     */
    public Set<TeacherToGroup> getTeacherToGroups() {
        return teacherToGroups;
    }

    /**
     * преподаватель - студенческая группа - учебный план
     * @param teacherToGroups
     */
    public void setTeacherToGroups(Set<TeacherToGroup> teacherToGroups) {
        this.teacherToGroups = teacherToGroups;
    }

    /**
     *
     * @return отдел
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * отдел
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     *
     * @return дополнительная информация
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * дополнительная информация
     * @param additionalInformation
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     *
     * @return сотовый телефон
     */
    public Integer getCellPhone() {
        return cellPhone;
    }

    /**
     * сотовый телефон
     * @param cellPhone
     */
    public void setCellPhone(Integer cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     *
     * @return имеил
     */
    public String getEmail() {
        return email;
    }

    /**
     * имеил
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return рабочий телефон
     */
    public Integer getOfficePhoneNumber() {
        return officePhoneNumber;
    }

    /**
     * рабочий телефон
     * @param officePhoneNumber
     */
    public void setOfficePhoneNumber(Integer officePhoneNumber) {
        this.officePhoneNumber = officePhoneNumber;
    }
}
