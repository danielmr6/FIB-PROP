
package domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * The Item class represents any item.An item can be of a specific type. These 
 * types are described in the ItemType class.
 * <p>
 * To identify an item we have its id that is unique within the set of items 
 * of the same type. Likewise, we have for each item what type it is and it's 
 * attributes. Each item has 3 types of attributes. 
 * <ul>
 * <li>Numeric attributes.
 * <li>Boolean attributes.
 * <li>Categorical attributes.
 * </ul>
 * And for each item we have an average of their evaluations.
 * @version v1.0 
 * @author Daniel García Estévez
 */
public class Item {

	/*************************************************************************/
    /**************************Private atributtes*****************************/
    /*************************************************************************/
	
	/**
	 * Attribute id
	 * 
	 * The id identifies the item in the system.
	 */
    private Integer id;
    
    /**
     * Attribute NameType
     * 
     * Indicates which type of item it is.
     */
    private String NameType;
    
    /**
     * Attribute ReviewAvarage
     * 
     * It gives us the average of the evaluations of all the evaluations 
     * that have been made to this item
     */
    private Double ReviewAvarage;
    
    /**
     * Attribute RaterUser
     * 
     * It is the set of users who have rated this item.
     */
    private ArrayList<Integer> RaterUser;
    
    /**
     * Attribute Attributes_numerics
     * 
     * The set of attributes numerics.
     */
    private TreeMap<String, Double> Attributes_numerics;
    
    /**
     * Attribute Attributes_booleans
     * 
     * The set of attributes booleans
     */
    private TreeMap<String, Boolean> Attributes_booleans;
    
    /**
     * Attribute Attributes_categorical
     * 
     * The set of attributes categoricals
     */
    private TreeMap<String, String> Attributes_categorical;
    
    /*************************************************************************/
    /****************************Private methods******************************/
    /*************************************************************************/
    
    /**
     * isDouble returns us if a given number is Doube or not.
     * @param x The number to evaluate if it is a Double.
     * @return Returns true if the number x is a real number, returns 
     *         false otherwise.
     */
    private Boolean isDouble(String x) 
    {
    	Boolean result;
    	try
    	{
    		Double.parseDouble(x);;
    		result = true;
    	}
    	catch (NumberFormatException e)
    	{
    		result = false;
    	}
    	return result;
    }
    
    /**
     * isBoolean returns us if a given string is a Boolean.
     * @param x The string to evaluate if it is a Boolean.
     * @return X will be a Boolean if and only if x == "true" regardless of case. 
     *         Therefore, it will return true if x == "true". It will return false otherwise.
     */
    private Boolean isBoolean(String x)
    {
    	String s = x.toLowerCase();
    	if (s.length() < 4) return false;
    	if (s.equals("true") || s.equals("false")) return true;
    	return false;
    }
    
    /**
     * Calculate the size of the union of two strings.
     * @param s1 The first string.
     * @param s2 The second string.
     * @return Returns the size of the set resulting from making the union between s1 and s2.
     */
	private static int union(String s1, String s2){
	    String s = (s1 + s2).toLowerCase(); //start with entire contents of both strings
	    int i = 0;
	    while(i < s.length()){
	        char c = s.charAt(i);
	        if(i != s.lastIndexOf(c)) //If c occurs multiple times in s, remove first one
	            s = s.substring(0, i) + s.substring(i+1, s.length());
	        else i++; //otherwise move pointer forward
	    }
	    return Math.max(s.length(), 1);
	}
	
	/**
	 * Calculate the size of the intersect of two strings.
	 * @param s1 The first string.
	 * @param s2 The second string.
	 * @return Returns the size of the set resulting from making the intersection between s1 and s2.
	 */
	private static int intersect(String s1, String s2){
	    String s = "";
	    s2 = s2.toLowerCase();
	    for(char c : s1.toLowerCase().toCharArray()){
	        if(s2.indexOf(c) != -1 && s.indexOf(c) == -1)
	            s += c;
	    }
	    return s.length();
	}
    
    /*************************************************************************/
    /****************************Constructors*********************************/
    /*************************************************************************/
    
    /**
     * Build an empty item
     */
    public Item()
    {
    	this.id = -1;
    	this.NameType = "";
    	this.ReviewAvarage = 0.0;
    	this.RaterUser = new ArrayList<Integer>();
    	this.Attributes_numerics = new TreeMap<String, Double>();
    	this.Attributes_booleans = new TreeMap<String, Boolean>();
    	this.Attributes_categorical = new TreeMap<String, String>();
    }
    
    /**
     * Build an item with this.id = id
     * @param id The id of the new item.
     */
    public Item(Integer id)
    {
    	this.id = id;
    	this.NameType = "";
    	this.ReviewAvarage = 0.0;
    	this.RaterUser = new ArrayList<Integer>();
    	this.Attributes_numerics = new TreeMap<String, Double>();
    	this.Attributes_booleans = new TreeMap<String, Boolean>();
    	this.Attributes_categorical = new TreeMap<String, String>();
    }
    
