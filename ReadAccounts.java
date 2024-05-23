package BankingSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ReadAccounts {
	  List<String> firstNames = new ArrayList<>();
	  List<String> lastNames = new ArrayList<>();
	  List<Integer> accountList = new ArrayList<>();
	  List<Integer> balanceList = new ArrayList<>();

	  ReadAccounts(String URL) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(URL))) {
	      String line;
	      while ((line = reader.readLine()) != null) {
	        String[] values = line.split(",");
	        firstNames.add(values[0]);
	        lastNames.add(values[1]);
	        accountList.add(Integer.parseInt(values[2]));
	        balanceList.add(Integer.parseInt(values[3]));
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  // Getter methods to return individual lists of data from the CSV file
	  public List<String> getFirstNames() {
	    return firstNames;
	  }

	  public List<String> getLastNames() {
	    return lastNames;
	  }

	  public List<Integer> getAccounts() {
	    return accountList;
	  }

	  public List<Integer> getBalances() {
	    return balanceList;
	  }
	}	