package org.example.collection.seq;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.collection.Array;
import org.junit.jupiter.api.Test;

public class ArrayTest {
  @Test
  void construct() {
    Array<Integer> array1 = Array.of(1, 2, 3, 4, 5);
    Array<Integer> array2 = Array.range(1, 6);
    Array<Integer> array3 = Array.rangeClosed(1, 5);
    Array<Integer> array4 = Array.rangeClosedBy(1, 5, 1);

    assertThat(array1).containsExactlyElementsIn(Array.of(1, 2, 3, 4, 5)).inOrder();
    assertThat(array2).containsExactlyElementsIn(Array.of(1, 2, 3, 4, 5)).inOrder();
    assertThat(array3).containsExactlyElementsIn(Array.of(1, 2, 3, 4, 5)).inOrder();
    assertThat(array4).containsExactlyElementsIn(Array.of(1, 2, 3, 4, 5)).inOrder();
  }

  @Test
  public void remove() {
    Array<Integer> array = Array.of(1, 2, 3);

    assertThat(array.removeAt(1)).containsExactlyElementsIn(Array.of(1, 3)).inOrder();
  }

  @Test
  public void replace() {
    Array<Integer> array = Array.of(1, 2, 3);

    assertThat(array.replace(2, 5)).containsExactlyElementsIn(Array.of(1, 5, 3)).inOrder();
  }
}
