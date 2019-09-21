package com.pivovarit.escollections.list;

import java.util.List;

class SetOp<T> implements ListOp<T> {

    private final int idx;
    private final T elem;

    public SetOp(int idx, T elem) {
        this.idx = idx;
        this.elem = elem;
    }

    @Override
    public Object apply(List<T> list) {
        return list.set(idx, elem);
    }
}