    /**
     * Build an item with this.id = id and with a defined item type that it is nameType.
     * @param id The id of the new item.
     * @param nameType The new type of the item.
     */
    public Item(Integer id, String nameType)
    {
    	this.id = id;
    	this.NameType = nameType;
    	this.ReviewAvarage = 0.0;
    	this.RaterUser = new ArrayList<Integer>();
    	this.Attributes_numerics = new TreeMap<String, Double>();
    	this.Attributes_booleans = new TreeMap<String, Boolean>();
    	this.Attributes_categorical = new TreeMap<String, String>();
    }
    
    /**
     * Build an item from a string. This string contains all the attributes necessary to 
     * define an item and must come in the following format.
     * <p>
     * <ul>
     * <li>To distinguish between attributes we will use the following character: ¬
     * <li>At the beginning we will find the id, followed by the type of Item and the average of the valuations.
     *     Then we will find the users who have rated this item and all the item's attributes.
     * </ul>
     * @param objectData The item data in String format
     */
    public Item(String objectData)
    {
    	this.id = -1;
    	this.NameType = "";
    	this.ReviewAvarage = 0.0;
    	this.RaterUser = new ArrayList<Integer>();
    	Attributes_numerics = new TreeMap<String, Double>();
    	Attributes_booleans = new TreeMap<String, Boolean>();
    	Attributes_categorical = new TreeMap<String, String>();
    	
    	int j = 0;
    	ArrayList<String> object = new ArrayList<String>();
    	for (int i = 1; i <= objectData.length(); ++i) {
    		if (i == objectData.length()) object.add(objectData.substring(j, i));
    		else if (objectData.charAt(i) == '¬') {
    			object.add(objectData.substring(j, i));
    			j = i + 1;
    		}
    	}
    	this.id = Integer.parseInt(object.get(0));
    	this.NameType = object.get(1);
    	for (int i = 2; i < object.size(); i += 2) {
    		if (isDouble(object.get(i + 1))) this.Attributes_numerics.put(object.get(i), Double.parseDouble(object.get(i + 1)));
    		else if (isBoolean(object.get(i + 1))) this.Attributes_booleans.put(object.get(i), Boolean.parseBoolean(object.get(i + 1)));
    		else this.Attributes_categorical.put(object.get(i), object.get(i + 1));
    	}
    }
    
    /*************************************************************************/
    /*******************************Getters***********************************/
    /*************************************************************************/
    
    /**
     * We check the id of the item.
     * @return Returns the id of the item.
     */
    public Integer getId()
    {
        return this.id;
    }
    
    /**
     * We check the type of the item.
     * @return Returns the type of the item.
     */
    public String getNameType()
    {
    	return this.NameType;
    }
    
    /**
     * We check the average of the evaluations made to this item.
     * @return Returns the average of the evaluations made to this item.
     */
    public Double getAvarageReviewed()
    {
    	return this.ReviewAvarage;
    }
    
    /**
     * We check the users who have rated this item.
     * @return Returns the users who have rated this item.
     */
    public ArrayList<Integer> getRaterUsers()
    {
    	return this.RaterUser;
    }
    
    /**
     * We check the attributes numerics.
     * @return Returns the attributes numerics.
     */
    public TreeMap<String, Double> getAttributesNumerics()
    {
    	return this.Attributes_numerics;
    }
    
    /**
     * We check the attributes booleans.
     * @return Returns the attributes booleans.
     */
    public TreeMap<String, Boolean> getAttributesBooleans()
    {
    	return this.Attributes_booleans;
    }
    
    /**
     * We check the attributes categorical.
     * @return Returns the attributes categorical.
     */
    public TreeMap<String, String> getAttributesCategorical()
    {
    	return this.Attributes_categorical;
    }
    
    /**
     * Returns the value of the named numeric attribute nameAtribute.
     * @param nameAtribute The name of the attribute that we wanna check.
     * @return Returns the value of the named attribute nameAtribute.
     */
    public Double getAtrNumericWithName(String nameAtribute)
    {
    	return this.Attributes_numerics.get(nameAtribute);
    }
    
    /**
     * Returns the value of the named boolean attribute nameAtribute.
     * @param nameAtribute The name of the attribute that we wanna check.
     * @return Returns the value of the named attribute nameAtribute.
     */
    public Boolean getAtrBooleanWithName(String nameAtribute)
    {
    	return this.Attributes_booleans.get(nameAtribute);
    }
    
    /**
     * Returns the value of the named categorical attribute nameAtribute.
     * @param nameAtribute The name of the attribute that we wanna check.
     * @return Returns the value of the named attribute nameAtribute.
     */
    public String getAtrCategoricalWithName(String nameAtribute)
    {
    	return this.Attributes_categorical.get(nameAtribute);
    }
    
