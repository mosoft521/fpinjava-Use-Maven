package com.fpinjava.recursion.exercise04_05;

import com.fpinjava.common.Function;
import org.junit.Test;

import java.util.List;

import static com.fpinjava.common.CollectionUtilities.list;
import static org.junit.Assert.assertEquals;

public class FoldRightTest {

    private static String addIS(Integer i, String s) {
        return "(" + i + " + " + s + ")";
    }

    @Test
    public void testFoldRight() {
        List<Integer> list = list(1, 2, 3, 4, 5);
        String identity = "0";
        Function<Integer, Function<String, String>> f = x -> y -> addIS(x, y);
        assertEquals("(1 + (2 + (3 + (4 + (5 + 0)))))", FoldRight.foldRight(list, identity, f));
    }

}
