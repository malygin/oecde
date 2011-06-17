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

    private String page="1";

    private final int maxNumber = 40;

    public List<Umk> getUmkList() {
        if(umkList==null){
            umkList = umkDao.getByPage(maxNumber, Integer.parseInt(page) );
        }
        return umkList;
    }
    
     public int getCountUmk(){
        return umkDao.getCount();
    }

//    public int getCount(){
//        List<Umk> l = getUmkList();
//        return l!=null?umkList.size():0;
//    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

 
    public void setUmkDao(IBasicDao<Umk> umkDao) {
        this.umkDao = umkDao;
    }
}
