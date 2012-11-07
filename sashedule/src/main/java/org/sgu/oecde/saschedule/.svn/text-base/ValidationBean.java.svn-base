package org.sgu.oecde.saschedule;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author Malygin
 */
@ManagedBean(name="validationBean")
@RequestScoped
public class ValidationBean {

    private String progressString="Fill the form please";

    @NotEmpty  
    @Length(min=3,max=12)
    private String name;

    @Email(message="мыло не хорошо!")
    @NotEmpty
    private String email;

    @NotNull
    @Min(18)
    @Max(100)
    private Integer age;

    private String str="zzzz...";

    public ValidationBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }


    public void success() {
        setProgressString(getProgressString() + "(Strored successfully)");
    }

    public String getProgressString() {
        return progressString;
    }

    public void setProgressString(String progressString) {
        this.progressString = progressString;
    }
    public void Action(){
      this.str="123123";
        System.out.println("//////////////////1");
    }
}
