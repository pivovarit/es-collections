package com.pivovarit.escollections.list;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
  // TODO
class ESListTest {

    @Test
    void shouldCreateEmpty() {
        List<Integer> sut = ESList.empty();

        assertThat(sut.size()).isZero();
        assertThat(sut.isEmpty()).isTrue();
    }

    @Test
    void shouldAddAndGet() {
        List<Integer> sut = ESList.empty();

        sut.add(42);
        sut.add(13);

        assertThat(sut.size()).isEqualTo(2);
        assertThat(sut.contains(42)).isTrue();
        assertThat(sut.contains(13)).isTrue();
        assertThat(sut.contains(12398)).isFalse();
    }

    @Test
    void shouldAddAndRemove() {
        List<Integer> sut = ESList.empty();

        sut.add(42);
        sut.add(13);
        sut.remove(Integer.valueOf(42));

        assertThat(sut.size()).isEqualTo(1);
        assertThat(sut.contains(13)).isTrue();
        assertThat(sut.contains(42)).isFalse();
    }

    @Test
    void shouldRemoveAll() {
        List<Integer> sut = ESList.empty();

        sut.add(42);
        sut.add(13);

        assertThat(sut.size()).isZero();
        assertThat(sut.isEmpty()).isTrue();
    }
}