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
		this.reservations = currentDay.getReservationIDs();
		Iterator iterate = reservations.iterator();
		Reservation reserve;
		double singles = 0;
		double doubles = 0;

		while(iterate.hasNext()) {
			reserve = (Reservation)iterate.next();
			if(reserve.getRoomType() == 1)
				singles++;
			else
				doubles++;
		}

		this.revenue = (Framework.SINGLE_RATE * singles) + (Framework.DOUBLE_RATE * doubles);
		
		getOccupancy(singles, doubles);
	}

	private double getOccupancy(double singles, double doubles) {
		this.occupancyRate = (singles + doubles) / (Framework.NUM_SINGLE_ROOMS + Framework.NUM_DOUBLE_ROOMS);
		return this.occupancyRate;
	}

	private void buildReport() {

		this.reservationNum = currentDay.getNumReservations();
		this.unreserved = (Framework.NUM_SINGLE_ROOMS) + (Framework.NUM_DOUBLE_ROOMS) - this.reservationNum;
		this.reserved = this.reservationNum;
		getRevenue();

	}
}