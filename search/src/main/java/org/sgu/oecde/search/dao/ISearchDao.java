package org.sgu.oecde.search.dao;

import java.util.List;
import org.sgu.oecde.search.SearchType;
import org.springframework.dao.DataAccessException;

/**
 * дао поиска. не наследуется о basicdao
 * @author ShihovMY
 */
public interface ISearchDao {

    /**
     * составляет поисковый запрос по всем стринговым полям класса
     * @param type тип искомого объекта
     * @param words ключевое слово
     * @return коллекция объектов
     * @throws DataAccessException
     */
    public List search(SearchType type,String[] words) throws DataAccessException;
}
