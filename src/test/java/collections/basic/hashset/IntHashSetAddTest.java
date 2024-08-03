package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetAddTest {
  @Test
  void oneElement_add_succeeds() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.add(10)).isTrue();
  }

  @Test
  void twoElements_add_succeeds() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.add(-1)).isTrue();
    assertThat(intHashSet.add(1)).isTrue();
  }

  @Test
  void nElements_add_succeeds() {
    IntHashSet intHashSet = new IntHashSet();
    for (int i = -50; i < 50; i++) {
      assertThat(intHashSet.add(i)).isTrue();
    }
  }

  @Test
  void emptySet_add_sameElementTwice_doesNotChangeSet() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.add(17)).isTrue();
    assertThat(intHashSet.add(17)).isFalse();
  }

  @Test
  void singletonSet_add_sameElementTwice_doesNotChangeSet() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.add(-17)).isTrue();
    assertThat(intHashSet.add(17)).isTrue();
    assertThat(intHashSet.add(17)).isFalse();
  }

  @Test
  void nSizeSet_add_sameElementTwice_doesNotChangeSet() {
    IntHashSet intHashSet = new IntHashSet();
    for (int i = -50; i < 50; i++) {
      intHashSet.add(i);
    }
    assertThat(intHashSet.add(17)).isFalse();
  }
}
