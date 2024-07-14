package collections.basic.function;

import java.util.Objects;

@FunctionalInterface
public interface IntConsumer {

  void accept(int i);

  default IntConsumer andThen(IntConsumer after) {
    Objects.requireNonNull(after);
    return (i) -> {
      accept(i);
      after.accept(i);
    };
  }
}
