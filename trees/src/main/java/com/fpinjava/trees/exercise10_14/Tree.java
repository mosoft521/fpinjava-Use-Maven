package com.fpinjava.trees.exercise10_14;


import com.fpinjava.common.Function;
import com.fpinjava.common.List;
import com.fpinjava.common.Result;
import com.fpinjava.common.TailCall;
import com.fpinjava.common.Tuple;

public abstract class Tree<A extends Comparable<A>> {

    @SuppressWarnings("rawtypes")
    private static Tree EMPTY = new Empty();

    @SuppressWarnings("unchecked")
    public static <A extends Comparable<A>> Tree<A> empty() {
        return EMPTY;
    }

    public static <A extends Comparable<A>> Tree<A> tree(List<A> list) {
        return list.foldLeft(empty(), t -> t::insert);
    }

    @SafeVarargs
    public static <A extends Comparable<A>> Tree<A> tree(A... as) {
        return tree(List.list(as));
    }

    public static <A extends Comparable<A>> boolean lt(A first, A second) {
        return first.compareTo(second) < 0;
    }

    public static <A extends Comparable<A>> boolean lt(A first, A second, A third) {
        return lt(first, second) && lt(second, third);
    }

    public static <A extends Comparable<A>> Tree<A> tree(Tree<A> t1, A a, Tree<A> t2) {
        return ordered(t1, a, t2)
                ? new T<>(t1, a, t2)
                : ordered(t2, a, t1)
                ? new T<>(t2, a, t1)
                : Tree.<A>empty().insert(a).merge(t1).merge(t2);
    }

    public static <A extends Comparable<A>> boolean ordered(Tree<A> left, A a, Tree<A> right) {
        return left.max().flatMap(lMax -> right.min().map(rMin -> lt(lMax, a, rMin))).getOrElse(left.isEmpty() && right.isEmpty())
                || left.min().mapEmpty().flatMap(ignore -> right.min().map(rMin -> lt(a, rMin))).getOrElse(false)
                || right.min().mapEmpty().flatMap(ignore -> left.max().map(lMax -> lt(lMax, a))).getOrElse(false);
    }

    public static <A extends Comparable<A>> Tree<A> balance(Tree<A> tree) {
        return balance_(tree.toListInOrderRight().foldLeft(Tree.<A>empty(), t -> a -> new T<>(empty(), a, t)));
    }

    private static <A extends Comparable<A>> Tree<A> balance_(Tree<A> tree) {
        return !tree.isEmpty() && tree.height() > log2nlz(tree.size())
                ? Math.abs(tree.left().height() - tree.right().height()) > 1
                ? balance_(balanceFirstLevel(tree))
                : new T<>(balance_(tree.left()), tree.value(), balance_(tree.right()))
                : tree;
    }

    public static int log2nlz(int n) {
        return n == 0
                ? 0
                : 31 - Integer.numberOfLeadingZeros(n);
    }

    private static <A extends Comparable<A>> Tree<A> balanceFirstLevel(Tree<A> tree) {
        return unfold(tree, t -> isUnBalanced(t)
                ? tree.right().height() > tree.left().height()
                ? Result.success(t.rotateLeft())
                : Result.success(t.rotateRight())
                : Result.empty());
    }

    private static <A extends Comparable<A>> boolean isUnBalanced(Tree<A> tree) {
        // Difference must be 0 if total size of branches is even and 1 if size is odd
        return Math.abs(tree.left().height() - tree.right().height()) > (tree.size() - 1) % 2;
    }

    public static <A> A unfold(A a, Function<A, Result<A>> f) {
        Result<A> ra = Result.success(a);
        return unfold(new Tuple<>(ra, ra), f).eval()._2.getOrElse(a);
    }

    public static <A> TailCall<Tuple<Result<A>, Result<A>>> unfold(Tuple<Result<A>, Result<A>> a, Function<A, Result<A>> f) {
        Result<A> x = a._2.flatMap(f::apply);
        return x.isSuccess()
                ? TailCall.sus(() -> unfold(new Tuple<>(a._2, x), f))
                : TailCall.ret(a);
    }

    public abstract A value();

    abstract Tree<A> left();

    abstract Tree<A> right();

    public abstract Tree<A> insert(A a);

    public abstract boolean member(A a);

    public abstract int size();

    public abstract int height();

    public abstract Result<A> max();

    public abstract Result<A> min();

    public abstract Tree<A> remove(A a);

    public abstract boolean isEmpty();

