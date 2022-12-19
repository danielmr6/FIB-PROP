package domain;
import java.util.*;

/**
 * Class Recommendation.
 * 
 * This class represents a set of items given to a specific user, which are shown to the user to valorate.
 * 
 * the maximum number of objects of this class to exist are the number of active users registered in the system.
 * 
 * @author Guillem González Valdivia
 */
public class Recommendation{
	
	//Private attributes
	
	/**
	 * 
	 */
	private ArrayList<Integer> Items;
	
	/**
	 * Attribute user.
	 * 
	 * Default value = -1 (there's no user holding this recommendation at the moment).
	 * 
	 * A non negative integer that represents the user owner of this recommendation. It is also the identifier of the class.
	 * 
	 * Some valid examples can be 2, 6782 or 91837. An invalid example could be -172.
	 */
	private Integer user;
	
	//Real number that gives the quality of this recommendation
	/**
	 * Attribute DCG.
	 * 
	 * Default value = 0.0.
	 * 
	 * A real, non negative number, that gives the quality of the recommendation, related to other valuations of the system where it is.
	 * If the DCG is bigger, the quality of the recommendation is better.
	 * 
	 * Some valid examples can be 12, 123.4 or 2.45. An invalid example can be -123,4.
	 */
	private Double DCG;
	
	//Methods
	
	//Constructors
	
	/**
	 * Constructor Recommendation().
	 * 
	 * Creates an empty recommendation, with all attributes with default values. It has no parameters.
	 * 
	 * @return An empty recommendation.
	 */
	public Recommendation() {
		this.user = -1;
		this.Items = new ArrayList<>();
		this.DCG = 0.0;
		return;
	}
	
	/**
	 * Constructor Recommendation().
	 * 
	 * Creates an almost empty recommendation. The only attribute set is the identifier (user).
	 * 
     * @param user The identifier and the user which owns the recommendation.
     * 
     * @return An almost empty recommendation.
     */
	public Recommendation(Integer user) {
		this.user = user;
		this.DCG = 0.0;
		this.Items = new ArrayList<>();
		return;
	}
	
	/**
	 * Constructor Recommendation().
	 * 
	 * Creates a filled recommendation, with its identifier and its items.
	 * 
     * @param user The idenfiticator and the user which owns the recommendation.
     * @param items The arraylist of items that go into the recommendation.
     * 
     * @return A new recommendation with its id and items defined.
     */
	public Recommendation(Integer user, ArrayList<Integer> items) {
		this.user = user;
		this.DCG = 0.0;
		this.Items = items;
		return;
	}
	
	/**
	 * Constructor Recommendation().
	 * 
	 * Creates a recommendation from a string, which contains all the information needed to define the attributes.
	 * 
	 * @param data A string that contains the values to create a full new recommendation.
	 * 
	 * @return A new recommendation with its identifier, DCG and items defined.
	 */
	public Recommendation(String data) {
		this.user = -1;
		this.DCG = 0.0;
		this.Items = new ArrayList<Integer>();
		
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
		if(things.size() > 0)this.user = Integer.parseInt(things.get(0));
		if(things.size() > 1)this.DCG = Double.parseDouble(things.get(1));
		for(i=2;i<things.size();++i) {
			System.out.println(things.get(i));
			this.Items.add(Integer.parseInt(things.get(i)));
		}
		return;
	}
	
	/**
	 * Constructor toString().
	 * 
	 * Creates a string, container of all the information of this recommendation (identifier, DCG and items).
	 * 
	 * @return s String that contains the attributes of the recommendation, separated by "¬" (dollars).
	 */
	@Override
	public String toString() {
		String s = new String(this.user + "¬" + this.DCG);
		for(int i=0;i<this.Items.size();++i) {
			s = s + new String("¬" + this.Items.get(i));
		}
		return s;
	}
	
	//Getters
	
	/**
	 * Method getId().
	 * 
	 * Returns the identifier of the recommendation, which is also the identifier of the user owner of the recommendation.
	 * 
     * @return userId The identifier of the recommendation.
     */
	public Integer getId(){
		return this.user;
	}
	
	/**
	 * Method getUser().
	 * 
	 * Returns the identifier of the owner of this recommendation.
	 * 
     * @return userId The identifier of the recommendation.
	 */
	public Integer getUser() {
		return this.user;
	}
	
