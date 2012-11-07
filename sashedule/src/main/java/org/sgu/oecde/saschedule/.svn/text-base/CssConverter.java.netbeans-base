package org.sgu.oecde.saschedule;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.sgu.oecde.de.users.Group;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * 
 * 
 */

@FacesConverter( value="CssConverter" )
class CssConverter implements Converter{

 

    CssConverter() {
        
    }

        public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
            System.out.println("-----------"+string);
            //System.out.println("select "+formBean.getCssSelect());
         //   System.out.println("result "+formBean.getCssResult());
            if (string == null) {
                return null;
            }

                return new Group(Long.parseLong(string));
        }

        public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
            System.out.println("__________________"+((Group)obj).getId());
                if(obj instanceof Group)
                        return Long.toString(((Group)obj).getId());
                return null;


}

  
}