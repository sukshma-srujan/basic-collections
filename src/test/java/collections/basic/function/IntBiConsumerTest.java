package collections.basic.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntBiConsumerTest {

  @Test
  void test_andThen_whenAfterIsNull_thenException() {
    IntBiConsumer first = (a, b) -> System.out.println(a + ":" + b);
    assertThrows(NullPointerException.class, () -> first.andThen(null));
  }

  @Test
  void test_andThen_bothConsumersExecuteAndInCorrectOrder() {
    List<String> l = new ArrayList<>(2);
    IntBiConsumer first = (a, b) -> l.add("a");
    IntBiConsumer second = (a, b) -> l.add("b");
    IntBiConsumer firstThenSecond = first.andThen(second);

    firstThenSecond.accept(1, 2);

    assertEquals(2, l.size());
    assertEquals("a", l.get(0));
    assertEquals("b", l.get(1));
  }
}
