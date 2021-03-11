package com.fpinjava.actors.listing14_06;


import com.fpinjava.actors.listing14_05.AbstractActor;
import com.fpinjava.actors.listing14_05.Actor;
import com.fpinjava.common.Result;
import com.fpinjava.common.TailCall;


public class Worker extends AbstractActor<Integer> {

    public Worker(String id, Type type) {
        super(id, type);
    }

    private static int fibo(int number) {
        return fibo_(0, 1, number).eval();
    }

    private static TailCall<Integer> fibo_(int acc1, int acc2, int x) {
        if (x == 0) {
            return TailCall.ret(1);
        } else if (x == 1) {
            return TailCall.ret(acc1 + acc2);
        } else {
            return TailCall.sus(() -> fibo_(acc2, acc1 + acc2, x - 1));
        }
    }

    @Override
    public void onReceive(Integer message, Result<Actor<Integer>> sender) {
        sender.forEach(a -> a.tell(fibo(message), self()));
    }

}
