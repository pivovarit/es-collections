package com.pivovarit.escollections.list;

import java.util.List;

class CleanOp<T> implements ListOp<T> {
    @Override
    public Object apply(List<T> list) {
        list.clear();
        return null;
    }
}
