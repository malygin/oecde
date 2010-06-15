package org.sgu.oecde.core.education;

import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.util.SemesterGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class CurriculumBuilder<T extends Curriculum> {

    @Autowired
    protected SemesterGetter getter;

    public AdvancedCurriculum getAdvInstance(){
        return new AdvancedCurriculum();
    }

    public T getInstance(){
        return (T) new Curriculum();
    }

    public T getInstance(Long id){
        Assert.state(id!=0,"id can not be 0");
        Curriculum c = getInstance();
        c.setId(id);
        return (T) c;
    }

    public T getInstance(int year,int semester){
        Assert.state(year!=0,"student can not be 0");
        Curriculum c = getInstance();
        c.setCalendarYear(year);
        c.setSemester(semester);
        return (T) c;
    }

    public T getInstance(int year,int booleanSemester,AbstractStudent student){
        Curriculum c = getInstance();
        Assert.notNull(student,"student can not be null");
        c.setSemester(getter.getSemesterByStudentYear(student, booleanSemester));
        return (T) c;
    }
}
