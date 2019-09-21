package com.pivovarit.escollections.list;

import java.util.Collection;
import java.util.List;

class RemoveAllOp<T> implements ListOp<T> {

    private final Collection<?> elem;

    RemoveAllOp(Collection<?> elem) {
        this.elem = elem;
    }

    @Override
    public List<T> apply(List<T> list) {
        list.removeAll(elem);
        return list;
    }
}
