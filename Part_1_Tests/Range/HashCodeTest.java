package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.jfree.data.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the hashCode() method in the Range class.
 */
public class HashCodeTest {

    // Test objects representing different ranges
    private Range range1;
    private Range range2;
    private Range range3;
    private Range rangeWithNegativeValues;

    // Set up the test cases before each test
    @Before
    public void setUp() {
        range1 = new Range(1.0, 10.0); // First test range
        range2 = new Range(1.0, 10.0); // Identical to range1
        range3 = new Range(5.0, 15.0); // Different from range1 and range2
        rangeWithNegativeValues = new Range(-10.0, -1.0); // Range with negative values
    }

    // Test that two identical ranges produce the same hash code
    @Test
    public void testHashCodeForIdenticalRanges() {
        assertEquals("Expected identical hash codes for identical ranges", 
            range1.hashCode(), range2.hashCode());
    }

    // Test that different ranges produce different hash codes
    @Test
    public void testHashCodeForDifferentRanges() {
        assertFalse("Expected different hash codes for different ranges", 
            range1.hashCode() == range3.hashCode());
    }

    // Test that two identical negative ranges produce the same hash code
    @Test
    public void testHashCodeWithNegativeValues() {
        Range anotherNegativeRange = new Range(-10.0, -1.0);
        assertEquals("Expected identical hash codes for identical negative ranges", 
            rangeWithNegativeValues.hashCode(), anotherNegativeRange.hashCode());
    }

    // Test that two zero-length ranges produce the same hash code
    @Test
    public void testHashCodeWithZeroLengthRange() {
        Range zeroLengthRange = new Range(5.0, 5.0);
        Range anotherZeroLengthRange = new Range(5.0, 5.0);
        assertEquals("Expected identical hash codes for identical zero-length ranges", 
            zeroLengthRange.hashCode(), anotherZeroLengthRange.hashCode());
    }

    // Test that the hash code is consistent across multiple calls
    @Test
    public void testHashCodeConsistency() {
        int hashCode1 = range1.hashCode();
        int hashCode2 = range1.hashCode();
        assertEquals("Expected consistent hash codes on repeated calls", 
            hashCode1, hashCode2);
    }
    
    // Test for floating-point precision differences in hash code generation
    @Test
    public void testHashCodeForFloatingPointPrecision() {
        Range rangeWithPrecisionIssue = new Range(1.0000000001, 10.0); // A tiny difference from range1
        assertFalse("Expected different hash codes for ranges with precision issues", 
            range1.hashCode() == rangeWithPrecisionIssue.hashCode());
    }

    // Test for zero-length range with negative bounds
    @Test
    public void testHashCodeForNegativeZeroLengthRange() {
        Range negativeZeroLengthRange = new Range(-5.0, -5.0); // Zero-length range with negative values
        assertEquals("Expected identical hash codes for identical negative zero-length ranges", 
            new Range(-5.0, -5.0).hashCode(), negativeZeroLengthRange.hashCode());
    }

    // Test for different bounds but same length ranges
    @Test
    public void testHashCodeForDifferentBoundsSameLength() {
        Range rangeWithSameLength = new Range(2.0, 9.0); // Same length as range1
        assertFalse("Expected different hash codes for ranges with the same length but different bounds", 
            range1.hashCode() == rangeWithSameLength.hashCode());
    }

    // Test for large values in the range
    @Test
    public void testHashCodeForLargeValues() {
        Range largeRange = new Range(1e10, 1e10 + 1000); // Very large numbers
        assertEquals("Expected identical hash codes for identical large ranges", 
            new Range(1e10, 1e10 + 1000).hashCode(), largeRange.hashCode());
    }

    // Test for very small range values
    @Test
    public void testHashCodeForTinyRange() {
        Range tinyRange = new Range(0.000001, 0.000002); // Extremely small range
        assertFalse("Expected different hash codes for ranges with extremely small values", 
            range1.hashCode() == tinyRange.hashCode());
    }

