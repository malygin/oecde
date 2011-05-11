package org.sgu.oecde.journal;

import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Node;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.education.AdvancedCurriculum;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.education.resource.Task;
import org.sgu.oecde.core.education.Umk;
import org.springframework.util.Assert;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.news.NewsItem;
import org.sgu.oecde.tests.TestEntity;

/**
 * Типы событий. содержат параметры<br/>
 * &nbsp;&nbsp;{@code Boolean chosen} - выбран ли данный тип для отображения в списке<br/>
 * &nbsp;&nbsp;{@code String rus} - наименование по-русски<br/>
 * оба этих параметра указываются по-умолчанию<br/>
 *<br/>
 * каждый тип события содержит 2 метода. первый формирует объект для записи в таблицу,
 * второй - для вывода;<br/>
 *<br/>
 * в качестве аттрибутов метода {@code EventItem fillEventItem(AbstractUser user, Object ... o)}
 * выступают объекты, указанные в описании конкретного типа
 * @author ShihovMY
 */
public enum EventType {

    /**
     * Вход на портал
     * на вход должен поступать объект типа String - айпи,
     * с которого пользователь совершил авторизацию. Пользователь любого типа
     */
    SYSTEM_LOGIN(false,"Вход в систему"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkObjectArrayAndUser(user, 2, this,String.class, o))
                return null;
            String[] str = {(String)o[0],(String)o[1]};
            return generateEventItem(user, 0l, str);
        }
     
        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[5];
            el[0] = new EventBodyElement(userType(item.getUser()));

            el[1] = new EventBodyElement(item.getUser().getId(),fioFromUser(item.getUser()));
            setPageTypeByUser(el[1],item.getUser());

            String str[] = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, 2, this))
                return null;

            el[2] = new EventBodyElement("вошёл в систему с удалённого адреса ");
            el[3] = new EventBodyElement(str[0],str[0] , EventBodyElement.whoIs);
            el[4] = new EventBodyElement("с браузера "+str[1]);
            return el;
        }
    },
    /**
     * Просмотр модуля (занятия) в умк.
     * В массиве объектов должны быть следующие элементы:
     * &nbsp;&nbsp;{@link Umk}
     * &nbsp;&nbsp;{@link Task}
     * пользователь - студент
     */
    UMK_VIEW(false,"Просмотр УМК"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkObjectArrayAndUser(user, UserType.STUDENT, this, 2, o))
                return null;

            Umk u = null;
            Task t = null;
            for(Object object:o){
                if(object instanceof Umk){
                    u = (Umk) object;
                }else if (object instanceof Task){
                    t = (Task) object;
                }else{
                    logWrongObjectType(object,this);
                    return null;
                }
            }
            String[]body = {u.getName(),t.getTitle(),t.getId().toString()};

            return generateEventItem(user, u.getId(), body);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[4];
            el[0] = new EventBodyElement(userType(item.getUser()));

            String str[] = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, 3, this))
                return null;

            AbstractStudent st = item.<AbstractStudent>getUser();
            el[1] = new EventBodyElement(item.getUser().getId(), st.getInitials(),EventBodyElement.studentPage);
            el[2] = new EventBodyElement("просматривал(а) курс "+str[0]+", занятие "+str[1]);
            return el;
        }

        @Override
        public EventBodyElement[] parseEventForAdmin(EventItem item) {
            EventBodyElement[] el = super.parseEventForAdmin(item);
            String str[] = item.getEventBody().split(splitter);
            el[2] = new EventBodyElement(item.getMultiId(), str[0], EventBodyElement.umkPage);
            el[3] = new EventBodyElement(Long.parseLong(str[2]), str[1], EventBodyElement.taskPage);
            return el;
        }

    },
    /**
     * отметка о получении рукописной контрольной рабоы
     * аттрибут:
     * {@link  AdvancedCurriculum}
     * пользователь преподаватель либо админ
     */
    HAND_WRITTEN_CONTROL_WORK(true,"Oтметка о получении контрольной работы, написанной вручную"){

        private final int length = 2;

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(checkObjectArrayAndUser(user,2, this, o))
                return null;

            switch (UserType.toType(user)) {
                case TEACHER:
                case ADMIN:
                    break;
                default:
                    throw new IllegalArgumentException("user type is "+UserType.toType(user));
            }
            AbstractStudent st = null;
            Discipline d = null;
            for(Object object:o){
                if(object instanceof AbstractStudent){
                    st = (AbstractStudent) object;
                }else if (object instanceof Discipline){
                    d = (Discipline) object;
                }else{
                    logWrongObjectType(object,this);
                    return null;
                }
            }
            String[] str = new String[length];
            str[0] = d.getName();
            str[1] = fioFromUser(st);
            return generateEventItem(user, st.getId(), str);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[5];
            el[0] = new EventBodyElement(userType(item.getUser()));

            el[1] = new EventBodyElement(item.getUser().getId(), fioFromUser(item.getUser()));
            setPageTypeByUser(el[1],item.getUser());

            String[] str = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, length, this))
                return null;

            el[2] = new EventBodyElement("отметил, что получено задание, которое отправил студент");
            el[3] = new EventBodyElement(item.getMultiId(), str[1], EventBodyElement.studentPage);
            el[4] = new EventBodyElement(" по дисциплине \""+str[0]+"\" в рукописном виде");
            return el;
        }

        @Override
        protected boolean addConditionByStudent(StringBuilder sb, AbstractStudent user) {
            super.addConditionByStudent(sb, user);
            sb.append(" and multiId = ").append(user.getId());
            return false;
        }

    },
    /**
     * Выставление оценки.
     * аттрибуты:
     * &nbsp;&nbsp;{@link AdvancedCurriculum}
     * &nbsp;&nbsp;{@link StudentGroup}
     * пользователь преподаватель
     */
    GRADING(true,"Выставление оценок"){

        private final int length = 2;

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkObjectArrayAndUser(user, UserType.TEACHER, this, 2, o))
                return null;

            StudentGroup gr = null;
            Discipline d = null;
            for(Object object:o){
                if(object instanceof StudentGroup){
                    gr = (StudentGroup) object;
                }else if (object instanceof Discipline){
                    d = (Discipline) object;
                }else{
                    logWrongObjectType(object,this);
                    return null;
                }
            }
            String[] str = new String[length];
            str[0] = gr.getName();
            str[1] = d.getName();
            return generateEventItem(user, gr.getId(), str);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;
            
            EventBodyElement[] el = new EventBodyElement[5];

            String[] str = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, length, this))
                return null;

            el[0] = new EventBodyElement(UserType.TEACHER.toString());
            el[1] = new EventBodyElement(item.<Teacher>getUser().getId(), item.<Teacher>getUser().getInitials(), EventBodyElement.teacherPage);
            el[2] = new EventBodyElement(" выставил(а) оценки группе ");
            el[3] = new EventBodyElement(item.getMultiId(), str[0], EventBodyElement.groupPage);
            el[4] = new EventBodyElement("по дисциплине "+str[1]);
            return el;
        }

        @Override
        protected  boolean addConditionByStudent(StringBuilder sb, AbstractStudent user) {
            super.addConditionByStudent(sb, user);
            sb.append(" and multiId = ").append(user.getGroup().getId());
            return false;
        }
    },
    /**
     * Обновление новостей.
     * аттрибуты:
     * {@link NewsItem}
     * пользователь любой
     */
    NEW_NEWS(true,"Добавление новости"){

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(checkSingleObjectArray(user,NewsItem.class, o))
                return null;

            NewsItem n = (NewsItem) o[0];
            return generateEventItem(user, n.getId(), n.getHeader());
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[2];
            el[0] = new EventBodyElement("Добавлена новость "+item.getEventBody());
            el[1] = new EventBodyElement(item.getMultiId(), "подбобнее", EventBodyElement.newsPage);
            return el;
        }

    },
    /**
     * Cвое сообщение. пользователь любой
     * аттрибут
     * {@link NewsItem}
     */
    OWN_MESSAGE(true,"Собственное сообщение"){

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(checkSingleObjectArray(user, NewsItem.class, o))
                return null;

            NewsItem n = (NewsItem) o[0];
            return generateEventItem(user, n.getId(), n.getAnnouncement());
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[1];
            el[0] = new EventBodyElement(item.getEventBody());
            return el;
        }
        
    },
    /**
     * Добавление личной фотографии.
     * аттрибутов нет. Пользователь любой
     */
    PHOTO_ADDITION(false,"Добавление фотографии"){

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(user == null){
                logger.debug(this+": user is null");
                return null;
            }
            return generateEventItem(user,0l, new String[]{user.getMediumPhoto()});
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = SYSTEM_LOGIN.parseEvent(item);
            el[2] = new EventBodyElement("добавил(а) фотографию");
            return el;
        }
    },
    /**
     * Отправка массовой рассылки админом или преподавателем всем.
     * аттрибутов нет
     * пользователь админ либо преподаватель
     */
    SPAM_ALL(true,"Рассылка всем"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(user == null){
                logger.debug(this+": user is null");
                return null;
            }
            switch (UserType.toType(user)) {
                case TEACHER:
                case ADMIN:
                    break;
                default:
                    throw new IllegalArgumentException("user type is "+UserType.toType(user));
            }
            String[] str = {null};
            return generateEventItem(user, 0L, str);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[4];
            AbstractPerson pers = item.<AbstractPerson>getUser();

            el[1] = new EventBodyElement(pers.getId(), pers.getInitials());
            setPageTypeByUser(el[1],item.getUser());

            el[0] = new EventBodyElement(userType(pers));
            el[2] = new EventBodyElement("сделал(а) массовую рассылку");
            return el;
        }
    },
    /**
     * Отправка массовой рассылки админом или преподавателем потоку.
     * аттрибуты:
     * &nbsp;&nbsp;{@link Speciality}
     * &nbsp;&nbsp;{@link Integer}
     * @see EventType#SPAM_ALL
     */
    SPAM_STREAM(true,"Рассылка потоку"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkObjectArrayAndUser(user, 2, this, o))
                return null;

            Speciality sp = null;
            Integer year = null;
            for(Object object:o){
                if(object instanceof Speciality){
                    sp = (Speciality) object;
                }else if (object instanceof Integer){
                    year = (Integer) object;
                }else {
                    logWrongObjectType(object,this);
                    return null;
                }
            }
            return generateEventItem(user, year.longValue(), sp.getName());
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = SPAM_ALL.parseEvent(item);
            el[3] = new EventBodyElement("специальности "+item.getEventBody()+" "+item.getMultiId()+" курса");
            return el;
        }
    },
    /**
     * Отправка массовой рассылки админом или преподавателем специальности.
     * аттрибуты:
     * &nbsp;&nbsp;{@link Speciality}
     * @see EventType#SPAM_ALL
     */
    SPAM_SPECIALITY(true,"Рассылка специальности"){
        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkSingleObjectArray(user, Speciality.class, o))
                return null;

            Speciality sp = (Speciality) o[0];
            return generateEventItem(user, sp.getId(), sp.getName());
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = SPAM_ALL.parseEvent(item);
            el[3] = new EventBodyElement("специальности "+item.getEventBody());
            return el;
        }
    },
    /**
     * Отправка массовой рассылки админом или преподавателем группе.
     * аттрибуты:
     * &nbsp;&nbsp;{@link Speciality}
     * &nbsp;&nbsp;{@link StudentGroup}
     * @see EventType#SPAM_ALL
     */
    SPAM_GROUP(true,"Рассылка группе"){
        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkSingleObjectArray(user, StudentGroup.class, o))
                return null;

            StudentGroup gr = (StudentGroup) o[0];
            return generateEventItem(user, gr.getId(), gr.getName());
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = SPAM_ALL.parseEvent(item);
            el[3] = new EventBodyElement(item.getId(), "группе "+item.getEventBody(),EventBodyElement.groupPage);
            return el;
        }
    },
    /**
     * Прочтение сообщения с заданием преподом.
     * аттрибуты:
     * {@link Discipline}
     * {@link AbstractStudent}
     * пользователь преподаватель
     */
    TASK_HAS_BEEN_READ(true, "Прочтение задания преподавателем"){

        private final int length = 2;

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(checkObjectArrayAndUser(user, UserType.TEACHER, this,2, o))
                return null;

            Discipline d = null;
            AbstractStudent st = null;
            for(Object object:o){
                if(object instanceof Discipline){
                    d = (Discipline) object;
                }else if(object instanceof AbstractStudent){
                    st = (AbstractStudent)object;
                }else{
                    logWrongObjectType(object,this);
                    return null;
                }
            }
            String[] str = new String[length];
            str[0] = d.getName();
            str[1] = fioFromUser(st);
            return generateEventItem(user, st.getId(), str);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[4];
            el[0] = new EventBodyElement(UserType.TEACHER.toString());
            el[1] = new EventBodyElement(item.<Teacher>getUser().getId(), item.<Teacher>getUser().getInitials(), EventBodyElement.teacherPage);
            
            String[] str = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, length, this))
                return null;

            el[2] = new EventBodyElement("прочитал(а) задание по дисциплине \""+str[0]+"\". Выполнивший студент(ка): ");
            el[3] = new EventBodyElement(item.getMultiId(), str[1], EventBodyElement.studentPage);
            return el;
        }

        @Override
        protected boolean addConditionByStudent(StringBuilder sb, AbstractStudent user) {
            super.addConditionByStudent(sb, user);
            sb.append(" and multiId = ").append(user.getId());
            return false;
        }

    },
    /**
     * Отправка задания преподавателю.
     * аттрибуты:
     * {@link AdvancedCurriculum}
     * пользователь студент
     */
    TASK_HAS_BEEN_SEND_TO_PREP(true,"Отправка задания"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkSingleObjectArray(user, UserType.STUDENT, AdvancedCurriculum.class, this, o))
                return null;

            AdvancedCurriculum c = (AdvancedCurriculum) o[0];
            return generateEventItem(user, c.getId(), c.getDiscipline().getName());
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[3];
            el[0] = new EventBodyElement(UserType.STUDENT.toString());
            el[1] = new EventBodyElement(item.<AbstractStudent>getUser().getId(), item.<AbstractStudent>getUser().getInitials(), EventBodyElement.studentPage);
            el[2] = new EventBodyElement("отправил(а) на проверку задание по дисциплине \""+item.getEventBody()+"\"");
            return el;
        }
    },
    /**
     * Завершение прохождения теста.
     * аттрибуты:
     * {@link TestEntity}
     * {@link Umk}
     * пользователь студент
     */
    TEST_END(true,"Завершение прохождения теста"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkObjectArrayAndUser(user, 2, this, o))
                return null;

            TestEntity t = null;
            Umk u = null;
            for(Object object:o){
                if(object instanceof TestEntity){
                    t = (TestEntity) object;
                }else if (object instanceof Umk){
                    u = (Umk) object;
                }else {
                    logWrongObjectType(object,this);
                    return null;
                }
            }
            String[] str = {u.getName(),t.getTitle(),t.getId().toString()};
            return generateEventItem(user, u.getId(), str);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            String[] str = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, 3, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[3];
            el[0] = new EventBodyElement(UserType.STUDENT.toString());
            el[1] = new EventBodyElement(item.<AbstractStudent>getUser().getId(), item.<AbstractStudent>getUser().getInitials(), EventBodyElement.studentPage);
            el[2] = new EventBodyElement("прошёл тест"+str[1]);
            return el;
        }

        @Override
        public EventBodyElement[] parseEventForAdmin(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[6];
            el[0] = new EventBodyElement(UserType.STUDENT.toString());
            el[1] = new EventBodyElement(item.<AbstractStudent>getUser().getId(), item.<AbstractStudent>getUser().getInitials(), EventBodyElement.studentPage);
            el[2] = new EventBodyElement("прошёл");
            String[] str = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, 3, this))
                return null;

            el[3] = new EventBodyElement(Long.valueOf(str[2]), str[1], EventBodyElement.testPage);
            el[4] = new EventBodyElement("по курсу");
            el[5] = new EventBodyElement(item.getMultiId(), str[0], EventBodyElement.umkPage);
            return el;
        }
    },
    /**
     * Создание модуля (занятия) в УМК.
     * аттрибуты:
     * {@link Umk}
     * пользователь админ
     */
    UMK_CREATE(true,"Создание УМК"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            if(checkSingleObjectArray(user, UserType.ADMIN,  Umk.class, this, o))
                return null;

            Umk u = (Umk) o[0];
            return generateEventItem(user, u.getId(), u.getName());
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[2];
            el[0] = new EventBodyElement("Создан  курс"+item.getEventBody());
            return el;
        }

        @Override
        public EventBodyElement[] parseEventForAdmin(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[2];
            el[0] = new EventBodyElement("Создан курс");
            el[1] = new EventBodyElement( item.getEventBody());
            return el;
        }
    },
    /**
     * Удаление модуля (занятия) в УМК.
     * аттрибуты:
     * {@link Umk}
     * пользователь админ
     */
    UMK_DELETE(true,"Удаление УМК"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            return UMK_CREATE.fillEventItem(user, o);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            EventBodyElement[] el = new EventBodyElement[1];
            el[0] = new EventBodyElement("Удалён курс "+item.getEventBody());
            return el;
        }

        @Override
        public EventBodyElement[] parseEventForAdmin(EventItem item) {
            EventBodyElement[] el = UMK_CREATE.parseEventForAdmin(item);
            el[0] = new EventBodyElement("Удалён курс ");
            return UMK_CREATE.parseEventForAdmin(item);
        }
    },
    /**
     * Редактирование модуля (занятия) в УМК.
     * аттрибуты:
     * {@link Umk}
     * пользователь админ
     */
    UMK_EDIT(true,"Редактирование УМК"){

        public EventItem fillEventItem(AbstractUser user, Object ... o){
            return UMK_CREATE.fillEventItem(user, o);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[1];
            el[0] = new EventBodyElement("Изменён курс "+item.getEventBody());
            return el;
        }

        @Override
        public EventBodyElement[] parseEventForAdmin(EventItem item) {
            EventBodyElement[] el = UMK_CREATE.parseEventForAdmin(item);
            el[0] = new EventBodyElement("Изменён курс ");
            return UMK_CREATE.parseEventForAdmin(item);
        }
    },
    /**
     * Отсылка сообщения студенту админом или преподом.
     * аттрибуты:
     * {@link Message}
     * пользователль админ или препод
     */
    MESSAGE_TO_STUDENT(true,"Отсылка сообшения"){

        private final int length = 2;
        
        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(checkSingleObjectArray(user, Message.class, o))
                return null;

            Message m = (Message) o[0];
            String[] str = new String[length];
            str[0] = m.getTheme();
            str[1] = fioFromUser(m.getAuthor());
            return generateEventItem(user, m.getId(), str);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            String[] str = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, length, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[3];
            el[0] = new EventBodyElement(str[1]+" отправил личное сообщение студенту с темой \""+str[0]+"\". Получатель: ");
            el[1] = new EventBodyElement(item.<AbstractStudent>getUser().getId(), item.<AbstractStudent>getUser().getInitials(), EventBodyElement.studentPage);
            return el;
        }

    },
    /**
     * Добавление теста в УМК.
     * аттрибуты:
     * {@link TestEntity}
     * {@link Umk}
     * пользователь админ
     */
    TEST_ADD(true,"Добавление теста в УМК"){

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            return TEST_END.fillEventItem(user, o);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[4];

            String[] str = item.getEventBody().split(splitter);
            if(checkSplittetArrayLength(str, 3, this))
                return null;

            el[0] = new EventBodyElement("К курсу");
            el[1] = new EventBodyElement(str[0]);
            el[2] = new EventBodyElement("добавлен тест");
            el[3] = new EventBodyElement(Long.valueOf(str[2]), str[1], EventBodyElement.teacherPage);
            return el;
        }

    },
    /**
     * Изменение теста в УМК.
     * аттрибуты:
     * {@link TestEntity}
     * {@link Umk}
     */
    TEST_CHANGE(true,"Изменение теста в УМК"){

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            return TEST_END.fillEventItem(user, o);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            EventBodyElement[] el = TEST_ADD.parseEvent(item);
            el[0] = new EventBodyElement("у курса");
            el[2] = new EventBodyElement("изменён тест");
            return el;
        }

    },
    /**
     * Изменение расписания группы.
     * аттрибуты:
     * {@link Lesson}
     * пользователь админ
     */
