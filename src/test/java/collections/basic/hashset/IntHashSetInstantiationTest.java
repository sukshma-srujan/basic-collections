package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetInstantiationTest {
  @Test
  void instantiation_noArg_succeeds() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.size()).isZero();
    assertThat(intHashSet.isEmpty()).isTrue();
  }

  @Test
  void instantiation_initialCapacityMoreThanOne_succeeds() {
    IntHashSet intHashSet = new IntHashSet(33);
    assertThat(intHashSet.size()).isZero();
    assertThat(intHashSet.isEmpty()).isTrue();
  }

  @Test
  void instantiation_initialCapacityOne_succeeds() {
    IntHashSet intHashSet = new IntHashSet(1);
    assertThat(intHashSet.size()).isZero();
    assertThat(intHashSet.isEmpty()).isTrue();
  }

  @Test
  void instantiation_initialCapacityZero_fails() {
    assertThatThrownBy(() -> new IntHashSet(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void instantiation_initialCapacityNegative_fails() {
    assertThatThrownBy(() -> new IntHashSet(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }

  @Test
  void instantiation_usingEmptyIntHashSet_createsEmptySet() {
    IntHashSet source = new IntHashSet();
    IntHashSet intHashSet = new IntHashSet(source);
    assertThat(intHashSet.size()).isZero();
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
}
