package org.sgu.oecde.journal;

import java.io.Serializable;
import java.util.List;
import org.sgu.oecde.core.education.AdvancedCurriculum;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.resource.Task;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.journal.dao.IJournalDao;
import org.sgu.oecde.journal.filter.BaseFilter;
import org.sgu.oecde.journal.util.RecordEventFactory;
import org.sgu.oecde.news.NewsItem;
import org.sgu.oecde.schedule.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author basakov,ShihovMY
 */
@Service(value="journalServise")
public class Journal implements Serializable{

    @Autowired
    private IJournalDao journalDao;

    @Autowired
    private RecordEventFactory ref;

    private static final long serialVersionUID = 155L;

    private Journal() {
    }

    /**************************************************************************/
    /**************************************************************************/
    /*******************  Методы логирования событий **************************/
    /**************************************************************************/
    /**
     * Добавляет в таблицу логов
     * запись о входе пользователя в систему.
     *
     * @param userId   идентификатор пользователя.
     * @param userType Тип пользователя.
     */
    public void logSystemLogin(AbstractUser userId,String ip) {
        ref.saveLogInEvent(userId, ip);
    }

    /**
     * Добавляет в таблицу логов
     * запись о добавлении пользователем личной фотографии.
     *
     * @param userId   идентификатор пользователя.
     * @param userType Тип пользователя.
     */
    public void logPhotoAddition(AbstractUser userId) {
        ref.saveSimpleActivity(userId,  EventType.PHOTO_ADDITION);
    }

    /**
     * Добавляет в таблицу логов
     * запись об удалении пользователем личной фотографии.
     *
     * @param userId   идентификатор пользователя.
     * @param userType Тип пользователя.
     */
    public void logPhotoDeletion(AbstractUser userId) {
        ref.saveSimpleActivity(userId,  EventType.PHOTO_DELETION);
    }

    /**
     * Добавляет в таблицу логов
     * запись о просмотре пользователем модуля(занятия) УМК
     *
     * @param userId   идентификатор пользователя.
     * @param userType Тип пользователя.
     * @param taskId   Идентификатор занятия
     */
    public void logViewUMK(AbstractUser userId,Curriculum curricuum,Task task) {
        ref.saveTaskView(userId,  EventType.UMK_VIEW, curricuum, task);
    }

    /**
     * Добавляет в таблицу логов
     * запись о редактировании пользователем модуля(занятия) УМК
     *
     * @param userId   идентификатор пользователя.
     * @param userType Тип пользователя.
     */
    public void logEditUMK(AbstractUser userId,Curriculum curricuum) {
        ref.saveUmkActivity(userId,  EventType.UMK_EDIT, curricuum);
    }

    /**
     * Добавляет в таблицу логов
     * запись о создании пользователем модуля(занятия) УМК
     *
     * @param userId   идентификатор пользователья.
     * @param userType Тип пользователя.
     * @param taskId   идентификатор задания
     */
    public void logCreateUMK(AbstractUser userId,Curriculum curricuum) {

        ref.saveUmkActivity(userId,  EventType.UMK_CREATE, curricuum);
    }

    /**
     * Добавляет в таблицу логов
     * запись об удалении пользователем модуля(занятия) УМК
     *
     * @param userId   идентификатор пользователья.
     * @param userType Тип пользователя.
     * @param taskId   идентификатор УМК
     */
    public void logDeleteUMK(AbstractUser userId,Curriculum curricuum) {
        ref.saveUmkActivity(userId,  EventType.UMK_DELETE, curricuum);
    }

    /**
     * Добавляет в таблицу логов
     * запись о том, что администратор сделал массовую рассылку
     *
     * @param userId идентификатор пользователья.
     */
    public void logSpamToAll(AbstractUser userId) {
        ref.saveSpamActivity(userId, EventType.SPAM_ALL, 0L, 0L, 0L);
    }

    /**
     * Добавляет в таблицу логов
     * запись о массовой рассылке администратором или преподавателем
     * сообщений по специальности.
     *
     * @param userId   Идентификатор пользователья.
     * @param userType Тип пользователя.
     * @param specId   Идентификатор специальности.
     */
    public void logSpamToSpeciality(AbstractUser userId,Long specId) {
        ref.saveSpamActivity(userId,  EventType.SPAM_SPECIALITY, specId, 0L, 0L);
    }

    /**
     * Добавляет в таблицу логов запись о том,
     * что администратор и преподаватель сделал массовую рассылку сообщений потоку.
     *
     * @param userId   Идентификатор пользователя.
     * @param userType Тип пользователя.
     * @param specId   Идентификатор специальности.
     * @param streamId Идентификатор потока(в данной системе это studentItem.getYear()).
     * @param specId   Идентификатор специальности(год + id специальности определят поток однозначно).
     */
    public void logSpamToStream(AbstractUser userId,Long specId, Long streamId) {
        ref.saveSpamActivity(userId,  EventType.SPAM_STREAM, specId, streamId, 0L);
    }

