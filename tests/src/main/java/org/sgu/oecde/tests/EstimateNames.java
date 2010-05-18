package org.sgu.oecde.tests;

import org.sgu.oecde.core.education.estimation.IEstimate;

/**
 *
 * @author ShihovMY
 */
public enum EstimateNames implements IEstimate{
    CONCLUDING_TEST,
    CONCLUDING_RE_TEST,
    TEST,
    RE_TEST;
    private static final long serialVersionUID = 78L;

    @Override
    public String toString() {
        switch (this) {
            case CONCLUDING_TEST:
                return "Итоговый тест";
            case CONCLUDING_RE_TEST:
                return "Итоговый тест пере";
            case TEST:
                return "тест";
            case RE_TEST:
                return "тест пере";
            default:
                return "тест";
        }
    }
}
