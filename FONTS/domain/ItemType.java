package domain;

import java.util.*;

/**
 * Class Type of Item.
 * 
 * This class represents a type of item with a specified number of attributes.
 * 
 * the maximum number of objects of this class is one.
 * 
 * @author Guillem González Valdivia
 */
public class ItemType{
	
	//Private attributes
	
	/**
	 * Non static attribute nameType.
	 * 
	 * Default value to "".
	 * 
	 * This attribute specifies the type of item of a set of items, like "films", "videogames"...
	 */
	private String nameType;
	
	/**
	 * Non static attribute nameAttributes.
	 * 
	 * Default value to new ArrayList<>().
	 * 
	 * Names of the different characteristics or attributes of a type of items. This attribute can be {duration,genre,description}, or 
	 * {colour, size, price, duration}. 
	 */
	private ArrayList<String> nameAttributes;
	
	//Methods
	
	//Constructors
	
	/**
	 * Constructor Itemtype()
	 *
	 * Creates and returns a new instance of ItemType with default values.
	 * 
	 * @return Returns a new instance of ItemType with default values.
	 */
	public ItemType() {
		this.nameType = "";
		this.nameAttributes = new ArrayList<String>();
		return;
	}
	
	/**
	 * Constructor ItemType()
	 * 
	 * @param nametype The identifier of the object. Must be unique among the other instances.
	 * @param a An integer with no value. We put it there to differentiate the constructor from a string to the common constructor.
	 * 
	 * @return Returns a new instance of ItemType with given nametype.
	 */
	public ItemType(String nametype, Integer a) {
		this.nameType = nametype;
		this.nameAttributes = new ArrayList<String>();
		return;
	}
	
	/**
	 * Constructor ItemType()
	 * 
	 * Constructor that inserts the nametype and the characteristics of the type of item.
	 * 
	 * @param nametype
	 * @param attrib
	 * 
	 * @return Returns a new instance of ItemType with given nametype and attributes.
	 */
	public ItemType(String nametype, ArrayList<String> attrib) {
		this.nameType = nametype;
		this.nameAttributes = attrib;
		return;
	}
	
	/**
	 * Constructor ItemType()
	 * 
	 * Constructor that converts the info in a string into an object of ItemType.
	 * 
	 * @param s String with the information necessary to create the instance.
	 * 
	 * @return Returns a new instance with given information (by the string).
	 */
	public ItemType(String data) {
		this.nameType = "";
		this.nameAttributes = new ArrayList<>();
		
		int j = 0, i = 1;
		ArrayList<String> things = new ArrayList<>();
		for(i=1; i<=data.length();++i) {
			if(i == data.length()) {
				things.add(data.substring(j,i));
			}
			else if(data.charAt(i) == '¬') {
				things.add(data.substring(j, i));
				j = i+1;
			}
		}
		this.nameType = things.get(0);
		for(i=1;i<things.size();++i) {
			this.nameAttributes.add(things.get(i));
		}
		return;
	}
	
	/**
	 * Constructor toString()
	 * 
	 * Constructor that turns the object into a string.
	 * 
	 * @return s The string containing the information of the object.
	 */
	@Override
	public String toString() {
		String s = new String(this.nameType);
		for(int i=0;i<this.nameAttributes.size();++i) {
			s = s + '¬' + this.nameAttributes.get(i);
		}
		return s;
	}
	
	//Getters
	
	/**
	 * Getter getNameType()
	 * 
	 * Getter that returns the name of the type of items.
	 * 
	 * @return this.nameType The name of the type of items.
	 */
	public String getNameType() {
		return this.nameType;
	}
	
	/**
	 * Getter getNameAttributes()
	 * 
	 * Getter that returns the names of the attributes in an array.
	 * 
	 * @return this.nameAttributes The names of the attributes of this type of item.
	 */
	public ArrayList<String> getNameAttributes(){
		 return this.nameAttributes;
	}
	
	//Setters
	
	/**
	 * Setter setNameType()
	 * 
	 * Setter that sets the name of the type of items.
	 * 
	 * @param nametype The identifier of the type of item we're setting.
	 */
	public void setNameType(String nametype) {
		this.nameType = nametype;
	}
	
	/**
	 * Setter setNameAttributes()
	 * 
	 * Setter that sets the names of the different characteristics of the type of item.
	 * 
	 * @param nameattributes The names of the attributes this type of item has.
	 */
	public void setNameAttributes(ArrayList<String> nameattributes) {
		this.nameAttributes = nameattributes;
	}
}













