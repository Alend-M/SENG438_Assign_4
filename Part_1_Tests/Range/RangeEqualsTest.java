package org.jfree.data.test.Range;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import java.util.*;

// Author: Agustin
// parameterized testing to reduce redundancy

@RunWith(Parameterized.class)
public class RangeEqualsTest{
	
	private Range range;
	public double x, y;
	public boolean isTrue;


	public RangeEqualsTest(double x, double y, boolean expected) {
	
		this.x= x;
		this.y = y;
		this.isTrue = expected;	
	}
	
	@Parameters
	public static Collection<Object[]> parameters() 
	{
        return Arrays.asList(new Object[][] {
            {-1, 1, true}, 
            {-0.5, 0.5, false},
            {0, 1, false}, 
            {-50, 1, false}, 
            {-1, 5, false}, 
            {-1, 0, false}, 
            {50, 100, false}, 
            {-50, -5, false}});
    }
	
	@Before
	public void setUp() throws Exception
	{
		range = new Range(-1, 1);
	}
	
	@Test
	public void invalidRange()
	{
		assertFalse("Ranges cannot be invalid", range.equals(new Object()));
	}
	
	@Test
	public void rangeIsEqual()
	{
		Assume.assumeTrue(isTrue); //test is skipped if isTrue is false
		assertTrue("Ranges must be equal", range.equals(new Range(x, y)));
	}
	
	@Test
	public void rangeIsNotEqual()
	{
		Assume.assumeTrue(!isTrue); //Test is skipped if isTrue is true
		assertFalse("Ranges cannot be equal", range.equals(new Range(x, y)));
	}
	
}
	
	
