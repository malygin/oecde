/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.core.util;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date Aug 22, 2011
 */
public class NumberUtil {
    
    
    static public String NumberToDateFormat(int num){
         if (num<10)  return "0"+num;
         else return num+"";
    }
}