    /*************************************************************************/
    /*******************************Setters***********************************/
    /*************************************************************************/
    
    /**
     * Update the item id.
     * @param new_id The new id for the item.
     */
    public void setId(Integer new_id)
    {
    	id = new_id;
    }
    
    /**
     * Update the type of the item.
     * @param new_NameType The new type for the item.
     */
    public void setNameType(String new_NameType)
    {
    	this.NameType = new_NameType;
    }
    
    /**
     * update the average of the item's valuations.
     * @param new_avarage The new average og the item's valuation.
     */
    public void setAvarageReviews(Double new_avarage)
    {
    	this.ReviewAvarage = new_avarage;
    }
    
    /**
     * Update the set of users who have rated this item.
     * @param new_RaterUsers The new set of users who have rated this item.
     */
    public void setRaterUsers(ArrayList<Integer> new_RaterUsers) 
    {
		this.RaterUser = new_RaterUsers;
	}
    
    /**
     * Updates the set of numeric attributes.
     * @param new_attributes The new set of numeric attributes
     */
    public void setAttributesNumerics(TreeMap<String, Double> new_attributes)
    {
    	Attributes_numerics = new_attributes;
    }
    
    /**
     * Updates the set of booleans attributes.
     * @param new_attributes The new set of booleans attributes
     */
    public void setAttributesBooleans(TreeMap<String, Boolean> new_attributes)
    {
    	Attributes_booleans = new_attributes;
    }
    
    /**
     * Updates the set of categorical attributes.
     * @param new_attributes The new set of categorical attributes
     */
    public void setAttributesCategorical(TreeMap<String, String> new_attributes)
    {
    	Attributes_categorical = new_attributes;
    }
    
    /*************************************************************************/
    /****************************Other Methods********************************/
    /*************************************************************************/
    
    /**
     * Calculate the distance between two items. This distance will be calculated 
     * depending on its attributes. Therefore, an item will have a shorter distance 
     * if it has similar attributes, and will have a longer distance otherwise.
     * @param x The item from which we want to know the distance to it.
     * @return Returns the distance between the two items.
     */
    public Double distance(Item x)
    {
    	if (this.NameType.equals(x.getNameType())) {
    		Double distanceNum = 0.0;
    		Double distanceBool = 0.0;
    		Double distanceCat = 0.0;
    		Integer k = 3;
    		
    		for (Map.Entry<String, Double> it : this.Attributes_numerics.entrySet()) {
    			distanceNum += Math.pow(it.getValue() - x.getAtrNumericWithName(it.getKey()),2.0);	//PUEDE MODIFICAR
    		}
    			
    		for (Map.Entry<String, Boolean> it : this.Attributes_booleans.entrySet()) {
    			if (!it.getValue().equals(x.getAtrBooleanWithName(it.getKey()))) distanceBool += 1.0;
    		}
    		
    		for (Map.Entry<String, String> it : this.Attributes_categorical.entrySet()) {				//HAY ALGO QUE FALLA AQUÍ, LA SEGUNDA LLAMADA A MAP DEVUELVE NULL EN ALGUNOS CASOS
    			if(x.getAtrCategoricalWithName(it.getKey()) != null) distanceCat += k * (intersect(it.getValue(), x.getAtrCategoricalWithName(it.getKey())) / union(it.getValue(), x.getAtrCategoricalWithName(it.getKey())));
    		}
    		
    		
    		
    		distanceNum /= this.Attributes_numerics.size();
    		distanceNum = Math.sqrt(distanceNum);
    		
    		return distanceNum + distanceBool + distanceCat;
    	}
    	return -1.0;
    }
    
    /*************************************************************************/
    /*************************Override functions******************************/
    /*************************************************************************/
    
    @Override
    /**
     * Convert an Item object to a String. The conversion will do it in the following way.
     * <p>
     * <ul>
     * <li>To differentiate between attributes we will use the following character: ¬
     * <li>We will convert the object as follows: first we save its id, followed by 
     *     the item type and the average of the valuations. 
     * <li>Then we will convert the set of users who have rated this item.
     * <li>And finally we will have the numerical attributes, the boolean ones and the categorical ones.
     * </ul>
     * @return Returns the object converted to string
     */
    public String toString()
    {
    	
    	String object = Integer.toString(id);
    	object = object + "¬" + NameType;
    	for (Map.Entry<String, Double> it : Attributes_numerics.entrySet()) object = object + "¬" + it.getKey() + "¬" + it.getValue().toString();
    	
    	for (Map.Entry<String, Boolean> it : Attributes_booleans.entrySet()) object = object + "¬" + it.getKey() + "¬" + it.getValue().toString();
    	
    	for (Map.Entry<String, String> it : Attributes_categorical.entrySet()) object = object + "¬" + it.getKey() + "¬" + it.getValue();
    	
    	return object;
    }
}
