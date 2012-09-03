package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.search.SearchEngine;
import org.sgu.oecde.search.SearchFiltersFields;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="searchBean")
@ViewScoped
public class SearchBean implements Serializable{
    
    @ManagedProperty(value="#{searchEngine}")
    private SearchEngine searchEngine;

    private String keyWord;
    private List<BasicItem>items;
    private SearchFiltersFields type = SearchFiltersFields.student;

    private static final long serialVersionUID = 176L;

    public List<BasicItem>getItems(){
        return items;
    }

    public void doSearch(){        
        items = searchEngine.search(keyWord, type,SecurityContextHandler.getUser(),  true);
    }

    public SearchFiltersFields[]getTypes(){
        return SearchFiltersFields.values();
    }
    public SearchFiltersFields[] getStudentTypes(){
        return type.getStudentValues();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public SearchFiltersFields getType() {
        return type;
    }

    public void setType(SearchFiltersFields type) {
        this.type = type;
    }

   

    public void setSearchEngine(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }
}
