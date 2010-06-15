package org.sgu.oecde.web;

import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.education.resource.Task;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.util.StringUtils;

/**
 *
 * @author ShihovMY
 */
public class AdminManagementFactory {
    private Long id;
    protected ICurriculumDao<DeCurriculum> curriculumDao;
    protected IResourceDao<AbstractResource> resourceDao;

    public DeCurriculum getCurriculum(){
        return curriculumDao.getById(id);
    }

    public Task getTask(){
        return (Task) resourceDao.getById(id);
    }

    public TestEntity getTest(){
        return (TestEntity) resourceDao.getById(id);
    }

    public void setCurriculumDao(ICurriculumDao<DeCurriculum> curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

    public void setId(String id) {
        if(StringUtils.hasText(id))
            this.id = Long.parseLong(id);
    }

    public void setResourceDao(IResourceDao<AbstractResource> resourceDao) {
        this.resourceDao = resourceDao;
    }
}
