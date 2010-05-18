package org.sgu.oecde.journal;

import java.util.List;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.dao.IJournalDao;
import org.sgu.oecde.journal.filter.BaseFilter;
import org.sgu.oecde.journal.util.RecordEventFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


/**
 * @author basakov
 */
public class Journal implements InitializingBean{

    private IJournalDao journalDAO;

    @Autowired
    private RecordEventFactory ref;
    

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
    public void logSystemLogin(AbstractUser userId) {
        ref.saveSimpleActivity(userId,  EventType.SYSTEM_LOGIN);
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
    public void logViewUMK(AbstractUser userId,int taskId) {
        ref.saveUmkActivity(userId,  EventType.UMK_VIEW, taskId);
    }

    /**
     * Добавляет в таблицу логов
     * запись о редактировании пользователем модуля(занятия) УМК
     *
     * @param userId   идентификатор пользователя.
     * @param userType Тип пользователя.
     */
    public void logEditUMK(AbstractUser userId,int taskId) {
        ref.saveUmkActivity(userId,  EventType.UMK_EDIT, taskId);
    }

    /**
     * Добавляет в таблицу логов
     * запись о создании пользователем модуля(занятия) УМК
     *
     * @param userId   идентификатор пользователья.
     * @param userType Тип пользователя.
     * @param taskId   идентификатор задания
     */
    public void logCreateUMK(AbstractUser userId,int taskId) {

        ref.saveUmkActivity(userId,  EventType.UMK_CREATE, taskId);
    }

    /**
     * Добавляет в таблицу логов
     * запись об удалении пользователем модуля(занятия) УМК
     *
     * @param userId   идентификатор пользователья.
     * @param userType Тип пользователя.
     * @param taskId   идентификатор УМК
     */
    public void logDeleteUMK(AbstractUser userId,int taskId) {
        ref.saveUmkActivity(userId,  EventType.UMK_DELETE, taskId);
    }

    /**
     * Добавляет в таблицу логов
     * запись о том, что администратор сделал массовую рассылку
     *
     * @param userId идентификатор пользователья.
     */
    public void logSpamToAll(AbstractUser userId) {
        ref.saveSpamActivity(userId, EventType.SPAM_ALL, 0, 0, 0);
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
    public void logSpamToSpeciality(AbstractUser userId,int specId) {
        ref.saveSpamActivity(userId,  EventType.SPAM_SPECIALITY, specId, 0, 0);
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
    public void logSpamToStream(AbstractUser userId,int specId, int streamId) {
        ref.saveSpamActivity(userId,  EventType.SPAM_STREAM, specId, streamId, 0);
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
    public void logSpamToGroup(AbstractUser userId,int specId, int streamId, int groupId) {
        ref.saveSpamActivity(userId,  EventType.SPAM_GROUP, specId, streamId, groupId);
    }

    /**
     * Добавляет в таблицу логов
     * запись о прохождении теста.
     */
    public void logTestGrading(AbstractUser userId, int testId, int umkId) {
        ref.saveTestActivity(userId,  EventType.TEST_END, testId, umkId);
    }

    /**
     * Добавляет в таблицу логов
     * запись о выставлении оценок.
     */
    public void logGradesPutting(AbstractUser userId, int specId, int groupId) {
        ref.saveGradesActivity(userId, EventType.GRADING_FIRST, specId, groupId);
    }

    /**
     * Добавляет в таблицу логов
     * запись о ДОвыставлении оценок.
     */
    public void logSecondGradesPutting(AbstractUser userId, int specId, int groupId) {
        ref.saveGradesActivity(userId, EventType.GRADING_SECOND, specId, groupId);
    }

    /**
     * Отправка студентом задания по дисциплине. Событие для админа.
     *
     * @param userId       Идентификатор студента.
     * @param disciplineId id дисциплины.
     */
    public void logTaskHasBeenSent(AbstractUser userId, int disciplineId) {
        ref.saveTaskHasBeenSent(userId, disciplineId);
    }

    /**
     * Прочтение преподавателем по дисциплине задания студента.
     *
     * @param userId       id преподавателя
     * @param disciplineId id дисциплины
     * @param studentId    id студента
     */
    public void logTaskHasBeenRead(AbstractUser userId, int disciplineId, int studentId) {
        ref.saveTaskHasBeenRead(userId, disciplineId, studentId);
    }

    /**
     * Добавение новой новости
     */
    public void logNewNews(int newsId, String header) {
        ref.saveNews(newsId, header);
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
    public void logMessageToStudent(AbstractUser userId,int studentId, String theme) {
        ref.saveMessageToStudent(userId,  studentId, theme);
    }

    /**
     * Логируется факт добавления теста в определнном УМК.
     */
    public void logTestAdditing(AbstractUser userId,int umkId, int testId) {
        ref.saveTestChanging(EventType.TEST_ADD, userId,  umkId, testId);
    }

    /**
     * Логируется факт изменения теста в определнном УМК.
     */
    public void logTestChanging(AbstractUser userId,int umkId, int testId) {
        ref.saveTestChanging(EventType.TEST_CHANGE, userId,  umkId, testId);
    }

    /**
     * Логируется факт изменения теста в определнном УМК.
     */
    public void logScheduleChanging(AbstractUser userId,int specId, int streamId, int groupId) {
        ref.saveScheduleChanging(userId,  specId, streamId, groupId);
    }

    /**
     * Возвращает произошедшие события.
     * Отслеживаемый период и типы событий содержатся в объекте filter.
     *
     * @return список событий.
     */
    public List<EventItem> getEvents(BaseFilter filter) {

        List<EventItem> list = journalDAO.getEvents(filter);
     int maxPN = 1;
     int totalEventCount = journalDAO.getCountOfEvents(filter);

        if (totalEventCount % filter.getCapacity() == 0) {
            maxPN = totalEventCount / filter.getCapacity();
        } else {
            maxPN = totalEventCount / filter.getCapacity() + 1;
        }
        filter.setMaxPageNumber(maxPN != 0 ? maxPN : 1);

        return list;
    }


    public void logViewNews(int id, String header, AbstractUser user) {
        ref.saveViewNews(id, header, user);
    }

    public void setJournalDAO(IJournalDao journalDAO) {
        this.journalDAO = journalDAO;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(journalDAO);
        Assert.notNull(ref);
    }
}