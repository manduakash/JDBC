package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menudriven {
	Scanner s = new Scanner(System.in);
	int Id, Age, Salary;
	String Name, City;
	
	//saving employee details in DB
	public void saveMenudriven() throws SQLException{
		System.out.println("Enter Employee ID: ");
		Id = s.nextInt();
		System.out.println("Enter Employee Name: ");
		Name = s.next();
		System.out.println("Enter Employee Age: ");
		Age = s.nextInt();		
		System.out.println("Enter Employee City: ");
		City = s.next();
		System.out.println("Enter Employee Salary: ");
		Salary = s.nextInt();
		Connection conn = Helper.con();
		PreparedStatement sl = conn.prepareStatement("insert into Menudriven values(?,?,?,?,?)");
		sl.setInt(1,Id);
		sl.setString(2, Name);
		sl.setInt(3,Age);
		sl.setString(4, City);
		sl.setInt(5,Salary);
		sl.executeUpdate();
	}
	
	//fetching datas
	public void fetchMenudriven() throws SQLException{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from Menudriven");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));
		}
	}
	
	//updating datas 
	public void updateMenudriven() throws SQLException{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();
		System.out.println("Enter Employe Id you want to update: ");
		Id = s.nextInt();
		System.out.println("Enter Employe City: ");
		City = s.nextLine();
		st.executeUpdate("update Menudriven set city='"+City+"' where Id="+Id);
	}
	
	//deleting datas
	public void deleteMenudriven() throws SQLException{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();
		System.out.println("Enter employee Id: ");
		Id = s.nextInt();
		st.executeUpdate("delete from Menudriven where Id="+Id);
	}
}
