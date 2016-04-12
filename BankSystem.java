public class BankSystem
{
	public class Reservation
	{
		public Reservation()
		{
			
		}
	}
	
	public class Customer
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
	public static boolean validateCard(Customer customer, Reservation res)
	{
		String ccType = customer.getCCType();
		long ccNumber = Integer.parseLong(customer.getCCNumber().replaceAll("[^a-zA-z]",""));
		String[] ccExpiration = customer.getCCExpiration().split("/");
		int expirationMonth = Integer.parseInt(ccExpiration[0]);
		int expirationYear = Integer.parseInt(ccExpiration[1]);
		
		system.out.println("Expiration Month:",expirationMonth);
		
		return false;
		
	}
	
	private static boolean queryExternalBank(long ccNumber, String ccType)
	{
		return true;
	}
	
	public void main(String[] args)
	{
		Customer c = new Customer();
		Reservation r = new Reservation();
		validateCard(c,r);
	}
}