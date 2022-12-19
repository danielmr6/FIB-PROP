package data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Driver for the Manager ActiveUser class, it is used to try every method of the Manager ActiveUser class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * @author Eloi Balaer Morales
 *
 */
public class DriverManagerActiveUser {
  public static void main(String[] args) {
    ManagerActiveUser mu = ManagerActiveUser.getInstance();
    
    ArrayList<String> Users = new ArrayList<String>();
    
    System.out.println("Welcome to the Driver menu of ManagerActiveUser!");
	System.out.println("Write the number of the option you want, or exit if you want to terminate the execution");
	 
	System.out.println("\t 1 -> loadActiveUser()");
	System.out.println("\t 2 -> storeActiveUser(ArrayList<String> Users)");
	System.out.println("\t exit -> close the Driver menu");
    
	Scanner scan = new Scanner(System.in);
	
	String command = scan.nextLine(); 
	while (!command.equals("exit")) {
		switch (command) {
			case "1":
				System.out.println("Loads all the .txt where the changes have been made");
				Users = mu.loadActiveUser();
				for (String str : Users)
			    { 		      
			         System.out.println(str); 		
			    }
				System.out.println("\n");
				break;
				
			case "2":
				Users = mu.loadActiveUser();
				System.out.println("Store a set of strings representing users on their respective .txt");
				System.out.println("Please, introduce the number of ActiveUsers you want to insert: (INTEGER)");
				Integer n = scan.nextInt();
				String x;
				//String d = scan.nextLine(); 
				Integer id, idR;
				Boolean adm = false;
				String d = scan.nextLine(); 
				for(int i = 0; i < n; ++i) {
					System.out.println("Please, introduce your ActiveUser id (INTEGER)");
					id = scan.nextInt(); 
					System.out.println("Please, introduce your Recommendation id (INTEGER)");
			 		idR = scan.nextInt();
			 		System.out.println("Please, introduce if the ActiveUser is an admin (true/false)");
			 		adm = scan.nextBoolean();
					x = (id + "¬" + idR  + "¬" + adm);
					scan.nextLine();
					System.out.println("Give the number of items to insert (INTEGER)");
					n = Integer.parseInt(scan.nextLine());
					System.out.println("Please, introduce the ids of the reviewed items (INTEGERS)");
					for(int j=0;j<n;++j) x += ("¬" + scan.nextInt());
					Users.add(x);
				}
				if (n != 0) scan.nextLine();
				mu.storeActiveUser(Users);
				System.out.println("Loads all the .txt where the changes have been made");
				Users = mu.loadActiveUser();
				for (String str : Users)
			    { 		      
			         System.out.println(str); 		
			    }
				System.out.println("\n");
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
