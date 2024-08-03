package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetInstantiationTest {
  @Test
  void noArg_instantiation_emptySet() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.size()).isZero();
    assertThat(intHashSet.isEmpty()).isTrue();
  }

  @Test
  void initialCapacity_instantiation_emptySet() {
    IntHashSet intHashSet = new IntHashSet(33);
    assertThat(intHashSet.size()).isZero();
    assertThat(intHashSet.isEmpty()).isTrue();
  }

  @Test
  void initialCapacity_instantiation_capacityOne_succeeds() {
    IntHashSet intHashSet = new IntHashSet(1);
    assertThat(intHashSet.size()).isZero();
    assertThat(intHashSet.isEmpty()).isTrue();
  }

  @Test
  void initialCapacity_instantiation_capacityLessThanOne_fails() {
    assertThatThrownBy(() -> new IntHashSet(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("initialCapacity must not be less than 1");
  }
}
