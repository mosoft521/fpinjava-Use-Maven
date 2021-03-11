package com.fpinjava.trees.exercise10_02;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TreeTest {

    @Test
    public void testMemberTrue() {
        Tree<Integer> tree = Tree.<Integer>empty().insert(4).insert(2).insert(6).insert(1).insert(3).insert(7).insert(5);
        assertTrue(tree.member(3));
    }

    @Test
    public void testMemberFalse() {
        Tree<Integer> tree = Tree.<Integer>empty().insert(4).insert(2).insert(6).insert(1).insert(3).insert(7).insert(5);
        assertFalse(tree.member(8));
    }
}
