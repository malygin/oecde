package org.sgu.oecde.core.education;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.Department;


/**
 * специальность
 * @author ShihovMY
 */
public class Speciality extends BasicItem{

    /**
     * название
     */
    private String name;
    /**
     *  сокращённое имя латинскими буквами
     */
    private String engShort;
    /**
     *  сокращённое имя русскими буквами
     */
    private String rusShort;
    
    private LevelTypeSpeciality levelTypeSpeciality;
    private SpeedTypeSpeciality speedTypeSpeciality;
    private Department department;
    
    private static final long serialVersionUID = 45L;

    public Speciality() {
    }

    public Speciality(Long id) {
        setId(id);
    }

    public Speciality(Long id, String name) {
        setId(id);
        this.name = name;
    }

    /**
     * @return имя специальности
     */
    public String getName() {
        return name+" "+levelTypeSpeciality==null?"":levelTypeSpeciality+" "+speedTypeSpeciality==null?"":speedTypeSpeciality+" ";
    }
    
    public String getSimpleName(){
        return name;
    }

    /**
     * имя специальности
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return сокращённое имя латинскими буквами
     */
    public String getEngShort() {
        return engShort;
    }

    /**
     * сокращённое имя латинскими буквами
     * @param engShort
     */
    public void setEngShort(String engShort) {
        this.engShort = engShort;
    }

    /**
     *  сокращённое имя русскими буквами
     * @return
     */
    public String getRusShort() {
        return rusShort;
    }

    /**
     *  сокращённое имя русскими буквами
     * @param rusShort
     */
    public void setRusShort(String rusShort) {
        this.rusShort = rusShort;
    }

    public LevelTypeSpeciality getLevelTypeSpeciality() {
        return levelTypeSpeciality;
    }

    public void setLevelTypeSpeciality(LevelTypeSpeciality levelTypeSpeciality) {
        this.levelTypeSpeciality = levelTypeSpeciality;
    }

    public SpeedTypeSpeciality getSpeedTypeSpeciality() {
        return speedTypeSpeciality;
    }

    public void setSpeedTypeSpeciality(SpeedTypeSpeciality speedTypeSpeciality) {
        this.speedTypeSpeciality = speedTypeSpeciality;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("название: ").append(name).append("; ");
        return sb.toString();
    }
}
