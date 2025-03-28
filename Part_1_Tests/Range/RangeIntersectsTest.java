package org.jfree.data.test.Range;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

public class RangeIntersectsTest {

    private Range testRange;
    private Range negativeRange;

    @Before
    public void setUp() {
        // A typical range from 1.0 to 10.0
        testRange = new Range(1.0, 10.0);
        // A negative range from -20.0 to -10.0
        negativeRange = new Range(-20.0, -10.0);
    }
    
    //Test if the given range intersects with another
    @Test
    public void testNoIntersectionSingleArg() {
        Range tempRange = new Range(11.0,12.0);
        assertFalse("Expected no intersection between ranges",
                testRange.intersects(tempRange));
    }
    
    // Test if the range intersects with another range that is entirely to the right
    @Test
    public void testNoIntersectionToRight() {
        // Parameter range [12, 20] is entirely to the right of [1,10]
        assertFalse("Expected no intersection when parameter range is completely to the right",
                testRange.intersects(12.0, 20.0));
    }
    
    // Test if the range intersects with another range that is entirely to the left
    @Test
    public void testNoIntersectionToLeft() {
        // Parameter range [-10, 0] is entirely to the left of [1,10]
        assertFalse("Expected no intersection when parameter range is completely to the left",
                testRange.intersects(-10.0, 0.0));
    }
    
    // Test if the range intersects with another range that is entirely above
    @Test
    public void testIntersectionAtLowerBoundary() {
        // Parameter range [1, 5] touches the lower bound of [1,10] and overlaps.
        assertTrue("Expected intersection when parameter range touches the lower boundary",
                testRange.intersects(1.0, 5.0));
    }
    
    // Test if the range intersects being partially inside and to the right
    @Test
    public void testContainedWithin() {
        // Parameter range [3, 8] lies entirely within [1,10]
        assertTrue("Expected intersection when parameter range is completely inside the test range",
                testRange.intersects(3.0, 8.0));
    }
    
    // Test if the range intersects being partially inside and to the left
    @Test
    public void testRangeContainedInParameter() {
        // Parameter range [0, 15] completely covers [1,10]
        assertTrue("Expected intersection when test range is completely contained within the parameter range",
                testRange.intersects(0.0, 15.0));
    }
    
    // Test if the range interesection is an exact match
    @Test
    public void testExactMatch() {
        // Parameter range exactly equals [1,10]
        assertTrue("Expected intersection when parameter range is an exact match",
                testRange.intersects(1.0, 10.0));
    }
    
    // Test if the range intersects if the range has no length at the boundary
    @Test
    public void testZeroLengthRangeAtBoundary() {
        // Zero-length range at the lower boundary [1,1]
        assertFalse("Expected no intersection for a zero-length parameter range at the lower boundary",
                testRange.intersects(1.0, 1.0));
        // Zero-length range at the upper boundary [10,10]
        assertFalse("Expected no intersection for a zero-length parameter range at the upper boundary",
                testRange.intersects(10.0, 10.0));
    }
    
    // Test if the range intersects if partial overlap occurs in the negative range
    @Test
    public void testNegativeRangeIntersection() {
        // negativeRange is [-20,-10]. Parameter range [-15,-5] overlaps in the interval [-15,-10].
        assertTrue("Expected intersection for overlapping negative ranges",
                negativeRange.intersects(-15.0, -5.0));
    }

    // Test if the range intersects if no overlap occurs in the negative range
    @Test
    public void testNegativeRangeNoIntersection() {
        // Parameter range [-30,-25] does not intersect with negativeRange [-20,-10]
        assertFalse("Expected no intersection for non-overlapping negative ranges",
                negativeRange.intersects(-30.0, -25.0));
    }
    // new tests 
    @Test
    public void testPointRangeAtLowerBoundary() {
        Range baseRange = new Range(1.0, 10.0);
        assertFalse("Point range at lower boundary should not intersect", 
                baseRange.intersects(1.0, 1.0));
    }

    @Test
    public void testPointRangeAtUpperBoundary() {
        Range baseRange = new Range(1.0, 10.0);
        assertFalse("Point range at upper boundary should not intersect", 
                baseRange.intersects(10.0, 10.0));
    }

    @Test
    public void testPointRangeInside() {
        Range baseRange = new Range(1.0, 10.0);
        assertTrue("Point range inside should intersect", 
                baseRange.intersects(5.0, 5.0));
    }

    @Test
    public void testJustOutsideBoundaries() {
        assertFalse("Range just below lower bound should not intersect", 
                testRange.intersects(0.9, 0.9));
        assertFalse("Range just above upper bound should not intersect", 
                testRange.intersects(10.1, 10.1));
    }

    @Test
    public void testBoundaryOverlaps() {
        assertTrue("Range overlapping lower boundary should intersect", 
                testRange.intersects(0.9, 1.1));
        assertTrue("Range overlapping upper boundary should intersect", 
                testRange.intersects(9.9, 10.1));
    }

    @Test
    public void testReversedBounds() {
        assertFalse("Reversed positive bounds should not intersect", 
                testRange.intersects(5.0, 3.0));
        assertFalse("Reversed negative bounds should not intersect", 
                negativeRange.intersects(-5.0, -15.0));
    }
}
