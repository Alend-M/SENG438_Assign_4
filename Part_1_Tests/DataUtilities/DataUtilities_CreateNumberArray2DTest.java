package org.jfree.data.test.DataUtilities;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.junit.*;
import java.security.InvalidParameterException;
import java.util.Arrays;
import org.junit.Test;

//Author: Agustin

public class DataUtilities_CreateNumberArray2DTest{
	
	@BeforeClass
	public static void setUpClass() throws Exception {}
	
	@Before
	public void setUp() throws Exception {}
	
	Number [][] ExpectedArray2D = {{1.0,2.0,3.0},{4.0,5.0,6.0}};
	double [][] ParameterArray2D = {{1.0,2.0,3.0},{4.0,5.0,6.0}};
	double [][] emptyArray2D = {};
	double [][] nullElementsArray2D = {{1.0,Double.NaN,3.0},{Double.NaN,5.0,6.0}}; // added for mutation coverage
	

	//Tests if 2D array of doubles is coverted to a 2D array of numbers
	@Test(timeout = 100) 
	public void testCreateNumberArray2D()
	{
		assertArrayEquals("Array of Numbers",
				ExpectedArray2D, DataUtilities.createNumberArray2D(ParameterArray2D));
	}
	
	@Test(timeout = 100, expected = IllegalArgumentException.class) 
	public void testCreateNumberArray2DNull()
	{
		DataUtilities.createNumberArray2D(null);
	}

	//Tests if empty 2D array is handled correctly
	@Test(timeout = 100)
    public void testEmptyArray2DConversion() {
		Number[][] result = DataUtilities.createNumberArray2D(emptyArray2D);
        assertEquals("Empty array should have length 0", 
                    0, result.length);
    }

	
	@After
	public void tearDown() throws Exception{
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
	}
}
