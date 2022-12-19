package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The ManagerItemType class represents a manager for the ItemType
 * objects that manages the saves and read operations.
 * <p>
 * ManagerItemType has been built with the Single Object design pattern.
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
 * that represents an ItemType.
 * <li>The name for each file corresponds to the name of the ItemType that stores.
 * 	If the file is named Movies, the file contains the instance of ItemType with nameType Movies.
 * </ul>
 * @see File
 * @see FileReader
 * @version v1.0
 * @author Daniel García Estévez
 *
 */
public class ManagerItemType {
	
	/*************************************************************************/
    /**************************Private atributtes*****************************/
    /*************************************************************************/
	
	/**
	 * Attribute fileR
	 * <ul>
	 * <li>fileR stores the manager's read file.
	 * <li>If we are not executing any I/O operation, then fileW is not reachable. 
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
	 * <li>singleObject stores de single instance of ManagerItemType.
	 * </ul>
	 */
	private static ManagerItemType singleObject;
	
	/*************************************************************************/
    /****************************Private methods******************************/
    /*************************************************************************/
	
	/**
	 * setFileItemTypeR open a {@link FileReader} and stars to read every line in the file.
	 * To store all the data and transmit data to the {@link CtrlDataLayer} we use the following
	 * protocol explained previously. When we finish the execution of setFileItemTypeR in objects 
	 * we will have:
	 * [object_1, object_2, object_3, ... , object_n]
	 * Where object is an ItemType object represented in a String.
	 * @see ItemType#toString()
	 * @param objects Represents a set of ItemType objects in the form of String.
	 * @throws FileNotFoundException If file with name NameFile doesn't exist and FileNotFoundException will be released.
	 * @throws IOException If we use fileR to do operations like read() and IOException will be released.
	 */
	private void setFileItemTypeR(ArrayList<String> objects) throws FileNotFoundException, IOException
	{
		fileR = new FileReader("../../../DATA/ItemType/" + NameFile + ".txt", StandardCharsets.UTF_8);
		Scanner scan = new Scanner(fileR);
		while (scan.hasNextLine()) objects.add(scan.nextLine());
		scan.close();
		fileR.close();
	}
	
	/**
	 * setFileItemTypeW open a {@link FileWriter} and stars to write. For each position in the {@link ArrayList} 
	 * we write a line in the file.
	 * @see ItemType#toString()
	 * @param objects Represents a set of ItemType objects in the form of String.
	 * @throws IOException If we use fileR to do operations like read() and IOException will be released.
	 */
	private void setFileItemTypeW(ArrayList<String> objects) throws IOException
	{
		File fw = new File("../../../DATA/ItemType/" + NameFile + ".txt");
		PrintWriter p = new PrintWriter(fw, StandardCharsets.UTF_8.name());
		for (Integer i = 0; i < objects.size(); ++i) p.println(objects.get(i));
		p.close();
	}
	
	/**
	 * We use this function to change instance of ItemType objects that we want to store
	 * or to load. We change the name of the NameFile attribute and look for a file with that
	 * name, if it does not exist we creat it and execute the I/O operations.
	 * @param new_NameFile Is the new namefile of teh current file
	 */
	private void setNameFile(String new_NameFile)
	{
		NameFile = new_NameFile;
		File new_file = new File("../../../DATA/ItemType/" + NameFile + ".txt");
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
	 * We declare the constructur of ManagerItemType in private as we want to maintain
	 * only one instance of ManagerItemType. We follow Single Object pattern.
	 */
	private ManagerItemType()
	{
		NameFile = "";
	}
	
	/*************************************************************************/
    /*******************************Getters***********************************/
    /*************************************************************************/
	
	/**
	 * getInstance return the single object of ManagerItemType.
	 * @return Returns the single instance of ManagerItemType
	 */
	public static ManagerItemType getInstance()
	{
		if (singleObject == null) singleObject = new ManagerItemType();
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
	 * storeItemType store in the file with name "type" the ItemType object that is in "object".
	 * @param type Type is the id of the ItemType instance that we have in object.
	 * @param object Represents a set of ItemType objects in the form of String.
	 */
	public void storeItemType(String type, ArrayList<String> object)
	{
		if (!NameFile.equals(type)) setNameFile(type);
		try
		{
			setFileItemTypeW(object);
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());	
		}
	}
	
	/**
	 * loadItemType load what's in the file with name "type" and returns the objects stores in that file.
	 * @param type Type is the id of the ItemType instance that we have in object.
	 * @return Returns the object store in the file specified.
	 */
	public ArrayList<String> loadItemType(String type)
	{
		ArrayList<String> list = new ArrayList<String>();
		if (!NameFile.equals(type)) setNameFile(type);
		try
		{
			setFileItemTypeR(list);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());	
		}

		return list;
	}
}
