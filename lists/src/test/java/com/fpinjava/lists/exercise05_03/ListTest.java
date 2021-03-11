package com.fpinjava.lists.exercise05_03;

import org.junit.Test;

import static com.fpinjava.lists.exercise05_03.List.list;
import static org.junit.Assert.assertEquals;

public class ListTest {

    @Test
    public void testToString() {
        assertEquals("[NIL]", list().toString());
        assertEquals("[1, 2, 3, NIL]", list(1, 2, 3).toString());
    }

}
