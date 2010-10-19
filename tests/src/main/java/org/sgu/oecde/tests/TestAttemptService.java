package org.sgu.oecde.tests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.util.ListUtil;
import org.sgu.oecde.tests.dao.ITestAttemptDao;
import org.sgu.oecde.tests.util.pointsCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * сервис по работе с прохождениями тестов
 * @author ShihovMY
 */
@Service
public class TestAttemptService implements Serializable{

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

    private static final long serialVersionUID = 152L;


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
    public List<AdditionalSelfDependentWork> getStudentSingleCurriculumTestsWithAttempts(Curriculum curriculum, AbstractStudent student){
        List<Curriculum> tmpCurList = ListUtil.<Curriculum>oneItemList(curriculum);
        List<AbstractStudent> sts = ListUtil.<AbstractStudent>oneItemList(student);
        return getTestsWithAttempts(tmpCurList, null, sts);
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
    public List<AdditionalSelfDependentWork> getStudentTestsWithAttempts(List<? extends Curriculum> curriculums, AbstractStudent student){
        List<AbstractStudent> sts = ListUtil.<AbstractStudent>oneItemList(student);
        return getTestsWithAttempts(curriculums, null, sts);
    }

    /**
     *
     * @param test тест
     * @param student студент
     * @return количество попыток, баллов и прочее по прохождениям конкретного студента конткретного теста
     */
    public AdditionalSelfDependentWork getStudentSingleTestWithAttempts(TestEntity test,AbstractStudent student,Curriculum curriculum){
        List<TestEntity>tests = ListUtil.<TestEntity>oneItemList(test);
        List<AbstractStudent> sts = ListUtil.<AbstractStudent>oneItemList(student);
        List<TestAttempt>attempts = testAttemptDao.getByStudentsAndTests(tests, sts, new TestAttempt());
        List<AdditionalSelfDependentWork>works = new LinkedList();
        attemptsIterator(attempts, works);
        if(works.isEmpty()){
            AdditionalSelfDependentWork work = new AdditionalSelfDependentWork(test);
            work.setStudent(student);
            work.setCurriculum(curriculum);
            return work;
        }
        return works.get(0);
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
    public List<AdditionalSelfDependentWork>getTestsWithAttempts(List<? extends Curriculum> curriculums,String testingDate,List<? extends AbstractStudent>students){
        Map<Curriculum,List<TestEntity>>m = resourceDao.<Curriculum,TestEntity>getResourceByCurriculums(curriculums,null, TestEntity.class);
        TestAttempt tmpAttempt = new TestAttempt(testingDate);
        List<TestAttempt>attempts = testAttemptDao.getByStudentsAndCurriculums(curriculums, students, tmpAttempt);
        
        AdditionalSelfDependentWork test = null;

        final Set<AdditionalSelfDependentWork> set = new HashSet<AdditionalSelfDependentWork>();

        Iterator mI = m.entrySet().iterator();
        while(mI.hasNext()){
            Map.Entry<Curriculum,List<TestEntity>> v = (Map.Entry)mI.next();
            for(TestEntity t:v.getValue()){
                test = new AdditionalSelfDependentWork(t);
                test.setCurriculum(v.getKey());
                for(AbstractStudent s:students){
                    test.setStudent(s);
                }
                set.add(test);
            }
            mI.remove();
        }

        attemptsIterator(attempts, set);
        return new ArrayList(set);
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
    public List<AdditionalCurriculum>getCurriculumAttemptsCount(Curriculum curriculum, List<? extends AbstractStudent> students){
        return getAttemptsCount(ListUtil.<Curriculum>oneItemList(curriculum), null, students);
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
    public List<AdditionalCurriculum>getStudentAttemptsCount(List<? extends Curriculum> curriculums, AbstractStudent student){
        return getAttemptsCount(curriculums, null, ListUtil.<AbstractStudent>oneItemList(student));
    }

    /**
     * Получает лист расширенных учебных планов, котрые содержат результаты прохождений.
     * Пробегается по ним и для каждой следующей дисциплины подсчитывает количество тестов
     * и прохождений
     * @param curriculums -учебные планы
     * @param testingDate - дата
     * @param students - студенты
     * @return лист расширенных учебных планов
     * @see #getTestsWithAttempts(java.util.List, java.lang.String, java.util.List, java.lang.Boolean)
     */
    public List<AdditionalCurriculum> getAttemptsCount(List<? extends Curriculum> curriculums, String testingDate,List<? extends AbstractStudent> students){

        List<AdditionalCurriculum>list = new ArrayList<AdditionalCurriculum>();
        if(CollectionUtils.isEmpty(curriculums)||CollectionUtils.isEmpty(students))
            return list;
        AdditionalCurriculum addCurriculum = null;

        List<TestAttempt> attempts = attemptsByStudentAndCurriculums(curriculums, students, null);
        int attemptsNumber = 0;

        AbstractStudent stnd = null;
        Curriculum temp = null;
        TestEntity testTemp = null;
        Collections.sort(attempts);
        for(TestAttempt attempt:attempts) {
            if(attempt.getCurriculum()==null||attempt.getWork()==null||attempt.getType().equals(TestAttemptType.trial))
                continue;
            //если лист не содержит полученную дисциплину/студента, то дальше
            if(!attempt.getCurriculum().equals(temp)||!attempt.getStudent().equals(stnd)){
                if (addCurriculum!=null){
                    //запихивает в умк полученные значения
                    addCurriculum.setPassedTests(attemptsNumber);
                }
                addCurriculum = new AdditionalCurriculum(attempt.getCurriculum());
                list.add(addCurriculum);
                //если да, то начинает считать прохождения
                attemptsNumber = 1;
            }else{
                if(!attempt.getWork().equals(testTemp)){
                    attemptsNumber +=1;
                }
            }
            temp = attempt.getCurriculum();
            testTemp = attempt.getWork();
            stnd = attempt.getStudent();
        }
        if (addCurriculum!=null){
            //запихивает в умк полученные значения
               addCurriculum.setPassedTests(attemptsNumber);
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
    public List<AdditionalSelfDependentWork> getStudentAttempts(List<? extends Curriculum> curriculums, AbstractStudent student,Boolean reExame){

        List<AbstractStudent> sts = ListUtil.<AbstractStudent>oneItemList(student);
        TestAttempt attempt = null;
        if(reExame!=null){
            attempt = new TestAttempt();
            attempt.setType(reExame?TestAttemptType.reTest:TestAttemptType.regular);
        }
        List<TestAttempt> attempts = attemptsByStudentAndCurriculums(curriculums, sts, attempt);

        List<AdditionalSelfDependentWork> additionalTests = new ArrayList<AdditionalSelfDependentWork>();

        attemptsIterator(attempts, additionalTests);
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
    public List<AdditionalSelfDependentWork> getCurriculumAttempts(Curriculum curriculum, List<? extends AbstractStudent> students){

        
        List<Curriculum> tmpCurList = ListUtil.<Curriculum>oneItemList(curriculum);
        List<AdditionalSelfDependentWork> additionalTests = new ArrayList<AdditionalSelfDependentWork>();

        List<TestAttempt> attempts = attemptsByStudentAndCurriculums(tmpCurList, students, null);

        attemptsIterator(attempts, additionalTests);

        return additionalTests;
    }

    /**
     * получает прохождения тестов по данным дисциплинам данных студентов
     * @param curriculums - учебные планы
     * @param students - студенты
     * @param testingDate - дата
     * @return прохождения тестов
     */
    private List<TestAttempt> attemptsByStudentAndCurriculums(List<? extends Curriculum>curriculums,List<? extends AbstractStudent>students, TestAttempt tmpAttempt){
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
    private void attemptsIterator(List<TestAttempt> attempts,Collection additionalTests){
        Assert.notNull(additionalTests);
        if(CollectionUtils.isEmpty(attempts))
            return;
        Collections.sort(attempts);
        List<TestAttempt> oneTestAttempts = null;
        AdditionalSelfDependentWork test = null;
        int trialNumber = 0;
        int reExameNumber = 0;
        int estimatedAttemptsNumber = 0;
        List<Integer> points = new ArrayList<Integer>();
        TestEstimationType previousEstimationType = null;
        TestAttemptType previousAttemptType = null;
        TestEntity tmpTest = null;
        AbstractStudent tmpStudent = null;
        ListIterator<TestAttempt> it = attempts.listIterator();
        boolean inserted = false;

        while(it.hasNext()){
           TestAttempt attempt = it.next();
            if(!attempt.getWork().equals(tmpTest)||!attempt.getStudent().equals(tmpStudent)){
                if(test!=null){
                    test.setPointsForWork(pointsCounter.count(previousEstimationType, points));
                    test.setEstimateAttemptsUsedNumber(estimatedAttemptsNumber);
                    test.setReExameAttemptsUsedNumber(reExameNumber);
                    test.setTrialAttemptsUsedNumber(trialNumber);
                }
                oneTestAttempts = new ArrayList<TestAttempt>();
                test = new AdditionalSelfDependentWork();
                test.setResults(oneTestAttempts);
                test.setWork(attempt.getWork());
                test.setCurriculum(attempt.getCurriculum());
                test.setStudent(attempt.getStudent());
                inserted = additionalTests.add(test);
                if(!inserted){
                    additionalTests.remove(test);
                    additionalTests.add(test);
                }
                trialNumber = 0;
                reExameNumber = 0;
                estimatedAttemptsNumber = 0;
            }

            if((!attempt.getType().equals(previousAttemptType)&&!attempt.getType().equals(TestAttemptType.trial))||!attempt.getWork().equals(tmpTest))
                points = new ArrayList<Integer>();
            if(attempt.getType().equals(TestAttemptType.trial))
                trialNumber++;
            else{
                oneTestAttempts.add(attempt);
                points.add(attempt.getPoints());
                previousEstimationType = attempt.<TestEntity>getWork().getEstimation();
                previousAttemptType = attempt.getType();
                if(attempt.getType().equals(TestAttemptType.regular))
                    estimatedAttemptsNumber++;
                else if(attempt.getType().equals(TestAttemptType.reTest))
                    reExameNumber++;
            }

            tmpStudent = attempt.getStudent();
            tmpTest = attempt.getWork();
            
            if(!it.hasNext()){
                test.setPointsForWork(pointsCounter.count(previousEstimationType, points));
                test.setEstimateAttemptsUsedNumber(estimatedAttemptsNumber);
                test.setReExameAttemptsUsedNumber(reExameNumber);
                test.setTrialAttemptsUsedNumber(trialNumber);
            }
        }
    }
}
