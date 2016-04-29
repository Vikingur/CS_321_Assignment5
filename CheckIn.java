/*
   This is the check-in business logic object. It ensures that a valid credit card is on file,
   and that enough rooms are available, before checking a customer into their reservation.
*/

import java.util.*;
import java.lang.*;

public class CheckIn{

   public static String checkIn(String[] inCustomerInfo){
      Customer customer = Framework.getCustomerByName(inCustomerInfo[1]);
      if(customer == null){
         System.out.println("Missing Name: "+inCustomerInfo[1]);
         return "No such customer found!\n";
      }
      //Check to see if credit card info is provided, and update info if necessary
      if(inCustomerInfo.length == 5){
         customer.setCCType(inCustomerInfo[2]);
         customer.setCCExpiration(inCustomerInfo[3]);
         customer.setCCNumber(inCustomerInfo[4]);
      }
      int CID = customer.getCustomerID();
      Framework.modifyCustomer(CID, customer);

      //Validate credit card
      boolean cardValid;
      if(customer.getCCNumber() != null){
          cardValid = BankSystem.validateCard(customer.getCCNumber(), customer.getCCType(), customer.getCCExpiration());
          if(cardValid == false){return (customer.getName() + " was not successfully checked in. Invalid credit card.\n");}
      }else {return (customer.getName() + " was not successfully checked in. No credit card data was received.\n");}

      Reservation reservation = Framework.getReservationByCID(CID);
      int RID = reservation.getReservationID();

      int roomNum = Room.findRoom(reservation.getRoomType());
      if(roomNum <= 0){return (customer.getName() + " was not successfully checked in. No available rooms.\n");}
      reservation.setRoomNumber(roomNum);

      //Flag reservation as checked-in and modify it in Framework
      reservation.setStatus(2);
      Framework.modifyReservation(RID, reservation);

      int rate = (reservation.getRoomType() == 1) ? Framework.SINGLE_RATE : Framework.DOUBLE_RATE;

      return (customer.getName()+" successfully checked customer in.\n\nCheck In Statement:\n" +
         "Customer Name: "+customer.getName()+"\nNights reserved: "+(reservation.getStartDate()-reservation.getEndDate()) +
         "\nNightly Rate: "+rate+"\nCheck In: January "+reservation.getStartDate()+", 2015" +
         "\nCheck Out: January "+reservation.getEndDate()+", 2015\n");
   }
}