package org.jfree.data.test.Range;

import static org.junit.Assert.*; 

import org.jfree.data.Range; 
import org.junit.*;

// Caleb Bourbonnais

public class RangeToStringTest {
	    private Range range1;
	    private Range range2;
	    private Range range3;
	    private Range range4;

    @Before
    public void setUp() { 
    	range1 = new Range(-100.0, 100.0);
    	range2 = new Range(-5.5, -2.0);
    	range3 = new Range(10.0, 100.0);
    	range4 = new Range(-20.0, -20.0);
 
    }

   
    // Test the toString() method with a Range object containing both negative and positive values.
    @Test
    public void toString_negative_and_positive() {
        assertEquals("Expecting string \"Range[lower,upper]\" where lower=lower range and upper=upper range.",
        "Range[-100.0,100.0]", range1.toString());
    }

    // Test the toString() method with a Range object containing both negative values.
    @Test
    public void toString_negative_and_negative() {
    	assertEquals("Expecting string \"Range[lower,upper]\" where lower=lower range and upper=upper range.",
    	"Range[-5.5,-2.0]", range2.toString());
    }

    // Test the toString() method with a Range object containing both positive values.
    @Test
    public void toString_positive_and_positive() {
    	assertEquals("Expecting string \"Range[lower,upper]\" where lower=lower range and upper=upper range.",
    	"Range[10.0,100.0]", range3.toString());
    }

    // Test the toString() method with a Range object where the lower bound equals the upper bound.
    @Test
    public void toString_no_difference() {
    	assertEquals("Expecting string \"Range[lower,upper]\" where lower=lower range and upper=upper range.",
    	"Range[-20.0,-20.0]", range4.toString());
    }
}