    // Test for consistent hash codes on repeated calls with negative values
    @Test
    public void testHashCodeConsistencyWithNegativeValues() {
        int hashCode1 = rangeWithNegativeValues.hashCode();
        int hashCode2 = rangeWithNegativeValues.hashCode();
        assertEquals("Expected consistent hash codes on repeated calls with negative values", 
            hashCode1, hashCode2);
    }

    // Test for empty range with identical bounds
    @Test
    public void testHashCodeForEmptyRangeWithBoundsSame() {
        Range emptyRange = new Range(0.0, 0.0); // Range where lower == upper
        assertEquals("Expected identical hash codes for identical zero-length empty ranges", 
            emptyRange.hashCode(), new Range(0.0, 0.0).hashCode());
    }

    // Test for very large negative and positive ranges producing different hash codes
    @Test
    public void testHashCodeForLargeNegativeAndPositiveRange() {
        Range largeNegativeRange = new Range(-1e10, -1e9);
        Range largePositiveRange = new Range(1e10, 1e11);
        assertFalse("Expected different hash codes for very large negative and positive ranges", 
            largeNegativeRange.hashCode() == largePositiveRange.hashCode());
    }
// new tests
 // Test for bit manipulation scenarios with Double.doubleToLongBits()
    @Test
    public void testHashCodeWithSpecialDoubleValues() {
        // Test with positive and negative zero
        Range positiveZero = new Range(0.0, 0.0);
        Range negativeZero = new Range(-0.0, -0.0);
        assertEquals("Hash codes should be consistent for positive and negative zero", 
            positiveZero.hashCode(), negativeZero.hashCode());

        // Test with NaN values
        Range nanRange1 = new Range(Double.NaN, 10.0);
        Range nanRange2 = new Range(Double.NaN, 10.0);
        assertEquals("Hash codes should be consistent for NaN ranges", 
            nanRange1.hashCode(), nanRange2.hashCode());
    }

    // Test for different bit manipulation scenarios
    @Test
    public void testHashCodeBitManipulationEdgeCases() {
        // Test ranges with values close to integer/long boundaries
        Range largeRange = new Range(Integer.MAX_VALUE, (long)Integer.MAX_VALUE + 1000);
        Range smallRange = new Range(Integer.MIN_VALUE, (long)Integer.MIN_VALUE + 1000);
        
        assertFalse("Hash codes should differ for large positive and negative ranges", 
            largeRange.hashCode() == smallRange.hashCode());
    }

    // Test for signed bit manipulation scenarios
    @Test
    public void testHashCodeWithSignedValues() {
        // Test ranges with signed values that might affect bit manipulation
        Range positiveRange = new Range(1.0, 10.0);
        Range equivalentNegativeRange = new Range(-10.0, -1.0);
        
        assertFalse("Hash codes should differ for positively and negatively scaled ranges", 
            positiveRange.hashCode() == equivalentNegativeRange.hashCode());
    }

    // Additional test for bit shift and XOR operations
    @Test
    public void testHashCodeBitOperations() {
        // Create ranges with slight variations to test bit-level differences
        Range slightlyDifferentRange1 = new Range(1.0000001, 10.0000001);
        Range slightlyDifferentRange2 = new Range(1.0000002, 10.0000002);
        
        assertFalse("Hash codes should differ for ranges with minute differences", 
            slightlyDifferentRange1.hashCode() == slightlyDifferentRange2.hashCode());
    }

    // Test for extreme floating-point value scenarios
    @Test
    public void testHashCodeWithExtremeFloatingPointValues() {
        Range infinityRange = new Range(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        Range anotherInfinityRange = new Range(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        
        assertEquals("Hash codes should be consistent for infinity ranges", 
            infinityRange.hashCode(), anotherInfinityRange.hashCode());
    }		

}
