package data;

import java.util.*;


/**
 * The data controller is responsible for linking the domain layer with the managers of each class for which we store data.
 * <ul> 
 * <li> Implementation of the "Singleton" design pattern, with the goal of having a single instance of this class in the system. 
 * <li> For the data management we save the objects in different .txt files in which we save the objects in the following way [object1¬object2...objectn]. 
 * <li> Each object is represented as a string and each class has its own constructor and toString() to communicate the data and domain layer.
 * </ul>
 * @author Daniel Morón Roces
 */
public class CtrlDataLayer {
	
	/*----------------------------------------------------------------------------------
	 *------------------------------------ATTRIBUTES------------------------------------
	 *----------------------------------------------------------------------------------*/
	
	/**
	 * <ul>
	 * <li> It is a static attribute that represents how the columns of the ratings CSV files are organised.
	 * <li> The first position of the array represents in which column is the user id when valuing Movies, the second position represents the id of the Item and the last position represents the value.
	 * <li> Possible values for the pos_col_Movies.get(0) are 1, 2 or 3.
	 * <li> The controller communicates with the managers (which have unique instances) and so can store and load data and also be persistent.
	 * </ul>
	 */
	private static ArrayList<Integer> pos_col_Movies;
	
	/**
	 * <ul>
	 * <li> It is a static attribute that represents in which position of the Item files where is the id of these.
	 * <li> The first position of the array represents in which column is the user id when Series are valued.
	 * <li> Possible values for the pos_col_Series.get(0) are 1, 2 or 3.
	 * </ul>
	 */
	private static ArrayList<Integer> pos_col_Series;
	
	/**
	 * This static attribute represents the path to load  the ratings.test files that are from Movies.
	 */
	private static String pathMovies;
	
	/**
	 * This static attribute represents the path to load  the ratings.test files that are from Series.
	 */
	private static String pathSeries;
	/**
	 * <ul>
	 * <li> A CtrlDataLayer static attribute that represents the unique instance of this controller.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static CtrlDataLayer singletonObject;
	
	
	
	/**
	 * <ul>
	 * <li> A ManagerActiveUser static attribute that represents the manager of the ActiveUser class which is unique in the system.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerActiveUser dataActUser;
	
	/**
	 * <ul>
	 * <li> A ManagerItem static attribute that represents the manager of the Item class which is unique in the system.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerItem dataItem;
	
	/**
	 * <ul>
	 * <li> A ManagerRecommendation static attribute that represents the manager of the Recommendation class which is unique in the system.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerRecommendation dataRecommendation;
	
	/**
	 * <ul>
	 * <li> A ManagerUser static attribute that represents the manager of the User class which is unique in the system.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerUser dataUser;
	
	/**
	 * <ul>
	 * <li> A ManagerReview static attribute that represents the manager of the Review class which is unique in the system.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerReview dataReview;
	
	/**
	 * <ul>
	 * <li> A ManagerCSV static attribute that represents the manager which is unique and is responsible for read the
	 * CSV files to get the useful information for the system.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerCSV dataCSV;
	
	/**
	 * <ul>
	 * <li> A ManagerItemType static attribute that represents the manager of the ItemType class which is unique in the system.
	 * <li> Default value = null, when the unique instance is not called with the method getInstance().
	 * </ul>
	 */
	private static ManagerItemType dataItemType;
	
		

	/*----------------------------------------------------------------------------------
	 *------------------------------------METHODS---------------------------------------
	 *----------------------------------------------------------------------------------*/
	
	
	/*-------------------------PRIVATES---------------------------*/
	
	
	/**
	 * The Constructor of CtrlDataLayer in private because we want to have only
	 * one instance of the controller, following the Singleton Pattern.
	 */
	private CtrlDataLayer() {}
	
