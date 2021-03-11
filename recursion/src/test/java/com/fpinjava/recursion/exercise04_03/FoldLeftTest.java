package com.fpinjava.recursion.exercise04_03;

import com.fpinjava.common.Function;
import org.junit.Test;

import java.util.List;

import static com.fpinjava.common.CollectionUtilities.list;
import static org.junit.Assert.assertEquals;

public class FoldLeftTest {

    private static String addSI(String s, Integer i) {
        return "(" + s + " + " + i + ")";
    }

    @Test
    public void testFoldLeft() {
        List<Integer> list = list(1, 2, 3, 4, 5);
        String identity = "0";
        Function<String, Function<Integer, String>> f = x -> y -> addSI(x, y);
        assertEquals("(((((0 + 1) + 2) + 3) + 4) + 5)", FoldLeft.foldLeft(list, identity, f));
    }

}
