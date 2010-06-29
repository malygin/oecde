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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * сервис по работе с прохождениями тестов
 * @author ShihovMY
 */
@Service
public class TestAttemptService{

    /**
     * дао прохождений тестов
     */
    @Autowired
    ITestAttemptDao<TestAttempt> testAttemptDao;

    /**
     * дао тестов
     */
    @Autowired
    IResourceDao<TestEntity> resourceDao;


    protected TestAttemptService() {
    }

    /**
     * получает тесты по одной дисциплине для одного студента вместе с прохождениями
     * @param curriculum - учебный план
     * @param testingDate - дата прохождения
     * @param student - студент
     * @return лист расширенных самостоятельных работ
     * @see #getTestsWithAttempts(java.util.List, java.lang.String, java.util.List, java.lang.Boolean)
     */
    public List<AdditionalSelfDependentWork> getStudentsSingleCurriculumAttempts(Curriculum curriculum, String testingDate,AbstractStudent student){
        List<Curriculum> tmpCurList = new LinkedList();
        tmpCurList.add(curriculum);
        List<AbstractStudent> sts = new LinkedList();
        sts.add(student);
        return getTestsWithAttempts(tmpCurList, testingDate, sts,null);
    }

    /**
     * получает тесты по всем данным дисциплинам одного студента вместе с его прохождениями
     * @param curriculums - учебные планы
     * @param testingDate - дата прохождения
     * @param student - студент
     * @param concluding - итоговый ли
     * @return лист расширенных самостоятельных работ
     * @see #getTestsWithAttempts(java.util.List, java.lang.String, java.util.List, java.lang.Boolean)
     */
    public List<AdditionalSelfDependentWork> getStudentsAllCurriculumAttempts(List<? extends Curriculum> curriculums, String testingDate,AbstractStudent student,Boolean concluding){
        List<AbstractStudent> sts = new LinkedList();
        sts.add(student);
        return getTestsWithAttempts(curriculums, testingDate, sts,concluding);
    }

    /**
     * получает все тесты по данным дисциплинам данных студентов.
     * Сначала получает тесты по данным дисциплинам данного типа. затем прохождения по данным тестам
     * данных студентов. Далее обрабатывает результаты тестов и крепит данные к расширенным самостоятельным
     * работам.
     * @param curriculums - учебные планы HashMap с расширенными самостоятельлными работами, которые
     * содержат ссылки на полученные ранее тесты.
     * @param testingDate - дата
     * @param students - студенты
     * @param concluding - итоговый ли
     * @return лист расширенных самостоятельных работ
     * @see #attemptsIterator(java.util.List, org.sgu.oecde.tests.TestAttemptService.Generator, java.util.Collection)
     */
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
        if(CollectionUtils.isEmpty(tests))
            return new ArrayList();
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

    /**
     * получает количество тестов по данной дисциплине для данных студенов и вносит их в
     * расширенный учебный план, который имеет ссылку на данный
     * @param curriculum - учебный план
     * @param testingDate - дата
     * @param students - студенты
     * @return лист расширенных учебных планов
     * @see #getAttemptsCount(java.util.List, java.lang.String, java.util.List)
     */
    public List<AdditionalCurriculum>getCurriculumAttemptsCount(Curriculum curriculum, String testingDate,List<? extends AbstractStudent> students){
        List<Curriculum>c = new LinkedList();
        c.add(curriculum);
        return getAttemptsCount(c, testingDate, students);
    }

    /**
     * получает количество тестов по данным дисциплинам для данного студента и вносит их в
     * расширенные учебные планф, которые имеют ссылки на данные
     * @param curriculums - учебные планы
     * @param testingDate - дата
     * @param student - студент
     * @return лист расширенных учебных планов
     * @see #getAttemptsCount(java.util.List, java.lang.String, java.util.List)
     */
    public List<AdditionalCurriculum>getStudentAttemptsCount(List<? extends Curriculum> curriculums, String testingDate,AbstractStudent student){
        List<AbstractStudent>st = new LinkedList();
        st.add(student);
        return getAttemptsCount(curriculums, testingDate, st);
    }

