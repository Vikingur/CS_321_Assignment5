import java.util.*;



class checkOut {

	Reservation reservation;
	boolean checkedOut = false;
	boolean reservationComplete = false;
	boolean paid = false;
	double payment;
	int customer;

	public checkOut(Reservation reservation, int customerID) {

		this.reservation = reservation;
		this.customer = customerID
	}

	private void checkingOut() {

		int daysReserved = reservation.getEndDate() - reservation.getStartDate();
		double nightlyCharge = (reservation.getRoomType > 1) ? Framework.DOUBLE_RATE : Framework.SINGLE_RATE;

		this.payment = daysReserved * nightlyCharge;
		chargeCustomer();

		if(paid == true)
			this.checkedOut = true;
	}

	private void chargeCustomer() {

		this.paid = bankSystem.chargeCustomer(customerID, payment);
	}
}