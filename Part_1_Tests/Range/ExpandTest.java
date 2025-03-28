package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.Range;

public class ExpandTest {

    private Range normalRange;
    private Range zeroLengthRange;
    private Range negativeRange;

    @Before
    public void setUp() {
        // Initialize test ranges with varied characteristics
        normalRange = new Range(2.0, 6.0);       // Regular range with length 4.0
        zeroLengthRange = new Range(5.0, 5.0);   // Point range (zero length)
        negativeRange = new Range(-10.0, -5.0);  // Range with negative bounds
    }


    @Test(expected = IllegalArgumentException.class)
    public void testExpandWithNullRange() {
        // Tests the null range validation branch
        Range.expand(null, 0.1, 0.1);
    }
    
    @Test
    public void testExpandWithPositiveMargins() {
        // Tests expanding a normal range with positive margins
        Range result = Range.expand(normalRange, 0.25, 0.5);
        assertEquals("Lower bound should expand by 25% of range length", 1.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should expand by 50% of range length", 8.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandWithNegativeMargins() {
        // Tests contracting a normal range with negative margins
        Range result = Range.expand(normalRange, -0.25, -0.5);
        assertEquals("Lower bound should contract by 25% of range length", 3.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should contract by 50% of range length", 4.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandWithMixedMargins() {
        // Tests range with positive lower margin and negative upper margin
        Range result = Range.expand(normalRange, 0.25, -0.25);
        assertEquals("Lower bound should expand by 25% of range length", 1.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should contract by 25% of range length", 5.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandNegativeValuedRange() {
        // Tests expanding a range with negative values
        Range result = Range.expand(negativeRange, 0.2, 0.4);
        assertEquals("Lower bound should expand correctly with negative values", -11.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should expand correctly with negative values", -3.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandWithZeroMargins() {
        // Tests that range is unchanged when margins are zero
        Range result = Range.expand(normalRange, 0.0, 0.0);
        assertEquals("Lower bound should remain unchanged", 2.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should remain unchanged", 6.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandZeroLengthRange() {
        // Tests expanding a point range (where length is 0)
        Range result = Range.expand(zeroLengthRange, 0.1, 0.1);
        assertEquals("Point range should remain unchanged when expanded", 5.0, result.getLowerBound(), 0.0001);
        assertEquals("Point range should remain unchanged when expanded", 5.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandWithVeryLargeMargins() {
        // Tests expanding with extremely large margins
        Range result = Range.expand(normalRange, 10.0, 10.0);
        assertEquals("Lower bound should expand by 1000% of range length", -38.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should expand by 1000% of range length", 46.0, result.getUpperBound(), 0.0001);
    }


    @Test
    public void testExpandWithExtremeNegativeLowerMargin() {
        // Tests the case where negative margin makes lower bound exceed upper bound
        Range result = Range.expand(normalRange, -1.5, 0.0);
        assertEquals("Bounds should be equal when negative margin causes lower > upper", result.getLowerBound(), result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandWithExtremeNegativeUpperMargin() {
        // Tests the case where negative upper margin makes upper bound fall below lower bound
        Range result = Range.expand(normalRange, 0.0, -2.0);
        assertEquals("Bounds should be equal when negative margin causes upper < lower", result.getLowerBound(), result.getUpperBound(), 0.0001);
    }


    @Test
    public void testExpandToIncludeWithNullRange() {
        // Tests creating a new range when the input range is null
        Range result = Range.expandToInclude(null, 5.0);
        assertEquals("Lower bound should equal the value when range is null", 5.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should equal the value when range is null", 5.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeWithValueBelowLowerBound() {
        // Tests expanding a range to include a value below the lower bound
        Range result = Range.expandToInclude(normalRange, 0.5);
        assertEquals("Lower bound should be adjusted to include the value", 0.5, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should remain unchanged", 6.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeWithValueAboveUpperBound() {
        // Tests expanding a range to include a value above the upper bound
        Range result = Range.expandToInclude(normalRange, 10.0);
        assertEquals("Lower bound should remain unchanged", 2.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be adjusted to include the value", 10.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeWithValueWithinRange() {
        // Tests that the range remains unchanged when the value is already within the range
        Range result = Range.expandToInclude(normalRange, 4.0);
        assertSame("Range should remain unchanged when value is already included", normalRange, result);
    }

    @Test
    public void testExpandToIncludeWithValueEqualToLowerBound() {
        // Tests that the range remains unchanged when the value equals the lower bound
        Range result = Range.expandToInclude(normalRange, 2.0);
        assertSame("Range should remain unchanged when value equals lower bound", normalRange, result);
    }

    @Test
    public void testExpandToIncludeWithValueEqualToUpperBound() {
        // Tests that the range remains unchanged when the value equals the upper bound
        Range result = Range.expandToInclude(normalRange, 6.0);
        assertSame("Range should remain unchanged when value equals upper bound", normalRange, result);
    }

    @Test
    public void testExpandToIncludeWithZeroLengthRange() {
        // Tests expanding a point range to include a different value
        Range result = Range.expandToInclude(zeroLengthRange, 7.0);
        assertEquals("Lower bound should remain unchanged for point range", 5.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be adjusted to include the value", 7.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeWithNegativeValue() {
        // Tests expanding a range to include a negative value
        Range result = Range.expandToInclude(new Range(0.0, 5.0), -3.0);
        assertEquals("Lower bound should be adjusted to include negative value", -3.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should remain unchanged", 5.0, result.getUpperBound(), 0.0001);
    }
// New Tests
    @Test
    public void testExpandWithNegativeLowerMarginCausingOverlap() {
        // Test negative lower margin causing lower > upper
        Range result = Range.expand(normalRange, -1.5, 0.0);
        assertEquals("Lower bound should be 7.0 when over-contracted", 
            7.0, result.getLowerBound(), 0.0001);
        assertEquals("Bounds should be equal after contraction conflict", 
            result.getLowerBound(), result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandWithNegativeUpperMarginCausingOverlap() {
        // Test negative upper margin causing upper < lower
        Range result = Range.expand(normalRange, 0.0, -2.0);
        assertEquals("Lower bound should be 0.0 when over-contracted", 
            0.0, result.getLowerBound(), 0.0001);
        assertEquals("Bounds should be equal after contraction conflict", 
            result.getLowerBound(), result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeNullRange() {
        Range result = Range.expandToInclude(null, 5.0);
        assertEquals("Null range should create single-point range", 
            new Range(5.0, 5.0), result);
    }

    @Test
    public void testExpandToIncludeValueBelowRange() {
        Range result = Range.expandToInclude(normalRange, 0.5);
        assertEquals("Lower bound should expand to include lower value", 
            0.5, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should remain unchanged", 
            6.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeValueAboveRange() {
        Range result = Range.expandToInclude(normalRange, 10.0);
        assertEquals("Lower bound should remain unchanged", 
            2.0, result.getLowerBound(), 0.0001);
        assertEquals("Upper bound should expand to include higher value", 
            10.0, result.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeValueWithinRange() {
        Range result = Range.expandToInclude(normalRange, 4.0);
        assertSame("Range should remain unchanged when including existing value", 
            normalRange, result);
    }
}