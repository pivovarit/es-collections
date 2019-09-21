package com.pivovarit.escollections.list;

interface ListOp<R> {
    int version();
    ESList<R> apply(ESList<R> list);
}
