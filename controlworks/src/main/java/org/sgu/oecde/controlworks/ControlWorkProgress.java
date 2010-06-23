package org.sgu.oecde.controlworks;

/**
 * статус контрольной рабоы
 * @author ShihovMY
 */
public enum ControlWorkProgress {
    /**
     * зачтена
     */
    passed,
    /**
     * не зачтена
     */
    failed,
    /**
     * в наличии
     */
    available,
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
            case available:
                return "в наличии";
            case notAvailable:
            default:
                return "нет в наличии";
        }
    }

    public String toName(){
        switch(this){
            case passed:
                return "passed";
            case failed:
                return "failed";
            case available:
                return "available";
            case notAvailable:
            default:
                return "notAvailable";
        }
    }

    public static ControlWorkProgress parse(String name){
        if("passed".equals(name))
            return passed;
        else if("failed".equals(name))
            return failed;
        else if("available".equals(name))
            return available;
        else
            return notAvailable;
    }
}
