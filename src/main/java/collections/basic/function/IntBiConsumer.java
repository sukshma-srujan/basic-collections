package collections.basic.function;

import java.util.Objects;

@FunctionalInterface
public interface IntBiConsumer {

  void accept(int a, int b);

  default IntBiConsumer andThen(IntBiConsumer after) {
    Objects.requireNonNull(after);
    return (a, b) -> {
      accept(a, b);
      after.accept(a, b);
    };
  }
}
