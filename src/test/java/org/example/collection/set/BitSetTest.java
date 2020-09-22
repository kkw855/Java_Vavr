package org.example.collection.set;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.collection.BitSet;
import org.junit.jupiter.api.Test;

public class BitSetTest {
  BitSet<Integer> bitSet = BitSet.of(1, 2, 3, 4, 5);

  @Test
  public void construct() {
    BitSet<Integer> bitSet1 = bitSet;

    assertThat(bitSet1).containsExactlyElementsIn(BitSet.of(1, 2, 3, 4, 5)).inOrder();
  }

  @Test
  public void take() {
    assertThat(bitSet.takeUntil(i -> i > 3)).containsExactlyElementsIn(BitSet.of(1, 2, 3)).inOrder();
  }
}
