package collections.basic.function;

import java.util.Objects;

@FunctionalInterface
public interface IntPredicate {

  boolean test(int i);

  default IntPredicate and(IntPredicate other) {
    Objects.requireNonNull(other);
    return i -> test(i) && other.test(i);
  }

  default IntPredicate or(IntPredicate other) {
    Objects.requireNonNull(other);
    return i -> test(i) || other.test(i);
  }

  default IntPredicate negate() {
    return i -> !test(i);
  }

  static IntPredicate not(IntPredicate other) {
    Objects.requireNonNull(other);
    return i -> !other.test(i);
  }
}
