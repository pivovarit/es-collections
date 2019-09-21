package com.pivovarit.escollections.list;

import java.util.Collection;
import java.util.List;

class AddAllIdxOp<T> implements ListOp<T> {

    private final int idx;
    private final Collection<? extends T> elem;

    public AddAllIdxOp(int idx, Collection<? extends T> elem) {
        this.idx = idx;
        this.elem = elem;
    }

    @Override
    public Object apply(List<T> list) {
        return list.addAll(idx, elem);
    }
}
