package org.example.collection.set;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.collection.List;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class SortedSetTest {
  SortedSet<String> set = TreeSet.of("Red", "Green", "Blue");

  @Test
  public void construct() {
    SortedSet<String> empty = TreeSet.empty();
    SortedSet<String> set1 = set;
    SortedSet<String> set2 = TreeSet.of(Comparator.reverseOrder(), "Red", "Green", "Blue");

    assertThat(empty.toList()).containsExactlyElementsIn(List.empty()).inOrder();
    assertThat(set1.toList()).containsExactlyElementsIn(List.of("Blue", "Green", "Red")).inOrder();
    assertThat(set2.toList()).containsExactlyElementsIn(List.of("Red", "Green", "Blue")).inOrder();
  }

  @Test
  public void size() {
    assertThat(set.size()).isEqualTo(3);
  }

  @Test
  public void get() {
    assertThat(set.head()).isEqualTo("Blue");
    assertThat(set.last()).isEqualTo("Red");
  }
}
