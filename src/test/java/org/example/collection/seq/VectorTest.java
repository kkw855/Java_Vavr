package org.example.collection.seq;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.collection.Vector;
import org.junit.jupiter.api.Test;

public class VectorTest {
  Vector<Integer> vector = Vector.range(1, 6);

  @Test
  public void construct() {
    Vector<Integer> vector1 = Vector.range(1, 6);
    Vector<Integer> vector2 = Vector.rangeClosed(1, 5);
    Vector<Integer> vector3 = Vector.rangeClosedBy(0, 5, 2);

    assertThat(vector1).containsExactlyElementsIn(Vector.of(1, 2, 3, 4, 5)).inOrder();
    assertThat(vector2).containsExactlyElementsIn(Vector.of(1, 2, 3, 4, 5)).inOrder();
    assertThat(vector3).containsExactlyElementsIn(Vector.of(0, 2, 4)).inOrder();
  }

  @Test
  public void size() {
    assertThat(vector.size()).isEqualTo(5);
  }

  @Test
  public void get() {
    assertThat(vector.get(0)).isEqualTo(1);
  }
}
