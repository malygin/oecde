package org.sgu.oecde.tests.estimation;

import org.sgu.oecde.core.education.estimation.IEstimate;

/**
 * названия полей с баллами по тестам
 * @author ShihovMY
 */
public enum TestEstimateNames implements IEstimate{
    /**
     * итоговый тест
     */
    CONCLUDING_TEST,
    /**
     * итоговый тест. переэкзаменовка
     */
    CONCLUDING_RE_TEST,
    /**
     * обычный тест
     */
    TEST,
    /**
     * обычный тест. переэкзаменовка
     */
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
