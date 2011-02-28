package org.sgu.oecde.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.Externalizable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import javax.activation.MimetypesFileTypeMap;
import org.apache.xmlbeans.impl.piccolo.util.RecursionException;
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
        Formatter f = new Formatter();
        File s;
        f.format("%S", "hello123");
    }
}