    /**
     * Добавляет в таблицу логов запись о том, что администратор или
     * преподаватеь сделал массовую рассылку группе.
     *
     * @param userId   Идентификатор пользователя.
     * @param userType Типа пользователя.
     * @param specId   Идентификатор специальности.
     * @param streamId Идентификатор потока(в данной системе это год studentItem.getYear()).
     * @param specId   Идентификатор специальности(номер курса + id специальности определят поток однозначно).
     * @param groupId  Номер группы.
     */
    public void logSpamToGroup(AbstractUser userId,Long specId, Long streamId, Long groupId) {
        ref.saveSpamActivity(userId,  EventType.SPAM_GROUP, specId, streamId, groupId);
    }

    /**
     * Добавляет в таблицу логов
     * запись о прохождении теста.
     */
    public void logTestGrading(AbstractUser userId, Long testId, Long umkId) {
        ref.saveTestActivity(userId,  EventType.TEST_END, testId, umkId);
    }

    /**
     * Добавляет в таблицу логов
     * запись о выставлении оценок.
     */
    public void logGradesPutting(AbstractUser userId, StudentGroup group, AdvancedCurriculum c) {
        ref.saveGradesActivity(userId, EventType.GRADING, group, c);
    }

    /**
     * Отправка студентом задания по дисциплине. Событие для админа.
     *
     * @param userId       Идентификатор студента.
     * @param disciplineId id дисциплины.
     */
    public void logTaskHasBeenSent(AbstractUser user, AdvancedCurriculum c) {
        ref.saveTaskHasBeenSent(user, c);
    }

    /**
     * Прочтение преподавателем по дисциплине задания студента.
     *
     * @param userId       id преподавателя
     * @param disciplineId id дисциплины
     * @param studentId    id студента
     */
    public void logTaskHasBeenRead(Teacher userId, AdvancedCurriculum c, AbstractStudent s) {
        ref.saveTaskHasBeenRead(userId, c, s);
    }

    public void logHandWrittenWorkGot(AbstractUser u, AdvancedCurriculum c, AbstractStudent studentId) {
        ref.saveHandWrittenWorkGot(u, studentId, c);
    }

    /**
     * Добавение новой новости
     */
    public void logNewNews(NewsItem item, AbstractUser user) {
        ref.saveNews(item,user, EventType.NEW_NEWS);
    }

    /**
     * Новый пост в обсуждении.
     *
     * @param node - пост.
     */
//    public void logPostAdd(Node node) {
//        ref.savePostAdd(node);
//    }
    /**
     * Ответ на пост в обсуждении.
     *
     * @param node - собственно ответ.
     * @param user - ответивший пользователь
     */
//    public void logPostAnswer(Node node, UserItem user) {
//        ref.savePostAnswer(node, user);
//    }

    /**
     * Добавление собственного события.
     *
     * @param userId  - id администратора.
     * @param message - текст сообщения
     */
    public void logOwnMessage(AbstractUser userId, String message) {
        ref.saveOwnMessage(userId, message);
    }

    /**
     * Отсылка сообщения студенту администратором или преподавателем.
     *
     * @param userId    id Отправителя
     * @param userType  Тип пользователя
     * @param studentId
     * @param theme
     */
    public void logMessageToStudent(AbstractUser userId,Long studentId, String theme) {
        ref.saveMessageToStudent(userId,  studentId, theme);
    }

    /**
     * Логируется факт добавления теста в определнном УМК.
     */
    public void logTestAdditing(AbstractUser userId,Long umkId, Long testId) {
        ref.saveTestChanging(EventType.TEST_ADD, userId,  umkId, testId);
    }

    /**
     * Логируется факт изменения теста в определнном УМК.
     */
    public void logTestChanging(AbstractUser userId,Long umkId, Long testId) {
        ref.saveTestChanging(EventType.TEST_CHANGE, userId,  umkId, testId);
    }

    /**
     * Логируется факт изменения теста в определнном УМК.
     */
    public void logScheduleChanging(Lesson lesson) {
        ref.saveScheduleChanging(lesson);
    }

    /**
     * Возвращает произошедшие события.
     * Отслеживаемый период и типы событий содержатся в объекте filter.
     *
     * @return список событий.
     */
    public List<EventItem> getEvents(BaseFilter filter) {

        List<EventItem> list = journalDao.getEvents(filter);
     int maxPN = 1;
     int totalEventCount = journalDao.getCountOfEvents(filter);

        if (totalEventCount % filter.getCapacity() == 0) {
            maxPN = totalEventCount / filter.getCapacity();
        } else {
            maxPN = totalEventCount / filter.getCapacity() + 1;
        }
        filter.setMaxPageNumber(maxPN != 0 ? maxPN : 1);

        return list;
    }


    public void logViewNews(NewsItem item, AbstractUser user) {
        ref.saveNews(item,user, EventType.NEWS_VIEW);
    }
}