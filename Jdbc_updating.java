package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc_updating {
	public static void main(String[] args) {	//main method
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	//loading driver
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/akash" ,"root" ,"root");	//getting connection 
			Statement stmt = conn.createStatement();	//creating statement
						
			stmt.executeUpdate("update emp_trainee set salary=50000 where emp_name='akash'");	//executeing update query
			stmt.executeUpdate("delete from emp_trainee where emp_name='sayan'");	//executeing update query
			stmt.executeUpdate("insert into emp_trainee values(2, 'sayan', 4500)");	//executeing inserting query
			
			System.out.println("inserted succesfully");
			
			ResultSet rs = stmt.executeQuery("select * from emp_trainee");	//executeing select query
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3));	//printing all data from database
			}
			conn.close();	// closing connection
		} catch (Exception e) {		//exception handeling
			System.out.println(e);
		}
		
	}
}
