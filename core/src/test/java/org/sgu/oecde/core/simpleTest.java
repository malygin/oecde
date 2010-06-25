package org.sgu.oecde.core;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
}
