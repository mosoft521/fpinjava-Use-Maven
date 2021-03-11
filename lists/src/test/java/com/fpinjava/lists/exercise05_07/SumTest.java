package com.fpinjava.lists.exercise05_07;

import org.junit.Test;

import static com.fpinjava.lists.exercise05_06.List.list;
import static com.fpinjava.lists.exercise05_07.Sum.sum;
import static org.junit.Assert.assertEquals;

public class SumTest {

    @Test
    public void testSum() {
        assertEquals(Integer.valueOf(0), sum(list()));
        assertEquals(Integer.valueOf(6), sum(list(1, 2, 3)));
    }

}
