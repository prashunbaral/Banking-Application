package BankingSystem;


public class Customer {

		  // These fields store the customer's first and last name (now private)
		  private String firstName;
		  private String lastName;

		  // Public getter methods to access private fields (encapsulation)
		  public String getFirstName() {
		    return firstName;
		  }

		  public String getLastName() {
		    return lastName;
		  }

		  // Setter methods to update customer names
		  public void setFirstName(String FName) {
		    this.firstName = FName;
		  }

		  public void setLastName(String LName) {
		    this.lastName = LName;
		  }
}
