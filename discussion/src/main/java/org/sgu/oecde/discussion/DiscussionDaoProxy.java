package org.sgu.oecde.discussion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.sgu.oecde.discussion.dao.IRootDao;
import org.sgu.oecde.discussion.util.NodeRevertComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author BasakovVY
 */
@Scope("prototype")
@Service
public class DiscussionDaoProxy {

    @Autowired
    private IRootDao rootDao;

    private Root root;
    private Long idObject;
    private String typeObject;
    private int postPerPage = 10;
    private List<SortedSet<Node>> list = null;
    private int currentPage;
    private int maxPage;

    public void addNode() {

    }

    public SortedSet<Node> getPage() {
        if (root == null || root.getChildren() == null || root.getChildren().size() == 0) {
            return null;
        }
        if (currentPage < 1 || currentPage > maxPage) {
            throw new IllegalStateException("NET TAKOI STRANICU");
        }
        if (list == null) {
            throw new IllegalStateException("list ne inicializirovan");
        }
        return list.get(currentPage - 1);
    }

    private void makePages(Set<Node> nodes) {
        list = new ArrayList<SortedSet<Node>>();
        SortedSet<Node> set = new TreeSet<Node>(new NodeRevertComparator());
        list.add(set);
        for (Node child : nodes) {
            int count = set.size();
            for (Node node : set) {
                count += node.getNodesCount();
            }
            if (child.getNodesCount() + count <= postPerPage) {
                set.add(child);
            } else {
                set = new TreeSet<Node>(new NodeRevertComparator());
                list.add(set);
                set.add(child);
            }
        }
        maxPage = list.size();
    }

    public Root getRoot() {
        if (idObject == null) {
            throw new IllegalStateException("Not all of fields have been initialised");
        }
        return root != null ? root : new Root(-1L);
    }

    public void setIdObject(Long idObject) {
        if (this.idObject!=null) {
            throw new IllegalStateException("idObject has already been initialised");
        }
        this.idObject = idObject;
        if (StringUtils.hasLength(typeObject)) {
            root = getRoot(idObject, typeObject.trim());
            if (root != null && root.getChildren() != null && root.getChildren().size() > 0) {
                makePages(root.getChildren());
            }
        }
    }

    public void setTypeObject(String typeObject) {
        if (StringUtils.hasLength(this.typeObject)) {
            throw new IllegalStateException("typeObject has already been initialised");
        }
        this.typeObject = typeObject;
        if (idObject!=null) {
            root = getRoot(idObject, typeObject.trim());
            if (root != null && root.getChildren() != null && root.getChildren().size() > 0) {
                makePages(root.getChildren());
            }
        }
    }

    private Root getRoot(Long idObject, String typeObject){
        Root r = new Root(idObject,ForumTypes.parse(typeObject));
        List<Root>l = rootDao.getByExample(r);
        if(l == null||l.size()!=1)
            throw new IllegalStateException("more than one root");
        return l.get(0);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPostPerPage() {
        return postPerPage;
    }

    public void setPostPerPage(int postPerPage) {
        this.postPerPage = postPerPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setRootDao(IRootDao rootDao) {
        this.rootDao = rootDao;
    }
}
