 /*
   This is the create reservation business logic object. It reads inputted customer and desired room information and creates
   an appropriate reservation that may or may not be guaranteed. It also adds the newly created reservation to the Calendar.
  */

public class CreateReservation
{
   public static String makeReservation(String[] inReservationInfo){
      Customer customer = new Customer();
      customer.setName(inReservationInfo[1]);
      customer.setAddress(inReservationInfo[2]);
      
      Reservation reservation = new Reservation();
      reservation.setStatus(1);
      
      boolean resMade = true;
      boolean guar = false;
      String reason = null;
      
      if(customer == null){
         resMade = false;
         reason = "Customer not found!";
      }
      
      reservation.setStartDate(Integer.parseInt(inReservationInfo[3]));
      if(resMade && (reservation.getStartDate() > 31 || reservation.getStartDate() < 1)){
         resMade = false;
         reason = "Invalid start date. Unable to create reservation for "+customer.getName()+".\n";
      }
      
      reservation.setEndDate(Integer.parseInt(inReservationInfo[4]));
      if(resMade && (reservation.getEndDate() > 31 || reservation.getEndDate() < 1)){
         resMade = false;
         reason = "Invalid end date. Unable to create reservation for "+customer.getName()+".\n";
      }
      
      if(resMade && (reservation.getEndDate() <= reservation.getStartDate())){
         resMade = false;
         reason = "Specified end date is before start date. Unable to create reservation for "+customer.getName()+".\n";
      }
      
      reservation.setRoomType(Integer.parseInt(inReservationInfo[5]));
      if(resMade && (reservation.getRoomType() > 2 || reservation.getRoomType() < 1)){
         resMade = false;
         reason = "Specified room type is not valid. Unable to create reservation for "+customer.getName()+".\n";
      }
      if(resMade && (Calendar.checkRoomAvailability(reservation.getRoomType(), reservation.getEndDate()) == false)){
         resMade = false;
         reason = "No available rooms of that type in this time frame. Unable to create reservation for "+customer.getName()+".\n";
      }
      reservation.setNumOccupants(Integer.parseInt(inReservationInfo[6]));
      if(resMade && (reservation.getNumOccupants() > 4 || reservation.getNumOccupants() < 1)){
         resMade = false;
         reason = "Invalid number of occupants. Unable to create reservation for "+customer.getName()+".\n";
      }
      reservation.setGuaranteed(Integer.parseInt(inReservationInfo[7]));
      if(resMade && (reservation.getGuaranteed() > 1 || reservation.getGuaranteed() < 0)){
         resMade = false;
         reason = "Invalid guarantee status. Unable to create reservation for "+customer.getName()+".\n";
      }
      
      //If reservation is guaranteed, read and store credit card info to customer
      if(Integer.parseInt(inReservationInfo[7]) == 1){
         customer.setCCType(inReservationInfo[8]);
         customer.setCCExpiration(inReservationInfo[9]);
         customer.setCCNumber(inReservationInfo[10]);
         if(BankSystem.validateCard(customer.getCCNumber(), customer.getCCType(), customer.getCCExpiration()) == false){
            reason = "Invalid credit card. Unable to create reservation for "+customer.getName()+".\n";
         }
      }
      int CID = Framework.storeCustomer(customer);
      customer.setCustomerID(CID);
      reservation.setCustomerID(CID);
      int RID = Framework.storeReservation(reservation);
      
      //Add reservation to the calendar on both the start and end dates
      if(resMade){
         (Calendar.getDate(Integer.parseInt(inReservationInfo[3]))).addReservation(RID);
         (Calendar.getDate(Integer.parseInt(inReservationInfo[4]))).addReservation(RID);
         reason = "Customer ID: " + CID + " & Reservation ID: " + RID + ".\n";
      }
      String output = "Make Reservation request for "+customer.getName()+ ":\n";
      output+= "Reservation: " + (resMade ? "Success\n" : "Failure\n");
      output+= "Guaranteed: "+(reservation.getGuaranteed() == 1 ? "True\n": "False\n");
      if(resMade){
      output+= "Check In: January "+reservation.getStartDate() +", 2015\n";
      output+= "Check Out: January "+reservation.getEndDate() +", 2015\n";
      }
      output+= reason;
      return output;
   }
}