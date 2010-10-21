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

        class a{
            int a=2;
            private int b = 3;

            public int getB() {
                return b;
            }

            public void setB(int b) {
                this.b = b;
            }
        }
        class b extends a{
            int a=2;
            private int b = 3;

            public int getB() {
                return b;
            }

            public void setB(int b) {
                this.b = b;
            }
        }
        a b = new b();
        b.a = 1;
        b.setB(2);
        System.out.println(b.a+"   "+((b)b).a);
        System.out.println(b.getB()+"   "+((b)b).getB());
    }
}
