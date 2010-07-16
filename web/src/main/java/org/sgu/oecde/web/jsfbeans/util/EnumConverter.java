
package org.sgu.oecde.web.jsfbeans.util;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.07.2010
 * 
 */
@FacesConverter( value="enumConverter" )
public class EnumConverter implements Converter, Serializable {
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
         Enum object = null;
//        if (string == null || string.length() == 0) {
//            return null;
//        } if (string.equalsIgnoreCase("YES") || string.equalsIgnoreCase("NO")) {
//          //  object = YNValue.getValue(string);
//        } else {
//            throw new IllegalArgumentException("Converter error");
//        }
        return object;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
       if (object == null) {
            return null;
        }
        if (object instanceof Enum) {
            Enum current = (Enum) object;
            return current.toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: java.lang.Enum");
        }
    }

}