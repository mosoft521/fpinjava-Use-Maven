package com.fpinjava.lists.exercise05_12;

import org.junit.Test;

import static com.fpinjava.lists.exercise05_10.List.list;
import static com.fpinjava.lists.exercise05_12.Reverse.reverseViaFoldLeft;
import static org.junit.Assert.assertEquals;

public class ReverseTest {

    @Test
    public void testReverseViaFoldLeft() {
        assertEquals("[NIL]", reverseViaFoldLeft(list()).toString());
        assertEquals("[3, 2, 1, NIL]", reverseViaFoldLeft(list(1, 2, 3)).toString());
    }

}
