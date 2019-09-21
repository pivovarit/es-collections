package com.pivovarit.escollections.list;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class ESListAPITest {

    @Test
    void empty() {
        var list = ESList.instance();
        assertThat(list.isEmpty()).isTrue();
        list.add(1);
    }

    @Test
    void not_empty() {
        var list = ESList.instance();
        list.add(1);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    void size_empty() {
        var list = ESList.instance();
        assertThat(list.size()).isZero();
    }

    @Test
    void size_not_empty() {
        var list = ESList.instance();
        list.add(ThreadLocalRandom.current().nextInt());
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void contains() {
        var list = ESList.instance();
        list.add(1);
        assertThat(list.contains(1)).isTrue();
        assertThat(list.contains(42)).isFalse();
    }

    @Disabled // TODO
    @Test
    void iterator() {
    }

    @Test
    void toArray() {
        var list = ESList.instance();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(list.toArray()).containsExactly(1, 2, 3);
    }

    @Test
    void toArrayGeneric() {
        var list = ESList.instance();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(list.toArray(new Object[0])).containsExactly(1, 2, 3);
    }

    @Test
    void add() {
        var sut = ESList.instance();

        sut.add(42);
        sut.add(13);

        assertThat(sut.size()).isEqualTo(2);
        assertThat(sut.contains(42)).isTrue();
        assertThat(sut.contains(13)).isTrue();
        assertThat(sut.contains(12398)).isFalse();
    }

    @Test
    void remove() {
        var sut = ESList.instance();

        sut.add(42);
        sut.add(13);
        sut.remove(Integer.valueOf(42));

        assertThat(sut.size()).isEqualTo(1);
        assertThat(sut.contains(13)).isTrue();
        assertThat(sut.contains(42)).isFalse();
    }

    @Test
    void containsAll() {
        var sut = ESList.instance();

        sut.add(42);
        sut.add(13);

        assertThat(sut.containsAll(List.of(42, 13))).isTrue();
    }

    @Test
    void addAll() {
        var sut = ESList.instance();

        sut.addAll(List.of(42, 13));

        assertThat(sut).containsExactly(42, 13);
    }

    @Disabled // TODO
    @Test
    void removeAll() {
    }

    @Disabled // TODO
    @Test
    void retainAll() {
    }

    @Test
    void clear() {
        var list = ESList.instance();
        list.add(1);
        list.clear();
        assertThat(list).isEmpty();
    }

    @Disabled // TODO
    @Test
    void get() {
    }

    @Disabled // TODO
    @Test
    void set() {
    }

    @Disabled // TODO
    @Test
    void indexOf() {
    }

    @Disabled // TODO
    @Test
    void lastIndexOf() {
    }

    @Disabled // TODO
    @Test
    void listIterator() {
    }

    @Disabled // TODO
    @Test
    void testListIterator() {
    }

    @Disabled // TODO
    @Test
    void subList() {
    }
}