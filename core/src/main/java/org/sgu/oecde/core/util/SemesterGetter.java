package org.sgu.oecde.core.util;

import java.util.List;
import java.util.Map;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.education.CalendarConstantName;
import org.sgu.oecde.core.education.ICalendarConstantName;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author ShihovMY
 */
public class SemesterGetter extends StringConstantsGetter{

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
    
    @Override
    public void fillConstantsMap(){
        List<Map> c = getDao().getConstants(getEntityName());
        if(!CollectionUtils.isEmpty(c)){
            for(Map m:c){
                ICalendarConstantName name = (ICalendarConstantName)m.get(key);
                String value = (String)m.get(this.value);
                if(CalendarConstantName.semester.equals(name))
                    setSemester(value);
                if(CalendarConstantName.year.equals(name))
                    setYear(value);
                if(CalendarConstantName.reExame.equals(name))
                    setReExame(value);
                else
                    constants.put(name,value);
            }
        }else if(CollectionUtils.isEmpty(constants)
                ||!StringUtils.hasText(getConstant(CalendarConstantName.semester))
                ||getCurrentYear()==0){
            save(CalendarConstantName.year,"2009",getEntityName());
            save(CalendarConstantName.semester,"0",getEntityName());
            setSemester("0");
            setYear("2009");
        }
    }

    public final int getCurrentSemester() {
        return semester;
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

    private void setSemester(String semester) {
        if(StringUtils.hasText(semester))
            this.semester = Integer.parseInt(semester);
    }
}