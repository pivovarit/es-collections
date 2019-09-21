package com.pivovarit.escollections.list;

import java.util.List;

class CleanOp<T> implements ListOp<T> {
    @Override
    public List<T> apply(List<T> list) {
        list.clear();
        return list;
    }
}
