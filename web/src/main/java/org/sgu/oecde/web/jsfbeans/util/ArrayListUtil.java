/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web.jsfbeans.util;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date Oct 26, 2011
 */
public class ArrayListUtil {
     public static boolean containsElement(Object[] array, Object element) {
    //     System.out.println("success!");
         for(Object o:array){
     //        System.out.println(o.toString()+" "+element);
             if (((String)o).equals(element)) return true;}
       return false;      
             
    }
}
