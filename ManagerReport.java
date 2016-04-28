import java.util.*;
import java.lang.*;

/* FINAL REPORT SAYS:
	Report gets info for the CURRENT DAY
	and returns: number of reservations,
	number of reserved/unreserved rooms,
	occupancy rate, and revenue generated
	for the day.
*/

//Called with input of Calendar.currentDay

public class ManagerReport{

   Date currentDay;
   int reservationNum = 0;
   int reserved = 0;
   int unreserved = 0;
   double occupancyRate = 0;
   double revenue = 0;
   ArrayList<Integer> reservations;

   public ManagerReport(String instruction){
      this.currentDay = Calendar.getDate((Integer.parseInt(instruction)));
      buildReport();
   }
   
   //Figures out revenue by getting the number of single/double rooms, and
   //multiplying them by the rate for each. Calls getOccupancy at the end
   //with the already calculated number of singles/double rooms.
   private void getRevenue() {
      this.reservations = currentDay.getReservationIDs();
      Iterator iterate = reservations.iterator();
      Reservation reserve;
      double singles = 0;
      double doubles = 0;
   
      while(iterate.hasNext()) {
         reserve = (Reservation)iterate.next();
         if(reserve.getRoomType() == 1)
            singles++;
         else
            doubles++;
      }
   
      this.revenue = (Framework.SINGLE_RATE * singles) + (Framework.DOUBLE_RATE * doubles);
   	
      getOccupancy(singles, doubles);
   }
   
   //Given the number of occupied singles and doubles, calculates
   //the rate of Occupancy at the hotel.
   private double getOccupancy(double singles, double doubles) {
      this.occupancyRate = ((singles + doubles) / (Framework.NUM_SINGLE_ROOMS + Framework.NUM_DOUBLE_ROOMS)) * 100;
      return this.occupancyRate;
   }
   
   //Populates the values of the class variables. Calls getRevenue.
   private void buildReport() {
      this.reservationNum = currentDay.getNumReservations();
      this.unreserved = (Framework.NUM_SINGLE_ROOMS) + (Framework.NUM_DOUBLE_ROOMS) - this.reservationNum;
      this.reserved = this.reservationNum;
      getRevenue();
   }
}
