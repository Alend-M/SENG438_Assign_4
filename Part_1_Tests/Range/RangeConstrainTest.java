package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.jfree.data.*;
import org.junit.Test;

public class RangeConstrainTest {
    public static final double TOLERANCE = 0.000000001d;

    // --- Original tests ---
    @Test
    public void constrainWithinRange() {
        Range range = new Range(-1, 1);
        assertEquals("Value should be 0", 0, range.constrain(0), TOLERANCE);
    }
    
    @Test
    public void constrainMaxBound() {
        Range range = new Range(-1, 1);
        assertEquals("Value should be 1", 1, range.constrain(1), TOLERANCE);
    }
    
    @Test
    public void constrainLowerBound() {
        Range range = new Range(-1, 1);
        assertEquals("Value should be -1", -1, range.constrain(-1), TOLERANCE);
    }
    
    @Test
    public void constrainedTowardLowerBound() {
        Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be -1", 
            -1, exampleRange.constrain(-12345.67), TOLERANCE);
    }

    @Test
    public void constrainedTowardUpperBound() {
        Range exampleRange = new Range(-1, 1);
        assertEquals("Constrained value should be 1", 
            1, exampleRange.constrain(12345.67), TOLERANCE);
    }

    // New tests
    @Test
    public void constrainPositiveRange() {
        Range positiveRange = new Range(5, 10);
        assertEquals("Value within positive range should remain unchanged", 
            7.5, positiveRange.constrain(7.5), TOLERANCE);
    }

    @Test
    public void constrainNegativeRange() {
        Range negativeRange = new Range(-10, -5);
        assertEquals("Value within negative range should remain unchanged", 
            -7.5, negativeRange.constrain(-7.5), TOLERANCE);
    }

    @Test
    public void constrainEdgeCases() {
        Range infiniteRange = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        assertEquals("Infinite range should return input value", 
            42.5, infiniteRange.constrain(42.5), TOLERANCE);
    }

    @Test
    public void constrainRangeWithSameUpperAndLowerBound() {
        Range singlePointRange = new Range(10, 10);
        assertEquals("Single-point range should return the bound value", 
            10, singlePointRange.constrain(42), TOLERANCE);
    }

    @Test
    public void constrainSmallValuesNearBounds() {
        Range range = new Range(-1, 1);
        assertEquals("Value just below lower bound should clamp to lower", 
            -1, range.constrain(-1.000000001), TOLERANCE);
        assertEquals("Value just above upper bound should clamp to upper", 
            1, range.constrain(1.000000001), TOLERANCE);
    }
}