package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Umk;

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
}
