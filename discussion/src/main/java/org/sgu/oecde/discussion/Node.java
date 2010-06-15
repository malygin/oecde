/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.discussion;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractUser;

/**
 * @author Basakovvy
 */
public class Node extends BasicItem implements Comparable {

    private String message;
    private String time;
    private AbstractUser user;

    private Boolean open;
    private Root root;
    private Node parent;
    private Set<Node> children;
    private static final long serialVersionUID = 87L;

    public Node() {
        children = new TreeSet<Node>();
    }

    public Node(Long id, String message, String time) {
        this();
        setId(id);
        this.time = time;
        this.message = message;
    }

    public Node(Long id, String message, String time, SortedSet<Node> children) {
        this(id, message, time);
        this.children = children;
    }

    public Node(Long id) {
        setId(id);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Node) {
            return time.compareTo(((Node) o).getTime());
        }
        throw new IllegalArgumentException("instance of Node.class expected");
    }

    /**
     * Добавляет дочерний элемент.
     *
     * @param child добавляемый элемент.
     * @return true, если успешно.
     */
    public Boolean addChild(Node child) {
        if (child != null && children.add(child)) {
            child.setParent(this);
            return true;
        }
        return false;
    }

    public Node getChild(Long id) {
        for (Node node : children) {
            if (node.getId() == id) {
                return node;
            }
            Node res = node.getChild(id);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    public Boolean addChildren(Set<Node> nodes) {
        return children.addAll(nodes);
    }

    public void setChildren(Set<Node> nodes) {
        children = nodes;
    }

    public Boolean removeChild(Node child) {
        return child != null && children.contains(child) && children.remove(child);
    }

    /**
     * Возвращает список дочерних элементов.
     *
     * @return элементы
     */
    public Set<Node> getChildren() {
        return children;
    }

    public SortedSet<Node> getAllNodes() {
        SortedSet<Node> set = new TreeSet<Node>();
        for (Node node : children) {
            set.addAll(node.getAllNodes());
        }
        return set;
    }

    /**
     * Возвращает элемент ветки, к которому "привязан" текущий элемент.
     *
     * @return родитель.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Устанавливает элемент ветки,
     * который будет "родительским" по отношению к текущему элементу.
     *
     * @param parent родитель.
     */
    public void setParent(Node parent) {
        this.parent = parent;
//        //На всякий случай(((
//        if (this.equals(parent)) {
//            return;
//        }
//        if (parent != null) {
//            if (this.parent != null) {
//                this.parent.removeChild(this);
//            }
//            this.parent = parent;
//        } else {
//            final String message = "Родитель записи не может быть равен null";
//            throw new IllegalArgumentException(message, new NullPointerException());
//        }
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public Root getRoot() {
        return root;
    }

    public Boolean isOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public AbstractUser getUser() {
        return user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public int getNodesCount() {
        int result = children.size();
        for (Node node : children) {
            result += node.getNodesCount();
        }
        return result;
    }
}
