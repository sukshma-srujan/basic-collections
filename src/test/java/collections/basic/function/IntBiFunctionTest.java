package collections.basic.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IntBiFunctionTest {

  @Test
  void test_andThen_whenAfterIsNull_thenException() {
    IntBiFunction fn = Integer::sum;
    assertThrows(NullPointerException.class, () -> fn.andThen(null));
  }

  @Test
  void test_andThen() {
    IntBiFunction sum = Integer::sum;
    IntFunction square = (a) -> a * a;
    IntBiFunction sumThenSquare = sum.andThen(square);

    assertEquals(25, sumThenSquare.apply(-1, -4));
    assertEquals(121, sumThenSquare.apply(0, 11));
    assertEquals(0, sumThenSquare.apply(0, 0));
  }
}
