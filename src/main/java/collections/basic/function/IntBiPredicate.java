package collections.basic.function;

import java.util.Objects;

@FunctionalInterface
public interface IntBiPredicate {

  boolean test(int a, int b);

  default IntBiPredicate and(IntBiPredicate after) {
    Objects.requireNonNull(after);
    return (a, b) -> test(a, b) && after.test(a, b);
  }

  default IntBiPredicate or(IntBiPredicate after) {
    Objects.requireNonNull(after);
    return (a, b) -> test(a, b) || after.test(a, b);
  }

  default IntBiPredicate negate() {
    return (a, b) -> !test(a, b);
  }

  static IntBiPredicate not(IntBiPredicate other) {
    Objects.requireNonNull(other);
    return (a, b) -> !other.test(a, b);
  }
}
