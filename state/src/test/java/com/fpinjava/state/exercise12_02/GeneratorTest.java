package com.fpinjava.state.exercise12_02;

import com.fpinjava.common.List;
import com.fpinjava.common.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GeneratorTest {


    @Test
    public void testIntegers() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers(rng, 3);
        assertEquals(List.list(-723955400, -1155484576, 384748), result._1);
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(1033096058), t._1);
    }

    @Test
    public void testIntegersLength1() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers(rng, 1);
        assertEquals(List.list(384748), result._1);
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(-1155484576), t._1);
    }

    @Test
    public void testIntegersLength0() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers(rng, 0);
        assertEquals(0, result._1.length());
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(384748), t._1);
    }

    @Test
    public void testIntegersNegativeLength() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers(rng, -3);
        assertEquals(0, result._1.length());
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(384748), t._1);
    }

    @Test
    public void testIntegers2() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers2(rng, 3);
        assertEquals(List.list(384748, 384748, 384748), result._1);
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(-1155484576), t._1);
    }

    @Test
    public void testIntegers2Length1() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers2(rng, 1);
        assertEquals(List.list(384748), result._1);
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(-1155484576), t._1);
    }

    @Test
    public void testIntegers2Length0() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers2(rng, 0);
        assertEquals(0, result._1.length());
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(384748), t._1);
    }

    @Test
    public void testIntegers2NegativeLength() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers2(rng, -3);
        assertEquals(0, result._1.length());
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(384748), t._1);
    }

    @Test
    public void testIntegers3() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers3(rng, 3);
        assertEquals(List.list(-723955400, -1155484576, 384748), result._1);
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(1033096058), t._1);
    }

    @Test
    public void testIntegers3Length1() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers3(rng, 1);
        assertEquals(List.list(384748), result._1);
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(-1155484576), t._1);
    }

    @Test
    public void testIntegers3Length0() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers3(rng, 0);
        assertEquals(0, result._1.length());
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(384748), t._1);
    }

    @Test
    public void testIntegers3NegativeLength() {
        RNG rng = JavaRNG.rng(0);
        Tuple<List<Integer>, RNG> result = Generator.integers3(rng, -3);
        assertEquals(0, result._1.length());
        Tuple<Integer, RNG> t = Generator.integer(result._2);
        assertEquals(Integer.valueOf(384748), t._1);
    }

}
