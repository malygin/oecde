
package org.sgu.oecde.web.jsfbeans.discussion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SwitchedUserCheker;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Node;
import org.sgu.oecde.discussion.Root;
import org.sgu.oecde.discussion.service.DiscussionService;
import org.sgu.oecde.discussion.util.NodeRevertComparator;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
import org.sgu.oecde.news.NewsItem;
import org.sgu.oecde.news.dao.INewsDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

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
    @ManagedProperty(value="#{newsDao}")
    private INewsDao newsDao;
    @ManagedProperty(value="#{journalService}")
    private JournalService journalService;

    private AbstractUser  currentUser;
    List<Node> nodes;

    private String objectId;
    private String objectType;
    private String nodeText;
    private String nodeTextReply;
    private String nodeId;
    //private String discussionId;
    private Node currentNode;

    private boolean renderReply=false;
    private boolean renderEdit=false;

    private Root currentRoot;
    private ForumTypes objectTypeEnum=ForumTypes.STUDENT_ORG;


    //Нодов на странице
    private int nodesOnPage=20;
    private int notAnsweredNodes  = -1;
    private int currentPage=1;
    private int numOfNodes=-1;

    public DiscussionBean() {
        currentUser = SecurityContextHandler.getUser();

    }
       
    /**
     * получение списка нодов по страницам
     * @return лист нодов для вывода
     */
    public List<Node> getNodesByPage(){
       if(objectId==null) return new ArrayList<Node>();
       if((nodes==null)){
           List<Node> nodesTemp=discussionService.getNodesByPage(new Long(objectId), objectTypeEnum, nodesOnPage, currentPage);
           currentRoot = new Root(new Long(objectId), objectTypeEnum);
           currentRoot.setId(new Long(objectId));
           Node nodeRoot=new Node();
           NodeRevertComparator comp= new NodeRevertComparator();
           //создали корень дерева для обработки листа
           TreeSet<Node> nodeSet=new TreeSet<Node>(comp);
           nodeSet.addAll(nodesTemp);
           nodeRoot.setChildren(nodeSet);
           //составили дерево для вывода в виде листа
           nodes=walk(new ArrayList<Node>(), nodeRoot,null, 1);
           //удалили корень
           nodes.remove(0);
        }
        return nodes;
    }

    public int getNotAnsweredNodes() {
        if (this.notAnsweredNodes== -1){

            List<Node> nodesTemp=discussionService.getNodesByPage(new Long(1), ForumTypes.STUDENT_FAQ, nodesOnPage, currentPage);
            this.notAnsweredNodes =nodesTemp.size();
            for(Node kid : nodesTemp) {
                  if (kid.getChildren().size() >0){
                      this.notAnsweredNodes-=1;
                  }
            }
        }
        return notAnsweredNodes;
    }

    public void setNotAnsweredNodes(int notAnsweredNodes) {
        this.notAnsweredNodes = notAnsweredNodes;
    }

    /**
     * служебный метод для рекурсии по дереву нодов
     */
    private List<Node> walk(List<Node> list, Node node, Node parent, int level) {
        node.setLevel(level);
        node.setParent(parent);
        list.add(node);
        for(Node kid : node.getChildren()) {
              walk(list, kid, node, level+1);
        }
        return list;
   }

    /**
     * получение количества нодов
     * @return
     */
     public int getNumOfNodes(String oId, String oTypeEnum ) {
         this.setObjectId(oId);
         //this.setObjectType(oTypeEnum);
        if((numOfNodes==-1)&&(objectId!=null))numOfNodes=discussionService.getCount(new Long(objectId), objectTypeEnum);
        return numOfNodes;
    }

    /**
     * сохранение нода
     * @throws IOException
     */
     public void saveNodes() throws IOException{
          Node node;
          AbstractUser userForSave=currentUser;
          List<GrantedAuthority> authority = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
          if(SwitchedUserCheker.check(authority)){
                Admin a =(Admin)(((SwitchUserGrantedAuthority)authority.get(1)).getSource().getPrincipal());                
                userForSave=(AbstractUser)a;
             }
          node = discussionService.addNode(null, new Long(objectId), objectTypeEnum, 0L, nodeText , userForSave);       
       
          if(objectTypeEnum==ForumTypes.NEWS || objectTypeEnum==ForumTypes.BLOGS){
                 NewsItem news=newsDao.getById(new Long(objectId));
                 news.setCommentNumber(news.getCommentNumber()+1);
                 newsDao.update(news);
                if (objectTypeEnum!=ForumTypes.BLOGS) journalService.save(EventType.POST_ADD, userForSave, news, node);
          }else   journalService.save(EventType.POST_ADD, userForSave, node);
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
             String[] s=request.getRequestURI().split("/");
            String url=s[s.length-1];      
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"?id="+objectId+"&type="+objectType);
     }
    /**
     * редактирование нода
     * @throws IOException
     */
     public void editNodes() throws IOException{
         Long parentId=0L;
         if (currentNode.getParent() != null) parentId= currentNode.getParent().getId();
         discussionService.addNode(currentNode.getId(), new Long(objectId), objectTypeEnum,  parentId, nodeText, currentUser);
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String[] s=request.getRequestURI().split("/");
            String url=s[s.length-1];
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"?id="+objectId+"&type="+objectType);
     }
 /**
     * редактирование нода
     * @throws IOException
     */
     public void cancelNodes() throws IOException{
         this.renderEdit=false;
         this.nodeText="";
     }
     /**
      * сохранение ответа
      * @throws IOException
      */
     public void saveReply() throws IOException{          
            AbstractUser userForSave=currentUser;
            List<GrantedAuthority> authority = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if(SwitchedUserCheker.check(authority)){
                Admin a =(Admin)(((SwitchUserGrantedAuthority)authority.get(1)).getSource().getPrincipal());                
                userForSave=(AbstractUser)a;
            }
           Node node=discussionService.addNode(null, new Long(objectId), objectTypeEnum, new Long(nodeId), nodeTextReply , userForSave);
           node.setParent(currentNode); 
           node.setRoot(currentRoot);           
           if(objectTypeEnum==ForumTypes.NEWS || objectTypeEnum==ForumTypes.BLOGS){
                  NewsItem news=newsDao.getById(new Long(objectId));
                  news.setCommentNumber(news.getCommentNumber()+1);
                  newsDao.update(news);
                  if (objectTypeEnum!=ForumTypes.BLOGS) journalService.save(EventType.POST_ANSWER, userForSave,news, node, Integer.toString(currentPage), node.getUser().getFio());
            }else journalService.save(EventType.POST_ANSWER, userForSave,node, Integer.toString(currentPage), node.getUser().getFio() );

             HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
             String[] s=request.getRequestURI().split("/");
             String url=s[s.length-1];
             FacesContext.getCurrentInstance().getExternalContext().redirect(url+"?id="+objectId+"&page="+currentPage+"&type="+objectType+"#"+nodeId);
     }

     /**
      * удаление нода
      * @throws IOException
      */
     public void deleteNode() throws IOException{
         Node node = new Node();
         node.setId(Long.parseLong(nodeId));
         discussionService.removeNode(node);
           if(objectTypeEnum==ForumTypes.NEWS){
                 NewsItem news=newsDao.getById(new Long(objectId));
                 news.setCommentNumber(news.getCommentNumber()-1);
                 newsDao.update(news);
             }
             HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
             String[] s=request.getRequestURI().split("/");
             String url=s[s.length-1];
             FacesContext.getCurrentInstance().getExternalContext().redirect(url+"?id="+objectId+"&page="+currentPage+"&type="+objectType);
     }

     /**
      * показать форму ответа
      */
     public void showReply(){
         this.renderReply=true;
     }

     /** 
      * показать форму редактирования
      */
     public void showEdit(){
         this.nodeText=currentNode.getMessage();
         this.renderEdit=true;
     }

    public String getForumTitle(){
        return objectTypeEnum.getTitle();
    }

    public DiscussionService getDiscussionService() {
        return discussionService;
    }

    public void setDiscussionService(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

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
        this.objectType=objectType;
        objectTypeEnum=ForumTypes.parse(objectType);
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

    public boolean isRenderEdit() {
        return renderEdit;
    }

    public void setRenderEdit(boolean renderEdit) {
        this.renderEdit = renderEdit;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public INewsDao getNewsDao() {
        return newsDao;
    }

    public void setNewsDao(INewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }


}
