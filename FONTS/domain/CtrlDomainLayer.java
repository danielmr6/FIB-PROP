package domain;
import java.util.*;

import data.*;

/**
 * Class Ctrl_domain_layer.
 * 
 * It's the controller of the domain layer and executes every operation available in the system.
 * 
 * There can only exist one instance of the class CtrlDomainLayer.
 * 
 * @version v1.0 | 07/11/2021
 * @author Daniel García Estévez
 * @author Guillem González Valdivia
 * @author Eloi Balaer Morales
 * @author Daniel Morón Roces
 */
public class CtrlDomainLayer{

	/*************************************************************************/
    /**************************Private atributtes*****************************/
    /*************************************************************************/
	
	/**
	 * Non static attribute Items.
	 * 
	 * Its default value is set to the void constructor (new TreeMap<Integer,Item>).
	 * Some examples can be: {(1, Item a),(2, Item b),(3, Item c)} or {(1642, Item z)}.
	 * 
	 * Array of Items sorted in increasing order by its id. It contains all the items of the same type stored in Data.
	 */
	private TreeMap<Integer,Item> Items;
	
	/**
	 * Non static attribute itemType.
	 * 
	 * Its default value is set to the void constructor of the class ItemType (new ItemType()).
	 * 
	 * A class itemType that contains the type of items we're dealing with, alongside with the diferent attributes of this type of items.
	 */
	private ItemType itemType;
	
	/**
	 * Non static attribute Users.
	 *  
	 * Its default value is set to the void constructor of the ArrayList structure (new ArrayList<_User_>()).
	 * 
	 * Array of users sorted increasingly by its id, and containing, for each user, its id and its reviews.
	 */
	private ArrayList<User> Users;
	
	/**
	 * Non static attribute active_user.
	 * 
	 * Its default value is set to -1.
	 * 
	 * Integer representing the id of the active user that is currently running the application. It is also a user in Users list.
	 */
	private Integer active_user;
	
	/**
	 * Non static attribute ActiveUsers.
	 * 
	 * Its default value is set to the void constructor of ArrayList (new ArrayList<_ActiveUsers_>()).
	 * 
	 * Every active user sorted in increasing order by its id. These active users can be administrator or not.
	 */
	private ArrayList<ActiveUser> ActiveUsers;
	
	/**
	 * Non static attribute activeRec.
	 * 
	 * Its default value is set to the void constructor of Recommendation (new Recommendation()).
	 * 
	 * Object of the type Recommendation that holds the current recommendation of the active user logged in the system.
	 */
	private Recommendation activeRec;
	
	/**
	 * Non static attribute Reviews.
	 * 
	 * Its default value is set to the void constructor of ArrayList (new ArrayList<_Reviews_>());
	 * 
	 * Array of Review that holds every review that one user has made to an item of the type of items itemType. Increasingly sorted by the id of user,
	 * then increasingly by the id of an item, and finally decreasingly by the valuation.
	 */
	private ArrayList<Review> Reviews;	//THIS MUST BE SORTED FOR THE LOVE OF GOD (by the criteria above)
	
	/**
	 * Non static attribute Unknown.
	 * 
	 * Its default value is set to the void constructor of ArrayList (new ArrayList<_Review_>());
	 * 
	 * Array of Review that holds the 10 most liked items of one user. It's information for testing. 
	 * It is sorted by the same criteria as the Reviews array.
	 */
	private ArrayList<Review> Unknown;
	
	/**
	 * Non static attribute Known.
	 * 
	 * Its default value is set to the void constructor of ArrayList (new ArrayList<_Review_>());
	 * 
	 * Array of Review that holds more reviews for the same items of the system. It's information for testing.
	 * It is sorted by the same criteria as the Reviews array.
	 */
	private ArrayList<Review> Known;
	
	/**
	 * Non static attribute strategy.
	 * 
	 * Integer representing the strategy we're using to generate the recommendations.
	 * 
	 * The possible values that can get are:
	 * 
	 * 0 to collaborative filtering.
	 * 1 to content based.
	 * 2 to hybrid approaches.
	 * -1 to set default case (no recommendation).
	 */
	private Integer strategy;
	
	/**
	 * Non static attribute ctrlData.
	 * 
	 * Its default value is set to the method CtrlDataLayer.getInstance().
	 * 
	 * Instance of the class CtrlDataLayer that communicates the domain layer with the data layer. It provides the program
	 * all the information stored in memory to create recommendations, give already made recommendations... And all the functionalities this
	 * application has.
	 */
	private CtrlDataLayer ctrlData;
	
	/**
	 * Non static attribute maxValuation.
	 * 
	 * Its default value is set to 0.0.
	 * 
	 * Integer that holds the maximum valuation for a fiven set of reviews. It's used to calculate the range of the possible 
	 * valuations that one type of item can hold.
	 */
	private Double maxValuation;
	
	/*************************************************************************/
    /****************************Constructors*********************************/
    /*************************************************************************/

    /**
     * Public void constructor CtrlDomainLayer().
     * 
     * @return A new instance of CtrlDomainLayer, with all its attributes to the default value.
     * 
     * This method creates a new instance of the class CtrlDomainLayer with all its attributes to the default value.
     * 
     * @author Guillem González Valdivia
     */
    public CtrlDomainLayer()
    {
    	this.Items = new TreeMap<Integer,Item>();
    	this.itemType = new ItemType();
    	this.Users = new ArrayList<>();
    	this.active_user = -1;
    	this.ActiveUsers = new ArrayList<>();
    	this.activeRec = new Recommendation();
    	this.Reviews = new ArrayList<Review>();
    	this.Known = new ArrayList<>();
    	this.Unknown = new ArrayList<>();
    	this.strategy = -1;
    	this.ctrlData = CtrlDataLayer.getInstance();
    	this.maxValuation = 0.0;
    }
    
