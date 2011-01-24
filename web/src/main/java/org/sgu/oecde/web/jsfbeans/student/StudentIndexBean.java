package org.sgu.oecde.web.jsfbeans.student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.controlworks.ControlWorkCalendarConstantName;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.ResourceService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ViewScoped
public class StudentIndexBean {

    @ManagedProperty(value="#{cwDatesGetter}")
    private StringConstantsGetter cwDatesGetter;

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    public Object getDate(String constantName){
        return cwDatesGetter.getConstant(ControlWorkCalendarConstantName.valueOf(constantName));
    }
    
    public String getTestClosingDate(){
        Student student = studentSessionBean.getStudent();
        if(student.getGroup().getYear()==1
                ||(student.getGroup().getYear()==2
                &&(!student.<Group>getGroup().getSpeciality().getName().contains("ускор")
                   ||!student.<Group>getGroup().getSpeciality().getName().contains("сокр"))))
            return resourceService.getSimpleSpecialitiesTestsClosing();
        else
            return resourceService.getRegularTestEndDate();
    }

    public void setCwDatesGetter(StringConstantsGetter cwDatesGetter) {
        this.cwDatesGetter = cwDatesGetter;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public void setStudentSessionBean(StudentSessionBean studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }
}