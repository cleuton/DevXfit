package com.datalearninghub.demos.functional;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
    	 int [] a = {4,7,8,10,11,12,20,25};
    	 int [] b = {1,5,7,7,13,2,9,8,30,35,20,15};
    	 int [] commons = {7,8,20};
    	 App app = new App();
    	 int [] returnArray = app.findCommons(a,b);
    	 Arrays.sort(returnArray);
    	 assertTrue(Arrays.equals(returnArray, commons));
    }
    
    @Test
    public void shouldAlsoAnswerWithTrue()
    {
    	 int [] a = {4,7,8,10,11,12,20,25};
    	 int [] b = {1,5,7,7,13,2,9,8,30,35,20,15};
    	 int [] commons = {7,8,20};
    	 AppFunctional app = new AppFunctional();
    	 int [] returnArray = app.findCommons(a,b);
    	 Arrays.sort(returnArray);
    	 assertTrue(Arrays.equals(returnArray, commons));
    }
    
    @Test
    public void shouldIndeedAnswerWithTrue()
    {
    	 int [] a = {4,7,8,10,11,12,20,25};
    	 int [] b = {1,5,7,7,13,2,9,8,30,35,20,15};
    	 int [] commons = {7,8,20};
    	 AppMoreFunctional app = new AppMoreFunctional();
    	 int [] returnArray = app.findCommons(a,b);
    	 Arrays.sort(returnArray);
    	 assertTrue(Arrays.equals(returnArray, commons));
    }
    @Test
    public void AuContrarie()
    {
    	 int [] b = {4,7,8,10,11,12,20,25};
    	 int [] a = {1,5,7,7,13,2,9,8,30,35,20,15};
    	 int [] commons = {7,8,20};
    	 AppMoreFunctional app = new AppMoreFunctional();
    	 int [] returnArray = app.findCommons(a,b);
    	 Arrays.sort(returnArray);
    	 assertTrue(Arrays.equals(returnArray, commons));
    }
}
