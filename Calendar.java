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
	
   //Method that iterates across all reservations tied to a given day and changes their statuses appropriately
	public static class processReservations{
		public static void checkReservations(Calendar systemCalendar){
			Date currentDate = systemCalendar.getCurrentDate();
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
						if(currentReservation.getGuaranteed() == 1){
							currentReservation.setStatus(5);
                  }
						//Flag not-guaranteed reservations as 'no-show' (status code = 4)
						else
							currentReservation.setStatus(4);
					}
				}
			}
		}
	}
}