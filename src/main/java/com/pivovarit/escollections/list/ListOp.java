package com.pivovarit.escollections.list;

import java.util.List;

interface ListOp<R> {
    int version();
    List<R> apply(List<R> list);
}
