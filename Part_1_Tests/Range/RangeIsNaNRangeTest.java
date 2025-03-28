package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.jfree.data.*;

import org.junit.Test;

// Caleb Bourbonnais

public class RangeIsNaNRangeTest {
	
	// testing two double bounds
	@Test
	public void RangeIsNaNRange_noNaNs() {
		Range range = new Range(1.0,2.0);
		assertFalse("Both bounds are type Double", range.isNaNRange());
	}
	// new tests
    @Test
    public void testIsNaNRange_BothBoundsNaN() {
        Range range = new Range(Double.NaN, Double.NaN);
        assertTrue("Range with both bounds as NaN should return true", range.isNaNRange());
    }
    
    @Test
    public void testIsNaNRange_NoBoundsNaN() {
        Range range = new Range(1.0, 2.0);
        assertFalse("Range with no NaN bounds should return false", range.isNaNRange());
    }
    
    @Test
    public void testIsNaNRange_PositiveInfinity() {
        Range range = new Range(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        assertFalse("Range with positive infinity should return false", range.isNaNRange());
    }
    
    @Test
    public void testIsNaNRange_NegativeInfinity() {
        Range range = new Range(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        assertFalse("Range with negative infinity should return false", range.isNaNRange());
    }
    
    @Test
    public void testIsNaNRange_ZeroBounds() {
        Range range = new Range(0.0, 0.0);
        assertFalse("Range with zero bounds should return false", range.isNaNRange());
    }
}
