package com.fpinjava.lists.exercise05_01;

import org.junit.Test;

import static com.fpinjava.lists.exercise05_01.List.list;
import static org.junit.Assert.assertEquals;

public class ListTest {

    @Test
    public void testCons() {
        assertEquals("a", list().cons("a").head());
        assertEquals("a", list("b", "c", "d").cons("a").head());
        assertEquals("b", list("b", "c", "d").cons("a").tail().head());
    }

}
