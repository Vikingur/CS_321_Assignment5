import java.util.*;
import java.lang.*;

// Proxy class of a customer for testing purposes
// TODO Remove when Customer exists
class Customer
{
	public Customer()
	{
		
	}
	public String getCCType()
	{
		return "MasterCard";
	}
	public String getCCNumber()
	{
		return "12342345 2835935 1  324";
	}
	public String getCCExpiration()
	{
		return "10/2015";
	}
}

// Proxy class of the calender for testing purposes
// TODO Remove when Calender exists
class Calender
{
    public static int getYear()
    {
        return 2016;
    }
    public static int getMonth()
    {
        return 1;
    }
}

public class BankSystem
{
	public static boolean validateCard(Customer customer)
	{
        // get the customer's credit card info, converting the number into an
        // integer
		String ccType = customer.getCCType();
		long ccNumber = Long.parseLong(customer.getCCNumber().replaceAll("[^0-9]",""));
        
        // split the date on / and - characters
        // Assuming the format MONTH/YEAR
		String[] ccExpiration = customer.getCCExpiration().split("(/|-)");
		
        // grab the Month/Year and convert to integers
        int expirationMonth = Integer.parseInt(ccExpiration[0]);
		int expirationYear = Integer.parseInt(ccExpiration[1]);
		
        // get the current month and year for comparison
        int currentMonth = Calender.getMonth();
        int currentYear = Calender.getYear();
        
        // compare the current month/year to the expiration month/year
		if (currentYear*100+currentMonth<expirationYear*100+expirationMonth)
        {
            // if the card is not expired. Send the number and type to
            // the external bank system for validation
            return queryExternalBank(ccNumber,ccType);
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
    
    // test method.
    // TODO Remove when integrating BankSystem
	public static void main(String[] args)
	{
		Customer c = new Customer();
		System.out.println(""+validateCard(c));
	}
}
