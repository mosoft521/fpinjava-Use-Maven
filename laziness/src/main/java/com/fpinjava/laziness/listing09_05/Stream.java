package com.fpinjava.laziness.listing09_05;

import com.fpinjava.common.List;
import com.fpinjava.common.Supplier;


abstract class Stream<A> {

    private static Stream EMPTY = new Empty();

    private Stream() {
    }

    static <A> Stream<A> cons(Supplier<A> hd, Supplier<Stream<A>> tl) {
        return new Cons<>(hd, tl);
    }

    static <A> Stream<A> cons(Supplier<A> hd, Stream<A> tl) {
        return new Cons<>(hd, () -> tl);
    }

    @SuppressWarnings("unchecked")
    public static <A> Stream<A> empty() {
        return EMPTY;
    }

    public static Stream<Integer> from(int i) {
        return cons(() -> i, () -> from(i + 1));
    }

    public abstract A head();

    public abstract Stream<A> tail();

    public abstract Boolean isEmpty();

    public List<A> toList() {
        java.util.List<A> result = new java.util.ArrayList<>();
        Stream<A> ws = this;
        while (!ws.isEmpty()) {
            result.add(ws.head());
            final Stream<A> ws2 = ws;
            Supplier<Stream<A>> tail = ws2::tail;
            ws = tail.get();
        }
        return List.fromCollection(result);
    }

    private static class Empty<A> extends Stream<A> {

        @Override
        public Stream<A> tail() {
            throw new IllegalStateException("tail called on empty");
        }

        @Override
        public A head() {
            throw new IllegalStateException("head called on empty");
        }

        @Override
        public Boolean isEmpty() {
            return true;
        }
    }

    private static class Cons<A> extends Stream<A> {

        private final Supplier<A> head;
        private final Supplier<Stream<A>> tail;
        private A h;
        private Stream<A> t;

        private Cons(Supplier<A> h, Supplier<Stream<A>> t) {
            head = h;
            tail = t;
        }

        @Override
        public A head() {
            if (h == null) {
                h = head.get();
            }
            return h;
        }

        @Override
        public Stream<A> tail() {
            if (t == null) {
                t = tail.get();
            }
            return t;
        }

        @Override
        public Boolean isEmpty() {
            return false;
        }
    }
}
