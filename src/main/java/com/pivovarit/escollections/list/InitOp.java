package com.pivovarit.escollections.list;

import java.util.List;

class InitOp<T> implements ListOp<T> {

    @Override
    public Object apply(List<T> list) {
        return null;
    }

    @Override
    public String toString() {
        return "init[]";
    }
}
