package domain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * The Driver for the Item class, it is used to try every method of the Item class.
 * 
 * The input is received by hand/tests on console, and the output is displayed on console.
 * 
 * @author Daniel García Estévez
 */
public class DriverItem {
	
	/**
	 * Function to write the start message
	 */
	private static void iniMessage()
	{
		System.out.println("DriverManagerCSV execution is started!");
		System.out.println("Please type a valid command.");
		
		System.out.println("\t 1) -> Item()");
		System.out.println("\t 2) -> Item(Integer id)");
		System.out.println("\t 3) -> Item(Integer id, String nameType)");
		System.out.println("\t 4) -> Item(String objectData)");
		System.out.println("\t 5) -> getId()");
		
		System.out.println("\t 6) -> getNameType()");
		System.out.println("\t 7) -> getAvarageReviewed()");
		System.out.println("\t 8) -> getRaterUsers()");
		System.out.println("\t 9) -> getAttributesNumerics()");
		System.out.println("\t 10) -> getAttributesBooleans()");
		
		System.out.println("\t 11) -> getAttributesCategorical()");
		System.out.println("\t 12) -> getAtrNumericWithName()");
		System.out.println("\t 13) -> getAtrBooleanWithName()");
		System.out.println("\t 14) -> getAtrCategoricalWithName()");
		System.out.println("\t 15) -> setId()");
		
		System.out.println("\t 16) -> setNameType()");
		System.out.println("\t 17) -> setAvarageReviews()");
		System.out.println("\t 18) -> setRaterUser()");
		System.out.println("\t 19) -> setAttributesNumerics()");
		System.out.println("\t 20) -> setAttributesBooleans()");
		
		System.out.println("\t 21) -> setAttributesCategorical()");
		System.out.println("\t 22) -> distance()");
		System.out.println("\t 23) -> toString()");
		
		System.out.println("\t 24) -> exit");
	}
	
	/**
	 * Function that prints the attributes of an Item object.
	 * @param i The Item that we wanna print.
	 */
	private static void printItem(Item i) 
	{
		System.out.println("Item id: " + i.getId().toString());
		System.out.println("Item type: " + i.getNameType());
		System.out.println("Item avarage reviewed: " + i.getAvarageReviewed().toString());
		System.out.print("Item Rater Users: ");
		System.out.println(i.getRaterUsers());
		System.out.print("Item attributes numerics: ");
		System.out.println(i.getAttributesNumerics());
		System.out.print("Item attributes booleans: ");
		System.out.println(i.getAttributesBooleans());
		System.out.print("Item attributes categorical: ");
		System.out.println(i.getAttributesCategorical());	
	}
	
