package org.sgu.oecde.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.tests.dao.ITestAttemptDao;
import org.sgu.oecde.tests.dao.ITestDao;
import org.sgu.oecde.tests.util.pointsCounter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public class TestAttemptService implements InitializingBean{
    
    ITestAttemptDao<TestAttempt> attemptsDao;

    ITestDao<TestEntity> testDao;


    protected TestAttemptService() {
    }

    public List<AdditionalSelfDependentWork> getStudentsSingleCurriculumAttempts(Curriculum curriculum, String testingDate,AbstractStudent student){
        List<Curriculum> tmpCurList = new ArrayList(1);
        tmpCurList.add(curriculum);
        List<AbstractStudent> sts = new ArrayList(1);
        sts.add(student);
        return getTestsWithAttempts(tmpCurList, testingDate, sts);
    }

    public List<AdditionalSelfDependentWork> getStudentsAllCurriculumAttempts(List<Curriculum> curriculums, String testingDate,AbstractStudent student){
        List<AbstractStudent> sts = new ArrayList(1);
        sts.add(student);
        return getTestsWithAttempts(curriculums, testingDate, sts);
    }

    public List<AdditionalSelfDependentWork>getTestsWithAttempts(List<Curriculum> curriculums,String testingDate,List<AbstractStudent>students){

        List<TestEntity>tests = testDao.getByCurriculums(curriculums, null);
        TestAttempt tmpAttempt = new TestAttempt(testingDate);

        List<TestAttempt> attempts = attemptsDao.getByStudentsAndTests(tests, students, tmpAttempt, true);
        
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

    public List<AdditionalCurriculum> getCurriculumAttemptsCount(List<Curriculum> curriculums, String testingDate,List<AbstractStudent> students){

        List<AdditionalSelfDependentWork> attempts = getTestsWithAttempts(curriculums, testingDate, students);
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
        }
        return list;
    }

    public List<AdditionalSelfDependentWork> getStudentAttempts(List<? extends Curriculum> curriculum, String testingDate,AbstractStudent student){

        List<AbstractStudent> sts = new ArrayList(1);
        sts.add(student);
        List<TestAttempt> attempts = attemptsByStudentAndCurriculums(curriculum, sts, testingDate);

        List<AdditionalSelfDependentWork> additionalTests = new ArrayList<AdditionalSelfDependentWork>();

        attemptsIterator(attempts, new Generator(),additionalTests);
        return additionalTests;
    }

    public List<AdditionalSelfDependentWork> getCurriculumAttempts(Curriculum curriculum, String testingDate,List<? extends AbstractStudent> students){

        
        List<Curriculum> tmpCurList = new ArrayList(1);
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
        List<TestAttempt> attempts = attemptsDao.getByStudentsAndCurriculums(curriculums, students, tmpAttempt, true);
        return attempts;

    }

    private void attemptsIterator(List<TestAttempt> attempts,Generator service,Collection additionalTests){

        List<TestAttempt> oneTestAttempts = null;
        AdditionalSelfDependentWork test = null;
        List<Integer> points = null;
        TestEstimationType previousAttemptType = null;
        TestEntity tmpTest = null;
        ListIterator<TestAttempt> it = attempts.listIterator();

        while(it.hasNext()){
            TestAttempt attempt = it.next();
            if(!attempt.getWork().equals(tmpTest)){
                if(test!=null){
                    test.setPointsForWork(pointsCounter.count(previousAttemptType, points));
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
            if(attempt.getType().equals(TestAttemptType.reTest)||!attempt.getWork().equals(tmpTest))
                points = new ArrayList<Integer>();
            points.add(attempt.getPoints());
            if(!attempt.getType().equals(TestAttemptType.trial))
                oneTestAttempts.add(attempt);

            previousAttemptType = attempt.<TestEntity>getWork().getEstimation();

            if(!it.hasNext()){
                test.setPointsForWork(pointsCounter.count(previousAttemptType, points));
                test.setEstimateAttemptsUsedNumber(oneTestAttempts == null?0:oneTestAttempts.size());
            }
            tmpTest = attempt.getWork();
        }
    }

    public void setAttemptsDao(ITestAttemptDao<TestAttempt> attemptsDao) {
        this.attemptsDao = attemptsDao;
    }

    public void setTestDao(ITestDao<TestEntity> testDao) {
        this.testDao = testDao;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(attemptsDao,"attemptsDao cant't be null in "+this.getClass().getName());
        Assert.notNull(testDao,"testDao cant't be null in "+this.getClass().getName());
    }
}
