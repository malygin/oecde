package org.sgu.oecde.de.education;

import org.sgu.oecde.core.education.CurriculumBuilder;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class DeCurriculumBuilder extends CurriculumBuilder<DeCurriculum>{

    public DeCurriculum getInstance(){
        return new DeCurriculum();
    }

    public DeCurriculum getInstance(int year,int booleanSemester,Student student){
        DeCurriculum c = this.getInstance(year,booleanSemester,(AbstractStudent)student);
        Assert.notNull(student.<Group>getGroup(),"student group can not be null");
        Assert.notNull(student.<Group>getGroup().getSpeciality(),"student speciality can not be null");
        c.setSpeciality(student.<Group>getGroup().getSpeciality());
        return c;
    }

    public DeCurriculum getInstanceByCurrentDate(Student student){
        return getInstance(getter.getCurrentSemester(),getter.getCurrentYear(),student);
    }
}
