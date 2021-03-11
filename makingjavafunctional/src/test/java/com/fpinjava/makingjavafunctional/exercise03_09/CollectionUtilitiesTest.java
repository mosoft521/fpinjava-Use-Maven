package com.fpinjava.makingjavafunctional.exercise03_09;

import org.junit.Test;

import static com.fpinjava.makingjavafunctional.exercise03_09.CollectionUtilities.list;
import static com.fpinjava.makingjavafunctional.exercise03_09.CollectionUtilities.prepend;
import static com.fpinjava.makingjavafunctional.exercise03_09.CollectionUtilities.reverse;
import static com.fpinjava.makingjavafunctional.exercise03_09.CollectionUtilities.reverse2;
import static org.junit.Assert.assertEquals;

public class CollectionUtilitiesTest {

    @Test
    public void testPrepend() {
        assertEquals("[0, 1, 2, 3]", prepend("0", list("1", "2", "3")).toString());
        assertEquals("[0]", prepend("0", list()).toString());
    }

    @Test
    public void testReverse() {
        assertEquals("[]", reverse(list()).toString());
        assertEquals("[1]", reverse(list(1)).toString());
        assertEquals("[3, 2, 1]", reverse(list(1, 2, 3)).toString());
    }

    @Test
    public void testReverse2() {
        assertEquals("[]", reverse2(list()).toString());
        assertEquals("[1]", reverse2(list(1)).toString());
        assertEquals("[3, 2, 1]", reverse2(list(1, 2, 3)).toString());
    }
}
