package org.sgu.oecde.discussion;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.discussion.dao.INodeDao;
import org.sgu.oecde.discussion.dao.IRootDao;
import org.sgu.oecde.discussion.service.DiscussionService;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations={"../spring/deBeans.xml"})
public class AppTest extends BasicTest{

//   @Ignore
    @Test
    public void removeComment() {
        DiscussionService s = getBean("discussionService");
      //  setDao("nodeDao");
        Node node = new Node();
        node.setId(1122L);
        s.removeNode(node);

    }

    @Ignore
    @Test
    public void addRoot() {
        Long idParent = 0L;
        Long idObject = 1182L;
        String typeObject = "news";
        setDao("adminDao");
        Admin author = this.<Admin>getItem(1L);

        Root root = new Root();
        root.setObjectId(idObject);
        Set<Node>nodes = new TreeSet<Node>();
     //   nodes.add(node);
      //  root.setChildren(nodes);
        root.setUser(author);
        root.setTime(DateConverter.convert(System.currentTimeMillis()));
        root.setObjectType(ForumTypes.parse(typeObject));
        setDao("rootDao");
        this.<IRootDao>getDao().save(root);
    }


   @Ignore
    @Test
    public void addComment(){
          DiscussionService s = getBean("discussionService");
          setDao("adminDao");
          Admin author = this.<Admin>getItem(1L);
          s.addNode(null, 1186L, ForumTypes.NEWS, 1084L, "tyyyyyyyyyyyyyyyyy", author);
    }

    @Ignore
    @Test
    public void getNode(){
        setDao("nodeDao");
        Node node = getItem(6L);
        System.out.println(node.getChildren());
    }

    @Ignore
    @Test
    public void getRoot(){
         DiscussionService s = getBean("discussionService");
        System.out.println(""+ s.getCount(1186L, ForumTypes.NEWS));
        setDao("rootDao");
        List<Node> nodes=s.getNodesByPage(1186L, ForumTypes.NEWS, 10, 1);
        System.out.println(" "+nodes);
    }

    private void recursive(Node n,String s){
        s+="   ";
        for(Node n2:n.getChildren()){
            System.out.println(n.getId()+"   "+s+n2+"  "+n2.getTime());
            recursive(n2,s);
        }
    }

    @Ignore
    @Test
    public void getRoot2(){
        setDao("rootDao");
        Root r = this.<Root>getItem(5L);
        System.out.println(r.getChildren());
    }
}
