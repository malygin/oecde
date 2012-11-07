package org.sgu.oecde.saschedule;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class Converter implements javax.faces.convert.Converter{

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		int index = value.indexOf(':');
		
		return new ToolBarItem();
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		ToolBarItem optionItem = (ToolBarItem) value;
		return optionItem.getLabel() + ":" + optionItem.getIcon();
	}

}