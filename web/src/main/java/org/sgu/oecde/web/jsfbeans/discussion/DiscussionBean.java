
package org.sgu.oecde.web.jsfbeans.discussion;

import java.io.IOException;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Node;
import org.sgu.oecde.discussion.service.DiscussionService;
import org.sgu.oecde.discussion.util.NodeRevertComparator;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 02.09.2010
 * основной бин для работы с обсуждениями
 */

@ManagedBean(name="DiscussionBean")
@ViewScoped
public class DiscussionBean {
    @ManagedProperty(value="#{discussionService}")
    private DiscussionService discussionService;

    private AbstractUser  currentUser;
    List<Node> nodes;

    private String objectId;
    private String objectType;
    private String nodeText;
    private String nodeTextReply;
    private String nodeId;
    private boolean renderReply=false;

    private ForumTypes objectTypeEnum;


    //Нодов на странице
    private int nodesOnPage=3;
    private int currentPage=1;
    private int numOfNodes=-1;

    public DiscussionBean() {
         currentUser = SecurityContextHandler.getUser();
    }

    public List<Node> getNodesByPage(){
        if(nodes==null){
           //nodes= new ArrayList();
           List<Node> nodesTemp=discussionService.getNodesByPage(new Long(objectId), objectTypeEnum, nodesOnPage, currentPage);
           Node nodeRoot=new Node();
           NodeRevertComparator comp= new NodeRevertComparator();
           TreeSet<Node> nodeSet=new TreeSet<Node>(comp);
           nodeSet.addAll(nodesTemp);

          // Collections.sort(nodesTemp);
           nodeRoot.setChildren(nodeSet);         
           nodes=walk(new ArrayList<Node>(), nodeRoot, 1);
          nodes.remove(0);
        }
        return nodes;
    }

    private List<Node> walk(List<Node> list, Node node, int level) {
       // System.out.println("");
        node.setLevel(level);
            //    System.out.println("add "+node.getMessage() );
        list.add(node);
        for(Node kid : node.getChildren()) {
         //   System.out.println(" "+kid.getMessage()+" "+kid.getTime());
          walk(list, kid, level+1);
        }
        return list;
  }

     public int getNumOfNodes() {
        if(numOfNodes==-1)numOfNodes=discussionService.getCount(new Long(objectId), objectTypeEnum);
        return numOfNodes;
    }

     public String saveNodes(){
         discussionService.addNode(null, new Long(objectId), objectTypeEnum, 0L, nodeText , currentUser);       
         return "discussion_list";
     }
     public void saveReply() throws IOException{
         discussionService.addNode(null, new Long(objectId), objectTypeEnum, new Long(nodeId), nodeTextReply , currentUser);
             HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
             String url=request.getRequestURI().split("/")[3];
             FacesContext.getCurrentInstance().getExternalContext().redirect(url+"?page="+currentPage);
  
     }

     public void deleteNode() throws IOException{
         Node node = new Node();
         node.setId(Long.parseLong(nodeId));
         discussionService.removeNode(node);
             HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
             String url=request.getRequestURI().split("/")[3];
             FacesContext.getCurrentInstance().getExternalContext().redirect(url+"?page="+currentPage);
     }

     public void showReply(){
         this.renderReply=true;
     }


    public DiscussionService getDiscussionService() {
        return discussionService;
    }

    public void setDiscussionService(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }
//todo: он возвращет что не надо убить или перенаправить на лист по страницам
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNodesOnPage() {
        return nodesOnPage;
    }

    public void setNodesOnPage(int nodesOnPage) {
        this.nodesOnPage = nodesOnPage;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        objectTypeEnum=ForumTypes.valueOf("NEWS");
    }

    public String getNodeText() {
        return nodeText;
    }

    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeTextReply() {
        return nodeTextReply;
    }

    public void setNodeTextReply(String nodeTextReply) {
        this.nodeTextReply = nodeTextReply;
    }

    public AbstractUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AbstractUser currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isRenderReply() {
        return renderReply;
    }

    public void setRenderReply(boolean renderReply) {
        this.renderReply = renderReply;
    }


}
