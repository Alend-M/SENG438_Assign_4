package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class rangeShiftTest {
	public static final double TOLERANCE = 0.000000001d;
	

	@Test(expected = IllegalArgumentException.class)
    public void testShiftNullBaseRangeShouldThrowException() {
        Range.shift(null, 5.0, true);
    }

    @Test
    public void testShiftAllowZeroCrossingPositiveDelta() {
        Range base = new Range(-1.0, 5.0);
        assertEquals("Lower bound should shift by +5 allowing zero crossing.", 4.0, Range.shift(base, 5.0, true).getLowerBound(), TOLERANCE);
        assertEquals("Upper bound should shift by +5 allowing zero crossing.", 4.0, Range.shift(base, 5.0, true).getLowerBound(), TOLERANCE);
    }
    
    @Test
    public void testShiftIgnoreZeroCrossing() {
        Range base = new Range(1.0, 5.0);
        assertEquals("Upper bound should shift by +5 allowing zero crossing.", 6.0, Range.shift(base, 5.0).getLowerBound(), TOLERANCE);
    }

    @Test
    public void testShiftAllowZeroCrossingNegativeDelta() {
        Range base = new Range(-10.0, 3.0);
        assertEquals("Range should shift by -5 allowing zero crossing.", -2.0, Range.shift(base, -5.0, true).getUpperBound(), TOLERANCE);
        assertEquals("Lower bound should shift correctly to -15.0.", -15.0, Range.shift(base, -5.0, true).getLowerBound(), TOLERANCE);

    }

    @Test
    public void testShiftAllowZeroCrossingZeroDelta() {
        Range base = new Range(2.0, 8.0);
        assertEquals("Lower bound should remain the same when delta is 0.", 2.0, Range.shift(base, 0.0, true).getLowerBound(), TOLERANCE);
        assertEquals("Upper bound should remain the same when delta is 0.", 8.0, Range.shift(base, 0.0, true).getUpperBound(), TOLERANCE);
    }

    @Test
    public void testShiftDisallowZeroCrossingPositiveDelta() {
        Range base = new Range(-3.0, 2.0);
        assertEquals("Lower bound should not cross zero.", 0, Range.shift(base, 5.0, false).getLowerBound(), TOLERANCE);
        assertEquals("Upper bound should shift correctly to 7.0", 7.0, Range.shift(base, 5.0, false).getUpperBound(), TOLERANCE);
    }

    @Test
    public void testShiftDisallowZeroCrossingNegativeDelta() {
        Range base = new Range(-5.0, 2.0);
        assertEquals("Upper bound should not cross zero.", 0, Range.shift(base, -3.0, false).getUpperBound(), 0.001);
        assertEquals("Lower bound should shift correctly to -8.0.", -8.0, Range.shift(base, -3.0, false).getLowerBound(), TOLERANCE);
    }
    
    @Test
    public void testShiftDisallowZeroCrossingPositiveBound() {
        Range base = new Range(3.0, 10.0);
        assertEquals("Lower bound should not go below 0.0", 0.0, Range.shift(base, -5.0, false).getLowerBound(), TOLERANCE);
        assertEquals("Upper bound should shift to 5.0.", 5.0, Range.shift(base, -5.0, false).getUpperBound(), TOLERANCE);
    }

    @Test
    public void testShiftDisallowZeroCrossingNegativeBound() {
        Range base = new Range(-8.0, -2.0);
        assertEquals("Lower bound should shift correctly to -5.0.", -5.0, Range.shift(base, 3.0, false).getLowerBound(), TOLERANCE);
        assertEquals("Upper bound should not go above 0.", 0.0, Range.shift(base, 3.0, false).getUpperBound(), TOLERANCE);
    }

    @Test
    public void testShiftDisallowZeroCrossingZeroBound() {
        Range base = new Range(0.0, 0.0);
        assertEquals("Lower bound should shift to 5.0.", 5.0, Range.shift(base, 5.0, false).getLowerBound(), TOLERANCE);
        assertEquals("Upper bound should shift to 5.0.", 5.0, Range.shift(base, 5.0, false).getUpperBound(), TOLERANCE);
    }
    // New tests
    @Test
    public void testShiftWithExtremeDeltaValues() {
        Range baseRange = new Range(-10.0, 10.0);
        
        // Test with extreme delta values
        Range resultPositiveMax = Range.shift(baseRange, Double.MAX_VALUE, true);
	        Range resultNegativeMax = Range.shift(baseRange, -Double.MAX_VALUE, true);
	        
	        assertNotNull("Shift with positive max delta should not be null", resultPositiveMax);
        assertNotNull("Shift with negative max delta should not be null", resultNegativeMax);
    }

    @Test
    public void testShiftPreservesRangeLength() {
        Range baseRange = new Range(-5.0, 5.0);
        double originalLength = baseRange.getUpperBound() - baseRange.getLowerBound();
        
        Range resultPositive = Range.shift(baseRange, 3.0, true);
        Range resultNegative = Range.shift(baseRange, -3.0, true);
        
        assertEquals("Positive shift should preserve range length", 
            originalLength, 
            resultPositive.getUpperBound() - resultPositive.getLowerBound(), 
            TOLERANCE);
        
        assertEquals("Negative shift should preserve range length", 
            originalLength, 
            resultNegative.getUpperBound() - resultNegative.getLowerBound(), 
            TOLERANCE);
    }

    @Test
    public void testShiftZeroBoundedRange() {
        Range zeroRange = new Range(0.0, 0.0);
        
        Range resultPositive = Range.shift(zeroRange, 5.0, true);
        Range resultNegative = Range.shift(zeroRange, -5.0, true);
        
        assertEquals("Zero range should shift entirely positive", 
            5.0, resultPositive.getLowerBound(), TOLERANCE);
        assertEquals("Zero range should shift entirely negative", 
            -5.0, resultNegative.getLowerBound(), TOLERANCE);
    }

    @Test
    public void testShiftBehaviorWithZeroCrossingFlag() {
        Range baseRange = new Range(-3.0, 3.0);
        
        // With zero crossing allowed
        Range resultAllowCrossing = Range.shift(baseRange, 5.0, true);
        assertEquals("Should allow crossing zero when flag is true", 
            2.0, resultAllowCrossing.getLowerBound(), TOLERANCE);
        
        // Without zero crossing
        Range resultPreventCrossing = Range.shift(baseRange, 5.0, false);
        assertEquals("Should prevent crossing zero when flag is false", 
            0.0, resultPreventCrossing.getLowerBound(), TOLERANCE);
    }

    @Test
    public void testShiftWithMinimalDeltas() {
        Range baseRange = new Range(1.0, 5.0);
        
        Range resultMinPositive = Range.shift(baseRange, Double.MIN_VALUE, true);
        Range resultMinNegative = Range.shift(baseRange, -Double.MIN_VALUE, true);
        
        assertNotNull("Shift with minimal positive delta should work", resultMinPositive);
        assertNotNull("Shift with minimal negative delta should work", resultMinNegative);
    }
}