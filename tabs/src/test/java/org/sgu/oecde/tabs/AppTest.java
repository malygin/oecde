package org.sgu.oecde.tabs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.tabs.dao.ITabsDao;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for simple App.
 */
public class AppTest extends BasicTest{

    @Ignore
    @Test
    public void save(){
        setDao("tabsDao");
        Tab t = new Tab();
        Page p = new Page();
        t.setName("12123qwedwe");
        t.setType(TabType.STUDENT_HELP);
        Set<Page>ps = new HashSet<Page>();
        ps.add(p);
        t.setPages(ps);
        Set<PageFile>pfs = new HashSet<PageFile>();
        PageFile f = new PageFile();
        f.setPage(p);
        f.setName("12123");
        f.setVisible(true);
        f.setImage(true);
        pfs.add(f);
        p.setTab(t);
        p.setFiles(pfs);
        p.setText("23423 234 23 4");
        p.setTitle("234234234");
        this.<IUpdateDao>getDao().update(t);
    }

    @Ignore
    @Test
    public void upd(){
        setDao("tabsDao");
        Tab t =getItem(17L);
        Page p = new Page();
        t.setName("555555555 5 5 5 55 ");
        t.setType(TabType.STUDENT_HELP);
        Set<Page>ps = t.getPages();
        ps.add(p);
        t.setPages(ps);
        Set<PageFile>pfs = new HashSet<PageFile>();
        PageFile f = new PageFile();
        f.setName("6 66 6 6 6 6 ");
        f.setVisible(true);
        f.setImage(true);
        f.setPage(p);
        pfs.add(f);
        p.setTab(t);
        p.setFiles(pfs);
        p.setText("vnvcncvbncvn");
        p.setTitle("cvncvbncvbncvn");
        this.<IUpdateDao>getDao().update(t);
    }

//    @Ignore
    @Test
    public void delete(){
        setDao("tabsDao");
        Tab t = getItem(20L);
        Page p = t.getPages().iterator().next();
        p.setTab(null);
        t.getPages().remove(p);
        this.<IUpdateDao>getDao().update(t);
    }

    @Ignore
    @Test
    public void get(){
        setDao("tabsDao");
        Tab example = new Tab(TabType.STUDENT_HELP);
        List l = getByExample(example);
        System.out.println(l);
    }
    
    @Ignore
    @Test
    public void delete2(){
        setDao("tabsDao");
        Tab t = getItem(16L);
        this.<ITabsDao>getDao().delete(t);
    }
}
