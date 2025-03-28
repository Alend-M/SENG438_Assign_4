package org.jfree.data.test.DataUtilities;

import static org.junit.Assert.*; 

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.*;
import org.junit.*;

//Author: Agustin

public class CalculateColTotalTest {
    private Mockery mocktext;
    private Values2D values;
    public static final double TOLERANCE = 0.000000001d;
    private Values2D nullValues;
    private Values2D noRows;
    private Values2D singleCol;
    private Values2D badRowCount;

    @Before
    public void setUp() throws Exception { 
        mocktext = new Mockery();

        //updated mocking for better mutation coverage
        
        values = mocktext.mock(Values2D.class);
        
        mocktext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(4));
                one(values).getColumnCount();
                will(returnValue(4));
                
                allowing(values).getValue(0, 0); will(returnValue(5.0));
                allowing(values).getValue(0, 1); will(returnValue(-3.0));
                allowing(values).getValue(0, 2); will(returnValue(12.0));
                allowing(values).getValue(0, 3); will(returnValue(7.5));
                
                allowing(values).getValue(1, 0); will(returnValue(-7.0));
                allowing(values).getValue(1, 1); will(returnValue(8.5));
                allowing(values).getValue(1, 2); will(returnValue(0.0));
                allowing(values).getValue(1, 3); will(returnValue(3.0));
                
                allowing(values).getValue(2, 0); will(returnValue(10.0));
                allowing(values).getValue(2, 1); will(returnValue(4.0));
                allowing(values).getValue(2, 2); will(returnValue(-6.0));
                allowing(values).getValue(2, 3); will(returnValue(1.0));
                
                allowing(values).getValue(3, 0); will(returnValue(2.0));
                allowing(values).getValue(3, 1); will(returnValue(6.0));
                allowing(values).getValue(3, 2); will(returnValue(4.5));
                allowing(values).getValue(3, 3); will(returnValue(-2.0));
                
                allowing(values).getValue(0, Integer.MAX_VALUE);
        		will(returnValue(1.0));
        		allowing(values).getValue(1, Integer.MAX_VALUE);
        		will(returnValue(2.0));
        		allowing(values).getValue(2, Integer.MAX_VALUE);
        		will(returnValue(3.0));
        		allowing(values).getValue(3, Integer.MAX_VALUE);
        		will(returnValue(4.0));
                
