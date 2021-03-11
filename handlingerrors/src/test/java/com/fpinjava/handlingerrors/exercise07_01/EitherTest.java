package com.fpinjava.handlingerrors.exercise07_01;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EitherTest {

    @Test
    public void testMapRight() {
        Either<String, Integer> either = Either.right(2);
        assertEquals("Right(4)", either.map(x -> x * 2).toString());
    }

    @Test
    public void testMapLeft() {
        Either<String, Integer> either = Either.left("error");
        assertEquals("Left(error)", either.map(x -> x * 2).toString());
    }

}
