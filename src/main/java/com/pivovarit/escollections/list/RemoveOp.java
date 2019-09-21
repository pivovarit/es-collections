package com.pivovarit.escollections.list;

import java.util.List;

class RemoveOp<T> implements ListOp<T> {

    private final Object elem;

    public RemoveOp(Object elem) {
        this.elem = elem;
    }

    @Override
    public List<T> apply(List<T> list) {
        list.remove(elem);
        return list;
    }
}
