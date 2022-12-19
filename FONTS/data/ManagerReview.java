package data;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

import java.nio.charset.StandardCharsets;

/**
 * <ul>
 * <li> This controller represents the manager which is responsible for manage the
 * information about the class Review.
 * <li> Implementation of the "Singleton" design pattern, with the goal of having a single instance of this class in the system. 
 * <li> Each string represents an object of the valuation class and the different values of the object are differentiated by the character '¬'.
 * </ul>
 * @author Daniel Morón Roces
 */

public class ManagerReview {

	 /*----------------------------------------------------------------------------------
	  *------------------------------------ATTRIBUTES------------------------------------
	  *----------------------------------------------------------------------------------*/
	
	/**
	 * <ul>
	 * <li> A ManagerReview static attribute that represents the unique instance of this controller.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerReview singletonObject;

	/** 
	 * <ul>
	 * <li> A static string that is the name of the text file where the information is stored.
	 * <li> One of the possible values for this attribute is "ReviewMovies".
	 * </ul>
	 */
	private static String FileName;

	/**
	 * <ul>
	 * <li> A static FileReader attribute that allows to load the file with name FILENAME_VAL.
	 * <li> "../DATA/" + FILENAME_VAL is the path to read the file.
	 * </ul>
	 */
	private static FileReader file_R;
	
	/*----------------------------------------------------------------------------------
	 *------------------------------------METHODS---------------------------------------
	 *----------------------------------------------------------------------------------*/
	
	/*-------------------------PRIVATES-------------------------*/
	
	
	/**
	 * The Constructor of ManagerReview in private because we want to have only
	 * one instance of the manager, following the Singleton Pattern.
	 */
	private ManagerReview() 
	{
		FileName = "";
	}
	
	
	
	/*+++++++++++++++++++++++++PUBLICS+++++++++++++++++++++++++*/
	
	/**
	 * This method gives the instance of the ManagerReview.
	 * <p>
	 * The singleton is used to ensure that there is only one instance and therefore the constructor is private.
	 * @return A singleton object of the controller ManagerReview.
	 */
	public static ManagerReview getInstance() 
	{
		if(singletonObject == null) {
			singletonObject = new ManagerReview();
		}
		return singletonObject;
	}
	
	/**
	 * This getter method returns the name file of the current file.
	 * @return Returns the name file of the current file.
	 */
	public String getFileName()
	{
		return FileName;
	}
	/**
	 * This function allows to change instance of Review objects. When we change the name of the FileName 
	 * attribute we check the file with the same name, if it does not exist we create it.
	 * @param new_NF is the new namefile of the current File of Reviews.
	 */
	private void updateFileName(String newNF)
	{
		FileName = newNF;
		File new_file = new File("../../../DATA/Review/" + FileName + ".txt");
		try
		{
			if (!new_file.exists()) new_file.createNewFile();
		}
		catch (Exception e)
		{
			System.out.println("System can't create the file specified with the name " + FileName);
		}
	}
	
	/**
	 * This method creates an instance of file_R and adds in listVal the information of Review.
	 * 
	 * @throws FileNotFoundException, IOException
	 */
	private void addFileReviewR(ArrayList<String> listVal) throws FileNotFoundException, 
	IOException
	{
		file_R = new FileReader("../../../DATA/Review/" + FileName + ".txt",  StandardCharsets.UTF_8);
		
		Scanner scn = new Scanner(file_R);
		while (scn.hasNextLine()) listVal.add(scn.nextLine());
		scn.close();
		file_R.close();
	}
	
	
	/**
	 *  This method creates an instance of file_W and updates the file with the Array obj information of Review.
	 * @throws IOException 
	 */
	private void addFileReviewW(ArrayList<String> obj) throws IOException
	{
		File fw = new File("../../../DATA/Review/" + FileName + ".txt");
		
		PrintWriter prWr = new PrintWriter(fw, StandardCharsets.UTF_8.name());
		for (Integer i = 0; i < obj.size(); ++i) prWr.println(obj.get(i));
		prWr.close();
	}
	
	/**
	 * This method gives the information about the instances of Review.
	 * 
	 * @param namef represents the name of the current file.
	 * @return An ArrayList<String> that represents firstly the ids of Review class and then the information, 
	 * this happened for each instance that is represented by a string.
	 */
	public ArrayList<String> loadReview(String namef)
	{
		ArrayList<String> listVal = new ArrayList<String>();
		
		if(!namef.equals(FileName)) updateFileName(namef);
		try {
			addFileReviewR(listVal);
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		return listVal;
	}
	
	
	/**
	 * This method allows to update the information in the file FileName.
	 * 
	 * @param namef represents the name of the current file
	 * @param obj is an ArrayList<String> that contains firstly the ids of Review and then the information,
	 * this for each instance of Review that is represented by a string.
	 */
	public void storeReview(String namef, ArrayList<String> obj)
	{
		if(!FileName.equals(namef)) updateFileName(namef);
		try {
			addFileReviewW(obj);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}	
}
