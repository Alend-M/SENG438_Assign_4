package org.jfree.data.test.Range;
import static org.junit.Assert.*; 
import org.junit.*;
import org.jfree.data.Range; 


public class rangeScaleTest {
private Range range;

 @Before
 public void setUp() throws Exception { 
       range = new Range(0, 10);
     }

@Test
public void factorGreaterThanZeroScaler() {
Range positiveFactorScaledRange = new Range(0, 20);
assertEquals("Should scale the range by a factor of 2.", positiveFactorScaledRange, Range.scale(range, 2));
}

@Test (expected = IllegalArgumentException.class)
public void factorLessThanZeroScaler() {
 Range.scale(range, -2);
}

@Test
public void zeroFactorScaler() {
Range zeroFactorScaledRange = new Range(0, 0);
assertEquals("Should scale the range by a factor of 0.", zeroFactorScaledRange, Range.scale(range, 0));
}

@Test (expected = IllegalArgumentException.class)
public void nullRangeScale() {
Range nullRange = null;
Range.scale(nullRange, 10);
}


// New Tests
@Test
public void factorOneScaler() {
    assertEquals("Scaling by 1 should return the same range.", range, Range.scale(range, 1));
}

@Test
public void fractionFactorScaler() {
    Range expected = new Range(0, 5);
    assertEquals("Should scale the range by a factor of 0.5.", expected, Range.scale(range, 0.5));
}

@Test
public void scaleRangeWithNegativeValues() {
    Range negativeRange = new Range(-5, 5);
    Range expected = new Range(-10, 10);
    assertEquals("Scaling a range that includes negative values should work correctly.", expected, Range.scale(negativeRange, 2));
}


@Test
public void scaleFloatingPointPrecision() {
    Range decimalRange = new Range(1.1, 2.2);
    Range expected = new Range(2.2, 4.4);
    assertEquals("Scaling a range with floating-point numbers should handle precision correctly.", expected, Range.scale(decimalRange, 2));
}
}