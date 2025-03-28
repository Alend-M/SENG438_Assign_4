/**
 * 
 */
package org.jfree.data.test.DataUtilities;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.jfree.data.*;

import org.junit.Test;

/**
 * 
 */
public class DataUtilitiesCloneTest {

	@Test
    public void testClone() {
        double[][] source = {
            {1.0, 20.0, -3.0},
            {-4.5, 50.5, 6.0}
        };
        
        double[][] cloned = DataUtilities.clone(source);

        assertNotSame("should be separate instances", source, cloned);
        assertArrayEquals( "same numbers within array", source, cloned);
    }
	
	// added for mutation coverage
	
	@Test(timeout = 100)
	public void testCloneNullSource() {
		double[][] source = null;
		
		try {
			double[][] cloned = DataUtilities.clone(source);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            return;
        } catch (Exception e) {
            fail("Expected IllegalArgumentException, exception caught: " + e);
        }
	}
	
	@Test(timeout = 100)
	public void testCloneNullElements() {
		double[][] source = {
	            {1.0, 20.0, -3.0},
	            null
	        };
		
		double[][] cloned = DataUtilities.clone(source);
		
		assertNotSame("should be separate instances", source, cloned);
        assertFalse( "arrays dont match", Arrays.equals(source,cloned));		
	}
	
	

}
