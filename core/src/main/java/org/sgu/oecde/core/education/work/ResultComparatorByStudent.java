/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.work;

import java.util.Comparator;

/**
 *
 * @author ShihovMY
 */
public class ResultComparatorByStudent implements Comparator<AbstractSelfDependentWorkResult>{

    @Override
    public int compare(AbstractSelfDependentWorkResult o1, AbstractSelfDependentWorkResult o2) {
       int id = Integer.valueOf(o1.getId()).compareTo(o2.getId());
       int date = o1.getDate().compareTo(o2.getDate());
       int student = Integer.valueOf(o1.getStudent().getId()).compareTo(o2.getStudent().getId());
       int work = Integer.valueOf(o1.getWork().getId()).compareTo(o2.getWork().getId());
       return student==0?
           (work==0?
               (date==0?id:date):
               work):
               student;
    }

}
