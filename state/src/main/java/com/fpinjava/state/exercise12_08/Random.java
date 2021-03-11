package com.fpinjava.state.exercise12_08;


import com.fpinjava.common.Function;
import com.fpinjava.common.List;
import com.fpinjava.common.Tuple;

public interface Random<A> extends Function<RNG, Tuple<A, RNG>> {

    Random<Integer> intRnd = RNG::nextInt;
    Random<Boolean> booleanRnd = Random.map(intRnd, x -> x % 2 == 0);
    Random<Double> doubleRnd = map(intRnd, x -> x / (((double) Integer.MAX_VALUE) + 1.0));
    Random<Tuple<Integer, Integer>> intPairRnd = map2(intRnd, intRnd, x -> y -> new Tuple<>(x, y));
    Function<Integer, Random<List<Integer>>> integersRnd = length -> sequence(List.fill(length, () -> intRnd));
    Random<Integer> notMultipleOfFiveRnd = Random.flatMap(intRnd, x -> {
        int mod = x % 5;
        return mod != 0
                ? unit(x)
                : Random.notMultipleOfFiveRnd;
    });

    static <A> Random<A> unit(A a) {
        return rng -> new Tuple<>(a, rng);
    }

    static <A, B> Random<B> map(Random<A> s, Function<A, B> f) {
        return flatMap(s, a -> unit(f.apply(a)));
    }

    static <A, B, C> Random<C> map2(Random<A> ra, Random<B> rb, Function<A, Function<B, C>> f) {
        return flatMap(ra, a -> map(rb, b -> f.apply(a).apply(b)));
    }

    static <A> Random<List<A>> sequence(List<Random<A>> rs) {
        return rs.foldLeft(unit(List.list()), acc -> r -> map2(r, acc, x -> y -> y.cons(x)));
    }

    static <A> Random<List<A>> sequence2(List<Random<A>> rs) {
        return rs.foldRight(unit(List.list()), r -> acc -> map2(r, acc, x -> y -> y.cons(x)));
    }

    static <A, B> Random<B> flatMap(Random<A> s, Function<A, Random<B>> f) {
        return rng -> {
            Tuple<A, RNG> t = s.apply(rng);
            return f.apply(t._1).apply(t._2);
        };
    }
}
