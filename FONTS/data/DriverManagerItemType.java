package data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Driver for the Manager ItemType  class, it is used to try every method of the Manager ItemType class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * 
 * @author Daniel García Estévez
 */
public class DriverManagerItemType {
	
	/**
	 * Function to write the start message
	 */
	private static void iniMessage()
	{
		System.out.println("DriverManagerItemType execution is started!");
		System.out.println("Please type a valid command.");
		System.out.println("\t 1) -> getInstance()");
		System.out.println("\t 2) -> getNameFile()");
		System.out.println("\t 3) -> storeItemType()");
		System.out.println("\t 4) -> loadItemType()");
		System.out.println("\t 5) -> exit");
	}
	
	public static void main(String[] args) 
	{
		ManagerItemType managerItemType = ManagerItemType.getInstance();
		Scanner in = new Scanner(System.in);
		iniMessage();
		
		String command = in.nextLine();
		
		while (!command.equals("5")) {
			
			switch (command) {
				case "1":
					managerItemType = ManagerItemType.getInstance();
		 			System.out.println("The current instace of ManagerItemType has as name file: " + managerItemType.getNameFile());
					System.out.println("\n");
					break;
				case "2":
					System.out.println("The current name file of ManagerItem is: " + managerItemType.getNameFile());
					System.out.println("\n");
					break;
				case "3":
					ArrayList<String> objects = new ArrayList<String>();
					System.out.println("Please specify the name of the ItemType.\nFor example: Movies");
					String type = in.nextLine();
					String object = type;
					System.out.println("Please specify the number of attributes that this type of Item has.");
		 			Integer n = Integer.parseInt(in.nextLine());
					System.out.println("Please specify the name of the attributes, one for each line.\nFor example:\nadult\nbudget");
					for (int i = 0; i < n; ++i) object += "¬" + in.nextLine();
		 			objects.add(object);
		 			managerItemType.storeItemType(type, objects);
		 			objects = managerItemType.loadItemType(type);
					System.out.println("These are the Items that have been saved: ");
		 			if (objects != null) for (int i = 0; i < objects.size(); ++i) System.out.println(objects.get(i));
					System.out.println("\n");
					break;
				case "4":
					System.out.println("Please specify the name of the ItemType.");
					type = in.nextLine();
					ArrayList<String> list = managerItemType.loadItemType(type);
					System.out.println("This is what is stored in the file: ");
					for (int i = 0; i < list.size(); ++i) System.out.println(list.get(i));
					System.out.println("\n");
					break;
				default:
					System.out.println(command + " is not a valid command.");
					System.out.println("\n");
					break;
			}
			
			command = in.nextLine();
		}
		System.out.println("DriverManagerItemTypeexecution is finished!");
		in.close();
	}
}
