package data;


import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Daniel Morón Roces
 */
public class DriverManagerValoration {
	  public static void main(String[] args) {
		  
		  ManagerValoration mV = ManagerValoration.getInstance();
		  ArrayList<String> valorations = new ArrayList<>();
		  System.out.println("DriverManagerValoration execution is started!");
		  
		  Scanner scan = new Scanner(System.in);
		  Scanner scanInfo = new Scanner(System.in);
		  
		  Integer command = scan.nextInt();
		  while(command != 3) {
			  
			  if(command == 1) { //Set info of Valorations
				  System.out.println("Include the information in the set");
				  
				  ArrayList<String> ex = new ArrayList<>();
				  
				  while(scan.hasNextLine()) {
					  ex.add(scanInfo.nextLine());
				  }
				  mV.storeValoration(ex);
				  valorations = mV.loadValoration();
				  for(String st : valorations) System.out.println(st);  
			  }
			  if(command == 2) {
				  valorations.add("5.0");
				  mV.storeValoration(valorations);
				  System.out.println("The information is updated");
				  valorations = mV.loadValoration();
				  for(String st : valorations) System.out.println(st);  
			  }  
			  command = scan.nextInt();
		  }	  	  
		  scan.close();
	  }
}
