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
public class ResultComparatorByCurriculum implements Comparator<AbstractSelfDependentWorkResult>{

    @Override
    public int compare(AbstractSelfDependentWorkResult o1, AbstractSelfDependentWorkResult o2) {
       int curriculum = (Integer.valueOf(o1.getCurriculum().getId()).compareTo(o2.getCurriculum().getId()));
       int id = Integer.valueOf(o1.getId()).compareTo(o2.getId());
       int date = o1.getDate().compareTo(o2.getDate());
       int work = Integer.valueOf(o1.getWork().getId()).compareTo(o2.getWork().getId());
       return curriculum==0?
           (work==0?
               (date==0?id:date):
               work):
               curriculum;
    }

}
