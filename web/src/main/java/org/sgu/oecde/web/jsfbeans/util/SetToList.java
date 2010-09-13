package org.sgu.oecde.web.jsfbeans.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="setToList")
@ApplicationScoped
public class SetToList {
    public List getList(Set set){
        if(!CollectionUtils.isEmpty(set))
            return new ArrayList(set);
        else
            return new ArrayList();
    }
}
