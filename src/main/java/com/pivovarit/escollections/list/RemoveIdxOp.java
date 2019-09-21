package com.pivovarit.escollections.list;

import java.util.List;

class RemoveIdxOp<T> implements ListOp<T> {

    private final int idx;

    public RemoveIdxOp(int idx) {
        this.idx = idx;
    }

    @Override
    public List<T> apply(List<T> list) {
        list.remove((int) idx);
        return list;
    }
}
