package com.fpinjava.recursion.exercise04_05;

import com.fpinjava.common.Function;
import com.fpinjava.recursion.listing04_03.TailCall;

import java.util.List;

import static com.fpinjava.common.CollectionUtilities.head;
import static com.fpinjava.common.CollectionUtilities.reverse;
import static com.fpinjava.common.CollectionUtilities.tail;
import static com.fpinjava.recursion.listing04_03.TailCall.ret;
import static com.fpinjava.recursion.listing04_03.TailCall.sus;

public class FoldRight {

    public static <T, U> U foldRight(List<T> ts, U identity, Function<T, Function<U, U>> f) {
        return foldRight_(identity, reverse(ts), f).eval();
    }

    private static <T, U> TailCall<U> foldRight_(U acc, List<T> ts, Function<T, Function<U, U>> f) {
        return ts.isEmpty()
                ? ret(acc)
                : sus(() -> foldRight_(f.apply(head(ts)).apply(acc), tail(ts), f));
    }
}
