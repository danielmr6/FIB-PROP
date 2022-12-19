package domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Driver for the User class, it is used to try every method of the User class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * 
 * @author Eloi Balaer Morales
 *
 */
public class DriverUser {
	 public static void main(String[] args) {
		 User u1 = new User();
		 Integer idI, n, idItem;
		 ArrayList<Integer> Val;
		 
		 System.out.println("Welcome to the Driver menu of User!");
		 System.out.println("Write the number of the option you want, or exit if you want to terminate the execution");
		 
		 System.out.println("\t 1 -> User()");
		 System.out.println("\t 2 -> User(Integer id)");
		 System.out.println("\t 3 -> User(Integer id, ArrayList<Integer> Reviews)");
		 System.out.println("\t 4 -> User(String data)");
		 System.out.println("\t 5 -> String toString()");
		 System.out.println("\t 6 -> Integer getId()");
		 System.out.println("\t 7 -> ArrayList<Integer> getReview()");
		 System.out.println("\t 8 -> void setId(Integer id)");
		 System.out.println("\t 9 -> void addReview(Integer id_item)");
		 System.out.println("\t exit -> close the Driver menu");
		 
		 Scanner scan = new Scanner(System.in);
	
		String command = scan.nextLine(); 
		while (!command.equals("exit")) {
			switch (command) {
				case "1":
					System.out.println("Constructor without attributes");
					u1 = new User();
			 		System.out.println("Id: "+ u1.getId());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		System.out.println(" ");
					break;
					
				case "2":
					System.out.println("Constructor with Id");
					System.out.println("Please, introduce your user id (INTEGER)");
			 		idI = scan.nextInt();
					u1 = new User(idI);
					System.out.println("Id: "+ u1.getId());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		System.out.println(" ");
			 		command = scan.nextLine();
					break;
				case "3":
					System.out.println("Constructor with Id and ReviewsIds");
					System.out.println("Please, introduce your user id (INTEGER)");
			 		idI = scan.nextInt();
			 		System.out.println("Give the number of items to insert (INTEGER)");
					n = scan.nextInt();
					System.out.println("Please, introduce the ids of the items reviewed by the user (INTEGERS)");
					Val = new ArrayList<Integer>();
					for(int i=0;i<n;++i) Val.add(scan.nextInt());
					u1 = new User(idI, Val);
					System.out.println("Id: "+ u1.getId());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		System.out.println(" ");
			 		command = scan.nextLine();
					break;
				case "4":
					System.out.println("Constructor with STRING (Id, and ReviewsIds)");
					System.out.println("Please, introduce your user id (INTEGER)");
			 		idI = scan.nextInt();
			 		String x = idI.toString();
			 		System.out.println("Give the number of items to insert (INTEGER)");
					n = scan.nextInt();
					System.out.println("Please, introduce the ids of the reviewed items (INTEGERS)");
					for(int i=0;i<n;++i) x += ("¬" + scan.nextInt());
					
					u1 = new User(x);
					System.out.println("Id: "+ u1.getId());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		command = scan.nextLine();
			 		System.out.println(" ");
					break;
				case "5":
					System.out.println("Get the actual user on string format, if the user is not initialized returns -1");
					System.out.println(u1.toString());
					System.out.println(" ");
					break;
				case "6":
					System.out.println("Get the id of the initialized user, if the user is not initialized returns -1");
					System.out.println("ID: " + u1.getId());
					System.out.println(" ");
					break;
				case "7":
					System.out.println("Get the ids of the reviewed items of the initialized user, if there's none returns []");
					System.out.println("ReviewsIds: " + u1.getReview());
					System.out.println(" ");
					break;
				case "8":
					System.out.println("Set the id the initialized user");
					System.out.println("Enter a valid id: (INTEGER)");
					idI = scan.nextInt();
					u1.setId(idI);
					command = scan.nextLine();
					System.out.println(" ");
					break;
				case "9":
					System.out.println("Add the id of a item to Reviews of the initialized user");
					System.out.println("Enter a valid item id: (INTEGER)");
					idItem = scan.nextInt();
					u1.addReview(idItem);
					command = scan.nextLine();
					System.out.println(" ");
					break;
				default:
					System.out.println(command + " is not a valid command.");
					break;
			}
			command = scan.nextLine();
		}
		scan.close();
	}
}
