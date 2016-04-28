/*
   This is the system coordinator. It accepts input from UserIO, determines which action to take,
   and initates the appropriate process with the correct input parameters.
*/

import java.util.*;


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
		return systemCoordinator;
	}

   //Reads instruction relayed by userIO and initiates appropriate process
	public void processInstructions(String[] inInstructions){
		instructions = inInstructions;

      //If first element in input array is 1: Create reservation
		if(instructions[0] == "@1"){
          output = Reservation.makeReservation(instructions) + "Customer ID: "+ Framework.getCustomerByName(instructions[1]).getCustomerID();
 		}
      //If first element in input array is 2: Check-in
      else if(instructions[0] == "@2"){
         output = CheckIn.checkIn(instructions);
      }
      //If first element in input array is 3: Check-out
      else if(instructions[0] == "@3"){
         output = CheckOut.checkOut(instructions);
      }
      //If first element in input array is 4: Print management report
      else if(instructions[0] == "@4"){
         ManagerReport m = new ManagerReport(instructions[1]);
         Logger.writeln(""+m);
      }
      //If first element in input array is 5: Day change signal
      else if(instructions[0] == "@5"){
         Calendar.dayChange();
      }
      //If first element in input array is 6: 6pm signal
      else if(instructions[0] == "@6"){
         ProcessReservations.checkReservations(Calendar.getCurrentDate());
      }
		
		//Sends any necessary results or requests back to UserIO for processing.
		//Accesses the static UserIO instance.
		UserIO.IO_Object.returnInstructions(output);
   }
}