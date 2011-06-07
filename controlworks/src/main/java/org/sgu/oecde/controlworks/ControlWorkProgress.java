package org.sgu.oecde.controlworks;

/**
 * статус контрольной рабоы
 * @author ShihovMY
 */
public enum ControlWorkProgress {
    /**
     * в наличии
     */
    available,
    /**
     * зачтена
     */
    passed,
    /**
     * не зачтена
     */
    failed,
    /**
     * нет в наличии
     */
    notAvailable;
    private static final long serialVersionUID = 92L;

    @Override
    public String toString() {
        switch(this){
            case passed:
                return "зачтено";
            case failed:
                return "не зачтено";
            case notAvailable:
                return "нет в наличии";
            default:
            case available:
                return "в наличии";
        }
    }

    public String toName(){
        switch(this){
            case passed:
                return "passed";
            case failed:
                return "failed";
            case notAvailable:
                return "notAvailable";
            default:
            case available:
                return "available";
        }
    }

    public static ControlWorkProgress parse(String name){
        if("passed".equals(name))
            return passed;
        else if("failed".equals(name))
            return failed;
        else
            return available;
    }

    public String getName(){
        return toString();
    }
}
