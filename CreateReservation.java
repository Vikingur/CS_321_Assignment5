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
      reservation.setEndDate(Integer.parseInt(inReservationInfo[4]));
      reservation.setRoomType(Integer.parseInt(inReservationInfo[5]));
      reservation.setNumOccupants(Integer.parseInt(inReservationInfo[6]));
      reservation.setGuaranteed(Integer.parseInt(inReservationInfo[7]));
      //If reservation is guaranteed, read and store credit card info to customer
      if(Integer.parseInt(inReservationInfo[7]) == 1){
         customer.setCCType(inReservationInfo[8]);
         customer.setCCExpiration(inReservationInfo[9]);
         customer.setCCNumber(inReservationInfo[10]);
      }
      int CID = Framework.storeCustomer(customer);
      reservation.setCustomerID(CID);
      int RID = Framework.storeReservation(reservation);
      
      //Add reservation to the calendar on both the start and end dates
      (Calendar.getDate(Integer.parseInt(inReservationInfo[3]))).addReservation(RID);
      (Calendar.getDate(Integer.parseInt(inReservationInfo[4]))).addReservation(RID);
      
      return "Successfully created reservation.\n";
   }
}