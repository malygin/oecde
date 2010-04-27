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

    @Override
    public String toString() {
        switch (this) {
            case CONCLUDING_TEST:
                return "Итоговый тест";
            case CONCLUDING_RE_TEST:
                return "Итоговый тест";
            case TEST:
                return "тест";
            case RE_TEST:
                return "тест";
            default:
                return "тест";
        }
    }
}
