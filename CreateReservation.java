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
      
      reservation.setStartDate(Integer.parseInt(inReservationInfo[3]));
      if(reservation.getStartDate() > 31 || reservation.getStartDate() < 1){
         return "Invalid start date. Unable to create reservation.\n";
      }
      
      reservation.setEndDate(Integer.parseInt(inReservationInfo[4]));
      if(reservation.getEndDate() > 31 || reservation.getEndDate() < 1){
         return "Invalid end date. Unable to create reservation.\n";
      }
      
      if(reservation.getEndDate() >= reservation.getStartDate()){
         return "Specified end date is before start date. Unable to create reservation.\n";
      }
      
      reservation.setRoomType(Integer.parseInt(inReservationInfo[5]));
      if(reservation.getRoomType() > 2 || reservation.getRoomType() < 1){
         return "Specified room type is not valid. Unable to create reservation.\n";
      }
      if(Calendar.checkRoomAvailability(reservation.getRoomType(), reservation.getEndDate()) == false){
         return "No available rooms of that type in this time frame. Unable to create reservation.\n";
      }
      reservation.setNumOccupants(Integer.parseInt(inReservationInfo[6]));
      if(reservation.getNumOccupants() > 4 || reservation.getNumOccupants() < 1){
         return "Invalid number of occupants. Unable to create reservation.\n";
      }
      reservation.setGuaranteed(Integer.parseInt(inReservationInfo[7]));
      if(reservation.getGuaranteed() > 1 || reservation.getGuaranteed() < 0){
         return "Invalid guarantee status. Unable to create reservation.\n";
      }
      
      //If reservation is guaranteed, read and store credit card info to customer
      if(Integer.parseInt(inReservationInfo[7]) == 1){
         customer.setCCType(inReservationInfo[8]);
         customer.setCCExpiration(inReservationInfo[9]);
         customer.setCCNumber(inReservationInfo[10]);
      }
      int CID = Framework.storeCustomer(customer);
      customer.setCustomerID(CID);
      reservation.setCustomerID(CID);
      int RID = Framework.storeReservation(reservation);
      
      //Add reservation to the calendar on both the start and end dates
      (Calendar.getDate(Integer.parseInt(inReservationInfo[3]))).addReservation(RID);
      (Calendar.getDate(Integer.parseInt(inReservationInfo[4]))).addReservation(RID);
      
      return "Successfully created reservation. Your Customer ID is: " + CID + ".\n";

   }
}