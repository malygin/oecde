/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.tests;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.de.users.Student;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 */
public class simpleTest{

//    @Ignore
    @Test
    public void getF(){

        Set<Curriculum> s = new HashSet<Curriculum>();
        s.add(new Curriculum(1L));
        s.add(new Curriculum(5L));
        s.add(new Curriculum(4L));
        System.out.println(s);
        List l = new ArrayList(s);
        System.out.println(l);
        Collections.shuffle(l);
        System.out.println(new LinkedHashSet(l));
    }
    
}