    /**
     * Получает лист расширенных самостоятельных работ, котрые содержат результаты прохождений.
     * Пробегается по ним и для каждой следующей дисциплины подсчитывает количество тестов
     * и прохождений
     * @param curriculums -учебные планы
     * @param testingDate - дата
     * @param students - студенты
     * @return лист расширенных учебных планов
     * @see #getTestsWithAttempts(java.util.List, java.lang.String, java.util.List, java.lang.Boolean)
     */
    public List<AdditionalCurriculum> getAttemptsCount(List<? extends Curriculum> curriculums, String testingDate,List<? extends AbstractStudent> students){

        List<AdditionalSelfDependentWork> attempts = getTestsWithAttempts(curriculums, testingDate, students,null);
        int count = 0;
        boolean wasPassed = false;
        int attemptsNumber = 0;
        List<AdditionalCurriculum>list = new ArrayList<AdditionalCurriculum>();
        for(Curriculum c:curriculums){
            list.add(new AdditionalCurriculum(c));
        }
        AdditionalCurriculum addCurriculum = null;
        Curriculum temp = null;
        TestEntity testTemp = null;
        for(AdditionalSelfDependentWork attempt:attempts) {
            if(attempt.getCurriculum()==null||attempt.getWork()==null)
                continue;
            //если лист не содержит полученную дисциплину, то дальше
            if(!attempt.getCurriculum().equals(temp)){
                if (addCurriculum!=null){
                    //запихивает в умк полученные значения
                       addCurriculum.setTestsCount(count);
                       addCurriculum.setPassedTests(attemptsNumber);
                       addCurriculum.setCurriculum(temp);
                }
                addCurriculum = list.get(list.indexOf(attempt.getCurriculum()));
                list.add(addCurriculum);
                //начинает считать общее количество тестов для дисциплины
                count=1;
                //пройден ли был тест
                wasPassed = (CollectionUtils.isEmpty(attempt.getResults()));
                //если да, то начинает считать прохождения
                attemptsNumber = wasPassed?1:0;
            }else{
                if(attempt.getWork().equals(testTemp)){
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

    /**
     * получает результаты тестов студента по данным дисциплинам
     * @param curriculums - учебные планы
     * @param testingDate - дата
     * @param student - студент
     * @return лист расширенных самостоятельных работ
     * @see #attemptsIterator(java.util.List, org.sgu.oecde.tests.TestAttemptService.Generator, java.util.Collection)
     */
    public List<AdditionalSelfDependentWork> getStudentAttempts(List<? extends Curriculum> curriculums, String testingDate,AbstractStudent student){

        List<AbstractStudent> sts = new LinkedList();
        sts.add(student);
        List<TestAttempt> attempts = attemptsByStudentAndCurriculums(curriculums, sts, testingDate);

        List<AdditionalSelfDependentWork> additionalTests = new ArrayList<AdditionalSelfDependentWork>();

        attemptsIterator(attempts, new Generator(),additionalTests);
        return additionalTests;
    }

    /**
     * получает результаты тестов по данной дисциплине для данных студентов
     * @param curriculum - учебный план
     * @param testingDate - дата
     * @param students - студенты
     * @return лист расширенных самостоятельных работ
     * @see #attemptsIterator(java.util.List, org.sgu.oecde.tests.TestAttemptService.Generator, java.util.Collection)
     */
    public List<AdditionalSelfDependentWork> getCurriculumAttempts(Curriculum curriculum, String testingDate,List<? extends AbstractStudent> students){

        
        List<Curriculum> tmpCurList = new LinkedList();
        tmpCurList.add(curriculum);
        List<AdditionalSelfDependentWork> additionalTests = new ArrayList<AdditionalSelfDependentWork>();

        List<TestAttempt> attempts = attemptsByStudentAndCurriculums(tmpCurList, students, testingDate);

        attemptsIterator(attempts, new Generator(),additionalTests);

        return additionalTests;
    }

    /**
     * инстанциирует расширенную самостятельную работу
     */
    protected class Generator{
        protected AdditionalSelfDependentWork generate(Object object){return new AdditionalSelfDependentWork(); };
    }

    /**
     * получает прохождения тестов по данным дисциплинам данных студентов
     * @param curriculums - учебные планы
     * @param students - студенты
     * @param testingDate - дата
     * @return прохождения тестов
     */
    private List<TestAttempt> attemptsByStudentAndCurriculums(List<? extends Curriculum>curriculums,List<? extends AbstractStudent>students, String testingDate){
        TestAttempt tmpAttempt = new TestAttempt(testingDate);
        List<TestAttempt> attempts = testAttemptDao.getByStudentsAndCurriculums(curriculums, students, tmpAttempt);
        return attempts;

    }

    /**
     * упорядочивает полученные результаты тестов. После пробегается по ним, обрабатывает баллы
     * и вносит полученные данные в данную коллекцию расширенных самостоятельных работ
     * @param attempts
     * @param service - генератор
     * @param additionalTests
     */
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
}
