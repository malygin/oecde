package org.sgu.oecde.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.activation.MimetypesFileTypeMap;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.web.jsfbeans.admin.ConstantsFormBean;

/**
 *
 * @author ShihovMY
 */
public class simpleTest{

    @Ignore
    @Test
    public void getTypes(){

        class a{
            int a=2;
            int b = 3;

            public int getB() {
                return b;
            }

            public void setB(int b) {
                this.b = b;
            }
        }
        class b extends a{
            int a=2;
            int b = 3;

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

    @Ignore
    @Test
    public void test(){
        label:
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (i > 2)
                    break label;
                System.out.print(j);
            }
            System.out.print(" ");
        }
    }

    @Ignore
    @Test
    public void asd(){
        String s = "";
        Boolean b1 = true;
        Boolean b2 = false;
        if((b2=false)|(21%5)>2)
            s+="x";
        if(b1||(b2 = true))
            s+="y";
        if(b2 == true)
            s+="z";
        System.out.println(s);
    }


    @Test
    public void asdasd(){
        System.out.println("Hello World!");
    }
}