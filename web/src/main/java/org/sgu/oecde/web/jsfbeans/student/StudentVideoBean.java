/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web.jsfbeans.student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



/**
 *
 * @author Pavel
 */
@ManagedBean(name="videoBean")
@ViewScoped
public class StudentVideoBean {
    private String currentFileName="";

    public StudentVideoBean() {
    }
    
    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String currentFileName) {
        this.currentFileName = currentFileName;
    }
    
    
}
