/*
   This is the entity class representing individual Reservations in the system.
*/

public class Reservation{
   //Class variables
   private int reservationID;
   private int status;
   private int startDate;
   private int endDate;
   private int roomType;
   private int numOccupants;
   private boolean guaranteed;
   private int roomNumber = -1;  //-1 until reservation is assigned a room at check-in
   private int customerID;

   //Class constructors
   public Reservation(){
      customerID = -1;
      reservationID = -1;
      status = -1;
      startDate = -1;
      endDate = -1;
      roomType = -1;
      numOccupants = -1;
      guaranteed = false;
      roomNumber = -1;
   }
   public Reservation(int inStatus, int inStartDate, int inEndDate, int inRoomType, int inNumOccupants, int inGuaranteed, int inCustomerID){
      status = inStatus;
      startDate = inStartDate;
      endDate = inEndDate;
      roomType = inRoomType;
      numOccupants = inNumOccupants;
      if(inGuaranteed == 1) guaranteed = true; else guaranteed = false;
      customerID = inCustomerID;
   }

   //Class setters
   public void setReservationID(int inReservationID){reservationID = inReservationID;}
   public void setStatus(int inStatus){status = inStatus;}
   public void setStartDate(int inStartDate){startDate = inStartDate;}
   public void setEndDate(int inEndDate){endDate = inEndDate;}
   public void setRoomType(int inRoomType){roomType = inRoomType;}
   public void setNumOccupants(int inNumOccupants){numOccupants = inNumOccupants;}
   public void setGuaranteed(int inGuaranteed){if(inGuaranteed == 1)guaranteed = true; else guaranteed = false;}
   public void setRoomNumber(int inRoomNumber){roomNumber = inRoomNumber;}
   public void setCustomerID(int inCustomerID){customerID = inCustomerID;}

   //Class getters
   public int getReservationID(){return reservationID;}
   public int getStatus(){return status;}
   public int getStartDate(){return startDate;}
   public int getEndDate(){return endDate;}
   public int getRoomType(){return roomType;}
   public int getNumOccupants(){return numOccupants;}
   public int getGuaranteed(){if(guaranteed)return 1; else return 0;}
   public int getRoomNumber(){return roomNumber;}
   public int getCustomerID(){return customerID;}

<<<<<<< HEAD
}
=======
   //Static method that creates both a reservation and a customer and stores them in the Framework
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
         if(BankSystem.validateCard(inReservationInfo[8], inReservationInfo[9], inReservationInfo[10])) {
            customer.setCCType(inReservationInfo[8]);
            customer.setCCExpiration(inReservationInfo[9]);
            customer.setCCNumber(inReservationInfo[10]);
         }
         else {
            return "Given credit card not valid.";
          }

<<<<<<< HEAD
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
>>>>>>> origin/master
=======
   //Static method that creates both a reservation and a customer and stores them in the Framework
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
>>>>>>> parent of 60dfc7a... Separated CreateReservation from Reservation
