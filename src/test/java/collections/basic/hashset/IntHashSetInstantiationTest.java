package collections.basic.hashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetInstantiationTest {
  @Test
  void instantiation_emptySet() {
    IntHashSet intHashSet = new IntHashSet();
    assertThat(intHashSet.size()).isZero();
    assertThat(intHashSet.isEmpty()).isTrue();
  }
}
