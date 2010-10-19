package org.sgu.oecde.web;

import java.util.List;
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
        Integer i1 = 1000;
        Integer i2 = 1000;
        System.out.println(i1 == i2);
        System.out.println(i1 == 1000);
        Integer i11 = 10;
        Integer i12 = 10;
        System.out.println(i11 == i12);
        System.out.println(i11 == 10);
         System.out.printf("%1$b", "123");
    }
}
