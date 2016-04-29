/*
   This is the system coordinator. It accepts input from UserIO, determines which action to take,
   and initates the appropriate process with the correct input parameters.
*/

import java.util.*;
import java.lang.*;


public class ReservationSystem{
   //Class variables
   private String instructions[];
   private String output;
   
   //Static 'global' variables
   public static ReservationSystem systemCoordinator;
	
   //Class constructors
   public ReservationSystem(){}
   public static ReservationSystem generateSystem(){
      if(systemCoordinator == null){systemCoordinator = new ReservationSystem();}
      Calendar.initCalendar();
      Room room = new Room();
      Room.populateRooms();
      BankSystem bankSystem = new BankSystem();
      return systemCoordinator;
   }

   //Reads instruction relayed by userIO and initiates appropriate process
   public void processInstructions(String[] inInstructions)
   {
      instructions = inInstructions;
      output = null;
      //If first element in input array is 1: Create reservation
      if(instructions[0].equals("1")){
         output = CreateReservation.makeReservation(instructions);
      }
      //If first element in input array is 2: Check-in
      else if(instructions[0].equals("2")){
         output = CheckIn.checkIn(instructions);
      }
      //If first element in input array is 3: Check-out
      else if(instructions[0].equals("3")){
         output = CheckOut.checkOut(instructions);
      }
      //If first element in input array is 4: Print management report
      else if(instructions[0].equals("4")){
         ManagerReport m = new ManagerReport();
         output = m.toString();
      }
      //If first element in input array is 5: Day change signal
      else if(instructions[0].equals("5")){
         Calendar.dayChange();
      }
      //If first element in input array is 6: 6pm signal
      else if(instructions[0].equals("6")){
         ProcessReservations.checkReservations(Calendar.getCurrentDate());
      }
   	
   	//Sends any necessary results or requests back to UserIO for processing.
   	//Accesses the static UserIO instance.
      if(output != null){
         System.out.println(output);
         Logger.writeln(output);
      }
   }
}