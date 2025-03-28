package org.jfree.data.test.DataUtilities;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class GetCumulativePercentagesTest {
    
    private Mockery mockery;
    private KeyedValues keyedValues;
    private static final double TOLERANCE = 0.000000001d;
    
    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        // Default three-item dataset taken from the docs 
        keyedValues = mockery.mock(KeyedValues.class, "defaultValues");
        mockery.checking(new Expectations() {{
            allowing(keyedValues).getItemCount(); will(returnValue(3));
            allowing(keyedValues).getValue(0); will(returnValue(5.0));
            allowing(keyedValues).getValue(1); will(returnValue(9.0));
            allowing(keyedValues).getValue(2); will(returnValue(2.0));
            allowing(keyedValues).getKey(0); will(returnValue(0));
            allowing(keyedValues).getKey(1); will(returnValue(1));
            allowing(keyedValues).getKey(2); will(returnValue(2));
        }});
    }
    
    @Test(timeout = 100)
    public void testGetCumulativePercentages() {
        // Expected cumulative percentages:
        // key 0: 5/16 = 0.3125
        // key 1: (5+9)/16 = 14/16 = 0.875
        // key 2: (5+9+2)/16 = 1.0
    	// This used the setup mock from above 
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
        assertEquals("Item count should be 3", 3, result.getItemCount());
        assertEquals("Cumulative percentage for key 0", 5.0 / 16.0,
                result.getValue(0).doubleValue(), TOLERANCE);
        assertEquals("Cumulative percentage for key 1", 14.0 / 16.0,
                result.getValue(1).doubleValue(), TOLERANCE);
        assertEquals("Cumulative percentage for key 2", 1.0,
                result.getValue(2).doubleValue(), TOLERANCE);
    }
    
    @Test(timeout = 100, expected = IllegalArgumentException.class)
    public void testGetCumulativePercentages_NullData() {
        // Verify that passing null throws an Illegal Argument Exception.
        DataUtilities.getCumulativePercentages(null);
    }
    
    @Test(timeout = 100)
    public void testGetCumulativePercentages_OneElement() {
        // A single element should always yield 1.0 (100%).
        final KeyedValues oneValue = mockery.mock(KeyedValues.class, "oneElement");
        mockery.checking(new Expectations() {{
            allowing(oneValue).getItemCount(); will(returnValue(1));
            allowing(oneValue).getValue(0); will(returnValue(10.0));
            allowing(oneValue).getKey(0); will(returnValue(1));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(oneValue);
        assertEquals("Item count should be 1", 1, result.getItemCount());
        assertEquals("Cumulative percentage for the single element", 1.0,
                result.getValue(0).doubleValue(), TOLERANCE); // had to add doubleValue() to make sure the return is a double 
    }
    
    @Test(timeout = 100)
    public void testGetCumulativePercentages_Empty() {
        // An empty dataset should produce an empty result.
        final KeyedValues empty = mockery.mock(KeyedValues.class, "emptyValues");
        mockery.checking(new Expectations() {{
            allowing(empty).getItemCount(); will(returnValue(0));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(empty);
        assertEquals("Item count should be 0 for empty input", 0, result.getItemCount());
    }
    
    @Test(timeout = 100)
    public void testGetCumulativePercentages_NegativeValues() {
        // Test with negative values:
        // For example: key 0: -3.0, key 1: 7.0, key 2: -2.0, key 3: 5.0.
        final KeyedValues negativeValues = mockery.mock(KeyedValues.class, "negativeValues");
        mockery.checking(new Expectations() {{
            allowing(negativeValues).getItemCount(); will(returnValue(4));
            allowing(negativeValues).getValue(0); will(returnValue(-3.0));
            allowing(negativeValues).getValue(1); will(returnValue(7.0));
            allowing(negativeValues).getValue(2); will(returnValue(-2.0));
            allowing(negativeValues).getValue(3); will(returnValue(5.0));
            allowing(negativeValues).getKey(0); will(returnValue(0));
            allowing(negativeValues).getKey(1); will(returnValue(1));
            allowing(negativeValues).getKey(2); will(returnValue(2));
            allowing(negativeValues).getKey(3); will(returnValue(3));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(negativeValues);
        assertEquals("Cumulative percentage for key 0", -3.0 / 7.0,
                result.getValue(0).doubleValue(), TOLERANCE);
        assertEquals("Cumulative percentage for key 1", 4.0 / 7.0,
                result.getValue(1).doubleValue(), TOLERANCE);
        assertEquals("Cumulative percentage for key 2", 2.0 / 7.0,
                result.getValue(2).doubleValue(), TOLERANCE);
        assertEquals("Cumulative percentage for key 3", 1.0,
                result.getValue(3).doubleValue(), TOLERANCE);
    }
    
    @Test(timeout = 100)
    public void testGetCumulativePercentages_AllZeros() {
        // When all values are 0, the total is 0.
        // Division by zero in Java results in NaN, so we expect NaN for each percentage.
        final KeyedValues zeros = mockery.mock(KeyedValues.class, "allZeros");
        mockery.checking(new Expectations() {{
            allowing(zeros).getItemCount(); will(returnValue(3));
            allowing(zeros).getValue(0); will(returnValue(0.0));
            allowing(zeros).getValue(1); will(returnValue(0.0));
            allowing(zeros).getValue(2); will(returnValue(0.0));
            allowing(zeros).getKey(0); will(returnValue(0));
            allowing(zeros).getKey(1); will(returnValue(1));
            allowing(zeros).getKey(2); will(returnValue(2));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(zeros);
        assertTrue("Expected NaN for key 0", Double.isNaN(result.getValue(0).doubleValue()));
        assertTrue("Expected NaN for key 1", Double.isNaN(result.getValue(1).doubleValue()));
        assertTrue("Expected NaN for key 2", Double.isNaN(result.getValue(2).doubleValue()));
    }
    
    // new test for mutation coverage
    
    @Test(timeout = 100)
    public void testGetCumulativePercentages_nullPresent() {
        final KeyedValues containsNulls = mockery.mock(KeyedValues.class, "containsNulls");
        mockery.checking(new Expectations() {{
            allowing(containsNulls).getItemCount(); will(returnValue(3));
            allowing(containsNulls).getValue(0); will(returnValue(1.0));
            allowing(containsNulls).getValue(1); will(returnValue(3.0));
            allowing(containsNulls).getValue(2); will(returnValue(null));
            allowing(containsNulls).getKey(0); will(returnValue(0));
            allowing(containsNulls).getKey(1); will(returnValue(1));
            allowing(containsNulls).getKey(2); will(returnValue(2));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(containsNulls);
        assertEquals("Cumulative percentage for key 0", 1.0 / 4.0,
                result.getValue(0).doubleValue(), TOLERANCE);
        assertEquals("Cumulative percentage for key 1", 4.0 / 4.0,
                result.getValue(1).doubleValue(), TOLERANCE);
        assertEquals("Cumulative percentage for key 2", 4.0 / 4.0,
                result.getValue(1).doubleValue(), TOLERANCE);
    }
    
}

	
