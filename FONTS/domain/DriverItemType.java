package domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Driver for the ItemType class, it is used to try every method of the ItemType class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * 
 * @author Eloi Balaer Morales
 *
 */
public class DriverItemType {
	 public static void main(String[] args) {
		 ItemType it1 = new ItemType();
		 Integer n;
		 String nameTypeI;
		 ArrayList<String> Val;
		 
		 System.out.println("Welcome to the Driver menu of ItemType!");
		 System.out.println("Write the number of the option you want, or exit if you want to terminate the execution");
		 
		 System.out.println("\t 1 -> ItemType()");
		 System.out.println("\t 2 -> ItemType((String nametype, ArrayList<String> attrib)");
		 System.out.println("\t 3 -> ItemType(String data)");
		 System.out.println("\t 4 -> String toString()");
		 System.out.println("\t 5 -> String getNameType()");
		 System.out.println("\t 6 -> ArrayList<String> getNameAttributes()");
		 System.out.println("\t 7 -> void setNameType(String nameType)");
		 System.out.println("\t 8 -> void setNameAttributes(ArrayList<String> nameattributes)");
		 System.out.println("\t exit -> close the Driver menu");
		 
		 Scanner scan = new Scanner(System.in);
	
		String command = scan.nextLine(); 
		while (!command.equals("exit")) {
			switch (command) {
				case "1":
					System.out.println("Constructor without attributes");
					it1 = new ItemType();
			 		System.out.println("nameType: "+ it1.getNameType());
			 		System.out.println("nameAttributes: " + it1.getNameAttributes());
			 		System.out.println(" ");
					break;
					
				case "2":
					System.out.println("Constructor with nameType and attributes");
					System.out.println("Please, introduce your ItemType nameType (String)");
			 		nameTypeI = scan.nextLine();
			 		System.out.println("Give the number of items to insert (INTEGER)");
					n = scan.nextInt();
					command = scan.nextLine();
					System.out.println("Please, introduce the nameTypes of the items reviewed by the ItemType (String)");
					Val = new ArrayList<String>();
					for(int i=0;i<n;++i) Val.add(scan.nextLine());
					it1 = new ItemType(nameTypeI, Val);
					System.out.println("nameType: "+ it1.getNameType());
			 		System.out.println("nameAttributes: " + it1.getNameAttributes());
			 		System.out.println(" ");
			 		//command = scan.nextLine();
					break;
				case "3":
					System.out.println("Constructor with a string given by the data domain.");
					System.out.println("Please, introduce your ItemType nameType (String)");
			 		nameTypeI = scan.nextLine();
			 		String x = (nameTypeI.toString());
			 		System.out.println("Please enter the number of attributes that this type of item has. (INTEGER)");
					n = scan.nextInt();
					command = scan.nextLine();
					System.out.println("Please, introduce the name of the atributtes (String)");
					for(int i=0;i<n;++i) x += ("¬" + scan.nextLine());
					it1 = new ItemType(x);
					System.out.println("nameType: "+ it1.getNameType());
			 		System.out.println("nameAttributes: " + it1.getNameAttributes());
			 		System.out.println(" ");
					
					
					break;
				case "4":
					System.out.println("Get the actual ItemType on string format");
					System.out.println(it1.toString());
					System.out.println(" ");
					break;
				case "5":
					System.out.println("Get the nameType of the initialized ItemType");
					System.out.println("nameType: " + it1.getNameType());
					System.out.println(" ");
					break;
				case "6":
					System.out.println("Get the nameAttributes of the set in the  initialized ItemType, if there's none returns []");
					System.out.println("nameAttributes: " + it1.getNameAttributes());
					System.out.println(" ");
					break;
				case "7":
					System.out.println("Set the nameType of the initialized ItemType");
					System.out.println("Enter a valid nameType: (String)");
					nameTypeI = scan.nextLine();
					it1.setNameType(nameTypeI);
					System.out.println(" ");
					break;
				case "8":
					System.out.println("Set the nameAttributes set of a itemType to the one of the initialized ItemType");
					System.out.println("Give the number of attributes to insert (INTEGER)");
					Val = new ArrayList<String>();
					n = scan.nextInt();
					command = scan.nextLine();
					for(int i = 0; i<n; ++i) {
						System.out.println("Enter a valid attributeName of nameAttribute: (String)");
						nameTypeI = scan.nextLine();
						Val.add(nameTypeI);
					}
					it1.setNameAttributes(Val);
					System.out.println(" ");
					break;
				default:
					System.out.println(command + " is not a itemType command.");
					break;
			}
			command = scan.nextLine();
		}
		scan.close();
	}
}
