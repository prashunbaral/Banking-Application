package BankingSystem;

import java.awt.EventQueue;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Main {

  public static void main(String[] args) {

    ReadAccounts readAccounts = new ReadAccounts("./Account.csv");

    LinkedList<Account> accounts = new LinkedList<>();
    LinkedList<String> firstNames = new LinkedList<>(readAccounts.getFirstNames());
    LinkedList<String> lastNames = new LinkedList<>(readAccounts.getLastNames());
    LinkedList<Integer> accountList = new LinkedList<>(readAccounts.getAccounts());
    LinkedList<Integer> balanceList = new LinkedList<>(readAccounts.getBalances());

    // Convert read data into Account objects
    for (int i = 0; i < readAccounts.firstNames.size(); i++) {
      accounts.add(new Account(firstNames.get(i), 
                                lastNames.get(i), 
                                accountList.get(i), 
                                balanceList.get(i)));
    }
   
    EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				GUI frame = new GUI(accounts);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
  }
}

