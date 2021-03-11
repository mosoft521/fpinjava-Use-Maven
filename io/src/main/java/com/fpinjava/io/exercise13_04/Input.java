package com.fpinjava.io.exercise13_04;


import com.fpinjava.common.Result;
import com.fpinjava.common.Tuple;

public interface Input {

    Result<Tuple<String, Input>> readString();

    Result<Tuple<Integer, Input>> readInt();

    default Result<Tuple<String, Input>> readString(String message) {
        return readString();
    }

    default Result<Tuple<Integer, Input>> readInt(String message) {
        return readInt();
    }
}
