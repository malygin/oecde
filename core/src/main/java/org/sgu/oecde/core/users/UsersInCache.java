package org.sgu.oecde.core.users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.sgu.oecde.core.util.DateConverter;
/**
 * пользователи в кеше пользователей онлайн, соответсвующем типу пользователя.
 * имеет методы на получение в кеш и получение из кеша
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

    /**
     *
     * @param user
     * @return есть пользователей в кеше
     */
    public boolean isUserInCache(AbstractUser user) {
        Ehcache c = cache(user);
        return c.isKeyInCache(user.getUsername());
    }

    /**
     * кладёт пользователя в соответсвующий тип
     * @param user
     */
    public void putUserInCache(AbstractUser user) {
        Ehcache c = cache(user);
        Element element = new Element(user.getUsername(), user);
        c.put(element);
    }

    /**
     * удаляет пользователя из соответсвующего типу кеша
     * @param user
     */
    public void removeUserFromCache(AbstractUser user) {
        Ehcache c = cache(user);
        c.remove(user.getUsername());
    }

    /**
     * @return все студенты в кеше
     */
    public List<? extends AbstractStudent> getStudents(){
        Ehcache c = cache(UserType.STUDENT);
        return this.<AbstractStudent>getUsers(c);
    }

    /**
     *
     * @return все преподаватели в кше
     */
    public List<? extends AbstractTeacher> getTeachers(){
        Ehcache c = cache(UserType.TEACHER);
        return this.<AbstractTeacher>getUsers(c);
    }

    /**
     *
     * @return все администраторы в кеше
     */
    public List<? extends Admin> getAdmins(){
        Ehcache c = cache(UserType.ADMIN);
        return this.<Admin>getUsers(c);
    }

    /**
     *
     * @return все наблюдатели в кеше
     */
    public List<? extends Supervisor> getSupervisors(){
        Ehcache c = cache(UserType.SUPERVISOR);
        return this.<Supervisor>getUsers(c);
    }

    /**
     *
     * @return
     */
    public List<? extends AbstractStudent> getSixStudents(){
        List<? extends AbstractStudent> l = getStudents();
        Collections.shuffle(l);
        return l.subList(0, 5);
    }

    public List<? extends AbstractTeacher> getSixTeachers(){
        List<? extends AbstractTeacher> l = getTeachers();
        Collections.shuffle(l);
        return l.subList(0, 5);
    }

    public List<? extends Admin> getSixAdmins(){
        List<? extends Admin> l = getAdmins();
        Collections.shuffle(l);
        return l.subList(0, 5);
    }

    public List<? extends Supervisor> getSixSupervisors(){
        List<? extends Supervisor> l = getSupervisors();
        Collections.shuffle(l);
        return l.subList(0, 5);
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

    private <T extends AbstractUser>List<T> getUsers(Ehcache cache){
        List<T>list = new ArrayList<T>();
        for(Object k:cache.getKeysWithExpiryCheck()){
            list.add((T) cache.get(k).getValue());
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
