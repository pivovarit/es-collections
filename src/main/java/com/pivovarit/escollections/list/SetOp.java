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
    public List<T> apply(List<T> list) {
        list.set(idx, elem);
        return list;
    }
}