    /**
     * Public constructor for testing in driver.
     * 
     * @return A new instance of CtrlDomainLayer, with all its attributes to the default value but with item type, active user and strategy given.
     * 
     * This method creates a new instance of CtrlDomainLayer with almost all of its attributes to the default value.
     * 
     * @author Guillem González Valdivia
     */
    public CtrlDomainLayer(String itemType, Integer activeUser, int strategy)
    {
    	this.Items = new TreeMap<Integer,Item>();
    	this.itemType = new ItemType(itemType,0);
    	this.Users = new ArrayList<>();
    	this.active_user = activeUser;
    	this.ActiveUsers = new ArrayList<>();
    	this.activeRec = new Recommendation();
    	this.Reviews = new ArrayList<Review>();
    	this.Known = new ArrayList<>();
    	this.Unknown = new ArrayList<>();
    	this.strategy = strategy;
    	this.ctrlData = CtrlDataLayer.getInstance();
    	this.maxValuation = 0.0;
    }
    
    
    /*************************************************************************/
    /*****************************Initialisers********************************/
    /*************************************************************************/
    
    /**
     * Public constructor for initialising the controller for the domain layer.
     * 
     * This method creates a new instance of CtrlDomainLayer class with default values on its attributes.
     * 
     * @author Daniel García Estévez
     */
    public void inicializeDomainLayer()
    {
    	dataLoad();
    }
    
    /*************************************************************************/
    /*************************Data Communication******************************/
    /*************************************************************************/
    
    /**
     * Public method dataLoad().
     * 
     * Method to charge the data from the .txt and .csv files into the program itself. It also sorts the information by diverse criteria.
     * 
     * @author Guillem González Valdivia
     */
    public void dataLoad() 
    {    	
    	//Item Type
    	
    	ArrayList<String> typesList = ctrlData.loadDataInfo("TypeItem" + this.itemType.getNameType());
    	this.itemType = new ItemType(typesList.get(0));
    	
    	//Items
    	this.Items.clear();
    	ArrayList<String> itemsList = ctrlData.loadDataInfo("Item" + this.itemType.getNameType());
    	for(int i=0;i<itemsList.size();++i) {
    		Item item = new Item(itemsList.get(i));
    		this.Items.put(item.getId(),item);
    	}
    	
    	//Users
    	
    	//LOOK FOR TROUBLE
    	this.Users.clear();
    	ArrayList<String> userList = ctrlData.loadDataInfo("User");
    	for(int i=0;i<userList.size();++i) {
    		User u = new User(userList.get(i));
    		this.Users.add(u);
    	}
    	
    	//Active Users
    	
    	this.ActiveUsers.clear();
    	ArrayList<String> activeList = ctrlData.loadDataInfo("ActiveUser");
    	for(int i=0;i<activeList.size();++i) {    		
    		ActiveUser actU = new ActiveUser(activeList.get(i));
    		this.ActiveUsers.add(actU);
    	}
    	
    	//Recommendations (WE ONLY CHARGE ONE AT THE TIME)
    	ArrayList<String> recommendList = ctrlData.loadDataInfo("Recommendation" + this.active_user);	//This has to change in store method
    	Recommendation rec = new Recommendation(recommendList.get(0));
    	this.activeRec = rec;
    	
    	//Reviews
    	this.Reviews.clear();
    	ArrayList<String> valorList = ctrlData.loadDataInfo("Review" + this.itemType.getNameType());
    	for(int i=0;i<valorList.size();++i) {
    		
      		Review val = new Review(valorList.get(i));
    		if(val.getValuation() > this.maxValuation) this.maxValuation = val.getValuation();
    		this.Reviews.add(val);
    	}
    	
    	//Known	
    	this.Known.clear();
    	ArrayList<String> knownList = ctrlData.loadDataInfo("Known" + this.itemType.getNameType());
    	for(int i=0;i<knownList.size();++i) {
    		Review val = new Review(knownList.get(i));
    		this.Reviews.add(val);
    		this.Known.add(val);
    	}
    	    	
    	//Unknown 	
    	this.Unknown.clear();
    	ArrayList<String> unknownList = ctrlData.loadDataInfo("Unknown" + this.itemType.getNameType());
    	for(int i=0;i<unknownList.size();++i) {
    		Review val = new Review(unknownList.get(i));
    		this.Unknown.add(val);
    	}
    	
    	//Compute other attributes
    	
    	//Computation of the average valuation AND the relation to rating users
    	for(int i=0;i<this.Reviews.size();++i) {
    		Integer item = this.Reviews.get(i).getItem();
    		Integer user = this.Reviews.get(i).getUser();
	    	ArrayList<Integer> newSet = this.Items.get(item).getRaterUsers();
	   		newSet.add(user);
	   		
	   		this.Items.get(item).setRaterUsers(newSet);
	   		this.Items.get(item).setAvarageReviews(this.Items.get(item).getAvarageReviewed() + this.Reviews.get(i).getValuation());
    	}
    	for(int i=0;i<this.Users.size();++i) {
    		ArrayList<Integer> items = this.Users.get(i).getReview();
    		boolean notValid = false;
    		for(int j=0;j<items.size() && !notValid;++j) {
    			if(this.Items.get(items.get(j)) == null) notValid = true;
    		}
    		if(notValid) {
    			this.Users.remove(i);
        		--i;
    		}
    	}
    	
    	for(Map.Entry<Integer, Item> ent : Items.entrySet()) {
    		Double average = this.Items.get(ent.getKey()).getAvarageReviewed();
    		average /= this.Items.get(ent.getKey()).getRaterUsers().size();
    		this.Items.get(ent.getKey()).setAvarageReviews(average);
    	}
    	
    	//WE HAVE TO SORT EVERYTHING IN ORDER TO HAVE THE METHODS OPERATING
    	
    	Collections.sort(this.Users);
    	Collections.sort(this.ActiveUsers);
    	Collections.sort(this.Reviews);
    	Collections.sort(this.Known);
    	Collections.sort(this.Unknown);
    	
    }
    
    /*************************************************************************/
    /**********************Recommendation algorithms**************************/
    /*************************************************************************/
    
