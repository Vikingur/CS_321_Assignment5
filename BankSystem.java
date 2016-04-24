/*
   This is the proxy class representing the external Bank System.
   It includes the functions validateCard and chargeCustomer.
   validateCard will examine the card's expiration date
*/

import java.util.*;
import java.lang.*;

public class BankSystem
{
    public static boolean validateCard(String ccNumber, String ccType, String ccExpiration)
    {
		//Return false if card doesn't exist
		if (ccNumber == null){return false;}
      
      //Convert customer's credit card information into a long int
      long ccNumberLong = Long.parseLong(ccNumber.replaceAll("[^0-9]",""));
       
      //Split the date on / and - characters
      String[] ccMonthYear = ccExpiration.split("(/|-)");
		
      //Grab the Month/Year and convert to integers
      int expirationMonth = Integer.parseInt(ccMonthYear[0]);
      int expirationYear = Integer.parseInt(ccMonthYear[1]);
    		
		//Get the current date
		Date date = Calendar.getCurrentDate();
      int currentMonth = date.getMonth();
      int currentYear = date.getYear();
        
      //Compare the current month/year to the card's expiration month/year
      if (currentYear < expirationYear || (currentYear == expirationYear && currentMonth <= expirationMonth)){return true;}
      else {return false;}
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
