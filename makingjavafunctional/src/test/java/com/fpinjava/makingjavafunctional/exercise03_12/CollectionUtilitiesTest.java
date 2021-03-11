package com.fpinjava.makingjavafunctional.exercise03_12;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionUtilitiesTest {

    @Test
    public void testUnfold() {
        assertEquals("[1, 2, 4, 8]", CollectionUtilities.unfold(1, x -> x * 2, x -> x < 10).toString());
        assertEquals("[x, xx, xxx, xxxx]", CollectionUtilities.unfold("x", x -> x + "x", x -> x.length() < 5).toString());
    }

}
