package com.fpinjava.optionaldata.exercise06_05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionTest {

    @Test
    public void testOrElse() {
        Option<Integer> option = Option.some(2);
        assertEquals("Some(4)", option.map(x -> x * 2).orElse(() -> {
            throw new RuntimeException();
        }).toString());
    }

    @Test(expected = RuntimeException.class)
    public void testOrElseNone() {
        Option<Integer> option = Option.none();
        option.map(x -> x * 2).orElse(() -> {
            throw new RuntimeException();
        });
    }

}
