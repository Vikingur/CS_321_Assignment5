import java.util.*;
import java.lang.*;

 /*
   This is the process reservations business logic object. It iterates across all reservations that have some relation 
   to the current date and flags them depending on certain conditions
  */
 
public class ProcessReservations{
	public static void checkReservations(Date today){
      ArrayList<Integer> reservationIDs = today.getReservationIDs();
		Reservation currentReservation;
   
      //Iterate across all reservations associated with the current date to flag as late, must-pay, cancel, etc.
		for(int i = 0; i < Calendar.getCurrentDate().getNumReservations(); i++){
			currentReservation = Framework.getReservationByID(reservationIDs.get(i));
			//For reservations who have not been checked-in, checked-out, or not flagged as must-pay
			if (currentReservation.getStatus() == 1){
				//For reservations whose check-in dates have passed
				if(Calendar.getCurrentDate().getDay() >= currentReservation.getStartDate()){
					//Flag guaranteed reservations as 'must-pay' (status code = 5)
					if(currentReservation.getGuaranteed() == 1){currentReservation.setStatus(5);}
					//Flag not-guaranteed reservations as 'no-show' (status code = 4)
					else{currentReservation.setStatus(4);}
				}
			}
         //For reservations who have been checked-in
         else if(currentReservation.getStatus() == 2){
            //For reservations who have not checked-out by specified end-date, increase end-date by one and change info in date's reservations
            if(currentReservation.getEndDate() < Calendar.getCurrentDate().getDay()){
               currentReservation.setEndDate(Calendar.getCurrentDate().getDay() + 1);
               Calendar.getCurrentDate().removeReservation(currentReservation.getReservationID());
               i--;
            }
         }
		}
	}
}