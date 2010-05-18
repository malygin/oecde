package org.sgu.oecde.journal.filter;

/**
 *
 * @author basakovvy
 */
public class StudentNewsLineFilter extends StudentFilter {

    private static String StudentNewsLineFilterCookiePath = "StudentNewsLineFilter";

    public StudentNewsLineFilter() {
        //Для студ-й новостной ленты изменено количество событий на странице.
        capacity = 7;
    }

    @Override
    public String getCookiePath() {
        return StudentNewsLineFilterCookiePath;
    }
}
