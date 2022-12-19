package data;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.nio.charset.StandardCharsets;

import java.lang.StringBuilder;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * The ManagerCSV class represents a manager for the CSV files to store
 * the data of those files. This manager will interpret the csv files and 
 * convert them into {@code ArrayList<String>} following the protocols established 
 * in the other managers and in the classes that it is involved in.
 * <p>
 * ManagerCSV can read csv files that contains items and ratings. To interpret the CSV files 
 * we will use OpenCSV a open-source library that it is used to read CSV files.
 * 
 * @see Item
 * @see ItemType
 * @see Review
 * @see User
 * @version v1.0
 * @author Daniel García Estévez
 *
 */
public class ManagerCSV {
	
	/*************************************************************************/
    /**************************Private atributtes*****************************/
    /*************************************************************************/
	
	/**
	 * Attribute singleObject 
	 * <ul>
	 * <li>singleObject stores de single instance of ManagerItem.
	 * </ul>
	 */
	private static ManagerCSV singleObject;
	
	/*************************************************************************/
    /****************************Private methods******************************/
    /*************************************************************************/
	
	/**
	 * getIdentifier() Finds where the identifier of the objects that are 
	 * presented in the csv file is and returns its position in names.
	 * @param names It is the name of each column in the CSV file.
	 * @return Returns in which postion in names is the identifier.
	 */
	private int getIdentifier(String[] names)
	{
		for (int i = 0; i < names.length; ++i) if (names[i].equals("id")) return i;
		return -1;
	}
	
	/**
	 * readReviews() is a function that reads in "reader" the Reviews and returns it. 
	 * We build the {@code ArrayList<String>} following the protocol specify in {@link Review}.
	 * We use "pos_col" to identifie were are the attributes of a Review object as we have to know
	 * for exemple in which column is the id of the user.
	 * @param reader The CSVReader that allows us to read teh CSV file.
	 * @param pos_col Pos_col works in the following way:
	 * <ul>
	 * <li>The first position of pos_col tell us where is the id of the user.
	 * <li>The second position of pos_col tell us where is the id of the item.
	 * <li>The third position of pos_col tell us where is the value of the rating.
	 * <li>The values of each position can be 0, 1 or 2. For exemple if it is 2, that means it is the third column.
	 * </ul>
	 * @return Returns a set of Reviews represented as a set of strings.
	 * @throws IOException 
	 * @throws CsvValidationException
	 */
	private ArrayList<String> readReviews(CSVReader reader, ArrayList<Integer> pos_col) throws IOException, CsvValidationException
	{
		String[] data = reader.readNext();
		
		ArrayList<String> list = new ArrayList<String>();
		
		data = reader.readNext();
		
		while (data != null) {
			String object = data[pos_col.get(0)] + "¬" + data[pos_col.get(1)] + "¬" + data[pos_col.get(2)];
			list.add(object);
			data = reader.readNext();
		}
		
		
		reader.close();
		return list;
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	private String stringTo(String s) 
	{
		for (int i = 0; i < s.length(); ++i) if (s.charAt(i) == '¬') return s.substring(0, i);
		return null;
	}
	
	/*************************************************************************/
    /****************************Constructors*********************************/
    /*************************************************************************/
	
	/**
	 * We declare the constructur of ManagerCSV in private as we want to maintain
	 * only one instance of ManagerItem. We follow Single Object pattern.
	 */
	private ManagerCSV()
	{
		
	}
	
	/*************************************************************************/
    /*******************************Getters***********************************/
    /*************************************************************************/
	
	/**
	 * getInstance return the single object of ManagerCSV.
	 * @return Returns the single instance of ManagerCSV.
	 */
	public static ManagerCSV getInstance()
	{
		if (singleObject == null) singleObject = new ManagerCSV();
		return singleObject;
	}
	
	/*************************************************************************/
    /****************************Other Methods********************************/
    /*************************************************************************/
	
	/**
	 * readItems() read a csv file taht represents a set of Items of the same type. 
	 * This file we can search from "pathfile"
	 * @param pathToFile Defines were the files is in our computer.
	 * @param TypeItem Defines which type of item is the file taht we are reading.
	 */
	public void readItems(String pathToFile, String TypeItem)
	{
		try
		{
			CSVReader reader = new CSVReader(new FileReader(pathToFile, StandardCharsets.UTF_8));
			
			String[] names = reader.readNext();
			
			ArrayList<String> Itemtype = new ArrayList<String>();
			ArrayList<String> Items = new ArrayList<String>();
			
			String Itemtype_object = TypeItem;
			
			Integer pos_identifier = getIdentifier(names);
			
			if (pos_identifier == -1) {
				System.out.println("The file has not an identifier.");
				return; // Haria falta enviar una excepción
			}
			
			for (int i = 0; i < names.length; ++i) if (i != pos_identifier) Itemtype_object = Itemtype_object + "¬" + names[i];
			Itemtype.add(Itemtype_object);
			
			
			String Item_object = null;
			
			String[] data = reader.readNext();
			
			while (data != null) {
				
				for (int i = 0; i < data.length; ++i) {
					for (int j = 0; j < data[i].length(); ++j) {
						if (data[i].charAt(j) == '\n') {
							StringBuilder newString = new StringBuilder(data[i]);
							newString.setCharAt(j, ' ');	
							data[i] = newString.toString();
						}		
					}
				}
				Item_object = new String(data[pos_identifier]);
				Item_object = Item_object + "¬" + TypeItem;
				for (int i = 0; i < data.length; ++i) if (i != pos_identifier) Item_object = Item_object + "¬" + names[i] + "¬" + data[i];
				
				Items.add(Item_object);
				data = reader.readNext();
				
			}
			
			ManagerItem.getInstance().storeItem("Item" + TypeItem, Items);
			ManagerItemType.getInstance().storeItemType(TypeItem, Itemtype);
			
			reader.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());	
		}
	}
	
