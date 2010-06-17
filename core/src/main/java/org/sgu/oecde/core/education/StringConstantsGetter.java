package org.sgu.oecde.core.education;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.sgu.oecde.core.education.dao.IConstantsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public class StringConstantsGetter{
    
    @Autowired(required=true)
    private IConstantsDao csDao;
    protected final Map<ICalendarConstantName,String> constants = new HashMap();
    protected final String key = "name";
    protected final String value = "value";
    private String entityName;

    @PostConstruct
    public void afterPropertiesSet() throws Exception{
        fillConstantsMap();
    }
    
    protected void fillConstantsMap(){
        List<Map> c = csDao.getConstants(getEntityName());
        if(!CollectionUtils.isEmpty(c)){
            for(Map m:c){
                ICalendarConstantName name = (ICalendarConstantName)m.get(key);
                String value = (String)m.get(this.value);
                constants.put(name,value);
            }
        }
    }

    public final String getConstant(ICalendarConstantName name) {
        if(constants.containsKey(name))
            return constants.get(name);
        else
            throw new AssertionError("there is no such constant in constants map with name "+name);
    }

    public void setCsDao(IConstantsDao csDao) {
        this.csDao = csDao;
    }

    public final IConstantsDao getDao() {
        return csDao;
    }

    public void update(Map c,String entity){
        csDao.update(c,entity);
    }

    public void save(ICalendarConstantName name,String value,String entity){
        Map map = new HashMap();
        map.put(this.key, name);
        map.put(this.value, value);
        csDao.save(map,entity);
    }

    protected void fillConstantsMap(String entityName){
    }

    @Required
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    protected String getEntityName(){
        return entityName;
    }
}
