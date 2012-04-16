package org.sgu.oecde.core.education.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.util.HqlConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

/**
 * {@inheritDoc }
 */
public class ResourceDao <T extends AbstractResource> extends UpdateDao<T> implements IResourceDao<T> {

    protected ResourceDao() {
        super((Class<T>)AbstractResource.class);
    }

    private static final long serialVersionUID = 132L;

    /**
     * {@inheritDoc }
     */
    @Override
    public <K extends Curriculum,V extends AbstractResource>Map<K,List<V>> getResourceByCurriculums(List<? extends Curriculum> curriculums, Long resourceId,Class type)throws DataAccessException{
        if(CollectionUtils.isEmpty(curriculums)||type==null)
            return null;
          Map<K,List<V>>map = new HashMap<K, List<V>>();
    try {
        String byExample = null;
        if(resourceId!=null)
            byExample = "r.id=:e";

        Query q = HqlConstructor.makeQuery(getSession(), "distinct cr,r ", "from Curriculum cr join cr.umk u join u.resources rs, "+type.getName()+" r",null, "cr in (:c) and r in (rs)", byExample, "r")
                .setParameterList("c", curriculums).setCacheable(true);

        if(resourceId!=null)
            q.setParameter("e", resourceId);

        ScrollableResults r = q.scroll();
      
        List<V>list = null;
            while(r.next()){
                K c = (K)r.get(0);
                V rs = (V) r.get(1);
                if(map.containsKey(c)){
                    map.get(c).add(rs);
                }else{
                    list = new ArrayList<V>();
                    list.add(rs);
                    map.put(c,list);
                }
            }
        r.close();
        }catch(Exception e) {           
        getSession(false).close();  // using  SessionFactoryUtils.closeSession

       }
        return map;
    }
}
