package domain;
import java.util.*;


/*
 * Class Valoration is the class that contains the valuation that a specific user has made
 * for a specific item. The class may includes the value of the attribute valoration
 * is between 0 and 5 combining with the decimal part that can be 0 or 5 until arrive to 5.0.
 * @author Daniel Morón Roces
 */
public class Valoration {
	
	 /*----------------------------------------------------------------------------------
	  *------------------------------------ATTRIBUTES------------------------------------
	  *----------------------------------------------------------------------------------*/
	
	/*
	  Integer value that represents the valuation of the Item made by the user.
	  Possible values that can have are:
	  {0,0.5,1,1.5,2,2.5,3,3.5,4,4.5,5} are valid values.
	  If valuation = 0.0, valuation is not initialized. 
	*/
	private Double valuation;
	
	// Indicates which item has been valued
	private Integer idIt;
	
	// Indicates which user has made the rating
	private Integer idUs;
	
	

	/*----------------------------------------------------------------------------------
	 *------------------------------------METHODS---------------------------------------
	 *----------------------------------------------------------------------------------*/
	
	
	/*+++++++++++++++++++++++CONSTRUCTORS+++++++++++++++++++++++*/
	
	/**
     * Special method that creates an instance of Valoration without parameters. The 
     * values of the attributes represent that they are invalid.
     * @return 
     */
	public Valoration() 
	{
		this.valuation = 0.0;
		return;
	}
	
	
	/**
	 * Special method that creates an instance of Valoration with the parameters specified
     * @param  value represents the user enjoyment with the item itm
     * @param itm represents the object which has been valued
     * @param usr represents the person who has been valued the item itm
     * @return 
     */
	public Valoration(Double value, Integer idItm, Integer idUsr) 
	{
		double decimalPart = value % 1;
		double dif = value - decimalPart;
		
		if((decimalPart == 0 || decimalPart == 0.5) && (dif >= 1.0 && dif <= 5.0)) {
			this.valuation = value;
		}
		
		else {
			System.out.println("The value of valuation is not valid");
		}	
		this.idIt = idItm;
		this.idUs = idUsr;
		return;
	}
	
	/**
	 * This function creates an instance of Valoration based on the string val
	 * @param data is a string that represents an instance of valoration. The 
	 * first is the ID user, the second is the ID item and the third is the value
	 * of the valoration.
	 * @return
	 */
	public Valoration(String val) 
	{
		int j = 0, i = 1;
		ArrayList<String> t = new ArrayList<>();
		for(i=1; i<=val.length();++i) {
			if(i == val.length()) {
				t.add(val.substring(j,i-1));
			}
			else if(val.charAt(i) == '$') {
				t.add(val.substring(j, i));
				j = i+1;
			}
		}
		this.idUs = Integer.parseInt(t.get(0));
		this.idIt = Integer.parseInt(t.get(1));
		this.valuation = Double.parseDouble(t.get(2));
		return;
	}
	
	/**
	 * @return String s that contains the attributes of the valoration, separated by "$" (dollars)
	 */
	@Override
	public String toString()
	{
		String s = new String(this.idUs + "$" + this.idIt + "$" + this.valuation);
		return s; 
	}
	
	

	/**
	 * 
	 * @param v
	 * @return
	 */
	public boolean compareCr(Valoration v) {
		if(idIt > v.getItem()) return true;
		else if(idUs > v.getUsuari()) return true;
		else return valuation < v.getValuation();
	}
	
	
	
	
	/*+++++++++++++++++++++++++++++GETTERS+++++++++++++++++++++++++++++*/
	
	/**
	 * This function allows to obtain the valoration 
     * @return Returns the value of the attribute valuation
     */
	public Double getValuation()
	{
		return this.valuation;
	}	
		
	/**
	 * This method allows to get the id of User
     * @return Returns the id of the User
     */
	public Integer getUsuari() 
	{
		return this.idUs;
	}
	
	/**
	 * This method allows to get the id of the Item
     * @return Returns the id of the Item
     */
	public Integer getItem() 
	{
		return this.idIt;
	}
	

	/*+++++++++++++++++++++++++++++SETTERS+++++++++++++++++++++++++++++*/
	
	/**
	 * This method updates the value of the valuation
     * @param  value is a double number that represents the valuation that the user has done
     */
	public void setValuation(Double value)
	{
		double decimalPart = value % 1;
		double dif = value - decimalPart;
		
		if((decimalPart == 0 || decimalPart == 0.5) && (dif >= 1.0 && dif <= 5.0)) {
			this.valuation = value;
		}
		else {
			System.out.println("The value of valuation is not valid");
		}
	}	
	
	/**
	 * This method updates the id of the User
     * @param  u is the user that has valued the valoration
     */
	public void setUsuari(Integer idu) 
	{
		this.idUs = idu;
	}
	
	/**
	 * This method updates the id of the Item
     * @param  i is the item that has been valued
     */
	public void setItem(Integer idIt) 
	{
		this.idIt = idIt;
	}
	

}
