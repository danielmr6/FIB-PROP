package test.domain;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Class RunnerTest.
 * <p>
 * <ul>
 * <li> This class is in charge of executing the methods of the UserTest class to check that everything works correctly.
 * <li> If there are any errors in the results, they will be detected and jumped when using the UserTest.class. 
 * </ul>
 * @author Daniel Morón Roces
 */
public class RunnerTest {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(UserTest.class);
		for(Failure failure: result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) System.out.println("The test was succesful");		
		else  System.out.println("The test was not succesful");		
	}
}
