package data;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interactive driver to 
 * 
 * @author Daniel García Estévez
 *
 */
public class DriverManagerCSV {
	
	/**
	 * 
	 */
	private static void iniMessage()
	{
		System.out.println("DriverManagerCSV execution is started!");
		System.out.println("Please type a valid command.");
		System.out.println("\t 1) -> readItems()");
		System.out.println("\t 2) -> readRatings()");
		System.out.println("\t 3) -> readKnown()");
		System.out.println("\t 4) -> readUnknown()");
		System.out.println("\t 5) -> exit");
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ManagerCSV managerCSV = ManagerCSV.getInstance();
		iniMessage();
		 
		 Scanner in = new Scanner(System.in);
		 
		 String command = in.nextLine();
		 while (!command.equals("5")) {
			 switch (command) {
			 	case "1":
			 		System.out.println("Please specify the path to the file.\nFor example ../../../DATA/CSVFiles/Movies/items.csv");
			 		String path = in.nextLine();
			 		System.out.println("Please specify the type of the item.\nFor example Movies");
			 		String type = in.nextLine();
			 		managerCSV.readItems(path, type);
			 		System.out.println("Data was stored. Look at the corresponding file in /DATA/Items and /DATA/ItemType");
					System.out.println("\n");
					break;
			 	case "2":
			 		System.out.println("Please specify the path to the file.\nFor example ../../../DATA/CSVFiles/Movies/ratings.db.csv");
			 		path = in.nextLine();
			 		System.out.println("Please specify the type of the item.\nFor example Movies");
			 		type = in.nextLine();
			 		System.out.println("Now we have to specify in which column 1, 2 or 3 is the id of User: ");
			 		ArrayList<Integer> pos_col = new ArrayList<Integer>();
			 		pos_col.add(in.nextInt() - 1);
			 		System.out.println("The id of Item: ");
			 		pos_col.add(in.nextInt() - 1);
			 		System.out.println("And the value of the rating: ");
			 		pos_col.add(in.nextInt() - 1);
			 		System.out.println("The path specified is: " + path + "\nAnd type item is: " + type);
			 		System.out.println(pos_col);
			 		managerCSV.readRatings(path, type, pos_col);
			 		System.out.println("Data was stored. Look at the corresponding file in /DATA/User and /DATA/Review");
			 		in.nextLine();
					System.out.println("\n");
					break;
			 	case "3":
			 		System.out.println("Please specify the path to the file.\nFor example ../../../DATA/CSVFiles/Movies/ratings.test.known.csv");
			 		path = in.nextLine();
			 		System.out.println("Now we have to specify in which column 1, 2 or 3 is the id of User: ");
			 		pos_col = new ArrayList<Integer>();
			 		pos_col.add(in.nextInt() - 1);
			 		System.out.println("The id of Item: ");
			 		pos_col.add(in.nextInt() - 1);
			 		System.out.println("And the value of the rating: ");
			 		pos_col.add(in.nextInt() - 1);
					ArrayList<String> list = managerCSV.readKnown(path, pos_col);
					if (list != null) for (int i = 0; i < list.size(); ++i) System.out.println(list.get(i));
					System.out.println("\n");
					in.nextLine();
					break;
			 	case "4":
			 		System.out.println("Please specify the path to the file.\nFor example ../../../DATA/CSVFiles/Movies/ratings.test.unkonwn.csv");
			 		path = in.nextLine();
			 		System.out.println("Now we have to specify in which column 1, 2 or 3 is the id of User: ");
			 		pos_col = new ArrayList<Integer>();
			 		pos_col.add(in.nextInt() - 1);
			 		System.out.println("The id of Item: ");
			 		pos_col.add(in.nextInt() - 1);
			 		System.out.println("And the value of the rating: ");
			 		pos_col.add(in.nextInt() - 1);
			 		list = managerCSV.readUnknown(path, pos_col);
			 		if (list != null) for (int i = 0; i < list.size(); ++i) System.out.println(list.get(i));
					System.out.println("\n");
					in.nextLine();
					break;
			 	default:
			 		System.out.println(command + " is not a valid command.");
					System.out.println("\n");
					break;
			}
			 command = in.nextLine();
		 }
		 System.out.println("DriverManagerCSV execution is finished!");
		 in.close();
	 }
}
