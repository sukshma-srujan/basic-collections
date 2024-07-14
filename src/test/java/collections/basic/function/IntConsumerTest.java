package collections.basic.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntConsumerTest {

  @Test
  void test_andThen_whenAfterIsNull_thenException() {
    IntConsumer consumer = System.out::println;
    assertThrows(NullPointerException.class, () -> consumer.andThen(null));
  }

  @Test
  void test_andThen_whenAfter_thenExecuteInCorrectOrder() {
    List<String> list = new ArrayList<>();
    IntConsumer c1 = c -> list.add("a");
    IntConsumer c2 = c -> list.add("b");
    IntConsumer twoConsumers = c1.andThen(c2);

    twoConsumers.accept(1);
    assertEquals(2, list.size());
    assertEquals("a", list.get(0));
    assertEquals("b", list.get(1));
  }
}
