package org.sgu.oecde.core.util;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public final class SwitchedUserCheker {

    private SwitchedUserCheker() {
        throw new AssertionError();
    }

    public static boolean check(Collection<GrantedAuthority>list){
        if(!CollectionUtils.isEmpty(list)){
            for(GrantedAuthority a:list){
                if("ROLE_PREVIOUS_ADMINISTRATOR".equals(a.getAuthority())){
                    return true;
                }
            }
        }
        return false;
    }
}
