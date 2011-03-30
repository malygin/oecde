package org.sgu.oecde.web.jsfbeans.admin;

import java.util.List;
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
public class UmkListBean {

    @ManagedProperty(value="#{umkDao}")
    private IBasicDao<Umk>umkDao;

    private List<Umk>umkList;

    private int page;

    private final int maxNumber = 40;

    public List<Umk> getUmkList() {
        if(umkList==null){
            umkList = umkDao.getAll();
        }
        return umkList;
    }

    public int getCount(){
        List<Umk> l = getUmkList();
        return l!=null?umkList.size():0;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public void setUmkDao(IBasicDao<Umk> umkDao) {
        this.umkDao = umkDao;
    }
}
