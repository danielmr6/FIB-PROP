package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

/**
 * ManagerUser class is the class used to store and load Users in the persistence level. 
 * 
 * The Manager utilizes for the loads and stores a .txt in the DATA folder.
 * 
 * @author Eloi Balaer Morales
 *
 */
public class ManagerUser {
	
	/**
	 * The name of the file where the users are going to be stored.
	 */
	private String filename = "Users";
	
	/**
	 * Stores the single instance of ManagerUser.
	 */
	private static ManagerUser singletonObject;
	
	/**
	 * fileR stores the manager read file.
	 * If we are not executing any I/O operation, then fileR is not reachable.
	 */
	private static FileReader fileR;
	
	/**
	 * getInstance return the single object of ManagerUser.
	 * @return Returns the single instance of ManagerUser.
	 */
	public static ManagerUser getInstance() {
		if (singletonObject == null)
			singletonObject = new ManagerUser() {
			};
		return singletonObject;
	}
	
	/**
	 * We declare the constructor of ManagerUser in private as we want to maintain
	 * only one instance of ManagerUser. We follow Single Object pattern.
	 */
	private ManagerUser() {	
	}

	/**
	 * We use this function to change instance of User objects that we want to store
	 * or to load. We change the name of the filename attribute and look for a file with that
	 * name, if it does not exist we create it and execute the I/O operations.
	 * @param new_NameFile Is the new filename of the current file.
	 */
	private void setNameFile(String new_NameFile)
	{
		filename = new_NameFile;
		File new_file = new File("../../../DATA/User/" + filename + ".txt");
		try
		{
			if (!new_file.exists()) new_file.createNewFile();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());	
		}
	}
	
	/**
	 * setFileUsersR open a {@link FileReader} and stars to read every line in the file.
	 * To store all the data and transmit data to the {@link CtrlDataLayer} we use the following
	 * protocol explained previously. When we finish the execution of setFileUsersR in Users 
	 * we will have:
	 * [User_1, User_2, User_3, ... , User_n]
	 * Where User is an User object represented in a String.
	 * @see User#toString()
	 * @param Users Represents a set of User objects in the form of String.
	 * @throws FileNotFoundException If file with name filename doesn't exist and FileNotFoundException will be released.
	 * @throws IOException If we use fileR to do operations like read() and IOException will be released.
	 */
	private void setFileUsersR(ArrayList<String> Users) throws FileNotFoundException, IOException
	{
		setNameFile(filename);
		fileR = new FileReader("../../../DATA/User/" + filename + ".txt",  StandardCharsets.UTF_8);
		Scanner scan = new Scanner(fileR);
		while (scan.hasNextLine())
			Users.add(scan.nextLine());
		scan.close();
		fileR.close();
	}
	
	/**
	 * setFileUsersW open a {@link FileWriter} and starts to write. For each position in the {@link ArrayList} 
	 * we write a line in the file. 
	 * @see User#toString()
	 * @param users Represents a set of User objects in the form of String.
	 * @throws IOException If we use fileR to do operations like read() and IOException will be released.
	 */
	private void setFileUsersW(ArrayList<String> users) throws IOException
	{
		setNameFile(filename);
		File fw = new File("../../../DATA/User/" + filename + ".txt");
		PrintWriter p = new PrintWriter(fw,  StandardCharsets.UTF_8.name());
		for (Integer i = 0; i < users.size(); ++i) p.println(users.get(i));
		p.close();
	}
	
	
	/**
	 * loadUser initializes a set of Users in string format, that will be filled by the {@link setFileUsersR} method.
	 * In case the latest mentioned method throws an exception, it will print the information of the exception.
	 * @return A set of Users that have been loaded from the text file filename.
	 */
	public ArrayList<String> loadUser() {
		ArrayList<String> Users = new ArrayList<String>();
		try {
			
			setFileUsersR(Users);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Users;
	}
	
	/**
	 * storeUser calls the method {@link setFileUsersW} with the users array we want to store on the filename text.
	 * In case the latest method mentioned method throws an exception, it will print the information of the exception.
	 * @param users A set of User objects in string format that have been transmitted from the {@link CtrlDataLayer}.
	 */
	public void storeUser(ArrayList<String> users ) {
		try {
			setFileUsersW(users);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
