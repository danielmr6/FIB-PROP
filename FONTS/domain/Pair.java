package domain;

/**
 * The pair class represents a container for a pair of data. 
 * Each pair has a key and a value.
 * @author Guillem González Valdivia 
 */
public class Pair<T1, T2> implements Comparable<Pair<T1,T2>>{
	
	/**
	 * Attribute key
	 * 
	 * We store the value of key.
	 */
    private T1 key;
    
    /**
     * Attribute value
     * 
     * We store the value of value.
     */
    private T2 value;
    
    /**
     * Build an empty pair.
     */
    public Pair()
    {
    	this.key = null;
    	this.value = null;
    }
    
    /**
     * Build a pair with key == key and value = value.
     * @param key The new key por the pair.
     * @param value The new value for the pair.
     */
    public Pair(T1 key, T2 value)
    {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Returns the key of the pair.
     * @return Returns the key of the pair.
     */
    public T1 getKey() 
    {
        return key;
    }
    
    /**
     * Returns the value of the pair.
     * @return Returns the value of the pair.
     */
    public T2 getValue() 
    {
        return value;
    }
    
    /**
     * Update the pair's key.
     * @param key The new key for the pair.
     */
    public void setKey(T1 key) 
    {
        this.key = key;
    }

    /**
     * Update the pair's value.
     * @param value The new value for the pair.
     */
    public void setValue(T2 value) 
    {
        this.value = value;
    }

    
	@Override
	/**
	 * A function that compares two pairs, this is inherited from the Comparable Interface.
	 * @param o The pair that we wanna compare to.
	 */
	public int compareTo(Pair<T1, T2> o) {
		return ((int)((Double)this.value*10) - (int)((Double)o.value*10));
	}
	
	/**
	 * Returns the result of looking to see if one pair is equal to another.
	 * @param p The pair that we wanna compare to.
	 * @return Retuns true if yhis.pair is equals to p, false otherwise.
	 */
	public boolean equals(Pair<T1, T2> p)
	{
		return this.key == p.getKey() && this.value == p.getValue();
	}
}