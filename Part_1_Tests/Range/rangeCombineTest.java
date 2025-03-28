package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class rangeCombineTest {

    private Range positiveRange1;
    private Range positiveRange2;
    private Range negativeRange1;
    private Range negativeRange2;
    private Range nullRange;
    private Range nanRange;
    private Range mixedNaNRangeLower;
    private Range mixedNaNRangeUpper;


    @Before
    public void setUp() throws Exception {
        positiveRange1 = new Range(1.0, 10.0);
        positiveRange2 = new Range(10.0, 30.0);
        negativeRange1 = new Range(-10.0, 0);
        negativeRange2 = new Range(-20.0, -1.0);
        nullRange = null;
        nanRange = new Range(Double.NaN, Double.NaN);
        mixedNaNRangeLower = new Range(Double.NaN, 5.0);
        mixedNaNRangeUpper = new Range(0, Double.NaN);
    }

    @Test
    public void testRange1NullRange2Positive() {
        assertEquals("When range1 is null, should return range2.", positiveRange1, Range.combine(nullRange, positiveRange1));
    }

    @Test
    public void testRange1PositiveRange2Null() {
        assertEquals("When range2 is null, should return range1.", positiveRange1, Range.combine(positiveRange1, nullRange));
    }

    @Test
    public void testCombine2PositiveRanges() {
        Range expectedCombinedRange = new Range(1.0, 30.0);
        assertEquals("2 Positive ranges should be properly combined.", expectedCombinedRange, Range.combine(positiveRange1, positiveRange2));
    }

    @Test
    public void testCombine2NegativeRanges() {
        Range expectedCombinedRange = new Range(-20.0, 0);
        assertEquals("2 Negative ranges should be properly combined.", expectedCombinedRange, Range.combine(negativeRange1, negativeRange2));
    }

    @Test
    public void testCombine1Positive1NegativeRange() {
        Range expectedCombinedRange = new Range(-20.0, 10.0);
        assertEquals("1 Negative and 1 Positive range should be properly combined.", expectedCombinedRange, Range.combine(positiveRange1, negativeRange2));
    }

    @Test
    public void testCombineIgnoringNaN_NullRange1PositiveRange2() {
        assertEquals("When range1 is null, should return range2.", positiveRange1, Range.combineIgnoringNaN(nullRange, positiveRange1));
    }

    @Test
    public void testCombineIgnoringNaN_PositiveRange1NullRange2() {
        assertEquals("When range2 is null, should return range1.", positiveRange1, Range.combineIgnoringNaN(positiveRange1, nullRange));
    }

    @Test
    public void testCombineIgnoringNaN_BothNaNRanges() {
        assertNull("When both ranges are NaN, should return null.", Range.combineIgnoringNaN(nanRange, nanRange));
    }

    @Test
    public void testCombineIgnoringNaN_ValidAndNaNRange() {
        assertEquals("When range1 is NaN, should return range2.", positiveRange1, Range.combineIgnoringNaN(nanRange, positiveRange1));
        assertEquals("When range2 is NaN, should return range1.", positiveRange1, Range.combineIgnoringNaN(positiveRange1, nanRange));
    }

    @Test
    public void testCombineIgnoringNaN_ValidRanges() {
        Range expectedCombinedRange = new Range(1.0, 30.0);
        assertEquals("2 Positive ranges should be properly combined ignoring NaN.", expectedCombinedRange, Range.combineIgnoringNaN(positiveRange1, positiveRange2));
    }

    @Test
    public void testCombineIgnoringNaN_OneRangeWithNaNLowerBound() {
        Range expectedCombinedRange = new Range(1.0, 10.0);
        assertEquals("Should ignore NaN lower bound and return the valid range.", expectedCombinedRange, Range.combineIgnoringNaN(mixedNaNRangeLower, positiveRange1));
    }

    @Test
    public void testCombineIgnoringNaN_OneRangeWithNaNUpperBound() {
        Range expectedCombinedRange = new Range(-10.0, 0);
        assertEquals("Should ignore NaN upper bound and return the valid range.", expectedCombinedRange, Range.combineIgnoringNaN(mixedNaNRangeUpper, negativeRange1));
    }
    
    @Test
    public void testCombineIgnoringNaN_NullRange1AndNaNRange2() {
        assertNull("When range1 is null and range2 is NaN, should return null.", Range.combineIgnoringNaN(nullRange, nanRange));
    }

    @Test
    public void testCombineIgnoringNaN_NaNRange1AndNullRange2() {
        assertNull("When range1 is NaN and range2 is null, should return null.", Range.combineIgnoringNaN(nanRange, nullRange));
    }
    
    // New Test 
    @Test
    public void testCombineIgnoringNaN_BothRangesNullOrNaN() {
        assertNull("When both ranges are null or NaN, should return null.", 
            Range.combineIgnoringNaN(nullRange, nanRange));
    }

    @Test
    public void testCombineIgnoringNaN_OverlappingRanges() {
        // Test ranges that partially overlap
        Range overlapRange1 = new Range(5.0, 15.0);
        Range overlapRange2 = new Range(10.0, 20.0);
        Range expectedCombinedRange = new Range(5.0, 20.0);
        
        assertEquals("Should correctly combine partially overlapping ranges.", 
            expectedCombinedRange, Range.combineIgnoringNaN(overlapRange1, overlapRange2));
    }

    @Test
    public void testCombineIgnoringNaN_ContainedRanges() {
        // Test when one range is completely contained within another
        Range containedRange1 = new Range(1.0, 10.0);
        Range containedRange2 = new Range(3.0, 7.0);
        Range expectedCombinedRange = new Range(1.0, 10.0);
        
        assertEquals("Should return the larger range when one range is contained within another.", 
            expectedCombinedRange, Range.combineIgnoringNaN(containedRange1, containedRange2));
    }

    @Test
    public void testCombineIgnoringNaN_AdjacentRanges() {
        // Test ranges that are exactly adjacent
        Range adjacentRange1 = new Range(1.0, 10.0);
        Range adjacentRange2 = new Range(10.0, 20.0);
        Range expectedCombinedRange = new Range(1.0, 20.0);
        
        assertEquals("Should correctly combine exactly adjacent ranges.", 
            expectedCombinedRange, Range.combineIgnoringNaN(adjacentRange1, adjacentRange2));
    }
    
    

    @Test
    public void testCombineIgnoringNaN_ExtremeValueCombinations() {
        // Test combinations with extreme values
        Range extremeRange1 = new Range(Double.MAX_VALUE, Double.MAX_VALUE);
        Range extremeRange2 = new Range(-Double.MAX_VALUE, -Double.MAX_VALUE);
        Range normalRange = new Range(-100.0, 100.0);

        // Combine extreme ranges with normal range
        Range resultExtreme1 = Range.combineIgnoringNaN(extremeRange1, normalRange);
        assertEquals("Should combine maximum value range with normal range", 
            new Range(-100.0, Double.MAX_VALUE), resultExtreme1);

        Range resultExtreme2 = Range.combineIgnoringNaN(extremeRange2, normalRange);
        assertEquals("Should combine minimum value range with normal range", 
            new Range(-Double.MAX_VALUE, 100.0), resultExtreme2);
    }


    @Test
    public void testCombineIgnoringNaN_BoundaryPrecisionEdgeCases() {
        // Test ranges with values very close to each other
        Range closeRange1 = new Range(1.0, 1.0 + Double.MIN_VALUE);
        Range closeRange2 = new Range(1.0 - Double.MIN_VALUE, 2.0);

        Range expectedCombinedRange = new Range(1.0 - Double.MIN_VALUE, 2.0);
        assertEquals("Should handle ranges with minimal boundary differences", 
            expectedCombinedRange, Range.combineIgnoringNaN(closeRange1, closeRange2));
    }
}