	/**
	 * 
	 * @param pathToFile Defines were the files is in our computer.
	 * @param typeItem Defines which type of item is the file taht we are reading.
	 * @param pos_col
	 */
	public void readRatings(String pathToFile, String typeItem, ArrayList<Integer> pos_col)
	{
		try
		{
			CSVReader reader = new CSVReader(new FileReader(pathToFile, StandardCharsets.UTF_8));
			String[] data = reader.readNext();
			
			ArrayList<String> old_Users = ManagerUser.getInstance().loadUser();
			TreeMap<String, String> mapUser = new TreeMap<String, String>();
			ArrayList<String> Reviews = new ArrayList<String>();
			
			for (int i = 0; i < old_Users.size(); ++i) mapUser.put(stringTo(old_Users.get(i)), old_Users.get(i));
			
			data = reader.readNext();
			
			while (data != null) {
				String Review_object = data[pos_col.get(0)] + "¬" + data[pos_col.get(1)] + "¬" + data[pos_col.get(2)];
				Reviews.add(Review_object);
				
				if (mapUser.containsKey(data[pos_col.get(0)])) {
					String user_s = mapUser.get(data[pos_col.get(0)]);
					user_s = user_s + "¬" + data[pos_col.get(1)];
					mapUser.put(data[pos_col.get(0)], user_s);
				}
				else mapUser.put(data[pos_col.get(0)], data[pos_col.get(0)] + "¬" + data[pos_col.get(1)]);

				data = reader.readNext();
			}
			
			ArrayList<String> Users = new ArrayList<String>();
			for (Map.Entry<String, String> it : mapUser.entrySet()) Users.add(it.getValue());
			
			ManagerUser.getInstance().storeUser(Users);
			ManagerReview.getInstance().storeReview("Review" + typeItem, Reviews);
			
			reader.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param path
	 * @param pos_col
	 * @return
	 */
	public ArrayList<String> readKnown(String path, ArrayList<Integer> pos_col)
	{
		try
		{
			CSVReader reader = new CSVReader(new FileReader(path, StandardCharsets.UTF_8));
			return readReviews(reader, pos_col);
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());	
		}
		return null;
	}
	
	/**
	 * 
	 * @param path
	 * @param pos_col
	 * @return
	 */
	public ArrayList<String> readUnknown(String path, ArrayList<Integer> pos_col)
	{
		try
		{
			CSVReader reader = new CSVReader(new FileReader(path, StandardCharsets.UTF_8));
			return readReviews(reader, pos_col);
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());	
		}
		return null;
	}
}
