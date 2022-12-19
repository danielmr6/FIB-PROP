package domain;
import java.util.*;

/**
 * The user class represents any user that has been stored on the system.
 * 
 * Every user has its unique id, and a set containing the ids of the items reviewed by the user.
 * 
 * @author Eloi Balaer Morales
 *
 */
public class User implements Comparable<User> {
	
	//Private attributes
	
	/**
	 * Unique id for User. The function that calls the constructor must assure that the id given is unique
	 */
	protected Integer id;
	
	/**
	 * Set Ids of the items reviewed by the user.
	 */
	protected ArrayList<Integer> Reviews;
	
	//Methods
	
	//Builders
	
	/**
	 * Constructor User().
	 * 
	 * Creates a empty User with all attributes set on default, has no parameters.
	 * 
	 * @return An empty User.
	 */
	public User() {
		this.id = -1;
		this.Reviews = new ArrayList<Integer>();
		return;
		
	}
	
	/**
	 * Constructor User().
	 * 
	 * Creates a User with only his id set, and with all the other parameters set on default.
	 * 
	 * @param id The identifier of the User.
	 * 
	 * @return A User with his id set as the parameter received.
	 */
	public User(Integer id) {
		this.id = id;
		this.Reviews = new ArrayList<Integer>();
		return;
		
	}
	
	/**
	 * Constructor User().
	 * 
	 * Creates a User with only his id and his set of reviewed items ids, and with all other parameters set on default.
	 * 
	 * @param id The identifier of the User.
	 * @param Reviews A set of Items ids that have been reviewed by the user.
	 * @return A User with his id and Reviews, sets as the parameters received.
	 */
	public User(Integer id, ArrayList<Integer> Reviews) {
		this.id = id;
		this.Reviews = Reviews;
		return;
		
	}
	
	/**
     * Constructor User().
	 * 
	 * Creates a User with all its attributes initialized with the entry string parameter.
	 * We use a specific format to read and write, in this case we receive a string 
	 * in this format:  id¬ReviewsIds¬... where every attribute is separated with ¬.
	 * The method reads the string and divides it by attributes, then initializes the User with the given attributes.
	 *  
	 * @param data A string that contains every attribute to create the ActiveUser in our special format: id¬ReviewsIds¬... , it needs to have at least the id to work. That can be -1 to state is not used.
	 * @return A User with all his attributes sets as the parameters received.
     */
	public User(String data) {
		int j = 0, i = 1;
		this.Reviews = new ArrayList<Integer>();
		ArrayList<String> things = new ArrayList<String>();
		for(i=1; i<=data.length();++i) {
			if(i == data.length()) {
				things.add(data.substring(j,i));
			}
			else if(data.charAt(i) == '¬') {
				things.add(data.substring(j, i));
				j = i+1;
			}
		}
		this.id = Integer.parseInt(things.get(0));
		for(i=1;i<things.size();++i) {
			Integer a = Integer.parseInt(things.get(i));
			this.Reviews.add(a);
		}
		
		Collections.sort(this.Reviews);			//ADDED BY GUILLEM GONZÁLEZ, THIS MAY CHANGE
		return;
	}
	
	//Getters
	
	/**
	 * Getter of the attribute Id.
	 * 
	 * @return Returns the attribute id as a Integer.
	 */
	public Integer getId(){
		return this.id;
	}
	
	/**
	 * Getter of the set of items reviewed by the user.
	 * 	
	 * @return An array list containing the ids of the items reviewed by the user as Integers.
	 */
	public ArrayList<Integer> getReview() {
		return Reviews;
	}
	
	//Setters
	
	/**
	 * Setter of the attribute id of the class.
	 * 
	 * @param id The id of the user we want to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Adder to the Reviews set.
	 * It adds items ids to the Reviews set given by the parameter.
	 * @param id_item A id of an existing item that has been reviewed by the user.
	 */
	public void addReview(Integer id_item) {
		Reviews.add(id_item);
	}
	
	 /**
     * Getter of the attributes of the class in our special string format.
     * 
     * @return String s that contains the attributes of the User, separated by "¬" (dollars)
     */
    @Override
    public String toString() {
        String s = (this.id).toString();
        for(int i=0;i<this.Reviews.size();++i) {
            s = s + new String("¬" + this.Reviews.get(i));
        }
        return s;
    }
    
     /**
      * Override of the method compareTo.
      * 
      * @param u The user to compare to.
      * 
      * @return an integer, 1 if the user id is bigger than the given user u id, 0 if equals and -1 if smaller.
      */
	@Override
	public int compareTo(User u) {
		int comparator = 0;
		if(this.id > u.id) comparator = 1;
		else if(this.id < u.id) comparator = -1;
		else comparator = 0;
		return comparator;
	}

}
