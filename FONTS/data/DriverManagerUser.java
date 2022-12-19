package data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Driver for the Manager User class, it is used to try every method of the Manager User class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * 
 * @author Eloi Balaer Morales
 *
 */
public class DriverManagerUser {
  public static void main(String[] args) {
    ManagerUser mu = ManagerUser.getInstance();
    
    ArrayList<String> Users = new ArrayList<String>();
    
    System.out.println("Welcome to the Driver menu of ManagerUser!");
	System.out.println("Write the number of the option you want, or exit if you want to terminate the execution");
	 
	System.out.println("\t 1 -> loadUser()");
	System.out.println("\t 2 -> storeUser(ArrayList<String> Users)");
	System.out.println("\t exit -> close the Driver menu");
    
	Scanner scan = new Scanner(System.in);
	
	String command = scan.nextLine(); 
	while (!command.equals("exit")) {
		switch (command) {
			case "1":
				System.out.println("Loads the users.txt");
				Users = mu.loadUser();
				for (String str : Users)
			    { 		      
			         System.out.println(str); 		
			    }
				System.out.println("\n");
				break;
				
			case "2":
				Users = mu.loadUser();
				ArrayList<String> store_users = new ArrayList<String>();
				System.out.println("Store a set of strings representing users on the users.txt");
				System.out.println("Please, introduce the number of users you want to insert: (INTEGER)");
				Integer n = scan.nextInt();
				String x;
				Integer id;
				for(int i = 0; i < n; ++i) {
					System.out.println("Please, introduce your user id (INTEGER)");
					id = scan.nextInt();  
					x = id.toString();
					System.out.println("Please, introduce the number of items you want to insert: (INTEGER)");
					Integer ni = scan.nextInt();
					System.out.println("Please, introduce the ids of the reviewed items (INTEGERS)");
					for(int j = 0; j < ni; ++j) x+= ("¬" + scan.nextInt());
					Users.add(x);
					store_users.add(x);
				}
				mu.storeUser(Users);
				System.out.println("These are the users that have been saved: ");
				for (String str : store_users)
			    { 		      
			         System.out.println(str); 		
			    }
				System.out.println("\n");
		 		command = scan.nextLine();
				 
				break;
			default:
				System.out.println(command + " is not a valid command.");
				System.out.println("\n");
				break;
		}
		command = scan.nextLine();
	}
	scan.close();
  }
}
