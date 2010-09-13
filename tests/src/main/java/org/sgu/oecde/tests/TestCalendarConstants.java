package org.sgu.oecde.tests;

import org.sgu.oecde.core.education.ICalendarConstantName;

/**
 * календарные константы
 * @author ShihovMY
 */
public enum TestCalendarConstants implements ICalendarConstantName{
    /**
     * дата начала прохождения обычных тестов
     */
    regularTestBeginDate,
    /**
     * дата окончания прохождения обычных тестов
     */
    regularTestEndDate,
    /**
     * дата начала прохождения итоговых тестов
     */
    concludingTestBeginDate,
    /**
     * дата окончания прохождения итоговых тестов
     */
    concludingTestEndDate,
    /**
     * дата окончания прохождения тестов для не специальностей не ускоренной и не сокращённой
     * программы 1, 2 и 3 курсов
     */
    simpleSpecialitiesTestsClosing,
    /**
     * количество попыток прохождения обычных тестов за летнюю переэкзаменовку
     */
    summerRegularTestReExameAttemtpsCount,
    /**
     * количество попыток прохождения итоговых тестов за летнюю переэкзаменовку
     */
    summerConcludingTestReExameAttemtpsCount,
    /**
     * количество попыток прохождения обычных тестов за зимнюю переэкзаменовку
     */
    winterRegularTestReExameAttemtpsCount,
    /**
     * количество попыток прохождения итоговых тестов за зимнюю переэкзаменовку
     */
    winterConcludingTestReExameAttemtpsCount;

    private static final long serialVersionUID = 58L;

    @Override
    public Object getDefault() {
        switch(this){
            case concludingTestBeginDate:
                return "2009.01.01 00:00:00";
            case concludingTestEndDate:
                return "2009.01.01 00:00:00";
            case regularTestBeginDate:
                return "2009.01.01 00:00:00";
            case regularTestEndDate:
                return "2009.01.01 00:00:00";
            case simpleSpecialitiesTestsClosing:
                return "2009.01.01 00:00:00";
            case summerConcludingTestReExameAttemtpsCount:
                return "1";
            case summerRegularTestReExameAttemtpsCount:
                return "1";
            case winterConcludingTestReExameAttemtpsCount:
                return "1";
            case winterRegularTestReExameAttemtpsCount:
                return "1";
            default:
                return new AssertionError();
        }
    }
}
