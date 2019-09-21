package com.pivovarit.escollections.list;

import java.util.List;

class AddOp<T> implements ListOp<T> {
    private final T elem;

    AddOp(T elem) {
        this.elem = elem;
    }

    @Override
    public List<T> apply(List<T> list) {
        list.add(elem);
        return list;
    }
}
