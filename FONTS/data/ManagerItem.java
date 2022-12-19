package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

import java.nio.charset.StandardCharsets;

/**
 * The ManagerItem class represents a manager for the Item
 * objects that manages the saves and read operations.
 * <p>
 * ManagerItem has been built with the Single Object design pattern.
 * <p>
 * To manage all the data files we use the {@link File} File class that is included in 
 * Java libraries.
 * <p>
 * To manage the objects that we have to store or load we uses the following protocol. As the 
 * {@link CtrlDataLayer} expect to receive the data in this way and also we expect that. 
 * The protocol is:
 * <ul>
 * <li>A string represents an object, in this case it represents an ItemType object.
 * <li>In the file we have for each line one object. For each line we have a String 
 * that represents an Item.
 * <li>The name for each file corresponds to the name of the Item + the type of the item that stores.
 * 	If the file is named ItemMovies, the file contains the instances of Item with nameType Movies.
 * </ul>
 * @see FIle
 * @see FileReade
 * @version v1.0
 * @author Daniel García Estévez
 * 
 */
public class ManagerItem {
	
	/*************************************************************************/
    /**************************Private atributtes*****************************/
    /*************************************************************************/
	
	/**
	 * Attribute fileR
	 * <ul>
	 * <li>fileR stores the manager's read file.
	 * <li>If we are not executing any I/O operation, then fileR is not reachable. 
	 * </ul>
	 */
	private static FileReader fileR;
	
	/**
	 * Attribute NameFile 
	 * <ul>
	 * <li>NameFile stores de name of the current file.
	 * <li>Default values is "".
	 * </ul>
	 */
	private static String NameFile;
	
	/**
	 * Attribute singleObject 
	 * <ul>
	 * <li> singleObject stores the single instance of ManagerItem.
	 * </ul>
	 */
	private static ManagerItem singleObject;
	
	/*************************************************************************/
    /****************************Private methods******************************/
    /*************************************************************************/
	
	/**
	 * setFileItemR open a {@link FileReader} and stars to read every line in the file.
	 * To store all the data and transmit data to the {@link CtrlDataLayer} we use the following
	 * protocol explained previously. When we finish the execution of setFileItemTypeR in objects 
	 * we will have:
	 * [object_1, object_2, object_3, ... , object_n]
	 * Where object is an Item object represented in a String.
	 * @see Item#toString()
	 * @param objects Represents a set of Item objects in the form of String.
	 * @throws FileNotFoundException If file with name NameFile doesn't exist and FileNotFoundException will be released.
	 * @throws IOException If we use fileR to do operations like read() and IOException will be released.
	 */
	private void setFileItemR(ArrayList<String> objects) throws FileNotFoundException, IOException
	{
		fileR = new FileReader("../../../DATA/Items/" + NameFile + ".txt",  StandardCharsets.UTF_8);
		Scanner scan = new Scanner(fileR);
		while (scan.hasNextLine()) objects.add(scan.nextLine());
		scan.close();
		fileR.close();
	}
	
	/**
	 * setFileItemW open a {@link FileWriter} and stars to write. For each position in the {@link ArrayList} 
	 * we write a line in the file. The file will have 
	 * @see Item#toString()
	 * @param objects Represents a set of ItemType objects in the form of String.
	 * @throws IOException If we use fileR to do operations like read() and IOException will be released.
	 */
	private void setFileItemW(ArrayList<String> objects) throws IOException
	{
		File fw = new File("../../../DATA/Items/" + NameFile + ".txt");
		PrintWriter p = new PrintWriter(fw, StandardCharsets.UTF_8.name());
		for (Integer i = 0; i < objects.size(); ++i) p.println(objects.get(i));
		p.close();
	}
	
	/**
	 * We use this function to change instance of Item objects that we want to store
	 * or to load. We change the name of the NameFile attribute and look for a file with that
	 * name, if it does not exist we creat it and execute the I/O operations.
	 * @param new_NameFile Is the new namefile of teh current file
	 */
	private void setNameFile(String new_NameFile)
	{
		NameFile = new_NameFile;
		File new_file = new File("../../../DATA/Items/" + NameFile + ".txt");
		try
		{
			if (!new_file.exists()) new_file.createNewFile();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());	
		}
	}
	
	/*************************************************************************/
    /****************************Constructors*********************************/
    /*************************************************************************/
	
	/**
	 * We declare the constructur of ManagerItem in private as we want to maintain
	 * only one instance of ManagerItem. We follow Single Object pattern.
	 */
	private ManagerItem()
	{
		NameFile = "";
	}
	
	/*************************************************************************/
    /*******************************Getters***********************************/
    /*************************************************************************/
	
	/**
	 * getInstance return the single object of ManagerItem.
	 * @return Returns the single instance of ManagerItem.
	 */
	public static ManagerItem getInstance()
	{
		if (singleObject == null) singleObject = new ManagerItem();
		return singleObject;
	}
	
	/**
	 * getNameFile returns the name file of the current file, or at least the last one.
	 * @return Returns the name file of the current file.
	 */
	public String getNameFile()
	{
		return NameFile;
	}
	
	/*************************************************************************/
    /****************************Other Methods********************************/
    /*************************************************************************/
	
	/**
	 * storeItem store in the file with name "namefile" the Item object that is in "object".
	 * @param namefile namefile is the name of the file taht we will store "object".
	 * @param object Represents a set of Item objects in the form of String.
	 */
	public void storeItem(String namefile, ArrayList<String> object)
	{
		if (!NameFile.equals(namefile)) setNameFile(namefile);
		try
		{
			setFileItemW(object);
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());	
		}
	}
	
	/**
	 * loadItem load what's in the file with name "namefile" and returns the objects stores in that file.
	 * @param namefile namefile is the name of the file that we want to load and return.
	 * @return Returns the objects store in the file specified.
	 */
	public ArrayList<String> loadItem(String namefile)
	{
		ArrayList<String> object = new ArrayList<String>();
		if (!NameFile.equals(namefile)) setNameFile(namefile);
		
		try
		{
			setFileItemR(object);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());	
		}
		return object;
	}
	
	
}