	public static void main(String[] args) 
	{
		Item current_item = new Item();
		iniMessage();
		Item i1;
		Item i2;
		Scanner scan = new Scanner(System.in);
		String command = scan.nextLine();
		
		while (!command.equals("24")) {
			
			switch (command) {
				case "1":
					current_item = new Item();
					printItem(current_item);
					System.out.println("\n");
					break;
				case "2":
					System.out.println("Please specify the id of the new item.");
					Integer id = Integer.parseInt(scan.nextLine()); 
					current_item = new Item(id);
					printItem(current_item);
					System.out.println("\n");
					break;
				case "3":
					System.out.println("Please specify the id of the new item.");
					id = Integer.parseInt(scan.nextLine());
					System.out.println("Please specify the type of the new item.");
					String type = scan.nextLine();
					current_item = new Item(id, type);
					printItem(current_item);
					System.out.println("\n");
					break;
				case "4":
					System.out.println("Please enter the id of the item.\nFor example: 1256");
					String object = scan.nextLine();
					System.out.println("Please enter the name of the item type.\nFor example: Movies");
					object += "¬" + scan.nextLine();
					System.out.println("Please enter the number of attributes that do you wanna add.\nFor example: 2");
					Integer n = Integer.parseInt(scan.nextLine());
					System.out.println("Enter " + n + " pairs composed of the name of the attribute and its name for each line.\nFor example:\nnote\n6.9 \ntime \n120");
					for (int i = 0; i < n*2; ++i) object += "¬" + scan.nextLine();
					current_item = new Item(object);
					printItem(current_item);
					System.out.println("\n");
					break;
				case "5":
					System.out.println(current_item.getId());
					System.out.println("\n");
					break;
				case "6":
					System.out.println(current_item.getNameType());
					System.out.println("\n");
					break;
				case "7":
					System.out.println(current_item.getAvarageReviewed());
					System.out.println("\n");
					break;
				case "8":
					System.out.println(current_item.getRaterUsers());
					System.out.println("\n");
					break;
				case "9":
					System.out.println(current_item.getAttributesNumerics());
					System.out.println("\n");
					break;
				case "10":
					System.out.println(current_item.getAttributesBooleans());
					System.out.println("\n");
					break;
				case "11":
					System.out.println(current_item.getAttributesCategorical());
					System.out.println("\n");
					break;
				case "12":
					System.out.println("Please specify which numeric attribute do you wanna know it's values.");
					String key = scan.nextLine(); 
					System.out.println(current_item.getAtrNumericWithName(key));
					System.out.println("\n");
					break;
				case "13":
					System.out.println("Please specify which boolean attribute do you wanna know it's values.");
					key = scan.nextLine(); 
					System.out.println(current_item.getAtrBooleanWithName(key));
					System.out.println("\n");
					break;
				case "14":
					System.out.println("Please specify which categorical attribute do you wanna know it's values.");
					key = scan.nextLine(); 
					System.out.println(current_item.getAtrCategoricalWithName(key));
					System.out.println("\n");
					break;
				case "15":
					System.out.println("Please specify the new id for the current Item.");
					id = Integer.parseInt(scan.nextLine());
					current_item.setId(id);
					printItem(current_item);
					break;
				case "16":
					System.out.println("Please specify the new type for the current Item.");
					type = scan.nextLine();
					current_item.setNameType(type);
					printItem(current_item);
					System.out.println("\n");
					break;
				case "17":
					System.out.println("Please specify the new avarage for the current Item.");
					Double avarage = Double.parseDouble(scan.nextLine()); 
					current_item.setAvarageReviews(avarage);
					printItem(current_item);
					System.out.println("\n");
					break;
				case "18":
					System.out.println("Please specify the number of rater users who have evaluated the current item.");
					n = Integer.parseInt(scan.nextLine());
					System.out.println("Please enter " + n.toString() + " id of users who have evaluated the current item.");
					ArrayList<Integer> raterusers = new ArrayList<Integer>(); 
					for (int i = 0; i < n; ++i) {
						id = Integer.parseInt(scan.nextLine());
						raterusers.add(id);
					}
					current_item.setRaterUsers(raterusers);
					System.out.println(current_item.getRaterUsers());
					System.out.println("\n");
					break;
				case "19":
					System.out.println("Please enter the number of numeric attributes you want to add.");
					n = Integer.parseInt(scan.nextLine());
					System.out.println("Please enter " + n.toString() + " pairs composed of the name of the attribute with its value.");
					System.out.println("As for example:\nName_Atribute\nValue_of_atribute");
					String name;
					Double valueD; 
					TreeMap<String, Double> at_numeric = new TreeMap<String, Double>();
					for (int i = 0; i < n; ++i) {
						name = scan.nextLine();
						valueD = Double.parseDouble(scan.nextLine()); 
						at_numeric.put(name, valueD);
					}
					current_item.setAttributesNumerics(at_numeric);
					printItem(current_item);
					System.out.println("\n");
					break;
				case "20":
					System.out.println("Please enter the number of boolean attributes you want to add.");
					n = Integer.parseInt(scan.nextLine());
					System.out.println("Please enter " + n.toString() + " pairs composed of the name of the attribute with its value.");
					System.out.println("As for example:\nName_Atribute\nValue_of_atribute");
					Boolean valueB;
					TreeMap<String, Boolean> at_boolean = new TreeMap<String, Boolean>();
					for (int i = 0; i < n; ++i) {
						name = scan.nextLine();
						valueB = Boolean.parseBoolean(scan.nextLine()); 
						at_boolean.put(name, valueB);
					}
					current_item.setAttributesBooleans(at_boolean);
					printItem(current_item);
					break;
				case "21":
					System.out.println("Please enter the number of categorical attributes you want to add.");
					n = Integer.parseInt(scan.nextLine());
					System.out.println("Please enter " + n.toString() + " pairs composed of the name of the attribute with its value.");
					System.out.println("As for example:\nName_Atribute\nValue_of_atribute");
					String valueS;
					TreeMap<String, String> at_categorical = new TreeMap<String, String>();
					for (int i = 0; i < n; ++i) {
						name = scan.nextLine();
						valueS = scan.nextLine(); 
						at_categorical.put(name, valueS);
					}
					current_item.setAttributesCategorical(at_categorical);
					printItem(current_item);
					System.out.println("\n");
					break;
				case "22":
					System.out.println("Now we hace to add 2 items: ");
					i1 = i2 = new Item();
					for (int j = 0; j < 2; ++j) {
						System.out.println("Please enter the id of the item.\nFor example: 1256");
						object = scan.nextLine();
						System.out.println("Please enter the name of the item type.\nFor example: Movies");
						object += "¬" + scan.nextLine();
						System.out.println("Please enter the number of attributes that do you wanna add.\nFor example: 2");
						n = Integer.parseInt(scan.nextLine());
						System.out.println("Enter " + n + " pairs composed of the name of the attribute and its name for each line.\nFor example:\nnote\n6.9 \ntime \n120");
						for (int i = 0; i < n*2; ++i) object += "¬" + scan.nextLine();
						if (j == 0) i1 = new Item(object);
						else if (j == 1) i2 = new Item(object);
					}
					System.out.print("The distance between the first item and the second is: ");
					System.out.println(i1.distance(i2));
					System.out.println("\n");
					break;
				case "23":
					System.out.print("The current item has the following form if we pass it to a string: ");
					System.out.println(current_item.toString());
					System.out.println("\n");
					break;
				default:
					System.out.println(command + " is not a valid command.");
					break;
			}
			command = scan.nextLine();
		}
		System.out.println("DriverItem execution is finished!");
		scan.close();
	}
}
