package org.sgu.oecde.web;

import javax.activation.MimetypesFileTypeMap;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.web.jsfbeans.admin.ConstantsFormBean;

/**
 *
 * @author ShihovMY
 */
public class simpleTest{

    @Ignore
    @Test
    public void getF() throws NoSuchFieldException{
        ConstantsFormBean c = new ConstantsFormBean();
        c.getClass().getDeclaredField("reExameBeginDate");
    }

//    @Ignore
    @Test
    public void getTypes(){
        System.out.println(new MimetypesFileTypeMap());
    }
}
