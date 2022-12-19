package domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class DriverRecommendation.
 * 
 * Driver for the class Recommendation. It provides an wasy way to test the class and all its methods.
 * 
 * There can only exist one instance of DriverRecommendation .
 * 
 * @author Guillem González Valdivia
 *
 */
public class DriverRecommendation{
	
	/**
	 * Main method.
	 * 
	 * @param args Arguments sent by parameter from the console
	 * 
	 * It provides an easy way to explore the class Recommendation and to test its functionalities
	 */
	public static void main(String[] args) {
		
		Recommendation R1 = new Recommendation();
		Recommendation R2 = new Recommendation();
		Recommendation R3 = new Recommendation();
		Integer id = -1;
		ArrayList<Integer> items = new ArrayList<>();
		String data = "";
		Integer n = -1;

		System.out.println("Welcome to the Driver menu of Recommendation!");
		System.out.println("Write the number of the option you want, or exit if you want to terminate the execution");

		System.out.println("\t 1 -> Void constructor.");
		System.out.println("\t 2 -> Constructor with given id.");
		System.out.println("\t 3 -> Constructor with given id and items.");
		System.out.println("\t 4 -> Constructor with a string given by the data domain. Please insert the string, separated by '¬'");
		System.out.println("\t 5 -> Constructor of the string of the object to be given to the data layer.");
		System.out.println("\t 6 -> Getter of the identifier of the recommendation.");
		System.out.println("\t 7 -> Getter of the user of the recommendation.");
		System.out.println("\t 8 -> Getter of the items of the recommendation.");
		System.out.println("\t 9 -> Getter of the DCG of the recommendation.");
		System.out.println("\t 10 -> Setter of the id of the recommendation.");
		System.out.println("\t 11 -> Setter of the user of the recommendation.");
		System.out.println("\t 12 -> Setter of the DCG of the recommendation.");
		System.out.println("\t 13 -> Setter of the items of the recommendation.");
		System.out.println("\t 14 -> Adder of items.");
		System.out.println("\t 15 -> Intersection of two recommendations.");
		System.out.println("\t 16 -> Union of two recommendations.");
		System.out.println("\t -1 -> Exit");
		
		String command;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please give the next command.");
		command = scan.nextLine();
		while(!command.equals("-1")) {
			switch(command) {
			case "1":
				System.out.println("Void constructor.");
				R1 = new Recommendation();
				System.out.println("ID: " + R1.getId() + ", DCG: " + R1.getDCG() + ", items:");
				for(int i=0;i<R1.getItems().size();++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				System.out.println("\n");
				break;
			case "2":
				System.out.println("Constructor with given id.");
				id = scan.nextInt();
				R1 = new Recommendation(id);
				System.out.println("ID: " + R1.getId() + ", DCG: " + R1.getDCG() + ", items:");
				for(int i=0;i<R1.getItems().size();++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				scan.nextLine();
				System.out.println("\n");
				break;
			case "3":
				System.out.println("Constructor with given id and items.");
				id = scan.nextInt();
				items = new ArrayList<>();
				System.out.println("Give the number of items to insert");
				n = scan.nextInt();
				for(int i=0;i<n;++i) {
					Integer it =  scan.nextInt();
					items.add(it);
				}
				R1 = new Recommendation(id, items);
				System.out.println("ID: " + R1.getId() + ", DCG: " + R1.getDCG() + ", items:");
				for(int i=0;i<n;++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				scan.nextLine();
				System.out.println("\n");
				break;
			case "4":
				System.out.println("Constructor with a string given by the data domain. Please insert the id:");
				data = scan.nextLine();
				System.out.println("Now insert the DCG of the recommendation:");
				data = data + '¬' + scan.nextLine();
				System.out.println("Finally, insert the items of the recommendation. When you want to end, type '-1':");
				String info = scan.nextLine();
				while(!info.equals("-1")) {
					data = data + '¬' + info;
					info = scan.nextLine();
				}
				R1 = new Recommendation(data);
				System.out.print("ID: " + R1.getId() + ", DCG: " + R1.getDCG() + ", items: " + R1.getItems());
				System.out.println("\n");
				break;
			case "5":
				System.out.println("Constructor of the string of the object to be given to the data layer.");
				data = R1.toString();
				System.out.println(data);
				System.out.println("\n");
				break;
			case "6":
				System.out.println("Getter of the identifier of the recommendation.");
				System.out.println("Identifier: " + R1.getId());
				System.out.println("\n");
				break;
			case "7":
				System.out.println("Getter of the user of the recommendation.");
				System.out.println("User: " + R1.getUser());
				System.out.println("\n");
				break;
			case "8":
				System.out.println("Getter of the items of the recommendation.");
				System.out.println("Items:");
				for(int i=0;i<R1.getItems().size();++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				System.out.println("\n");
				break;
			case "9":
				System.out.println("Getter of the DCG of the recommendation.");
				System.out.println("DCG: " + R1.getDCG());
				System.out.println("\n");
				break;
			case "10":
				R1.setId(-1);
				System.out.println("Setter of the id of the recommendation. Give the id:");
				R1.setId(scan.nextInt());
				System.out.println("Id: " + R1.getId());
				scan.nextLine();
				System.out.println("\n");
				break;
			case "11":
				R1.setId(-1);
				System.out.println("Setter of the user of the recommendation. Give the user:");
				R1.setUser(scan.nextInt());
				System.out.println("User: " + R1.getUser());
				scan.nextLine();
				System.out.println("\n");
				break;
			case "12":
				R1.setDCG(0.0);
				System.out.println("Setter of the DCG of the recommendation. Give the DCG with a comma (,):");
				R1.setDCG(scan.nextDouble());
				System.out.println("DCG: " + R1.getDCG());
				scan.nextLine();
				System.out.println("\n");
				break;
			case "13":
				R1.setItems(new ArrayList<Integer>());
				System.out.println("Setter of the items of the recommendation. Give the number of items:");
				n = scan.nextInt();
				System.out.println("Give the items of the recommendation:");
				for(int i=0;i<n;++i) {R1.addItem(scan.nextInt());}
				for(int i=0;i<R1.getItems().size();++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				scan.nextLine();
				System.out.println("\n");
				break;
			case "14":
				System.out.println("Adder of items. Let's see the previous items given:");
				for(int i=0;i<R1.getItems().size();++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				System.out.println("Give the new item to give:");
				R1.addItem(scan.nextInt());
				System.out.println("Now, the items of the recommendation are:");
				for(int i=0;i<R1.getItems().size();++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				scan.nextLine();
				System.out.println("\n");
				break;
			case "15":
				System.out.println("We give first the constructor for the first recommendation, then the second:");
				System.out.println("First constructor with given id and items. Give the id:");
				id = scan.nextInt();
				items = new ArrayList<>();
				System.out.println("Give the number of items to insert:");
				n = scan.nextInt();
				for(int i=0;i<n;++i) items.add(scan.nextInt());
				R1 = new Recommendation(id, items);
				System.out.println("ID: " + R1.getId() + ", DCG: " + R1.getDCG() + ", items:");
				for(int i=0;i<R1.getItems().size();++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				System.out.println("Second constructor with given id and items. Give the id:");
				id = scan.nextInt();
				items = new ArrayList<>();
				System.out.println("Give the number of items to insert:");
				n = scan.nextInt();
				for(int i=0;i<n;++i) items.add(scan.nextInt());
				R2 = new Recommendation(id, items);
				System.out.println("ID: " + R2.getId() + ", DCG: " + R2.getDCG() + ", items:");
				for(int i=0;i<R2.getItems().size();++i) {
					System.out.println(" " + R2.getItems().get(i));
				}
				
				R3 = Recommendation.intersection(R1, R2);
				System.out.println("The intersection is:");
				System.out.println("ID: " + R3.getId() + ", DCG: " + R3.getDCG() + ", items:");
				for(int i=0;i<R3.getItems().size();++i) {
					System.out.println(" " + R3.getItems().get(i));
				}
				scan.nextLine();
				System.out.println("\n");
				break;
			case "16":
				System.out.println("We give first the constructor for the first recommendation, then the second:");
				System.out.println("First constructor with given id and items. Give the id:");
				id = scan.nextInt();
				items = new ArrayList<>();
				System.out.println("Give the number of items to insert:");
				n = scan.nextInt();
				for(int i=0;i<n;++i) items.add(scan.nextInt());
				R1 = new Recommendation(id, items);
				System.out.println("ID: " + R1.getId() + ", DCG: " + R1.getDCG() + ", items:");
				for(int i=0;i<R1.getItems().size();++i) {
					System.out.println(" " + R1.getItems().get(i));
				}
				System.out.println("Second constructor with given id and items. Give the id:");
				id = scan.nextInt();
				items = new ArrayList<>();
				System.out.println("Give the number of items to insert:");
				n = scan.nextInt();
				for(int i=0;i<n;++i) items.add(scan.nextInt());
				R2 = new Recommendation(id, items);
				System.out.println("ID: " + R2.getId() + ", DCG: " + R2.getDCG() + ", items:");
				for(int i=0;i<R2.getItems().size();++i) {
					System.out.println(" " + R2.getItems().get(i));
				}
				
				R3 = Recommendation.union(R1, R2);
				System.out.println("The intersection is:");
				System.out.println("ID: " + R3.getId() + ", DCG: " + R3.getDCG() + ", items:");
				for(int i=0;i<R3.getItems().size();++i) {
					System.out.println(" " + R3.getItems().get(i));
				}
				scan.nextLine();
				System.out.println("\n");
				break;
			default:
				System.out.println("Something went wrong. Please insert the command againg.");
			}
			System.out.println("Please give the next command.");
			command = scan.nextLine();
		}
		scan.close();
	}
}