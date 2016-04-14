public class Reservation{
	private int reservationID;
	private int status;
	private int startDate;
	private int endDate;
	private int roomType;
	private int numOccupants;
	private int guaranteed;
	//This'll be -1 until the Reservation actually gets assigned a room.
	private int roomNumber = -1;
	private int customerID;
	private static int lastID;

	public Reservation(int status, int startDate, int endDate, int roomType, int numOccupants, int guaranteed){
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.roomType = roomType;
		this.numOccupants = numOccupants;
		this.guaranteed = guaranteed;

		this.reservationID = ++lastID;
		while(Framework.getReservationByID(this.reservationID) != NULL){
			this.reservationID = ++lastID;
		}
	}

	public void cancelReservation(){
		if(roomNumber != -1){
			Room.setRoomOccupancy(roomNumber, false);
		}
	}

	public void setReservationID(int reservationID){
		if(Framework.getReservationByID(reservationID) != NULL){
			return;
		}
		this.reservationID = reservationID;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public void setStartDate(int startDate){
		this.startDate = startDate;
	}

	public void setEndDate(int endDate){
		this.endDate = endDate;
	}

	public void setRoomType(int roomType){
		this.roomType = roomType;
	}

	public void setNumOccupants(int numOccupants){
		this.numOccupants = numOccupants;
	}

	public void setGuaranteed(int guaranteed){
		this.guaranteed = guaranteed;
	}

	public void setRoomNumber(int roomNum){
		this.roomNumber = roomNum;
	}

	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}

	public int getReservationID(){
		return reservationID;
	}

	public int getStatus(){
		return status;
	}

	public int getStartDate(){
		return startDate;
	}

	public int getEndDate(){
		return endDate;
	}

	public int getRoomType(){
		return roomType;
	}

	public int getNumOccupants(){
		return numOccupants;
	}

	public int getRoomNumber(){
		return roomNumber;
	}

	public int getCustomerID(){
		return customerID;
	}
}