    /**
     * Public method getAlgorithm().
     * 
     * It returns the strategy it's currently being used. As we say previously the meaning of attribute strategy is:
     * <ul>
     * <li>0 to collaborative filtering
	 * <li>1 to content based
	 * <li>2 to hybrid approaches
	 * <li>-1 to set default case (no recommendation)
	 * </ul>
     * @return strategy Returns the current strategy used when given recommendations.
     * @author Guillem González Valdivia
     */
    public Integer getAlgorithm() {
    	return this.strategy;
    }
    
    /**
     * Public method setAlgorithm().
     * 
     * Sets a new strategy, as we say previously the meaning of the attribute strategy is:
     * <ul>
     * <li>0 to collaborative filtering
	 * <li>1 to content based
	 * <li>2 to hybrid approaches
	 * <li>-1 to set default case (no recommendation)
	 * </ul>
     * @param alg Number of the algorithm to use. 
     * @author Guillem González Valdivia
     */
    public void setAlgorithm(Integer alg) {
    	this.strategy = alg;
    }
    
    /**
     * Public method recommend().
     * 
     * Method to produce a new recommendation. The main part of the program is executed by this procedure.
     * Following the value of this.strategy attribute, it provides the user a new recommendation made with the information stored in the program a the moment.
     * 
     * <p>
     * <ul>
     * <li> Collaborative filtering for value of strategy 0
     * <li> Content based filtering for value of strategy 1
     * <li> Hybrid approaches for value of strategy 2
     * </ul>
     * @author Daniel Morón Roces
     */
    public void recommend(){
    	
    	Recommendation A = new Recommendation();
		Recommendation B = new Recommendation();
    	Recommendation C = new Recommendation();
    	
    	switch(this.strategy) {
    		case 0:
    			A = this.collaborativeFiltering();
    			A.setDCG(this.calculateDCG(A));
    			
    			this.activeRec = A;
    			break;
    		case 1:
    			B = this.contentBased();
    			B.setDCG(this.calculateDCG(B));
    			
    			this.activeRec = B;
    			break;
    		case 2:
    			A = this.collaborativeFiltering();
    			B = this.contentBased();
    			
    			C =	Recommendation.union(A,B); //Recommendation.intersection(A, B); We may want to change this
    			C.setDCG(this.calculateDCG(C));
    			
    			this.activeRec = C;
    			break;
    		default:
    			break;
    	}
    	
    	System.out.print("The items recommended are: [" + this.activeRec.getItems().get(0));
    	for(int i=1;i<this.activeRec.getItems().size();++i) {
    		System.out.print(", " + this.activeRec.getItems().get(i));
    	}
    	System.out.println("]");
    	System.out.println("This recommendation's DCG is set to: " + this.activeRec.getDCG());
    }
    
    /**
     * Private method binarySearchReviewUser().
     * 
     * Returns the position of the first Review of the given user.
     * 
     * @param id Identifier of the user we're looking for.
     * 
     * @return The position of the first Review of the user id, if exists; -1 otherwise.
     * @author Guillem Gonzalez Valdivia
     */
    private Integer binarySearchReviewUser(Integer id) {
   
    	Integer l = 0; Integer r = this.Reviews.size()-1;
    	boolean found = false; Integer m = (r+l)/2;
    	while(!found && l<=r) {
    		m = (r+l)/2;
    		if(this.Reviews.get(m).getUser().equals(id)) {
	    		//We do a loop to find the first occurence with the idUser we're looking for
	    		while(m > 0 && this.Reviews.get(m-1).getUser().equals(id)) {
	    			--m;
	    		}
	    		found = true;
	    	}
    		else {
	    		if(this.Reviews.get(m).getUser() < id) {
	    			l = m+1;
	    		}
	    		else {
	    			r = m-1;
	    		}
	    	}
    	}
    	return m;
    }
    
    /**
     * Private method binarySearchUser().
     * 
     * Binary search for a position of a user in the array of users Users.
     * 
     * @param id.
     * @return m The position of the user in the list of users.
     * @author Guillem Gonzalez Valdivia
     */
    private Integer binarySearchUser(Integer id) {
    	   
    	Integer l = 0; Integer r = this.Users.size()-1;
    	boolean found = false; Integer m = (r+l)/2;
    	while(!found && l<=r) {
    		m = (r+l)/2;
    		if(this.Users.get(m).getId().equals(id)) {
	    		//We do a loop to find the first occurence with the idUser we're looking for
	    		found = true;
	    	}
    		else {
	    		if(this.Users.get(m).getId() < id) {
	    			l = m+1;
	    		}
	    		else {
	    			r = m-1;
	    		}
	    	}
    	}
    	return m;
    }
    
    /**
     * Private method binarySearchReviewUserItem().
     * 
     * Binary search for a position of a review that a user has made. If the review not exists returns -1. 
     * 
     * @param id identifier for the user we want to find its review.  
     * @param item identifier for the item we want to find its review.
     * @return An integer that's the position where the review is located. If not found returns -1.
     * @author Eloi Balaer Morales
     */
    private Integer binarySearchReviewUserItem(Integer id, Integer item) {
    	boolean found = false; 
    	Integer m = this.binarySearchReviewUser(id);
    	while(!found && !m.equals(-1)) {
    		if (!this.Reviews.get(m).getUser().equals(id)) {
    			m = -1;
    			found = true;
    		}
    		else if (this.Reviews.get(m).getItem().equals(item)) found = true;
    		else if (this.Reviews.get(m).getItem() > item) {
    			if (this.Reviews.get(m - 1).getItem() < item) {
    				m = -1;
    				found = true;
    			}
    			else --m;
    		}
    		else {
    			if (this.Reviews.get(m + 1).getItem() > item) {
    				m = -1;
    				found = true;
    			}
    			else ++m;
    		}
    	}
    	return m;
    }
    
