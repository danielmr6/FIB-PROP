package data;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Driver for the controller ManagerRecommendation. It provides an easy way to test the class and all the methods.
 * @author Daniel Morón Roces
 *
 */
public class DriverManagerRecommendation {
  public static void main(String[] args) {
		  
		  ManagerRecommendation mR = ManagerRecommendation.getInstance();
		  Scanner scan = new Scanner(System.in);
		  ArrayList<String> recom = new ArrayList<>();
		  String id;
		  System.out.println("Welcome to the Driver menu of ManagerRecommendation!");
		  System.out.println("\t 1) -> loadRecommendation(): ArrayList<String>");
		  System.out.println("\t 2) -> storeRecommendation(String id, String obj)");
		  System.out.println("\t 3) -> exit)");
		  String command = scan.nextLine();  
		  while(!command.equals("3")) {
			  switch(command) {

			  	case "1":
			  		System.out.println("Specify the id of the active user: INTEGER number");
			  		id = scan.nextLine();
			  		recom = mR.loadRecommendation(id);
			  		for(int i = 0; i < recom.size(); ++i)  System.out.println(recom.get(i));
					System.out.println("\n");
					break;
			  	
			  	case "2":
			  	
			  		System.out.println("Please, specify the id of the active user: INTEGER number");
			  		String res = scan.nextLine();
			  		String idu = res;
			  		
			  		System.out.println("Please, specify the DCG with comma (,).");
			  		res += "¬" + scan.nextDouble();
			  		
			  		System.out.print("Please specify the number of Item objects that you wanna store: ");
			  		Integer n = scan.nextInt();
					
		 			System.out.println("Please, specify the Item ids that are recommended");
					for(int i = 0; i < n; ++i) {
						res += "¬";
						res += scan.nextInt();
					}
					mR.storeRecommendation(idu, res);
					recom = mR.loadRecommendation(idu);
		 			for (int i = 0; i < recom.size(); ++i) System.out.println(recom.get(i));
		 			command = scan.nextLine();
					System.out.println("\n");
					break;
			  	default:
			  		System.out.println(command + " is not a valid command.");
					System.out.println("\n");
					break;
			  }
			  command = scan.nextLine();
		  }
		  System.out.println("The DriverManagerRecommendation execution is finished, thanks for your time!");
		  scan.close();
	  }
}
