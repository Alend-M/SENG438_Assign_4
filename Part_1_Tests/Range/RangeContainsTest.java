package org.jfree.data.test.Range;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class RangeContainsTest {

    private Range range; 

    @Before
    public void setUp() throws Exception { 
        range = new Range(-200, 200);
    }

    @Test
    public void contains_positive_in_range() {
        boolean result = range.contains(100);
        assertEquals("Range contains 100:",  true, result);
    }

    @Test
    public void contains_negative_in_range() {
        boolean result = range.contains(-100);
        assertEquals("Range contains -100:",  true, result);
    }

    @Test 
    public void contains_start_range() {
        boolean result = range.contains(-200);
        assertEquals("Range contains -200:",  true, result);
    }

    @Test
    public void contains_end_range() {
        boolean result = range.contains(200);
        assertEquals("Range contains 200:",  true, result);
    }

    @Test
    public void contains_positive_outside_range() {
        boolean result = range.contains(201);
        assertEquals("Range contains 201:",  false, result);
    }

    @Test
    public void contains_negative_outside_range() {
        boolean result = range.contains(-201);
        assertEquals("Range contains -201:",  false, result);
    }

    @Test
    public void contains_decimal_in_range() {
        boolean result = range.contains(10.56);
        assertEquals("Range contains 10.56:",  true, result);
    }

    @Test
    public void contains_decimal_outside_range() {
        boolean result = range.contains(200.1);
        assertEquals("Range contains 200.1:",  false, result);
    }
} 