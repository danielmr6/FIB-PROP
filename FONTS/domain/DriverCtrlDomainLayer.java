package domain;

import java.util.ArrayList;
import java.util.Scanner;
import data.ManagerCSV;

/**
 * Class DriverCtrlDomainLayer.
 * 
 * This class is used to test the controller of the domain layer. It creates instances of the controller, sets strategies, creates recommendations and
 * calculates the DCG.
 * 
 * The number of objects of this class is set to 0 (if no constructed) or 1 (if constructed).
 * 
 * @author Daniel García Estévez.
 */
public class DriverCtrlDomainLayer{
	
	public static void main(String[] args) {
		
		CtrlDomainLayer ctrlD = null;
		ManagerCSV manCSV = ManagerCSV.getInstance();

		// Cargamos los datos iniciales, para prevenir que los datos esten corruptos
		manCSV.readItems("../../../DATA/CSVFiles/Movies/items.csv", "Movies");
		manCSV.readItems("../../../DATA/CSVFiles/Series/items.csv", "Series");
		ArrayList<Integer> pos = new ArrayList<Integer>();
		pos.add(0);
		pos.add(1);
		pos.add(2);
		manCSV.readRatings("../../../DATA/CSVFiles/Movies/ratings.db.csv", "Movies", pos);
		pos = new ArrayList<Integer>();
		pos.add(2);
		pos.add(0);
		pos.add(1);
		manCSV.readRatings("../../../DATA/CSVFiles/Series/ratings.db.csv", "Series", pos);

		System.out.println("Welcome to the Driver menu of the controller of the domain layer!");
		System.out.println("Write the number of the option you want, or exit if you want to terminate the execution");

		System.out.println("\t 0 -> Void constructor.");
		System.out.println("\t 1 -> Void constructor with given item type, active user and algorithm.");
		System.out.println("\t 2 -> Data load.");
		System.out.println("\t 3 -> Change algorithm.");
		System.out.println("\t 4 -> Recommend and DCG.");
		System.out.println("\t -1 -> close the driver menu.");

		String command;
		Scanner scan = new Scanner(System.in);
		command = scan.nextLine();

		while(!command.equals("-1")) {
			switch (command){
			case "0":
				System.out.println("Void constructor.");
				ctrlD = new CtrlDomainLayer();		//Types of items, id of active user, strategy
				System.out.println("\n");
				break;
			case "1":
				System.out.println("Void constructor with given item type, active user and algorithm");
				ctrlD = new CtrlDomainLayer("Movies", 50, -1);
				System.out.println("\n");
				break;
			case "2":
				System.out.println("Data load.");
				ctrlD.dataLoad();
				System.out.println("\n");
				break;
			case "3":
				System.out.println("Change algorithm. Please write the algorithm you want to use");
				System.out.println("(0 to use collaborative filtering, 1 to use content based)");
				Integer alg = scan.nextInt();
				scan.nextLine();
				ctrlD.setAlgorithm(alg);
				System.out.println("\n");
				break;
			case "4":
				System.out.println("Recommend and DCG. This case creates a new recommendation based on the algorithm given before.\nThis operation will take a few minutes, please keep waiting.");
				ctrlD.recommend();
				System.out.println("\n");
				break;
			default:
				System.out.println(command + " is not a valid command.");
				System.out.println("\n");
				break;
			}
			command = scan.nextLine();
		}
		scan.close();
	}
}