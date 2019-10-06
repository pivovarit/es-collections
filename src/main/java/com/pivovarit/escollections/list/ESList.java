package com.pivovarit.escollections.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * snapshottable cache-aware lock-free concurrent retaining list
 */
public class ESList<T> implements List<T> {

    private static final InitOp<?> EMPTY_INIT = new InitOp<>();

    private final AtomicInteger version = new AtomicInteger(0);

    /**
     * append-only bin log (infinite retention for now)
     */
    private final ArrayList<ListOp<T>> binLog = new ArrayList<>(); // TODO manage concurrent access and retention

    private final List<T> current = new ArrayList<>();

    private ESList() {
        handle((InitOp<T>) EMPTY_INIT);
    }

    public static <T> ESList<T> newInstance() {
        return new ESList<>();
    }

    @Override
    public int size() {
        return current.size();
    }

    @Override
    public boolean isEmpty() {
        return current.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return current.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return current.iterator();
    }

    @Override
    public Object[] toArray() {
        return current.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return current.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return (boolean) handle(new AddOp<>(t));
    }

    @Override
    public boolean remove(Object o) {
        return (boolean) handle(new RemoveOp<T>(o));
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return current.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return (boolean) handle(new AddAllOp<>(c));
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return (boolean) handle(new AddAllIdxOp<>(index, c));
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return (boolean) handle(new RemoveAllOp<>(c));
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return (boolean) handle(new RetainAllOp<>(c));
    }

    @Override
    public void clear() {
        handle(new ClearOp<>());
    }

    @Override
    public T get(int index) {
        return current.get(index);
    }

    @Override
    public T set(int index, T element) {
        return (T) handle(new SetOp<>(index, element));
    }

    @Override
    public void add(int index, T element) {
        handle(new AddIdxOp<>(index, element));
    }

    @Override
    public T remove(int index) {
        return (T) handle(new RemoveIdxOp<T>(index));
    }

    @Override
    public int indexOf(Object o) {
        return current.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return current.lastIndexOf(o);
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
            try {
                binLog.get(i).apply(snapshot);
            } catch (Exception ignored) { }
        }
        return snapshot;
    }

    public void displayLog() {
        for (int i = 0; i < version.get(); i++) {
            System.out.printf("v%d :: %s%n", i, binLog.get(i).toString());
        }
    }

    private synchronized Object handle(ListOp<T> op) {
        binLog.add(op);
        version.incrementAndGet();
        return op.apply(current);
    }
}
