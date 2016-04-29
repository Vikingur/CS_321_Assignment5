/*
   This is the check-out business logic object. It ensures that a valid credit card is on file, 
   and that enough rooms are available, before checking a customer into their reservation.
*/
import java.util.*;

public class CheckOut {
   public static String checkOut(String[] instructions){
      Customer customer = Framework.getCustomerByName(instructions[1]);
      if(customer != null){
         Reservation reservation = Framework.getReservationByCID(customer.getCustomerID());
      
         if(reservation != null && reservation.getStatus() == Framework.STATUS_CHECKED_IN){
            int daysReserved = reservation.getEndDate() - reservation.getStartDate();
            double nightlyCharge = (reservation.getRoomType() > 1) ? Framework.DOUBLE_RATE : Framework.SINGLE_RATE;
         
            double payment = daysReserved * nightlyCharge;
         
            if(BankSystem.chargeCustomer(customer.getCustomerID(), payment)){
               Room.vacateRoom(reservation.getRoomType(), reservation.getRoomNumber());
               reservation.setStatus(Framework.STATUS_CHECKED_OUT);
               Framework.modifyReservation(reservation.getReservationID(), reservation);
               return checkOutReceipt(reservation.getReservationID());
            }
         }
      }
      return "Check Out could not be completed. Please re-enter information and try again!\n";
   }
   
   public static String checkOutReceipt(int reservationID){
      Reservation reservation = Framework.getReservationByID(reservationID);
      if(reservation != null){
         String report = "Check Out completed. Thank you for choosing us!\n";
         report += "Butts Motel\n";
         report += "Nights Reserved: "+(reservation.getEndDate() - reservation.getStartDate()) + "\n";
         double nightlyCharge = (reservation.getRoomType() > 1) ? Framework.DOUBLE_RATE : Framework.SINGLE_RATE;    
         double payment = (reservation.getEndDate() - reservation.getStartDate()) * nightlyCharge;
         report += "Total Cost: "+ payment + "\n";
         return report;
      }
      return null;
   }
}
