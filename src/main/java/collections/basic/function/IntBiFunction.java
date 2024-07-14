package collections.basic.function;

import java.util.Objects;

@FunctionalInterface
public interface IntBiFunction {

  int apply(int a, int b);

  default IntBiFunction andThen(IntFunction after) {
    Objects.requireNonNull(after);
    return (a, b) -> after.apply(apply(a, b));
  }
}
