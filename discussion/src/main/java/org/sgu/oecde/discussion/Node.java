package org.sgu.oecde.discussion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.discussion.util.NodeRevertComparator;

/**
 * пост ветки обсуждений
 * @author Basakovvy
 * @todo почистить надо от лишних методов
 */
public class Node extends BasicItem implements Comparable {

   
    private String message; 
    private String time;
    private AbstractUser user;
    //открыт ли, пока не используется, возможно пригодится если решим не удалять ноды, а помечать как закрытые
    private Boolean open;
    // корень к которому относитя нод, если нод потомок нода - пустой
    private Root root;
    // нод - родитель
    private Node parent;
    // множество нодов-потомков
    private Set<Node> children;
    // уровень вложенности нода в иерархии, используется для удобства вывода
    private int level=1;

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

    public List<Node> getChildrenList(){
            return (new ArrayList(children));
    }

    public SortedSet<Node> getAllNodes() {
        SortedSet<Node> set = new TreeSet<Node>();
        for (Node node : children) {
            set.addAll(node.getAllNodes());
        }
        return set;
    }

   public int getNodesCount() {
        int result = children.size();
        for (Node node : children) {
            result += node.getNodesCount();
        }
        return result;
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

    public TreeSet<Node> getChildren() {
          NodeRevertComparator comp= new NodeRevertComparator();
          TreeSet<Node> nodeSet=new TreeSet<Node>(comp);
          nodeSet.addAll(this.children);
          return nodeSet;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
}
