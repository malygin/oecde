package org.sgu.oecde.search;


import java.util.List;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.de.users.Teacher;
import org.sgu.oecde.search.dao.ISearchDao;

/**
 * @author basakovvy
 */
public class SearchEngine {

    ISearchDao dao;

    public List search(String keywords, String type) {
        String [] words;
        if (keywords == null || keywords.length() <= 3) {
            words = new String[0];
        }else{
            words = keywords.trim().split(" ");
        }
        return dao.search(getClass(type), words);
    }

    public void setDao(ISearchDao dao) {
        this.dao = dao;
    }

    private Class getClass(String type){
        if("teacher".equals(type))
            return Teacher.class;
        else if("student".equals(type))
            return Student.class;
        else if("umk".equals(type))
            return Umk.class;
        return null;
    }
}
