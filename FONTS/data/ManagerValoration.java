package data;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Daniel Morón Roces
 *
 */
public class ManagerValoration {

	 /*----------------------------------------------------------------------------------
	  *------------------------------------ATTRIBUTES------------------------------------
	  *----------------------------------------------------------------------------------*/
	

	/**
	 * 
	 */
	private static ManagerValoration singletonObject;

	/**
	 * 
	 */
	private static String FILENAME_VAL = "Valorations.txt";

	/**
	 * 
	 */
	private FileReader file_R;
	
	/**
	 * 
	 */
	private FileWriter file_W;	
	

	
	/*----------------------------------------------------------------------------------
	 *------------------------------------METHODS---------------------------------------
	 *----------------------------------------------------------------------------------*/
	
	/*-------------------------PRIVATES-------------------------*/
	
	
	/**
	 * @param  Parametro Descripcion 
    * @return 
	 */
	private ManagerValoration() 
	{
		File archive = new File("../DATA/" + FILENAME_VAL);
		try {
			if(!archive.exists()) archive.createNewFile();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		return;
	}
	
	
	
	/*+++++++++++++++++++++++++PUBLICS+++++++++++++++++++++++++*/
	
	/**
	 * @param  Parametro Descripcion 
    * @return 
	 */
	public static ManagerValoration getInstance() 
	{
		if(singletonObject == null) {
			singletonObject = new ManagerValoration();
		}
		return singletonObject;
	}
	
	
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void addFileValorationR(ArrayList<String> listVal) throws FileNotFoundException, 
	IOException
	{
		file_R = new FileReader("../DATA/" + FILENAME_VAL);
		if (file_R != null) {
			Scanner scn = new Scanner(file_R);
			while (scn.hasNextLine()) listVal.add(scn.nextLine());
			scn.close();
			file_R.close();
		}
	}
	
	
	/**
	 * 
	 * @throws IOException
	 */
	public void addFileValorationW(ArrayList<String> obj) throws IOException
	{
		file_W = new FileWriter("../DATA/" + FILENAME_VAL);
		if (file_W != null) {
			PrintWriter prWr = new PrintWriter(file_W);
			for (Integer i = 0; i < obj.size(); ++i) prWr.println(obj.get(i));
			prWr.close();
			file_W.close();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> loadValoration()
	{
		ArrayList<String> listVal = new ArrayList<String>();
	
		try {
			addFileValorationR(listVal);
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		return listVal;
	}
	
	/**
	 * 
	 * @param mode
	 * @param obj
	 */
	public void storeValoration(ArrayList<String> obj)
	{
		try {
			addFileValorationW(obj);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}	
}
