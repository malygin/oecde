package org.sgu.oecde.journal.util;

import javax.annotation.Resource;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.education.dao.IResourceDao;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Node;
import org.sgu.oecde.journal.EventItem;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.dao.IJournalDao;
import org.sgu.oecde.news.NewsItem;
import org.sgu.oecde.news.dao.INewsDao;
import org.sgu.oecde.shedule.Lesson;
import org.springframework.stereotype.Service;
import static org.sgu.oecde.journal.util.LogTerms.splitter;

/**
 * @author basakov, ShihovMY
 */
@Service
public class RecordEventFactory {
    
    @Resource
    private IJournalDao journalDao;
    @Resource
    private ICurriculumDao<Curriculum>curriculumDao;
    @Resource
    private INewsDao newsDao;
    @Resource
    private IResourceDao<AbstractResource> resourceDao;
    @Resource
    private IBasicDao<Speciality>specialityDao;
    @Resource
    private IBasicDao<Discipline>disciplineDao;
    @Resource
    private IBasicDao<AbstractPerson>userDao;

    //Так мы гарантируем, что получить экземпляр класса можно получить только через сприногвый контекст.
    private RecordEventFactory() {

    }

    public static String getTime() {
        return DateConverter.currentDate();
    }

    public static String getFioByUserId(AbstractUser user){
        String fio =  user instanceof AbstractPerson
                ?((AbstractPerson)user).getFio()
                :user.getUsername();
        return fio;
    }

    private String getSpecialytyNameById(Long specId) {
        Speciality spec =  specialityDao.getById(specId);
        return spec!=null?spec.getName():null;
    }

    private String getDisciplineNameById(Long disciplineId) {
        Discipline disc =  disciplineDao.getById(disciplineId);
        return disc!=null?disc.getName():null;
    }

    private String getTestNameById(Long testId) {
        AbstractResource test =  resourceDao.getById(testId);
        return test!=null?test.getTitle():null;
    }

    private String getNewsHeader(Long id){
        NewsItem newsItem = newsDao.getById(id);
        return newsItem!=null?newsItem.getHeader():"";
    }

    private String getFioByUserId(Long id) {
        AbstractUser user =  userDao.getById(id);
        return getFioByUserId(user);
    }

    private String getType(AbstractUser user){
        return UserType.fromRole(user).toString();
    }

    /**
     * Сохраняет новое событие в базу.
     *
     * @param eventType - тип события.
     * @param userId    - id пользователя.
     * @param userType  -  типа пользователя.
     * @param multiId   - мульти-Id события.
     * @param body      -  тело события в виде массива строк.
     *                  Для каждого типа событий формируется по-разному.
     */
    public void save(EventType eventType, AbstractUser user, Long multiId, String[] body) {
        EventItem item = new EventItem(eventType, user, getTime(), multiId);
        StringBuilder sb = new StringBuilder();
        //Из массива строк формируется тело сообщения.
        for (String s : body) {
            sb.append(s).append(splitter);
        }
        item.setEventBody(sb.toString());
        journalDao.saveEventItem(item);
    }

    /**
     * Логируется факт того, что произошла массовая рассылка сообщений.
     * В теле события через разделитель записаны следующие данные:
     * 0 - Тип пользователя (значение, а не ID);
     * 1 - ФИО;
     * 2 - Специальность;
     * 3 - Поток (Сейчас это означает специальность + год, поэтому хранится только год);
     * 4 - Группа (номер)(На разных специальностях могут быть специальности с разными номерами);
     * 5 - Название специальности (значение, а не ID);
     */
    public void saveSpamActivity(AbstractUser userId, EventType eventType, Long specId, Long streamId, Long groupId) {
        //Формируется тело сообщения.
        String[] str = new String[6];
        str[0] = getType(userId);
        str[1] =  getFioByUserId(userId);
        //breack; не поставлены намеренно.
        switch (eventType) {
            case SPAM_GROUP:
                str[4] = groupId + "";
            case SPAM_STREAM:
                str[3] = streamId + "";
            case SPAM_SPECIALITY:
                str[2] = specId + "";
        }
        str[5] = getSpecialytyNameById(specId);

        //Формируется multiId
        Long multiId = streamId * 10000 * 10000 + specId * 10000 + groupId;
        save(eventType, userId,  multiId, str);
    }

