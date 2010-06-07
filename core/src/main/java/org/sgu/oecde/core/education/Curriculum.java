package org.sgu.oecde.core.education;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class Curriculum extends BasicItem{

    private Integer semester;
    private Integer calendarYear;
    private Set<TeacherToGroup> teacherToGroups;
    private Umk umk;
    private ExaminationType examinationType;
    private static final long serialVersionUID = 59L;

    public Curriculum() {
    }

    public Curriculum(Integer id) {
        setId(id);
    }
    /**
     * @return семестр
     */
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    /**
     * @return год
     */
    public Integer getCalendarYear() {
        return calendarYear;
    }

    public void setCalendarYear(Integer year) {
        this.calendarYear = year;
    }

    public Set<TeacherToGroup> getTeacherToGroups() {
        return teacherToGroups;
    }

    public void setTeacherToGroups(Set<TeacherToGroup> teacherToGroups) {
        this.teacherToGroups = teacherToGroups;
    }

    public<T extends Umk> T getUmk() {
        return (T) umk;
    }

    public void setUmk(Umk umk) {
        this.umk = umk;
    }

    public ExaminationType getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(ExaminationType examinationType) {
        this.examinationType = examinationType;
    }

    /**
	 * 
	 * @return 
	 */
	@Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Semestr: ").append(this.semester).append("; ");
        sb.append("Year: ").append(this.calendarYear).append("; ");
        return sb.toString();
    }
}
