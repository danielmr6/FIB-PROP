package data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

/**
 * ManagerActiveUser class is the class used to store and load ActiveUsers in the persistence level. 
 * 
 * The Manager utilizes for the loads and stores a .txt in the DATA folder.
 * 
 * @author Eloi Balaer Morales
 *
 */
public class ManagerActiveUser {
	
	/**
	 * The name of the file where the ActiveUsers are going to be stored.
	 */
	private String filename;
	
	/**
	 * Stores the single instance of ManagerActiveUser.
	 */
	private static ManagerActiveUser singletonObject;

	/**
	 * fileR stores the manager read file.
	 * If we are not executing any I/O operation, then fileR is not reachable.
	 */
	private static FileReader fileR;
	
	/**
	 * getInstance return the single object of ManagerActiveUser.
	 * @return Returns the single instance of ManagerActiveUser.
	 */
	public static ManagerActiveUser getInstance() {
		if (singletonObject == null)
			singletonObject = new ManagerActiveUser() {
			};
		return singletonObject;
	}
	
	/**
	 * We declare the constructor of ManagerActiveUser in private as we want to maintain
	 * only one instance of ManagerActiveUser. We follow Single Object pattern.
	 */
	private ManagerActiveUser() {	
	}

	/**
	 *  We use this function to change instance of ActiveUser objects that we want to store
	 * or to load. We change the name of the filename attribute and look for a file with that
	 * name, if it does not exist we create it and execute the I/O operations.
	 * @param new_NameFile Is the new filename of the current file.
	 */
	private void setNameFile(String new_NameFile)
	{
		filename = new_NameFile;
		File new_file = new File("../../../DATA/ActiveUser/" + filename + ".txt");
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
	 * setFileActiveUsersR open a {@link FileReader} and stars to read every line in the file.
	 * To store all the data and transmit data to the {@link CtrlDataLayer} we use the following
	 * protocol explained previously. When we finish the execution of setFileUsersR in ActiveUsers 
	 * we will have:
	 * [ActiveUser_1, ActiveUser_2, ActiveUser_3, ... , ActiveUser_n]
	 * Where ActiveUser is an ActiveUser object represented in a String.
	 * @see ActiveUser#toString()
	 * @param ActiveUsers Represents a set of ActiveUser objects in the form of String.
	 * @throws FileNotFoundException If file with name filename doesn't exist and FileNotFoundException will be released.
	 * @throws IOException If we use fileR to do operations like read() and IOException will be released.
	 */
	private void setFileActiveUsersR(ArrayList<String> ActiveUsers) throws FileNotFoundException, IOException
	{
		File carpeta = new File("../../../DATA/ActiveUser/");
		File[] lista = carpeta.listFiles();
		
		for(int i = 0; i < lista.length; i++) {
			if(!lista[i].getName().equals("README.md")) {
				fileR = new FileReader(lista[i],  StandardCharsets.UTF_8);
				Scanner scan = new Scanner(fileR);
				while (scan.hasNextLine())
					ActiveUsers.add(scan.nextLine());
				scan.close();
				fileR.close();
			}
		}
	}
	
	/**
	 * stringtoid Reads Active User in format string and returns the id.
	 * 
	 * @param s represents an ActiveUser in format string.
	 * @return id of the ActiveUser from the parameter.
	 */
	private String stringtoid(String s) {
		for (int i = 0; i < s.length(); ++i) if (s.charAt(i) == '¬') return s.substring(0, i);
		return null;
	}
	
	/**
	 * setFileActiveUsersW open a {@link FileWriter} and starts to write. For each position in the {@link ArrayList} 
	 * we write a line in the file.
	 * @see ActiveUser#toString()
	 * @param ActiveUsers Represents a set of ActiveUser objects in the form of String.
	 * @throws IOException If we use fileR to do operations like read() and IOException will be released.
	 */
	private void setFileActiveUsersW(ArrayList<String> ActiveUsers) throws IOException
	{
		for (Integer i = 0; i < ActiveUsers.size(); ++i) {
			setNameFile(stringtoid(ActiveUsers.get(i)));
			File fw = new File("../../../DATA/ActiveUser/" + filename + ".txt");
			PrintWriter p = new PrintWriter(fw, StandardCharsets.UTF_8.name());
			p.println(ActiveUsers.get(i));
			p.close();
		}
		
	}
	
	/**
	 * loadActiveUser initializes a set of ActiveUsers in string format, that will be filled by the {@link setFileActiveUsersR} method.
	 * In case the latest mentioned method throws an exception, it will print the information of the exception.
	 * @return A set of ActiveUsers that have been loaded from the text file filename.
	 */
	public ArrayList<String> loadActiveUser() 
	{
		ArrayList<String> ActiveUsers = new ArrayList<String>();
		try {
			
			setFileActiveUsersR(ActiveUsers);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		return ActiveUsers;
	}
	
	/**
	 * storeActiveUser calls the method {@link setFileActiveUsersW} with the ActiveUsers array we want to store on the filename text.
	 * In case the latest method mentioned method throws an exception, it will print the information of the exception.
	 * @param users A set of ActiveUser objects in string format that have been transmitted from the {@link CtrlDataLayer}.
	 */
	public void storeActiveUser(ArrayList<String> users ) 
	{
		try {
			setFileActiveUsersW(users);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
}
