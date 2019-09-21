package com.pivovarit.escollections.list;

import java.util.List;

class RemoveIdxOp<T> implements ListOp<T> {

    private final int idx;

    public RemoveIdxOp(int idx) {
        this.idx = idx;
    }

    @Override
    public Object apply(List<T> list) {
        return list.remove((int) idx);
    }

    @Override
    public String toString() {
        return String.format("remove(index = %d)", idx);
    }
}
