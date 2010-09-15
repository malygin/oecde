
package org.sgu.oecde.web.jsfbeans.util;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;


@ManagedBean(name="demo")
@SessionScoped
public class Controller implements Serializable{
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


}
