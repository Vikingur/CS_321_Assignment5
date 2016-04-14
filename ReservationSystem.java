/*

PROCESS INSTRUCTIONS

IF Instructions indicate that a new reservation is to be created THEN
	Create reservation with instructions.
ENDIF

IF Instructions indicate that a check-in is being performed THEN
	Run the check-in process with the instructions.
ENDIF

IF Instructions indicate that a check-out is being performed THEN
	Run the check-out process with the instructions.
ENDIF

IF Instructions indicate that a managerial report is requested THEN
	Request a Managerial Report
ENDIF

EXIT

*/

public class ReservationSystem{

	private String instructions[];
	private String output[];

	public static ReservationSystem systemObject;
	
//Sends information and expects a return from UserIO.
//Has the capability to send back output coded to ask for more information.
//Essentially, UserIO can recognize requests for elaboration from ReservationSystem.

	private ReservationSystem(){

	}

	public static ReservationSystem generateSystem(){
		if(systemObject == null){
			systemObject = new ReservationSystem();
		}
		return systemObject;		
	}

	//Gets instructions from userIO in a formatted String form.
	public void processInstructions(String[] instruct){
		//Based on the input at the beginning of instructions, do the correct function.
		instructions = instruct;

		if(instructions.length == 11 && instructions[0] == "1"){
	//		output = CreateReservation.createReservation(instructions);
		}

		if(instructions.length == 5 && instructions[0] == "2"){
      
         if(instructions[2] == null){
            CheckIn.checkIn(instructions[1]);
         }
			else CheckIn.checkIn(instructions[1], instructions[2], instructions[3], instructions[4]);
		}

		if(instructions.length == 2 && instructions[0] == "3"){
	//		output = CheckOut.checkOut(instructions);
		}

		if(instructions.length == 1 && instructions[0] == "4"){
	//			output = PrintManagementReport.printReport();
		}

		//Sends any necessary results or requests back to UserIO for processing.
		//Accesses the static UserIO instance.
		UserIO.IO_Object.returnInstructions(output);
	         //O_O//
   }
}