package org.example.collection.set;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.collection.HashSet;
import org.junit.jupiter.api.Test;

public class HashSetTest {
  HashSet<Integer> set = HashSet.range(1, 6);

  @Test
  public void construct() {
    HashSet<Integer> set1 = set;
    HashSet<Integer> set2 = HashSet.rangeClosed(1, 5);
    HashSet<Integer> set3 = HashSet.rangeClosedBy(0, 5, 2);

    assertThat(set1).containsExactlyElementsIn(HashSet.of(1, 2, 3, 4, 5)).inOrder();
    assertThat(set2).containsExactlyElementsIn(HashSet.of(1, 2, 3, 4, 5)).inOrder();
    assertThat(set3).containsExactlyElementsIn(HashSet.of(0, 2, 4)).inOrder();
  }

  @Test
  public void size() {
    assertThat(set.size()).isEqualTo(5);
  }

  @Test
  public void add() {
    assertThat(set.add(6)).containsExactlyElementsIn(HashSet.of(1, 2, 3, 4, 5, 6)).inOrder();
  }

  @Test
  public void search() {
    assertThat(set.contains(5)).isEqualTo(true);
  }
}
