package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.education.resource.AbstractResource;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ViewScoped
public class AdminUmkBean implements Serializable{
    
    private Umk umk;

    @ManagedProperty(value="#{umkDao}")
    private IBasicDao<Umk>umkDao;
    private long id;

    private List<AbstractResource> tests;
    private static final long serialVersionUID = 192L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        umk = umkDao.getById(id);
        this.id = id;
    }

    public Umk getUmk() {
        return umk;
    }

    public void setUmkDao(IBasicDao<Umk> umkDao) {
        this.umkDao = umkDao;
    }
    public List<AbstractResource> getTests(){
        if (tests==null){ 
            tests=(List<AbstractResource>) umk.getResources();
             Iterator<AbstractResource> i = tests.iterator();
             while (i.hasNext()) if (i.next()==null) i.remove();               
             Collections.sort(tests, new AbstractResource.OrderByTitle());            
        }
       return tests;
        
    }
    
     

    
    
}
