package org.jfree.data.test.DataUtilities;

import static org.junit.Assert.*;
import org.jfree.data.*;

import org.junit.Test;

public class DataUtilitiesEqualTest {

	@Test(timeout = 100)
	public void testEqualNoNull() {
		double[][] a = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0}
	        };
		double[][] b = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0}
	        };
		
		assertTrue("Arrays have equal values", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testEqualANull() {
		double[][] a = null;
		double[][] b = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0}
	        };
		
		assertFalse("Array A is null", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testEqualBNull() {
		double[][] a = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0}
	        };
		double[][] b = null;
		
		assertFalse("Array B is null", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testEqualBothNull() {
		double[][] a = null;
		double[][] b = null;
		
		assertTrue("Array A and B are null", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testEqualLongerA() {
		double[][] a = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0},
	            {1.0, 20.0, -3.0}
	        };
		double[][] b = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0}
	        };
		
		assertFalse("Array A longer than B", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testEqualLongerB() {
		double[][] a = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0}
	        };
		double[][] b = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0},
	            {1.0, 20.0, -3.0}
	        };
		
		assertFalse("Array B longer than A", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testEqualVarryRowSize() {
		double[][] a = {
		        {1.0, 2.0},
		        {3.0, 4.0, 5.0}
		    };
		    double[][] b = {
		        {1.0, 2.0},
		        {3.0, 4.0}
		    };
		    assertFalse("Arrays have different row lengths", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testNotEqual() {
		double[][] a = {
	            {1.0, 20.0, -3.0},
	            {-4.5, 50.5, 6.0}
	        };
		double[][] b = {
				{1.0, 2.0, -1.0},
	            {-9.5, 50.5, 6.0}
	        };
		
		assertFalse("Array values dont match", DataUtilities.equal(a, b));
	}
	
	// added for mutation tests
	
	@Test(timeout = 100)
	public void testEqualAEmpty() {
	    double[][] a = {};
	    double[][] b = {{1.0, 2.0, 3.0}};
	    assertFalse("Array A is empty but B is not", DataUtilities.equal(a, b));
	}

	@Test(timeout = 100)
	public void testEqualBEmpty() {
	    double[][] a = {{1.0, 2.0, 3.0}};
	    double[][] b = {};
	    assertFalse("Array B is empty but A is not", DataUtilities.equal(a, b));
	}

	@Test(timeout = 100)
	public void testEqualBothEmpty() {
	    double[][] a = {};
	    double[][] b = {};
	    assertTrue("Both arrays are empty", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testEqualWithOneNegativeSwitch() {
	    double[][] a = {
	        {1.0, 2.0, 3.0},
	        {4.0, 5.0, 6.0}
	    };
	    double[][] b = {
	        {-1.0, 2.0, 3.0},
	        {4.0, 5.0, 6.0}
	    };
	    assertFalse("Arrays should not be equal when 1.0 is replaced with -1.0", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testEqualWithEmptyArrays() {
		double[][] a = new double[0][];
	    double[][] b = new double[0][];
	    
	    assertTrue("Arrays should be equal when theyre both empty", DataUtilities.equal(a, b));
	}
	
	@Test(timeout = 100)
	public void testDifferentInnerArrays() {
	    double[][] a = {{1.0, 2.0, 3.0}, {3.0, 4.0, 4.0 }};
	    double[][] b = {{1.0, 2.0}, {3.0, 5.0}}; // Slight difference

	    assertFalse(DataUtilities.equal(a, b)); // Should return false
	}
	
//	@Test(timeout = 100)
//	public void testEqualAGreaterthanB() {
//		double[][] a = {
//	            {1.0}
//	        };
//		double[][] b = {
//	            {2.0}
//	        };
//		
//		assertFalse("Arrays have equal values", DataUtilities.equal(a, b));
//	}
//	
//	@Test(timeout = 100)
//	public void testEqualBGreaterthanA() {
//		double[][] a = {
//	            {2.0}
//	        };
//		double[][] b = {
//	            {1.0}
//	        };
//		
//		assertFalse("Arrays have equal values", DataUtilities.equal(a, b));
//	}


}