    /**
     * Private method userDistance().
     * 
     * This method calculates the distance between two users given by its id's, using the reviews of the users.
     * 
     * @param id1 Identifier of the first user.
     * @param id2 Identifier of the second user.
     * @return dist Distance between the two users.
     * @author Guillem González Valdivia
     */
    private Double userDistance(Integer id1, ArrayList<Pair<Integer,Double>> val2){
    	
    	Double dist = 0.0; boolean found = false;
    	
    	    	
    	int v1 = this.binarySearchReviewUser(id1);	//First Review of the user id1
    	int v2 = 0;
    	
    	while((v1 < this.Reviews.size() && this.Reviews.get(v1).getUser().equals(id1)) && (v2 < val2.size()) ){
    		//For every Review of the two users, we compare every item and see if they valorated it both.
    		    		
    		if(this.Reviews.get(v1).getItem() < val2.get(v2).getKey()) {
    			++v1;
    		}
    		else if(this.Reviews.get(v1).getItem() > val2.get(v2).getKey()) {
    			++v2;
    		}
    		else {
    			found = true;
    			dist += Math.pow(val2.get(v2).getValue() - this.Reviews.get(v1).getValuation(), 2);	//Power of the difference between Reviews
    			++v1;
    			++v2;
    		}
    	}
    	
    	dist = Math.sqrt(dist);		//square root of the result of the algorithm    	
    	if(!found) {
    		dist = -1.0;
    	}
    	
    	return dist;				//We have the distance.
    }
    
    /**
     * Private method computeCentroid().
     * 
     * Methos to compute, using an array of users, a new imaginary centroid with minimum sum of distances to every user in the set given.
     * 
     * @param users {@link ArrayList} of user identifiers of a subset from a partition of k-means.
     * @return centroid The centroid of the given array.
     * @author Guillem González Valdivia
     */
    private Pair<Integer,ArrayList<Pair<Integer,Double>>> computeCentroid(ArrayList<Integer> users) {
    	
    	//CÁLCULO: Tenemos que crear un centroide ficticio que es un usuario ficticio del sistema, y a partir de ahí
    	//Calculamos la media de todas las valoraciones, que el cálculo será que valora todo lo que han valorado los usuarios
    	//Y los items que ha valorado más de un usuario hará la media de valoraciones
    	
    	//Sabemos que, cuando a todo usuario en la iteración iésima, se le asigna el mismo cluster en la iteración
    	//(i+1)ésima, hemos acabado de calcular k means
    	
    	//CENTROID: Para un usuario ficticio getKey y sus valoraciones getValue, en getValue tenemos el id del item que ha valorado,
    	//La valoración media del ítem (que hasta el final es suma de valoraciones) y el número de veces que los usuarios han valorado el ítem
    	
    	Pair<Integer,ArrayList<Pair<Pair<Integer,Double>,Integer>>> centroid = 
    			new Pair<Integer,ArrayList<Pair<Pair<Integer,Double>,Integer>>>(-1, new ArrayList<Pair<Pair<Integer,Double>,Integer>>());
    	
    	for(int i=0;i<users.size();++i) {
    		
    		Integer val1 = 0;
    		Integer val2 = this.binarySearchReviewUser(users.get(i));
    		Pair<Pair<Integer,Double>,Integer> data = new Pair<Pair<Integer,Double>,Integer>();
    		
    		while((val1 < centroid.getValue().size()) && (val2 < this.Reviews.size() && this.Reviews.get(val2).getUser().equals(users.get(i)))) {
    			if(centroid.getValue().get(val1).getKey().getKey() < this.Reviews.get(val2).getItem()) {
    				++val1;
    			}
    			else if(centroid.getValue().get(val1).getKey().getKey() > this.Reviews.get(val2).getItem()) {
    				data.setKey(new Pair<Integer,Double>(this.Reviews.get(val2).getItem(), this.Reviews.get(val2).getValuation()));
    				data.setValue(1);
    				centroid.getValue().add(val1,data);
    				++val1;
    				++val2;
    			}
    			else {
    				//Hay una valoración más del ítem, hay que añadirlo y aumentar el contador
    				centroid.getValue().get(val1).getKey().setValue(centroid.getValue().get(val1).getKey().getValue() + 
    						this.Reviews.get(val2).getValuation());
    				centroid.getValue().get(val1).setValue(centroid.getValue().get(val1).getValue()+1);
    				++val1; ++val2;
    			}
    		}
    		
    		//LAS VALORACIONES RESTANTES QUE HAN QUEDADO
    		while(val2 < this.Reviews.size() && this.Reviews.get(val2).getUser().equals(users.get(i))) {
    			data.setKey(new Pair<Integer,Double>(this.Reviews.get(val2).getItem(), this.Reviews.get(val2).getValuation()));
				data.setValue(1);
				centroid.getValue().add(data);
				++val2;
    		}
    	}
    	
    	Pair<Integer,ArrayList<Pair<Integer,Double>>> centFinal = new Pair<Integer,ArrayList<Pair<Integer,Double>>>(-1, new ArrayList<Pair<Integer,Double>>());
    	for(int i=0;i<centroid.getValue().size();++i) {
    		Pair<Integer,Double> pair = new Pair<Integer,Double>();
    		pair.setKey(centroid.getValue().get(i).getKey().getKey());
    		pair.setValue(centroid.getValue().get(i).getKey().getValue() / centroid.getValue().get(i).getValue());
    		centFinal.getValue().add(pair);
    	}
    	
    	centFinal.setKey(-1);
    	
    	return centFinal;
    }
    
