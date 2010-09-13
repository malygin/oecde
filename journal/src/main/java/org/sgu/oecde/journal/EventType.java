package org.sgu.oecde.journal;

/**
 * @author basakovvy
 */
public enum EventType {
    /** Типы событий.*/
    /**
     * Вход на портал
     */
    SYSTEM_LOGIN,
    /**
     * Просмотр модуля (занятия) в умк.
     */
    UMK_VIEW,
    /**
     * отметка о получении рукописной контрольной рабоы
     */
    HAND_WRITTEN_CONTROL_WORK,
    /**
     * Выставление оценки.
     */
    GRADING,
    /**
     * Обновление новостей.
     */
    NEW_NEWS,
    /**
     * Cвое сообщение.
     */
    OWN_MESSAGE,
    /**
     * Добавление личной фотографии.
     */
    PHOTO_ADDITION,
    /**
     * Удаление личной фотографии.
     */
    PHOTO_DELETION,
    /**
     * Отправка массовой рассылки админом или преподавателем всем.
     */
    SPAM_ALL,
    /**
     * Отправка массовой рассылки админом или преподавателем потоку.
     */
    SPAM_STREAM,
    /**
     * Отправка массовой рассылки админом или преподавателем специальности.
     */
    SPAM_SPECIALITY,
    /**
     * Отправка массовой рассылки админом или преподавателем группе.
     */
    SPAM_GROUP,
    /**
     * Прочтение сообщения с заданием преподом.
     */
    TASK_HAS_BEEN_READ,
    /**
     * Отправка задания преподавателю.
     */
    TASK_HAS_BEEN_SEND_TO_PREP,
    /**
     * Завершение прохождения теста.
     */
    TEST_END,
    /**
     * Создание модуля (занятия) в УМК.
     */
    UMK_CREATE,
    /**
     * Удаление модуля (занятия) в УМК.
     */
    UMK_DELETE,
    /**
     * Редактирование модуля (занятия) в УМК.
     */
    UMK_EDIT,
    /**
     * Отсылка сообщения студенту админом или преподом.
     */
    MESSAGE_TO_STUDENT,
    /**
     * Добавление теста в УМК.
     */
    TEST_ADD,
    /**
     * Изменение теста в УМК.
     */
    TEST_CHANGE,
    /**
     * Изменение расписания группы.
     */
    SHEDULE_CHANGE,
    /**
     * Ответ на пост в обсуждении.
     */
    POST_ANSWER,
    /**
     * Добавление поста в обсуждении.
     */
    POST_ADD,
    /**
     * Просмотр новости.
     */
    NEWS_VIEW
    ;
    private static final long serialVersionUID = 86L;

    public int getId() {
        switch (this) {
            case SYSTEM_LOGIN:
                return 1;
            case PHOTO_ADDITION:
                return 6;
            case PHOTO_DELETION:
                return 7;
            case TEST_END:
                return 9;
            case GRADING:
                return 45;
            case TASK_HAS_BEEN_SEND_TO_PREP:
                return 50;
            case TASK_HAS_BEEN_READ:
                return 51;
            case UMK_VIEW:
                return 3;
            case UMK_CREATE:
                return 53;
            case UMK_EDIT:
                return 52;
            case UMK_DELETE:
                return 54;
            case SPAM_ALL:
                return 94;
            case SPAM_STREAM:
                return 95;
            case SPAM_SPECIALITY:
                return 96;
            case SPAM_GROUP:
                return 97;
            case OWN_MESSAGE:
                return 77;
            case NEW_NEWS:
                return 73;
            case MESSAGE_TO_STUDENT:
                return 115;
            case TEST_ADD:
                return 136;
            case TEST_CHANGE:
                return 137;
            case SHEDULE_CHANGE:
                return 138;
            case POST_ANSWER:
                return 157;
            case NEWS_VIEW:
                return 179;
            case POST_ADD:
                return 199;
        }
        return 0;
    }

    public static EventType value(String key) {
        for (EventType event : EventType.values()) {
            if (event.toString().equalsIgnoreCase(key)) {
                return event;
            }
        }
        return null;
    }

    public static EventType value(int key) {
        for (EventType event : EventType.values()) {
            if (event.getId() == key) {
                return event;
            }
        }
        return null;
    }

    public static boolean contains(String key) {
        return value(key) != null;
    }

    public static boolean contains(int key) {
        return value(key) != null;
    }

    public static boolean equals(String str) {
        return contains(str);
    }

    public String getName() {
        return this.name();
    }

    /**
     *
     * @return тип события по-русски
     */
    public String getRus() {
        switch (this) {
            case SYSTEM_LOGIN:
                return "Вход в систему";
            case PHOTO_ADDITION:
                return "Добавление фотографии";
            case PHOTO_DELETION:
                return "Удаление фотографии";
            case TEST_END:
                return "Завершение прохождения теста";
            case GRADING:
                return "Выставление оценок";
            case TASK_HAS_BEEN_SEND_TO_PREP:
                return "Отправка задания";
            case TASK_HAS_BEEN_READ:
                return "Прочтение задания преподавателем";
            case UMK_VIEW:
                return "Просмотр УМК";
            case UMK_CREATE:
                return "Создание УМК";
            case UMK_EDIT:
                return "Редактирование УМК";
            case UMK_DELETE:
                return "Удаление УМК";
            case SPAM_ALL:
                return "Рассылка всем";
            case SPAM_STREAM:
                return "Рассылка потоку";
            case SPAM_SPECIALITY:
                return "Рассылка специальности";
            case SPAM_GROUP:
                return "Рассылка группе";
            case OWN_MESSAGE:
                return "Собственное сообщение";
            case NEW_NEWS:
                return "Добавление новости";
            case MESSAGE_TO_STUDENT:
                return "Отсылка сообшения";
            case TEST_ADD:
                return "Добавление теста в УМК";
            case TEST_CHANGE:
                return "Изменение теста в УМК";
            case SHEDULE_CHANGE:
                return "Изменение расписания группы";
            case POST_ANSWER:
                return "Ответ на сообщение в ветке обсуждений";
            case NEWS_VIEW:
                return "Просмотр новости";
            case POST_ADD:
                return "Добавлени комментария в ветках обсуждений";
        }
        return "";
    }
    }
