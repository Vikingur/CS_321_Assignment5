import java.util.*;
import java.lang.*;

public class BankSystem
{
	// returns true if card is valid, false otherwise
    public static boolean validateCard(String ccNumber, String ccType, String ccExpiration)
    {
		// first check if the customer has a credit card number in the first place
		if (ccNumber.equals(""))
		{
			return false;
		}
        // Convert customer's credit card information into a long int
        long ccNumberLong = Long.parseLong(ccNumber.replaceAll("[^0-9]",""));
        
        // split the date on / and - characters
        // Assuming the format MONTH/YEAR
        String[] ccMonthYear = ccExpiration.split("(/|-)");
		
        // grab the Month/Year and convert to integers
        int expirationMonth = Integer.parseInt(ccMonthYear[0]);
        int expirationYear = Integer.parseInt(ccMonthYear[1]);
    		
		// get the current date to determine the month and year
		Date date = ReservationSystem.calendar.getCurrentDate();
		
        // get the current month and year for comparison
        int currentMonth = date.getMonth();
        int currentYear = date.getYear();
        
        // compare the current month/year to the expiration month/year
        if (currentYear*100+currentMonth<expirationYear*100+expirationMonth)
        {
            // if the card is not expired. Send the number and type to
            // the external bank system for validation
            return queryExternalBank(ccNumberLong,ccType);
        }
        return false;
    }
	
    private static boolean queryExternalBank(long ccNumber, String ccType)
    {
        // This is the function that "asks the external bank if the card 
        // is valid" we're just going to assume that any card that is 
        // not expired is valid for simplicities sake
        return true;
    }
	
	// returns true if charge successful, false otherwise
	public static boolean chargeCustomer(int customerID, double paymentAmount)
	{
		// get the customer's cc info for validateCard
		Customer c = Framework.getCustomerByID(customerID);
		String ccNumber = c.getCCNumber();
		String ccType = c.getCCType();
		String ccExpiration = c.getCCExpiration();
		
		// check card validity
		if (validateCard(ccNumber,ccType,ccExpiration))
		{
			// if valid, "charge" customer, return true for success
			System.out.println("Customer charged, $"+String.format("%.2f",paymentAmount)+".");
			return true;
		}
		else
		{
			// otherwise, report that the charge was unsuccessful
			System.out.println("Card is invalid. No payment made.");
			return false;
		}
	}
}