    /**
     * Private method collaborativeFiltering().
     * 
     * One of the two algorithms to generate a new recommendation. Applying K-means and Slope one, this method creates a new recommendation
     * by doing this: First, it computes k clusters of users with minimum distance between them and associates the active user with one
     * cluster; then uses the users of the cluster related to the active user to calculate an approximated estimation to the reviews the
     * active user could make to a set of items that the users of the cluster have reviewed. Finally, inserts the items of maximum expected
     * valuation into the recommendation.
     * 
     * @return Recommendation R aplying collaborative filtering.
     * @author Guillem González Valdivia
     */
    private Recommendation collaborativeFiltering() {
    	
    	/***********THIS LITTLE MANEUVER IS GOING TO COST US 51 YEARS****************/
    	
    	Recommendation R = new Recommendation(this.active_user);
    	int k = 5;
    	int num_rec = 10;
    	
    	//Algorithm
    	
    	/*********K-MEANS*********/
    	
    	//We compute the first k centroids generated randomly
    	ArrayList<Pair<Integer,ArrayList<Pair<Integer,Double>>>> centroids = new ArrayList<Pair<Integer,ArrayList<Pair<Integer,Double>>>>();
    	
    	//Para los centroides, tendremos para cada posición del array, el identificador del usuario, y un arraylist con 
    	//Los items que ha valorado y su puntuación.
    	
    	while(centroids.size()<k) {
    		Random r = new Random();
    		Integer c = r.nextInt(this.Users.size());
    		c = this.Users.get(c).getId();
    		boolean found2 = this.binarySearchReviewUser(c).equals(-1);
    		boolean found = false;
    		for(int i=0;i<centroids.size();++i) {
    			if(centroids.get(i).getKey().equals(c)) found = true;
    		}
    		if(!found && !found2) centroids.add(new Pair<Integer,ArrayList<Pair<Integer,Double>>>
    		(c, new ArrayList<Pair<Integer,Double>>()));
    	}
    	
    	for(int c = 0;c<centroids.size();++c) {
    		int val = this.binarySearchReviewUser(centroids.get(c).getKey());
    		while(val < this.Reviews.size() && this.Reviews.get(val).getUser().equals(centroids.get(c).getKey())) {
				Pair<Integer,Double> pair = new Pair<Integer,Double>(this.Reviews.get(val).getItem(), this.Reviews.get(val).getValuation());
    			centroids.get(c).getValue().add(pair);
    			++val;
    		}
    	}
    	    	
    	//We now have the initial centroids for the users. Now we have to compare every user with the centroids,
    	//relate every user to a subset related to a certain centroid, recalculate the centroid and compare the new
    	//centroids (i+1) to the previous centroids (i). If they are the same, we have found the partition of users
    	//and we now associate the active user to one subset of the partition --> solution!
    	
    	boolean found = false;		//found will be true when we find the solution to k-means
    	ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
    	
    	//Vector de enteros que indica a qué subset se ha ido el usuario iésimo. 
    	//Lo usamos para la comprobación de si se ma movido de grupo o no
    	ArrayList<Pair<Integer,Double>> userSubset = new ArrayList<Pair<Integer,Double>>();
    	
    	for(int u=0;u<this.Users.size();++u){
    		userSubset.add(new Pair<Integer,Double>(-1,Double.MAX_VALUE));
    	}
    	
    	while(!found) {
    		subsets.clear();
    		for(int s=0;s<k;++s) {
    			subsets.add(new ArrayList<Integer>());
    		}
    		
    		found = true;
    		
    		//Ahora el centroide no está en su subset dentro de la partición, como el elemento 0
    		    		    		
    		for(int i=0;i<this.Users.size();++i) {	//For every user i we insert it to be compared to every centroid
    			Integer id = this.Users.get(i).getId();
    			Double minDist = Double.MAX_VALUE;
    			Integer jMin = -1;
    			boolean valid = !this.binarySearchReviewUser(id).equals(-1);
    			Double distance = -1.0;
    			Double distanceAnt = -1.0;
    			for(int j=0;j<k && valid;++j) {				//For every centroid j we compare i with j	    				
    				//Le da bien los usuarios, hya algo que no cuadra
    				
    				//Ahora el cálculo de distancia con los centroides recibe el id del usuario a comparar
    				//Y un pair de identificador del centroide y un array de sus items valorados con la nota
    				
    				distance = this.userDistance(id, centroids.get(j).getValue());
    				//if(userSubset.get(i).getKey().equals(j)) distanceAnt = distance;
    				
    				if(distance != -1.0 && distance < minDist) {
    					minDist = distance;
    					jMin = j;
    				}
    			}
    			if(!jMin.equals(-1)) { 
    				/*
    				subsets.get(jMin).add(id);
    				if (!userSubset.get(i).getKey().equals(jMin)) {
    					userSubset.get(i).setKey(jMin);
    					found = false;
    					usuarios_mueven.put(id, jMin);
    				}
    				*/
    				
      				if(!jMin.equals(userSubset.get(i).getKey())) {
    					if(userSubset.get(i).getValue() > distance) {
    						userSubset.set(i, new Pair<Integer,Double>(jMin, distance));
    						subsets.get(jMin).add(id);
    						found = false;
    					}
    					else {
    						subsets.get(userSubset.get(i).getKey()).add(id);
    						userSubset.get(i).setValue(distanceAnt);
    					}
    				}
    				else {
    					subsets.get(jMin).add(id);
    					userSubset.get(i).setValue(distance);
    				}
    				
    			}

    		}
    		    		
    		//At this point, we have the partition of elements generated from the centroids given
    		//Now we compute the centroid of every subset and we put them into the centroids array, in given order
    		
    		for(int i=0;i<k;++i) {
    			Pair<Integer,ArrayList<Pair<Integer,Double>>> centi = this.computeCentroid(subsets.get(i));			
    			centroids.set(i, centi);
    		}	
    	}
    	
    	found = false;
    	int pos = 0;
    	int pos_j = 0;
    	for(int i = 0;i < k; ++i) {
    		for(int j = 0; j < subsets.get(i).size(); ++j) {
    			if(subsets.get(i).get(j).equals(this.active_user)) {
    				pos = i;
    				pos_j = j;
    			}
    		}
    	}
    	
    	ArrayList<Integer> kMeans = subsets.get(pos);			//We get the subset of users that are related to the active user
    	kMeans.remove(pos_j);									//We erase the active user from the set of users
    	
    	
    	//kMeans CONTAINS THE SUBSET OF USERS RELATED TO THE ACTIVE USER
    	
    	/*******SLOPE ONE*********/
    	
    	int i = 0;
    	int j = 0;
    	//Obtenemos u: vector con valoraciones de los usuarios obtenidos por el k means
    	//SE PUEDE OPTIMIZAR USANDO BINARY SEARCH
    	ArrayList<ArrayList<Pair<Integer,Double>>> u = new ArrayList<ArrayList<Pair<Integer,Double>>>(); //int es id item y double la valoracion
    	for(i=0; i < kMeans.size();++i) {
    		u.add(new ArrayList<Pair<Integer, Double>>());
    		
    		//HAY QUE OPTIMIZARLO PORQUE DA ASCO
    		
    		for(j=0; j < Reviews.size();++j) {
    			if(Reviews.get(j).getUser().equals(kMeans.get(i))) {
    				u.get(i).add(new Pair<Integer,Double>(Reviews.get(j).getItem(),Reviews.get(j).getValuation()));
    			}
    		}	
    	}
    	
    	ArrayList<Pair<Integer,Double>> uActive = new ArrayList<Pair<Integer,Double>>();	//Valoraciones del usuario activo
    	Double uMeanActive = 0.0;															//Media de las valoraciones del usuario activo
    	int itActive = this.binarySearchReviewUser(active_user);
    	while(itActive != -1 && itActive<this.Reviews.size() && this.Reviews.get(itActive).getUser() == this.active_user) {
    		uActive.add(new Pair<Integer,Double>(this.Reviews.get(itActive).getItem(),this.Reviews.get(itActive).getValuation()));
    		uMeanActive += this.Reviews.get(itActive).getValuation();
    		++itActive;
    	}
    	uMeanActive /= uActive.size();
    	
    	//Media de las valoraciones y S(u)
    	ArrayList<Double> averagesU = new ArrayList<Double>();			//Vector de medias de los usuarios del kmeans
    	ArrayList<ArrayList<Integer>> sU = new ArrayList<ArrayList<Integer>>();		//Vector bidimensional que guarda, para cada user del kmeans, los items que le han gustado
    	
    	for(i=0; i < u.size(); ++i) {
    		sU.add(new ArrayList<Integer>());
    		averagesU.add(0.0);
    		for(j=0; j < u.get(i).size(); ++j) {
    			averagesU.set(i, averagesU.get(i)+u.get(i).get(j).getValue());
    			sU.get(i).add(u.get(i).get(j).getKey());
    		}
    		averagesU.set(i, averagesU.get(i)/u.get(i).size());
    	}
    	
    	Integer cardS = 0;
    	//Esta mal
    	for(i=0;i<sU.size();++i) {
    		cardS += sU.get(i).size();
    	}
    	
    	ArrayList<ArrayList<Pair<Integer,Double>>> Chi = new ArrayList<ArrayList<Pair<Integer,Double>>>();
    	for(i=0;i<kMeans.size();++i) {
    		Chi.add(new ArrayList<Pair<Integer, Double>>());
    		int it = this.binarySearchReviewUser(kMeans.get(i));	//Buscamos la primera aparición del usuario en valoraciones
    		while(it<this.Reviews.size() && this.Reviews.get(it).getUser().equals(kMeans.get(i))){		//Añadimos toda valoración
    			Chi.get(i).add(new Pair<Integer,Double> (this.Reviews.get(it).getItem(), this.Reviews.get(it).getValuation()));
    			++it;
    		}
    	}
    	
    	//S y SX estan juntos
    	TreeMap<Integer, ArrayList<Integer>> S = new TreeMap<Integer, ArrayList<Integer>>();	//Ya tenemos todos los items valorados por los users del kMeans
    	for(i=0;i<sU.size();++i) 
    		for(j=0;j<sU.get(i).size();++j) 
    			S.put(sU.get(i).get(j), new ArrayList<Integer>());
    
    	for (Map.Entry<Integer, ArrayList<Integer>> it : S.entrySet() ) {
    		ArrayList<Integer> list = new ArrayList<Integer>();
    		for (i = 0; i < this.Reviews.size(); ++i) {
    			if (it.getKey().equals(Reviews.get(i).getItem())) list.add(Reviews.get(i).getUser());
    		}
    		it.setValue(list);
    	}
    	
    	/**COMPUTATION OF THE SLOPE ONE ALGORITHM**/
    	
    	
    	//Primero conseguimos la desviación mediana de todos los items
    	ArrayList<ArrayList<Double>> deviations = new ArrayList<ArrayList<Double>>();
    	
    	for(i=0;i<S.size();++i) {
    		deviations.add(new ArrayList<Double>());
    		for (j = 0; j < S.size(); ++j) deviations.get(i).add(0.0);
    	}
    	
    	int pos_i = 0;
    	pos_j = 0;
    	for (Map.Entry<Integer, ArrayList<Integer>> it1 : S.entrySet()) {
    		for (Map.Entry<Integer, ArrayList<Integer>> it2 : S.entrySet()) {
    			ArrayList<Integer> val_it1 = new ArrayList<Integer>();	//users that evaluate this item S[j]
    			ArrayList<Integer> val_it2 = new ArrayList<Integer>();	//users that evaluate this item S[i]
    			
    			for (i = 0; i < it1.getValue().size(); ++i) val_it1.add(it1.getValue().get(i));
    			for (i = 0; i < it2.getValue().size(); ++i) val_it2.add(it2.getValue().get(i));
    			
    			i = j = 0;
    			
    			ArrayList<Integer> val_it1_it2 = new ArrayList<Integer>();
    			
    			while (i < val_it1.size() && j < val_it2.size()) {
    				if(val_it1.get(i) < val_it2.get(j)) ++i;
    				else if (val_it1.get(i) > val_it2.get(j)) ++j;
    				else {
    					val_it1_it2.add(val_it1.get(i));
    					++i;
    					++j;
    				}
    			}
    			for (i = 0; i < val_it1_it2.size(); ++i) {
    				int it1_Val = this.binarySearchReviewUserItem(val_it1_it2.get(i), it1.getKey()); //Position of the Review for j and user valji[l]
    				int it2_Val = this.binarySearchReviewUserItem(val_it1_it2.get(i), it2.getKey()); //Position of the Review for j and user valji[l]
    				Double d = deviations.get(pos_i).get(pos_j);
    				d += Math.abs(this.Reviews.get(it1_Val).getValuation() - this.Reviews.get(it2_Val).getValuation() / val_it1_it2.size());
    				deviations.get(pos_i).set(pos_j, d);
    			}
    			++pos_j;
    			if (pos_j == S.size()) {
    				++pos_i;
    				pos_j = 0;
    			}
    		}
    	}
    
    	//Now we have the deviations for all the items
    	    	
    	ArrayList<Pair<Integer,Double>> expect = new ArrayList<Pair<Integer,Double>>();
    	i = 0;
    	for (Map.Entry<Integer, ArrayList<Integer>> it : S.entrySet()) {
    		Double loop = 0.0;									//Variable to hold the calculation of the sums of the deviations, and then divide between card(S(u))
    		for(j = 0; j < deviations.get(i).size(); ++j) {	//We compute the sum of the deviations of the type dj,i for every item i
    			loop += deviations.get(i).get(j);
    		}
    		loop /= uActive.size();			//We divide between the number of items this user has reviewed
    		loop += uMeanActive;
    		expect.add(new Pair<Integer,Double>(it.getKey(), loop));
    		++i;
    	}
    	
    	Collections.sort(expect);
    	for(i = 0; i < num_rec; ++i) {
    		R.addItem(expect.get(i).getKey());
    	}
    	
    	return R;
    	
    }
    
