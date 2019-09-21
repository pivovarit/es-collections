package com.pivovarit.escollections.list;

import java.util.Collection;
import java.util.List;

class AddAllOp<T> implements ListOp<T> {

    private final Collection<? extends T> elem;

    AddAllOp(Collection<? extends T> elem) {
        this.elem = elem;
    }

    @Override
    public List<T> apply(List<T> list) {
        list.addAll(elem);
        return list;
    }
}
