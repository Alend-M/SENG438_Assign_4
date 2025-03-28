package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.jfree.data.*;
import org.jfree.data.Range; 
import org.junit.*;

import org.junit.Test;

public class RangeGetLowerBoundTest{
	
	public static final double TOLERANCE = 0.000000001d;

	//Test to check a negative lower bound
	@Test
	public void lowerBoundNegative()
	{
		Range range = new Range(-1, 1);
		assertEquals("Lower bound must be -1", -1, range.getLowerBound(), TOLERANCE);
	}

	//Test to check a positive lower bound
	@Test
	public void lowerBoundPositive()
	{
		Range range = new Range(1, 10);	
		assertEquals("Lower bound must be 1", 1, range.getLowerBound(), TOLERANCE);
	}

	//Test to check a negative lower bound with a positive upper bound
	@Test
    public void LowerBoundMixed() {
        Range range = new Range(-10.0, 5.0);
        assertEquals("Lower bound must be -10.0", -10.0, range.getLowerBound(), TOLERANCE);
    }

	//Test to check a lower bound equal to 0
    @Test
    public void LowerBoundZero() {
        Range range = new Range(0.0, 0.0);
        assertEquals("Lower bound must be 0.0", 0.0, range.getLowerBound(), TOLERANCE);
    }
    
}