    /**
     * Private method contentBased().
     * 
     * The second and last of the algorithms to generate a new recommendation.
     * 
     * This method applies K-nearest neighbours. The algorithm gets the items liked by the user (the criteria to see of an item is liked
     * by the active user it that if the valuation given to an item is equal or higher than the maximum valuation possible to give divided by 2).
     * Then, using these items, we get the k closest items to those of the list returned before and we create a recommendation with these items.
     * 
     * @return Recommendation R obtained from the current recommendation aplying content-based
     * @author Eloi Balaer
     * @author Guillem González Valdivia
     */
    private Recommendation contentBased() {
    	
    	Recommendation R = new Recommendation();
    	
    	//Algorithm
    	//Primero buscamos los items que le gustan al usuario activo,
    	//lo miramos con las valoraciones más altas que ha hecho este señor.
    	//ItemsValorats es un pair de la id de los items y su valoracion. 
    	//asignamos k
    	int k = 10;
    	ArrayList<Pair<Integer,Double>> ItemsAgradats = new ArrayList<>();
    	for(int i = 0; i < Reviews.size(); ++i) {
    		if(Reviews.get(i).getUser().equals(active_user) && Reviews.get(i).getValuation() >= this.maxValuation/2.0) {
    			Pair<Integer,Double> p = new Pair<Integer,Double>(Reviews.get(i).getItem(), Reviews.get(i).getValuation());
    			ItemsAgradats.add(p);
    		}
    	}
    	//Ahora tocaria calcular cuales de estos items le gustan al active user
    	//mi idea es pillar los q tengan val > 4 por ejemplo
    	//NUEVA IDEA, TENER EN CUENTA LAS VALORACIONES MAS ALTAS Y DARLES PRIORIDAD
    	
    	if(ItemsAgradats.size() == 0) {
    		//cuidao que si no le gusta nada no se que pasa
    		//permutacion aleatoria
    		
    		Random Rand = new Random();
    			
    		ArrayList<Integer> rPerm = new ArrayList<>();	//Array to contain the random permutation of initial items for the given active user
    		for(int g = 0; g < k; ++g) {
    			Integer r = Rand.nextInt(this.Items.size());
    			rPerm.add(r);
    		}
    		Collections.sort(rPerm);
    		Set<Integer> cole = Items.keySet();
    		Integer actual = 0;
    		Integer posJ = 0;
    		Iterator<Integer> iter = cole.iterator();
    		while(iter.hasNext() && actual<k) {
    			if(rPerm.get(actual).equals(posJ)) {
    				R.addItem(iter.next());
    				++actual;
    					
    			}
    			else {
    				iter.next();
    			}
    			++posJ;
    		}
    			
    	}
    	else {
    	//Ahora tenemos el conjunto de los items que le gustan, segun nuestro criterio.
    	//Debemos ejecutar el algoritmo para todo objeto.
    	//Voy a suponer que k es 5 ahora mismo, en el futuro quizas cambiarla a raiz de size del conjunto items.
    	//Para todo item agradat, busco los k items(en todo el conjunto de items) que se le parezcan mas.
    		PriorityQueue<Pair<Integer, Double>> pq = new PriorityQueue<>();
    		for(int i = 0; i < ItemsAgradats.size(); ++i) {
    			ArrayList<Pair<Integer, Double>> pp = new ArrayList<Pair<Integer, Double>>();
    			Integer idAgr = (Integer)ItemsAgradats.get(i).getKey();
    			Item x = Items.get(idAgr);
    				//mirar los atributos del item
    				//comparar con todos los items del sistema
    				//retornar k items mas parecidos 
    			for(Map.Entry<Integer,Item> entry : Items.entrySet()) {
    				if(!entry.getKey().equals(idAgr)) {
	    				ArrayList<Integer> rated = new ArrayList<>();
	    				for(int j=0; j<this.Users.size();++j) {
	    					if(this.Users.get(j).getId().equals(this.active_user)) rated = this.Users.get(j).getReview();
	    				}
	    				boolean found = true;
	    				for(int j=0;j<rated.size();++j) {
	    					if(rated.get(j).equals(entry.getKey())) found = false;
	    				}
	    				if(found) {
	    					// el priority queue que contenga las id de los items y la distancia al item original
	    					//ya esta implementada la ultima linea del tercer parrafo de la documentacion que consiste 
	    					//en dar valor a las valoraciones al calcular el algoritmo
	    					Double dist = x.distance(entry.getValue()) * Math.pow(entry.getValue().getAvarageReviewed(), -1.0);
	    					pp.add(new Pair<Integer,Double>(entry.getKey(),dist));
	    				}
    				}
    			}
    			Collections.sort(pp);
    			int pos = 0;
    			while(pos<pp.size()) {
    				if(pos < pp.size()-1 && pp.get(pos).getKey().equals(pp.get(pos+1).getKey())) pp.remove(pos+1);
    				else pos++;
    			}
    			for(int h = 0; h < k; ++h) {
    				pq.add(pp.get(h));
    			}
    		}
    		//actualmente la cola de prioridad que devuelve es el conjunto de items con sus distancias
    		//donde hay k*size(itemsagradats) elementos
    		//para todos los elementos de la pq
    		
    		int num = 0;
    		while(!pq.isEmpty() && num<k){
    			R.addItem(pq.poll().getKey());
    			++num;
    		}
    		
    	}
    	R.setId(this.active_user);
    	return R;
    }
    
