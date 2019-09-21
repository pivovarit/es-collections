package com.pivovarit.escollections.list;

import java.util.List;

class RemoveOp<T> implements ListOp<T> {

    private final Object elem;

    public RemoveOp(Object elem) {
        this.elem = elem;
    }

    @Override
    public Object apply(List<T> list) {
        return list.remove(elem);
    }

    @Override
    public String toString() {
        return String.format("remove(%s)", elem);
    }
}
