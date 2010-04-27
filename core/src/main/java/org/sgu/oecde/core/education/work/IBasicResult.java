package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 *
 * @author ShihovMY
 */
public interface  IBasicResult {
    public String getDate();

    public AbstractStudent getStudent();

    public Curriculum getCurriculum() ;

    public int getId();
}
