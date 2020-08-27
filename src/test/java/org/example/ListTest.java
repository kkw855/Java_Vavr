package org.example;

import static com.google.common.truth.Truth.assertThat;

import io.vavr.collection.List;
import io.vavr.collection.Vector;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ListTest {
  @Test
  public void construct() {
    List<String> list1 = List.empty();
    List<String> list2 = List.of("Java", "PHP", "Jquery");
    List<String> list3 = List.ofAll(Stream.of("Java", "PHP", "Jquery"));

    assertThat(list1).isEmpty();
    assertThat(list2).containsExactlyElementsIn(Vector.of("Java", "PHP", "Jquery")).inOrder();
    assertThat(list3).containsExactlyElementsIn(Vector.of("Java", "PHP", "Jquery")).inOrder();
  }

  @Test
  public void drop() {
    List<String> list = List.of("Java", "PHP", "Jquery", "JavaScript", "JShell", "JAVA");

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

  }
}
