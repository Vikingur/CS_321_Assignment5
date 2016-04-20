/*
   This is the entity class representing individual Customers in the system.
*/

public class Customer{
   //Class variables
	private int customerID;
	private String name;
	private String address;
	private String ccType;
	private String ccNumber;
	private String ccExpiration;

   //Class constructors
   public Customer(){
      customerID = -1;
      name = null;
      address = null;
      ccType = null;
      ccNumber = null;
      ccExpiration = null;
   }
	public Customer(String inName, String inAddress, String inCCType, String inCCNumber, String inCCExpiration){
		name = inName;
      address = inAddress;
      ccType = inCCType;
      ccNumber = inCCNumber;
      ccExpiration = inCCExpiration;
	}

   //Class setters
	public void setCustomerID(int inCustomerID){customerID = inCustomerID;}
	public void setName(String inName){name = inName;}
	public void setAddress(String inAddress){address = inAddress;} 
	public void setCCType(String inCCType){ccType = inCCType;}
	public void setCCNumber(String inCCNumber){ccNumber = inCCNumber;}
	public void setCCExpiration(String inCCExpiration){ccExpiration = inCCExpiration;}
	
   //Class getters
   public int getCustomerID(){return customerID;}
   public String getName(){return name;}
	public String getAddress(){return address;}
	public String getCCType(){return ccType;}
	public String getCCNumber(){return ccNumber;}
	public String getCCExpiration(){return ccExpiration;}
}