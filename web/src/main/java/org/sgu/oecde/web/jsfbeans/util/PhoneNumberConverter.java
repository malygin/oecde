
package org.sgu.oecde.web.jsfbeans.util;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.07.2010
 * конвертер принимающий номер в виде +7(927)-45-45-565, возвращающий 79274545565
 */
@FacesConverter( value="phoneNumberConverter" )
public class PhoneNumberConverter implements Converter, Serializable {
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {

        String str= string.replaceAll("[+()-]", "");
        return Long.valueOf(str);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
      return object.toString();
    }

}