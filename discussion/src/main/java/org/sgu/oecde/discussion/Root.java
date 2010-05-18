/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.discussion;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractUser;

/**
 * @author Basakovvy
 */
public class Root extends BasicItem implements Comparable {

    private int objectId;
    private ForumTypes objectType;
    private String time;
    private boolean open = true;
    private String title;
    private AbstractUser user;
    private Set<Node> children;

    //private HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();

    public Root() {
    }


    public Root(int id) {
        setId(id);
    }

    public Root(int objectId, ForumTypes objectType) {
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public boolean addChild(Node node) {
        if (node != null && children.add(node)) {
            node.setRoot(this);
            return true;
        }
        return false;
    }

    private Node getNearestNode(int id) {
        for (Node node : children) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }

    public AbstractUser getUser() {
        return user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public void addChildren(Collection<Node> nodes) {
        for (Node node : nodes) {
            addChild(node);
        }
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

    public int getNodesCount() {
        int result = children.size();
        for (Node node : children) {
            result += node.getNodesCount();
        }
        return result;
    }

    /**
     * Возвращает записей на определенную страницу.
     */
    public Set<Node> getPage(int i) {
        int nodesPerPage = 5;
        int[][] pages = null;
        int[] numbers = pages[i];
        Set<Node> res = new TreeSet<Node>();
        for (int k : numbers) {
            res.add(getNearestNode(k));
        }
        return res;
    }

    public int getPages() {
        return 0;
    }

    public Set<Node> getNodesForPage(int pageNum) {
        return null;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("**").append(title).append("**").append("\n");
        for (Node node : children) {
            sb.append(print(1, node));
        }
        return sb.toString();
    }

    public String print(int i, Node node) {
        StringBuilder sb = new StringBuilder();
        for (int k = 1; k <= i; k++) {
            sb.append("      ");
        }
        sb.append(node.getMessage()).append("\n");
//        System.out.println(node.getMessage());
        i++;
        for (Node child : node.getChildren()) {
            sb.append(print(i, child));
        }
        return sb.toString();
    }


    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
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

    public String printFields() {
        StringBuilder sb = new StringBuilder();
        final Class aClass = this.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            try {
                sb.append(field.getName()).append(" = ");
                sb.append(field.get(this)).append("\n");
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        return sb.toString();
    }


}
