package org.jfree.data.test.Range;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

public class getCentralValueTest {
	public static final double TOLERANCE = 0.000000001d;
	

	// Test with positive values to get the median
	@Test
	public void testGetMiddleValuePositiveRange() {
		Range positiveRange = new Range(1.0, 3.0);
		double expectedMedian = 2.0;
		assertEquals("getCentralValue should return the median value of a given positive range", expectedMedian, positiveRange.getCentralValue(), TOLERANCE);
	}
	
	// Test with negative values to get the median
	@Test
	public void testGetMiddleValueNegativeRange() {
		Range negativeRange = new Range(-3.0, -1.0);
		double expectedMedian = -2.0;
		assertEquals("getCentralValue should return the median value of a given negative range", expectedMedian, negativeRange.getCentralValue(), TOLERANCE);
	}
	
	// Test with identical values to get the median (1.0)
	@Test
	public void testGetMiddleValueNoLengthRange() {
		Range noLengthRange = new Range(1.0, 1.0);
		double expectedMedian = 1.0;
		assertEquals("getCentralValue should return the median value of a given no length range", expectedMedian, noLengthRange.getCentralValue(), TOLERANCE);
	}
	
	// Test with mixed sign of the same value to get the median
	@Test
	public void testGetMiddleValueMixedSignRange() {
		Range mixedSignRange = new Range(-10.0, 10.0);
		double expectedMedian = 0.0;
		assertEquals("getCentralValue should return the median value of a given mixed sign range", expectedMedian, mixedSignRange.getCentralValue(), TOLERANCE);
	}
	
	// Test with large range to get the median
	@Test
	public void testGetMiddleValueLargeRange() {
		Range largeRange = new Range(1000000.0, 2000000.0);
		double expectedMedian = 1500000.0;
		assertEquals("getCentralValue should return the median value of a given large range", expectedMedian, largeRange.getCentralValue(), TOLERANCE);
	}
	
	// Test with very small range to get the median
	@Test
	public void testGetMiddleValueTinyRange() {
		Range tinyRange = new Range(1.000001, 1.000002);
		double expectedMedian = 1.0000015;
		assertEquals("getCentralValue should return the median value of a given very small range", expectedMedian, tinyRange.getCentralValue(), TOLERANCE);
	}

	// Test with a higher double in the start of the range then to a lower double 
	@Test(expected = IllegalArgumentException.class)
	public void testGetMiddleValueIntinRange() {
		Range nullRange = new Range(5.0, 3.0);
		nullRange.getCentralValue();
	}
}
