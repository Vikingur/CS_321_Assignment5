import java.util.*;
import java.io.*;
/*

PROCESS INSTRUCTION
IF Instruction has the wrong number of arguments for its type THEN
	Reject
ENDIF

IF Instruction is a response type THEN
	IF Instructions indicate new information is required THEN
		Prompt Framework for next instruction.
	ELSE
		Print Instructions and end.
	ENDIF
ENDIF

Send instructions to ReservationSystem and record output.
EXIT

*/

public class UserIO {
	//Mostly just calls the Framework functions to create the queue and get instructions.
	//This method will be called by main.
   private ArrayList<String[]> instructionQueue;
   public static UserIO IO_Object;

   public UserIO(){
      instructionQueue = new ArrayList<String[]>();
   }

   public static void main(String[] args){
   	
   	//Creates the static instances of every Business Logic Class that will be needed.
      ReservationSystem.generateSystem();
      UserIO.IO_Object = new UserIO();
      Logger.initLogger(args[0]);
   
   	//Starts up the system.
      try{
         Framework.init(args[0]);
      }
      catch (FileNotFoundException ex){
      
      }
      if(Framework.hasNextInstruction()){
         IO_Object.loadInstruction(Framework.nextInstruction());
         ReservationSystem.systemCoordinator.processInstructions(IO_Object.instructionQueue.get(0));
      }
      Logger.closeLogger();
   }

   public void loadInstruction(String[] instruction){
      instructionQueue.add(instruction);
   }

   public void returnInstructions(String instruction){
      System.out.println(instruction);
   	//If the message has no specific need, like re-entering instructions,
   	//simply print and call the next set of instructions.
      if(Framework.hasNextInstruction()){
         loadInstruction(Framework.nextInstruction());
         ReservationSystem.systemCoordinator.processInstructions(IO_Object.instructionQueue.get(0));
      }
   }
}