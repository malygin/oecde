
package org.sgu.oecde.web.jsfbeans.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 30.06.2010
 * Бин для отображения панели навигации для любых списков
 */
@ManagedBean(name="NavigationBean")
@ViewScoped
public class NavigationBean implements Serializable{
   // диапазон выводимых значений
    private final int range=2;
    private List<String> pages=null;
    private String page;
    //элементов на странице
    private int elementOnPage;
    //Количество элементов всего
    private int numElements;
    private UIComponent params;
    private Object field;

 

    public NavigationBean() {
    }

    public void SimpleMethod(){

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
        this.page = page;
    }
    //формирование списка страниц
    public List<String> getPages(String onPage, String  num) {
      //  System.out.println(""+onPage+" "+num);
        this.elementOnPage=Integer.parseInt(onPage);
        this.numElements=Integer.parseInt(num);
        if ((pages==null)&&(numElements!=0)){
             pages=new ArrayList();
            //  System.out.println("nE "+numElements);
          //    System.out.println("el "+elementOnPage);
             int forsum=numElements / elementOnPage;
             //если страница всего одна - возвращаем пустой список
             if (forsum==0) return pages;
             if ((numElements%elementOnPage)!=0) forsum++;
           
             int b;
             if ((Integer.parseInt(getPage())-range)>1){
                 pages.add("begin");
                 b=Integer.parseInt(getPage())-range;
             }else b=1;
             int e=(Integer.parseInt(getPage())+range)<forsum?(Integer.parseInt(getPage())+range):forsum;
             
             for(int i=b;i<=e;i++) pages.add(Integer.toString(i));

             if ((Integer.parseInt(getPage())+range)<forsum) pages.add("end");
           
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

    public UIComponent getParams() {
        return params;
    }

    public void setParams(UIComponent params) {
        this.params = params;
        String field = (String) params.getAttributes().get("foo");
    }
    private void lookupFields() {
      field = params.getAttributes().get("foo");
    }
    public String getSomething() {
      if (field == null) {
        lookupFields();
      }
      return "" + field;
    }

public int getNumPages(){
    return (numElements / elementOnPage)+1;
}
  
}
