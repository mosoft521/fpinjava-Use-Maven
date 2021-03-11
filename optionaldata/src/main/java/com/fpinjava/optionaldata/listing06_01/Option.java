package com.fpinjava.optionaldata.listing06_01;

public abstract class Option<A> {

    @SuppressWarnings("rawtypes")
    private static Option none = new None();

    private Option() {
    }

    public static <A> Option<A> some(A a) {
        return new Some<>(a);
    }

    @SuppressWarnings("unchecked")
    public static <A> Option<A> none() {
        return none;
    }

    public abstract A getOrThrow();

    private static class None<A> extends Option<A> {

        private None() {
        }

        @Override
        public A getOrThrow() {
            throw new IllegalStateException("getOrThrow called on None");
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
        public A getOrThrow() {
            return this.value;
        }

        @Override
        public String toString() {
            return String.format("Some(%s)", this.value);
        }
    }
}
