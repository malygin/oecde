package org.sgu.oecde.controlworks;

import org.sgu.oecde.core.BasicItem;

/**
 * @author shihovmy
 * сущность попытка выполнения контрольной работы
 */
public class ControlWorkAttempt  extends BasicItem{
    /**
     * attempDate - дата отправки работы
     */
    private String attemptDate;
    /**
     * filePath - путь к файлу с работой
     */
    private String filePath;
    /**
     * read - прочитано ли преподом
     */
    private Boolean read;

    private ControlWork work;
    private static final long serialVersionUID = 90L;

    public ControlWorkAttempt() {
    }

    public String getAttemptDate() {
        return attemptDate;
    }

    public void setAttemptDate(String attemptDate) {
        this.attemptDate = attemptDate;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean  isRead() {
        return read;
    }

    public void setRead(Boolean  read) {
        this.read = read;
    }

    public ControlWork getWork() {
        return work;
    }

    public void setWork(ControlWork work) {
        this.work = work;
    }
}