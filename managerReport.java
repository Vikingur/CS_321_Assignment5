import java.util.*;
import java.lang.*;

/* FINAL REPORT SAYS:
	Report gets info for the CURRENT DAY
	and returns: number of reservations,
	number of reserved/unreserved rooms,
	occupancy rate, and revenue generated
	for the day.
*/

//Called with input of Calendar.currentDay

class managerReport {

	Date currentDay;
	int reservationNum = 0;
	int reserved = 0;
	int unreserved = 0;
	double occupancyRate = 0;
	double revenue = 0;
	ArrayList<Integer> reservations;

	public managerReport(Date day) {

		this.currentDay = day;
		buildReport();
	}

	private void getRevenue() {
		this.reservations = currentDay.getIDs();
		Iterator iterate = reservations.iterator();
		Reservation reserve;
		int singles = 0;
		int doubles = 0;

		while(iterate.hasNext()) {
			reserve = iterate.next();
			if(reserve.getRoomType == 1)
				singles++;
			else
				doubles++;
		}

		this.revenue = (Framework.SINGLE_RATE * singles) + (Framework.DOUBLE_RATE * doubles);
	}

	private void getOccupancy() {
		this.reservations = currentDay.getIDs();
		Iterator iterate = reservations.iterator();
		Reservation reserve;
		int occupantsByRoom;

		while(iterate.hasNext()) {
			reserve = iterate.next();
			occupantsByRoom = reserve.getNumOccupants();
			this.occupancyRate += occupantsByRoom;
		}
	}

	private void bulidReport() {

		this.reservations = currentDay.getNumReservations();
		this.unreserved = (Framework.NUM_SINGLE_ROOMS) + (Framework.NUM_DOUBLE_ROOMS) - this.reservations;
		this.reserved = this.reservations;
		getOccupancy();
		getRevenue();

	}
}