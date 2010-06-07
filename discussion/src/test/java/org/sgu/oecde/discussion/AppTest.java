package org.sgu.oecde.discussion;

import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.discussion.dao.INodeDao;
import org.sgu.oecde.discussion.dao.IRootDao;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../applicationContext.xml","../spring/discussionBeans.xml"})
public class AppTest extends BasicTest{

    @Ignore
    @Test
    public void removeComment() {
        setDao("nodeDao");
        this.<INodeDao>getDao().delete(new Node(1));
    }

    @Ignore
    @Test
    public void addComment(){
        Integer nodeId = 8;
        Integer idParent = 18;
        int idObject = 5;
        String typeObject = "stfaq";
        String message = "ответ номер 3 ";
        setDao("nodeDao");
        Node node = getItem(nodeId);
        if(node == null)
            node = new Node();
        node.setMessage(message);
        setDao("adminDao");
        Admin author = this.<Admin>getItem(1);
        node.setUser(author);
        node.setOpen(true);
        node.setTime(DateConverter.convert(System.currentTimeMillis()));
        Root root = new Root(idObject,ForumTypes.parse(typeObject));
        setDao("rootDao");
        List<Root>l = getByExample(root);
        if (l==null||l.isEmpty()||l.get(0)==null) {
            //Создается новый корень обсуждений.
            root = new Root();
            root.setObjectId(idObject);
            root.addChild(node);
            root.setUser(author);
            root.setTime(DateConverter.convert(System.currentTimeMillis()));
            root.setObjectType(ForumTypes.parse(typeObject));
            setDao("rootDao");
            this.<IRootDao>getDao().save(root);
        }else{
            root = l.get(0);
        }
        if(idParent!=0)
            node.setParent(new Node(idParent));
        else
            node.setRoot(root);
        setDao("nodeDao");
        if(node.getId()==0)
            this.<INodeDao>getDao().save(node);
        else
            this.<INodeDao>getDao().update(node);
    }

    @Ignore
    @Test
    public void getNode(){
        setDao("nodeDao");
        Node node = getItem(6);
        System.out.println(node.getChildren());
    }

    @Ignore
    @Test
    public void getRoot(){
        DiscussionDaoProxy dp = this.<DiscussionDaoProxy>getBean("discussionDaoProxy");
        dp.setIdObject(5);
        dp.setTypeObject("stfaq");
        dp.setCurrentPage(1);
        Root r = dp.getRoot();
        Set<Node> nodes = dp.getPage();
        System.out.println("--------");
        String s = "";
        for(Node n:nodes){
            System.out.println(s+n+"  "+n.getTime());
            recursive(n,s);
        }
    }

    private void recursive(Node n,String s){
        s+="   ";
        for(Node n2:n.getChildren()){
            System.out.println(s+n2+"  "+n2.getTime());
            recursive(n2,s);
        }
    }

    @Ignore
    @Test
    public void getRoot2(){
        setDao("rootDao");
        Root r = this.<Root>getItem(5);
        System.out.println(r.getChildren());
    }
}
