package test.domain;

import domain.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
/**
 * Class UserTest.
 * <p>
 * 
 * <ul>
 * <li> This class checks if the User class methods are correct and all the results are successful.
 * <li> For each method of the User class we have a method that is executed and checks that the results are as expected, 
 *      if with the assertEquals function the expected result is different from the one obtained, the test will not be carried out successfully.
 * </ul>
 * 
 * @author Daniel Morón Roces
 */
public class UserTest {

	@Test
	public void getId() 
	{
		User u = new User(1);
		Integer result = u.getId();
		assertEquals(1, result, 0);
	}
	
	@Test
	public void getReview() 
	{
		
		User u = new User(1);
		u.addReview(1);
		u.addReview(2);
		u.addReview(3);
		
		ArrayList<Integer> resultV = u.getReview();
		for(int i = 0; i < 3; ++i) assertEquals(i+1, resultV.get(i), 0);
	}
	
	@Test
	public void setid() 
	{
		User u = new User(1);
		u.setId(7);
		Integer result = u.getId();
		assertEquals(7, result, 0);
		
	}
	
	@Test
	public void addReview() 
	{
		Integer new_val =  46;
		User u = new User(1);
		u.addReview(1);
		u.addReview(2);
		u.addReview(new_val);
		int n = u.getReview().size();
		assertEquals(46, u.getReview().get(n-1), 0);
	}	
	
	@Test
	public void compareCr()
	{
		User u1 = new User(2);
		User u2 = new User(1);
		assertEquals(1, u1.compareTo(u2), 0);
	}
	
	@Test
	public void checkToString()
	{
		User u = new User(1);
		u.addReview(1);
		u.addReview(2);
		assertEquals("The same string", u.toString(), "1¬1¬2");
	}
	
}

