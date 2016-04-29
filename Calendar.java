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
   
   //Calendar based methods
   //This method iterates from the start of the calendar to a specified end date checking if there are available rooms of the inputted type
   public static boolean checkRoomAvailability(int inRoomType, int endDate){
      Date date;
      ArrayList<Integer> reservationIDs;
      Reservation reservation;
      int occupiedRooms = 0;
      for(int a = 1; a <= endDate; a++){
         date = getDate(a);
         //Only do checks if date actually has reservations tied to it
         if(date.getNumReservations() > 0){
            reservationIDs = date.getReservationIDs();
            //Iterate across the reservations tied to the date
            for(int b = 0; b < date.getNumReservations(); b++){
               reservation = Framework.getReservationByID(reservationIDs.get(b));
               //If a certain reservation is tied to the specific room type
               if(reservation.getRoomType() == inRoomType){
                  //Increment # of occupied rooms if date is start date
                  if(reservation.getStartDate() == date.getDay()){occupiedRooms++;}
                  //Decrease # if date is end date
                  else if(reservation.getEndDate() == date.getDay()){occupiedRooms--;}
               }
            }
         }
      }
      //Check to see if occupied rooms counter is equal to amount of rooms in system.
      if(inRoomType == 1 && occupiedRooms >= Framework.NUM_SINGLE_ROOMS){return false;}
      else if (inRoomType == 2 && occupiedRooms >= Framework.NUM_DOUBLE_ROOMS){return false;}
      
      return true;
   }
}