package com.fpinjava.functions.exercise02_05;


public interface Function<T, U> {

    static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
        return f -> g -> x -> f.apply(g.apply(x));
    }

    U apply(T arg);
}
