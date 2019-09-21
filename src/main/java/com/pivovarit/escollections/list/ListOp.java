package com.pivovarit.escollections.list;

import java.util.List;

interface ListOp<R> {
    List<R> apply(List<R> list);
}