    /**
     * Private method calculateDCG().
     * 
     * This method calculates the quality of the new recommendation. Given a recommendation, it compares the items of the recommendation
     * with the items in Unknown list or reviews
     * 
     * @param R New recommendation computed with collaborative filtering, content based or hybrid approaches.
     * @return DCG Real number which gives the quality of the current recommendation in the system.
     * @author Daniel García Estévez
     */
    private Double calculateDCG(Recommendation R) {
    	
    	//Explicación del algoritmo:
    	
    	//Cuando a un usuario le das una recomendación, este va evaluando los items hasta que todos están evaluados.
    	//Cuando pasa eso, calculamos cuán relevantes son los items recomendados para él, así como si el órden dado ha sido el correcto
    	//(los más importantes antes). Manteniendo el órden de los ítems de la recomendación, vemos qué valoración tendría en la vida real (Unknown)
    	//que sería reli, mientras que i es la posición del item en la recomendación, de tal manera que cuanto más pequeño mejor, porque se supone que tiene 
    	//mayor prioridad.
    	
    	//Obtaining the LR list of Reviews of the items given by the recommendation
    	//We suppose that every array is sorted by item(increasingly),user(increasingly),value(decreasingly)
    	
    	ArrayList<Pair<Integer,Double>> LR = new ArrayList<>();
    	ArrayList<Pair<Integer,Double>> LT = new ArrayList<>();
    	
    	for(int i = 0; i < R.getItems().size(); ++i) LR.add(new Pair<Integer,Double>(R.getItems().get(i), 0.0));

    	for (int i = 0; i < this.Unknown.size(); ++i) 
    		if (this.Unknown.get(i).getUser().equals(active_user)) 
    			LT.add(new Pair<Integer, Double>(this.Unknown.get(i).getItem(), this.Unknown.get(i).getValuation()));
    	
    	
    	for(int i = 0; i < LR.size(); ++i) {
    		int k = this.binarySearchReviewUserItem(active_user, LR.get(i).getKey());
    		if (k != -1) LR.get(i).setValue(this.Reviews.get(k).getValuation());
    		else LR.get(i).setValue(0.0);
    	}
    	Collections.sort(LR);
    	Collections.sort(LT);


    	
    	//Algorithm
    	Double DCG = 0.0;
    	int pos = 1;
    	for(int i = 0; i < LR.size(); ++i) {
    		if(i != 0 && !LR.get(i).getValue().equals(LR.get(i-1).getValue())) pos++;	//We say that the next item is one position far from the previous one
    		
    		//Find the valuation in the Unknown list of valuations
    		int k = 0; 
    		boolean found = false;
    		Double reli = 0.0;
    		while(k < LT.size() && !found) {
    			if(LT.get(k).getKey().equals(LR.get(i).getKey())) {
    				reli = LT.get(k).getValue();
    				found = true;
    			}
    			++k;
    		}
    		//Compute the value for this item i
    		DCG += (Math.pow(2, reli)-1.0) / (Math.log(pos+1) / Math.log(2));
    	}
    	return DCG;
    }
    
