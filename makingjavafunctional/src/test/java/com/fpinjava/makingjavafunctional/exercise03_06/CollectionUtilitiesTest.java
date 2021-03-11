package com.fpinjava.makingjavafunctional.exercise03_06;

import com.fpinjava.common.Function;
import org.junit.Test;

import java.util.List;

import static com.fpinjava.makingjavafunctional.exercise03_06.CollectionUtilities.foldLeft;
import static com.fpinjava.makingjavafunctional.exercise03_06.CollectionUtilities.list;
import static org.junit.Assert.assertEquals;

public class CollectionUtilitiesTest {

    private static String addSI(String s, Integer i) {
        return "(" + s + " + " + i + ")";
    }

    @Test
    public void testFoldLeft() {
        List<Integer> list = list(1, 2, 3, 4, 5);
        String identity = "0";
        Function<String, Function<Integer, String>> f = x -> y -> addSI(x, y);
        assertEquals("(((((0 + 1) + 2) + 3) + 4) + 5)", foldLeft(list, identity, f));
    }
}
