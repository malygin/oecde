package org.sgu.oecde.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.util.DateConverter;

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

    @Ignore
    @Test
    public void dates(){
        String s = DateConverter.currentDate();
        String s2 = new String("2010.04.06 13:51:09");
        System.out.println(s.compareTo(s2));
	}

//    @Ignore
    @Test
    public void strun(){
        String s = "asdasd  (уск)";
        String s2 = "asdasd  (ус)";
        System.out.println(s.contains("уск"));
        System.out.println(s2.contains("уск"));
}
}
