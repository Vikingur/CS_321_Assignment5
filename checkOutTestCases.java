public class checkOutTestCases{
   public static void main(String[] args){
   //Expired credit card
   Customer tess = new Customer("Tess Case", "444 Blank Ave.", "American Express", "1111444411113333", "01/01");
   //Valid credit card
   Customer klaus = new Customer("Klaus Dougram", "445 Blank Ave.", "American Express", "1111444411113334", "02/02");
   
   Framework.addCustomer(tess);
   Framework.addCustomer(klaus);
   
   }
}