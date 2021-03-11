package com.fpinjava.makingjavafunctional.exercise03_05;

import org.junit.Test;

import java.util.List;

import static com.fpinjava.makingjavafunctional.exercise03_04.CollectionUtilities.list;
import static com.fpinjava.makingjavafunctional.exercise03_05.Fold.fold;
import static org.junit.Assert.assertEquals;

public class FoldTest {

    @Test
    public void testFold() {
        List<Integer> emptyList = list();
        List<Integer> single = list(1);
        List<Integer> listInteger = list(1, 2, 3, 4, 5, 6);
        assertEquals(Integer.valueOf(21), fold(listInteger, 0, x -> y -> x + y));
        assertEquals(Integer.valueOf(0), fold(emptyList, 0, x -> y -> x + y));
        assertEquals(Integer.valueOf(1), fold(single, 0, x -> y -> x + y));
    }

}
