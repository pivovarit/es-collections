package com.pivovarit.escollections.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

public class ESList<T> implements List<T> {

    private final AtomicInteger version = new AtomicInteger(0);

    /**
     * append-only bin log (infinite retention for now)
     */
    private final List<ListOp<T>> binLog = new ArrayList<>(); // TODO manage concurrent access

    private ESList() {
    }

    public static <T> ESList<T> newInstance() {
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
        return snapshot().contains(o);
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
        var op = new AddOp<>(t);
        register(op);
        incrementVersion();
        return (boolean) op.apply(snapshot());
    }

    @Override
    public boolean remove(Object o) {
        var op = new RemoveOp<T>(o);
        register(op);
        incrementVersion();
        return (boolean) op.apply(snapshot());
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return snapshot().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        var op = new AddAllOp<T>(c);
        register(op);
        incrementVersion();
        return (boolean) op.apply(snapshot());
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        var op = new AddAllIdxOp<T>(index, c);
        register(op);
        incrementVersion();
        return (boolean) op.apply(snapshot());
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        var op = new RemoveAllOp<T>(c);
        register(op);
        incrementVersion();
        return (boolean) op.apply(snapshot());
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        var op = new RetainAllOp<T>(c);
        register(op);
        incrementVersion();
        return (boolean) op.apply(snapshot());
    }

    @Override
    public void clear() {
        var op = new ClearOp<T>();
        register(op);
        incrementVersion();
    }

    @Override
    public T get(int index) {
        return snapshot().get(index);
    }

    @Override
    public T set(int index, T element) {
        var op = new SetOp<>(index, element);
        register(op);
        incrementVersion();
        return (T) op.apply(snapshot());
    }

    @Override
    public void add(int index, T element) {
        var op = new AddIdxOp<>(index, element);
        register(op);
        incrementVersion();
        op.apply(snapshot());
    }

    @Override
    public T remove(int index) {
        var op = new RemoveIdxOp<T>(index);
        register(op);
        incrementVersion();
        return (T) op.apply(snapshot());
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

    public int version() {
        return version.get();
    }

    public List<T> snapshot() {
        return snapshot(version.get());
    }

    public List<T> snapshot(int version) {
        var snapshot = new ArrayList<T>();
        for (int i = 0; i < version; i++) {
            binLog.get(i).apply(snapshot);
        }
        return snapshot;
    }

    public void displayLog() {
        for (int i = 0; i < version.get(); i++) {
            System.out.printf("v%d :: %s%n", i, binLog.get(i).toString());
        }
    }

    private void register(ListOp<T> op) {
        binLog.add(op);
    }

    private void incrementVersion() {
        version.incrementAndGet();
    }
}
