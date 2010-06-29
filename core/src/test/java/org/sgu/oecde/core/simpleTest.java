package org.sgu.oecde.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author ShihovMY
 */
public class simpleTest{

    @Ignore
    @Test
    public void getF(){
        Calendar c = new GregorianCalendar();
        System.out.println(Calendar.getInstance());
        c.set(2010, Calendar.JUNE, 2,18,45);
        System.out.println(c.after(Calendar.getInstance()));
    }

    @Ignore
    @Test
    public void list(){
        List l = new ArrayList();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(6);
        if(l.size()>5)
        System.out.println(l.subList(0, 6));
    }
}
