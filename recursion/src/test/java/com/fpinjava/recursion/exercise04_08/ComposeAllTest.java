package com.fpinjava.recursion.exercise04_08;

import com.fpinjava.common.Function;
import org.junit.Test;

import java.util.List;

import static com.fpinjava.common.CollectionUtilities.list;
import static org.junit.Assert.assertEquals;

public class ComposeAllTest {

    Function<String, String> f1 = x -> "(a" + x + ")";
    Function<String, String> f2 = x -> "{b" + x + "}";
    Function<String, String> f3 = x -> "[c" + x + "]";
    List<Function<String, String>> list = list(f1, f2, f3);

    @Test
    public void testComposeAllLeft() {
        assertEquals("(a{b[cx]})", ComposeAll.composeAllViaFoldLeft(list).apply("x"));
    }

    @Test
    public void testComposeAllRight() {
        assertEquals("(a{b[cx]})", ComposeAll.composeAllViaFoldRight(list).apply("x"));
    }

    @Test
    public void testAndThenAllLeft() {
        assertEquals("[c{b(ax)}]", ComposeAll.andThenAllViaFoldLeft(list).apply("x"));
    }

    @Test
    public void testAndThenAllRight() {
        assertEquals("[c{b(ax)}]", ComposeAll.andThenAllViaFoldRight(list).apply("x"));
    }
}
