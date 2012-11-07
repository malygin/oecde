package org.sgu.oecde.controlworks;

import org.sgu.oecde.core.BasicItem;

/**
 * @author shihovmy
 * сущность попытка выполнения контрольной работы
 */
public class ControlWorkAttempt  extends BasicItem{
    /**
     * дата отправки работы
     */
    private String attemptDate;
    /**
     * путь к файлу с работой
     */
    private String filePath;
    /**
     * прочитано ли преподом
     */
    private Boolean read;

    /**
     * ссылка на работу
     */
    private ControlWork work;
    private static final long serialVersionUID = 90L;

    public ControlWorkAttempt() {
    }

    /**
     *
     * @return дата
     */
    public String getAttemptDate() {
        return attemptDate;
    }

    /**
     * дата
     * @param attemptDate
     */
    public void setAttemptDate(String attemptDate) {
        this.attemptDate = attemptDate;
    }

    /**
     *
     * @return путь к кр
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * путь к кр
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @return прочитана ли
     */
    public Boolean  getRead() {
        return read;
    }

    /**
     * прочитана ли
     * @param read
     */
    public void setRead(Boolean  read) {
        this.read = read;
    }

    /**
     *
     * @return работа
     */
    public ControlWork getWork() {
        return work;
    }

    /**
     * рабоа
     * @param work
     */
    public void setWork(ControlWork work) {
        this.work = work;
    }

    @Override
    public String toString() {
    StringBuffer sb = new StringBuffer(super.toString());
        sb.append("\nработа: ").append(work).append(";\n");
        sb.append("путь: ").append(filePath).append(";\n");
        return sb.toString();
    }
}