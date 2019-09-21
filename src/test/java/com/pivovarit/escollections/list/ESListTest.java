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
        List<Integer> sut = new ESList<>();

        assertThat(sut)
          .hasSize(0)
          .isEmpty();
    }

    @Test
    void shouldAddAndGet() {
        List<Integer> sut = new ESList<>();

        sut.add(42);
        sut.add(13);

        assertThat(sut)
          .hasSize(2)
          .containsExactly(42, 13);
    }

    @Test
    void shouldAddAndRemove() {
        List<Integer> sut = new ESList<>();

        sut.add(42);
        sut.add(13);
        sut.remove(Integer.valueOf(42));

        assertThat(sut)
          .hasSize(1)
          .containsExactly(13);
    }

    @Test
    void shouldRemoveAll() {
        List<Integer> sut = new ESList<>();

        sut.add(42);
        sut.add(13);

        assertThat(sut)
          .hasSize(0)
          .isEmpty();
    }
}