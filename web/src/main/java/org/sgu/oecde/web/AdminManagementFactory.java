package org.sgu.oecde.web;

import javax.annotation.Resource;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.education.resource.Task;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author ShihovMY
 */
public class AdminManagementFactory {
    private Long id;
    @Resource
    protected ICurriculumDao<DeCurriculum> curriculumDao;
    @Resource
    protected IResourceDao<AbstractResource> resourceDao;

    private AdminManagementFactory() {
    }

    public DeCurriculum getCurriculum(){
        return curriculumDao.getById(id);
    }

    public Task getTask(){
        return (Task) resourceDao.getById(id);
    }

    public TestEntity getTest(){
        return (TestEntity) resourceDao.getById(id);
    }

    public void setId(String id) {
        if(StringUtils.hasText(id))
            this.id = Long.parseLong(id);
    }
}
