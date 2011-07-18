
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
@FacesConverter( value="dateConverter" )
public class DateConverter implements Converter, Serializable {
    private static Map<String, String> map=new HashMap<String, String>();
    String[] dateNow;
    String[] dateYesterday;

  
    {
        
          dateNow=org.sgu.oecde.core.util.DateConverter.convert(System.currentTimeMillis()).split(" ");
           Calendar cal = Calendar.getInstance();
           cal.add(Calendar.DATE, -1);
          dateYesterday=org.sgu.oecde.core.util.DateConverter.convert(cal.getTime()).split(" ");
    }

      static{
        map.put("01", "января");
        map.put("02", "февраля");
        map.put("03", "марта");
        map.put("04", "апреля");
        map.put("05", "мая");
        map.put("06", "июня");
        map.put("07", "июля");
        map.put("08", "августа");
        map.put("09", "сентября");
        map.put("10", "октября");
        map.put("11", "ноября");
        map.put("12", "декабря");
      }

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
         Enum object = null;

        return object;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
       if (object == null) {
            return null;
        }else{         
           StringBuilder dateResult= new StringBuilder();
           String[] date=((String)object).split(" ");
           if (date[0]!=null){
               String[] ymd=date[0].split("\\.");

               if (dateNow[0].equals(date[0])) dateResult.append("сегодня");
               else if (dateYesterday[0].equals(date[0])) dateResult.append("вчера");
               else dateResult.append((ymd[2].charAt(0)=='0')?ymd[2].charAt(1):ymd[2])
                       .append(" ").append(map.get(ymd[1]));

               if(date.length>1){
                   String[] hms=date[1].split(":");
                   dateResult.append(" "+hms[0]+":"+hms[1]);
               }
           }

           return dateResult.toString();
        }
    }

}