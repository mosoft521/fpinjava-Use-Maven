package com.fpinjava.handlingerrors.exercise07_12;

import com.fpinjava.common.Function;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultTest {

    private Function<Result<String>, Result<Integer>> parseIntResult = Result.lift(Integer::parseInt);

    @Test
    public void testLift() {
        assertEquals(Result.success(345).toString(), parseIntResult.apply(Result.success("345")).toString());
    }

    @Test
    public void testLiftEmpty() {
        assertEquals(Result.empty(), parseIntResult.apply(Result.empty()));
    }
}
