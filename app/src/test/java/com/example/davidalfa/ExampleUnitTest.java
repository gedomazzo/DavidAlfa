package com.example.davidalfa;

import org.junit.Test;
import com.jjoe64.graphview.series.DataPoint;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void produceChart_test() {
        DataPoint[] points = MainActivity.produceChart();
        assertNotNull(points);
        assertEquals(100, points.length);
        for (int i = 0; i < points.length; i++) {
            assertEquals((double) i, points[i].getX(), 0.0001);
        }
    }
}