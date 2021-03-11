package com.fpinjava.functions.exercise02_09;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionExamplesTest {

    public static <A, B, C, D> String func(A a, B b, C c, D d) {
        return String.format("%s, %s, %s, %s", a, b, c, d);
    }

    @Test
    public void testF() {
        assertEquals(func("A", "B", "C", "D"), FunctionExamples.f().apply("A").apply("B").apply("C").apply("D"));
    }

}
