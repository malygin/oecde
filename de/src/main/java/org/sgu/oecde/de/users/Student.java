package org.sgu.oecde.de.users;

import org.sgu.oecde.core.users.AbstractStudent;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Digits;

/**
 * студент дистанционного образования
 * @author ShihovMY
 */
public class Student extends AbstractStudent{

    /**
     * e-mail
     */
    @Email(message="неверный и-меил")
    private String email;
    /**
     * мобильный
     */
    @Digits(integer=10,message="Содержит недопустимые символы",fraction=0)
    private Integer cellPhone;
    /**
     * icq
     */
    @Digits(integer=9,message="Содержит недопустимые символы",fraction=0)
    private Integer icq;
    private static final long serialVersionUID = 51L;

    public Student() {
    }

    public Student(Long id) {
        setId(id);
    }

    /**
     *
     * @return сотовый
     */
    public Integer getCellPhone() {
        return cellPhone;
    }

    /**
     * сотовый
     * @param cellPhone
     */
    public void setCellPhone(Integer cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return icq
     */
    public Integer getIcq() {
        return icq;
    }

    /**
     * icq
     * @param icq
     */
    public void setIcq(Integer icq) {
        this.icq = icq;
    }
}
