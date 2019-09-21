package com.pivovarit.escollections.list;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class ESListAPITest {

    @Test
    void empty() {
        var list = ESList.empty();
        assertThat(list.isEmpty()).isTrue();
        list.add(1);
    }

    @Test
    void not_empty() {
        var list = ESList.empty();
        list.add(1);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    void size_empty() {
        var list = ESList.empty();
        assertThat(list.size()).isZero();
    }


    @Test
    void size_not_empty() {
        var list = ESList.empty();
        list.add(ThreadLocalRandom.current().nextInt());
        assertThat(list.size()).isZero();
    }

    @Test
    void contains() {
        var list = ESList.empty();
        list.add(1);
        assertThat(list.contains(1)).isTrue();
        assertThat(list.contains(42)).isFalse();
    }

    @Test // TODO
    void iterator() {
    }

    @Test
    void toArray() {
        ESList<Integer> list = ESList.empty();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(list.toArray()).containsExactly(1, 2, 3);
    }

    @Test
    void toArrayGeneric() {
        ESList<Integer> list = ESList.empty();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(list.toArray(new Integer[0])).containsExactly(1, 2, 3);
    }

    @Test
    void add() {
        List<Integer> sut = ESList.empty();

        sut.add(42);
        sut.add(13);

        assertThat(sut.size()).isEqualTo(2);
        assertThat(sut.contains(42)).isTrue();
        assertThat(sut.contains(13)).isTrue();
        assertThat(sut.contains(12398)).isFalse();
    }

    @Test
    void remove() {
        List<Integer> sut = ESList.empty();

        sut.add(42);
        sut.add(13);
        sut.remove(Integer.valueOf(42));

        assertThat(sut.size()).isEqualTo(1);
        assertThat(sut.contains(13)).isTrue();
        assertThat(sut.contains(42)).isFalse();
    }

    @Test // TODO
    void containsAll() {
    }

    @Test // TODO
    void addAll() {
    }

    @Test // TODO
    void removeAll() {
    }

    @Test // TODO
    void retainAll() {
    }

    @Test
    void clear() {
        var list = ESList.empty();
        list.add(1);
        list.clear();
        assertThat(list).isEmpty();
    }

    @Test // TODO
    void get() {
    }

    @Test // TODO
    void set() {
    }

    @Test // TODO
    void indexOf() {
    }

    @Test // TODO
    void lastIndexOf() {
    }

    @Test // TODO
    void listIterator() {
    }

    @Test // TODO
    void testListIterator() {
    }

    @Test // TODO
    void subList() {
    }
}