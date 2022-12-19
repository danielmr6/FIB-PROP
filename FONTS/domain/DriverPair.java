package domain;

import java.util.Scanner;

/**
 * This driver is responsible for checking that the Pair class is working correctly.
 * @author Daniel Morón Roces
 *
 */
public class DriverPair {
	 public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
		 Pair<Integer, Integer> p = new Pair<Integer, Integer>();
		  
		 System.out.println("Welcome to the Driver menu of Pair!");
 
		 //Constructors
		 System.out.println("\t 1) -> Pair()");
		 System.out.println("\t 2)-> Pair(T1 key, T2 value)");
		 
		 
		 //Getters and Setters
		 System.out.println("\t 3)-> T1 getKey()");
		 System.out.println("\t 4) -> T2 getValue() ");	
		 System.out.println("\t 5) -> void setKey(T1 key) ");
		 System.out.println("\t 6) -> void setValue(T2 value) ");
		 
		 //Others
		
		 System.out.println("\t 7) -> boolean equals(Pair<T1, T2> p)");
		 System.out.println("\t 8) -> Exit ");
		 
		 System.out.println("Please, choose an option with a new command");
		 String command = scan.nextLine(); 
		 while(!command.equals("8")) {
			 switch(command) {
			 	case "1":
			 		System.out.println("Constructor without attributes");
			 		System.out.println("The Key is " + p.getKey());
			 		System.out.println("The value is " + p.getValue());
			 		System.out.println("\n");
			 		break;
			 		
			 	case "2":
			 		System.out.println("Constructor with the 2 parameters");
			 		System.out.println("Give the key");
			 		Integer key = scan.nextInt();
			 		System.out.println("Give the id");
			 		Integer val = scan.nextInt();
			 		
			 		p = new Pair<Integer, Integer>(key, val);
			 		System.out.println("The Key is " + p.getKey());
			 		System.out.println("The value is " + p.getValue());
			 		command = scan.nextLine();
			 		System.out.println("\n");
			 		break;
			 		
			 	case "3":
			 		System.out.println("Get the information about the key of the Pair");
			 		System.out.println("The Key is " + p.getKey());
			 		System.out.println("\n");
			 		break;
			 		
			 	case "4": 
			 		System.out.println("Get the information about the value of the Pair");
			 		System.out.println("The value is " + p.getValue());
			 		System.out.println("\n");
			 		break;
			 	
			 	case "5":
			 		System.out.println("Give the new key that you want");
			 		Integer new_key = scan.nextInt();
			 		p.setKey(new_key);
			 		System.out.println("The Key is " + p.getKey());
			 		System.out.println("\n");
			 		command = scan.nextLine();
			 		break;
			 		
			 	case "6": 
			 		System.out.println("Give the new value that you want");
			 		Integer new_value = scan.nextInt();
			 		p.setValue(new_value);
			 		System.out.println("The value is " + p.getValue());
			 		System.out.println("\n");
			 		command = scan.nextLine();
			 		break;
			 	
			 		
			 	case "7":
			 		System.out.println("Give the key for the second pair");
			 		Integer newkey3 = scan.nextInt();
			 		System.out.println("Give the value for the second pair");
			 		Integer newvalue3 = scan.nextInt();
			 		Pair<Integer, Integer> p3 = new Pair<Integer, Integer>(newkey3,newvalue3);
			 		
			 		System.out.println(p.equals(p3));	
			 		command = scan.nextLine();
			 		System.out.println("\n");
			 		break;
	
			 		
				default:
					System.out.println(command + " is not a valid command.");
					System.out.println("\n");
					break;
			 	} 
			 System.out.println("Please, choose an option with a new command");
			 command = scan.nextLine();
			 }
		 System.out.println("The DriverPair execution is finished, thanks for your time!");
	     scan.close();
	 }
}
