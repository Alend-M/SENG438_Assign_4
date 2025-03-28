package org.jfree.data.test.Range;
import static org.junit.Assert.*;
import org.jfree.data.*;

import org.junit.Before;
import org.junit.Test;

public class RangeGetLengthTest {
	
	private Range range1;
	private Range range2;
	public static final double TOLERANCE = 0.000000001d;
	
	@Before
    public void setUp() { 
    	range1 = new Range(-100.0, 100.0); 
    	range2 = new Range(20.0,20.0);
    }

	@Test
	public void testGetLengthPositive() {
		double result = range1.getLength();
		assertEquals("Calculating length for range, where upper > lower", 200.0, result, TOLERANCE);
	}
	
	@Test
	public void testGetLengthZero() {
		double result = range2.getLength();
		assertEquals("Calculating length for range, where upper == lower", 0.0, result, TOLERANCE);
	}
}
