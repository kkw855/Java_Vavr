package org.example;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import org.junit.jupiter.api.Test;

public class QueueTest {
  Queue<Integer> queue = Queue.of(1, 2, 3, 4);

  @Test
  public void construct() {
    Queue<Integer> queue1 = Queue.of(1, 2);

    assertThat(queue1).containsExactlyElementsIn(Queue.of(1, 2)).inOrder();
  }

  @Test
  public void enqueue() {
    assertThat(queue.enqueue(5, 6)).containsExactlyElementsIn(Queue.of(1, 2, 3, 4, 5, 6)).inOrder();
    assertThat(queue.enqueueAll(List.of(5, 6)))
        .containsExactlyElementsIn(Queue.of(1, 2, 3, 4, 5, 6))
        .inOrder();
  }

  @Test
  public void dequeue() {
    Tuple2<Integer, Queue<Integer>> dequeue = queue.dequeue();

    assertThat(dequeue).isEqualTo(Tuple.of(1, Queue.of(2, 3, 4)));
  }

  @Test
  public void combination() {
    //  [1,2,3].combinations() = [
    //    [],                  // k = 0
    //    [1], [2], [3],       // k = 1
    //    [1,2], [1,3], [2,3], // k = 2
    //    [1,2,3]              // k = 3
    //  ]
    assertThat(queue.combinations(2))
        .containsExactlyElementsIn(
            Queue.of(
                Queue.of(1, 2),
                Queue.of(1, 3),
                Queue.of(1, 4),
                Queue.of(2, 3),
                Queue.of(2, 4),
                Queue.of(3, 4)))
        .inOrder();
  }
}