	/**
	 * Method getDCG().
	 * 
	 * Returns the result of applying the calculation for the value of some recommendation, in a given system with valuations.
	 * 
     * @return 
	 */
	public Double getDCG() {
		return this.DCG;
	}
	
	/**
	 * Method getItems().
	 * 
	 * Returns the array of identifiers of items of which set up this recommendation.
	 * 
     * @return items The array of items of the recommendation.
	 */
	public ArrayList<Integer> getItems() {
		return this.Items;
	}
	
	//Setters
	
	/**
	 * Method setId().
	 * 
	 * Sets the identifier of the recommendation with a new value, given by parameter.
	 * 
     * @param user The identifier of the recommendation, which is also the identifier of the user owner.
     */
	public void setId(Integer user) {
		this.user = user;
	}
	
	/**
	 * Method setUser().
	 * 
	 * Sets the identifier of the user owner of this recommendation.
	 * 
     * @param user The identifier of the user that will be the new owner of this recommendation.
     */
	public void setUser(Integer user) {
		this.user = user;
	}
	
	/**
	 * Method setDCG().
	 * 
	 * Sets the DCG of the recommendation.
	 * 
     * @param DCG The real non negative value of the DCG of this recommendation.
     */
	public void setDCG(Double DCG) {
		this.DCG = DCG;
	}
	
	/**
	 * Method setItems().
	 * 
	 * Sets the items of which this recommendation will be made of.
	 * 
     * @param items An array of identifiers of the items that set up the recommendation.
     */
	public void setItems(ArrayList<Integer> items) {
		this.Items = items;
	}
	
	/**
	 * Method addItem()
	 * 
	 * Adds an item, given by its identifier, to the end of the array of items.
	 * 
     * @param item An identifier of an already existing item.
     */
	public void addItem(Integer item) {
		this.Items.add(item);
	}
	
	//Static methods for the class
	
	/**
	 * Static method intersection().
	 * 
	 * Calculates the intersection of this recommendation and the one given by parameter (set intersection operation).
	 * The operation consists of finding the items that are in both recommendations.
	 * 
     * @param A First recommendation.
     * @param B Second recommendation.
     * 
     * @return I The intersection of the two recommendations A and B.
     */
	public static Recommendation intersection(Recommendation A, Recommendation B) {
		
		Recommendation I = new Recommendation(A.user);
		
		ArrayList<Integer> list1 = A.Items;
		ArrayList<Integer> list2 = B.Items;
		
		Collections.sort(list1);
		Collections.sort(list2);
		
		int i=0; int j=0;
		while(i<list1.size() && j<list2.size()) {
			if(list1.get(i) < list2.get(j)) {
				++i;
			}
			else if(list1.get(i) > list2.get(j)) {
				++j;
			}
			else {
				I.addItem(list1.get(i));
				++i;
				++j;
			}
		}
		return I;
	}
	
	/**
	 * Static method union().
	 * 
	 * Calculates the union of this recommendation and the one given by parameter (set operation).
	 * The operation consists of adding the items that appear in one recommendation or both (set union operation).
	 * 
     * @param A First recommendation.
     * @param B Second recommendation.
     * 
     * @return U The union of the two recommendations A and B.
     */
	public static Recommendation union(Recommendation A, Recommendation B) {
		
		Recommendation U = new Recommendation(A.user);
		
		ArrayList<Integer> list1 = A.Items;
		ArrayList<Integer> list2 = B.Items;
		
		Collections.sort(list1);
		Collections.sort(list2);
		
		int i=0; int j=0;
		while(i<list1.size() && j<list2.size()) {
			if(list1.get(i) < list2.get(j)) {
				U.addItem(list1.get(i));
				++i;
			}
			else if(list1.get(i) > list2.get(j)) {
				U.addItem(list2.get(j));
				++j;
			}
			else {
				U.addItem(list1.get(i));
				++i;
				++j;
			}
		}
		while(i<list1.size()) {
			U.addItem(list1.get(i));
			++i;
		}
		while(j<list2.size()) {
			U.addItem(list2.get(j));
			++j;
		}
		return U;
	}
}













