package org.example.collection.seq;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.collection.CharSeq;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

public class CharSeqTest {
  CharSeq chars = CharSeq.of("vavr");

  @Test
  public void construct() {
    CharSeq chars1 = chars;
    CharSeq chars2 = CharSeq.range('A', 'D');

    assertThat(chars1.toList()).containsExactlyElementsIn(List.of('v', 'a', 'v', 'r')).inOrder();
    assertThat(chars2.toList()).containsExactlyElementsIn(List.of('A', 'B', 'C')).inOrder();
  }

  @Test
  public void size() {
    assertThat(chars.size()).isEqualTo(4);
  }

  @Test
  public void get() {
    assertThat(chars.get(1)).isEqualTo('a');
  }

  @Test
  public void to() {
    assertThat(chars.mkString()).isEqualTo("vavr");
  }
}
