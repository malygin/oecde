/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import static org.junit.Assert.*;

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
