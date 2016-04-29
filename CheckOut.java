/*
   This is the check-out business logic object. It ensures that a valid credit card is on file, 
   and that enough rooms are available, before checking a customer into their reservation.
*/
import java.util.*;

public class CheckOut {
   public static String checkOut(String[] instructions){
      Customer customer = Framework.getCustomerByName(instructions[1]);
      Reservation reservation = Framework.getReservationByCID(customer.getCustomerID());
      
      int daysReserved = reservation.getEndDate() - reservation.getStartDate();
      double nightlyCharge = (reservation.getRoomType() > 1) ? Framework.DOUBLE_RATE : Framework.SINGLE_RATE;
      
      double payment = daysReserved * nightlyCharge;
   
      if(BankSystem.chargeCustomer(customer.getCustomerID(), payment)){
         Room.vacateRoom(reservation.getRoomType(), reservation.getRoomNumber());
         reservation.setStatus(Framework.STATUS_CHECKED_OUT);
         Framework.modifyReservation(reservation.getReservationID(), reservation);
         return "Check Out completed. Thank you for choosing us!";
      }
      return "Check Out could not be completed. Please re-enter information and try again!";
   }
   
   public static String checkOutReceipt(int reservationID){
      return "";
   }
}
