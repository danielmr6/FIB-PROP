package data;


import java.util.ArrayList;
import java.util.Scanner;
/**
 * This driver is in charge of checking that the ManagerReview class and all its methods work
 * @author Daniel Morón Roces
 */
public class DriverManagerReview {
	  public static void main(String[] args) {
		  
		  ManagerReview mV = ManagerReview.getInstance();
		  ArrayList<String> Reviews = new ArrayList<>();
		  Scanner scan = new Scanner(System.in);
		  String filename;
		  System.out.println("Welcome to the Driver menu of ManagerReview!");
		  System.out.println("\t 1) -> getInstance(): ManagerReview");
		  System.out.println("\t 2) -> loadReview(String filename): ArrayList<String>");
		  System.out.println("\t 3) -> storeReview(String filename, ArrayList<String>obj)");
		  System.out.println("\t 4) -> getFileName(): String");
		  System.out.println("\t 5) -> exit");
		  
		  
		  String command = scan.nextLine();  
		  while(!command.equals("5")) {
			  switch(command) {

			     case "1":
		 			mV = ManagerReview.getInstance();
		 			System.out.print("The current name file of ManagerReview is: ");
		 			System.out.println(mV.getFileName());
					System.out.println("\n");
					break;
			  	
			  	case "2":
			  		System.out.println("Please, specify the type of Reviews you want to load");
			  		filename = scan.nextLine();
			  		Reviews = mV.loadReview("Review" + filename);
			  		for(int i = 0; i < Reviews.size(); ++i)  System.out.println(Reviews.get(i));
					System.out.println("\n");
					break;
			  	
			  	case "3":
				  	Reviews.clear();
			  		System.out.println("Please, specify the type of Reviews you want to store.");
					String type = scan.nextLine();
					System.out.println("Please, specify the number of Reviews that you want to store.");
					Integer n = scan.nextInt();
					for (int i = 0; i < n; ++i) {
						System.out.println("Please, specify the User Id");
						Integer usid = scan.nextInt();
						System.out.println("Please, specify the Item Id");
						Integer itid = scan.nextInt();
						System.out.println("Please, specify the value");
						Double v = scan.nextDouble();
						Reviews.add(usid + "¬" + itid + "¬" + v );
					}
					mV.storeReview(type, Reviews);
					Reviews = mV.loadReview(type);
		 			if (Reviews != null) System.out.println(Reviews);
					System.out.println("\n");
					scan.nextLine();
					break;
			  		
			  	case "4":
			  		System.out.println(mV.getFileName());
					System.out.println("\n");
					break;
			  		
				default:
		 			System.out.println(command + " is not a valid command.");
					System.out.println("\n");
					break;
			  }
			  command = scan.nextLine();
		  }
		  System.out.println("The DriverManagerReview execution is finished, thanks for your time!");
		  scan.close();
	  }
}
