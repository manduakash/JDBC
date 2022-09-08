package JDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeUploader {
	int department_id, employee_id, employee_salary, employee_contactNo;
	String department_name, department_head, department_description, employee_name, employee_address;
	//for storing db datas
	int DBemployee_id, DBdepartment_id, DBemployee_contactNo, DBemployee_salary;
	String DBemployee_name;
	double PF;
	
	//method for storing department details
	public void storeDepartmentDetails() throws SQLException, Exception{
		Connection conn = Helper.con();
		Statement st = conn.createStatement();
		
		//taking user input
		Scanner d_id = new Scanner(System.in);
		Scanner d_name = new Scanner(System.in);
		Scanner d_head = new Scanner(System.in);
		Scanner d_desc = new Scanner(System.in);
		
		System.out.println("Enter department id: ");
		department_id=d_id.nextInt();
		System.out.println("Enter department name: ");
		department_name=d_name.nextLine();
		System.out.println("Enter department head: ");
		department_head=d_head.nextLine();
		System.out.println("Enter department description: ");
		department_description=d_desc.nextLine();
		
		//validations
		ResultSet rs = st.executeQuery("select department_id from department_table where department_id="+department_id);
		while(rs.next()) {
			DBdepartment_id=rs.getInt(1);
		}
		if(DBdepartment_id!=department_id){
			throw new departmentNotPresentException("Department does not exists");
		}
		else {
		st.executeUpdate("insert into department_table values("+department_id+", '"+department_name+"', '"+department_head+"', '"+department_description+"')");
		System.out.println("Department details has been added successfully.... ");
		}
		d_id.close();
		d_name.close();
		d_head.close();
		d_desc.close();
		
	}
	//method for storing employee details
		public void storeEmployeeDetails() throws SQLException,Exception{
			Connection conn = Helper.con();
			Statement st = conn.createStatement();
			
			//taking user input
			Scanner e_id = new Scanner(System.in);
			Scanner e_name = new Scanner(System.in);
			Scanner e_salary = new Scanner(System.in);
			Scanner e_contactNo = new Scanner(System.in);
			Scanner e_address = new Scanner(System.in);
			Scanner d_id = new Scanner(System.in);
			
			System.out.println("Enter employee id: ");
			employee_id=e_id.nextInt();
			System.out.println("Enter employee name: ");
			employee_name=e_name.nextLine();
			System.out.println("Enter employee salary: ");
			employee_salary=e_salary.nextInt();
			System.out.println("Enter employee contact no. : ");
			employee_contactNo=e_contactNo.nextInt();
			System.out.println("Enter employee address: ");
			employee_address=e_address.nextLine();
			System.out.println("Enter department id: ");
			department_id=d_id.nextInt();
			
			//validations
			ResultSet rs = st.executeQuery("select employee_id, employee_name, employee_contactNo, employee_salary from employee_table where employee_id="+employee_id);
			while(rs.next()) {
				DBemployee_id=rs.getInt(1);
				DBemployee_name=rs.getString(2);
				DBemployee_contactNo=rs.getInt(3);
				DBemployee_salary=rs.getInt(4);
			}
			if((DBemployee_id==employee_id) || (DBemployee_name==employee_name) || (DBemployee_contactNo==employee_contactNo)){
				throw new duplicateDataException("Employee already exists");
			}
			if(DBemployee_salary<1000) {
				throw new lessSalaryAmountException("Salary can not be less than 1000/-");
			}
			else {
			st.executeUpdate("insert into employee_table values("+employee_id+", '"+employee_name+"', "+employee_salary+", "+employee_contactNo+", '"+employee_address+"', "+department_id+")");
			System.out.println("Employee details has been added successfully.... ");
			}
			
			e_id.close();
			e_name.close();
			e_salary.close();
			e_contactNo.close();
			e_address.close();
			d_id.close();
			
		}
		
		//fetching datas
		public void retrieveEmployeeDetails() throws SQLException{
			Connection conn = Helper.con();
			Statement st = conn.createStatement();
			
			//taking user input
			Scanner e_id = new Scanner(System.in);
			System.out.println("Enter employee id: ");
			DBemployee_id=e_id.nextInt();
			
			ResultSet rs = st.executeQuery("select employee_id, employee_name, employee_contactNo, employee_address, department_name, department_head from employee_table,department_table where employee_id="+DBemployee_id);
			while(rs.next()) {
				employee_id=rs.getInt(1);
				employee_name=rs.getString(2);
				employee_contactNo=rs.getInt(3);
				employee_address=rs.getString(4);
				department_name=rs.getString(5);
				department_head=rs.getString(6);
			}
			if(employee_id==0) {
				System.out.println("Employee ID not present...");
			}else {				
				System.out.println("employee_id='"+employee_id+"', employee_name='"+employee_name+"', employee_contactNo='"+employee_contactNo+"', employee_address='"+employee_address+"', department_name='" +department_name+ "', department_head='"+department_head+"'");
			}
			e_id.close();
		}
		//fetching datas
		public void calculatePF() throws SQLException{
			Connection conn = Helper.con();
			Statement st = conn.createStatement();
			
			//taking user input
			Scanner e_id = new Scanner(System.in);
			System.out.println("Enter employee id: ");
			employee_id=e_id.nextInt();
			
			ResultSet rs = st.executeQuery("select employee_id,  employee_salary from employee_table where employee_id="+employee_id);
			while(rs.next()) {
				DBemployee_id=rs.getInt(1);
				employee_salary=rs.getInt(2);	
			}
			if(DBemployee_id==0) {
				System.out.println("Employee ID not present...");
			}else {				
				if((employee_salary>=1000)&&(employee_salary<=10000)) {
					PF=employee_salary*0.05;
				}
				if((employee_salary>=10000)&&(employee_salary<=100000)) {
					PF=employee_salary*0.06;
				}
				if(employee_salary>100000) {
					PF=employee_salary*0.07;
				}
				System.out.println("Employee PF amount is: "+PF);
			}
			e_id.close();
		}
}

//exception classes 
class duplicateDataException extends Exception{
	private static final long serialVersionUID = 1L;
	duplicateDataException(String s){
		super(s);
	}
}

class lessSalaryAmountException extends Exception{
	private static final long serialVersionUID = 1L;
	lessSalaryAmountException(String s){
		super(s);
	}
}

class departmentNotPresentException extends Exception{
	private static final long serialVersionUID = 1L;
	departmentNotPresentException(String s){
		super(s);
	}
}
