package org.sgu.oecde.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.tests.dao.ITestAttemptDao;
import org.sgu.oecde.tests.util.pointsCounter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@Service
public class TestAttemptService implements InitializingBean{

    @Autowired
    ITestAttemptDao<TestAttempt> testAttemptDao;

    @Autowired
    IResourceDao<TestEntity> resourceDao;


    protected TestAttemptService() {
    }

    public List<AdditionalSelfDependentWork> getStudentsSingleCurriculumAttempts(Curriculum curriculum, String testingDate,AbstractStudent student){
        List<Curriculum> tmpCurList = new LinkedList();
        tmpCurList.add(curriculum);
        List<AbstractStudent> sts = new LinkedList();
        sts.add(student);
        return getTestsWithAttempts(tmpCurList, testingDate, sts,null);
    }

    public List<AdditionalSelfDependentWork> getStudentsAllCurriculumAttempts(List<? extends Curriculum> curriculums, String testingDate,AbstractStudent student,Boolean concluding){
        List<AbstractStudent> sts = new LinkedList();
        sts.add(student);
        return getTestsWithAttempts(curriculums, testingDate, sts,concluding);
    }

    public List<AdditionalSelfDependentWork>getTestsWithAttempts(List<? extends Curriculum> curriculums,String testingDate,List<? extends AbstractStudent>students, Boolean concluding){
        TestEntity e = null;
        if(concluding!=null){
            e = new TestEntity();
            if(concluding)
                e.setType(TestType.concluding);
            else
                e.setType(TestType.regular);
        }
        List<TestEntity>tests = resourceDao.getResourceByCurriculums(curriculums, e,TestEntity.class);
        TestAttempt tmpAttempt = new TestAttempt(testingDate);

        List<TestAttempt> attempts = testAttemptDao.getByStudentsAndTests(tests, students, tmpAttempt, true);
        
        AdditionalSelfDependentWork test = null;

        final HashMap<TestEntity,AdditionalSelfDependentWork> testMap = new LinkedHashMap<TestEntity, AdditionalSelfDependentWork>();
        for(TestEntity t:tests){
            test = new AdditionalSelfDependentWork(t);
            testMap.put(t, test);
        }

        attemptsIterator(attempts, new Generator(){
            protected AdditionalSelfDependentWork generate(Object object){
                return testMap.get(((TestAttempt)object).<TestEntity>getWork());
            }
        },testMap.values());
        return new ArrayList(testMap.values());
    }

    public List<AdditionalCurriculum>getCurriculumAttemptsCount(Curriculum curriculum, String testingDate,List<? extends AbstractStudent> students){
        List<Curriculum>c = new LinkedList();
        c.add(curriculum);
        return getAttemptsCount(c, testingDate, students);
    }

    public List<AdditionalCurriculum>getStudentAttemptsCount(List<? extends Curriculum> curriculums, String testingDate,AbstractStudent student){
        List<AbstractStudent>st = new LinkedList();
        st.add(student);
        return getAttemptsCount(curriculums, testingDate, st);
    }

    public List<AdditionalCurriculum> getAttemptsCount(List<? extends Curriculum> curriculums, String testingDate,List<? extends AbstractStudent> students){

        List<AdditionalSelfDependentWork> attempts = getTestsWithAttempts(curriculums, testingDate, students,null);
        int count = 0;
        boolean wasPassed = false;
        int attemptsNumber = 0;
        List<AdditionalCurriculum>list = new ArrayList<AdditionalCurriculum>();
        AdditionalCurriculum addCurriculum = null;
        Curriculum temp = null;
        TestEntity testTemp = null;
        for(AdditionalSelfDependentWork attempt:attempts) {
            //если лист не содержит полученную дисциплину, то дальше
            if(!temp.equals(attempt.getCurriculum())){
                if (addCurriculum!=null){
                    //запихивает в умк полученные значения
                       addCurriculum.setTestsCount(count);
                       addCurriculum.setPassedTests(attemptsNumber);
                       addCurriculum.setCurriculum(temp);
                }
                addCurriculum = new AdditionalCurriculum();
                list.add(addCurriculum);
                //начинает считать общее количество тестов для дисциплины
                count=1;
                //пройден ли был тест
                wasPassed = (CollectionUtils.isEmpty(attempt.getResults()));
                //если да, то начинает считать прохождения
                attemptsNumber = wasPassed?1:0;
            }else{
                if(testTemp.equals(attempt.getWork())){
                    //очередная попытка всё того же теста. если до этого не было полученно данных о том,
                    // что он пройден, то у текущей попытке это выянсяется
                    wasPassed = !wasPassed?(CollectionUtils.isEmpty(attempt.getResults())):wasPassed;
                }else{
                    //продолжается подсчёт количества тестов для дисциплины
                    count++;
                    //если он был пройден, то количество пройденных тестов увеличивается
                    wasPassed = (CollectionUtils.isEmpty(attempt.getResults()));
                    attemptsNumber +=(wasPassed?1:0);
                }
            }
            temp = attempt.getCurriculum();
            testTemp = attempt.getWork();
        }
        if (addCurriculum!=null){
            //запихивает в умк полученные значения
               addCurriculum.setTestsCount(count);
               addCurriculum.setPassedTests(attemptsNumber);
               addCurriculum.setCurriculum(temp);
        }
        return list;
    }

