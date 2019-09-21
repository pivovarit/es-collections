package com.pivovarit.escollections.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

public class ESList<T> implements List<T> {

    private final AtomicInteger version = new AtomicInteger();

    private final List<ListOp<T>> eventLog = new ArrayList<>(); // TODO concurrent access

    private ESList() {
    }

    public static <T> ESList<T> empty() {
        return new ESList<>();
    }

    @Override
    public int size() {
        return snapshot().size();
    }

    @Override
    public boolean isEmpty() {
        return snapshot().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return snapshot().isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return snapshot().iterator();
    }

    @Override
    public Object[] toArray() {
        return snapshot().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return snapshot().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return snapshot().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return snapshot().get(index);
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return snapshot().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return snapshot().lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return snapshot().listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return snapshot().listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return snapshot().subList(fromIndex, toIndex);
    }

    private List<T> snapshot() {
        List<T> snapshot = new ArrayList<>();
        for (int i = 0; i < version.get(); i++) {
            ListOp<T> tListOp = eventLog.get(i);
            snapshot = tListOp.apply(snapshot);
        }
        return snapshot;
    }
}
