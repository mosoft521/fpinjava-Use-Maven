package com.fpinjava.handlingerrors.exercise07_02;


import com.fpinjava.common.Function;

public abstract class Either<E, A> {

    public static <E, A> Either<E, A> left(E value) {
        return new Left<>(value);
    }

    public static <E, A> Either<E, A> right(A value) {
        return new Right<>(value);
    }

    public abstract <B> Either<E, B> map(Function<A, B> f);

    public abstract <B> Either<E, B> flatMap(Function<A, Either<E, B>> f);

    private static class Left<E, A> extends Either<E, A> {

        private final E value;

        private Left(E value) {
            this.value = value;
        }

        public <B> Either<E, B> map(Function<A, B> f) {
            return new Left<>(value);
        }

        public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f) {
            return new Left<>(value);
        }

        @Override
        public String toString() {
            return String.format("Left(%s)", value);
        }
    }

    private static class Right<E, A> extends Either<E, A> {

        private final A value;

        private Right(A value) {
            this.value = value;
        }

        public <B> Either<E, B> map(Function<A, B> f) {
            return new Right<>(f.apply(value));
        }

        public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f) {
            return f.apply(value);
        }

        @Override
        public String toString() {
            return String.format("Right(%s)", value);
        }
    }
}
