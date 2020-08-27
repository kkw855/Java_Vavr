package org.example;

import static com.google.common.truth.Truth.assertThat;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import io.vavr.Function5;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;

public class IntroductionTest {
  @Test
  public void option() {
    Option<String> none = Option.of(null);
    Option<String> some = Option.of("val");

    assertThat(none).isEqualTo(Option.none());
    assertThat(some).isEqualTo(Option.some("val"));

    assertThat(none.getOrElse("default")).isEqualTo("default");
    assertThat(some.getOrElse("default")).isEqualTo("val");
  }

  @Test
  public void tuple() {
    Tuple3<String, Integer, Double> java8 = Tuple.of("Java", 8, 1.8);

    assertThat(java8._1).isEqualTo("Java");
    assertThat(java8._2).isEqualTo(8);
    assertThat(java8._3).isEqualTo(1.8);
  }

  @Test
  public void $try() {
    // Lazy 아님. Try.of 함수로 전달하는 computation 즉시 평가
    @SuppressWarnings({"NumericOverflow", "divzero"})
    Try<Integer> result = Try.of(() -> 1 / 0);

    assertThat(result.getCause()).isInstanceOf(ArithmeticException.class);

    assertThat(result.getOrElse(1)).isEqualTo(1);
  }

  @Test
  public void functional_interfaces() {
    Function5<String, String, String, String, String, String> concat =
        (a, b, c, d, e) -> a + b + c + d + e;

    assertThat(concat.apply("Hello ", "world", "! ", "Learn ", "Vavr"))
        .isEqualTo("Hello world! Learn Vavr");
  }

  @Test
  public void collections() {
    List<Integer> intList = List.of(1, 2, 3);
    List<Integer> addList = intList.push(0);

    assertThat(intList).containsExactlyElementsIn(List.of(1, 2, 3)).inOrder();
    assertThat(addList).containsExactlyElementsIn(List.of(0, 1, 2, 3)).inOrder();
  }

  @Test
  public void validation() {
    class PersonValidator {
      final String NAME_ERR = "Invalid characters in name: ";
      final String AGE_ERR = "Age must be at least 0";

      public Validation<Seq<String>, Person> validatePerson(String name, int age) {
        return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
      }

      public Validation<String, String> validateName(String name) {
        String invalidChars = name.replaceAll("[a-zA-Z ]", "");
        return invalidChars.isEmpty()
            ? Validation.valid(name)
            : Validation.invalid(NAME_ERR + invalidChars);
      }

      public Validation<String, Integer> validateAge(int age) {
        return age < 0 ? Validation.invalid(AGE_ERR) : Validation.valid(age);
      }
    }

    PersonValidator personValidator = new PersonValidator();

    Validation<Seq<String>, Person> valid = personValidator.validatePerson("John Doe", 30);
    Validation<Seq<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -1);

    assertThat(valid.getOrElse(new Person("Default", 0))).isEqualTo(new Person("John Doe", 30));
    assertThat(invalid.getError())
        .containsExactlyElementsIn(
            List.of("Invalid characters in name: ?!4", "Age must be at least 0"))
        .inOrder();
  }

  @Test
  public void lazy() {
    Lazy<Double> lazy = Lazy.of(Math::random);
    assertThat(lazy.isEvaluated()).isEqualTo(false);

    double val1 = lazy.get();
    assertThat(lazy.isEvaluated()).isEqualTo(true);

    double val2 = lazy.get();
    assertThat(val1).isEqualTo(val2);
  }

  @Test
  public void patternMatching() {
    int input = 2;

    String output =
        Match(input).of(Case($(1), "one"), Case($(2), "two"), Case($(3), "three"), Case($(), "?"));

    assertThat(output).isEqualTo("two");
  }
}
