package org.sgu.oecde.web;

import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.web.admin.ConstantsFormBean;

/**
 *
 * @author ShihovMY
 */
public class simpleTest{

//    @Ignore
    @Test
    public void getF() throws NoSuchFieldException{
        ConstantsFormBean c = new ConstantsFormBean();
        c.getClass().getDeclaredField("reExameBeginDate");
    }
}
