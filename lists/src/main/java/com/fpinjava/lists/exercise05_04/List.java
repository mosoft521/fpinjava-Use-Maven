package com.fpinjava.lists.exercise05_04;

import com.fpinjava.common.TailCall;

import static com.fpinjava.common.TailCall.ret;
import static com.fpinjava.common.TailCall.sus;

public abstract class List<A> {

    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil();

    private List() {
    }

    @SuppressWarnings("unchecked")
    public static <A> List<A> list() {
        return NIL;
    }

    @SafeVarargs
    public static <A> List<A> list(A... a) {
        List<A> n = list();
        for (int i = a.length - 1; i >= 0; i--) {
            n = new Cons<>(a[i], n);
        }
        return n;
    }

    public abstract A head();

    public abstract List<A> tail();

    public abstract boolean isEmpty();

    public abstract List<A> setHead(A h);

    public abstract List<A> drop(int n);

    public List<A> cons(A a) {
        return new Cons<>(a, this);
    }

    private static class Nil<A> extends List<A> {

        private Nil() {
        }

        public A head() {
            throw new IllegalStateException("head called en empty list");
        }

        public List<A> tail() {
            throw new IllegalStateException("tail called en empty list");
        }

        public boolean isEmpty() {
            return true;
        }

        @Override
        public List<A> setHead(A h) {
            throw new IllegalStateException("setHead called en empty list");
        }

        public String toString() {
            return "[NIL]";
        }

        /*
         * It's somewhat subjective whether to throw an exception when asked
         * to drop more elements than the list contains. The usual default for
         * `drop` is not to throw an exception, since it's typically used in cases
         * where this is not indicative of a programming error. If you pay attention
         * to how you use `drop`, it's often in cases where the length of the input
         * list is unknown, and the number of elements to be dropped is being
         * computed from something else. If `drop` threw an exception, we'd have to
         * first compute or check the length and only drop up to that many elements.
         */
        @Override
        public List<A> drop(int n) {
            return this;
        }
    }

    private static class Cons<A> extends List<A> {

        private final A head;
        private final List<A> tail;

        private Cons(A head, List<A> tail) {
            this.head = head;
            this.tail = tail;
        }

        public A head() {
            return head;
        }

        public List<A> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<A> setHead(A h) {
            return new Cons<>(h, tail());
        }

        public String toString() {
            return String.format("[%sNIL]", toString(new StringBuilder(), this)
                    .eval());
        }

        private TailCall<StringBuilder> toString(StringBuilder acc, List<A> list) {
            return list.isEmpty()
                    ? ret(acc)
                    : sus(() -> toString(acc.append(list.head()).append(", "),
                    list.tail()));
        }

        @Override
        public List<A> drop(int n) {
            return n <= 0
                    ? this
                    : drop_(this, n).eval();
        }

        private TailCall<List<A>> drop_(List<A> list, int n) {
            return n <= 0 || list.isEmpty()
                    ? ret(list)
                    : sus(() -> drop_(list.tail(), n - 1));
        }
    }
}
