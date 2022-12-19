package domain;
import java.util.*;

/**
 * ActiveUser class represents every user that has been active on the system. 
 * 
 * In other words, every user that uses the functions to get recommendations.
 * This type of user can be administrator of the system and get special privileges.
 * The class is an extension of the class User, so it has the parent attributes and methods.
 *  
 * @author Eloi Balaer Morales
 *
 */
public class ActiveUser extends User {
	
	//Private attributes
	
	/**
	 * Attribute id_r is used as a identifier for the recommendation the Active User gets.
	 * Its default value is -1 , indicating the user doesn't have a recommendation yet.
	 */
	private Integer id_r;
	
	/**
	 * Attribute admin is used as a flag that indicates if the Active User have administrator privileges or not.
	 * Its default value is false, meaning it's not an admin.
	 */
	private boolean admin;
	
	//Methods
	
	/**
	 * Constructor ActiveUser().
	 * 
	 * Creates an empty ActiveUser with all attributes set on default, has no parameters.
	 * 
	 * @return An empty ActiveUser.
	 */
	public ActiveUser() {
		super(-1);
		this.id_r = -1;
		this.admin = false;
		return;
	}
	
	/**
	 * Constructor ActiveUser().
	 * 
	 * @param u Existent user in the system.
	 * 
	 * @return A initialized ActiveUser with the parameters of the user of the parameter.
	 */
	public ActiveUser(User u) {
		this.id = u.id;
		this.id_r = u.id;
		this.admin = false;
		this.Reviews = u.Reviews;
	}
	
	/**
	 * Constructor ActiveUser().
	 * 
	 * Creates an ActiveUser with only his id set, and with all the other parameters set on default.
	 * 
	 * @param id The identifier of the Active User.
	 * 
	 * @return An Active User with his id set as the parameter received.
	 */
	public ActiveUser(Integer id) {
		super(id);
		this.id_r = id;
		this.admin = false;
		return;
	}
	
	/**
	 * Constructor ActiveUser().
	 * 
	 * Creates an ActiveUser with only his id and his set of reviewed items ids, and with all other parameters set on default.
	 * 
	 * @param id The identifier of the Active User.
	 * @param Reviews A set of Items ids that have been reviewed by the user.
	 * @return An Active User with his id and Reviews, sets as the parameters received.
	 */
	public ActiveUser(Integer id, ArrayList<Integer> Reviews) {
		super(id,Reviews);
		this.id_r = id;
		this.admin = false;
		return;
	}

	/**
	 * Constructor ActiveUser().
	 * 
	 * Creates an ActiveUser with all its attributes initialized with the entry parameters.
	 * 
	 * @param id The identifier of the Active User.
	 * @param id_r The id of the Recommendation that the Active User can receive.
	 * @param admin The boolean that suggest if an Active User is an Administrator or not.
	 * @param Reviews A set of Items ids that have been reviewed by the user.
	 * @return An Active User with all his attributes sets as the parameters received.
	 */
	public ActiveUser(Integer id, Integer id_r, boolean admin, ArrayList<Integer> Reviews) {
		super(id,Reviews);
		this.id_r = id_r;
		this.admin = admin;
		return;
	}
	
	/**
	 * Constructor ActiveUser().
	 * 
	 * Creates an ActiveUser with all its attributes initialized with the entry string parameter.
	 * We use a specific format to read and write, in this case we receive a string 
	 * in this format:  id¬id_recomendation¬admin¬ReviewsIds¬... where every attribute is separated with ¬.
	 * The method reads the string and divides it by attributes, then initializes the ActiveUser with the given attributes.
	 *  
	 * @param data A string that contains every attribute to create the ActiveUser in our special format: id¬id_recomendation¬admin¬ReviewsIds¬... , it needs to have at least the id, id_r and admin attributes to work. Those can be -1, or false to state they are not used.
	 * @return An Active User with all his attributes sets as the parameters received.
	 */
	public ActiveUser(String data) {
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
		this.id = Integer.parseInt(things.get(0));
		this.id_r = Integer.parseInt(things.get(1));
		this.admin = Boolean.parseBoolean(things.get(2));
		for(i=3;i<things.size();++i) {
			Reviews.add(Integer.parseInt(things.get(i)));
		}
		
		return;
	}
	
	//Setters
	
	/**
	 * Setter of id_r, it sets the received parameter into the ActiveUser id_r attribute.
	 * 
	 * @param id_r The id of the recommendation the Active User receives.
	 */
	public void setId_r(Integer id_r) {
			this.id_r = id_r;		
	}
	
	/**
	 * Setter of admin, it sets the received parameter into the ActiveUser admin attribute.
	 * 
	 * @param adm the admin boolean of the class, the Active User receives.
	 */
	public void setAdmin(Boolean adm) {
			this.admin = adm;
		}
	
	//Getters
	
	/**
	 * Getter of the attribute Id_r.
	 * 
	 * @return Returns the attribute id_r as a Integer.
	 */
	public Integer getId_r(){
			return this.id_r;
		}
	
	/**
	 * Getter of the attribute Admin.
	 * 
	 * @return Returns the attribute admin as a boolean.
	 */
	public Boolean getAdmin(){
		return this.admin;
	}
	
	 /**
     * Getter of the attributes of the class in our special string format.
     * 
     * @return String s that contains the attributes of the ActiveUser, separated by "¬".
     */
    @Override
    public String toString() {
        String s = new String(this.id + "¬" + this.id_r+ "¬" + this.admin);
        for(int i=0;i<this.Reviews.size();++i) {
            s = s + new String("¬" + this.Reviews.get(i));
        }
        return s;
    }
}