    /**
     * TODO
     * Логируется факт просмотра УМК.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Тип пользователя (значение, а не ID);
     * 1 - ФИО;
     * 2 - Тип активности (значение, а не ID: просматривал/создавал/удалял/редактировал);
     * 3 - Название УМК (значение, а не ID);
     * 4 - Название таска (значение, а не ID);
     * 5 - ID таска;
     * 6 - ID модуля;
     * 7 - ID UMK;
     */
    public void saveUmkActivity(AbstractUser userId,  EventType eventType, Long taskId, Long curriculumId) {
        if (UserType.fromRole(userId).equals(UserType.STUDENT) & !(eventType.equals(EventType.UMK_VIEW))) {
            return;
        }
        //Формируется тело сообщения.
        String[] str = new String[8];
        str[0] = getType(userId);
        str[1] = getFioByUserId(userId);
        switch (eventType) {
            case UMK_VIEW:
                str[2] = "просматривал(а)";
                break;
            case UMK_CREATE:
                str[2] = "создал(а)";
                break;
            case UMK_DELETE:
                str[2] = "удалил(а)";
                break;
            case UMK_EDIT:
                str[2] = "редактировал(а)";
                break;
        }

        Long umkId = 0L;

        if (eventType.equals(EventType.UMK_CREATE) || eventType.equals(EventType.UMK_DELETE) || eventType.equals(EventType.UMK_EDIT)) {
            umkId = taskId;
            str[7] = umkId + "";
        } else if (eventType.equals(EventType.UMK_VIEW)) {
            Curriculum c = curriculumDao.getById(curriculumId);
            AbstractResource t = resourceDao.getById(taskId);
            str[3] = c.getUmk().getName();
            str[4] = t.getTitle();
            str[5] = t.getId() + "";
            str[6] = null + "";
            str[7] = c.getUmk().getId() + "";
            umkId = c.getUmk().getId();
        }

        save(eventType, userId, umkId, str);
    }

    /**
     * Логируется факт добавления/удаления фотографии либо входа в систему.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Тип пользователя (значение, а не ID);
     * 1 - ФИО;
     * 2 - Тип активности (значение, а не ID: добавлял/удалял/заходил в систему);     *
     */
    public void saveSimpleActivity(AbstractUser userId, EventType eventType) {
        //Формируется тело сообщения.
        String[] str = new String[3];
        str[0] = getType(userId);
        str[1] = getFioByUserId(userId);
        switch (eventType) {
            case SYSTEM_LOGIN:
                str[2] = "вошел(а) в систему";
                break;
            case PHOTO_ADDITION:
                str[2] = "добавил(а) фотографию";
                break;

            case PHOTO_DELETION:
                str[2] = "удалил(а) фотографию";
                break;

        }
        save(eventType, userId, 0L, str);
    }

    /**
     * Логируется факт проставления (либо довыставления) оценок.
     * <p/>
     * Для данного события представяет собой 12-значное число, где
     * 6 старших разрядов отведены под индетификатор специальности, а
     * 6 младших разрядов отведены по номер группы.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Тип преподавателя (значение, а не ID);
     * 1 - ФИО преподавателя;
     * 2 - id группы(ее номер);
     * 3 - id специальности;
     * 4 - название специальности словами;
     * !!! Номер группы уникален только в пределах специальности. В разных спец-х
     * возможно дублирование.
     */
    public void saveGradesActivity(AbstractUser userId, EventType eventType, Long specId, Long groupId) {
        String[] str = new String[5];
        str[0] = UserType.TEACHER.toString();
        str[1] = getFioByUserId(userId);
        str[2] = groupId + "";
        str[3] = specId + "";
        str[4] = getSpecialytyNameById(specId);
        //Формируется multiId
        Long multiId = specId * 10000 + groupId;
        save(eventType, userId,  multiId, str);
    }

    /**
     * Логируется факт завершения теста.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Тип пользователя (значение, а не ID);
     * 1 - ФИО ;
     * 2 - id теста.
     * 3 - название теста
     */
    public void saveTestActivity(AbstractUser userId,  EventType eventType, Long testId, Long umkId) {
        String[] str = new String[4];
        str[0] = getType(userId);
        str[1] = getFioByUserId(userId);
        str[2] = testId + "";
        str[3] = getTestNameById(testId);
        save(eventType, userId,  umkId, str);
    }

    /**
     * Логируется факт отправки студентом задания по дисциплине. Событие для админа.
     * В теле события через разделитель записаны следующий данные:
     * 0 - ФИО студента.
     * 1 - Название дисциплины.
     */
    public void saveTaskHasBeenSent(AbstractUser userId, Long disciplineId) {
        String[] str = new String[2];
        str[0] = getFioByUserId(userId);
        str[1] = getDisciplineNameById(disciplineId);
        save(EventType.TASK_HAS_BEEN_SEND_TO_PREP, userId, disciplineId, str);
    }

    /**
     * Логируется факт прочтения преподавателем по дисциплине задания студета. Событие для админа.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Название дисциплины.
     * 1 - ФИО студента, выполнившего задание.
     * 2 - ФИО преподавателя.
     * <p/>
     * Решено, что хранить id этих объектов не имеет смысла, достаточно
     * Стринговых значений ФИО и  названия дисциплины.
     */
    public void saveTaskHasBeenRead(AbstractUser userId, Long disciplineId, Long studentId) {
        String[] str = new String[3];
        str[0] = getDisciplineNameById(disciplineId);
        str[1] = getFioByUserId(studentId);
        str[2] = getFioByUserId(userId);
        save(EventType.TASK_HAS_BEEN_READ, userId, studentId, str);
    }

    /**
     * Логируется факт обновления новостей.
     * В multyId кладется id новости.
     * <p/>
     * В теле события через разделитель записаны следующий данные:
     * 0 - Заголовок новости.
     */
    public void saveNews(Long newsId, String header) {
        //String newsText = journalDAO.getNewsById(newsId);
        String[] str = {header};
        save(EventType.NEW_NEWS, null, newsId, str);
    }

