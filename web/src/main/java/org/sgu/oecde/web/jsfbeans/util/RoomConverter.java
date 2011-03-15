
package org.sgu.oecde.web.jsfbeans.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 08.02.2011
 * конфертер который приводит дату вида 2009.01.01 13:23:12 к виду "1 января 13:23"
 */
@FacesConverter( value="roomConverter" )
public class RoomConverter implements Converter, Serializable {
  
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
         Enum object = null;
        return object;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
       if (object == null) {
            return null;
        }else{         
          Integer i=(Integer)object;
            i=i/13+1;         
            return i.toString();
           }

           
        }
    }

