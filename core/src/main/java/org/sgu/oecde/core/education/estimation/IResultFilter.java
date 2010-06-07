package org.sgu.oecde.core.education.estimation;

import org.sgu.oecde.core.education.work.AbstractResult;

/**
 *
 * @author ShihovMY
 */
public interface IResultFilter {

    public void check(AbstractResult result,Points points);

    public void setPoints(Points points);
}
