package org.sgu.oecde.core.util;

import java.util.List;
import java.util.Map;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.education.CalendarConstantName;
import org.sgu.oecde.core.education.ICalendarConstantName;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * хранит текущий год, семестр и идёт ли переэкзаменовка.
 * содержит методы по получению этих констант, а так же по получению семестров и годов по
 * различным параметрам
 * @author ShihovMY
 */
public class SemesterGetter extends StringConstantsGetter{

    /**
     * текущий год
     */
    private int currentYear;
    /**
     * текущий семестр
     */
    private int semester;

    private static final long serialVersionUID = 118L;

    protected SemesterGetter() {
    }

    /**
     *
     * @param student - студент
     * @param semester - семестр
     * @return семестр по курсу обучения студента и параметру semester
     */
    public Integer getSemesterByStudentYear(AbstractStudent student, int semester){
        Assert.notNull(student);
        return student.getGroup().getYear()*2 - semester - getCurrentSemester();
    }

    /**
     *
     * @param semester
     * @return массив зимних или летних семестров по semester, который равено 0 или 1
     */
    public Integer[] getSemestersByInt(int semester){
        switch(semester){
            case 0: return Semesters.summer();
            case 1: return Semesters.winter();
            default: return Semesters.winter();
        }
    }

    /**
     *
     * @param semester
     * @return год по semester, который равено 0 или 1
     */
    public int getCalendarYear(int semester){
        return getCurrentYear()-getCurrentSemester()*semester;
    }

    /**
     *
     * @param student
     * @param semester
     * @return год по курсу студента и semester
     */
    public Integer getCalendarYear(AbstractStudent student, int semester){
        if(student == null)
            return null;
        int year = semester!=0
                ?Math.round(semester/2)+semester%2
                :student.getGroup().getYear();
        return getCurrentYear()-student.getGroup().getYear()+year;
    }

    /**
     * получает константы из бд и вносит их значения в поля.
     * если же значений в бд нет, то заносит значения по умолчанию
     */
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
                else
                    constants.put(name,value);
            }
        }else{
            setSemester(CalendarConstantName.year.getDefault());
            setYear(CalendarConstantName.semester.getDefault());
        }
    }

    /**
     *
     * @return текущий семестр
     */
    public final int getCurrentSemester() {
        return semester;
    }

    /**
     *
     * @return  текущий год
     */
    public final int getCurrentYear() {
        return currentYear;
    }

    /**
     * год
     * @param year
     */
    private final void setYear(Object year) {
        if(year!=null&&!year.toString().isEmpty())
            this.currentYear = Integer.parseInt(year.toString());
    }

    /**
     * семестр
     * @param semester
     */
    private void setSemester(Object semester) {
        if(semester!=null&&!semester.toString().isEmpty())
            this.semester = Integer.parseInt(semester.toString());
    }
}