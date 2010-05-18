/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.util;

import org.sgu.oecde.core.education.CalendarConstants;
import org.sgu.oecde.core.education.dao.ICurrentSemesterDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class SemesterGetter{

    private ICurrentSemesterDao csDao;
    private CalendarConstants constants;

    protected SemesterGetter() {
    }

    public Integer getSemesterByStudentYear(AbstractStudent student, int semester){
        if(student == null)
            return null;
        return student.getGroup().getYear() - semester - (this.constants.getSemester());
    }

    public Integer[] getSemestersByInt(int semester){
        switch(semester){
            case 0: return Semesters.summer();
            case 1: return Semesters.winter();
            default: return Semesters.winter();
        }
    }

    public int getCalendarYear(int semester){
        return this.constants.getYear()-this.constants.getSemester()*semester;
    }

    public Integer getCalendarYear(AbstractStudent student, int semester){
        if(student == null)
            return null;
        int year = semester!=0
                ?Math.round(semester/2)+semester%2
                :student.getGroup().getYear();
        return this.constants.getYear()-student.getGroup().getYear()+year;
    }

    public void setCsDao(ICurrentSemesterDao csDao) {
        this.csDao = csDao;
    }
    
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(csDao,"CurrentSemesterDao is null");
        constants = this.csDao.getCurrentSemester();
        Assert.notNull(constants,"semester and year is null");
    }

    public final <T extends CalendarConstants>T getConstants() {
        return (T) constants;
    }
}
