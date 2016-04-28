import java.util.*;
import java.lang.*;
import java.text.*;

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
   int singles;
   int doubles;
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
      singles = 0;
      doubles = 0;
   
      while(iterate.hasNext()) {
         reserve = (Reservation)iterate.next();
         if(reserve.getRoomType() == 1)
            singles++;
         else
            doubles++;
      }
   
      this.revenue = (Framework.SINGLE_RATE * (double)singles) + (Framework.DOUBLE_RATE * (double)doubles);
   	
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

   public String toString()
   {
      DecimalFormat df = new DecimalFormat("0.00");
      DecimalFormat rf = new DecimalFormat("0.##");
      int month = currentDay.getMonth();
      int day = currentDay.getDay();
      int year = currentDay.getYear();
      return "Management Report for "+month+"/"+day+"/"+year+"\n"+
      "Number of Reservations: "+reservationNum+"\n"+
      "Single Rooms Reserved: "+singles+"\n"+
      "Double Rooms Reserved: "+doubles+"\n"+
      "Occupancy Rate: "+rf.format(occupancyRate)+"%"+"\n"+
      "Total Revenue: $"+df.format(revenue);
   }

   // testing manager report
   public static void main(String[] args)
   {
      try
      {
         Framework.init(args[1]);
      }
      catch (Exception e)
      {

      }
      Calendar.initCalendar();
      Calendar.buildCalendar();
      ManagerReport rep = new ManagerReport("1");
      System.out.println(rep);
   }
}
