package org.sgu.oecde.core.users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.sgu.oecde.core.authentication.RoleItem;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.springframework.stereotype.Service;
/**
 * пользователи в кеше пользователей онлайн, соответсвующем типу пользователя.
 * имеет методы на получение в кеш и получение из кеша
 * @author ShihovMY
 */
@Service
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
    public List<? extends Teacher> getTeachers(){
        Ehcache c = cache(UserType.TEACHER);
        return this.<Teacher>getUsers(c);
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
     * @return 6 случайных студентов
     */
    public List<? extends AbstractStudent> getSixStudents(){
        return getSixUsers(getStudents());
    }

    /**
     *
     * @return 6 случайных преподавателей
     */
    public List<? extends Teacher> getSixTeachers(){
        return getSixUsers(getTeachers());
    }

    /**
     *
     * @return 6 случайных админов
     */
    public List<? extends Admin> getSixAdmins(){
        return getSixUsers(getAdmins());
    }

    /**
     *
     * @return 6 случайных супервайзеров
     */
    public List<? extends Supervisor> getSixSupervisors(){
        return getSixUsers(getSupervisors());
    }

    /**
     *
     * @return число студентов
     */
    public int getStudentsNumber(){
        return getStudents().size();
    }

    /**
     *
     * @return число преподавателей
     */
    public int getTeachersNumber(){
        return getTeachers().size();
    }

    /**
     *
     * @return  число администраторов
     */
    public int getAdminsNumber(){
        return getAdmins().size();
    }

    /**
     *
     * @return число супервайзеров
     */
    public int getSupervisorsNumber(){
        return getSupervisors().size();
    }

    /**
     *
     * @param <T> extends AbstractUser
     * @param l лист пользователей из кеша
     * @return 6 случайных пользователей из полученного листа
     */
    private <T extends AbstractUser>List<T>getSixUsers(List<T> l){
        Collections.shuffle(l);
        if(l.size()>5)
            return l.subList(0, 6);
        else
            return l;
    }

    /**
     *
     * @param <T> extends AbstractUser
     * @param cache кеш
     * @return пользователи из кеша одного типа
     */
    private <T extends AbstractUser>List<T> getUsers(Ehcache cache){
        List<T>list = new ArrayList<T>();
        AbstractUser currentUser = SecurityContextHandler.getUser();
        if(currentUser==null)
            return list;
        for(Object k:cache.getKeysWithExpiryCheck()){
            T cachedUser = (T) cache.get(k).getValue();
            if(!currentUser.equals(cachedUser))
                list.add((T) cache.get(k).getValue());
        }
        return list;
    }

    /**
     *
     * @param user пользователь
     * @return кеш по типу тользователя
     */
    private Ehcache cache(AbstractUser user){
        return cache(UserType.toType(user));
    }

    /**
     *
     * @param name имя типа
     * @return кеш по типу пользователя
     */
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
