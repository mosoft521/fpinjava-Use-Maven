package com.fpinjava.io.exercise13_05;


public interface IO {

    IO empty = () -> {
    };

    void run();

    default IO add(IO io) {
        return () -> {
            IO.this.run();
            io.run();
        };
    }

}