    public List<AdditionalSelfDependentWork> getStudentAttempts(List<? extends Curriculum> curriculum, String testingDate,AbstractStudent student){

        List<AbstractStudent> sts = new LinkedList();
        sts.add(student);
        List<TestAttempt> attempts = attemptsByStudentAndCurriculums(curriculum, sts, testingDate);

        List<AdditionalSelfDependentWork> additionalTests = new ArrayList<AdditionalSelfDependentWork>();

        attemptsIterator(attempts, new Generator(),additionalTests);
        return additionalTests;
    }

    public List<AdditionalSelfDependentWork> getCurriculumAttempts(Curriculum curriculum, String testingDate,List<? extends AbstractStudent> students){

        
        List<Curriculum> tmpCurList = new LinkedList();
        tmpCurList.add(curriculum);
        List<AdditionalSelfDependentWork> additionalTests = new ArrayList<AdditionalSelfDependentWork>();

        List<TestAttempt> attempts = attemptsByStudentAndCurriculums(tmpCurList, students, testingDate);

        attemptsIterator(attempts, new Generator(),additionalTests);

        return additionalTests;
    }
    
    protected class Generator{
        protected AdditionalSelfDependentWork generate(Object object){return new AdditionalSelfDependentWork(); };
    }

    private List<TestAttempt> attemptsByStudentAndCurriculums(List<? extends Curriculum>curriculums,List<? extends AbstractStudent>students, String testingDate){
        TestAttempt tmpAttempt = new TestAttempt(testingDate);
        List<TestAttempt> attempts = testAttemptDao.getByStudentsAndCurriculums(curriculums, students, tmpAttempt);
        return attempts;

    }

    private void attemptsIterator(List<TestAttempt> attempts,Generator service,Collection additionalTests){
        Collections.sort(attempts);
        List<TestAttempt> oneTestAttempts = null;
        AdditionalSelfDependentWork test = null;
        List<Integer> points = null;
        TestEstimationType previousEstimationType = null;
        TestAttemptType previousAttemptType = null;
        TestEntity tmpTest = null;
        ListIterator<TestAttempt> it = attempts.listIterator();

        while(it.hasNext()){
            TestAttempt attempt = it.next();
            if(attempt.getType().equals(TestAttemptType.trial))
                continue;
            if(!attempt.getWork().equals(tmpTest)){
                if(test!=null){
                    test.setPointsForWork(pointsCounter.count(previousEstimationType, points));
                    test.setEstimateAttemptsUsedNumber(oneTestAttempts == null?0:oneTestAttempts.size());
                }
                oneTestAttempts = new ArrayList<TestAttempt>();
                test = service.generate(attempt);
                if(additionalTests instanceof List)
                    additionalTests.add(test);
                test.setResults(oneTestAttempts);
                test.setWork(attempt.getWork());
                test.setCurriculum(attempt.getCurriculum());
            }
            if(!attempt.getType().equals(previousAttemptType)||!attempt.getWork().equals(tmpTest))
                points = new ArrayList<Integer>();

            points.add(attempt.getPoints());

            if(!attempt.getType().equals(TestAttemptType.trial))
                oneTestAttempts.add(attempt);

            previousEstimationType = attempt.<TestEntity>getWork().getEstimation();
            previousAttemptType = attempt.getType();

            if(!it.hasNext()){
                test.setPointsForWork(pointsCounter.count(previousEstimationType, points));
                test.setEstimateAttemptsUsedNumber(oneTestAttempts == null?0:oneTestAttempts.size());
            }
            tmpTest = attempt.getWork();
        }
    }

    public void setAttemptsDao(ITestAttemptDao<TestAttempt> attemptsDao) {
        this.testAttemptDao = attemptsDao;
    }

    public void setTestDao(IResourceDao<TestEntity> testDao) {
        this.resourceDao = testDao;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(testAttemptDao,"attemptsDao cant't be null in "+this.getClass().getName());
        Assert.notNull(resourceDao,"testDao cant't be null in "+this.getClass().getName());
    }
}
