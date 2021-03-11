package com.fpinjava.handlingerrors.exercise07_14;

import com.fpinjava.common.Function;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ResultTest {

    private static Function<Integer, Function<String, Integer>> parseWithRadix = radix -> string -> Integer.parseInt(string, radix);

    private static void failTest(Object o) {
        fail();
    }

    @Test
    public void testMap2SuccessSuccess() {
        int radix = 16;
        String string = "AEF15DB";
        assertEquals(Result.success(Integer.parseInt(string, radix)), Result.map2(Result.success(radix), Result.success(string), parseWithRadix));
    }

    @Test(expected = IllegalStateException.class)
    public void testMap2SuccessFailure() {
        int radix = 16;
        Result.map2(Result.success(radix), Result.failure("error"), parseWithRadix).forEachOrThrow(ResultTest::failTest);
    }

    @Test(expected = IllegalStateException.class)
    public void testMap2FailureSuccess() {
        String string = "AEF15DB";
        Result.map2(Result.failure("error"), Result.success(string), parseWithRadix).forEachOrThrow(ResultTest::failTest);
    }

    @Test
    public void testMap2EmptySuccess() {
        String string = "AEF15DB";
        Result.map2(Result.empty(), Result.success(string), parseWithRadix).forEachOrThrow(ResultTest::failTest);
    }

    @Test
    public void testMap2SuccessEmpty() {
        int radix = 16;
        Result.map2(Result.success(radix), Result.empty(), parseWithRadix).forEachOrThrow(ResultTest::failTest);
    }
}
