package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc_retrieving {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	//loading driver
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/akash" ,"root" ,"root");	//getting connection 
			Statement stmt = conn.createStatement();	//creating statement
			ResultSet rs = stmt.executeQuery("select * from emp_trainee");	//executeing query
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3));	//printing all data from database
			}
			conn.close();			//closing connection
		} catch (Exception e) {		//exception handeling
			System.out.println(e);
		}
	}
}
