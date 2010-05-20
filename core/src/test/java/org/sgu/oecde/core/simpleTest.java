/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ShihovMY
 */
public class simpleTest{

    @Ignore
    @Test
    public void getF(){
        String d = "$$ y = C_1 e^{ \\lambda x} + C_2 x e^{ \\lambda x} $$".replaceAll("[\\n\\r\\t\\v]", "").replace("\\", "\\\\");
        System.out.println(d);
    }
}
