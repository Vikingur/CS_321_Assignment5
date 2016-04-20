/*
   This is the check-in business logic object. It ensures that a valid credit card is on file, 
   and that enough rooms are available, before checking a customer into their reservation.
*/

import java.util.*;
import java.lang.*;

public class CheckIn{
	
   public static checkIn(String[] inCustomerInfo){
      Customer customer = Framework.getCustomerByName(inCustomerInfo[1]);
      
      //Check to see if credit card info is provided, and update info if necessary
      if(inCustomerInfo.length == 5){
         customer.setCCType(inCustomerInfo[2]);
         customer.setCCExpiration(inCustomerInfo[3]);
         customer.setCCNumber(inCustomerInfo[4]);
      }
      int CID = customer.getCustomerID();
      Framework.modifyCustomer(CID, customer);
      
      //Validate credit card
      if(customer.getCCNumber() != NULL){
          boolean cardValid = BankSystem.validateCard(customer.getCCNumber(), customer.getCCType(), customer.getCCExpiration());
          if(!cardValid)return false; //TO DO: Create some type of output that lets clerk know card is invalid...
      }else return false; //TO DO: Create some type of output that lets clerk know card is still needed...

      Reservation reservation = Framework.getReservationByCID(CID);
      int RID = reservation.getReservationID();
      
      int roomNum = Room.findRoom(reservation.getRoomType());
      if(roomNum == -1){
         return false; //TO DO: Create some type of output that lets clerk know no rooms are available...
      }
      reservation.setRoomNumber(roomNum);
      
      //Flag reservation as checked-in and modify it in Framework
      reservation.setStatus(2);
      Framework.modifyReservation(RID, reservation);
   }
}