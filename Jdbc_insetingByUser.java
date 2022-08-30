package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Jdbc_insetingByUser {
	public static void main(String[] args) {
		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		Scanner s3 = new Scanner(System.in);

		System.out.println("enter employee id: ");
		String emp_id = s1.next();
		System.out.println("enter employee name: ");
		String emp_name = s2.next();
		System.out.println("enter employee salary: ");
		String basic = s3.next();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	//loading driver
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/akash" ,"root" ,"root");	//getting connection 
			PreparedStatement stmt = conn.prepareStatement("insert into emp_trainee values(?, ?, ?)");	//creating statement
			
			stmt.setString(1, emp_id);
			stmt.setString(2, emp_name);
			stmt.setString(3, basic);
			stmt.execute();	//executeing query
			System.out.println("inserted succesfully");

		} catch (Exception e) {		//exception handeling
			System.out.println(e);
		}
		s1.close();
		s2.close();
		s3.close();
	}
}
