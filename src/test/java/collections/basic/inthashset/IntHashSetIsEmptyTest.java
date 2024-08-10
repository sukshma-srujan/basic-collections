package collections.basic.inthashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetIsEmptyTest {
  @Test
  void emptySet_isEmpty_isTrue() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.isEmpty()).isTrue();
  }

  @Test
  void singletonSet_isEmpty_isFalse() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(0);
    assertThat(intHashSet.isEmpty()).isFalse();
  }

  @Test
  void twoElementSet_isEmpty_isFalse() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(-16);
    intHashSet.add(16);
    assertThat(intHashSet.isEmpty()).isFalse();
  }

  @Test
  void nElementSet_isEmpty_isFalse() {
    IntHashSet intHashSet = new IntHashSet();
    for (int i = -50; i < 50; i++) {
      intHashSet.add(i);
    }
    assertThat(intHashSet.isEmpty()).isFalse();
  }
}
