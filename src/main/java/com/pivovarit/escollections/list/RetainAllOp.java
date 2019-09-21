package com.pivovarit.escollections.list;

import java.util.Collection;
import java.util.List;

class RetainAllOp<T> implements ListOp<T> {

    private final Collection<?> elem;

    public RetainAllOp(Collection<?> elem) {
        this.elem = elem;
    }

    @Override
    public Object apply(List<T> list) {
        return list.retainAll(elem);
    }
}
