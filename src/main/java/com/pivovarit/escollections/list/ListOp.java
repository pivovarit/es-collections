package com.pivovarit.escollections.list;

import java.util.List;

interface ListOp<R> {
    Object apply(List<R> list);
}