	/**
	 * This method initializes the Data Controller getting the instances of the managers and is called 
	 * when is created the singleton instance of CtrlDataLayer.
	 */
	private static void initializeCtrlDataLayer() 
	{
		dataCSV = ManagerCSV.getInstance();
		dataRecommendation = ManagerRecommendation.getInstance();
		dataActUser = ManagerActiveUser.getInstance();
		dataReview = ManagerReview.getInstance();
		dataUser = ManagerUser.getInstance();
		dataItem = ManagerItem.getInstance();
		dataItemType = ManagerItemType.getInstance();
		pos_col_Movies = new ArrayList<Integer>();
		pos_col_Series = new ArrayList<Integer>();
		for (int i = 0; i < 3; ++i) pos_col_Movies.add(i);
		pos_col_Series.add(2);
		pos_col_Series.add(0);
		pos_col_Series.add(1);
		
		pathMovies = "../../../DATA/CSVFiles/Movies/ratings.test";
		pathSeries = "../../../DATA/CSVFiles/Series/ratings.test";
		
	}
	
	/*+++++++++++++++++++++++++PUBLICS+++++++++++++++++++++++++*/
	
	/**
	 * This method gives the instance of the Data Controller initialized with the values of their managers.
	 * @return A singleton object of the controller CtrlDataLayer.
	 */
	public static CtrlDataLayer getInstance() 
	{
		if(singletonObject == null) {
			singletonObject = new CtrlDataLayer() {};
			initializeCtrlDataLayer();
		}
		return singletonObject;
	}
	
	
	/**
	 * This method gives the Data information about the class with the name type.
	 * <p>
	 * <ul>
	 * <li> Regarding the implementation of known and unknown we have differentiated when the files we want to load are movies and series.
	 * <li> In the future we want to implement a method to be able to load any CSV file in a more general way.
	 * </ul>
	 * 
	 * @param type a String which represents the name of the class that the Domain Controller wants to get data.
	 * 
	 * @return An ArrayList<String> that represents the objects classes with the name type. Returns null if the type 
	 * is an invalid string.
	 */
	public ArrayList<String> loadDataInfo(String type) 
	{
		if(type.startsWith("Item")) return dataItem.loadItem(type);
		else if(type.startsWith("Review")) return dataReview.loadReview(type);
		else if(type.startsWith("TypeItem")) return dataItemType.loadItemType(type.substring(8));
		else if(type.startsWith("Recommendation")) return dataRecommendation.loadRecommendation(type.substring(14));
		else if(type.startsWith("Known")) {
			
			if (type.substring(5).equals("Movies")) {
				return dataCSV.readKnown(pathMovies + ".known.csv", pos_col_Movies);
			}
			return dataCSV.readKnown(pathSeries+ ".known.csv", pos_col_Series);
		}
		else if(type.startsWith("Unknown")) {
			if (type.substring(7).equals("Movies")) return dataCSV.readUnknown(pathMovies + ".unknown.csv", pos_col_Movies);
			return dataCSV.readUnknown(pathSeries + ".unknown.csv", pos_col_Series);
		}
		switch(type) {
			case "User":
				return dataUser.loadUser();
			
			case "ActiveUser":
				return dataActUser.loadActiveUser();
				
			default:
				System.out.println("The type of information you want to load is incorrect");
				return null;
		}
		
	}
	/**
	 * This method stores the Data information with obj in the classes with the name type.
	 * 
	 * @param type a String which represents the name of the class that the Domain Controller wants to update the data.
	 * @param obj an ArrayList<String> that contains the ids of the classes with the name type.
	 */
	public void storeDataInfo(String type, ArrayList<String> obj)
	{
		if(type.startsWith("Item")) dataItem.storeItem(type, obj);
		else if(type.startsWith("Review")) dataReview.storeReview(type, obj);
		else if(type.startsWith("TypeItem")) dataItemType.storeItemType(type.substring(8), obj);
		else if(type.startsWith("Recommendation")) {
			String x = obj.get(0);
			dataRecommendation.storeRecommendation(type.substring(14), x);
		}
		switch(type) {
		
			case "User":
				dataUser.storeUser(obj);
				break;
			
			case "ActiveUser":
				dataActUser.storeActiveUser(obj);
				break;
		}
	}
}