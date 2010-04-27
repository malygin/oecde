/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.estimation;

import java.util.List;
import org.sgu.oecde.core.education.work.IRegularResult;

/**
 *
 * @author ShihovMY
 */
public interface IResultFilter {
    public void check(IRegularResult result,List<EstimatedWorkPoints> workPoints, int sum);
    public EstimatedWorkPoints check(IRegularResult result,int sum);
    public EstimatedWorkPoints check(IRegularResult result);
}
