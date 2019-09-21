package com.pivovarit.escollections.list;

import java.util.List;

class AddIdxOp<T> implements ListOp<T> {
    private final int idx;
    private final T elem;

    AddIdxOp(int idx, T elem) {
        this.idx = idx;
        this.elem = elem;
    }

    @Override
    public Object apply(List<T> list) {
        list.add(idx, elem);
        return null;
    }
}
