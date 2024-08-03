package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetSizeTest {
  @Test
  void emptySet_size_isZero() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.size()).isZero();
  }

  @Test
  void singletonSet_size_isOne() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(1);
    assertThat(intHashSet.size()).isOne();
  }

  @Test
  void twoElementSet_size_isTwo() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(1);
    intHashSet.add(-1);
    assertThat(intHashSet.size()).isEqualTo(2);
  }

  @Test
  void nElementSet_size_isN() {
    IntHashSet intHashSet = new IntHashSet();
    for (int i = -50; i < 50; i++) {
      intHashSet.add(i);
    }
    assertThat(intHashSet.size()).isEqualTo(100);
  }
}
