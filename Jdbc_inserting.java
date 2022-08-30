package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Jdbc_inserting {
	public static void main(String[] args) {	//main method
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	//loading driver
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/akash" ,"root" ,"root");	//getting connection 
			Statement stmt = conn.createStatement();	//creating statement
			
			stmt.executeUpdate("insert into emp_trainee values(2, 'sayan', 45000)");	//executeing query
			System.out.println("inserted succesfully");
			conn.close();	// closing connection
		} catch (Exception e) {		//exception handeling
			System.out.println(e);
		}
		
	}
}
