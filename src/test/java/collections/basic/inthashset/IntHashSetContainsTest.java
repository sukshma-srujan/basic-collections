package collections.basic.inthashset;

import static org.assertj.core.api.Assertions.assertThat;

import collections.basic.IntHashSet;
import org.junit.jupiter.api.Test;

class IntHashSetContainsTest {
  @Test
  void emptySet_contains_anyItem_isFalse() {
    IntHashSet intHashSet = new IntHashSet();

    assertThat(intHashSet.contains(-17)).isFalse();
    assertThat(intHashSet.contains(-1)).isFalse();
    assertThat(intHashSet.contains(0)).isFalse();
    assertThat(intHashSet.contains(1)).isFalse();
    assertThat(intHashSet.contains(17)).isFalse();
  }

  @Test
  void singletonSet_contains_forContainingItem_isTrue() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(0);

    assertThat(intHashSet.contains(0)).isTrue();
  }

  @Test
  void singletonSet_contains_forNonContainingItem_isFalse() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(0);

    assertThat(intHashSet.contains(1)).isFalse();
  }

  @Test
  void twoElementSet_contains_forContainingItem_isTrue() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(-12);
    intHashSet.add(12);

    assertThat(intHashSet.contains(12)).isTrue();
  }

  @Test
  void twoElementSet_contains_forNonContainingItem_isFalse() {
    IntHashSet intHashSet = new IntHashSet();
    intHashSet.add(-12);
    intHashSet.add(12);

    assertThat(intHashSet.contains(13)).isFalse();
  }

  @Test
  void nElementSet_contains_forContainingItem_isTrue() {
    IntHashSet intHashSet = new IntHashSet();
    for (int i = -150; i < 0; i++) {
      intHashSet.add(i);
    }

    assertThat(intHashSet.contains(-52)).isTrue();
  }

  @Test
  void nElementSet_contains_forNonContainingItem_isFalse() {
    IntHashSet intHashSet = new IntHashSet();
    for (int i = 0; i < 150; i++) {
      intHashSet.add(i);
    }

    assertThat(intHashSet.contains(152)).isFalse();
  }
}
