package org.sgu.oecde.search;

import java.util.List;
import javax.annotation.Resource;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.search.dao.ISearchDao;
import org.springframework.stereotype.Service;

/**
 * поисковый сервис
 * @author ShihovMY
 */
@Service
public class SearchEngine {

    @Resource
    ISearchDao searchDao;

    /**
     * ищет по ключевому слову среди всех стринговых полей данного типа объекта
     * @param keywords  ключевое слово
     * @param type тип искомого объекта
     * @return
     */
    public List search(String keywords, String type) {
        String [] words;
        if (keywords == null || keywords.length() <= 3) {
            words = new String[0];
        }else{
            words = keywords.trim().split(" ");
        }
        return searchDao.search(getClass(type), words);
    }

    /**
     * конвертирует тип тип объекта в класс
     * @param type тип
     * @return класс
     */
    private Class getClass(String type){
        if("teacher".equals(type))
            return Teacher.class;
        else if("student".equals(type))
            return AbstractStudent.class;
        else if("umk".equals(type))
            return Umk.class;
        return null;
    }
}
