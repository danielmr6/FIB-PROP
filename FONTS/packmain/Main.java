package packmain;

import domain.*;
import java.util.*;

//Tenemos que especificar el package donde se encuentran TODAS las clases y el main, pero como de momento lo tenemos todo junto
//no vamos a tocar nada.

public class Main{
	
	/*
	 * 
	 */
	public static void main(String[] args) throws Exception{
		
		System.out.println("------------------- PROP PROJECT Q1 2021-2022 --------------------");
		System.out.println("Authors: Eloi Balaer, Daniel García, Guillem González, Daniel Moron");
		
		Scanner scan = new Scanner(System.in);
		CtrlDomainLayer ctrlD = new CtrlDomainLayer();
		
		Integer user_id = null;
		String admin_s = "";
		
		boolean logged = false;
		
		while(!logged) {
			
			System.out.println("Welcome to 'GranBazar'. Type '1' to log in, '2' to create a profile");
			scan = new Scanner(System.in);
			Integer type = scan.nextInt();
			
			switch(type) {
			case 1:			//Log in with an existing profile
				
				System.out.println("Type here your id:");
				user_id = scan.nextInt();
				
				boolean status = ctrlD.log_in(user_id);
				
				if(status) {
					System.out.println("Everything went OK. You're logged now.");
					logged = true;
				}
				else {
					System.out.println("The given id does not correspond to any logged. You can try again, or create a profile.");
					System.out.println("");
					break;
				}
				break;
			case 2:			//Create a profile
				
				System.out.println("You are creating a new profile. Type here your id:");
				user_id = scan.nextInt();
				System.out.println("Are you an administrator? [Yes/No]");
				admin_s = scan.nextLine();
				admin_s.toLowerCase();
				
				boolean admin = admin_s == "yes";
				boolean status = ctrlD.createProfile(user_id, admin);
				
				if(!status) {
					System.out.println("The given id already exists as a created account. Please try again.");
					System.out.println("");
				}
				else {
					ctrlD.log_in(user_id);
					logged = true;
				}
				break;
			default:
				
				System.out.println("Something went wrong. Please type '1' if you want to log in with an existing profile, '2' to create a new profile.");
				break;
			}
		}
		
		//Start of the program itself
		
		String instruction = new String();
		System.out.println("Now you are logged. Type the next instruction, or type 'help' to get information about the commands to use "
				+ "or type 'end' to force terminate without saving any data");
		instruction = scan.nextLine();
		boolean end = false;
		
		while(!end) {
			
			instruction = scan.nextLine();
			instruction.toLowerCase();
			
			switch (instruction) {
				case "valorate":
					
					break;
				case "buy":
					
					break;
				case "wishlist":
					
					break;
				case "select_item":
					
					break;
				case "add_item":
					
					break;
					
				case "modify_item":
					
					break;
				case "remove_item":
					
					break;
				case "create_profile":
					
					break;
				case "remove_profile":
					
					break;
				case "modify_password":
					
					break;
				case "log_in":			//This case is impossible. First the user must close his session, and then log in
					
					break;
				case "view_profile":
					
					break;
				case "save_session":
					
					break;
				case "exit_without_saving":
					
					break;
				case "exit_and_save":
					
					break;
				case "save":			//Case "save everything"
					
					break;
				case "modify_valoration":
					
					break;
				case "remove_valoration":
					
					break;
				case "add_valoration":
					
					break;
				case "end":
					//Default terminating case. We have to close the scanner and end the application
					
					scan.close();
					end = false;
					break;		
				default:
					//Mistyping case. The user must have written something wrong, and we have to inform that something was wrong
					//We tell him what options he has and we tell him to introduce his command once again
					
					break;
			}
		}
	}
}
























