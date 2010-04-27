/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.dao;

import org.sgu.oecde.core.education.CalendarConstants;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface ICurrentSemesterDao {
    public CalendarConstants getCurrentSemester() throws DataAccessException;
}
