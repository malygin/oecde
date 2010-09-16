
package org.sgu.oecde.web.jsfbeans.util;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;


@ManagedBean(name="demo")
@SessionScoped
public class DemoBean implements Serializable{
    private String date="10.09.2010";
    private String status = "[Unknown]";
    
    public void drag() {
         status = "[Drag]";
    }

    public void drop() {
        status = "[Drop]";
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        System.out.println("!"+date);
    }


}
