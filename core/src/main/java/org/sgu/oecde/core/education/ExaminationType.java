package org.sgu.oecde.core.education;

/**
 * тип оценивания данной специальности по данной дисциплины в данном семестре в данном году.
 * @author ShihovMY
 */
public enum ExaminationType {
    /**
     * экзамен
     */
    exame,
    /**
     * зачёт
     */
    test,
    /**
     * не оценивается
     */
    empty;
    private static final long serialVersionUID = 57L;

    /**
     * @{@inheritDoc }
     * @return
     */
    @Override
    public String toString() {
        switch(this){
            case exame:
                return "экзамен";
            case test:
                return "зачёт";
            case empty:
            default:
                return "";
        }
    }

    /**
     *
     * @return стринговое значение по энаму
     */
    public String toName(){
        switch(this){
            case exame:
                return "Э";
            case test:
                return "З";
            case empty:
            default:
                return "";
        }
    }

    /**
     *
     * @param name
     * @return энам по стриногому имени
     */
    public static ExaminationType parse(String name){
        if("З".equals(name))
            return test;
        else if("Э".equals(name))
            return exame;
        else
            return empty;
    }
}
