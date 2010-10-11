package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
public class StudentEditBean implements Serializable{

    private IUpdateDao<Student>studentDao;
    private IBasicDao<Speciality>specialityDao;
    private Student student;
    private List<Speciality>specialities;
    private long id;

    private static final long serialVersionUID = 169L;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Speciality> getSpecialities() {
        if(specialities == null){
            specialities = specialityDao.getAll();
        }
        return specialities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        student = studentDao.getById(id);
        this.id = id;
    }

    public void setStudentDao(IUpdateDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    public void setSpecialityDao(IBasicDao<Speciality> specialityDao) {
        this.specialityDao = specialityDao;
    }
}
