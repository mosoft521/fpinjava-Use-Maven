package com.fpinjava.functions;

import org.junit.Test;

import static com.fpinjava.functions.ComposingFunctionsStackOverflow.g;

public class ComposingFunctionsStackOverflowTest {

    @Test(expected = StackOverflowError.class)
    public void test() {
        g.apply(0);
    }
}
