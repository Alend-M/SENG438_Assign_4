package org.jfree.data.test.DataUtilities;

import static org.junit.Assert.*; 
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.*;
import org.junit.*;

// Caleb Bourbonnais

public class CalculateRowTotalTest {
    private Mockery mocktext;
    private Values2D values;
    private Values2D nullValues;
    private Values2D noCols;
    private Values2D singleRow;
    private Values2D badColCount;
    
    public static final double TOLERANCE = 0.000000001d;
    
    // This is the setup method using the mockery library
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
                
                allowing(values).getValue(Integer.MAX_VALUE, 0);
        		will(returnValue(1.0));
        		allowing(values).getValue(Integer.MAX_VALUE, 1);
        		will(returnValue(2.0));
        		allowing(values).getValue(Integer.MAX_VALUE, 2);
        		will(returnValue(3.0));
        		allowing(values).getValue(Integer.MAX_VALUE, 3);
        		will(returnValue(4.0));
                
                allowing(values).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        });
        
        nullValues = mocktext.mock(Values2D.class, "nullValues");
        mocktext.checking(new Expectations() {
            {
                allowing(nullValues).getColumnCount();
                will(returnValue(3));
                
                allowing(nullValues).getValue(1, 0);
                will(returnValue(null));
                allowing(nullValues).getValue(1, 1);
                will(returnValue(3));
                allowing(nullValues).getValue(1, 2);
                will(returnValue(7));
                
                allowing(nullValues).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        });    
        
        noCols = mocktext.mock(Values2D.class, "noRows");
        mocktext.checking(new Expectations() {
            {
                allowing(noCols).getColumnCount();
                will(returnValue(0));
                
                allowing(noCols).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        }); 
        
        singleRow = mocktext.mock(Values2D.class, "singleRow");
        mocktext.checking(new Expectations() {
            {
                allowing(singleRow).getColumnCount();
                will(returnValue(3));
                allowing(singleRow).getRowCount();
                will(returnValue(1));
                
                allowing(singleRow).getValue(0, 0);
                will(returnValue(null));
                allowing(singleRow).getValue(0, 1);
                will(returnValue(3.0));
                allowing(singleRow).getValue(0, 2);
                will(returnValue(7.0));
                
                allowing(singleRow).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        }); 
        
        badColCount = mocktext.mock(Values2D.class, "badRowCount");
        mocktext.checking(new Expectations() {
            {
                allowing(badColCount).getColumnCount();
                will(returnValue(0));
                allowing(badColCount).getRowCount();
                will(returnValue(-1));
                
                allowing(badColCount).getValue(1, 0);
                will(returnValue(1.0));
                allowing(badColCount).getValue(1, 1);
                will(returnValue(3.0));
                allowing(badColCount).getValue(1, 2);
                will(returnValue(7.0));
                
                allowing(badColCount).getValue(with(any(Integer.class)), with(any(Integer.class)));
				will(throwException(new IndexOutOfBoundsException()));
            }
        });
        
    }

    //Tests if the sum of values in the first column is correctly calculated
    @Test(timeout = 100)
    public void calculateRowTotal_firstRowIndex() {
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("Sum of the first Row", 21.5, result, TOLERANCE);
    }

    //Tests if the sum of values in the second column is correctly calculated
    @Test(timeout = 100)
    public void calculateRowTotal_middleRowIndex() {
        double result = DataUtilities.calculateRowTotal(values, 1);
        assertEquals("Sum of the middle Row", 4.5, result, TOLERANCE);
    }

    //Tests if the sum of values in the third column is correctly calculated
    @Test(timeout = 100)
    public void calculateRowTotal_thirdRowIndex() {
        double result = DataUtilities.calculateRowTotal(values, 2);
        assertEquals("Sum of the third Row", 9.0, result, TOLERANCE);
    }

    //Tests if the sum of values in the last column is correctly calculated
    @Test(timeout = 100) 
    public void calculateRowTotal_lastRowIndex() {
        double result = DataUtilities.calculateRowTotal(values, 3);
        assertEquals("Sum of the last Row", 10.5, result, TOLERANCE);
    }

    @Test(timeout = 100) 
    public void calculateRowTotal_maxIntIndex() {
        double result = DataUtilities.calculateRowTotal(values, Integer.MAX_VALUE);
        assertEquals("Sum of the last Row", 10.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateRowTotal_maxIntIndexValidCols() {
    	int[] validCols = {0,1,2,3};
        double result = DataUtilities.calculateRowTotal(values, Integer.MAX_VALUE, validCols);
        assertEquals("Sum of the last Row", 10.0, result, TOLERANCE);
    }

    //Tests if the method handles out of bounds row index
    @Test(timeout = 100, expected = IndexOutOfBoundsException.class)
    public void calculateRowTotal_outOfBoundsRowIndex() {
    	DataUtilities.calculateRowTotal(values, 4);
    }

    //Tests if method handles negative column index
    @Test(timeout = 100, expected = IndexOutOfBoundsException.class)
    public void calculateRowTotal_neativeRowIndex() {
        DataUtilities.calculateRowTotal(values, -1);
    }

    //Tests if method throws IllegalArgumentException for a null argument
    @Test(timeout = 100, expected = IllegalArgumentException.class)
    public void calculateRowTotal_nullDataObject() {
    	DataUtilities.calculateRowTotal(null, 1);
    }
    
    @Test(timeout = 100, expected = IllegalArgumentException.class)
    public void calculateRowTotal_nullDataObjectValidCols() {
    	int[] validCols = {0,1,2,3};
        DataUtilities.calculateRowTotal(null, 1, validCols);
    }
    
    @Test(timeout = 100)
    public void calculateRowTotal_ValidCols() {    	
    	int [] validCols = {0,2};
    	double result = DataUtilities.calculateRowTotal(values, 1, validCols);	
    	assertEquals("Sum of Row 1 for valid rows 0 and 2", -7.0, result, TOLERANCE);	
    }
    
    @Test(timeout = 100)
    public void calculateRowTotal_NullValidCols() {
    	
    	int [] validCols = {0,1,2};
    	double result = DataUtilities.calculateRowTotal(singleRow, 0, validCols);
    	assertEquals("Sum of Row 1 with some null values", 10.0, result, TOLERANCE);
    }
    
    // new tests for mutation coverage
    
    @Test(timeout = 100)
    public void calculateRowTotal_Null() {
    	
    	double result = DataUtilities.calculateRowTotal(singleRow, 0);
    	assertEquals("Sum of Row 1 with some null values", 10.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100)
    public void calculateRowTotal_firstRowIndexOverloaded() {
    	int [] validCols = {0, 1, 2, 3};
        double result = DataUtilities.calculateRowTotal(values, 0, validCols);
        assertEquals("Sum of the first Row", 21.5, result, TOLERANCE);
    }

    @Test(timeout = 100)
    public void calculateRowTotal_middleRowIndexOverloaded() {
    	int [] validCols = {0, 1, 2, 3};
        double result = DataUtilities.calculateRowTotal(values, 1, validCols);
        assertEquals("Sum of the middle Row", 4.5, result, TOLERANCE);
    }

    @Test(timeout = 100)
    public void calculateRowTotal_thirdRowIndexOverloaded() {
    	int [] validCols = {0, 1, 2, 3};
        double result = DataUtilities.calculateRowTotal(values, 2, validCols);
        assertEquals("Sum of the third Row", 9.0, result, TOLERANCE);
    }

    @Test(timeout = 100) 
    public void calculateRowTotal_lastRowIndexOverloaded() {
    	int [] validCols = {0, 1, 2, 3};
        double result = DataUtilities.calculateRowTotal(values, 3, validCols);
        assertEquals("Sum of the Row", 10.5, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateRowTotal_noColsValidCols() {
    	int [] validCols = {0};
        double result = DataUtilities.calculateRowTotal(noCols, 0, validCols);
        assertEquals("Sum of the Row", 0.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateRowTotal_noCols() {
        double result = DataUtilities.calculateRowTotal(noCols, 0);
        assertEquals("Sum of the Row", 0.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateRowTotal_wrongColCount() {
        double result = DataUtilities.calculateRowTotal(badColCount, 1);
        assertEquals("Sum of the Row", 0.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateRowTotal_wrongColCountValidRows() {
    	int [] validCols = {0, 1, 2};
        double result = DataUtilities.calculateRowTotal(badColCount, 1, validCols);
        assertEquals("Sum of the Row", 0.0, result, TOLERANCE);
    }
    
    @Test(timeout = 100) 
    public void calculateRowTotal_wrongValidCols() {
    	int [] validCols = {0,0,4};
        double result = DataUtilities.calculateRowTotal(values, 0, validCols);
        assertEquals("Sum of the Row", 10.0, result, TOLERANCE);
    }
    

    
}
