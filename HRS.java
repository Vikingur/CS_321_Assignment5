import java.util.*;
import java.io.*;

public class HRS{
   public static void main(String[] args){
   //Creates the static instances of every Business Logic Class that will be needed.
      ReservationSystem.generateSystem();
      Logger.initLogger(args[0]);
   
   	//Starts up the system.
      try{
         Framework.init(args[0]);
      }
      catch (FileNotFoundException ex){
      
      }
      while(Framework.hasNextInstruction()){
         ReservationSystem.systemCoordinator.processInstructions(Framework.nextInstruction());
      }
   }
}