//    SHEDULE_CHANGE(true,"Изменение расписания группы"){
//
//        @Override
//        public EventItem fillEventItem(AbstractUser user, Object... o) {
//            if(checkSingleObjectArray(user, UserType.ADMIN, Lesson.class, this, o))
//                return null;
//
//            Lesson l = (Lesson) o[0];
//            String[] str = new String[l.getGroups().size()];
//            ListIterator<StudentGroup>i = l.getGroups().listIterator();
//            while(i.hasNext()){
//                str[i.nextIndex()] = i.next().getId().toString();
//            }
//            i = l.getGroups().listIterator();
//            while(i.hasNext()){
//                str[i.nextIndex()] = i.next().getName();
//            }
//            return generateEventItem(user, 0l, str);
//        }
//
//        @Override
//        public EventBodyElement[] parseEvent(EventItem item) {
//            String[] str = checkArray(item);
//            if(str == null)
//                return null;
//
//            int size = str.length;
//            EventBodyElement[] el = new EventBodyElement[size/2];
//            StringBuilder sb = new StringBuilder("У групп ");
//
//            for(int i = 0;i<size/2;i++){
//                sb.append(str[i+size]).append(", ");
//            }
//
//            el[0] = new EventBodyElement(sb.toString());
//            return el;
//        }
//
//        @Override
//        public EventBodyElement[] parseEventForAdmin(EventItem item) {
//            String[] str = checkArray(item);
//            if(str == null)
//                return null;
//
//            int size = str.length;
//            EventBodyElement[] el = new EventBodyElement[size/2];
//            el[0] = new EventBodyElement("У групп ");
//            for(int i = 0;i<size/2;i++){
//                el[i+1] = new EventBodyElement(Long.valueOf(str[i]), str[i+size], EventBodyElement.groupPage);
//            }
//            return el;
//        }
//
//        @Override
//        protected boolean addConditionByStudent(StringBuilder sb, AbstractStudent user) {
//            super.addConditionByStudent(sb, user);
//            sb.append(" and multiId like %'").append(user.getId()).append("%' ");
//            return false;
//        }
//
//        private String[] checkArray(EventItem item){
//            if(checkEventItem(item, this))
//                return null;
//
//            String[] str = item.getEventBody().split(splitter);
//            if(str == null){
//                logger.debug(this+": string array is null");
//                return null;
//            }
//            if(str.length<2){
//                logger.debug(this+": string array length less than 2");
//                return null;
//            }
//            return str;
//        }
//
//    },
    /**
     * Ответ на пост в обсуждении.
     * аттрибуты:
     * {@link NewsItem}
     * {@link Node}
     */
    POST_ANSWER(true,"Ответ на сообщение в ветке обсуждений"){

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(o == null){
                logger.debug(this+": o is null");
                return null;
            }
            Node node = null;
            NewsItem news=null;
            Long id;

            for(Object object:o){
                    if(object instanceof Node){
                        node = (Node) object;
                    }else if (object instanceof NewsItem){
                        news = (NewsItem) object;
                    }}
             String[] str=new String[o.length+1];
             ForumTypes type = node.getRoot().getObjectType();
             str[0] = type.toString();
                  
             if(ForumTypes.NEWS.equals(type)&&news!=null){
                str[1] = news.getHeader();
                str[2] = node.getParent().getId().toString();
                str[3]=(String)o[2];
                str[4]=(String)o[3];
                id = news.getId();
             }else{
                 id = node.getRoot().getObjectId();
                 str[1]=node.getParent().getId().toString();
                 str[2]=(String)o[1];
                 str[3]=(String)o[2];
            }
            return generateEventItem(node.getParent().getUser(), id, str);
        }


        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;
            EventBodyElement[] el = new EventBodyElement[3];
            String str[] = item.getEventBody().split(splitter);
    
            String subjectType = str[0];
            ForumTypes postType = ForumTypes.parse(subjectType);

            switch (postType) {
               case STUDENT_FAQ:
                    el[0] = new EventBodyElement(str[3]+" ответил(а) на ваш пост ");
                    el[1] = new EventBodyElement("&amp;page="+str[2]+"#"+str[1], "на техническом форуме", EventBodyElement.forumStudentTechPage);
                    break;
              case STUDENT_ORG:
                    el[0] = new EventBodyElement(str[3]+" ответил(а) на ваш пост ");
                    el[1] = new EventBodyElement("&amp;page="+str[2]+"#"+str[1], "на организационном форуме", EventBodyElement.forumTeacherOrgPage);
                    break;
                case NEWS:
                    el[0] = new EventBodyElement("К вашему комментарию к новости ");
                    el[1] = new EventBodyElement("?id="+item.getMultiId()+"&amp;page="+str[3]+"#"+str[2], str[1], EventBodyElement.newsPage);
                    el[2] = new EventBodyElement(" "+ str[4]+"  добавил(а) ответ.");
                    break;
            }
            return el;
        }

        @Override
        protected boolean addConditionByStudent(StringBuilder sb, AbstractStudent user) {
            super.addConditionByStudent(sb, user);
            sb.append(" and user.id = ").append(user.getId());
            return false;
        }

    },
    /**
     * Добавление поста в обсуждении.
     * аттрибуты:
     * {@link NewsItem}
     * {@link Node}
     */
    POST_ADD(true,"Добавление поста в обсуждении"){

        private final int length = 2;

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(user == null || o == null || o.length==0){
              //  logger.debug(this+": user is null or objects is empty");
              //  return null;
            }
            
            NewsItem news = null;
            Node node = null;
            if(o.length == 1 && (o[0] instanceof Node))
                node = (Node)o[0];
            else
                for(Object object:o){
                    if(object instanceof Node){
                        node = (Node) object;
                    }else if (object instanceof NewsItem){
                        news = (NewsItem) object;
                    }
                }

            final String[] str = new String[length];

            long id = 0;
            ForumTypes type = node.getRoot().getObjectType();
            str[0] = type.toString();

            if(ForumTypes.NEWS.equals(type)&&news!=null){
                str[1] = news.getHeader();             
                id = news.getId();
            }              
          return generateEventItem(user, id, str);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            EventBodyElement[] el = new EventBodyElement[5];
            String str[] = item.getEventBody().split(splitter);
            el[0] = new EventBodyElement(userType(item.getUser()));
            el[1] = new EventBodyElement(item.getUser().getId(), fioFromUser(item.getUser()));
            setPageTypeByUser(el[1], item.getUser());

            String subjectType = str[0];
            ForumTypes postType = ForumTypes.parse(subjectType);
            switch (postType) {
                case STUDENT_FAQ:
                    el[3] = new EventBodyElement("добавил(а) комментарий на ");
                    el[4] = new EventBodyElement("", " техническом форуме студентов", EventBodyElement.forumStudentTechPage);
                    break;
              case STUDENT_ORG:
                    el[3] = new EventBodyElement("добавил(а) комментарий на ");
                    el[4] = new EventBodyElement("", "на организационном форуме студентов", EventBodyElement.forumStudentOrgPage);
                    break;
                case NEWS:
                    el[3] = new EventBodyElement("добавил(а) комментарий к новости ");
                    el[4] = new EventBodyElement(item.getMultiId(), str[1], EventBodyElement.newsPage);
                    break;
            }
            return el;
        }

    },
    /**
     * Просмотр новости.
     * аттрибуты:
     * {@link NewsItem}
     */
    NEWS_VIEW(false,"Просмотр новости"){

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            return NEW_NEWS.fillEventItem(user, o);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;
            
            EventBodyElement[] el = new EventBodyElement[4];
            el[0] = new EventBodyElement(fioFromUser(item.getUser())+" просматривал(а) новость ");
            el[1] = new EventBodyElement(item.getMultiId(), item.getEventBody(), EventBodyElement.newsPage);
            return el;
        }
    },
    /**
     * Изменение своих учебных планов преподавателем
     *
     */
    CURRICULUMS_CHANGING_BY_TEACHER(false,"Изменение своих учебных планов преподавателем"){

        @Override
        public EventItem fillEventItem(AbstractUser user, Object... o) {
            if(checkObjectArrayAndUser(user, UserType.TEACHER, this))
                return null;
            String[] str = {};
            return generateEventItem(user, null, str);
        }

        @Override
        public EventBodyElement[] parseEvent(EventItem item) {
            if(checkEventItem(item, this))
                return null;

            EventBodyElement[] el = new EventBodyElement[3];
            el[0] = new EventBodyElement(UserType.TEACHER.toString()+" ");
            el[1] = new EventBodyElement(item.getUser().getId(), fioFromUser(item.getUser()), EventBodyElement.teacherPage);
            el[2] = new EventBodyElement(" изменил свои учебные планы.");
            return el;
        }
    }
    ;

    private static final long serialVersionUID = 86L;
    
    private final static Log logger = LogFactory.getLog(EventType.class);

    /**
     * разделитель элементов массива в строке текстового тела события.
     * используется для формирования строки события и её парсинга
     */
    private static final String splitter = "@#";

    private EventType(Boolean chosen,String rus) {
        this.chosen = chosen;
        this.rus = rus;
    }

    private Boolean chosen;

    private String rus;

    protected boolean isChosen() {
        return chosen;
    }

    /**
     *
     * @return тип события по-русски
     */
    public String getRus() {
        return rus;
    }

    /**
     *
     * @param user пользователь, к которому привязано событие
     * @param o набор объектов, из которых будет сформировано тело события
     * @return сгенерированный  {@link EventItem}
     */
    public abstract EventItem fillEventItem(AbstractUser user, Object ... o);

    /**
     * Формируется массив {@code EventBodyElement} - текстовое тело события, который будет выведен на странице
     * @param item событие
     * @return EventBodyElement
     */
    public abstract EventBodyElement[]parseEvent(EventItem item);

    /**
     * формирует тело события для адмнинистратора
     * @param item событие
     * @return EventBodyElement
     * @see #parseEvent(org.sgu.oecde.journal.EventItem)
     */
    public EventBodyElement[]parseEventForAdmin(EventItem item){
        return parseEvent(item);
    }

    /**
     * если метод переопределён у события, то добавляет условие в запрос, содержащийся в {@code StringBuilder}
     * по умолчанию, метод возвращает {@code true} для обозначения, что условие не было добавлено и запрос
     * остался без изменения.
     * Используется для получения событий конкретного студента для набора событий {@link FilterType#studentEvents}
     * @param sb запрос
     * @param user студент, на основе данных которого будет строится дополнительное условие
     * @return true/false
     */
    protected boolean addConditionByStudent(StringBuilder sb, AbstractStudent user){
        return true;
    }

    /**
     * Генерирует новый {@link EventItem}
     * @param user пользователь
     * @param id мульти айди
     * @param body массив строк, из которых будет составлено текстовое тело события, где каждый элемент
     * массива отделён от другогос помощью {@link #splitter}
     * @return EventItem
     * @see #generateEventItem(org.sgu.oecde.core.users.AbstractUser, java.lang.Long, java.lang.String)
     */
    private static EventItem generateEventItem(AbstractUser user, Long id, String[]body) {
        StringBuilder sb = new StringBuilder();
        for (String s : body) {
            if (s !=null) sb.append(s).append(splitter);
        }
        return generateEventItem(user, id, sb.toString());
    }

    /**
     * Генерирует новый {@link EventItem}
     * @param user пользователь
     * @param id мульти айди
     * @param body текстовое тело события
     * @return
     */
    private static EventItem generateEventItem(AbstractUser user, Long id, String body) {
        EventItem item = new EventItem(user, id, body);
        return item;
    }

    private static String fioFromUser(AbstractUser user){
        Assert.notNull(user);
        String fio =  user instanceof AbstractPerson
                ?((AbstractPerson)user).getInitials()
                :user.getUsername();
        return fio;
    }

    private static String userType(AbstractUser user){
        UserType u = UserType.toType(user);
        Assert.notNull(u);
        return u.toString();
    }

    private static void logWrongObjectType(Object object, EventType e){
        if(logger.isDebugEnabled()){
            logger.debug(e+": object is "+object!=null?object.getClass().getSimpleName():null);
        }
    }

    private static boolean checkObjectArrayAndUser(AbstractUser user,int size, EventType e,Object...o){
        return checkObjectArrayAndUser(user, null, e, size, o);
    }

    private static boolean checkObjectArrayAndUser(AbstractUser user,UserType type, EventType e,int size,Object...o){
        boolean condition = o == null||o.length!=size||user==null;
        if(condition){
            if(logger.isDebugEnabled())
                logger.debug(e+": o is null "+(o==null)+"; user is null "+(user == null)+"; o.length!="+size+" "+(o!=null?o.length!=size:0));
            return true;
        }
        return checkObjectArrayAndUser(user, type, e);
    }

    private static boolean checkObjectArrayAndUser(AbstractUser user,UserType type, EventType e){
        if(user!=null&&type!=null&&!type.equals(UserType.toType(user))){
            logger.debug(e+": user type is "+UserType.toType(user));
            return true;
        }
        return false;
    }

    private static boolean checkSingleObjectArray(AbstractUser user,Class type,Object...o){
        return checkSingleObjectArray(user, null, type, null, o);
    }

    private static boolean checkSingleObjectArray(AbstractUser user,UserType userType,Class type, EventType e,Object...o){
        Assert.notNull(type);
        boolean checked=checkObjectArrayAndUser(user,userType, e, 1, o);
        if(checked||!type.isInstance(o[0])){
            if(logger.isDebugEnabled()&&!checked){
                logger.debug(e+": o[0] is "+o[0]);
            }
            return true;
        }
        return false;
    }

    private static boolean checkEventItem(EventItem item, EventType e){
        if(item == null){
            logger.debug(e+": item is null "+(item == null));
            return true;
        }else if(item.getUser() == null||item.getEventBody()==null||item.getId() == null){
            logger.debug(e+": user is null"+(item.getUser() == null) + "; body is null "+(item.getEventBody()==null)
                    +"; id is null "+(item.getId() == null));
            return true;
        }
        return false;
    }

    private static boolean checkSplittetArrayLength(String[] arr,int length, EventType e){
        if(arr == null){
            logger.debug(e+": array is null");
            return true;
        } else if (arr.length!=length){
            logger.debug(e+": array has wrong arr.length. Actual is "+arr.length+", expected is "+length);
            return true;
        }
        return false;
    }

    private static void setPageTypeByUser(EventBodyElement el, AbstractUser user){
        switch (UserType.toType(user)) {
            case STUDENT:
                el.setType(EventBodyElement.studentPage);
                break;
            case TEACHER:
                el.setType(EventBodyElement.teacherPage);
                break;
            case ADMIN:
                el.setType(EventBodyElement.adminPage);
                break;
            case SUPERVISOR:
                el.setType(EventBodyElement.supervisorPage);
                break;
            default:
                throw new IllegalArgumentException("user type is "+UserType.toType(user));
        }
    }
}