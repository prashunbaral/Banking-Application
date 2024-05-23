package BankingSystem;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField sourceAccountNumber;
	private JTextField destAccountNumber;
	private JTextField amount;
	protected Component frame;
	private LinkedList<Account> globalAccounts;
	private JTextField accDeposit;
	private JTextField depositInput;
	private JTextField accWithdraw;
	private JTextField withdrawInput;
	private JLabel showAllData ; 


	public GUI(LinkedList<Account> accounts) {
		this.globalAccounts = accounts;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1154, 767);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Banking Application");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(517, 25, 159, 49);
		contentPane.add(lblNewLabel);
		
		sourceAccountNumber = new JTextField();
		sourceAccountNumber.setBounds(231, 190, 116, 32);
		contentPane.add(sourceAccountNumber);
		sourceAccountNumber.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Source Account Number :-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 191, 170, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Destination Account Number :-");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 233, 198, 24);
		contentPane.add(lblNewLabel_1_1);
		
		destAccountNumber = new JTextField();
		destAccountNumber.setColumns(10);
		destAccountNumber.setBounds(231, 232, 116, 32);
		contentPane.add(destAccountNumber);
		
		JButton transfer = new JButton("Transfer");
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
        		    String fromAccountNumber = sourceAccountNumber.getText();
        		    String toAccountNumber = destAccountNumber.getText();
        		    String amountText = amount.getText();
        		    if(fromAccountNumber.trim().isEmpty() || toAccountNumber.trim().isEmpty() || amountText.trim().isEmpty()) {
        		        JOptionPane.showMessageDialog(frame,"Inputs cannot be empty");
        		        return;
        		    }
        		    int amount = Integer.parseInt(amountText);
        		    Account fromAccount = null, toAccount = null;
        		    Boolean found1 = false , found2 =false ;
        		    for (Account account : globalAccounts) {
        		        if (Integer.toString(account.getAccountNumber()).equals(fromAccountNumber)) {
        		            fromAccount = account;
        		            found1 = true;
        		            
        		        } else if (Integer.toString(account.getAccountNumber()).equals(toAccountNumber)) {
        		            toAccount = account;
        		            found2 = true;
        		        }
        		        
        		    }
        		    if(!found1 || !found2) {
        		    	JOptionPane.showMessageDialog(frame, "Account doesnt exist!");
        		    }
        		    
        		    if (fromAccount != null && toAccount != null) {
        		    	if (fromAccount.getBalance() >= amount) {
        		    		if(amount<0) {
        		    			JOptionPane.showMessageDialog(frame, "Invalid Amount ");
        		    		} else {
        		    			Transaction.transfer(fromAccount, toAccount, amount);
        		    			String message = "Transfered $%d from Account number %s to Account number %s";
        		    			String result = String.format(message,amount,fromAccountNumber,toAccountNumber);
        		    			JOptionPane.showMessageDialog(frame,result);
        		    		}
        		    		} else {
        		    		JOptionPane.showMessageDialog(frame,"Insufficient balance !");
        		    	}
        		        
        		    }
        		} catch (Exception e3) {
        		    JOptionPane.showMessageDialog(frame,"Invalid input for transfer amount or account number");
        		}
			}
		});
		
		transfer.setBounds(117, 345, 116, 49);
		contentPane.add(transfer);
		
		JButton deposit = new JButton("Deposit");
		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    String accountNumber = accDeposit.getText();
                    boolean found = false;
                    int amounts = Integer.parseInt(depositInput.getText());
                    for (Account account : globalAccounts) {
                        if (Integer.toString(account.getAccountNumber()).equals(accountNumber)) {
                        	if(amounts<0) {
                        		JOptionPane.showMessageDialog(frame, "Invalid Amount ");
                        		found=true;
                        	}else {
                        		account.deposit(amounts);
                        		String message = "Deposited $%d in account number %s";
                        		String result = String.format(message,amounts,accountNumber);
                        		JOptionPane.showMessageDialog(frame, result);
                        		found = true;
                            break;
                        	}
                        		
                        }
                    }
                    if(!found) {
                    	JOptionPane.showMessageDialog(frame,"Account does not exist");
                    }
                } catch (NumberFormatException e1) {
                	JOptionPane.showMessageDialog(frame,"Invalid input for deposit amount or account number");
                }
				
			}
		});
		deposit.setBounds(497, 315, 96, 49);
		contentPane.add(deposit);
		
		JButton withdraw = new JButton("Withdraw");
		withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                  String accountNumber = accWithdraw.getText();
	                  boolean found3=false;
	                  int amounts = Integer.parseInt(withdrawInput.getText());
	                  for (Account account : globalAccounts) {
	                      if (Integer.toString(account.getAccountNumber()).equals(accountNumber)) {
	                    	  if(account.getBalance()>=amounts) {
	                    		  if(amounts<0) {
	                    			  JOptionPane.showMessageDialog(frame, "Invalid Amount ");
	                    			  found3=true;
	                    		  }else {
	                    			  account.withdraw(amounts);
	                    			 String message = "Withdrawn $%d from account number %s .";
	                    			 String result = String.format(message,amounts,accountNumber,account.getBalance());
	                    			 JOptionPane.showMessageDialog(frame, result); 
	                    			 found3=true;
	                    		  }
	                    			 
	                    	  } 
	                          break;
	                      } 
	                  }
	                  if(!found3) {
	                	  JOptionPane.showMessageDialog(frame, "Account does not exist ");
	                  }
	              } catch (NumberFormatException e2) {
	            	  JOptionPane.showMessageDialog(frame,"Invalid input for withdraw amount or account number");
	              }
			}
		});
		withdraw.setBounds(884, 315, 96, 49);
		contentPane.add(withdraw);
		
		JLabel lblNewLabel_1_2 = new JLabel("Amount :-");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(140, 291, 68, 24);
		contentPane.add(lblNewLabel_1_2);
		
		amount = new JTextField();
		amount.setColumns(10);
		amount.setBounds(230, 290, 116, 32);
		contentPane.add(amount);
		
		JLabel Deposit_label = new JLabel("Enter Account_number :-");
		Deposit_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Deposit_label.setBounds(403, 187, 171, 32);
		contentPane.add(Deposit_label);
		
		accDeposit = new JTextField();
		accDeposit.setBounds(602, 190, 96, 32);
		contentPane.add(accDeposit);
		accDeposit.setColumns(10);
		
		JLabel depositlabels = new JLabel("Amount :-");
		depositlabels.setFont(new Font("Tahoma", Font.PLAIN, 15));
		depositlabels.setBounds(506, 242, 68, 31);
		contentPane.add(depositlabels);
		
		depositInput = new JTextField();
		depositInput.setBounds(602, 247, 96, 26);
		contentPane.add(depositInput);
		depositInput.setColumns(10);
		
		JLabel withdrawacclabel = new JLabel("Enter Account_number :-");
		withdrawacclabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		withdrawacclabel.setBounds(750, 187, 171, 32);
		contentPane.add(withdrawacclabel);
		
		accWithdraw = new JTextField();
		accWithdraw.setBounds(949, 190, 152, 32);
		contentPane.add(accWithdraw);
		accWithdraw.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Amount :-");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(853, 241, 68, 32);
		contentPane.add(lblNewLabel_2);
		
		withdrawInput = new JTextField();
		withdrawInput.setBounds(949, 244, 152, 31);
		contentPane.add(withdrawInput);
		withdrawInput.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(380, 128, 13, 316);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(727, 128, 13, 316);
		contentPane.add(separator_1);
		
		JButton showAllButton = new JButton("Show All");
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sbAllData = new StringBuilder("<html>");
				
				for(Account accounts : globalAccounts) {
					sbAllData.append(String.format("Username: %s <br> Account Number: %d <br> Balance :- %d", 
							(accounts.getFirstName() + accounts.getLastName()),
							accounts.getAccountNumber(),accounts.getBalance()))
					.append("<br><br>");
				}
				showAllData.setText(sbAllData.toString());
				
				
			}
		});
		showAllButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		showAllButton.setBounds(101, 489, 132, 49);
		contentPane.add(showAllButton);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBackground(Color.BLACK);
		separator_1_1.setBounds(0, 443, 1150, 11);
		contentPane.add(separator_1_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setForeground(Color.BLACK);
		separator_1_1_1.setBackground(Color.BLACK);
		separator_1_1_1.setBounds(0, 128, 1150, 11);
		contentPane.add(separator_1_1_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(0, 128, 13, 316);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(1137, 128, 13, 316);
		contentPane.add(separator_3);
		
		showAllData = new JLabel("");
		showAllData.setBounds(243, 464, 433, 258);
		contentPane.add(showAllData);
	}
}
