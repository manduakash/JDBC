package JDBC;

import java.sql.SQLException;
import java.util.Scanner;

public class CrudOperation {
	public static void main(String[] args) throws SQLException{
		Menudriven e = new Menudriven();
		Scanner s = new Scanner(System.in);
		int ch;
		do {
			System.out.println("\n 1. Insert \n 2. Display \n 3. Update \n 4. Delete \n 5. Exit");
			System.out.println("Enter your choice from 1-5");
			ch = Integer.parseInt(s.nextLine());
			System.out.println("-------------------------------");
		switch (ch) {
		case 1: 
			e.saveMenudriven();
			break;
		case 2: 
			e.fetchMenudriven();
			break;
		case 3: 
			e.updateMenudriven();
			break;
		case 4: 
			e.deleteMenudriven();
			break;
		case 5: 
			System.exit(0);
			break;
		default:
			System.out.println("invalid input...");
		
		}
		
		}while(ch!=5);
		s.close();
	}
}
