package data;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * 
 * Driver for the controller data layer. It provides an easy way to test the class and all its methods.
 * @author Daniel Morón Roces
 *
 */
public class DriverCtrlDataLayer {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		CtrlDataLayer cd = CtrlDataLayer.getInstance();
		String type;
		ArrayList<String> list = new ArrayList<String>();
		
		 System.out.println("Welcome to the Driver menu of CtrlDataLayer!");
		 System.out.println("Choose an option");
		 
		 System.out.println("\t 1) -> loadDataInfo(String type):  ArrayList<String>");
		 System.out.println("\t 2) -> storeDataInfo(String type, ArrayList<String> obj)");
		 System.out.println("\t 3) -> Exit>");
		 String command = scan.nextLine();  
		while(!command.equals("3")) {
			switch(command) {
			
				case "1": 
					System.out.println("Please, specify the name of the file  you want to load: STRING");
					type = scan.nextLine();
					list = cd.loadDataInfo(type);
					for(int i = 0; i < list.size(); ++i) {
						System.out.println(list.get(i));
					}
					System.out.println("\n");
					break;
				case "2":	
					System.out.println("Please, specify the name of the file you want to store: STRING");
					type = scan.nextLine();
					System.out.println("Please, specify the number of objects you want to store: INTEGER");
					Integer numberObjects = scan.nextInt();
					command = scan.nextLine();
					ArrayList<String> newO = new ArrayList<String>();
					for(int i=0; i < numberObjects; ++i) {
						list.add(scan.nextLine());
					}
					cd.storeDataInfo(type, list);
					newO = cd.loadDataInfo(type);
		 			if (newO != null) for (int i = 0; i < newO.size(); ++i) System.out.println(newO.get(i));
					System.out.println("\n");
					break;
			}
			command = scan.nextLine();
		}	
		System.out.println("The DriverCtrlDataLayer execution is finished, thanks for your time!");
		scan.close();	
	}
}
