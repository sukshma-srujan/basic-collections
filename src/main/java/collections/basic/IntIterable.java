package collections.basic;

import collections.basic.function.IntConsumer;
import java.util.Objects;

public interface IntIterable {
  IntIterator iterator();

  default void forEach(IntConsumer action) {
    Objects.requireNonNull(action);

    for (IntIterator ii = iterator(); ii.hasNext(); ) {
      action.accept(ii.next());
    }
  }
}
