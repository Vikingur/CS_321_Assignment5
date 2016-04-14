import java.util.*;
import java.util.ArrayList;

public class Calendar {
	//Class variables
	ArrayList<Date> dates;
	Date currentDate;

	//Class constructor
	public Calendar() {
		dates = new ArrayList<Date>();
		buildCalendar();
		currentDate = dates.get(0);
	}

	//Constructor helper method (to populate with dates)
	public void buildCalendar() {
		for(int i=1; i<=Framework.NUM_DAYS; i++) {
			dates.add(new Date(1,i));
		}
	}

	//Class setters
	public void setCurrentDate(Date inDate){
		currentDate = inDate;
	}
	
	//Class getters
	public Date getCurrentDate(){
		return currentDate;
	}
	public ArrayList<Date> getDates(){
		return dates;
	}
	public Date getDate(int day){
		return dates.get(day-1);
	}	
}