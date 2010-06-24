package org.sgu.oecde.journal.util;

import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.journal.EventItem;
import org.sgu.oecde.journal.EventType;
import org.springframework.stereotype.Service;
import static org.sgu.oecde.journal.util.LogTerms.splitter;

/**
 * @author basakovvy
 *         <p/>
 *         <p/>
 *         В зависимости от типа события по-разному формирует строку сообщения
 * @TODO Админы личных страниц не имеют, но их имена выводятся в ввиде ссылок(нерабочих, конечно).
 */
@Service
public class EventParser {

    private EventParser() {
    }

    public String parseEventBody(EventItem item) {
        switch (item.getEventType()) {
            case SYSTEM_LOGIN:
            case PHOTO_ADDITION:
            case PHOTO_DELETION:
                return parseSimpleActivity(item);
            case UMK_VIEW:
            case UMK_CREATE:
            case UMK_DELETE:
            case UMK_EDIT:
                return parseUmkActivity(item);
            case SPAM_GROUP:
            case SPAM_STREAM:
            case SPAM_SPECIALITY:
            case SPAM_ALL:
                return parseSpamActivity(item);
            case TEST_END:
                return parseTestActivity(item);
            case GRADING_SECOND:
            case GRADING_FIRST:
                return parseGradesActivity(item);
            case OWN_MESSAGE:
                return parseOwnMessage(item);
            case NEW_NEWS:
                return parseNewNews(item);
            case TASK_HAS_BEEN_READ:
                return parseTaskHasBeenRead(item);
            case TASK_HAS_BEEN_SEND_TO_PREP:
                return parseTaskHasBeenSent(item);
            case MESSAGE_TO_STUDENT:
                return parseMessageToStudent(item);
            case TEST_ADD:
            case TEST_CHANGE:
                return parseTestChanging(item);
            case SHEDULE_CHANGE:
                return parseScheduleChange(item);
            case POST_ANSWER:
                return parsePostAnswer(item);
            case POST_ADD:
                return parsePostAdd(item);
            case NEWS_VIEW:
                return parseNewsView(item);
            default:
                throw new RuntimeException("Оператор Case не сработал");
        }

    }

    /**
     * Логируется факт просмотра студентом или преподавателем новости.
     * В теле события через разделитель записаны следующие данные:
     * 0 - заголовок;
     */

    private String parseNewsView(EventItem item) {

        String str[] = item.getEventBody().split(splitter);
        //Получаем из  спрингового контекста юзер дао, а из него вытягиваем по типу пользователя и id его ФИО.
        String fio = RecordEventFactory.getFioByUserId(item.getUser());
        //Собираем текст сообщения.
        //Студент/преподаватель ФИО просматривал(а) новость <ссылка>"Заголовок"<ссылка>.
        StringBuilder sb = new StringBuilder(item.getUser().getAuthority().getAuthority());
        sb.append(" ").append(fio);
        sb.append(" просматривал(а) новость ").append("<a href=\"#newsFullText/id=").append(item.getMultiId())
                .append("&pN=1&count=10\">").append("\"").append(str[0]).append("\"").append("</a>");

        return sb.toString();  //To change body of created methods use File | Settings | File Templates.
    }


