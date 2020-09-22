package org.example;

import static com.google.common.truth.Truth.assertThat;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;
import static io.vavr.Predicates.*;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

public class TryTest {
  @SuppressWarnings({"NumericOverflow", "divzero"})
  @Test
  public void failure() {
    Try<Integer> t = Try.of(() -> 1 / 0);

    Integer res = t.map(i -> i * 10).getOrElse(50);

    assertThat(t.isFailure()).isEqualTo(true);
    assertThat(t.isSuccess()).isEqualTo(false);
    assertThat(t.toOption()).isEqualTo(Option.none());
    assertThat(res).isEqualTo(50);
  }

  @Test
  public void success() {
    Try<Integer> t = Try.of(() -> 4 / 2);

    Integer res = t.map(i -> i * 10).getOrElse(50);

    assertThat(t.isFailure()).isEqualTo(false);
    assertThat(t.isSuccess()).isEqualTo(true);
    assertThat(t.toOption()).isEqualTo(Option.some(2));
    assertThat(res).isEqualTo(20);
  }

  @SuppressWarnings({"NumericOverflow", "divzero"})
  @Test
  public void patternMatchingWhenFailed() {
    Try<Integer> failure = Try.of(() -> 1 / 0);

    Try<Integer> recovered =
        failure.recover(r -> Match(r).of(Case($(instanceOf(ArithmeticException.class)), 100)));

    assertThat(recovered.isFailure()).isEqualTo(false);
    assertThat(recovered.get()).isEqualTo(100);

    //    Integer recovered = Match(failure).of(
    //        Case($Success($()), v -> v * 10),
    //        Case($Failure($()), e -> 0)
    //    );
    //    assertThat(recovered).isEqualTo(0);
  }
}
