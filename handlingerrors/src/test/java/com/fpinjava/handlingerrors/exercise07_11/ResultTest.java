package com.fpinjava.handlingerrors.exercise07_11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultTest {

    Result<Integer> empty = Result.empty();
    Result<Integer> failure = Result.failure("failure message");
    Result<Integer> success = Result.success(4);

    @Test
    public void testForEachOrExceptionEmpty() {
        TestResult tr = new TestResult();
        empty.forEachOrException(x -> tr.value = x).forEach(e -> tr.value = 12);
        assertEquals(0, tr.value);
    }

    @Test
    public void testForEachOrExceptionFailure() {
        TestResult tr = new TestResult();
        failure.forEachOrException(x -> tr.value = x).forEach(e -> tr.value = 12);
        assertEquals(12, tr.value);
    }

    @Test
    public void testForEachOrExceptionSuccess() {
        TestResult tr = new TestResult();
        success.forEachOrException(x -> tr.value = x).forEach(e -> tr.value = 12);
        assertEquals(4, tr.value);
    }

    public static class TestResult {
        int value;
    }

}