    /*
    *
    * final String[] str = new String[]{idPost+""};
    * int authorId = Integer.valueOf(author.getId());
    * int multiId = authorId*100 + author.getType().getId();
    * В теле события через разделитель записаны следующие данные:
    *   0 - id поста, на который ответили.
    *   1 - тип обсуждаемого объекта.
    *   2 - id обсждаемого объекта.
    */
    private String parsePostAnswer(EventItem item) {
        try {
//        DiscussionDao dao = (DiscussionDao) SpringContext.getBean("discussionDao");
            String str[] = item.getEventBody().split(splitter);
            String subjectType = str[1];
            ForumTypes postType = ForumTypes.parse(subjectType);
            String subjectID = str[2];
            StringBuilder sb = new StringBuilder();
            switch (postType) {
                case STUDENT_FAQ:
                case STUDENT_ORG:
                case STUDENT_CITY:
                case TEACHER_FAQ:
                case TEACHER_ORG:
                    sb = new StringBuilder().append("К вашему сообщению на ")
                            .append("<a href=\"#forum/type=").append(postType).append("&id=").append(subjectID)
                            .append("\">").append("форуме")
                            .append("</a> был добавлен комментарий.");
                    return sb.toString();
                case NEWS:
                    sb = new StringBuilder().append("К вашему сообщению в обсуждении новости")
                            .append("<a href=\"#newsFullText/id=").append(subjectID).append("&pN=1&count=10\">\"");
                    if(str.length>3)
                        sb.append(str[3]);
                    sb.append("\"</a> был добавлен комментарий.");
                    return sb.toString();

            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(item.getId() + "*******************************************************");
        }
        return "К вашему сообщению в ветках обсуждений был добавлен комментарий";
    }

    /*
    *
    * В теле события через разделитель записаны следующие данные:
    *   0 - id поста.
    *   1 - тип обсуждаемого объекта.
    *   2 - id обсждаемого объекта.
    */
    private String parsePostAdd(EventItem item) {
        String[] str = item.getEventBody().split(splitter);
        String subjectType = str[1];
        AbstractUser user = item.getUser();
        String fio = RecordEventFactory.getFioByUserId(item.getUser());
        ForumTypes postType = ForumTypes.parse(subjectType);
        String subjectID = str[2];
        StringBuilder sb;
        switch (postType) {
            case STUDENT_FAQ:
            case STUDENT_ORG:
            case STUDENT_CITY:
            case TEACHER_FAQ:
            case TEACHER_ORG:
                sb = new StringBuilder().append(UserType.fromRole(user)).append(" ").append(fio)
                        .append(" добавил комментарий ")
                        .append("<a href=\"#forum/type=").append(postType).append("&id=").append(subjectID)
                        .append("\">").append("на форуме")
                        .append("</a>.");
                return sb.toString();
            case NEWS:
                sb = new StringBuilder().append(UserType.fromRole(user)).append(" ").append(fio)
                        .append(" добавил комментарий к новости")
                        .append("<a href=\"#newsFullText/id=").append(subjectID).append("&pN=1&count=10\">\"");
                if(str.length>3)
                    sb.append(str[3]);
                sb.append("\"</a>.");
                return sb.toString();
        }
        return new StringBuilder().append(UserType.fromRole(user)).append(" ").append(fio).append(" добавил комментарий.")
                .toString();
    }

    /**
     * Логируется факт того, что произошла массовая рассылка сообщений.
     * В теле события через разделитель записаны следующие данные:
     * 0 - Тип пользователя (значение, а не ID);
     * 1 - ФИО;
     * 2 - Специальность;
     * 3 - Поток (Сейчас это означает специальность + год, поэтому храниться только номер курса);
     * 4 - Группа (номер)(Номер группы уникален только в пределах специальности);
     * 5 - Название специальности (значение, а не ID);
     */
    private String parseSpamActivity(EventItem item) {
        String str[] = item.getEventBody().split(splitter);
        StringBuilder sb = new StringBuilder();
        sb.append(str[0]).append(" ");
        sb.append("<a href=\"#");
        switch (UserType.fromRole(item.getUser())) {
            case TEACHER:
                sb.append("teacher");
                break;
        }
        sb.append("/id=").append(item.getUser().getId()).append("\">").append(str[1]);
        sb.append("</a> ").append(" сделал(а) массовую рассылку");

        if (item.getEventType().equals(EventType.SPAM_GROUP)) {
            sb.append(" группе ").append(str[4]).append(" специальности");
        }

        if (item.getEventType().equals(EventType.SPAM_STREAM)) {
            sb.append(" потоку");
        }

        if (item.getEventType().equals(EventType.SPAM_ALL)) {
            sb.append(" всем студентам (или пользователям?)");
        } else {
            sb.append(" \"" + str[5] + "\"");
        }

        if (item.getEventType().equals(EventType.SPAM_STREAM)) {
            sb.append("(").append(str[3]).append(" курс)");
        }
        return sb.toString();
    }

    /**
     * Имеется факт просмотра УМК.
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
    //@todo Добавить для тасков и модулей проверки на null,
    // дабы была возможность учитывать действия с УМК без привязки к конкретному таску.
    private String parseUmkActivity(EventItem item) {
        String str[] = item.getEventBody().split(splitter);
        StringBuilder sb = new StringBuilder();
        sb.append(str[0]).append(" ");
        sb.append("<a href=\"");
        switch (UserType.fromRole(item.getUser())) {
            case STUDENT:
                sb.append("#student");
                break;
            case TEACHER:
                sb.append("#teacher");
                break;
            case SUPERVISOR:
            case ADMIN:
                sb.append("#admin");
                break;
        }
        sb.append("/id=").append(item.getUser().getId()).append("\">").append(str[1]);
        sb.append("</a> ").append(str[2]).append(" ");
        sb.append("<a href=\"#course/id=").append(str[7]).append("\">");
        sb.append("УМК");
        if (item.getEventType().equals(EventType.UMK_VIEW)) {
            sb.append(" \"");
            sb.append(str[3]).append("\"</a>, задание ");
            sb.append("<a href=\"exbook.jsp#").append(str[5]).append("\">").append(str[4]).append("</a>");
        }
        return sb.toString();
    }

    /**
     * �?меется факт добавления/удаления фотографии.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Тип пользователя (значение, а не ID);
     * 1 - Ф�?О;
     * 2 - Тип активности (значение, а не ID: добавлял/удалял);     *
     */
    private String parseSimpleActivity(EventItem item) {
        String str[] = item.getEventBody().split(splitter);
        StringBuilder sb = new StringBuilder();
        sb.append(str[0]).append(" ");
        switch (UserType.fromRole(item.getUser())) {
            case STUDENT:
                sb.append("<a href=\"");
                sb.append("#student");
                sb.append("/id=").append(item.getUser().getId()).append("\">");
                break;
            case TEACHER:
                sb.append("<a href=\"");
                sb.append("#teacher");
                sb.append("/id=").append(item.getUser().getId()).append("\">");
                break;
            case ADMIN:
            case SUPERVISOR:
                sb.append("<a href=\"");
                sb.append("#admin");
                sb.append("/id=").append(item.getUser().getId()).append("\">");
                break;
        }
        sb.append(str[1]);
        if (!UserType.GUEST.equals(UserType.fromRole(item.getUser()))) {
            sb.append("</a> ");
        }
        sb.append(" ").append(str[2]);
        return sb.toString();
    }

    /**
     * Выводится факт проставления (либо довыставления) оценок.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Тип преподавателя (значение, а не ID);
     * 1 - ФИО преподавателя;
     * 2 - id группы(ее номер);
     * 3 - id специальности;
     * 4 - название специальности словами;
     * !!! Номер группы уникален только в пределах специальности. В разных спец-х
     * возможно дублирование.
     */
    private String parseGradesActivity(EventItem item) {
        StringBuilder sb = new StringBuilder();
        String[] str = item.getEventBody().split(splitter);
        sb.append("<a href=\"");
        sb.append("#teacher");
        sb.append("/id=").append(item.getUser().getId()).append("\">");
        sb.append(str[0]).append(" ").append(str[1]).append("</a>");
        switch (item.getEventType()) {
            case GRADING_FIRST:
                sb.append(" выставил(а) оценки группе ");
                break;
            case GRADING_SECOND:
                sb.append(" довыставил(а) оценки группе ");
                break;
        }
        sb.append(str[2]).append(" специальности \"");
        sb.append(str[4]).append("\"");
        return sb.toString();
    }

    /**
     * Выводится факт завершения теста.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Тип пользователя (значение, а не ID);
     * 1 - ФИО ;
     * 2 - id теста.
     * 3 - название теста
     */
    private String parseTestActivity(EventItem item) {
        StringBuilder sb = new StringBuilder();
        String[] str = item.getEventBody().split(splitter);
        sb.append("Студент ").append("<a href=#student/id=");
        sb.append(item.getUser().getId()).append(">");
        sb.append(str[1]).append("</a>");
        switch (item.getEventType()) {
            case TEST_END:
                sb.append(" прошел(прошла) ");
        }
        sb.append("<a href=\"test.jsp?id=").append(str[2]).append("\">");
        sb.append(str[3]);
        sb.append("</a>");
        return sb.toString();
    }

    /**
     * Выводится факт отправки студентом задания по дисциплине. Событие для админа.
     * В теле события через разделитель записаны следующий данные:
     * 0 - ФиО студента.
     * 1 - Название дисциплины.
     */
    private String parseTaskHasBeenSent(EventItem item) {
        StringBuilder sb = new StringBuilder();
        String[] str = item.getEventBody().split(splitter);
        sb.append("Cтудент ").append("<a href=#student/id=");
        sb.append(item.getUser().getId()).append(">");
        sb.append(str[0]).append("</a>").append(" ");
        sb.append("отправил(а) на проверку задание по дисциплине \"");
        sb.append(str[1]).append("\"");
        return sb.toString();
    }

    /**
     * Выводится факт прочтения преподавателем по дисциплине задания студета. Событие для админа.
     * В теле события через разделитель записаны следующий данные:
     * 0 - Название дисциплины.
     * 1 - ФИО студента, выполнившего задание.
     * 2 - ФИО преподавателя.
     * <p/>
     * <p/>
     * Решено, что хранить id этих объектов не имеет смысла, достаточно
     * Стринговых значений ФИО и  названия дисциплины.
     */
    private String parseTaskHasBeenRead(EventItem item) {
        StringBuilder sb = new StringBuilder();
        String[] str = item.getEventBody().split(splitter);
        sb.append("Преподаватель ").append("<a href=#teacher/id=").append(item.getUser().getId()).append(">");
        sb.append(str[2]).append("</a>").append(" ");
        sb.append("прочитал(а) задание по дисциплине \"").append(str[0]).append("\". ");
        sb.append("Выполнивший студент(ка): ").append(str[1]).append(".");
        return sb.toString();
    }

    /**
     * Выводится факт обновления новостей.
     * В multyId лежит id новости.
     */
    private String parseNewNews(EventItem item) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("<a href=\"#news/id=").append(item.getMultiId()).append("\">");
//        sb.append("Добавлена новость").append("</a>");
        String[] str = item.getEventBody().split(splitter);
        StringBuilder sb = new StringBuilder().append("Добавлена новость \"").append(str[0])
                .append("\", подробнее <a href=\"#newsFullText/id=").append(item.getMultiId())
                .append("&pN=1&count=10\">").append("здесь").append("</a>");
        return sb.toString();
    }

