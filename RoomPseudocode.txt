public class Room{

	private static ArrayList<Room> roomList = new ArrayList<Room>(Framework.NUM_SINGLE_ROOMS + NUM_DOUBLE_ROOMS);
	private final int roomNum;
	private final boolean singleRoom; //If true, single room. If false, double.
	private bool occupied;

	//Constructor
	public Room(int num, boolean single){
		roomNum = num;
		singleRoom = single;
		occupied = false;
	}

	//Creates a full roomList from the starting Framework variables of NUM_SINGLE_ROOMS and NUM_DOUBLE ROOMS
	//Only called at the very start of the program, when UserIO is created.
	public void createRoomList(){
		for(int x = 0; x < FrameWork.NUM_SINGLE_ROOMS + FrameWork.NUM_DOUBLE_ROOMS; x++){
			if(x <= NUM_SINGLE_ROOMS){
				roomList.add(new Room(x, true));
			}

			if(x <= NUM_SINGLE_ROOMS){
				roomList.add(new Room(x, false));
			}
		}
	}

	//Adds new rooms to the room list
	public static newRoom(boolean single){
			roomList.add(new Room(roomList.size()+1, single));
		}
	}

	//Searches the room list for a matching room and returns it.
	//Returns null if no match.
	public static findRoomByType(boolean type){
		for(int x = 0; x < roomList.size(); x++){
			Room currentRoom = roomList.get(x);
			if(currentRoom.singleRoom = type && !currentRoom.occupied){
				return currentRoom;
			}
		}
	}

	//Searches for the matching room and adjusts it.
	public static setRoomOccupancy(int roomNum, boolean occupancy){
		for(int x = 0; x < roomList.size(); x++){
			Room currentRoom = roomList.get(x);
			if(currentRoom.roomNum == roomNum){
				currentRoom.occupancy = occupancy;
				roomList.set(x, currentRoom);
			}
		}
	}
}