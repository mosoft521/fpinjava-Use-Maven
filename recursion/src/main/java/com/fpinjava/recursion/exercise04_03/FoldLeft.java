package com.fpinjava.recursion.exercise04_03;

import com.fpinjava.common.Function;
import com.fpinjava.recursion.listing04_03.TailCall;

import java.util.List;

import static com.fpinjava.common.CollectionUtilities.head;
import static com.fpinjava.common.CollectionUtilities.tail;
import static com.fpinjava.recursion.listing04_03.TailCall.ret;
import static com.fpinjava.recursion.listing04_03.TailCall.sus;


public class FoldLeft {

    public static <T, U> U foldLeft(List<T> ts, U identity, Function<U, Function<T, U>> f) {
        return foldLeft_(ts, identity, f).eval();
    }

    private static <T, U> TailCall<U> foldLeft_(List<T> ts, U identity, Function<U, Function<T, U>> f) {
        return ts.isEmpty()
                ? ret(identity)
                : sus(() -> foldLeft_(tail(ts), f.apply(identity).apply(head(ts)), f));
    }
}
