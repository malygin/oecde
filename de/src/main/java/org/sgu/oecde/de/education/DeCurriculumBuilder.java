package org.sgu.oecde.de.education;

import org.sgu.oecde.core.education.CurriculumBuilder;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * формирует учебный план дистанционного образования
 * @author ShihovMY
 * @see org.sgu.oecde.core.education.CurriculumBuilder
 */
@Service(value="curriculumBuilder")
public class DeCurriculumBuilder extends CurriculumBuilder<DeCurriculum>{

    public DeCurriculum getInstance(){
        return new DeCurriculum();
    }

    private static final long serialVersionUID = 154L;

    /**
     *
     * @param year
     * @param booleanSemester
     * @param student
     * @return DeCurriculum по году, зимнему/летнему семестру и курсу обучения студента
     */
    public DeCurriculum getInstance(int semester,Student student){
        DeCurriculum c = super.getInstance(getter.getCalendarYear(student, semester),semester);
        setGroup(student, c);
        return c;
    }

    /**
     *
     * @param student
     * @return DeCurriculum по конкретной дате и курсу обучения студента
     */
    public DeCurriculum getInstanceByCurrentDate(Student student,int booleanSemester){
        DeCurriculum c =  getInstance(getter.getCurrentYear(),booleanSemester,student);
        setGroup(student, c);
        return c;
    }

    public DeCurriculum getInstance(Student student,Long id){
        DeCurriculum c =  getInstance(id);
        setGroup(student, c);
        return c;
    }

    private void setGroup(Student student,DeCurriculum c){
        Assert.notNull(student.<Group>getGroup(),"student group can not be null");
        Assert.notNull(student.<Group>getGroup().getSpeciality(),"student speciality can not be null");
        c.setSpeciality(student.<Group>getGroup().getSpeciality());
    }
}