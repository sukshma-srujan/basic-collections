package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntHashSet;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class IntHashSetInstantiationTest {
  @Test
  void noArg_instantiation_createsEmptySet() {
    IntHashSet intHashSet = new IntHashSet();

    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void initialCapacityNegative_instantiation_fails() {
    assertThatThrownBy(() -> new IntHashSet(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void initialCapacityZero_instantiation_fails() {
    assertThatThrownBy(() -> new IntHashSet(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void initialCapacityOne_instantiation_succeeds() {
    IntHashSet intHashSet = new IntHashSet(1);

    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void initialCapacityOne_instantiation_createdSetIsUsable() {
    IntHashSet intHashSet = new IntHashSet(1);
    intHashSet.add(87);

    assertThat(intHashSet.contains(87)).isTrue();
    assertThat(intHashSet.size()).isOne();
  }

  @Test
  void initialCapacityOne_instantiation_createdSetCanAccommodateAnyNumberOfElements() {
    IntHashSet intHashSet = new IntHashSet(1);
    for (int e = -4; e < 4; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(8);
    for (int e = -4; e < 4; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void initialCapacityMoreThanOne_instantiation_succeeds() {
    IntHashSet intHashSet = new IntHashSet(33);

    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void initialCapacityMoreThanOne_instantiation_createdSetIsUsable() {
    IntHashSet intHashSet = new IntHashSet(41);
    intHashSet.add(34);

    assertThat(intHashSet.size()).isOne();
    assertThat(intHashSet.contains(34)).isTrue();
  }

  @Test
  void initialCapacityMoreThanOne_instantiation_createdSetCanAccommodateAnyNumberOfElements() {
    IntHashSet intHashSet = new IntHashSet(41);
    for (int e = -40; e < 40; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(80);
    for (int e = -40; e < 40; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void emptyIntHashSet_instantiation_createsEmptySet() {
    IntHashSet source = new IntHashSet();

    IntHashSet intHashSet = new IntHashSet(source);

    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void emptyIntHashSet_instantiation_createdSetCanAccommodateMoreElements() {
    IntHashSet source = new IntHashSet();

    IntHashSet intHashSet = new IntHashSet(source);
    intHashSet.add(2);
    intHashSet.add(3);

    assertThat(intHashSet.size()).isEqualTo(2);
    assertThat(intHashSet.contains(2)).isTrue();
    assertThat(intHashSet.contains(3)).isTrue();
  }

  @Test
  void singletonIntHashSet_instantiation_createsSingletonSet() {
    IntHashSet source = new IntHashSet();
    source.add(167);

    IntHashSet intHashSet = new IntHashSet(source);

    assertThat(intHashSet.size()).isOne();
    assertThat(intHashSet.contains(167)).isTrue();
  }

  @Test
  void singletonIntHashSet_instantiation_createdSetCanAccommodateMoreElements() {
    IntHashSet source = new IntHashSet();
    source.add(167);

    IntHashSet intHashSet = new IntHashSet(source);
    intHashSet.add(2);
    intHashSet.add(3);

    assertThat(intHashSet.size()).isEqualTo(3);
    assertThat(intHashSet.contains(167)).isTrue();
    assertThat(intHashSet.contains(2)).isTrue();
    assertThat(intHashSet.contains(3)).isTrue();
  }

  @Test
  void nElementIntHashSet_instantiation_createsNElementSet() {
    IntHashSet source = new IntHashSet();
    int[] expectedElements = new int[150];
    int idx = 0;
    for (int e = -75; e < 75; e++) {
      source.add(e);
      expectedElements[idx++] = e;
    }

    IntHashSet intHashSet = new IntHashSet(source);

    assertThat(intHashSet.size()).isEqualTo(150);

    for (int e : expectedElements) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void nElementIntHashSet_instantiation_createdSetCanAccommodateMoreElements() {
    IntHashSet source = new IntHashSet();
    int[] expectedElements = new int[150];
    int idx = 0;
    for (int e = -75; e < 75; e++) {
      source.add(e);
      expectedElements[idx++] = e;
    }

    IntHashSet intHashSet = new IntHashSet(source);

    assertThat(intHashSet.size()).isEqualTo(150);

    for (int e : expectedElements) {
      assertThat(intHashSet.contains(e)).isTrue();
    }

    intHashSet.add(1002);
    intHashSet.add(1003);
    assertThat(intHashSet.contains(1002)).isTrue();
    assertThat(intHashSet.contains(1003)).isTrue();
    assertThat(intHashSet.size()).isEqualTo(152);
  }

  @Test
  void emptyIterable_from_createsEmptySet() {
    HashSet<Integer> source = new HashSet<>();
    IntHashSet intHashSet = IntHashSet.from(source);
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void emptyIterable_from_createdSetIsUsable() {
    HashSet<Integer> source = new HashSet<>();

    IntHashSet intHashSet = IntHashSet.from(source);
    assertThat(intHashSet.size()).isZero();

    for (int e = -2; e < 15; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(17);
    for (int e = -2; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void singletonIterable_from_createsSingletonSet() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(-46);

    IntHashSet intHashSet = IntHashSet.from(source);

    assertThat(intHashSet.size()).isOne();
    assertThat(intHashSet.contains(-46)).isTrue();
  }

  @Test
  void singletonIterable_from_createsSetIsUsable() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(-46);

    IntHashSet intHashSet = IntHashSet.from(source);
    for (int i = -2; i < 4; i++) {
      intHashSet.add(i);
    }

    assertThat(intHashSet.size()).isEqualTo(7);
    assertThat(intHashSet.contains(-46)).isTrue();
    for (int i = -2; i < 4; i++) {
      assertThat(intHashSet.contains(i)).isTrue();
    }
  }

  @Test
  void nSizeIterableContainingDuplicateElements_from_createsSetWithDistinctElements() {
    ArrayList<Integer> source = new ArrayList<>();
    source.add(-46);
    source.add(53);
    source.add(76);
    source.add(53);

    IntHashSet intHashSet = IntHashSet.from(source);

    assertThat(intHashSet.size()).isEqualTo(3);
    assertThat(intHashSet.contains(-46)).isTrue();
    assertThat(intHashSet.contains(53)).isTrue();
    assertThat(intHashSet.contains(76)).isTrue();
  }

  @Test
  void nSizeIterable_from_createsNSizeSet() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 15; e++) {
      source.add(e);
    }

    IntHashSet intHashSet = IntHashSet.from(source);

    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void nSizeIterable_from_createdSetIsUsable() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 2; e++) {
      source.add(e);
    }

    IntHashSet intHashSet = IntHashSet.from(source);
    for (int e = 2; e < 15; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void nSizeIterableWithNullElement_from_fails() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 15; e++) {
      source.add(e);
    }
    source.add(null);

    assertThatThrownBy(() -> IntHashSet.from(source)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void emptyIterableWithCoerceNullToZero_from_createsEmptySet() {
    HashSet<Integer> source = new HashSet<>();
    IntHashSet intHashSet = IntHashSet.from(source, true);
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void emptyIterableWithCoerceNullToZero_from_createdEmptySetIsUsable() {
    HashSet<Integer> source = new HashSet<>();

    IntHashSet intHashSet = IntHashSet.from(source, true);
    for (int e = -15; e < 13; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(28);
    for (int e = -15; e < 13; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void singletonIterableWithCoerceNullToZero_from_createsSingletonSet() {
    HashSet<Integer> source = new HashSet<>();
    source.add(98);

    IntHashSet intHashSet = IntHashSet.from(source, true);

    assertThat(intHashSet.size()).isEqualTo(1);
    assertThat(intHashSet.contains(98)).isTrue();
  }

  @Test
  void singletonIterableWithCoerceNullToZero_from_createsSingletonSetIsUsable() {
    HashSet<Integer> source = new HashSet<>();
    source.add(98);

    IntHashSet intHashSet = IntHashSet.from(source, true);
    for (int i = -9; i < 9; i++) {
      intHashSet.add(i);
    }

    assertThat(intHashSet.size()).isEqualTo(19);
    for (int i = -9; i < 9; i++) {
      assertThat(intHashSet.contains(i)).isTrue();
    }
    assertThat(intHashSet.contains(98)).isTrue();
  }

  @Test
  void
      singletonIterableContainingNullWithCoerceNullToZero_from_createsSingletonSetContainingZero() {
    HashSet<Integer> source = new HashSet<>();
    source.add(null);

    IntHashSet intHashSet = IntHashSet.from(source, true);

    assertThat(intHashSet.size()).isEqualTo(1);
    assertThat(intHashSet.contains(0)).isTrue();
  }

  @Test
  void singletonIterableContainingNullWithCoerceNullToZero_from_createsSingletonSetIsUsable() {
    HashSet<Integer> source = new HashSet<>();
    source.add(null);

    IntHashSet intHashSet = IntHashSet.from(source, true);
    for (int i = 1; i < 11; i++) {
      intHashSet.add(i);
    }

    assertThat(intHashSet.size()).isEqualTo(11);
    for (int i = 1; i < 11; i++) {
      assertThat(intHashSet.contains(i)).isTrue();
    }
    assertThat(intHashSet.contains(0)).isTrue();
  }

  @Test
  void nSizeIterableContainingNullWithCoercingNullToZero_from_createsNSizeSetContainingZero() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 0; e++) {
      source.add(e);
    }
    source.add(null);

    IntHashSet intHashSet = IntHashSet.from(source, true);
    assertThat(intHashSet.size()).isEqualTo(15 + 1);
    for (int e = -15; e < 0; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
    assertThat(intHashSet.contains(0)).isTrue();
  }

  @Test
  void nSizeIterableContainingNullWithCoercingNullToZero_from_createdNSizeSetIsUsable() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 0; e++) {
      source.add(e);
    }
    source.add(null);

    IntHashSet intHashSet = IntHashSet.from(source, true);
    for (int e = 1; e < 15; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }
}
