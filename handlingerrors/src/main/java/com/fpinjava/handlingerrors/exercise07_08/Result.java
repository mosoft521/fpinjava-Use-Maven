package com.fpinjava.handlingerrors.exercise07_08;


import com.fpinjava.common.Function;
import com.fpinjava.common.Supplier;

import java.io.Serializable;

public abstract class Result<T> implements Serializable {

    @SuppressWarnings("rawtypes")
    private static Result empty = new Empty();

    private Result() {
    }

    public static <T> Result<T> failure(String message) {
        return new Failure<>(message);
    }

    public static <T> Result<T> failure(Exception e) {
        return new Failure<>(e);
    }

    public static <T> Result<T> failure(RuntimeException e) {
        return new Failure<>(e);
    }

    public static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Result<T> empty() {
        return empty;
    }

    public static <T> Result<T> of(T value) {
        return value != null
                ? success(value)
                : empty();
    }

    public static <T> Result<T> of(T value, String message) {
        return value != null
                ? success(value)
                : failure(message);
    }

    public static <T> Result<T> of(Function<T, Boolean> predicate, T value) {
        try {
            return predicate.apply(value)
                    ? success(value)
                    : empty();
        } catch (Exception e) {
            String errMessage =
                    String.format("Exception while evaluating predicate: %s", value);
            return Result.failure(new IllegalStateException(errMessage, e));
        }
    }

    public static <T> Result<T> of(Function<T, Boolean> predicate,
                                   T value, String message) {
        try {
            return predicate.apply(value)
                    ? Result.success(value)
                    : Result.failure(String.format(message, value));
        } catch (Exception e) {
            String errMessage =
                    String.format("Exception while evaluating predicate: %s",
                            String.format(message, value));
            return Result.failure(new IllegalStateException(errMessage, e));
        }
    }

    public abstract T getOrElse(final T defaultValue);

    public abstract T getOrElse(final Supplier<T> defaultValue);

    public abstract <U> Result<U> map(Function<T, U> f);

    public abstract <U> Result<U> flatMap(Function<T, Result<U>> f);

    public abstract Result<T> mapFailure(String s);

    public abstract Result<T> mapFailure(String s, Exception e);

    public abstract Result<T> mapFailure(Exception e);

    public abstract Result<T> failIfEmpty(String message);

    public Result<T> orElse(Supplier<Result<T>> defaultValue) {
        return map(x -> this).getOrElse(defaultValue);
    }

    public Result<T> filter(Function<T, Boolean> p) {
        return flatMap(x -> p.apply(x)
                ? this
                : failure("Condition not matched"));
    }

    public Result<T> filter(Function<T, Boolean> p, String message) {
        return flatMap(x -> p.apply(x)
                ? this
                : failure(message));
    }

    public boolean exists(Function<T, Boolean> p) {
        return map(p).getOrElse(false);
    }

    private static class Empty<T> extends Result<T> {

        public Empty() {
            super();
        }

        @Override
        public T getOrElse(final T defaultValue) {
            return defaultValue;
        }

        @Override
        public <U> Result<U> map(Function<T, U> f) {
            return empty();
        }

        @Override
        public <U> Result<U> flatMap(Function<T, Result<U>> f) {
            return empty();
        }

        @Override
        public Result<T> mapFailure(String s) {
            return this;
        }

        @Override
        public Result<T> mapFailure(String s, Exception e) {
            return this;
        }

        @Override
        public Result<T> mapFailure(Exception e) {
            return this;
        }

        @Override
        public Result<T> failIfEmpty(String message) {
            return failure(message);
        }

        @Override
        public String toString() {
            return "Empty()";
        }

        @Override
        public T getOrElse(Supplier<T> defaultValue) {
            return defaultValue.get();
        }
    }

    private static class Failure<T> extends Empty<T> {

        private final RuntimeException exception;

        private Failure(String message) {
            super();
            this.exception = new IllegalStateException(message);
        }

        private Failure(RuntimeException e) {
            super();
            this.exception = e;
        }

        private Failure(Exception e) {
            super();
            this.exception = new IllegalStateException(e.getMessage(), e);
        }

        @Override
        public String toString() {
            return String.format("Failure(%s)", exception.getMessage());
        }

        @Override
        public <U> Result<U> map(Function<T, U> f) {
            return failure(exception);
        }

        @Override
        public <U> Result<U> flatMap(Function<T, Result<U>> f) {
            return failure(exception);
        }

        @Override
        public Result<T> mapFailure(String s) {
            return failure(new IllegalStateException(s, exception));
        }

        @Override
        public Result<T> mapFailure(String s, Exception e) {
            return failure(new IllegalStateException(s, e));
        }

        @Override
        public Result<T> mapFailure(Exception e) {
            return failure(e);
        }

        @Override
        public Result<T> failIfEmpty(String message) {
            return failure(message);
        }
    }

    private static class Success<T> extends Result<T> {

        private final T value;

        private Success(T value) {
            super();
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Success(%s)", value.toString());
        }

        @Override
        public T getOrElse(T defaultValue) {
            return value;
        }

        @Override
        public T getOrElse(Supplier<T> defaultValue) {
            return value;
        }

        @Override
        public <U> Result<U> map(Function<T, U> f) {
            return success(f.apply(value));
        }

        @Override
        public <U> Result<U> flatMap(Function<T, Result<U>> f) {
            return f.apply(value);
        }

        @Override
        public Result<T> mapFailure(String s) {
            return this;
        }

        @Override
        public Result<T> mapFailure(String s, Exception e) {
            return this;
        }

        @Override
        public Result<T> mapFailure(Exception e) {
            return this;
        }

        @Override
        public Result<T> failIfEmpty(String message) {
            return this;
        }
    }
}
