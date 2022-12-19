package domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  The Driver for the ActiveUser class, it is used to try every method of the ActiveUser class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * @author Eloi Balaer Morales
 *
 */
public class DriverActiveUser {
	 public static void main(String[] args) {
		 ActiveUser u1 = new ActiveUser();
		 Integer idI, n, idItem, idR;
		 Boolean adm = false;
		 ArrayList<Integer> Val;
		 
		 System.out.println("Welcome to the Driver menu of ActiveUser!");
		 System.out.println("Write the number of the option you want, or exit if you want to terminate the execution");
		 
		 System.out.println("\t 1 -> ActiveUser()");
		 System.out.println("\t 2 -> ActiveUser(Integer id)");
		 System.out.println("\t 3 -> ActiveUser(Integer id, ArrayList<Integer> Reviews)");
		 System.out.println("\t 4 -> ActiveUser(Integer id, Integer id_r, boolean admin, ArrayList<Integer> Reviews)");
		 System.out.println("\t 5 -> ActiveUser(String data)");
		 System.out.println("\t 6 -> String toString()");
		 System.out.println("\t 7 -> Integer getId()");
		 System.out.println("\t 8 -> Integer getId_r()");
		 System.out.println("\t 9 -> Integer getAdmin()");
		 System.out.println("\t 10 -> ArrayList<Integer> getReview()");
		 System.out.println("\t 11 -> void setId(Integer id)");
		 System.out.println("\t 12 -> void setId_r(Integer id_r)");
		 System.out.println("\t 13 -> void setAdmin(Boolean adm)");
		 System.out.println("\t 14 -> void addReview(Integer id_item)");
		 System.out.println("\t exit -> close the Driver menu");
		 
		 Scanner scan = new Scanner(System.in);
	
		String command = scan.nextLine(); 
		while (!command.equals("exit")) {
			switch (command) {
				case "1":
					System.out.println("Constructor without attributes");
					u1 = new ActiveUser();
			 		System.out.println("Id: "+ u1.getId());
			 		System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		System.out.println(" ");
					break;
					
				case "2":
					System.out.println("Constructor with Id");
					System.out.println("Please, introduce your ActiveUser id (INTEGER)");
			 		idI = scan.nextInt();
					u1 = new ActiveUser(idI);
					System.out.println("Id: "+ u1.getId());
					System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		command = scan.nextLine();
			 		System.out.println(" ");
			 		break;
				case "3":
					System.out.println("Constructor with Id and ReviewsIds");
					System.out.println("Please, introduce your user id (INTEGER)");
			 		idI = scan.nextInt();
			 		System.out.println("Give the number of items to insert (INTEGER)");
					n = scan.nextInt();
					System.out.println("Please, introduce the ids of the reviewed items (INTEGERS)");
					Val = new ArrayList<Integer>();
					for(int i=0;i<n;++i) Val.add(scan.nextInt());
					u1 = new ActiveUser(idI, Val);
					System.out.println("Id: "+ u1.getId());
					System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		command = scan.nextLine();
			 		System.out.println(" ");
			 		break;
				case "4":
					System.out.println("Constructor with Id, Id_Recomendation, admin and ReviewsIds");
					System.out.println("Please, introduce your user id (INTEGER)");
			 		idI = scan.nextInt();
			 		System.out.println("Please, introduce your Recommendation id (INTEGER)");
			 		idR = scan.nextInt();
			 		System.out.println("Please, introduce if the ActiveUser is an admin (true/false)");
			 		adm = scan.nextBoolean();
			 		System.out.println("Give the number of items to insert (INTEGER)");
					n = scan.nextInt();
					System.out.println("Please, introduce the ids of the reviewed items (INTEGERS)");
					Val = new ArrayList<Integer>();
					for(int i=0;i<n;++i) Val.add(scan.nextInt());
					u1 = new ActiveUser(idI, idR, adm, Val);
					System.out.println("Id: "+ u1.getId());
					System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		command = scan.nextLine();
			 		System.out.println(" ");
			 		break;
				case "5":
					System.out.println("Constructor with STRING (Id, Id_Recomendation, admin and ReviewsIds)");
					System.out.println("Please, introduce your user id (INTEGER)");
			 		idI = scan.nextInt();
			 		System.out.println("Please, introduce your Recommendation id (INTEGER)");
			 		idR = scan.nextInt();
			 		System.out.println("Please, introduce if the ActiveUser is an admin (true/false)");
			 		adm = scan.nextBoolean();
			 		String x = (idI + "¬" + idR + "¬" + adm);
			 		System.out.println("Give the number of items to insert (INTEGER)");
					n = scan.nextInt();
					System.out.println("Please, introduce the ids of the reviewed items (INTEGERS)");
					for(int i=0;i<n;++i) x += ("¬" + scan.nextInt());
					
					u1 = new ActiveUser(x);
					System.out.println("Id: "+ u1.getId());
					System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
			 		System.out.println("ReviewsIds: " + u1.getReview());
			 		command = scan.nextLine();
			 		System.out.println(" ");
					break;
				case "6":
					System.out.println("Get the actual ActiveUser on string format, if the user is not initialized returns -1");
					System.out.println(u1.toString());
					System.out.println(" ");
					break;
				case "7":
					System.out.println("Get the id of the initialized user, if the user is not initialized returns -1");
					System.out.println("ID: " + u1.getId());
					System.out.println(" ");
					break;
				case "8":
					System.out.println("Get the id of the recommendation of the initialized user, if it's not initialized returns -1");
					System.out.println("ID_R: " + u1.getId_r());
					System.out.println(" ");
					break;
				case "9":
					System.out.println("Get the boolean to know if the user is an Admin, if it's not initialized returns false");
					System.out.println("ADMIN: " + u1.getAdmin());
					System.out.println(" ");
					break;
				case "10":
					System.out.println("Get the ids of the reviewed items of the initialized user, if there's none returns []");
					System.out.println("ReviewsIds: " + u1.getReview());
					System.out.println(" ");
					break;
				case "11":
					System.out.println("Set a new id for the ActiveUser");
					System.out.println("Enter a valid id: (INTEGER)");
					idI = scan.nextInt();
					u1.setId(idI);
					System.out.println("ID: " + u1.getId());
					System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
					System.out.println("ReviewsIds: " + u1.getReview());
					command = scan.nextLine();
					System.out.println(" ");
					break;
				case "12":
					System.out.println("Set a new id recomendation for the ActiveUser");
					System.out.println("Enter a valid id_r: (INTEGER)");
					idR = scan.nextInt();
					u1.setId_r(idR);
					System.out.println("ID: " + u1.getId());
					System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
					System.out.println("ReviewsIds: " + u1.getReview());
					command = scan.nextLine();
					System.out.println(" ");
					break;
				case "13":
					System.out.println("Set a new Admin for the ActiveUser");
					System.out.println("Enter a valid state for Admin: (true/false)");
					adm = scan.nextBoolean();
					u1.setAdmin(adm);
					System.out.println("ID: " + u1.getId());
					System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
					System.out.println("ReviewsIds: " + u1.getReview());
					command = scan.nextLine();
					System.out.println(" ");
					break;
				case "14":
					System.out.println("Add the id of a item to Reviews of the initialized user");
					System.out.println("Enter a valid item id: (INTEGER)");
					idItem = scan.nextInt();
					u1.addReview(idItem);
					System.out.println("ID: " + u1.getId());
					System.out.println("Id Recomendation: "+ u1.getId_r());
			 		System.out.println("Admin: "+ u1.getAdmin());
					System.out.println("ReviewsIds: " + u1.getReview());
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
