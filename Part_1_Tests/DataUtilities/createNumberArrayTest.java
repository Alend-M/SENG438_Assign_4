package org.jfree.data.test.DataUtilities;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.Test;
import java.security.InvalidParameterException;

public class createNumberArrayTest {

	@Test(timeout = 100)
	public void testCreateNumberArrayWithValidInput() {
		double[] input = {1.1, 10.1, 2.2};
		Number[] expectedOutput = {1.1, 10.1, 2.2};
		Number[] actualOutput = DataUtilities.createNumberArray(input);
		
		assertArrayEquals("Method should convert double primitive array to a Number array", expectedOutput, actualOutput);
 	}
	
	@Test(timeout = 100)
	public void testCreateArrayWithEmptyArray() {
		double[] input = {};
		Number[] expectedOutput = {};
		Number[] actualOutput = DataUtilities.createNumberArray(input);
		
		assertArrayEquals("Method should handle the conversion of an empty double array", expectedOutput, actualOutput);
 	}
	
	// new tests for mutation coverage
	
	@Test(timeout = 100)
	public void testCreateNumberArrayLargeArray() {
	    double[] input = new double[1000000];
	    for (int i = 0; i < input.length; i++) {
	        input[i] = i * 1.1;
	    }
	    Number[] result = DataUtilities.createNumberArray(input);
	    assertEquals(input.length, result.length);
	}
	
	@Test(timeout = 100)
	public void testCreateNumberArrayNegativeNumbers() {
	    double[] input = {-1.5, -2.75, -3.0};
	    Number[] expected = {-1.5, -2.75, -3.0};
	    Number[] result = DataUtilities.createNumberArray(input);
	    assertArrayEquals(expected, result);
	}
	
	@Test(timeout = 100, expected = IllegalArgumentException.class)
	public void testCreateArrayWithNull() {
		DataUtilities.createNumberArray(null);
 	}
	
	@Test(timeout = 100)
	public void testCreateNumberArrayWithNaNElementsWrongLengths() {
		double[] input = {1.1, 10.1, Double.NaN, 2.2};
		Number[] expectedOutput = {1.1, 10.1, 2.2};
		Number[] actualOutput = DataUtilities.createNumberArray(input);
		
		assertNotEquals("Method should convert double primitive array to a Number array", expectedOutput, actualOutput);
 	}
	
	@Test(timeout = 100)
	public void testCreateNumberArrayWithNaNElements() {
		double[] input = {1.1, Double.NaN, 2.2};
		Number[] expectedOutput = {1.1, Double.NaN, 2.2};
		Number[] actualOutput = DataUtilities.createNumberArray(input);
		
		assertArrayEquals("Method should convert double primitive array to a Number array", expectedOutput, actualOutput);
 	}
}
