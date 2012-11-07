package org.sgu.oecde.saschedule;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import javax.faces.event.ValueChangeEvent;

import org.richfaces.model.CalendarDataModel;
import org.richfaces.model.CalendarDataModelItem;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.dao.ILessonDao;
import org.springframework.dao.DataAccessException;


/**
 *
 * @author Malygin
 */
@ManagedBean(name="calendarDataModel")
@SessionScoped
public class CalendarDataModelImpl implements CalendarDataModel,  Serializable  {

	/* (non-Javadoc)
	 * @see org.richfaces.component.CalendarDataModel#getData(java.util.Date[])
	 */
     private CalendarDataModelItem[] items;

     @ManagedProperty(value="#{lessonDao}")
     ILessonDao lessonDao;
    


	private String currentDescription;
	private String currentShortDescription;
	private Date currentDate;
	private boolean currentDisabled;


	/* (non-Javadoc)
	 * @see org.richfaces.model.CalendarDataModel#getData(java.util.Date[])
	 */
	public CalendarDataModelItem[] getData(Date[] dateArray) {

		if (dateArray == null) {
			return null;
		}
                  items = new CalendarDataModelItem[dateArray.length];
                try {
                    Date date=dateArray[0];
                    DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                    String today = formatter.format(date);
                   // System.out.println(" "+today.substring(0, 4)+"-"+today.substring(5, 7));
                    List<Lesson> lessons = lessonDao.getListByMonth(today.substring(0, 4), today.substring(5, 7));
                    for (int i = 0; i < dateArray.length; i++) {
                        items[i] = createDataModelItem(dateArray[i], lessons);
                }

                } catch (DataAccessException ex) {
                    Logger.getLogger(CalendarDataModelImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CalendarDataModelImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
		return items;
	}



	/**
	 * @param date
	 * @return CalendarDataModelItem for date
	 */
	protected CalendarDataModelItem createDataModelItem(Date date, List<Lesson> lessons) {
		CalendarDataModelItemImpl item = new CalendarDataModelItemImpl();
		Map data = new HashMap();
                Calendar c = Calendar.getInstance();
                
                String name_num_lesson;
                Lesson l = new Lesson();
                DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                String today = formatter.format(date);
                l.setLessonDate(today);
                Integer num_lesson =0;

                for(Lesson li:lessons){
                    if (li.getLessonDate().substring(0, 10).equals(today)){
                        num_lesson++;
                    }
                }
                if (num_lesson!=0){
                    name_num_lesson=num_lesson.toString();
                    data.put("shortDescription",name_num_lesson );
		    data.put("description","");
                }else{
                    data.put("shortDescription","" );
		    data.put("description"," ");
                }		
		c.setTime(date);
		item.setDay(c.get(Calendar.DAY_OF_MONTH));
		item.setEnabled(true);
		item.setStyleClass("rel-hol");
		item.setData(data);

              //  System.out.println("!!" +today);
             //   System.out.println("count "+num_lesson);
             
		return item;
	}

	/* (non-Javadoc)
	 * @see org.richfaces.model.CalendarDataModel#getToolTip(java.util.Date)
	 */
	public Object getToolTip(Date date) {

		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return items
	 */
	public CalendarDataModelItem[] getItems() {
		return items;
	}

	/**
	 * @param setter for items
	 */
	public void setItems(CalendarDataModelItem[] items) {
		this.items = items;
	}

	/**
	 * @param valueChangeEvent handling
	 */
	public void valueChanged(ValueChangeEvent event) {
		try {
			Thread.currentThread().sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setCurrentDate((Date)event.getNewValue());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentDate());
		setCurrentDescription((String)((HashMap)items[calendar.get(Calendar.DAY_OF_MONTH)-1].getData()).get("description"));
		setCurrentShortDescription((String)((HashMap)items[calendar.get(Calendar.DAY_OF_MONTH)-1].getData()).get("shortDescription"));
	}

	/**
	 * Storing changes action
	 */
	public void storeDayDetails() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentDate());
		((HashMap)items[calendar.get(Calendar.DAY_OF_MONTH)-1].getData()).put("shortDescription", getCurrentShortDescription());
		((HashMap)items[calendar.get(Calendar.DAY_OF_MONTH)-1].getData()).put("description", getCurrentDescription());
	}

	/**
	 * @return currentDescription
	 */
	public String getCurrentDescription() {
		return currentDescription;
	}

	/**
	 * @param currentDescription
	 */
	public void setCurrentDescription(String currentDescription) {
		this.currentDescription = currentDescription;
	}

	/**
	 * @return currentDisabled
	 */
	public boolean isCurrentDisabled() {
		return currentDisabled;
	}

	/**
	 * @param currentDisabled
	 */
	public void setCurrentDisabled(boolean currentDisabled) {
		this.currentDisabled = currentDisabled;
	}

	/**
	 * @return currentShortDescription
	 */
	public String getCurrentShortDescription() {
		return currentShortDescription;
	}

	/**
	 * @param currentShortDescription
	 */
	public void setCurrentShortDescription(String currentShortDescription) {
		this.currentShortDescription = currentShortDescription;
	}

	/**
	 * @return currentDate
	 */
	public Date getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate
	 */
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }
}