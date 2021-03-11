package com.fpinjava.optionaldata.exercise06_03;


import com.fpinjava.common.Function;
import com.fpinjava.common.Supplier;

public abstract class Option<A> {

    @SuppressWarnings("rawtypes")
    private static Option none = new None();

    public static <A> Option<A> some(A a) {
        return new Some<>(a);
    }

    @SuppressWarnings("unchecked")
    public static <A> Option<A> none() {
        return none;
    }

    protected abstract A getOrThrow();

    public abstract A getOrElse(Supplier<A> defaultValue);

    public abstract <B> Option<B> map(Function<A, B> f);

    private static class None<A> extends Option<A> {

        private None() {
        }

        @Override
        protected A getOrThrow() {
            throw new IllegalStateException("getOrThrow called on None");
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            return none();
        }

        @Override
        public String toString() {
            return "None";
        }
    }

    private static class Some<A> extends Option<A> {

        private final A value;

        private Some(A a) {
            value = a;
        }

        @Override
        protected A getOrThrow() {
            return this.value;
        }

        public A getOrElse(Supplier<A> defaultValue) {
            return this.value;
        }

        public <B> Option<B> map(Function<A, B> f) {
            return new Some<>(f.apply(this.value));
        }

        @Override
        public String toString() {
            return String.format("Some(%s)", this.value);
        }
    }
}
