package com.fpinjava.io.exercise13_07;


import com.fpinjava.common.Function;
import com.fpinjava.common.Nothing;

public interface IO<A> {

    IO<Nothing> empty = () -> Nothing.instance;

    static <A> IO<A> unit(A a) {
        return () -> a;
    }

    A run();

    default <B> IO<B> map(Function<A, B> f) {
        return () -> f.apply(this.run());
    }

    default <B> IO<B> flatMap(Function<A, IO<B>> f) {
        return () -> f.apply(this.run()).run();
    }
}
