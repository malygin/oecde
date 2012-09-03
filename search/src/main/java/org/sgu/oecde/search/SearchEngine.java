package org.sgu.oecde.search;

import java.util.List;
import javax.annotation.Resource;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractUser;
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
    public List<BasicItem>search(String keywords, SearchFiltersFields type, AbstractUser user, Boolean restrict) {
        String [] words;
        if (keywords == null || keywords.length() < 3) {
            return null;
        }else{
            words = keywords.trim().split(" ");
        }
       return searchDao.search(type, words, user, restrict);
    }
}
