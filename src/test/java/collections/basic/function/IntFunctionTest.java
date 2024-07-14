package collections.basic.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IntFunctionTest {

  @Test
  void test_compose_whenBeforeIsNull_thenException() {
    IntFunction f = r -> r;
    assertThrows(NullPointerException.class, () -> f.compose(null));
  }

  @Test
  void test_compose_whenBefore_thenApply() {
    IntFunction multiplyBy2 = r -> 2 * r;
    IntFunction reduceBy2 = r -> r - 2;
    IntFunction composition = multiplyBy2.compose(reduceBy2);

    assertEquals(18, composition.apply(11));
  }

  @Test
  void test_andThen_whenAfterIsNull_thenException() {
    IntFunction f = r -> r;
    assertThrows(NullPointerException.class, () -> f.andThen(null));
  }

  @Test
  void test_andThen_whenAfter_thenApply() {
    IntFunction multiplyBy2 = r -> 2 * r;
    IntFunction reduceBy2 = r -> r - 2;
    IntFunction composition = reduceBy2.andThen(multiplyBy2);

    assertEquals(-20, composition.apply(-8));
  }

  @Test
  void test_identity() {
    IntFunction identity = IntFunction.identity();
    assertEquals(0, identity.apply(0));
    assertEquals(-9, identity.apply(-9));
    assertEquals(12, identity.apply(12));
  }
}
