package collections.basic.function;

import java.util.Objects;

@FunctionalInterface
public interface IntFunction {

  int apply(int i);

  default IntFunction compose(IntFunction before) {
    Objects.requireNonNull(before);
    return i -> apply(before.apply(i));
  }

  default IntFunction andThen(IntFunction after) {
    Objects.requireNonNull(after);
    return i -> after.apply(apply(i));
  }

  static IntFunction identity() {
    return t -> t;
  }
}
