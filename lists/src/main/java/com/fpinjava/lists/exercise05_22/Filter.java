package com.fpinjava.lists.exercise05_22;

import com.fpinjava.common.Function;
import com.fpinjava.lists.exercise05_21.List;

public class Filter {

    public static <A> List<A> filterViaFlatMap(List<A> list, Function<A, Boolean> p) {
        return list.flatMap(a -> p.apply(a)
                ? List.list(a)
                : List.list());
    }
}
