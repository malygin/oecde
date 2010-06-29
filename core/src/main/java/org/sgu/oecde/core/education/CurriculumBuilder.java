package org.sgu.oecde.core.education;

import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.util.SemesterGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * формирует Curriculum
 * @author ShihovMY
 * @param <T> extends Curriculum
 * @see org.sgu.oecde.core.education.Curriculum
 */
public class CurriculumBuilder<T extends Curriculum> {

    /**
     * @see org.sgu.oecde.core.util.SemesterGetter
     */
    @Autowired
    protected SemesterGetter getter;

    public AdvancedCurriculum getAdvInstance(){
        return new AdvancedCurriculum();
    }

    /**
     *
     * @return Curriculum
     */
    public T getInstance(){
        return (T) new Curriculum();
    }

    /**
     *
     * @param id
     * @return Curriculum по айди
     */
    public T getInstance(Long id){
        Assert.state(id!=0,"id can not be 0");
        Curriculum c = getInstance();
        c.setId(id);
        return (T) c;
    }

    /**
     *
     * @param year
     * @param semester
     * @return Curriculum по году и семестру
     */
    public T getInstance(int year,int semester){
        Assert.state(year!=0,"student can not be 0");
        Curriculum c = getInstance();
        c.setCalendarYear(year);
        c.setSemester(semester);
        return (T) c;
    }

    /**
     *
     * @param year
     * @param booleanSemester
     * @param student
     * @return Curriculum по году, параметру,зимний или летний семестр, и студенту
     * @see org.sgu.oecde.core.util.SemesterGetter#getSemesterByStudentYear(org.sgu.oecde.core.users.AbstractStudent, int) 
     */
    public T getInstance(int year,int booleanSemester,AbstractStudent student){
        Curriculum c = getInstance();
        Assert.notNull(student,"student can not be null");
        c.setSemester(getter.getSemesterByStudentYear(student, booleanSemester));
        c.setCalendarYear(year);
        return (T) c;
    }
}