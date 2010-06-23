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
     * дата начала прохождения тестов переэкзаменовки
     */
    reExameBeginDate,
    /**
     * дата окончания прохождения тестов переэкзаменовки
     */
    reExameEndDate,
    /**
     * дата окончания прохождения тестов для не специальностей не ускоренной и не сокращённой
     * программы 1, 2 и 3 курсов
     */
    simpleSpecialitiesTestsClosing;
    private static final long serialVersionUID = 58L;
}
