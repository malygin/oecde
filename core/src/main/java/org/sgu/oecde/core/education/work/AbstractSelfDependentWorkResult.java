package org.sgu.oecde.core.education.work;

import org.springframework.util.StringUtils;


/**
 * результат по самостоятельной работе, к которой могут относиться тесты, тренажёры и тд
 * @author ShihovMY
 */
abstract public class AbstractSelfDependentWorkResult extends AbstractResult{
    /**
     * ссылка на самостоятельную работу
     */
    private SelfDependentWork work;
    public AbstractSelfDependentWorkResult() {
    }

    /**
     *
     * @param <T> extends SelfDependentWork
     * @return самостоятельная работа
     */
    public <T extends SelfDependentWork>T getWork() {
        return (T) work;
    }

    /**
     * самостоятельная работа
     * @param work
     */
    public void setWork(SelfDependentWork work) {
        this.work = work;
    }

    @Override
    public int compareTo(AbstractResult o) {
       int superCompare = super.compareTo(o);
       int workInt = 0;
       int dateInt = 0;
       if(o instanceof AbstractSelfDependentWorkResult){
           AbstractSelfDependentWorkResult r = (AbstractSelfDependentWorkResult)o;
           if(r.getWork()!=null&&getWork()!=null)
               workInt = r.getWork().getId().compareTo(getWork().getId());
           dateInt = r.getDate().compareTo(getDate());
           //группируем попытки других типов сверху списка
       }else{
          if (o.getStudent().equals(this.getStudent())&&(o.getCurriculum().equals(this.getCurriculum())))return 1;
       }
       return superCompare==0?
           (workInt==0?
               (dateInt):
               workInt):
                   superCompare;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        if(work!=null&&StringUtils.hasText(work.getTitle()))
            sb.append("\nработа: ").append(work.getTitle()).append(" (").append(work.getClass().getSimpleName()).append(");\n");
        return sb.toString();
    }
}