    /************Everything that is below here is not implemented*************/
    
    /*************************************************************************/
    /*****************************User cases**********************************/
    /*************************************************************************/
    
    
    public void dataStore() 
    {
    	//Type of items --
    	ArrayList<String> typeI = new ArrayList<String>();
    	typeI.add(this.itemType.toString());
    	ctrlData.storeDataInfo("TypeItem" + this.itemType.getNameType(), typeI);
    	
    	//Items -- ARREGLAR PORQUE SON MAPS
    	ArrayList<String> itemsList = new ArrayList<String>();
    	for(Map.Entry<Integer, Item> ent : Items.entrySet()) {
    		String s = ent.getValue().toString();
    		itemsList.add(s);
    	}
    	ctrlData.storeDataInfo("Item" + this.itemType.getNameType(), itemsList);
    	
    	//Users --
    	ArrayList<String> userList = new ArrayList<String>();
    	for(int i=0;i<this.Users.size();++i) {
    		String s = this.Users.get(i).toString();
    		userList.add(s);
    	}
    	ctrlData.storeDataInfo("User", userList);
    	
    	//Active Users --
    	ArrayList<String> activeList = new ArrayList<String>();
    	for(int i=0;i<this.ActiveUsers.size();++i) {
    		String s = this.ActiveUsers.get(i).toString();
    		activeList.add(s);
    	}
    	ctrlData.storeDataInfo("ActiveUser", activeList);
    	
    	//Recommendation --
    	ArrayList<String> recommendList = new ArrayList<String>();
    	recommendList.add(this.activeRec.toString());
    	ctrlData.storeDataInfo("Recommendation" + this.active_user, recommendList);
    	
    	//Reviews --
    	ArrayList<String> ReviewList = new ArrayList<String>();
    	for(int i=0;i<this.Reviews.size();++i) {
    		String val = this.Reviews.get(i).toString();
    		ReviewList.add(val);
    	}
    	ctrlData.storeDataInfo("Review" + this.itemType.getNameType(), ReviewList);
    }
    
    public void valorate()
    {

    }

    public void buy()
    {

    }

    public void wishlist()		//Anterior afegir_m'agrada
    {

    }

    public void selectItem()
    {

    }

    public void addItem()
    {

    }

    public void modifyItem()
    {

    }

    public void removeItem()
    {

    }

    public void createProfile()
    {

    }

    public void removeProfile()
    {

    }

    public void modifyPassword()
    {

    }

    public void log_in()
    {
    	
    }

    public void viewProfile()
    {

    }

    public void saveSession()
    {

    }

    public void exitWithoutSaving()
    {

    }

    public void exitAndSave()
    {

    }

    public void saveEverything()
    {

    }

    public void modifyReview()
    {

    }

    public void removeReview()
    {

    }

    public void addReview()
    {

    }

}
