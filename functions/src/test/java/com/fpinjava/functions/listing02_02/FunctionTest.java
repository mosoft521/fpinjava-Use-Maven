package com.fpinjava.functions.listing02_02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {

    @Test
    public void TestHigherCompose() {

        Function<Double, Integer> f = a -> (int) (a * 3);
        Function<Integer, Double> g = a -> a + 2.0;

        assertEquals(Integer.valueOf(9), f.compose(g).apply(1));
        assertEquals(Integer.valueOf(9), Function.<Integer, Double, Integer>higherCompose().apply(f).apply(g).apply(1));
    }

    @Test
    public void TestHigherAndThen() {

        Function<Double, Integer> f = a -> (int) (a * 3);
        Function<Integer, Double> g = a -> a + 2.0;

        assertEquals(Integer.valueOf(9), g.andThen(f).apply(1));
        assertEquals(Integer.valueOf(9), Function.<Integer, Double, Integer>higherAndThen().apply(g).apply(f).apply(1));
    }
}
