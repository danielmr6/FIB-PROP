package domain;

import java.util.Scanner;

/**
 * The Driver for the Review class, it is used to try every method of the Review class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * 
 * @author Daniel Morón Roces
 */
public class DriverReview {
	 public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
		 Review v1 = new Review();
		 Review v2 = new Review();
		 Review v3 = new Review();
		 int idus, idit;
		 double val;
		 
		 System.out.println("Welcome to the Driver menu of Review!");
 
		 //Constructors
		 System.out.println("\t 1) -> Review()");
		 System.out.println("\t 2)-> Review(Integer idItm, Integer idUsr)");
		 System.out.println("\t 3)-> Review(Double value, Integer idItm, Integer idUsr)");
		 System.out.println("\t 4) -> Review(String val)");
	
		 //Getters and Setters
		 System.out.println("\t 5) -> Integer getUser()");
		 System.out.println("\t 6) -> void setUser(Integer idu)");
		 System.out.println("\t 7) -> Integer getItem()");
		 System.out.println("\t 8) -> void setItem(Integer idIt)");
		 System.out.println("\t 9) -> Double getReview()");
		 System.out.println("\t 10) -> void setReview(Double value)");
		 
		 //Others and exit
		 System.out.println("\t 11) -> String toString()");
		 System.out.println("\t 12) -> boolean compareCr(Review v)");
		 System.out.println("\t 13) -> Exit and close the Driver menu");
		 
		 
		 
		 System.out.println("Please, choose an option with a new command");
		 String command = scan.nextLine(); 
		 while(!command.equals("13")) {
			 switch(command) {
			 	case "1":
			 		v1 = new Review();
			 		System.out.println("Constructor without attributes: Review v1");
			 		
			 		System.out.println("Review v1");
			 		System.out.println("UserID: " + v1.getUser());
			 		System.out.println("ItemID: " + v1.getItem());
			 		System.out.println("Valuation: " + v1.getValuation());	
					System.out.println("\n");
			 		break;
			 		
			 	case "2":
			 		System.out.println("Please, introduce your user id");
			 		idus = scan.nextInt();
			 		
					System.out.println("Perfect, now introduce the id for the Item");
			 		idit = scan.nextInt();
			 		
			 		v1 = new Review(idit, idus);
			 		
			 		System.out.println("Review v1");
			 		System.out.println("UserID: " + v1.getUser());
			 		System.out.println("ItemID: " + v1.getItem());
			 		System.out.println("Valuation: " + v1.getValuation());	
			 		command = scan.nextLine();
			 		System.out.println("\n");
			 		break;
			 		
			 	case "3":
			 		System.out.println("Constructor with all of the attributes: Review v2");
			 
			 		System.out.println("Please, introduce your user id");
			 		idus = scan.nextInt();
			 		
					System.out.println("Perfect, now introduce the id for the Item");
			 		idit = scan.nextInt();
			 		
			 		System.out.println("To conclude, introduce the value with comma");
			 		val = scan.nextDouble();
			 		
			 		v2 = new Review(val,idit,idus);
			 		
			 		System.out.println("Review v2");
			 		System.out.println("UserID: " + v2.getUser());
			 		System.out.println("ItemID: " + v2.getItem());
			 		System.out.println("Valuation: " + v2.getValuation());	
			 		command = scan.nextLine();
			 		System.out.println("\n");
			 		break;
			 		
			 	case "4": 
			 		System.out.println("Constructor with a string: Review v3");
			 		System.out.println("Please, introduce UserID");
			 		Integer uId = scan.nextInt();
			 		System.out.println("Please, introduce ItemID");
			 		Integer itId = scan.nextInt();
			 		System.out.println("Please, introduce Valuation");
			 		Double v = scan.nextDouble();
			 		String x = uId + "¬" + itId + "¬" + v;
			 		
			 		v3 = new Review(x);
			 		
			 		System.out.println("Review v3");
					System.out.println("UserID: " + v3.getUser());
			 		System.out.println("ItemID: " + v3.getItem());
			 		System.out.println("Valuation: " + v3.getValuation());	
			 		command = scan.nextLine();
			 		System.out.println("\n");
			 		break;
			 	
			 	case "5": 
			 		
			 		System.out.println("Review v2");
			 		System.out.println("UserID: " + v2.getUser());
			 		System.out.println("\n");
			 		break;
			 		
			 	case "6": 
			 		System.out.println("Please, introduce your new user id for Review v2");
			 		idus = scan.nextInt();
			 		v2.setUser(idus);
			 		System.out.println("Review v2");
			 		System.out.println("UserID: " + v2.getUser());
			 		command = scan.nextLine();
			 		System.out.println("\n");
			 		break;
			 		
			 	case "7": 
			 		System.out.println("Review v2");
			 		System.out.println("ItemID: " + v2.getItem());	
			 		System.out.println("\n");
			 		break;
			 		
			 	case "8":
			 		System.out.println("Please, introduce the new item id for Review v2");
			 		idit = scan.nextInt();
			 		v2.setItem(idit);
			 		System.out.println("Review v2");
			 		System.out.println("ItemID: " + v2.getItem());
			 		command = scan.nextLine();
			 		System.out.println("\n");
			 		break;
			 		
			 	case "9": 
			 		System.out.println("Review v2");
			 		System.out.println("Valuation: " + v2.getValuation());	
			 		System.out.println("\n");
			 		break;
			 		
			 	case "10": 
			 		System.out.println("Please, introduce the new value of your opinion for Review v2");
			 		val = scan.nextDouble();
			 		v2.setValuation(val);
			 		System.out.println("Review v2");
			 		System.out.println("Valuation: " + v2.getValuation());		
			 		command = scan.nextLine();
			 		System.out.println("\n");
			 		break;
			 	
			 	case "11": 
			 		System.out.println(v2.toString());
			 		System.out.println("\n");
			 		break;
			 	
			 	case "12": 
			 		if(v2.compareCr(v3)) System.out.println("v2 goes before than v3");
			 		else System.out.println("v3 goes before than v2");
			 		System.out.println("\n");
			 		break;
				default:
					System.out.println(command + " is not a valid command.");
					System.out.println("\n");
					break;
			 	} 
			 command = scan.nextLine();
			 }
		 System.out.println("The DriverReview execution is finished, thanks for your time!");
	     scan.close();
	 }
}
