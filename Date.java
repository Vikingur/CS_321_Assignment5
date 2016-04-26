import java.util.*;
import java.util.ArrayList;

public class Date{
	//Class variables
	int day;
	int month;
	int numReservations;
	ArrayList<Integer> reservationIDs;

	//Class constructor
	public Date(int inMonth, int inDay) {
		reservationIDs = new ArrayList<Integer>();
		numReservations = 0;
		day = inDay;
		month = inMonth;
	}

	//Class setters
	public void setDay(int inDay){day = inDay;}
	public void setMonth(int inMonth){month = inMonth;}
	public void setNumReservations(int inNum){numReservations = inNum;}
	
	//Class getters
	public int getDay(){return day;}
	public int getMonth(){return month;}
	public int getYear(){return 2015;}
	public int getNumReservations(){return numReservations;}
	public ArrayList<Integer> getReservationIDs(){return reservationIDs;}
	
	//Reservation list methods
	public void addReservation(int reservationId) {
		reservationIDs.add(reservationId);
		numReservations++;
	}
   public void removeReservation(int reservationId){
      reservationIDs.remove(reservationIDs.getIndexOf(reservationId));
      numReservations--;
   }
}