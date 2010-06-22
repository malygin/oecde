package org.sgu.oecde.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@Service
public class TestService{

    @Autowired
    IResourceDao<TestEntity> resourceDao;

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
        }
        return t;
    }

    public void saveTest(TestEntity t){
        resourceDao.update(t);
    }
}
