package org.sgu.oecde.web;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.controlworks.estimation.ControlWorkFilter;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.dao.IResultDao;
import org.sgu.oecde.core.education.estimation.EstimateFilter;
import org.sgu.oecde.core.education.estimation.ResultPreFilter;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.tests.estimation.TestFilter;
import org.springframework.util.StringUtils;

/**
 *
 * @author ShihovMY
 */
public class EducationFactory {

    @Resource
    protected SemesterGetter sg;
    @Resource
    protected TestAttemptService testAttemptService;
    @Resource
    protected ControlWorkService cwService;
    @Resource
    protected DeCurriculumBuilder curriculumBuilder;
    @Resource
    protected ResultPreFilter preFilter;
    @Resource
    protected EstimateFilter estimateFilter;
    @Resource
    protected TestFilter testFilter;
    @Resource
    protected ControlWorkFilter controlWorkFilter;
    @Resource
    protected ICurriculumDao<DeCurriculum> curriculumDao;
    @Resource
    protected IResultDao<AbstractResult>resultDao;
    @Resource
    protected IResultDao<Estimate>estimateDao;
    @Resource
    protected IResourceDao<AbstractResource> resourceDao;

    protected int semester;

    protected Long id;

    protected Long curriculumId;

    protected Student student;

    public <T extends AbstractResource>T getResource(DeCurriculum c,AbstractResource r,Class clazz){
        if(c==null)
            return null;
        List<DeCurriculum> l = new LinkedList<DeCurriculum>();
        l.add(c);
        List<AbstractResource> rs = resourceDao.getResourceByCurriculums(l, r, clazz);
        if(rs.isEmpty())
            return null;
        return (T) rs.get(0);
    }

    public void setId(String id) {
        if(StringUtils.hasText(id))
            this.id = Long.parseLong(id);
    }

    public void setCurriculumId(String curriculumId) {
        if(StringUtils.hasText(curriculumId))
            this.id = Long.parseLong(curriculumId);
    }

    public void setSemester(String semester) {
        if(StringUtils.hasText(semester))
            this.id = Long.parseLong(semester);
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
