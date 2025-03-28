package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.jfree.data.*;

import org.junit.Test;

// Caleb Bourbonnais

public class RangeGetUpperBoundTest {

	public static final double TOLERANCE = 0.000000001d;

	@Test
	public void UpperBoundNegative()
	{
		Range range = new Range(-100.0, -20.0);
		assertEquals("Lower bound must be -20.0", -20.0, range.getUpperBound(), TOLERANCE);
	}
	
	@Test
	public void UpperBoundPositive()
	{
		Range range = new Range(0.0, 9.0);
		assertEquals("Upper bound must be 9", 9, range.getUpperBound(), TOLERANCE);
	}
}
