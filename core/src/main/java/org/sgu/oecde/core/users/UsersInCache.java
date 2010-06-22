package org.sgu.oecde.core.users;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.sgu.oecde.core.util.DateConverter;
/**
 *
 * @author ShihovMY
 */

public class UsersInCache{

    private UsersInCache() {
    }

    @Resource
    private Ehcache studentCache;
    @Resource
    private Ehcache teacherCache;
    @Resource
    private Ehcache adminCache;
    @Resource
    private Ehcache supervisorCache;

    public boolean isUserInCache(AbstractUser user) {
        Ehcache c = cache(user);
        return c.isKeyInCache(user.getUsername());
    }

    public void putUserInCache(AbstractUser user) {
        Ehcache c = cache(user);
        Element element = new Element(user.getUsername(), DateConverter.currentDate());
        c.put(element);
    }

    public void removeUserFromCache(AbstractUser user) {
        Ehcache c = cache(user);
        c.remove(user.getUsername());
    }

    public List<String> getStudents(){
        Ehcache c = cache(UserType.STUDENT);
        return this.getUsers(c);
    }

    public List<String> getTeachers(){
        Ehcache c = cache(UserType.TEACHER);
        return this.getUsers(c);
    }

    public List<String> getAdmins(){
        Ehcache c = cache(UserType.ADMIN);
        return this.getUsers(c);
    }

    public List<String> getSupervisors(){
        Ehcache c = cache(UserType.SUPERVISOR);
        return this.getUsers(c);
    }

    public List<String> getSixStudents(){
        return getStudents().subList(0, 5);
    }

    public List<String> getSixTeachers(){
        return getTeachers().subList(0, 5);
    }

    public List<String> getSixAdmins(){
        return getAdmins().subList(0, 5);
    }

    public List<String> getSixSupervisors(){
        return getSupervisors().subList(0, 5);
    }

    public int getStudentsCount(){
        return getStudents().size();
    }

    public int getTeachersCount(){
        return getTeachers().size();
    }

    public int getAdminsCount(){
        return getAdmins().size();
    }

    public int getSupervisorsCount(){
        return getSupervisors().size();
    }

    private List<String> getUsers(Ehcache cache){
        List<String>list = new ArrayList();
        for(Object k:cache.getKeysWithExpiryCheck()){
            list.add((String) cache.get(k).getValue());
        }
        return list;
    }

    private Ehcache cache(AbstractUser user){
        return cache(UserType.fromRole(user));
    }

    private Ehcache cache(UserType name){
        switch (name) {
            case ADMIN:
                return adminCache;
            case SUPERVISOR:
                return supervisorCache;
            case TEACHER:
                return teacherCache;
            case STUDENT:
                return studentCache;
            default:
                throw new AssertionError("no cache with name "+name);
        }
    }
}
