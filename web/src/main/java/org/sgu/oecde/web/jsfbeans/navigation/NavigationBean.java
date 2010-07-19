
package org.sgu.oecde.web.jsfbeans.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 30.06.2010
 * Бин для отображения панели навигации для любых списков
 */
@ManagedBean(name="NavigationBean")
@ViewScoped
public class NavigationBean implements Serializable{
    private List<String> pages=null;
    private String page;
    //элементов на странице
    private int elementOnPage;
    //Количество элементов всего
    private int numElements;

    public NavigationBean() {
    }

    public int getElementOnPage() {
        return elementOnPage;
    }

    public void setElementOnPage(int elementOnPage) {
        this.elementOnPage = elementOnPage;
    }

    public String getPage() {

        if (page==null) page="1";
        return page;
    }

    public void setPage(String page) {
        System.out.println("____________________");
        this.page = page;
    }
    //формирование списка страниц
    public List<String> getPages() {
        if (pages==null){
             pages=new ArrayList();
           //  System.out.println("nE "+numElements);
           //    System.out.println("el "+elementOnPage);
             int forsum=numElements / elementOnPage;
             if ((numElements%elementOnPage)!=0) forsum++;

             for(int i=1;i<=forsum;i++){
                 pages.add(Integer.toString(i));
             }
        } 
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public int getNumElements() {
        return numElements;
    }

    public void setNumElements(int numElements) {
        this.numElements = numElements;
    }

  



}
