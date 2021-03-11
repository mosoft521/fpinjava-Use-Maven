package com.fpinjava.recursion.listing04_04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandaloneTest {

    @Test
    public void test() {
        assertEquals(Integer.valueOf(100000003), Standalone.add.apply(3).apply(100000000));
    }

}
