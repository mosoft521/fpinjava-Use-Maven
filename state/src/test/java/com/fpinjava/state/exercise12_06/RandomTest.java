package com.fpinjava.state.exercise12_06;

import com.fpinjava.common.List;
import com.fpinjava.common.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RandomTest {

    @Test
    public void testSequence() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Random.integersRnd.apply(3).apply(rng);
        assertEquals(List.list(384748, -1155484576, -723955400), result._1);
        Tuple<Integer, RNG> t = Random.intRnd.apply(result._2);
        assertEquals(Integer.valueOf(1033096058), t._1);
    }

    @Test
    public void testSequenceLength1() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Random.integersRnd.apply(1).apply(rng);
        assertEquals(List.list(384748), result._1);
        Tuple<Integer, RNG> t = Random.intRnd.apply(result._2);
        assertEquals(Integer.valueOf(-1155484576), t._1);
    }

    @Test
    public void testSequenceLength0() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Random.integersRnd.apply(0).apply(rng);
        assertEquals(0, result._1.length());
        Tuple<Integer, RNG> t = Random.intRnd.apply(result._2);
        assertEquals(Integer.valueOf(384748), t._1);
    }

    @Test
    public void testSequenceNegativeLength() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Random.integersRnd.apply(-3).apply(rng);
        assertEquals(0, result._1.length());
        Tuple<Integer, RNG> t = Random.intRnd.apply(result._2);
        assertEquals(Integer.valueOf(384748), t._1);
    }
}
