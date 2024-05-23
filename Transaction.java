package BankingSystem;

class Transaction {

	  // Static method to facilitate transfers between accounts
	  public static void transfer(Account acc1, Account acc2, int amount) {
	    if (acc1.getBalance() >= amount) {
	    	if(acc1.getBalance()<0) {
	    		System.out.println("Invalid amount");
	    	}
	      acc1.withdraw(amount);
	      acc2.deposit(amount);
	      System.out.println("Transferred $" + amount + " from " + acc1.getFirstName() + acc1.getLastName() + " to " + acc2.getFirstName() + acc2.getLastName() + ".");
	    } else {
	      System.out.println("Insufficient funds in the source account. Transfer cannot be completed.");
	    }
	  }
	}
