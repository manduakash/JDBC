package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BankMenudriven {
	Scanner s = new Scanner(System.in);
	String  accountHolder, ifsc_code, branch, accountType, DB_ifsc_code;
	int accountNo, pin, newPin, DB_accountNo, DB_pin;
	double balance, old_Balance, updated_Balance;
	
	//--------------------------------------------------------------
		public void createAccount() throws SQLException{
			Connection conn = Helper.con();
			Statement st = conn.createStatement();
			
			accountNo= Integer.parseInt(JOptionPane.showInputDialog("Enter account no.: "));
			accountHolder= JOptionPane.showInputDialog("Enter account holder name: ");
			ifsc_code= JOptionPane.showInputDialog("Enter IFSC code: ");
			branch= JOptionPane.showInputDialog("Enter branch name: ");
			accountType= JOptionPane.showInputDialog("Enter account tyepe (saving/current): ");
			balance= Integer.parseInt(JOptionPane.showInputDialog("Deposite some amount: "));
			pin= Integer.parseInt(JOptionPane.showInputDialog("Enter your 4 digit pin: "));
			
			//validations
			ResultSet rs = st.executeQuery("select * from bank where account_no="+accountNo);
			while(rs.next()) {
				DB_accountNo=rs.getInt(1);
				DB_ifsc_code=rs.getString(3);
			}
			if(DB_accountNo!=accountNo) {		//duplicate account number input
				JOptionPane.showMessageDialog(null,"Already Account number is taken...");
			}
			if(DB_ifsc_code!=ifsc_code) {		//duplicate ifsc number input
				JOptionPane.showMessageDialog(null,"Already IFSC code is taken...");
			}else {
			
			st.executeUpdate("insert into bank values("+accountNo+", '"+accountHolder+"', '"+ifsc_code+"', '"+branch+"', '"+accountType+"', "+balance+", "+pin+")");
			JOptionPane.showMessageDialog(null, "Account "+accountNo+" has been created successfully.... ");
			}
		}
	//fetching datas
	public void balanceInquiry() throws SQLException{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();
		
		accountNo= Integer.parseInt(JOptionPane.showInputDialog("Enter account no.: "));
		pin= Integer.parseInt(JOptionPane.showInputDialog("Enter your 4 digit pin: "));
		
		ResultSet rs = st.executeQuery("select * from bank where account_no="+accountNo);
		while(rs.next()) {
			DB_accountNo=rs.getInt(1);
			DB_pin=rs.getInt(7);
			balance=rs.getInt(6);
		}
		//validations
		if(DB_accountNo!=accountNo) {		//wrong account number input
			JOptionPane.showMessageDialog(null,"Wrong Account number...");
		}
		if(DB_pin!=pin) {		//wrong pin validation
			JOptionPane.showMessageDialog(null,"Wrong pin...");
		}
		
		else {
			JOptionPane.showMessageDialog(null, "Total balance: "+balance+"/- Rs.");
		}
	}
	
	//updating datas 
	public void deposite() throws SQLException{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();
		
		accountNo= Integer.parseInt(JOptionPane.showInputDialog("Enter account no.: "));
		pin= Integer.parseInt(JOptionPane.showInputDialog("Enter your 4 digit pin: "));
		balance= Integer.parseInt(JOptionPane.showInputDialog("Deposite some amount: "));
		
		ResultSet rs = st.executeQuery("select * from bank where account_no="+accountNo);
		while(rs.next()) {
			
			old_Balance=rs.getInt(6);
			DB_pin=rs.getInt(7);
		}
		
		//validation
		if(DB_pin!=pin) {		//wrong pin validation
			JOptionPane.showMessageDialog(null,"Wrong pin...");
		}else {
		st.executeUpdate("update bank set balance = balance+"+balance+" where account_no="+accountNo);
		JOptionPane.showMessageDialog(null,"Amount "+balance+"/- Rs. has been deposited to your account successfully.... \nOld balance = "+old_Balance+"/- Rs.\nUpdated balance = "+(old_Balance+balance)+"/- Rs.");
		}
		}
	
	//updating datas 
	public void withdrawal() throws SQLException{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();
		
		accountNo= Integer.parseInt(JOptionPane.showInputDialog("Enter account no.: "));
		pin= Integer.parseInt(JOptionPane.showInputDialog("Enter your 4 digit pin: "));
		balance= Integer.parseInt(JOptionPane.showInputDialog("Enter your withdrawing amount: "));
		
		ResultSet rs = st.executeQuery("select * from bank where account_no="+accountNo);
		while(rs.next()) {
			DB_accountNo=rs.getInt(1);
			old_Balance=rs.getInt(6);
			DB_pin=rs.getInt(7);
		}
		
		//validations
		if(DB_accountNo!=accountNo) {		//wrong account number input
			JOptionPane.showMessageDialog(null,"Wrong Account number...");
		}
		if((old_Balance-balance)<0) {		//insufficient balance validation
			JOptionPane.showMessageDialog(null,"Insufficient Balance...");
		}
		if(DB_pin!=pin) {		//wrong pin validation
			JOptionPane.showMessageDialog(null,"Wrong pin...");
		}else {
		
		st.executeUpdate("update bank set balance = balance-"+balance+" where account_no="+accountNo);
		JOptionPane.showMessageDialog(null,"Amount has been credited from your account successfully.... \nOld balance = "+old_Balance+"/- Rs.\nUpdated balance = "+(old_Balance+balance)+"/- Rs.");
			}
		}
	
	//updating datas 
	public void pinChange() throws SQLException{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();

		accountNo= Integer.parseInt(JOptionPane.showInputDialog("Enter account no.: "));
		pin= Integer.parseInt(JOptionPane.showInputDialog("Enter your 4 digit old pin: "));
		newPin = Integer.parseInt(JOptionPane.showInputDialog("Enter your new 4 digit pin: "));
		
		ResultSet rs = st.executeQuery("select * from bank where account_no="+accountNo);
		while(rs.next()) {
			DB_accountNo=rs.getInt(1);
			DB_pin=rs.getInt(7);
		}
		//validations
		if(DB_accountNo!=accountNo) {		//wrong account number input
			JOptionPane.showMessageDialog(null,"Wrong Account number...");
		}
		if(DB_pin!=pin) {		//wrong pin validation
			JOptionPane.showMessageDialog(null,"Wrong pin...");
		}else {
		st.executeUpdate("update bank set pin = "+newPin+" where account_no="+accountNo);
		JOptionPane.showMessageDialog(null,"Your pin has been updated successfully.... ");
		}
	}
	
	//deleting datas
	public void deleteAccount() throws SQLException{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();
		
		accountNo= Integer.parseInt(JOptionPane.showInputDialog("Enter account no.: "));
		pin= Integer.parseInt(JOptionPane.showInputDialog("Enter your 4 digit old pin: "));
		
		ResultSet rs = st.executeQuery("select * from bank where account_no="+accountNo);
		while(rs.next()) {
			DB_accountNo=rs.getInt(1);
			DB_pin=rs.getInt(7);
		}
		//validations
		if(DB_accountNo!=accountNo) {		//wrong account number input
			JOptionPane.showMessageDialog(null,"Wrong Account number...");
		}
		if(DB_pin!=pin) {		//wrong pin validation
			JOptionPane.showMessageDialog(null,"Wrong pin...");
		}else {
		st.executeUpdate("delete from bank where account_no="+accountNo);
		JOptionPane.showMessageDialog(null,"Your account has been deleted successfully.... ");
		}
	}
	
	
}
