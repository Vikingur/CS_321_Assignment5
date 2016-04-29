/*
   This is the entity class representing individual Rooms in the system.
*/

import java.util.*;
import java.lang.*;

public class Room{
   //Class variables
   private static ArrayList<Room> singleRooms = new ArrayList<Room>(Framework.NUM_SINGLE_ROOMS);
   private static ArrayList<Room> doubleRooms = new ArrayList<Room>(Framework.NUM_DOUBLE_ROOMS);
   private int roomNumber;
	private int roomType; //1 = single, 2 = double
	private boolean occupied;

	//Class constructors
   public Room(){
      roomNumber = -1;
      roomType = -1;
      occupied = false;
   }
	public Room(int inRoomNumber, int inRoomType){
		roomNumber = inRoomNumber;
		roomType = inRoomType;
		occupied = false;
	}
   
   //Creates room lists for single and doubles. Single room#s are 1-#singleRooms and double room#s are (#singleRooms+1)-(#doubleRooms+#singleRooms)
   public static void populateRooms(){
   System.out.println(Framework.NUM_SINGLE_ROOMS);
   System.out.println(Framework.NUM_DOUBLE_ROOMS);
      for(int a = 1; a <= Framework.NUM_SINGLE_ROOMS; a++){singleRooms.add(new Room(a,1));}
      for(int b = 1; b <= Framework.NUM_DOUBLE_ROOMS; b++){doubleRooms.add(new Room(b+singleRooms.size(), 2));}
   }
   
	//Class setters
   public static ArrayList<Room> getSingleRooms(){return singleRooms;}
   public static ArrayList<Room> getDoubleRooms(){return doubleRooms;}
   public int getRoomNumber(){return roomNumber;}
   public int getRoomType(){return roomType;}
   public boolean getOccupied(){return occupied;}
   
   //Class getters
   public void setRoomNumber(int inRoomNumber){roomNumber = inRoomNumber;}
   public void setRoomType(int inRoomType){roomType = inRoomType;}
   public void setOccupied(boolean inOccupancy){occupied = inOccupancy;}
   
   //Searches through room lists to find an unoccupied room of requested room type
   public static int findRoom(int inRoomType){
      int number = -1;
      if(inRoomType == 1){
         for(int a = 1; a <= singleRooms.size(); a++){
            if(singleRooms.get(a-1).getOccupied() == false){
               number = singleRooms.get(a-1).getRoomNumber();
               singleRooms.get(a-1).setOccupied(true);
               return number;
            }
         }
      }
      else{
         for(int b = 1; b <= doubleRooms.size(); b++){
            if(doubleRooms.get(b-1).getOccupied() == false){
               number = doubleRooms.get(b-1).getRoomNumber();
               doubleRooms.get(b-1).setOccupied(true);
               return number;
            }
         }
      }
      return number;
   }
   //Vacates a particular room    
   public static boolean vacateRoom(int inRoomType, int inRoomNum){
      if(inRoomType == 1){
         singleRooms.get(inRoomNum-1).setOccupied(false);
         return true;
      }
      else{
         doubleRooms.get(inRoomNum-singleRooms.size()-1).setOccupied(false);
         return true;
      }
   }
}