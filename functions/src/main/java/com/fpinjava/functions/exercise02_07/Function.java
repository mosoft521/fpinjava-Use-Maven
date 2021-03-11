package com.fpinjava.functions.exercise02_07;


public interface Function<T, U> {

    static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
        return f -> g -> x -> f.apply(g.apply(x));
    }

    static <T, U, V> Function<Function<T, U>, Function<Function<U, V>, Function<T, V>>> higherAndThen() {
        return f -> g -> z -> g.apply(f.apply(z));
    }

    U apply(T arg);
}