                allowing(values).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        });
        
        nullValues = mocktext.mock(Values2D.class, "nullValues");
        mocktext.checking(new Expectations() {
            {
                allowing(nullValues).getRowCount();
                will(returnValue(3));
                
                allowing(nullValues).getValue(0, 1);
                will(returnValue(null));
                allowing(nullValues).getValue(1, 1);
                will(returnValue(3));
                allowing(nullValues).getValue(2, 1);
                will(returnValue(7));
                
                allowing(nullValues).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        });    
        
        noRows = mocktext.mock(Values2D.class, "noRows");
        mocktext.checking(new Expectations() {
            {
                allowing(noRows).getRowCount();
                will(returnValue(0));
                
                allowing(noRows).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        }); 
        
        singleCol = mocktext.mock(Values2D.class, "singleCol");
        mocktext.checking(new Expectations() {
            {
                allowing(singleCol).getColumnCount();
                will(returnValue(1));
                allowing(singleCol).getRowCount();
                will(returnValue(3));
                
                allowing(singleCol).getValue(0, 1);
                will(returnValue(null));
                allowing(singleCol).getValue(1, 1);
                will(returnValue(3.0));
                allowing(singleCol).getValue(2, 1);
                will(returnValue(7.0));
                
                allowing(singleCol).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        }); 
        
        badRowCount = mocktext.mock(Values2D.class, "badRowCount");
        mocktext.checking(new Expectations() {
            {
                allowing(badRowCount).getColumnCount();
                will(returnValue(1));
                allowing(badRowCount).getRowCount();
                will(returnValue(-1));
                
                allowing(badRowCount).getValue(0, 1);
                will(returnValue(1.0));
                allowing(badRowCount).getValue(1, 1);
                will(returnValue(3.0));
                allowing(badRowCount).getValue(2, 1);
                will(returnValue(7.0));
                
                allowing(badRowCount).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        });
        
        
    }

    //Tests if the sum of values in the first column is correctly calculated
    @Test(timeout = 100)
    public void calculateColumnTotal_firstColumnIndex() {
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("Sum of the first column", 10.0, result, TOLERANCE);
    }

    //Tests if the sum of values in the second column is correctly calculated
    @Test(timeout = 100)
    public void calculateColumnTotal_middleColumnIndex() {
        double result = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals("Sum of the middle column", 15.5, result, TOLERANCE);
    }

    //Tests if the sum of values in the third column is correctly calculated
    @Test(timeout = 100)
    public void calculateColumnTotal_thirdColumnIndex() {
        double result = DataUtilities.calculateColumnTotal(values, 2);
        assertEquals("Sum of the third column", 10.5, result, TOLERANCE);
    }

    //Tests if the sum of values in the last column is correctly calculated
    @Test(timeout = 100) 
    public void calculateColumnTotal_lastColumnIndex() {
        double result = DataUtilities.calculateColumnTotal(values, 3);
        assertEquals("Sum of the last column", 9.5, result, TOLERANCE);
    }

    @Test(timeout = 100) 
    public void calculateColumnTotal_maxIntIndex() {
        double result = DataUtilities.calculateColumnTotal(values, Integer.MAX_VALUE);
        assertEquals("Sum of the last column", 10.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateColumnTotal_maxIntIndexValidRows() {
    	int[] validRows = {0,1,2,3};
        double result = DataUtilities.calculateColumnTotal(values, Integer.MAX_VALUE, validRows);
        assertEquals("Sum of the last column", 10.0, result, TOLERANCE);
    }

    //Tests if the method handles out of bounds column index
    @Test(timeout = 100, expected = IndexOutOfBoundsException.class)
    public void calculateColumnTotal_outOfBoundsColumnIndex() {
    	DataUtilities.calculateColumnTotal(values, 4);
    }

    //Tests if method handles negative column index
    @Test(timeout = 100, expected = IndexOutOfBoundsException.class)
    public void calculateColumnTotal_negativeColumnIndex() {
        DataUtilities.calculateColumnTotal(values, -1);
    }

    //Tests if method throws IllegalArgumentException for a null argument
    @Test(timeout = 100, expected = IllegalArgumentException.class)
    public void calculateColumnTotal_nullDataObject() {
    	DataUtilities.calculateColumnTotal(null, 1);
    }
    
    @Test(timeout = 100, expected = IllegalArgumentException.class)
    public void calculateColTotal_nullDataObjectValidRows() {
    	int[] validRows = {0,1,2,3};
        DataUtilities.calculateColumnTotal(null, 1, validRows);
    }
    
    @Test(timeout = 100)
    public void calculateColumnTotal_ValidRows() {    	
    	int [] validRows = {0,2};
    	double result = DataUtilities.calculateColumnTotal(values, 1, validRows);	
    	assertEquals("Sum of column 1 for valid rows 0 and 2", 1.0, result, TOLERANCE);	
    }
    
    @Test(timeout = 100)
    public void calculateColumnTotal_NullValidRows() {
    	
    	int [] validRows = {0,1,2};
    	double result = DataUtilities.calculateColumnTotal(singleCol, 1, validRows);
    	assertEquals("Sum of column 1 with some null values", 10.0, result, TOLERANCE);
    }
    
    // new tests for mutation coverage
    @Test(timeout = 100)
    public void calculateColumnTotal_Null() {
    	
    	double result = DataUtilities.calculateColumnTotal(singleCol, 1);
    	assertEquals("Sum of column 1 with some null values", 10.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100)
    public void calculateColumnTotal_firstColumnIndexOverloaded() {
    	int [] validRows = {0, 1, 2, 3};
        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
        assertEquals("Sum of the first column", 10.0, result, TOLERANCE);
    }

    @Test(timeout = 100)
    public void calculateColumnTotal_middleColumnIndexOverloaded() {
    	int [] validRows = {0, 1, 2, 3};
        double result = DataUtilities.calculateColumnTotal(values, 1, validRows);
        assertEquals("Sum of the middle column", 15.5, result, TOLERANCE);
    }

    @Test(timeout = 100)
    public void calculateColumnTotal_thirdColumnIndexOverloaded() {
    	int [] validRows = {0, 1, 2, 3};
        double result = DataUtilities.calculateColumnTotal(values, 2, validRows);
        assertEquals("Sum of the third column", 10.5, result, TOLERANCE);
    }

    @Test(timeout = 100) 
    public void calculateColumnTotal_lastColumnIndexOverloaded() {
    	int [] validRows = {0, 1, 2, 3};
        double result = DataUtilities.calculateColumnTotal(values, 3, validRows);
        assertEquals("Sum of the column", 9.5, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateColumnTotal_noRowsValidRows() {
    	int [] validRows = {0};
        double result = DataUtilities.calculateColumnTotal(noRows, 0, validRows);
        assertEquals("Sum of the column", 0.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateColumnTotal_noRows() {
        double result = DataUtilities.calculateColumnTotal(noRows, 0);
        assertEquals("Sum of the column", 0.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateColumnTotal_wrongRowCount() {
        double result = DataUtilities.calculateColumnTotal(badRowCount, 0);
        assertEquals("Sum of the column", 0.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateColumnTotal_wrongRowCountValidRows() {
    	int [] validRows = {0, 1, 2};
        double result = DataUtilities.calculateColumnTotal(badRowCount, 0, validRows);
        assertEquals("Sum of the column", 0.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateColumnTotal_wrongValidRows() {
    	int [] validRows = {0,0,4};
        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
        assertEquals("Sum of the column", 10.0, result, TOLERANCE);
    }
    

}
