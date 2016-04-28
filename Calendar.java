import java.util.*;
import java.util.ArrayList;

public class Calendar {
	//Class variables
	private static ArrayList<Date> dates;
	private static Date currentDate;
   	private static int currentDateInt;

	//Class constructor
	public static void initCalendar() {
		dates = new ArrayList<Date>();
		buildCalendar();
      	currentDateInt = 1;
		currentDate = dates.get(0);
	}

	//Constructor helper method (to populate with dates)
	public static void buildCalendar() {
		for(int i=1; i<=Framework.NUM_DAYS; i++) {
			dates.add(new Date(1,i));
		}
	}

	//Class setters
	public static void setCurrentDate(Date inDate){currentDate = inDate;}
   public static void dayChange(){currentDateInt++; setCurrentDate(dates.get(currentDateInt-1));}
	
	//Class getters
	public static Date getCurrentDate(){return currentDate;}
	public static ArrayList<Date> getDates(){return dates;}
	public static Date getDate(int day){return dates.get(day-1);}
}