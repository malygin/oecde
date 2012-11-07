package org.sgu.oecde.discussion;

import java.util.Set;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractUser;

/**
 * корневой элемент ветки обсуждений
 * @author Basakovvy
 * @todo почистить надо от лишних методов
 */
public class Root extends BasicItem implements Comparable {

    //id объекта к которому прицеплен рут
    private Long objectId;
    //тип объекта к которому прицеплен рут
    private ForumTypes objectType;
    private String time;
    //открыт ли рут, пока не используется
    private Boolean open;
    //заголовок рута, типа "город Саратов"
    private String title;
    //автор
    private AbstractUser user;
    //список нодов
    private Set<Node> children;
    private static final long serialVersionUID = 88L;

    public Root() {
    }


    public Root(Long id) {
        setId(id);
    }

    public Root(Long objectId, ForumTypes objectType) {
        this.objectId = objectId;
        this.objectType = objectType;
    }
    
    public AbstractUser getUser() {
        return user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    //@TODO У детей удалить этот корень из родителей
    public void setChildren(Set<Node> nodes) {
        this.children = nodes;
    }

    public Set<Node> getChildren() {
        return children;
    }

    public ForumTypes getObjectType() {
        return objectType;
    }

    public void setObjectType(ForumTypes objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Root) {
            Root root = (Root) o;
            return time.compareTo(root.time);
        }
        throw new IllegalArgumentException();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
  
}
