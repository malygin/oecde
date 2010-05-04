package org.sgu.oecde.core.education.estimation;

import java.util.List;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author ShihovMY
 */
public interface IResultFilter extends InitializingBean{
    public void check(AbstractSelfDependentWorkResult result);
    
    public List<EstimatedWorkPoints> getEstimatedWorkPoints() ;

    public int getSum() ;

    public void clear();
}
