/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.dao;

import java.util.Collection;
import java.util.List;
import org.sgu.oecde.core.IAdvancedDao;
import org.sgu.oecde.core.education.work.Estimate;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface IEstimateDao<T extends Estimate> extends IAdvancedDao<T>{
    @SuppressWarnings("unchecked")
    public List<T> getGroupGrades(Collection students,Collection curriculums) throws DataAccessException;
}
