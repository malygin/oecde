package org.sgu.oecde.core.education.work;


/**
 *
 * @author ShihovMY
 */
abstract public class AbstractSelfDependentWorkResult extends AbstractResult{
    private SelfDependentWork work;
    public AbstractSelfDependentWorkResult() {
    }

    public <T extends SelfDependentWork>T getWork() {
        return (T) work;
    }

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
       }
       return superCompare==0?
           (workInt==0?
               (dateInt):
               workInt):
                   superCompare;
    }
}
