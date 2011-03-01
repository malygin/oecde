
package org.sgu.oecde.discussion.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Resource;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Node;
import org.sgu.oecde.discussion.Root;
import org.sgu.oecde.discussion.dao.INodeDao;
import org.sgu.oecde.discussion.dao.IRootDao;
import org.springframework.stereotype.Service;

/**
 * сервис для работы с ветками обсуждений
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 30.08.2010 * 
 */
@Service
public class DiscussionService {

    @Resource (name="rootDao")
    IRootDao rootDao;

    @Resource (name="nodeDao")
    INodeDao nodeDao;

    public DiscussionService() {
    }

    /**
     * Количество нодов прицепленных к руту
     * @param id обджекта рута
     * @param type рута
     * @return кол-во нодов
     */
    public int getCount(Long id, ForumTypes type){
        return rootDao.getNodesCount(id, type);
    }

    /**
     * удаление нода
     * @param node
     */
    public void removeNode(Node node){
      nodeDao.delete(node);
    }

    /**
     * получение списка нодов по странице
     * @param id
     * @param type
     * @param messageOnPage
     * @param numPage
     * @return
     */
    public List<Node> getNodesByPage(Long id, ForumTypes type, int nodesOnPage, int numPage){
        return  nodeDao.getByPage(id, type, nodesOnPage, numPage);
    }

    /**
     * добавление нода
     * @param idNode
     * @param idObject
     * @param type
     * @param idParent
     * @param message
     * @param user
     */
    public Node addNode(Long idNode, Long idObject, ForumTypes type, Long idParent, String message, AbstractUser user){
        Node node= new Node();
        if(idNode!=null) node.setId(idNode);
        node.setMessage(message);
        node.setUser((AbstractPerson) user);
        node.setOpen(true);
        node.setTime(DateConverter.convert(System.currentTimeMillis()));
        Root root = new Root(idObject,type);
        List<Root>l = rootDao.getByExample(root);
        if (l==null||l.isEmpty()||l.get(0)==null) {
            //Создается новый корень обсуждений.
            root = new Root();
            root.setObjectId(idObject);
            Set<Node>nodes = new TreeSet<Node>();
            nodes.add(node);
            root.setChildren(nodes);
            root.setUser(user);
            root.setTime(DateConverter.convert(System.currentTimeMillis()));
            root.setObjectType(type);
            rootDao.save(root);
        }else{
            root = l.get(0);
        }        
        if(idParent!=0) node.setParent(new Node(idParent));
        else node.setRoot(root);
        if(node.getId()==null)  nodeDao.save(node);
        else  nodeDao.update(node);
        return node;
    }
}
