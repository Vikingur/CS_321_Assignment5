import java.util.*;



class Calendar {

	ArrayList<Date> days;
	Date currentDay;


	public Calendar() {

		days = new ArrayList<Date>();
	}

	public void buildCalendar() {

		for(int i=1; i<=Framework.NUM_DAYS; i++) {
			Date day = new Date();
			days.add(day);
		}
	}

	public void addReservation(int id, int date) {
		Date day = days.get(date);
		day.addReservation(id);
	}
	
	public static int getYear() {
		return 2016;
	}
	
	public static int getMonth() {
		return 1;
	}
}



class Date {

	ArrayList<Integer> reservationIDs;
	int numReservations;

	public Date() {
		reservationIDs = new ArrayList<Integer>();
		numReservations = 0;
	}

	public void addReservation(int id) {
		reservationIDs.add(id);
		numReservations++;
	}

	public getNumReservations() {	//For the Manager Report.
		return this.numReservations;
	}

	public ArrayList<Integer> getIDs() {
		return this.reservationIDs;
	}
}
