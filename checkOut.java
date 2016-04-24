import java.util.*;

public class CheckOut {

   Reservation reservation;
   boolean checkedOut = false;
   boolean reservationComplete = false;
   boolean paid = false;
   double payment;
   int customer;

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
}