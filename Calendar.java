import java.util.*;
import java.util.ArrayList;

public class Calendar {
	//Class variables
	private static ArrayList<Date> dates;
	private static Date currentDate;
   private static int currentDateInt;

	//Class constructor
	public Calendar() {
		dates = new ArrayList<Date>();
		buildCalendar();
      currentDateInt = 0;
		currentDate = dates.get(0);
	}

	//Constructor helper method (to populate with dates)
	public static void buildCalendar() {
		for(int i=1; i<=Framework.NUM_DAYS; i++) {
			dates.add(new Date(1,i));
		}
	}

	//Class setters
	public static void setCurrentDate(Date inDate){
		currentDate = inDate;
	}
   
   public static void dayChange(){
      currentDateInt++;
      setCurrentDate(dates.get(currentDateInt));
   }
	
	//Class getters
	public static Date getCurrentDate(){
		return currentDate;
	}
	public static ArrayList<Date> getDates(){
		return dates;
	}
	public static Date getDate(int day){
		return dates.get(day-1);
	}
	
   //Method that iterates across all reservations tied to a given day and changes their statuses appropriately
	public static class ProcessReservations{
		public static void checkReservations(){
			ArrayList<Integer> reservationIDs = currentDate.getReservationIDs();
			Reservation currentReservation;
			//Iterate across all reservations associated with the current date to flag as late, must-pay, cancel, etc.
			for(int i = 0; i < currentDate.getNumReservations(); i++){
				currentReservation = Framework.getReservationByID(reservationIDs.get(i));
				//For reservations who have not been checked-in, checked-out, or not flagged as must-pay
				if (currentReservation.getStatus() == 1){
					//For reservations whose check-in dates have passed
					if(currentDate.getDay() >= currentReservation.getStartDate()){
						//Flag guaranteed reservations as 'must-pay' (status code = 5)
						if(currentReservation.getGuaranteed() == 1)
							currentReservation.setStatus(5);
						//Flag not-guaranteed reservations as 'no-show' (status code = 4)
						else
							currentReservation.setStatus(4);
					}
				}
			}
		}
	}
}