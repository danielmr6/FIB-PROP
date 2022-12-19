package domain;
import java.util.*;

/**
 * This class contains the valuation that a specific user has made for a specific item 
 * and is identified by the ItemID and the UserID. {idIt,idUS}.
 * 
 * @author Daniel Morón Roces
 */
public class Review implements Comparable<Review>{
	
	 /*----------------------------------------------------------------------------------
	  *------------------------------------ATTRIBUTES------------------------------------
	  *----------------------------------------------------------------------------------*/
	
	/**
	 * <ul>
	 * <li> A double number that represents the valuation of the Item made by the user.
	 * <li> Possible values that can have: 2, 3.5 or 7.
	 * <li> Default value: If valuation = null, valuation is not initialized. 
	 * </ul>
	 */
	private Double valuation;
	

	/**
	 * <ul>
	 * <li> A non negative integer number that indicates which item has been valued. 
	 * <li> A pair of examples can be idIt = 100 or idIt = 456 because is valid when:  idIt >= 1.
	 * <li> Default value: If idIt = -1, idIt is not initialized. 
	 * </ul>
	 */
	private Integer idIt;
	
	
	/**
	 * <ul>
	 * <li> A non negative integer number that indicates which user has made the rating. 
	 * <li> A couple of valid values can be idUs = 35 or IdUs = 60 because is valid when:  idUs >= 1.
	 * <li> Default value: If idUs = -1, idUs is not initialized. 
	 * </ul>
	 */
	private Integer idUs;
		

	/*----------------------------------------------------------------------------------
	 *------------------------------------METHODS---------------------------------------
	 *----------------------------------------------------------------------------------*/
	
	
	/*+++++++++++++++++++++++CONSTRUCTORS+++++++++++++++++++++++*/
	
	/**
     * Creates an instance of Review without parameters. The values of the attributes represent that 
     * they are invalid.
     * 
     * @return An empty Review.
     */
	public Review() 
	{
		this.valuation = null;
		this.idIt = -1;
		this.idUs = -1;
		return;
	}
	
	
	/**
	 * Creates an instance of Review with all the parameters specified.
	 * 
     * @param  value represents the user enjoyment with the item itm.
     * @param idItm represents the object which has been valued.
     * @param idUsr represents the person who has been valued the item itm.
     * @return A Review with valid values.
     */
	public Review(Double value, Integer idItm, Integer idUsr) 
	{
		this.valuation = value;
		
		if(idItm >= 1) this.idIt = idItm;
		else System.out.println("The integer Item id is not valid");
		
		if(idUsr >= 1) this.idUs = idUsr;
		else System.out.println("The integer User id is not valid");
		
		return;
	}
	
	/**
	 * Creates an instance of Review with the parameters specified that represent the object's identification.
	 * 
     * @param idItm represents the object which has been valued.
     * @param idUsr represents the person who has been valued the item itm.
     * @return A Review with only the id parameters.
     */
	public Review(Integer idItm, Integer idUsr) 
	{
		if(idItm >= 1) this.idIt = idItm;
		else System.out.println("The integer Item id is not valid");
		
		if(idUsr >= 1) this.idUs = idUsr;
		else System.out.println("The integer User id is not valid");
		
		return;
	}
	
	/**
	 * Creates an instance of Review based on the string val. The separation between values in 
	 * the string must be with ¬. After the transformation, we can get a new object of Review
	 * with the values.
	 * 
	 * @param data is a string that represents an instance of Review. The 
	 * first is the ID user, the second is the ID item and the last is the value
	 * of the Review.
	 * 
	 * @return A Review transformed by the string with Item id, User id and the valuation.
	 */
	public Review(String val) 
	{
		int j = 0, i = 1;
		ArrayList<String> t = new ArrayList<>();
		for(i=1; i<=val.length();++i) {
			if(i == val.length()) {
				t.add(val.substring(j,i));
			}
			else if(val.charAt(i) == '¬') {
				t.add(val.substring(j, i));
				j = i+1;
			}
		}
		Integer idusr = Integer.parseInt(t.get(0));
		if(idusr >= 1) this.idUs = idusr; 
		else System.out.println("The integer User id is not valid");
		
		
		Integer iditm = Integer.parseInt(t.get(1));
		if(iditm >= 1) this.idIt = iditm; 
		else System.out.println("The integer Item id is not valid");
		
		this.valuation = Double.parseDouble(t.get(2));
	
		
		return;
	}
	
	/**
	 * This override method transforms an instance of Review to a new string.
	 * 
	 * @return A string that contains the attributes of the Review, separated by "¬".
	 */
	@Override
	public String toString()
	{
		String s = new String(this.idUs + "¬" + this.idIt + "¬" + this.valuation);
		return s; 
	}
	
	

	/**
	 * This method checks the comparison criterion between two instances of Review
	 * 
	 * @param v is a Review which will be used to check the criterion to compare
	 * two instances of Review. First of all, instances are compared with the Item 
	 * id (increasing), secondly with User id (increasing) and finally with the valuation 
	 * (decreasing).
	 * 
	 * @return Returns true if the comparison criterion is fulfilled, however returns false.
	 */
	public boolean compareCr(Review v) {
		if(idIt > v.getItem()) return true;
		else if(idUs > v.getUser()) return true;
		else return valuation < v.getValuation();
	}
	
		
	/*+++++++++++++++++++++++++++++GETTERS+++++++++++++++++++++++++++++*/
	
	/**
	 * This method allows to obtain the valuation from a user to an item.
	 *  
     * @return Returns a double number which is the value of the attribute valuation.
     */
	public Double getValuation()
	{
		return this.valuation;
	}	
		
	/**
	 * This method allows to get the id of User who has rated.
	 * 
     * @return Returns an integer number which is the id of the User.
     */
	public Integer getUser() 
	{
		return this.idUs;
	}
	
	/**
	 * This method allows to get the id of the Item that has been valued.
	 * 
     * @return Returns an integer number which is the id of the Item.
     */
	public Integer getItem() 
	{
		return this.idIt;
	}
	

	/*+++++++++++++++++++++++++++++SETTERS+++++++++++++++++++++++++++++*/
	
	/**
	 * This method updates the value of the attribute valuation. The number is checked to 
	 * ensure that it is a valid value.
	 * 
     * @param  value is a double number that represents the valuation that the user has done.
     */
	public void setValuation(Double value)
	{
		this.valuation = value;	
	}	
	
	/**
	 * This method updates the id of the User and checks the number to ensure that is a
	 * positive integer number.
	 * 
     * @param  idu is the id of the user that has valued the Review.
     */
	public void setUser(Integer idu) 
	{
		if(idu >= 1) this.idUs = idu;
		else System.out.println("The integer User id is not valid");
	}
	
	/**
	 * This method updates the id of the Item and checks the number to ensure that is a
	 * positive integer number.
	 * 
     * @param  idIt is the id of the item that has been valued.
     */
	public void setItem(Integer idIt) 
	{
		if(idIt >= 1) this.idIt = idIt;
		else System.out.println("The integer Item id is not valid");
	}
	
	@Override
	public int compareTo(Review v)
	{
		int comparation = 0;
		if (this.idUs < v.getUser()) comparation = -1;
		else if (this.idUs > v.getUser()) comparation = 1;
		else {
			if (this.idIt < v.getItem()) comparation = -1;
			else if (this.idIt > v.getItem()) comparation = 1;
			else {
				if (this.valuation < v.getValuation()) comparation = -1;
				else if (this.valuation > v.getValuation()) comparation = 1;
				else comparation = 0;
			}
		}
		return comparation;
	}
}
