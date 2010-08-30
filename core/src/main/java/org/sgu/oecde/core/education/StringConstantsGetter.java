package org.sgu.oecde.core.education;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.sgu.oecde.core.education.dao.IConstantsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;

/**
 * получает Map констант и помещает в Map constants только стринговые знацения (в основном даты)
 * @author ShihovMY
 */
public class StringConstantsGetter implements Serializable{

    /**
     * дао календарных констант
     */
    @Autowired
    private IConstantsDao csDao;
    /**
     * календарные константы
     */
    protected final Map<ICalendarConstantName,Object> constants = new HashMap();
    /**
     * имя поля, содержащее название константы
     */
    protected final String key = "name";
    /**
     * имя поля, содержащее значение константы
     */
    protected final String value = "value";
    private String entityName;

    private static final long serialVersionUID = 117L;

    /**
     * после инициализации бина выполняется метод fillConstantsMap()
     * @throws Exception
     * @see #fillConstantsMap()
     */
    @PostConstruct
    public void afterPropertiesSet() throws Exception{
        fillConstantsMap();
    }

    /**
     * получает {@code List<Map>} из базы, пробегается по каждому значению листа,
     * который содержит  {@code Map}, состоящий из имени константы и соответствующего значения,
     * и помещает их в  {@code constants}
     * @see ICalendarConstantName имя константы
     * @see #constants константы
     */
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

    /**
     *
     * @param name имя константы
     * @return значение константы по имени, если такое есть в  {@code constants}
     */
    public final Object getConstant(ICalendarConstantName name) {
        if(name == null)
            throw new AssertionError();
        Object o = constants.get(name);
        return o==null?name.getDefault():o;
    }

    /**
     * формирует из заданных параметров {@code Map}, который после заносится в базу методом апдейт
     * @param name
     * @param value
     * @param entity
     * @see org.hibernate.Session#update(java.lang.Object) update
     */
    public void update(ICalendarConstantName name,Object value){
        Map map = new HashMap();
        map.put(this.key, name);
        map.put(this.value, value);
        csDao.update(map,entityName);
    }

    /**
     * формирует из заданных параметров {@code Map}, который после заносится в базу методом save
     * @param name
     * @param value
     * @param entity
     * @see org.hibernate.Session#save(java.lang.Object) save
     */
    public void save(ICalendarConstantName name,Object value){
        Map map = new HashMap();
        map.put(this.key, name);
        map.put(this.value, value);
        csDao.save(map,entityName);
    }

    /**
     * устанавливает имя entity. Вызов метода обязателен при инициализации бина
     * @param entityName
     */
    @Required
    public final void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * имя entity
     * @return
     */
    protected final String getEntityName(){
        return entityName;
    }

    /**
     *
     * @return дао календарных констант
     * @see org.sgu.oecde.core.education.dao.ConstantsDao
     */
    protected final IConstantsDao getDao() {
        return csDao;
    }
}
