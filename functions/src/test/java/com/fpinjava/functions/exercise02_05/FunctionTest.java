package com.fpinjava.functions.exercise02_05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTest {

    public static final Function<Integer, Integer> triple = x -> x * 3;

    public static final Function<Integer, Integer> square = x -> x * x;

    @Test
    public void test() {
        assertEquals(Integer.valueOf(36), Function.<Integer, Integer, Integer>higherCompose().apply(square).apply(triple).apply(2));
    }

    @Test
    public void test1() {
        Double cos = Function.compose(z -> Math.PI / 2 - z, Math::sin).apply(2.0);
        Double cos2 = Function.compose(z -> Math.PI / 2 - z, (Function<Double, Double>) a -> Math.sin(a)).apply(2.0);
        System.out.println(cos);//0.6614988999692148
        System.out.println(Math.sin(2.0));//0.9092974268256817
        System.out.println(Math.cos(2.0));//-0.4161468365471424
    }

    @Test
    public void test2() {
        Double cos = Function.<Double, Double, Double>higherCompose().apply(z -> Math.PI / 2 - z).apply(Math::sin).apply(2.0);
        System.out.println(cos);//0.6614988999692148
        System.out.println(Math.sin(2.0));//0.9092974268256817
        System.out.println(Math.cos(2.0));//-0.4161468365471424
    }
}
