package org.sgu.oecde.web.jsfbeans.journal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.journal.FilterType;

/**
 *
 * @author ShihovMY
 */

@ManagedBean
@SessionScoped
public class JournalFilters implements Serializable{
    private Map<FilterType,List<FilterType.EventForChoise>>types = new HashMap();

    private static final long serialVersionUID = 186L;

    public List<FilterType.EventForChoise> getFilter(FilterType type){
        List<FilterType.EventForChoise> list = types.get(type);
        if(list == null){
            list = type.getAvailableEventsList();
            types.put(type, list);
        }
        return list;
    }
}
