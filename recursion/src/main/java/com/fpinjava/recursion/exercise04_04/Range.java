package com.fpinjava.recursion.exercise04_04;

import com.fpinjava.recursion.listing04_03.TailCall;

import java.util.List;

import static com.fpinjava.common.CollectionUtilities.append;
import static com.fpinjava.common.CollectionUtilities.list;
import static com.fpinjava.recursion.listing04_03.TailCall.ret;
import static com.fpinjava.recursion.listing04_03.TailCall.sus;

public class Range {

    public static List<Integer> range(Integer start, Integer end) {
        return range_(list(), start, end).eval();
    }

    private static TailCall<List<Integer>> range_(List<Integer> acc, Integer start, Integer end) {
        return end <= start
                ? ret(acc)
                : sus(() -> range_(append(acc, start), start + 1, end));
    }
}
