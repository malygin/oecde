package org.sgu.oecde.core.util;

import java.util.Iterator;
import java.util.List;
import org.sgu.oecde.core.education.CalendarConstantName;
import org.sgu.oecde.core.education.ICalendarConstantName;
import org.sgu.oecde.core.education.CalendarConstants;
import org.sgu.oecde.core.education.dao.ICurrentSemesterDao;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author ShihovMY
 */
public class SemesterGetter{

    private ICurrentSemesterDao csDao;
    private List<CalendarConstants> constants;
    private int currentYear;
    private int semester;
    private boolean reExame;

    protected SemesterGetter() {
    }

    public Integer getSemesterByStudentYear(AbstractStudent student, int semester){
        Assert.notNull(student);
        return student.getGroup().getYear() - semester - getCurrentSemester();
    }

    public Integer[] getSemestersByInt(int semester){
        switch(semester){
            case 0: return Semesters.summer();
            case 1: return Semesters.winter();
            default: return Semesters.winter();
        }
    }

    public int getCalendarYear(int semester){
        return getCurrentYear()-getCurrentSemester()*semester;
    }

    public Integer getCalendarYear(AbstractStudent student, int semester){
        if(student == null)
            return null;
        int year = semester!=0
                ?Math.round(semester/2)+semester%2
                :student.getGroup().getYear();
        return getCurrentYear()-student.getGroup().getYear()+year;
    }

    public void update(CalendarConstants c,String entity){
        csDao.update(c,entity);
    }

    public void save(CalendarConstants c,String entity){
        csDao.save(c,entity);
    }
    
    public void afterPropertiesSet() throws Exception{
        Assert.notNull(csDao,"CurrentSemesterDao is null");
        constants = this.csDao.getCurrentSemester();
        if(!CollectionUtils.isEmpty(constants)){
            Iterator<CalendarConstants> i = constants.iterator();
            while(i.hasNext()){
                CalendarConstants c = i.next();
                if(c==null)
                    i.remove();
                else if(c.getName().equals(CalendarConstantName.semester)){
                    setSemester(c.getValue());
                    i.remove();
                }else if(c.getName().equals(CalendarConstantName.year)){
                    setYear(c.getValue());
                    i.remove();
                }else if(c.getName().equals(CalendarConstantName.reExame)){
                    setReExame(c.getValue());
                    i.remove();
                }
            }
        }else if(CollectionUtils.isEmpty(constants)
                ||!StringUtils.hasText(getConstant(CalendarConstantName.semester))
                ||getCurrentYear()==0){
            csDao.save(new CalendarConstants(CalendarConstantName.year, "2009"),"MainCalendarConstants");
            csDao.save(new CalendarConstants(CalendarConstantName.semester, "0"),"MainCalendarConstants");
            setSemester("0");
            setYear("2009");
        }
    }

    public final String getConstant(ICalendarConstantName name) {
        for(CalendarConstants c:constants){
            if(c.getName().equals(name))
                return c.getValue();
        }
        throw new AssertionError("there is no such constant in constants array with name "+name);
    }

    public void setCsDao(ICurrentSemesterDao csDao) {
        this.csDao = csDao;
    }

    public final int getCurrentSemester() {
        return semester;
    }

    private void setSemester(String semester) {
        if(StringUtils.hasText(semester))
            this.semester = Integer.parseInt(semester);
    }

    public final int getCurrentYear() {
        return currentYear;
    }

    public final boolean isReExame() {
        return reExame;
    }

    private final void setYear(String year) {
        if(StringUtils.hasText(year))
            this.currentYear = Integer.parseInt(year);
    }

    private final void setReExame(String reExame) {
        if(StringUtils.hasText(reExame))
            this.reExame = Boolean.parseBoolean(reExame);
    }
}