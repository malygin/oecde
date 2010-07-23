package org.sgu.oecde.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.tests.estimation.TestsCountEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * сервис для получения теста с вопросами и сохранения изменений в тесте
 * @author ShihovMY
 */
@Service
public class TestService{

    /**
     * тест дао
     */
    @Autowired
    IResourceDao<TestEntity> resourceDao;

    /**
     * получает тест. Если стоит флажок перемешивать - перемешивает варианты ответов и вопросы,
     * а так же формирует варианты ответов для вопроса типа сопоставление
     * @param id
     * @return тест с вопросами и вариантами ответов
     */
    public TestEntity getTestWithQuestions(Long id){
        TestEntity t = resourceDao.getById(id);
        if(t==null&&CollectionUtils.isEmpty(t.getQuestions()))
            return null;
        if(t.isShuffle()){
            List l = new ArrayList(t.getQuestions());
            Collections.shuffle(l);
            t.setQuestions(new LinkedHashSet(l));
        }

        for(Question q:t.getQuestions()){
            if(q==null&&!q.getType().equals(QuestionType.comparison)&&CollectionUtils.isEmpty(q.getAnswers()))
                continue;
            Answer compare = null;
            Set<Answer>comparisons = new HashSet<Answer>();
            for(Answer a:q.getAnswers()){
                compare = new Answer(a.getRightAnswer());
                comparisons.add(compare);
            }
            q.getAnswers().addAll(comparisons);
            List l = new ArrayList(q.getAnswers());
            Collections.shuffle(l);
            q.setAnswers(new LinkedHashSet(l));
        }
        return t;
    }

    public <T extends Curriculum>Map<T,List<TestEntity>> getCurriculumTestsMap(List<? extends Curriculum> curriculums){
        return resourceDao.<T,TestEntity>getResourceByCurriculums(curriculums,null, TestEntity.class);
    }

    public void countTests(Map<? extends Curriculum,List<TestEntity>> m, Points p){
        if(p==null ||CollectionUtils.isEmpty(m))
            return;
        List<TestEntity>list = m.get(p.getCurriculum());
        int r = 0;
        int ct = 0;
        if(!CollectionUtils.isEmpty(list)){
            Iterator<TestEntity>i = list.iterator();
            while(i.hasNext()){
               TestEntity t = i.next();
               if(t!=null){
                   if(TestType.concluding.equals(t.getType()))
                       ct++;
                   else if(TestType.regular.equals(t.getType()))
                       r++;
               }
               i.remove();
            }
        }
        p.addNewWorkPoint(TestsCountEnum.CONCLUDING_TESTS_COUNT, ct);
        p.addNewWorkPoint(TestsCountEnum.TESTS_COUNT, r);
    }

    public void saveTest(TestEntity t){
        resourceDao.update(t);
    }
}
