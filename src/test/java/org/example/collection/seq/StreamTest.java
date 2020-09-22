package org.example.collection.seq;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.jupiter.api.Test;

public class StreamTest {
  @Test
  public void construct() {
    Stream<Integer> stream1 = Stream.iterate(0, i -> i + 1).take(5);

    assertThat(stream1.toList()).containsExactlyElementsIn(List.of(0, 1, 2, 3, 4)).inOrder();
  }

  @Test
  public void filter() {
    Stream<Integer> stream = Stream.iterate(0, i -> i + 1).take(5);

    long evenSum = stream.filter(i -> i % 2 == 0).sum().longValue();

    assertThat(evenSum).isEqualTo(6);
  }

  @Test
  public void tabulate() {
    Stream<Integer> stream = Stream.tabulate(5, i -> i + i);

    assertThat(stream.toList()).containsExactlyElementsIn(List.of(0, 2, 4, 6, 8)).inOrder();
  }

  @Test
  public void zip() {
    Stream<Integer> s1 = Stream.of(1, 2, 3, 4);
    Stream<Integer> s2 = Stream.of(5, 6, 7);

    Stream<Tuple2<Integer, Integer>> s3 = s1.zip(s2);
    Tuple2<Integer, Integer> t1 = s3.get(2);

    assertThat(s3.size()).isEqualTo(3);
    assertThat(t1).isEqualTo(Tuple.of(3, 7));
  }
}
