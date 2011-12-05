package org.sgu.oecde.tests.estimation;

import java.util.LinkedList;
import java.util.List;
import org.sgu.oecde.core.education.estimation.IEstimate;
import org.sgu.oecde.core.education.estimation.IResultFilter;
import org.sgu.oecde.core.education.estimation.Points;
import org.sgu.oecde.core.education.estimation.ResultType;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.tests.TestAttempt;
import org.sgu.oecde.tests.TestAttemptType;
import org.sgu.oecde.tests.TestEntity;
import org.sgu.oecde.tests.util.pointsCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * фильтр результатов тестов. обрабатывает получаемые результаты и
 * @author ShihovMY
 */
@Scope("prototype")
@Service
@ResultType(type=TestAttempt.class)
public final class TestFilter implements IResultFilter{

    /**
     * фабрика формирования названий полей с баллами по результату теста
     */
    @Autowired
    private TestsPointsFactory pointsFactory;
    /**
     * баллы за обычные прохождения
     */
    private List<Integer> points ;
    /**
     * баллы за прохождения переэкзаменовки
     */
    private List<Integer> rePoints ;
    /**
     * предыдущий тест
     */
    private TestEntity test;
    /**
     * предыдущая обычная попытка
     */
    private TestAttempt previousAttempt;
    /**
     * предыдущая попытка переэкзаменовки
     */
    private TestAttempt previousReAttempt;

    private static final long serialVersionUID = 153L;

    private TestFilter() {
    }

    /**
     * полученный результат, в зависимости от типа, заносит баллы прохождения в соответсвтующий лист.
     * Перед каджым новым тестом обрабатывает массивы с баллами прохождений предыдущего теста
     * и вносит их в Points,
     * после чего сохраняет предыдущие значения прохождения и теста.
     * @param result результат теста
     * @param point баллы
     * @see #setPoints(org.sgu.oecde.core.education.estimation.Points)
     */
    @Override
    public void check(AbstractResult result,Points point) {
        TestAttempt att = (TestAttempt) result;
        //System.out.println("! "+att.toString());
        if(att==null||att.<TestEntity>getWork()==null||att.<TestEntity>getWork().getType()==null||att.getType()==null||att.getType().equals(TestAttemptType.trial))
            return;
        if(!att.<TestEntity>getWork().equals(test))
            setPoints(point);
        if(att.getType().equals(TestAttemptType.reTest)){
            rePoints.add(att.getPoints());
            previousReAttempt = att;
        }else if (att.getType().equals(TestAttemptType.regular)){
            points.add(att.getPoints());
            previousAttempt = att;
        }
        test = att.<TestEntity>getWork();
    }

    /**
     * в зависимости от наличия баллов прохождения переэкзаменовки, обрабатывает только
     * лист с обычными баллами либо оба листа и соответсвующим образом суммирует эти баллы
     * @param point
     * @see #fillMap(org.sgu.oecde.tests.TestAttempt, java.util.List, boolean, org.sgu.oecde.core.education.estimation.Points)
     */
    public void setPoints(Points point){
  
        if(point == null)
            return;
        if(!CollectionUtils.isEmpty(points)&&CollectionUtils.isEmpty(rePoints)){
            fillMap(previousAttempt,points,true,point);
        }
        if(!CollectionUtils.isEmpty(rePoints)){
            if(!CollectionUtils.isEmpty(points))
                fillMap(previousAttempt,points,false,point);
            fillMap(previousReAttempt,rePoints,true,point);
        }
        points = new LinkedList<Integer>();
        rePoints = new LinkedList<Integer>();
    }

    /**
     * по типу оценивания данной попытки получает соответвующий результирующий балл из
     * полученного листа баллов и вносит их в Points вместе с суммой
     * @param attempt попытка
     * @param pointsList баллы
     * @param doSum - суммировать ли
     * @param points
     */
    private void fillMap(TestAttempt attempt, List pointsList,boolean doSum, Points points){
        Assert.notNull(attempt);
        IEstimate name = pointsFactory.createEstimatedWorkValue(attempt);
        int p = pointsCounter.count(attempt.<TestEntity>getWork().getEstimation(),pointsList);
        if(doSum)
            points.addSum(p);
        points.addIntegerWorkPoints(name, p);
    }
}