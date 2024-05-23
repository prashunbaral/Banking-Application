package BankingSystem;


public class Account extends Customer {

		  // Account specific details - account number and current balance (now private)
		  private int accountNumber;
		  private int balance;

		  // Constructor to set up a new account with customer details and account number
		  public Account(String FName, String LName, int accNum, int accBal) {
		    // Set customer details using setter methods from Customer class
		    setFirstName(FName);
		    setLastName(LName);

		    this.accountNumber = accNum;
		    this.balance = accBal;
		  }

		  // Public getter and setter methods for accountNumber and balance (encapsulation)
		  public int getAccountNumber() {
		    return accountNumber;
		  }

		  public int getBalance() {
		    return balance;
		  }

		  // Method to deposit funds into the account
		  public void deposit(int amount) {
		    if (amount > 0) {
		      balance += amount;
		      System.out.println("Deposited $" + amount + ". New balance is $" + balance);
		    } else {
		      System.out.println("Invalid deposit amount. Please enter a positive value.");
		    }
		  }

		  // Method to withdraw funds from the account (with some safety checks)
		  public void withdraw(int amount) {
		    if (amount > 0 && balance >= amount) {
		      balance -= amount;
		      System.out.println("Withdrew $" + amount + ". New balance is $" + balance);
		    } else {
		      System.out.println("Insufficient funds. The requested amount is greater than your current balance.");
		    }
		  }

}
