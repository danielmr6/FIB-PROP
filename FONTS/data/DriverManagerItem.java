package data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Driver for the Manager Item class, it is used to try every method of the Manager Item class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * 
 * @author Daniel García Estévez
 *
 */
public class DriverManagerItem {
	
	/**
	 * Function to write the start message
	 */
	private static void iniMessage()
	{
		System.out.println("DriverItem execution is started!");
		System.out.println("Please type a valid command.");
		System.out.println("\t 1) -> getInstance()");
		System.out.println("\t 2) -> getNameFile()");
		System.out.println("\t 3) -> storeItem()");
		System.out.println("\t 4) -> loadItem()");
		System.out.println("\t 5) -> exit");
	}
	
	public static void main(String[] args) 
	{
		ManagerItem managerItem = ManagerItem.getInstance();
		Scanner in = new Scanner(System.in);
		iniMessage();
		 
		String command = in.nextLine();
		while (!command.equals("5")) {
		 
			switch (command) {
		 		case "1":
		 			managerItem = ManagerItem.getInstance();
		 			System.out.println("The current instace of ManagerItem has as name file: " + managerItem.getNameFile());
					System.out.println("\n");
					break;
		 		case "2":
		 			System.out.println("The current name file of ManagerItem is: " + managerItem.getNameFile());
					System.out.println("\n");
					break;
		 		case "3":
		 			System.out.println("Please specify which type of Items do you wanna store.\nFor example: Movies");
		 			String type = in.nextLine();
		 			System.out.print("Please specify the number of Item objects that you wanna store: ");
		 			Integer n = in.nextInt();
		 			in.nextLine();	//Necesario para evitar errores
		 			ArrayList<String> storeObj = new ArrayList<String>();
		 			for (int i = 0; i < n; ++i) {
						System.out.println("Please enter the id of the item.\nFor example: 1256");
						String object = in.nextLine() + "¬" + type;
						System.out.println("Please enter the number of attributes that do you wanna add.\nFor example: 2");
						Integer na = Integer.parseInt(in.nextLine());
						System.out.println("Enter " + n + " pairs composed of the name of the attribute and its name for each line.\nFor example:\nnote\n6.9 \ntime \n120");
						for (int j = 0; j < na*2; ++j) object += "¬" + in.nextLine();
		 				storeObj.add(object);
		 			}
		 			managerItem.storeItem("Item" + type, storeObj);
		 			storeObj = managerItem.loadItem("Item" + type);
					System.out.println("These are the Items that have been saved: ");
		 			if (storeObj != null) for (int i = 0; i < storeObj.size(); ++i) System.out.println(storeObj.get(i));
					System.out.println("\n");
					break;
		 		case "4":
		 			System.out.println("Please specify which type of Items do you wanna load.");
		 			type = in.nextLine();
		 			ArrayList<String> list = managerItem.loadItem("Item" + type);
					System.out.println("This is what is stored in the file: ");
		 			if (list != null) for (int i = 0; i < list.size(); ++i) System.out.println(list.get(i));
					System.out.println("\n");
					break;
		 		default:
		 			System.out.println(command + " is not a valid command.");
					System.out.println("\n");
					break;
			}
			command = in.nextLine();
		}
		System.out.println("DriverManagerItem execution is finished!");
		in.close();
	}
}
