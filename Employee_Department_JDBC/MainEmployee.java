package JDBC;

import java.util.Scanner;

public class MainEmployee {

	public static void main(String[] args) {
	try {
		EmployeeUploader obj = new EmployeeUploader();
		
		// to select what operation to be done from user
			while(true) {
				Scanner scan = new Scanner(System.in);
				System.out.println("\n1. Store Department Details");
				System.out.println("2. Store Employee Details");
				System.out.println("3. Retrieve Employee Details");
				System.out.println("4. Calculate PF");
				System.out.println("5. Exit");
				System.out.print("Enter your choice: ");
				// read the user input
				int choice = scan.nextInt();
				// switch case
				switch(choice) {
				
				case 1: 
					obj.storeDepartmentDetails();
					break;
					
				case 2:
					obj.storeEmployeeDetails();
					break;
						
				case 3:
					obj.retrieveEmployeeDetails();
					break;
					
				case 4:
					obj.calculatePF();
					break;
					
				// 5. Exit	
				case 5:
					System.out.println("Have a nice day...");
					scan.close();
					break;
					
				default:
					System.out.println("wrong input");
					break;
				}
				if(choice==5) {
					break;
				}
			}
	}catch(Exception e) {
		System.out.println(e);
	}

	}

}
