package collections.basic.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntPredicateTest {

  @Test
  void test_and_whenOtherIsNull_thenException() {
    IntPredicate p = i -> true;
    assertThrows(NullPointerException.class, () -> p.and(null));
  }

  @Test
  void test_and_whenOther_thenBothPredicateExecuteInCorrectOrder() {
    List<String> list = new ArrayList<>(2);

    IntPredicate numberIsEven =
        i -> {
          list.add("a");
          return (i & 1) == 0;
        };

    IntPredicate numberIsTen =
        i -> {
          list.add("b");
          return i == 10;
        };

    IntPredicate composition = numberIsEven.and(numberIsTen);

    assertTrue(composition.test(10));
    assertEquals(2, list.size());
    assertEquals("a", list.get(0));
    assertEquals("b", list.get(1));
  }

  @Test
  void test_and_whenOther_thenOperationIsDefinitelyAND() {
    IntPredicate numberIsEven = i -> (i & 1) == 0;
    IntPredicate numberIsNine = i -> i == 9;
    IntPredicate numberIsTen = i -> i == 10;
    IntPredicate numberIsEvenAndIsTen = numberIsEven.and(numberIsTen);

    assertTrue(numberIsEvenAndIsTen.test(10)); // T, T
    assertFalse(numberIsEvenAndIsTen.test(12)); // T, F
    assertFalse(numberIsEven.and(numberIsNine).test(9)); // F, T
    assertFalse(numberIsEvenAndIsTen.test(13)); // F, F
  }

  @Test
  void test_or_whenOtherIsNull_thenException() {
    IntPredicate p = i -> true;
    assertThrows(NullPointerException.class, () -> p.or(null));
  }

  @Test
  void test_or_whenOther_thenBothPredicateExecuteInCorrectOrder() {
    List<String> list = new ArrayList<>(2);

    IntPredicate numberIsNine =
        i -> {
          list.add("a");
          return i == 9;
        };

    IntPredicate numberIsOdd =
        i -> {
          list.add("b");
          return (i & 1) == 1;
        };

    IntPredicate composition = numberIsNine.or(numberIsOdd);

    assertTrue(composition.test(11));
    assertEquals(2, list.size());
    assertEquals("a", list.get(0));
    assertEquals("b", list.get(1));
  }

  @Test
  void test_or_whenOther_thenOperationIsDefinitelyOR() {
    IntPredicate numberIsEven = i -> (i & 1) == 0;
    IntPredicate numberIsNine = i -> i == 9;
    IntPredicate numberIsTen = i -> i == 10;
    IntPredicate numberIsEvenOrIsTen = numberIsEven.or(numberIsTen);

    assertTrue(numberIsEvenOrIsTen.test(10)); // T, T
    assertTrue(numberIsEvenOrIsTen.test(12)); // T, F
    assertTrue(numberIsEven.or(numberIsNine).test(9)); // F, T
    assertFalse(numberIsEvenOrIsTen.test(9)); // F, F
  }

  @Test
  void test_negate() {
    IntPredicate numberIsTen = i -> i == 10;
    IntPredicate negationOfNumberIsTen = numberIsTen.negate();

    assertFalse(negationOfNumberIsTen.test(10));
    assertTrue(negationOfNumberIsTen.test(11));
    assertTrue(negationOfNumberIsTen.test(9));
    assertTrue(negationOfNumberIsTen.test(-10));
  }

  @Test
  void test_not_whenOtherIsNull_thenException() {
    assertThrows(NullPointerException.class, () -> IntPredicate.not(null));
  }

  @Test
  void test_not_whenOther_thenVerifyNOT() {
    IntPredicate numberIsTen = i -> i == 10;
    IntPredicate numberIsNotTen = IntPredicate.not(numberIsTen);

    assertFalse(numberIsNotTen.test(10));
    assertTrue(numberIsNotTen.test(11));
    assertTrue(numberIsNotTen.test(9));
    assertTrue(numberIsNotTen.test(-10));
  }
}
