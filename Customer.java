public class Customer{

	private int customerID;
	private String name;
	private String address;
	private String ccType;
	private String ccNumber;
	private String ccExpiration;
	private static int lastID;

	public Customer(String name, String address, String cType, String cNumber, String cExpiration){
		this.name = name;
		this.address = address;
		this.ccType = cType;
		this.ccNumber = cNumber;
		this.ccExpiration = cExpiration;
		this.customerID = ++lastID;
		while(Framework.getCustomerByID(this.customerID) != null){
			this.customerID = ++lastID;
		}
		Framework.storeCustomer(this);
	}

	public void setCustomerID(int id){
		if(Framework.getCustomerByID(id) != null){
			return;
		}

		customerID = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setAddress(String address){
		this.address = address;
	}
   
	public void setCCType(String ccType){
		this.ccType = ccType;
	}

	public void setCCNumber(String ccNumber){
		this.ccNumber = ccNumber;
	}

	public void setCCExpiration(String ccExpiration){
		this.ccExpiration = ccExpiration;
	}

	public String getName(){
		return name;
	}

	public String getAddress(){
		return address;
	}

	public String getCCType(){
		return ccType;
	}

	public String getCCNumber(){
		return ccNumber;
	}

	public String getCCExpiration(){
		return ccExpiration;
	}
	
	public int getCustomerID()
	{
		return customerID;
	}
}