    /**
     * Логируется факт рассылки администратором собственного сообщения.
     * 0 - Ф�?О администратора.
     * 1 - Текст сообщения.
     */
    private String parseOwnMessage(EventItem item) {
        String[] str = item.getEventBody().split(splitter);
        return str[1];
    }

    /**
     * Логируется факт посылки администратором или преподователем сообщения студенту.
     * В теле события через разделитель записаны следующий данные:
     * 0 - тема сообщения;
     * 1 - Тип пользователя отправителя(имя, а не id).
     * 2 - ФИО отправителя.
     * 3 - ФИО получателя (всегда студент).
     */
    private String parseMessageToStudent(EventItem item) {
        StringBuilder sb = new StringBuilder();
        String[] str = item.getEventBody().split(splitter);
        sb.append(str[0]).append(" ").append(str[1]);
        sb.append(" отправил личное сообщение студенту. Получатель: ");
        sb.append("<a href=\"").append("#student/id=").append(item.getMultiId());
        sb.append("\">").append(str[3]).append("</a> ");

        return sb.toString();
    }

    /**
     * Логируется факт добавления/изменения теста в определнном УМК.
     * В теле события через разделитель записаны следующий данные:
     * 0 - id теста.
     */
    private String parseTestChanging(EventItem item) {
        StringBuilder sb = new StringBuilder();
        String[] str = item.getEventBody().split(splitter);
        sb.append("У УМК <a href=\"#course/id=").append(str[1]);
        sb.append("</a> добавлен <a href=\"test.jsp?id=\"");
        sb.append(str[0]).append("\">тест</a>");
        return sb.toString();
    }

    /**
     * Парсится факт изменения расписания у группы.
     * <p/>
     * В теле события через разделитель записаны следующий данные:
     * 0 - Поток (Сейчас это означает специальность + год, поэтому храниться только номер курса);
     * 1 - Группа (номер)(Номер группы уникален только в пределах специальности);
     * 2 - Название специальности (значение, а не ID);
     */
    private String parseScheduleChange(EventItem item) {
        String str[] = item.getEventBody().split(splitter);
        StringBuilder sb = new StringBuilder("У групп ");
        for(String s:str){
            sb.append(s);
        }
        sb.append(" изменилось расписание");
        return sb.toString();
    }
}
