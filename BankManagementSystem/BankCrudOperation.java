package JDBC;

import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BankCrudOperation {
	public static void main(String[] args) throws SQLException{
		BankMenudriven e = new BankMenudriven();
		Scanner s = new Scanner(System.in);
		int ch;
		do {
			System.out.println("\n 1. Create Account \n 2. Balance Inquiry \n 3. Deposite \n 4. Withdrawal \n 5. Change Pin \n 6. Delete Account \n 7. Exit");
			ch = Integer.parseInt(JOptionPane.showInputDialog("Enter your choice from 1-7"));
			
			System.out.println("-------------------------------");
		switch (ch) {
		case 1: 
			e.createAccount();
			break;
		case 2: 
			e.balanceInquiry();
			break;
		case 3: 
			e.deposite();
			break;
		case 4: 
			e.withdrawal();
			break;
		case 5: 
			e.pinChange();
			break;
		case 6: 
			e.deleteAccount();
			break;
		case 7: 
			System.exit(0);
			break;
		default:
			System.out.println("invalid input...");
		
		}
		
		}while(ch!=7);
		s.close();
	}
}
