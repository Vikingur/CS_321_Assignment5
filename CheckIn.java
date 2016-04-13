import java.util.*;
import java.lang.*;

public class CheckIn
{
	// run this if the customer does not provide new credit card info at checkin
	public static boolean checkIn(String customerName)
	{
		// find the customer with the framework
		Customer c = Framework.getCustomerByName(customerName);
		if (c==null)
		{
			// error out if customer not found
			System.out.println("Customer not found.");
			return false;
		}
		// find the reservation with the customer id
		cid = c.getCustomerID();
		Reservation res = Framework.getReservationByCID(cid);
		// check if the reservation is guaranteed
		if (res.getGuaranteed()==1)
		{
			// check if the card is valid
			if (BankSystem.validateCard(c.getCCNumber(),c.getCCType(),c.getCCExpiration()))
			{
				// set the customer's reservation to "check-in"
				res.setStatus(Framework.STATUS_CHECKED_IN);
				// print out reservation information
				System.out.println(
					"Customer Name: "+customerName+"\n"
					+"Customer ID: "+cid+"\n"
					+"Room Number: "+res.getRoomNumber()+"\n"
					+"Check-in Date: "+res.getStartDate()+"\n"
					+"Check-out Date"+res.getEndDate()
				);
				return true;
			}
			// notify clerk if card not valid
			res.setStatus(Framework.STATUS_MUST_PAY);
			System.out.println("Credit Card info not valid. Reservation marked as MUST_PAY");
			return false;
		}
		// this shouldn't happen if the customer is checking in w/o a card
		// just in case, mark the reservation as must pay in this case
		res.setStatus(Framework.STATUS_MUST_PAY);
		System.out.println("Need credit card information to check in. Reservation marked as MUST_PAY");
		return false;
	}
	
	// run this when the customer provides credit card info with their name at check in.
	public static boolean checkIn(String customerName, String ccNumber, String ccType, String ccExpiration)
	{
		// same as above, check if the customer exists in the system
		Customer c = Framework.getCustomerByName(customerName);
		if (c==null)
		{
			System.out.println("Customer not found.");
			return false;
		}
		cid = c.getCustomerID();
		Reservation res = Framework.getReservationByCID(cid);
		// check if provided card is valid
		if (BankSystem.validateCard(c.getCCNumber(),c.getCCType(),c.getCCExpiration()))
		{
			// if so, update customer and reservation info
			c.setCCNumber(ccNumber);
			c.setCCType(ccType);
			c.setCCExpiration(ccExpiration);
			res.setGuaranteed(1);
			res.setStatus(Framework.STATUS_CHECKED_IN);
			// print information out for customer
			System.out.println(
					"Customer Name: "+customerName+"\n"+
					"Customer ID: "+cid+"\n"+
					"Room Number: "+res.getRoomNumber()+"\n"+
					"Check-in Date: "+res.getStartDate()+"\n"+
					"Check-out Date"+res.getEndDate()
			);
			return true;
		}
		// if the new info isn't valid. Notify the clerk and mark the reservation as MUST_PAY
		res.setStatus(Framework.STATUS_MUST_PAY);
		System.out.println("Credit Card info not valid, Reservation marked as MUST_PAY");
		return false;
	}
}