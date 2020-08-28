package org.example;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.collection.Iterator;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Vector;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ListTest {
  List<String> list = List.of("Java", "PHP", "Jquery", "JavaScript", "JShell", "JAVA");

  @Test
  public void construct() {
    List<String> list1 = List.empty();
    List<String> list2 = List.of("Java", "PHP", "Jquery");
    List<String> list3 = List.ofAll(Stream.of("Java", "PHP", "Jquery"));
    List<Integer> list4 = List.rangeClosed(5, 10);

    assertThat(list1).isEmpty();
    assertThat(list2).containsExactlyElementsIn(Vector.of("Java", "PHP", "Jquery")).inOrder();
    assertThat(list3).containsExactlyElementsIn(Vector.of("Java", "PHP", "Jquery")).inOrder();
    assertThat(list4).containsExactlyElementsIn(List.of(5, 6, 7, 8, 9, 10)).inOrder();
  }

  @Test
  public void drop() {
    assertThat(list.drop(2))
        .containsExactlyElementsIn(List.of("Jquery", "JavaScript", "JShell", "JAVA"))
        .inOrder();

    assertThat(list.dropRight(2))
        .containsExactlyElementsIn(List.of("Java", "PHP", "Jquery", "JavaScript"))
        .inOrder();

    assertThat(list.dropUntil(s -> s.contains("Shell")))
        .containsExactlyElementsIn(List.of("JShell", "JAVA"))
        .inOrder();

    assertThat(list.dropWhile(s -> s.length() < 7))
        .containsExactlyElementsIn(List.of("JavaScript", "JShell", "JAVA"))
        .inOrder();
  }

  @Test
  public void take() {
    assertThat(list.take(2)).containsExactlyElementsIn(List.of("Java", "PHP")).inOrder();

    assertThat(list.takeRight(2)).containsExactlyElementsIn(List.of("JShell", "JAVA")).inOrder();

    assertThat(list.takeUntil(s -> s.contains("Script")))
        .containsExactlyElementsIn(List.of("Java", "PHP", "Jquery"))
        .inOrder();

    assertThat(list.takeWhile(s -> s.length() < 7))
        .containsExactlyElementsIn(List.of("Java", "PHP", "Jquery"))
        .inOrder();
  }

  @Test
  public void distinct() {
    assertThat(list.distinctBy((s1, s2) -> s1.startsWith(s2.charAt(0) + "") ? 0 : 1))
        .containsExactlyElementsIn(List.of("Java", "PHP"))
        .inOrder();
  }

  @Test
  public void intersperse() {
    assertThat(List.of("Boys", "Girls").intersperse("and"))
        .containsExactlyElementsIn(List.of("Boys", "and", "Girls"))
        .inOrder();
  }

  @Test
  public void group() {
    Iterator<List<String>> iterator = list.grouped(2);
    assertThat(iterator)
        .containsExactlyElementsIn(
            Iterator.of(
                List.of("Java", "PHP"), List.of("Jquery", "JavaScript"), List.of("JShell", "JAVA")))
        .inOrder();

    Map<Boolean, List<String>> map = list.groupBy(s -> s.startsWith("J"));
    assertThat(map.get(false).get()).containsExactlyElementsIn(List.of("PHP")).inOrder();
    assertThat(map.get(true).get())
        .containsExactlyElementsIn(List.of("Java", "Jquery", "JavaScript", "JShell", "JAVA"))
        .inOrder();

    assertThat(list.peek()).isEqualTo("Java");
    assertThat(list.peek()).isEqualTo("Java");
    assertThat(list.pop())
        .containsExactlyElementsIn(List.of("PHP", "Jquery", "JavaScript", "JShell", "JAVA"))
        .inOrder();
    assertThat(list.pop())
        .containsExactlyElementsIn(List.of("PHP", "Jquery", "JavaScript", "JShell", "JAVA"))
        .inOrder();
  }

  @Test
  public void stack() {
    // last-in-first-out (LIFO)
    List<Integer> intList = List.empty();

    List<Integer> intList1 = intList.pushAll(List.rangeClosed(5, 10));

    assertThat(intList1).containsExactlyElementsIn(List.of(10, 9, 8, 7, 6, 5)).inOrder();

    assertThat(intList1.peek()).isEqualTo(10);
    assertThat(intList1.pop()).containsExactlyElementsIn(List.of(9, 8, 7, 6, 5)).inOrder();
  }
}
