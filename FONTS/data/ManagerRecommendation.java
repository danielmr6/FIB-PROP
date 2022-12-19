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
 * <ul>
 * <li> This controller represents the manager which is responsible for manage the
 * information about the class Recommendation.
 * <li> Implementation of the "Singleton" design pattern, with the goal of having a single instance of this class in the system. 
 * <li> Each string represents an object of the recommendation class and the different values of the object are differentiated by the character '¬'.
 * </ul>
 * @author Daniel Morón Roces
 */
public class ManagerRecommendation {
	
	 /*----------------------------------------------------------------------------------
	  *------------------------------------ATTRIBUTES------------------------------------
	  *----------------------------------------------------------------------------------*/

	/**
	 * <ul>
	 * <li> A ManagerRecommendation static attribute that represents the unique instance of this controller.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerRecommendation singletonObject;
	
	/**
	 * <ul>
	 * <li> A static string that is the name of the text file where the information is stored.
	 * <li> The unique possible value for this attribute is "Recommendations.txt".
	 * </ul>
	 */
	private static String FILENAME_RECOM;

	
	/**
	 * <ul>
	 * <li> A static FileReader attribute that allows to load the file with name FILENAME_RECOM.
	 * <li> "../DATA/" + FILENAME_RECOM is the path to read the file.
	 * </ul>
	 */
	private static FileReader file_R;
	
	/*----------------------------------------------------------------------------------
	 *------------------------------------METHODS---------------------------------------
	 *----------------------------------------------------------------------------------*/
	
	/*-------------------------PRIVATES-------------------------*/
	
	
	/**
	 * This constructor creates the file if doesn't exist in the path.
	 * 
	 * @return An unique instance of the manager Recommendation.
	 */
	private ManagerRecommendation() 
	{
	
	}
	
	/**
	 * This method changes the name of the file to the id of the Recommendation.
	 * <p>
	 * <ul>
	 * <li>If it does not exist, a new one is created with the default values and with the id as the first value of the string.
	 * </ul>
	 * @param id this parameter represents the identifier of the Recommendation 
	 * which is the id of the user who gets the corresponding recommendation-
	 */
	private void updateFileName(String id)
	{
		FILENAME_RECOM = id;
		try
		{
			File new_file = new File("../../../DATA/Recommendation/" + FILENAME_RECOM + ".txt");
			if (!new_file.exists()) {
				new_file.createNewFile();
				String list = id + "¬0.0";
				this.storeRecommendation(id,list);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/*+++++++++++++++++++++++++PUBLICS+++++++++++++++++++++++++*/
	
	/**
	 * This method gives the instance of the ManagerRecommendation.
	 * <p>
	 * <ul>
	 * <li> The singleton is used to ensure that there is only one instance and therefore the constructor is private.
	 * </ul>
	 * @return A singleton object of the ManagerRecommendation.
	 */
	public static ManagerRecommendation getInstance() 
	{
		if(singletonObject == null) {
			singletonObject = new ManagerRecommendation();
		}
		return singletonObject;
	}
	
	
	
	/**
	 * This method creates an instance of file_R and adds in listRec the information of Recommendation.
	 * 
	 * @throws FileNotFoundException, IOException
	 */
	public void addFileRecommendationR(ArrayList<String> listRec) throws FileNotFoundException, IOException
	{
		file_R = new FileReader("../../../DATA/Recommendation/" + FILENAME_RECOM + ".txt", StandardCharsets.UTF_8);
		
		Scanner scn = new Scanner(file_R);
		while (scn.hasNextLine()) listRec.add(scn.nextLine());
		scn.close();
		file_R.close();
	}
	
	
	/**
	 * This method creates an instance of file_W and updates the file with obj information of Recommendation.
	 * @throws IOException
	 */
	public void addFileRecommendationW(String obj) throws IOException
	{
		File fw = new File("../../../DATA/Recommendation/" + FILENAME_RECOM + ".txt");
		
		PrintWriter prWr = new PrintWriter(fw, StandardCharsets.UTF_8.name());
		prWr.println(obj);
		prWr.close();
	}
	
	/**
	 * This method gives the information about the instances of Recommendation.
	 * 
	 * @return An ArrayList<String> that represents firstly the id of Recommendation class and then the information, 
	 * this happened for each instance that is represented by a string.
	 */
	public ArrayList<String> loadRecommendation(String id)
	{
		ArrayList<String> listR = new ArrayList<String>();
		this.updateFileName(id);
		try
		{
			addFileRecommendationR(listR);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());	
		}
		return listR;
	}
	
	/**
	 * 
	 * This method allows to update the information in the file with name id, if this file 
	 * does not exists a new one shall be created and updated.
	 * @param id represents the id of the Recommendation and her name.
	 * @param obj is an ArrayList<String> that contains firstly the id of Recommendation and then the information,
	 * this for each instance of Recommendation that is represented by a string.
	 */
	public void storeRecommendation(String id, String obj)
	{
		this.updateFileName(id);
		try {
			addFileRecommendationW(obj);
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		
	}

}
