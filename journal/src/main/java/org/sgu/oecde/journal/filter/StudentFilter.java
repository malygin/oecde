package org.sgu.oecde.journal.filter;


import java.util.Iterator;
import java.util.List;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.journal.EventType;
import org.springframework.stereotype.Service;

/**
 * @author basakovvy
 */

@Service
public class StudentFilter extends BaseFilter {

    /**
     * Список УМК, за которыми позволено следить данному студенту.
     */
    protected List<Umk> umkList;
    private static String StudentFilterCookiePath = "StudentFilter";

    public StudentFilter() {
        availableEvents.clear();
        availableEvents.add(EventType.SPAM_ALL);
        availableEvents.add(EventType.SPAM_GROUP);
        availableEvents.add(EventType.SPAM_SPECIALITY);
        availableEvents.add(EventType.SPAM_STREAM);
        availableEvents.add(EventType.GRADING);
        availableEvents.add(EventType.TASK_HAS_BEEN_READ);
        availableEvents.add(EventType.UMK_EDIT);
        availableEvents.add(EventType.UMK_CREATE);
        availableEvents.add(EventType.UMK_DELETE);
        availableEvents.add(EventType.NEW_NEWS);
        availableEvents.add(EventType.OWN_MESSAGE);
        availableEvents.add(EventType.POST_ANSWER);
//        availableEvents.add(EventType.NEW_NEWS);
//        availableEvents.add(EventType.MESSAGE_TO_STUDENT);
    }

    public void setUmkList(List<Umk> umkList) {
        this.umkList = umkList;
    }

//    @Override
//    public String getAddString() {
//        return " AND ID_USER = " + getUserId();

    //    }

    @Override
    public String getAddCondition(EventType event) {
        final Student user = (Student) getUserItem();
        int spamId = 0;
        switch (event) {
            case GRADING:
                Long gradeId = user.<Group>getGroup().getSpeciality().getId() * 10000 + user.getGroup().getId();
                return " AND MULTI_ID = " + gradeId;
            case SPAM_GROUP:
                spamId += user.getGroup().getId();
            case SPAM_SPECIALITY:
                spamId += user.<Group>getGroup().getSpeciality().getId() * 10000;
            case SPAM_STREAM:
                spamId += user.<Group>getGroup().getYear() * 10000 * 10000;
                return " AND MULTI_ID = " + spamId;
            case UMK_CREATE:
            case UMK_EDIT:
            case UMK_DELETE:
                StringBuilder sb = new StringBuilder();
                sb.append(" AND MULTI_ID in (");
                Iterator<Umk> it = umkList.iterator();
                for (int i = 0; it.hasNext(); i++) {
                    sb.append(it.next().getId());
                    if (i != umkList.size() - 1) {
                        sb.append(",");
                    } else {
                        sb.append(")");
                    }
                }
                return sb.toString();
            case TASK_HAS_BEEN_READ:
                return new StringBuilder("AND MULTI_ID = ").append(getUserId()).toString();
            case POST_ANSWER:
                Long multiId = userItem.getId() *100 +  UserType.toType(userItem).toInt();
                return new StringBuilder("AND MULTI_ID = ").append(multiId).toString();
            default:
                return super.getAddCondition(event);
        }
    }

    public void setUser(AbstractUser userItem) {
        if (userItem instanceof Student) {
            super.setUserItem(userItem);
            super.setUserId(userItem.getId());
        } else {
            throw new IllegalArgumentException(Student.class + " required, " + userItem.getClass() + " found");
        }
    }

    @Override
    public String getCookiePath() {
        return StudentFilterCookiePath;
    }
}
