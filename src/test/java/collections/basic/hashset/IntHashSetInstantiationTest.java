package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntHashSet;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class IntHashSetInstantiationTest {
  @Test
  void instantiation_noArg_succeeds() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void instantiation_initialCapacityNegative_fails() {
    assertThatThrownBy(() -> new IntHashSet(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void instantiation_initialCapacityZero_fails() {
    assertThatThrownBy(() -> new IntHashSet(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void instantiation_initialCapacityOne_succeeds() {
    IntHashSet intHashSet = new IntHashSet(1);
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void instantiation_initialCapacityOne_createdSetIsUsable() {
    IntHashSet intHashSet = new IntHashSet(1);
    intHashSet.add(87);

    assertThat(intHashSet.contains(87)).isTrue();
    assertThat(intHashSet.size()).isOne();
  }

  @Test
  void instantiation_initialCapacityOne_createdSetCanAccommodateAnyNumberOfElements() {
    IntHashSet intHashSet = new IntHashSet(1);
    for (int e = -4; e < 4; e++) {
      intHashSet.add(e);
    }

    for (int e = -4; e < 4; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
    assertThat(intHashSet.size()).isEqualTo(8);
  }

  @Test
  void instantiation_initialCapacityMoreThanOne_succeeds() {
    IntHashSet intHashSet = new IntHashSet(33);
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void instantiation_initialCapacityMoreThanOne_createdSetIsUsable() {
    IntHashSet intHashSet = new IntHashSet(41);
    intHashSet.add(34);

    assertThat(intHashSet.contains(34)).isTrue();
    assertThat(intHashSet.size()).isOne();
  }

  @Test
  void instantiation_initialCapacityMoreThanOne_createdSetCanAccommodateAnyNumberOfElements() {
    IntHashSet intHashSet = new IntHashSet(41);
    for (int e = -40; e < 40; e++) {
      intHashSet.add(e);
    }

    for (int e = -40; e < 40; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
    assertThat(intHashSet.size()).isEqualTo(80);
  }

  @Test
  void instantiation_usingEmptyIntHashSet_createsEmptySet() {
    IntHashSet source = new IntHashSet();
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void instantiation_usingEmptyIntHashSet_createdSetCanAccommodateMoreElements() {
    IntHashSet source = new IntHashSet();
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isZero();

    intHashSet.add(2);
    intHashSet.add(3);
    assertThat(intHashSet.contains(2)).isTrue();
    assertThat(intHashSet.contains(3)).isTrue();
    assertThat(intHashSet.size()).isEqualTo(2);
  }

  @Test
  void instantiation_usingSingletonIntHashSet_createsSingletonSet() {
    IntHashSet source = new IntHashSet();
    source.add(167);
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isOne();
    assertThat(intHashSet.contains(167)).isTrue();
  }

  @Test
  void instantiation_usingSingletonIntHashSet_createdSetCanAccommodateMoreElements() {
    IntHashSet source = new IntHashSet();
    source.add(167);
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isOne();
    assertThat(intHashSet.contains(167)).isTrue();

    intHashSet.add(2);
    intHashSet.add(3);
    assertThat(intHashSet.contains(2)).isTrue();
    assertThat(intHashSet.contains(3)).isTrue();
    assertThat(intHashSet.size()).isEqualTo(3);
  }

  @Test
  void instantiation_usingNElementIntHashSet_createsNElementSet() {
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
  void instantiation_usingNElementIntHashSet_createdSetCanAccommodateMoreElements() {
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
  void instantiation_usingEmptySet_succeeds() {
    HashSet<Integer> source = new HashSet<>();
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void instantiation_usingEmptySet_createdSetIsUsable() {
    HashSet<Integer> source = new HashSet<>();
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isZero();

    for (int e = -15; e < 15; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void instantiation_usingNonEmptySet_succeeds() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 15; e++) {
      source.add(e);
    }
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void instantiation_usingNonEmptySetContainingNull_fails() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 15; e++) {
      source.add(e);
    }
    source.add(null);

    assertThatThrownBy(() -> new IntHashSet(source)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void instantiation_usingNonEmptySet_setIsUsable() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 0; e++) {
      source.add(e);
    }
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isEqualTo(15);

    for (int e = 0; e < 15; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void instantiation_usingEmptySetWithCoercingNullToZero_succeeds() {
    HashSet<Integer> source = new HashSet<>();
    IntHashSet intHashSet = new IntHashSet(source, true);
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void instantiation_usingEmptySetWithCoercingNullToZero_createdSetIsUsable() {
    HashSet<Integer> source = new HashSet<>();
    IntHashSet intHashSet = new IntHashSet(source, true);
    assertThat(intHashSet.size()).isZero();

    for (int e = -15; e < 15; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void instantiation_usingNonEmptySetWithCoercingNullToZero_succeeds() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 15; e++) {
      source.add(e);
    }
    IntHashSet intHashSet = new IntHashSet(source, true);
    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 15; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }

  @Test
  void instantiation_usingNonEmptySetContainingNullWithCoercingNullToZero_succeeds() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 0; e++) {
      source.add(e);
    }
    source.add(null);

    IntHashSet intHashSet = new IntHashSet(source, true);
    assertThat(intHashSet.size()).isEqualTo(16);
  }

  @Test
  void instantiation_usingNonEmptySetContainingNullWithCoercingNullToZero_createdSetIsUsable() {
    HashSet<Integer> source = new HashSet<>();
    for (int e = -15; e < 0; e++) {
      source.add(e);
    }
    source.add(null);

    IntHashSet intHashSet = new IntHashSet(source, true);
    assertThat(intHashSet.size()).isEqualTo(16);

    for (int e = 1; e < 15; e++) {
      intHashSet.add(e);
    }

    assertThat(intHashSet.size()).isEqualTo(30);
    for (int e = -15; e < 0; e++) {
      assertThat(intHashSet.contains(e)).isTrue();
    }
  }
}
