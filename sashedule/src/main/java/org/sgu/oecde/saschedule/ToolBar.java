package org.sgu.oecde.saschedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="toolBar")
@SessionScoped
public class ToolBar {
	private List<ToolBarItem> items = new ArrayList<ToolBarItem>();
	private List<ToolBarItem> freeItems = new ArrayList<ToolBarItem>();
	public ToolBar() {
		ToolBarItem item = new ToolBarItem();
		item.setIcon("create_folder");
		item.setLabel("Create Folder");
		items.add(item);
		item = new ToolBarItem();
		item.setIcon("create_doc");
		item.setLabel("Create Doc");
		items.add(item);
		item = new ToolBarItem();
		item.setIcon("find");
		item.setLabel("Find");
		items.add(item);
		item = new ToolBarItem();
		item.setIcon("open");
		item.setLabel("Open");
		freeItems.add(item);
		item = new ToolBarItem();
		item.setIcon("save");
		item.setLabel("Save");
		freeItems.add(item);
		item = new ToolBarItem();
		item.setIcon("save_all");
		item.setLabel("Save All");
		freeItems.add(item);
		item = new ToolBarItem();
		item.setIcon("delete");
		item.setLabel("Delete");
		freeItems.add(item);
	}

    public List<ToolBarItem> getFreeItems() {
        return freeItems;
    }

    public void setFreeItems(List<ToolBarItem> freeItems) {
        this.freeItems = freeItems;
    }

    public List<ToolBarItem> getItems() {
        return items;
    }

    public void setItems(List<ToolBarItem> items) {
        this.items = items;
    }
    public void Action(){
        System.out.println("Action");
        System.out.println("11 "+freeItems);
    }
	

}
