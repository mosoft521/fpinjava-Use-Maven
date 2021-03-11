package com.fpinjava.recursion.exercise04_06;

import com.fpinjava.common.Function;

import java.util.List;

import static com.fpinjava.common.Function.identity;
import static com.fpinjava.recursion.exercise04_05.FoldRight.foldRight;

public class ComposeAll {

    static <T> Function<T, T> composeAll(List<Function<T, T>> list) {
        return foldRight(list, identity(), x -> y -> x.compose(y));
    }

    /*
     * Since the functions are from T to T, composition may be implemented
     * using andThen instead of compose.
     */
    static <T> Function<T, T> composeAllViaAndThen(List<Function<T, T>> list) {
        return foldRight(list, identity(), x -> y -> y.andThen(x));
    }
}
