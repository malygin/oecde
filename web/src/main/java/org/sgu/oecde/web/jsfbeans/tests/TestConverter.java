
package org.sgu.oecde.web.jsfbeans.tests;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.sgu.oecde.tests.Answer;
import org.sgu.oecde.tests.GivenAnswer;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 27.09.2010
 * 
 */
@FacesConverter( value="testConverter" )
public class TestConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {        
         Answer answer=new Answer();
         answer.setId(Long.parseLong(string));
         return answer;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        System.out.println(((Answer) object).getId().toString());
        return ((Answer) object).getId().toString();
    }

}