    public abstract Tree<A> merge(Tree<A> a);

    public abstract Tree<A> merge(A a, Tree<A> right);

    public abstract List<A> toListInOrderRight();

    /**
     * Merges two subtrees with the particularity that all elements of one
     * are either greater or smaller than all elements of the other.
     * <p>
     * This is an optimized merge for removal of the value, when we need to merge
     * the remaining right and left tree.
     */
    protected abstract Tree<A> removeMerge(Tree<A> ta);

    public abstract <B> B foldLeft(B identity, Function<B, Function<A, B>> f, Function<B, Function<B, B>> g);

    public abstract <B> B foldRight(B identity, Function<A, Function<B, B>> f, Function<B, Function<B, B>> g);

    public abstract <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f);

    public abstract <B> B foldPreOrder(B identity, Function<A, Function<B, Function<B, B>>> f);

    public abstract <B> B foldPostOrder(B identity, Function<B, Function<B, Function<A, B>>> f);

    public abstract <B extends Comparable<B>> Tree<B> map(Function<A, B> f);

    protected abstract Tree<A> rotateLeft();

    protected abstract Tree<A> rotateRight();

    private static class Empty<A extends Comparable<A>> extends Tree<A> {

        @Override
        public A value() {
            throw new IllegalStateException("value() called on empty");
        }

        @Override
        Tree<A> left() {
            throw new IllegalStateException("left() called on empty");
        }

        @Override
        Tree<A> right() {
            throw new IllegalStateException("right() called on empty");
        }

        @Override
        public Tree<A> insert(A value) {
            return new T<>(empty(), value, empty());
        }

        @Override
        public boolean member(A a) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public int height() {
            return -1;
        }

        @Override
        public Result<A> max() {
            return Result.empty();
        }

        @Override
        public Result<A> min() {
            return Result.empty();
        }

        @Override
        public Tree<A> remove(A a) {
            return this;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public Tree<A> merge(Tree<A> a) {
            return a;
        }

        @Override
        public Tree<A> merge(A a, Tree<A> right) {
            return right.min().map(min -> a.compareTo(min) < 0
                    ? new T<>(empty(), a, right)
                    : right.insert(a)).getOrElse(this.insert(a));
        }

        @Override
        public List<A> toListInOrderRight() {
            return List.list();
        }

        @Override
        protected Tree<A> removeMerge(Tree<A> ta) {
            return ta;
        }

        @Override
        public <B> B foldLeft(B identity, Function<B, Function<A, B>> f, Function<B, Function<B, B>> g) {
            return identity;
        }

        @Override
        public <B> B foldRight(B identity, Function<A, Function<B, B>> f, Function<B, Function<B, B>> g) {
            return identity;
        }

        @Override
        public <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f) {
            return identity;
        }

        @Override
        public <B> B foldPreOrder(B identity, Function<A, Function<B, Function<B, B>>> f) {
            return identity;
        }

        @Override
        public <B> B foldPostOrder(B identity, Function<B, Function<B, Function<A, B>>> f) {
            return identity;
        }

        @Override
        public <B extends Comparable<B>> Tree<B> map(Function<A, B> f) {
            return empty();
        }

        @Override
        protected Tree<A> rotateLeft() {
            return this;
        }

        @Override
        protected Tree<A> rotateRight() {
            return this;
        }

        @Override
        public String toString() {
            return "E";
        }
    }

    private static class T<A extends Comparable<A>> extends Tree<A> {

        private final Tree<A> left;
        private final Tree<A> right;
        private final A value;
        private final int height;
        private final int size;

        private T(Tree<A> left, A value, Tree<A> right) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.height = 1 + Math.max(left.height(), right.height());
            this.size = 1 + left.size() + right.size();
        }

        @Override
        public A value() {
            return value;
        }

        @Override
        Tree<A> left() {
            return left;
        }

        @Override
        Tree<A> right() {
            return right;
        }

        @Override
        public Tree<A> insert(A value) {
            return value.compareTo(this.value) < 0
                    ? new T<>(left.insert(value), this.value, right)
                    : value.compareTo(this.value) > 0
                    ? new T<>(left, this.value, right.insert(value))
                    : new T<>(this.left, value, this.right);
        }

        @Override
        public boolean member(A value) {
            return value.compareTo(this.value) < 0
                    ? left.member(value)
                    : value.compareTo(this.value) <= 0 || right.member(value);
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public int height() {
            return height;
        }

        @Override
        public Result<A> max() {
            return right.max().orElse(() -> Result.success(value));
        }

        @Override
        public Result<A> min() {
            return left.min().orElse(() -> Result.success(value));
        }

        @Override
        public Tree<A> remove(A a) {
            if (a.compareTo(this.value) < 0) {
                return new T<>(left.remove(a), value, right);
            } else if (a.compareTo(this.value) > 0) {
                return new T<>(left, value, right.remove(a));
            } else {
                return left.removeMerge(right);
            }
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public Tree<A> merge(Tree<A> a) {
            if (a.isEmpty()) {
                return this;
            }
            if (a.value().compareTo(this.value) > 0) {
                return new T<>(left, value, right.merge(new T<>(empty(), a.value(), a.right()))).merge(a.left());
            }
            if (a.value().compareTo(this.value) < 0) {
                return new T<>(left.merge(new T<>(a.left(), a.value(), empty())), value, right).merge(a.right());
            }
            return new T<>(left.merge(a.left()), value, right.merge(a.right()));
        }

        @Override
        public Tree<A> merge(A a, Tree<A> right) {
            return right.isEmpty()
                    ? max().map(max -> max.compareTo(a) > 0 // Default value can never be used, but is necessary
                    ? insert(a)
                    : new T<>(this, a, right)).getOrElse(this)
                    : max().flatMap(lmax -> right.min().map(rmin -> a.compareTo(lmax) > 0 && a.compareTo(rmin) < 0 ? new T<>(this, a, right) : merge(right).insert(a))).getOrElse(right);
        }

        @Override
        public List<A> toListInOrderRight() {
            return unBalanceRight(List.list(), this).eval();
        }

        private TailCall<List<A>> unBalanceRight(List<A> acc, Tree<A> tree) {
            return tree.isEmpty()
                    ? TailCall.ret(acc)
                    : tree.left().isEmpty()
                    ? TailCall.sus(() -> unBalanceRight(acc.cons(tree.value()), tree.right()))
                    : TailCall.sus(() -> unBalanceRight(acc, tree.rotateRight()));
        }

        protected Tree<A> removeMerge(Tree<A> ta) {
            if (ta.isEmpty()) {
                return this;
            }
            if (ta.value().compareTo(value) < 0) {
                return new T<>(left.removeMerge(ta), value, right);
            } else if (ta.value().compareTo(value) > 0) {
                return new T<>(left, value, right.removeMerge(ta));
            }
            throw new IllegalStateException("Shouldn't be merging two subtrees with the same value");
        }

        @Override
        public <B> B foldLeft(B identity, Function<B, Function<A, B>> f, Function<B, Function<B, B>> g) {
            // Post order right:
            return g.apply(right.foldLeft(identity, f, g)).apply(f.apply(left.foldLeft(identity, f, g)).apply(this.value));
        }

        @Override
        public <B> B foldRight(B identity, Function<A, Function<B, B>> f, Function<B, Function<B, B>> g) {
            // Pre order left
            return g.apply(f.apply(this.value).apply(left.foldRight(identity, f, g))).apply(right.foldRight(identity, f, g));
        }

        @Override
        public <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f) {
            return f.apply(left.foldInOrder(identity, f)).apply(value).apply(right.foldInOrder(identity, f));
        }

        @Override
        public <B> B foldPreOrder(B identity, Function<A, Function<B, Function<B, B>>> f) {
            return f.apply(value).apply(left.foldPreOrder(identity, f)).apply(right.foldPreOrder(identity, f));
        }

        @Override
        public <B> B foldPostOrder(B identity, Function<B, Function<B, Function<A, B>>> f) {
            return f.apply(left.foldPostOrder(identity, f)).apply(right.foldPostOrder(identity, f)).apply(value);
        }

        @Override
        public <B extends Comparable<B>> Tree<B> map(Function<A, B> f) {
            return foldInOrder(Tree.<B>empty(), t1 -> i -> t2 -> Tree.tree(t1, f.apply(i), t2));
        }

        @Override
        protected Tree<A> rotateLeft() {
            return right.isEmpty()
                    ? this
                    : new T<>(new T<>(left, value, right.left()), right.value(), right.right());
        }

        @Override
        protected Tree<A> rotateRight() {
            return left.isEmpty()
                    ? this
                    : new T<>(left.left(), left.value(), new T<>(left.right(), value, right));
        }

        @Override
        public String toString() {
            return String.format("(T %s %s %s)", left, value, right);
        }
    }
}
