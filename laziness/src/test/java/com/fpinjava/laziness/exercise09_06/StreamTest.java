package com.fpinjava.laziness.exercise09_06;

import com.fpinjava.common.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class StreamTest {

    private List<Integer> evaluated = List.list();
    ;
    private Stream<Integer> stream =
            Stream.cons(() -> evaluate(1),
                    Stream.cons(() -> evaluate(2),
                            Stream.cons(() -> evaluate(3),
                                    Stream.cons(() -> evaluate(4),
                                            Stream.cons(() -> evaluate(5), Stream.<Integer>empty())))));

    private int evaluate(int n) {
        evaluated = List.cons(n, evaluated);
        return n;
    }

    @Test
    public void testExistsTrue() {
        assertTrue(stream.exists(x -> x > 2));
        assertEquals("[3, 2, 1, NIL]", evaluated.toString());
    }

    @Test
    public void testExistsFalse() {
        assertFalse(stream.exists(x -> x < 0));
        assertEquals("[5, 4, 3, 2, 1, NIL]", evaluated.toString());
    }

    @Test
    public void testExists£Empty() {
        assertFalse(Stream.<Integer>empty().exists(x -> x < 0));
    }
}