    /**
     * Логируется факт просмотра новостей.
     * В multyId кладется id новости.
     * <p/>
     * В теле события через разделитель записаны следующий данные:
     * 0 - Заголовок новости.
     */
    public void saveViewNews(Long newsId, String header, AbstractUser user) {
        String[] str = {header};
        save(EventType.NEWS_VIEW, user, newsId, str);
    }

    /**
     * Логируется факт рассылки администратором собственного сообщения.
     * В теле события через разделитель записаны следующий данные:
     * 0 - ФИО администратора.
     * 1 - Текст сообщения.
     */
    public void saveOwnMessage(AbstractUser userId, String message) {
        String[] str = {getFioByUserId(userId), message};
        save(EventType.OWN_MESSAGE, userId, 0L, str);
    }

    /**
     * Логируется факт посылки администратором или преподователем сообщения студенту.
     * В теле события через разделитель записаны следующий данные:
     * 0 - тема сообщения;
     * 1 - Тип пользователя отправителя(имя, а не id).
     * 2 - ФИО отправителя.
     * 3 - ФИО получателя (всегда студент).
     */
    public void saveMessageToStudent(AbstractUser userId, Long studentId, String theme) {
        String[] str = new String[4];
        str[0] = theme;
        str[1] = getType(userId);
        str[2] = getFioByUserId(userId);
        str[3] = getFioByUserId(studentId);
        save(EventType.MESSAGE_TO_STUDENT, userId,  studentId, str);
    }

    /**
     * TODO
     * Логируется факт добавления/изменения теста в определнном УМК.
     * В теле события через разделитель записаны следующий данные:
     * 0 - id теста.
     * 1 - Название УМК( значение, а не ID);
     */
    public void saveTestChanging(EventType eventType, AbstractUser userId,  Long curriculumId, Long testId) {

        switch (eventType) {
            case TEST_ADD:
            case TEST_CHANGE:
                final String[] str = new String[2];
                str[0] = testId + "";
                Curriculum c = curriculumDao.getById(curriculumId);
                str[1] = c.getUmk().getName();
                save(eventType, userId,  c.getUmk().getId(), str);
        }
    }

    /**
     * Логируется факт изменения расписания у группы.
     * <p/>
     * В теле события через разделитель записаны следующий данные:
     * 0 - город (Сейчас это означает специальность + год, поэтому храниться только номер курса);
     * 1 - Группа (номер)(Номер группы уникален только в пределах специальности);
     * 2 - Название специальности (значение, а не ID);
     */
    public void saveScheduleChanging(Lesson lesson) {
        Long multiId = lesson.getId() * 10000;
        final String[] str = new String[lesson.getGroup().size()];
        StringBuilder sb = new StringBuilder();
        for (Group gr:lesson.getGroup()){
            sb.append(gr.getCity().getName()).append(", ").append(gr.getSpeciality().getRusShort()).append(", ").append(gr.getName()).append("; ").append(splitter);
        }
        save(EventType.SHEDULE_CHANGE, lesson.getTeacher(),  multiId, str);
    }

    /**
     * @param node -  id поста, на который ответили.
     * @param user - ответивший пользователь
     *             0 - id поста, на который ответили.
     *             1 - тип обсуждаемого объекта.
     *             2 - id обсуждаемого объекта.
     */
    public void savePostAnswer(Node node, AbstractUser user) {
        final String[] str = new String[4];
        ForumTypes type = node.getRoot().getObjectType();
        str[0] = node.getParent().getId() + "";
        str[1] = type.toString();
        str[2] = node.getRoot().getObjectId() + "";
        if(ForumTypes.NEWS.equals(type))
            str[3] = getNewsHeader(node.getParent().getId());        
        //Автор поста, на который ответили.
        AbstractUser author = node.getParent().getUser();
        Long authorId = author.getId();
        //Подобно массовой рассылке в мульти id сохраняется 2 значения: индетификатор пользователя и его тип.
        Long multiId = authorId * 100 + UserType.fromRole(user).toInt();
        save(EventType.POST_ANSWER,user,  multiId, str);
    }

    /**
     * @param node -  добавленный пост
     *             0 - id поста.
     *             1 - тип обсуждаемого объекта.
     *             2 - id обсждаемого объекта.
     */
    public void savePostAdd(Node node) {
        final String[] str = new String[4];
        AbstractUser user = node.getUser();
        ForumTypes type = node.getRoot().getObjectType();
        str[0] = node.getId() + "";
        str[1] = node.getRoot().getObjectType().toString();
        str[2] = node.getRoot().getObjectId() + "";
        if(ForumTypes.NEWS.equals(type)){
            str[3] = getNewsHeader(node.getParent().getId());
        }
        //Автор поста
        //Подобно массовой рассылке в мульти id сохраняется 2 значения: индетификатор пользователя и его тип.
        Long multiId = user.getId() * 100 + UserType.fromRole(user).toInt();
        save(EventType.POST_ADD, user, multiId, str);
    }
}