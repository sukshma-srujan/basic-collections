package collections.basic.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntBiPredicateTest {

  @Test
  void test_and_whenAfterIsNull_thenException() {
    IntBiPredicate sumIsTen = (a, b) -> (a + b) == 10;
    assertThrows(NullPointerException.class, () -> sumIsTen.and(null));
  }

  @Test
  void test_and_predicatesExecuteInCorrectOrder() {
    List<String> l = new ArrayList<>(2);
    IntBiPredicate first = (a, b) -> l.add("a");
    IntBiPredicate second = (a, b) -> l.add("b");
    IntBiPredicate firstAndSecond = first.and(second);

    firstAndSecond.test(1, 2);
    assertEquals(2, l.size());
    assertEquals("a", l.get(0));
    assertEquals("b", l.get(1));
  }

  @Test
  void test_and() {
    IntBiPredicate sumIsTen = (a, b) -> (a + b) == 10;
    IntBiPredicate diffIsTwo = (a, b) -> (a - b) == 2;
    IntBiPredicate sumIsTenAndDiffIsTwo = sumIsTen.and(diffIsTwo);

    assertTrue(sumIsTenAndDiffIsTwo.test(6, 4)); // T, T
    assertFalse(sumIsTenAndDiffIsTwo.test(4, 6)); // T, F
    assertFalse(sumIsTenAndDiffIsTwo.test(2, 4)); // F, T
    assertFalse(sumIsTenAndDiffIsTwo.test(3, 6)); // F, F
  }

  @Test
  void test_or_whenAfterIsNull_thenException() {
    IntBiPredicate sumIsTen = (a, b) -> (a + b) == 10;
    assertThrows(NullPointerException.class, () -> sumIsTen.or(null));
  }

  @Test
  void test_or_predicatesExecuteInCorrectOrder() {
    IntBiPredicate sumIsTen = (a, b) -> (a + b) == 10;
    IntBiPredicate diffIsTwo = (a, b) -> (a - b) == 2;
    IntBiPredicate sumIsTenOrDiffIsTwo = sumIsTen.or(diffIsTwo);

    assertTrue(sumIsTenOrDiffIsTwo.test(6, 4)); // T, T
    assertTrue(sumIsTenOrDiffIsTwo.test(4, 6)); // T, F
    assertTrue(sumIsTenOrDiffIsTwo.test(4, 2)); // F, T
    assertFalse(sumIsTenOrDiffIsTwo.test(4, 5)); // F, F
  }

  @Test
  void test_negate() {
    IntBiPredicate sumIsTen = (a, b) -> (a + b) == 10;
    IntBiPredicate sumIsNotTen = sumIsTen.negate();
    assertTrue(sumIsNotTen.test(4, 5));
    assertFalse(sumIsNotTen.test(5, 5));
  }

  @Test
  void test_not_whenOtherIsNull_thenException() {
    assertThrows(NullPointerException.class, () -> IntBiPredicate.not(null));
  }

  @Test
  void test_not() {
    IntBiPredicate sumIsTen = (a, b) -> (a + b) == 10;
    IntBiPredicate sumIsNotTen = IntBiPredicate.not(sumIsTen);
    assertTrue(sumIsNotTen.test(4, 5));
    assertFalse(sumIsNotTen.test(5, 5));
  }
}
